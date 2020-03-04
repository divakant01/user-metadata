package com.org.runner

import java.text.SimpleDateFormat
import java.util.Calendar

import org.junit.Assert._
import org.junit._

@Test
class MetadataBatchUpdateTest {


    @Test
    def testBatchSuccessScenarioAllUsers() = {
        val random=new scala.util.Random
        val format = new SimpleDateFormat("d-M-y-hh-mm-ss-"+random.nextInt(100))

        val inputData="src/test/resources/input/test_data.csv"
        val outputData="src/test/resources/output/"+format.format(Calendar.getInstance().getTime())

        MetadataBatchUpdate.runJob(inputData,outputData)
        assertTrue(new java.io.File(outputData+"/_SUCCESS").exists())
    }

    @Test
    def testBatchSuccessScenarioSingleUsers() = {
        val random=new scala.util.Random
        val format = new SimpleDateFormat("d-M-y-hh-mm-ss-"+random.nextInt(100))

        val inputData="src/test/resources/input/test_data_1user.csv"
        val outputData="src/test/resources/output/"+format.format(Calendar.getInstance().getTime())

        MetadataBatchUpdate.runJob(inputData,outputData)
        assertTrue(new java.io.File(outputData+"/_SUCCESS").exists())
    }



}


