package com.org.runner

import com.org.schema.UserMetadata
import org.apache.spark.sql.Encoders
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{lag, unix_timestamp}
import org.apache.spark.sql.streaming.OutputMode

/**
  * @author Divakant
  *
  *         This class runs the stream job for Batch Update
  *
  *         Ingest from Kafka, Update counts,
  *
  *         Take care of Lags, Watermarking, Checkpointing
  *
  */

object MetadataStreamUpdate extends MetadataJob {

  val kafkaStreamingConsumerParams = Map[String,String](
    elems = GROUP_ID -> configuration.groupId,
    AUTO_OFFSET_RESET -> LATEST,
    HEARTBEAT_INTERVAL -> configuration.heartBeat,
    AUTO_OFFSET_RESET->configuration.autoOffset,
    SESSION_TIMEOUT->configuration.sessionTimeout,
    REQUEST_TIMEOUT -> configuration.requestTimeout
  )

  def runJob(inputData:String,outputData:String)={


    import spark.implicits._

    val stream=spark.readStream.format(KAFKA)
      .options(kafkaStreamingConsumerParams)
      .option(KAFKA_BROKERS,configuration.kafkaBrokers)
      .option(SUBSCRIBE,configuration.topic)
      .option(START_OFFSETS,"earliest")
      .option(ENABLE_AUTO_COMMIT,"false")
      .option(FAIl_ON_DATA_LOSS,"false")
      .load().withWatermark("timestamp",configuration.watermarkWindowTime).
      writeStream
      .outputMode(OutputMode.Append())
      .format(PARQUET)
      .option(CHECKPOINT_LOCATION, configuration.checkpoint)
      .start(inputData);

    val schema = Encoders.product[UserMetadata].schema

    val userData=spark.sqlContext.read.format(CSV).schema(schema)
      .option(HEADER,IS_HEADER)
      .option(TIMESTAMP_FORMAT_KEY, TIMESTAMP_FORMAT)
      .load(inputData)
      .as[UserMetadata]

    //"/home/rock/user-metadata/src/test/resources/test_data.csv"

    val kafkaData=userData.toDF().distinct()

    val window = Window.partitionBy($"userId").orderBy($"visitTime".desc)

   val result= kafkaData.withColumn("prev_value", lag($"visitTime",1).over(window))
      .withColumn("diff",
        -unix_timestamp($"visitTime")+unix_timestamp(lag($"visitTime", 1).
          over(window))
      ).na.fill(0)
    result.writeStream.outputMode(OutputMode.Append())
      .format(PARQUET)
      .option(CHECKPOINT_LOCATION, configuration.checkpoint)
      .start(outputData)
  }

}
