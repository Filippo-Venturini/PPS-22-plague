package model.configuration

import Parsers.Parser
import model.configuration.Parsers.Region.RegionParser
import model.configuration.Parsers.Virus.VirusParser
import model.infection.Virus
import model.world.Region

import scala.io.Source
import scala.util.{Failure, Success, Try, Using}

object Loader:
  object File:
    def readLinesFromResources(path: String): Iterable[String] =
      Try(Source.fromResource(path).getLines()) match
        case Success(value) => value.toSeq
        case Failure(_) => Seq()

  trait ConfigurationFile[T]:
    def path: String

  case class RegionFile(override val path: String) extends ConfigurationFile[Region]
  case class VirusFile(override val path: String) extends ConfigurationFile[Virus]

  object ConfigurationsLoader:
    given Parser[Region] = RegionParser()
    given Parser[Virus] = VirusParser()

    def load[T](file: ConfigurationFile[T])(using parser: Parser[T]): Iterable[T] =
      File.readLinesFromResources(file.path)
        .filter(line => !line.startsWith("#"))
        .map(line => parser.parse(line))
        .filter(opt => opt.isDefined)
        .map(opt => opt.get)
        .toSeq