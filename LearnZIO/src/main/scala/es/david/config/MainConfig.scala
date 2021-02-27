package es.david.config

import zio.config._
import zio.config.typesafe.TypesafeConfigSource

object MainConfig {
  def main(args: Array[String]): Unit = {
    val jsonSource =
      s"""
         |{
         |   "dataSource": {
         |     "Par": {
         |       "data": {
         |         "host"     : "host"
         |         "port"     : "2222"
         |         "username" : "user"
         |         "password" : "pw"
         |       }
         |       "s3": {
         |         "bucketName" : "unBucket"
         |         "prefix"     : "awss3"
         |       }
         |     }
         |   }
         |  "region" : "ap-sur-2"
         |}
         |""".stripMargin
    val sourceEither: Either[ReadError[String], ConfigSource] =
      TypesafeConfigSource.fromHoconString(jsonSource)

    val configResult = for {
      source <- sourceEither
      aplicationConf <- read(ApplicationConfig.config from source)
    } yield Core.run(aplicationConf)

    println(configResult)
  }
}

object Core {
  def run(applicationConfig: ApplicationConfig): Unit = {
    applicationConfig.dataSource match {
      case ApplicationConfig.Par(data, s3) => println(s"$data $s3")
      case database@ApplicationConfig.Database(_, _, _, _) => println(s"Database: $database")
      case b3@ApplicationConfig.S3Bucket(_, _) => println(s"s3 bucket $b3")
      case ApplicationConfig.Kafka(brokers, topicName) => println(s"kafka: $brokers, $topicName")
    }
  }
}