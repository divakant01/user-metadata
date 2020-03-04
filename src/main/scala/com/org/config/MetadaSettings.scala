package com.org.config

import com.org.util.ConstantUtils
import com.typesafe.config.{Config, ConfigFactory}

/**
  * @author Divakant
  *
  *  This class loads all the configurations and params from property file
  *
  */
final class MetadaSettings(conf:Option[Config]=None) extends ConstantUtils{

  private val config = conf match{

    case Some(c) => c.withFallback(ConfigFactory.load())
    case _ => ConfigFactory.load
  }


  protected val kafka=config.getConfig(KAFKA_CONFIG)
  protected val spark=config.getConfig(SPARK_CONFIG)
  protected val application=config.getConfig(APP_CONFIG)

   val kafkaBrokers=kafka.getString(KAFKA_BROKERS)
   val topic=kafka.getString(KAFKA_TOPICS)
   val groupId=kafka.getString(GROUP_ID)
   val heartBeat=kafka.getString(GROUP_ID)
   val autoOffset=kafka.getString(GROUP_ID)
   val sessionTimeout=kafka.getString(GROUP_ID)
   val requestTimeout=kafka.getString(GROUP_ID)


   val sparkMaster=spark.getString(SPARK_MASTER)
   val checkpoint=application.getString(CHECKPOINT_KEY)
   val watermarkWindowTime=application.getString(WATERMARK_WINDOW_TIME)



}
