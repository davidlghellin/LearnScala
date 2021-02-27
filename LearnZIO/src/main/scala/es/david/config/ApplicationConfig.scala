package es.david.config

import es.david.config.ApplicationConfig.DataSource
import zio.config._
import zio.config.magnolia.descriptor

final case class ApplicationConfig(dataSource: DataSource, region: Option[String])

object ApplicationConfig {
  val config: ConfigDescriptor[ApplicationConfig] = descriptor[ApplicationConfig]

  sealed trait DataSource

  final case class Database(host: String, port: Int, username: String, password: String) extends DataSource

  final case class S3Bucket(bucketName: String, prefix: String) extends DataSource

  final case class Kafka(brokers: List[String], topicName: String) extends DataSource

  final case class Par(data: Database, s3: S3Bucket) extends DataSource

}
