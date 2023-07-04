package model.configuration

import Parsers.Parser
import model.configuration.Loader.RegionFile
import model.configuration.Parsers.Region.RegionParser
import model.configuration.Parsers.Virus.VirusParser
import model.infection.Virus
import model.world.{Region, World}

import scala.io.Source
import scala.util.{Failure, Success, Try, Using}

object Loader:

  val regionFile: RegionFile = RegionFile("configs/regions.txt")
  val virusFile: VirusFile = VirusFile("configs/virus.txt")
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

    def load[T](file: ConfigurationFile[T])(using parser: Parser[T]): List[T] =
      File.readLinesFromResources(file.path)
        .filter(line => !line.startsWith("#"))
        .map(line => parser.parse(line))
        .filter(opt => opt.isDefined)
        .map(opt => opt.get)
        .toList

    def loadWorld(regionFile: RegionFile = RegionFile("configs/regions.txt")): World =
      val world: World = new World(load(regionFile))
      //TODO load routes
      //add routes to 
      ???