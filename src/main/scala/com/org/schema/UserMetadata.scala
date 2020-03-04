package com.org.schema

/**
  * @author Divakant Pandey on 12/21/19.
  * This is the schema for the Metadata class for User behaviour
  *
  */
case class UserMetadata(
                       userId:String,
                       url:String,
                       visitTime:java.sql.Timestamp
                       )
