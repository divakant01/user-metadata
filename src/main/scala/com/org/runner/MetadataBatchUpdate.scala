package com.org.runner

import com.org.schema.UserMetadata
import org.apache.spark.sql.Encoders
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{lag, unix_timestamp}

/**
  * @author Divakant
  *
  *         This class will run the Batch Update for metadata
  *
  *         Compute row difference for each sorted timestamp per user id
  *
  *         Saves the output in the compressed format in hdfs
  *
  */
object MetadataBatchUpdate extends MetadataJob{

  def runJob(inputData:String,outputData:String)={

    import spark.implicits._

    val schema = Encoders.product[UserMetadata].schema

    val userData=spark.sqlContext.read.format(CSV).schema(schema)
      .option(HEADER,IS_HEADER)
      .option(TIMESTAMP_FORMAT_KEY, TIMESTAMP_FORMAT)
      .load(inputData)
      .as[UserMetadata]

    val metadataDF=userData.toDF().distinct()

    val window = Window.partitionBy($"userId").orderBy($"visitTime".desc)

    val diffData=metadataDF
     // .withColumn("prev_value", lag($"visitTime",1).over(window))
      .withColumn("diff_time",
        -unix_timestamp($"visitTime")+unix_timestamp(lag($"visitTime", 1).
          over(window))
      ).na.fill(0)

    diffData.show()

    diffData.write.format("com.databricks.spark.csv")
      .option(HEADER, "true")
      .option("codec", "org.apache.hadoop.io.compress.GzipCodec")
      .save(outputData)
  }
}
