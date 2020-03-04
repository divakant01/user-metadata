package com.org.util

import com.org.config.Configuration
import org.apache.spark.sql.SparkSession

/**
  * @author Divakant
  *
  * This is the connection manager for Spark sessions and context
  *
  */
object SparkSessionManager extends Configuration{

  val getSparkSession=SparkSession.builder().
    appName(APP_NAME)
    .master(configuration.sparkMaster)
    .getOrCreate()
}
