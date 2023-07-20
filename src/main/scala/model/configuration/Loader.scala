package model.configuration

import Parsers.Parser
import model.configuration.builders.RawRouteBuilder.RawRoute
import model.configuration.builders.RegionIdentifierBuilder.RegionIdentifier
import model.configuration.Loader.RegionFile
import model.configuration.Parsers.RawRoute.RawRouteParser
import model.configuration.Parsers.Region.RegionParser
import model.configuration.Parsers.RegionIdentifier.RegionIdentifierParser
import model.configuration.Parsers.Virus.VirusParser
import model.infection.Virus
import model.world.RegionParameters.ReachableMode
import model.world.{AirportRouteManager, PortRouteManager, Region, World}

import scala.io.Source
import scala.util.{Failure, Success, Try, Using}

object Loader:

  val regionFilePath: String = "configs/regions.txt"
  val virusFilePath: String = "configs/virus.txt"
  val routesFilePath: String = "configs/routes.txt"
  val regionIdentifierFilePath: String = "configs/regionsIDs.txt"
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
  case class RegionIdentifierFile(override val path: String) extends ConfigurationFile[RegionIdentifier]

  object ConfigurationsLoader:
    given Parser[Region] = RegionParser()
    given Parser[Virus] = VirusParser()
    given Parser[RawRoute] = RawRouteParser()
    given Parser[RegionIdentifier] = RegionIdentifierParser()

    def load[T](file: ConfigurationFile[T])(using parser: Parser[T]): List[T] =
      File.readLinesFromResources(file.path)
        .filter(line => !line.startsWith("#"))
        .map(line => parser.parse(line))
        .filter(opt => opt.isDefined)
        .map(opt => opt.get)
        .toList

    def loadWorld(regionFile: RegionFile = RegionFile(regionFilePath)): World =
      val world: World = new World(load(regionFile))
      val routes: Iterable[RawRoute] = this.load(RouteFile(routesFilePath))
      routes.foreach(r => (world.getRegion(r.nameRegion1), world.getRegion(r.nameRegion2), r.reachableMode) match
        case (Some(a), Some(b), ReachableMode.Port) => {PortRouteManager().addRoute(a, b); PortRouteManager().addRoute(b, a)}
        case (Some(a), Some(b), ReachableMode.Airport) => {AirportRouteManager().addRoute(a, b); AirportRouteManager().addRoute(b,a)}
        case (Some(a), Some(b), ReachableMode.Border) => {a.addBorderingRegion(b); b.addBorderingRegion(a)}
        case _ =>  )
      world

    def loadVirus(virusFile: VirusFile = VirusFile(virusFilePath)): Option[Virus] =
      this.load(virusFile).headOption