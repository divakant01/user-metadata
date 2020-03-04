package com.org

import com.org.runner.{JobResolverFactory}
import org.apache.log4j.Logger
import org.apache.log4j.Level


/**
  * @author Divakant Pandey
  *
  * This class is the entry point for User metadata
  *
  *
 */

object SparkJobRunner  {


  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)



  def main(args:Array[String]): Unit ={

    val isBatchJob=args.apply(0)
    val inputData=args.apply(1)
    val outputData=args.apply(2)

    JobResolverFactory.run(isBatchJob,inputData,outputData)
  }
}
