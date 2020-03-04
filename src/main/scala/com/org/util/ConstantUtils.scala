package com.org.util

/**
  * @author Divakant on 12/22/19.
  */
trait ConstantUtils {

  // CONFIG Constants
  protected val KAFKA_CONFIG = "kafka"
  protected val SPARK_CONFIG = "spark"
  protected val APP_CONFIG = "application"

  protected val KAFKA_BROKERS = "hosts"
  protected val KAFKA_TOPICS = "topics"
  protected val GROUP_ID = "group.id"

  protected val SPARK_MASTER = "master"

  //Utility Constants
  protected val CSV = "csv"
  protected val HEADER = "header"
  protected val IS_HEADER = "true"
  protected val TIMESTAMP_FORMAT_KEY = "timestampFormat"
  protected val TIMESTAMP_FORMAT = "MM-dd-yyyy hh:mm:ss"

  //Kafka Configs
  protected val KAFKA = "kafka"
  protected val LATEST = "latest"
  protected val AUTO_OFFSET_RESET = "auto.offset.reset"
  protected val HEARTBEAT_INTERVAL = "heartbeat.interval.ms"
  protected val SESSION_TIMEOUT = "session.timeout.ms"
  protected val REQUEST_TIMEOUT = "request.timeout.ms"
  protected val SUBSCRIBE = "subscribe"
  protected val ENABLE_AUTO_COMMIT = "enable.auto.commit"
  protected val FAIl_ON_DATA_LOSS = "failOnDataLoss"
  protected val START_OFFSETS = "startingOffsets"
  protected val PARQUET = "parquet"
  protected val CHECKPOINT_LOCATION = "checkpointLocation"
  protected val CHECKPOINT_KEY = "checkpoint_location"
  protected val WATERMARK_WINDOW_TIME = "watermark_window_time"



}
