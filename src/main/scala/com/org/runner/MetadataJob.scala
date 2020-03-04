package com.org.runner

import com.org.config.Configuration
import com.org.util.{ConstantUtils, SparkSessionManager}

/**
  * @author Divakant
  *
  */
trait MetadataJob extends Configuration with ConstantUtils{

  val spark=SparkSessionManager.getSparkSession

   def runJob(inputData:String,outputData:String)

}
