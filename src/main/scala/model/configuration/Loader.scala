package model.configuration

import Parsers.Parser
import model.configuration.Builders.RawRoute
import model.configuration.Loader.RegionFile
import model.configuration.Parsers.RawRoute.RawRouteParser
import model.configuration.Parsers.Region.RegionParser
import model.configuration.Parsers.Virus.VirusParser
import model.infection.Virus
import model.world.RegionTypes.ReachableMode
import model.world.{PortRouteManager, Region, World}

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
  case class RouteFile(override val path: String) extends ConfigurationFile[RawRoute]

  object ConfigurationsLoader:
    given Parser[Region] = RegionParser()
    given Parser[Virus] = VirusParser()
    given Parser[RawRoute] = RawRouteParser()

    def load[T](file: ConfigurationFile[T])(using parser: Parser[T]): List[T] =
      File.readLinesFromResources(file.path)
        .filter(line => !line.startsWith("#"))
        .map(line => parser.parse(line))
        .filter(opt => opt.isDefined)
        .map(opt => opt.get)
        .toList
/*
    def loadWorld(regionFile: RegionFile = RegionFile("configs/regions.txt")): World =
      val world: World = new World(load(regionFile))
      val routes: Iterable[RawRoute] = this.load(RouteFile("configs/routes.txt"))
      routes.foreach(r => r.reachableMode match
        case ReachableMode.Port => PortRouteManager().addRoute()
        case ReachableMode.Airport => ???
        case ReachableMode.Border => ???)
      //TODO load routes
      //add routes to
      ???
*/

    def loadVirus(virusFile: VirusFile = VirusFile("configs/virus.txt")): Option[Virus] =
      this.load(virusFile).headOption