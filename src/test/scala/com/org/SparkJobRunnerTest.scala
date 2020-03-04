package com.org

import java.text.SimpleDateFormat
import java.util.Calendar

import org.junit._
import Assert._

@Test
class SparkJobRunnerTest {

    val random=new scala.util.Random
    val format = new SimpleDateFormat("d-M-y-hh-mm-ss-"+random.nextInt(100))

    val inputData="src/test/resources/input/test_data.csv"
    val outputData="src/test/resources/output/"+format.format(Calendar.getInstance().getTime())
    val isBatchJob="true"


    @Test
    def testBatch() = {
        var args= Array[String](isBatchJob, inputData,outputData)
        SparkJobRunner.main(args)
        assertTrue(new java.io.File(outputData+"/_SUCCESS").exists())
    }

    @Test
    def testInvalidInputFile() = {
        try {
            var args = Array[String](isBatchJob, "", outputData)
            SparkJobRunner.main(args)
        }catch{
            case ex : IllegalArgumentException => assertTrue(true)
        }
    }

    @Test
    def testInvalidOutputFile() = {
        try {
            var args = Array[String](isBatchJob, inputData, "")
            SparkJobRunner.main(args)
        }catch{
            case ex : IllegalArgumentException => assertTrue(true)
        }
    }


    @Test
    def testNullInputFile() = {
        try {
            var args = Array[String](isBatchJob, null, outputData)
            SparkJobRunner.main(args)
        }catch{
            case ex : IllegalArgumentException => assertTrue(true)
        }
    }

    @Test
    def testNullOutputFile() = {
        try {
            var args = Array[String](isBatchJob, inputData, null)
            SparkJobRunner.main(args)
        }catch{
            case ex : IllegalArgumentException => assertTrue(true)
        }
    }

    @Test
    def testInvalidJobType() = {
        try {
            var args = Array[String]("job", inputData, outputData)
            SparkJobRunner.main(args)
        }catch{
            case ex : IllegalArgumentException => assertTrue(true)
        }
    }
}


