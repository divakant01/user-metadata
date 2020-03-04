package com.org.runner

/**
  * @author Divakant
  */
object JobResolverFactory {

  @throws(classOf[IllegalArgumentException])
  def run(isBatch:String,inputData:String,outputData:String)={

    if(isBatch == "" || isBatch==null || inputData=="" || inputData==null
    ||outputData=="" || outputData==null)
      throw new IllegalArgumentException("Invalid parameters: "+isBatch+","
        +inputData+","
        +outputData)

    if(isBatch.equalsIgnoreCase("true"))
       MetadataBatchUpdate.runJob(inputData,outputData)
    else if(isBatch.equalsIgnoreCase("false"))
       MetadataStreamUpdate.runJob(inputData,outputData)
    else
      throw new IllegalArgumentException("IsBatch param takes true/false arg only "+isBatch)
  }

}
