package model.configuration

import Parsers.Parser

import scala.io.Source
import scala.util.{Success, Failure, Using, Try}

object File:
  def readLinesFromResources(path: String): Iterable[String] =
    Try(Source.fromResource(path).getLines()) match
      case Success(value) => value.toSeq
      case Failure(_) => Seq()

object Loader:
  trait ConfigurationFile:
    def path: String

  case class RegionFile(override val path: String) extends ConfigurationFile

  object ConfigurationsLoader:
    def load[T](file: ConfigurationFile)(using parser: Parser[T]): Iterable[T] =
      File.readLinesFromResources(file.path)
        .map(line => parser.parse(line))
        .filter(opt => opt.isDefined)
        .map(opt => opt.get)
        .toSeq