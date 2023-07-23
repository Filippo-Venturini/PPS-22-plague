package model.configuration

import Parsers.Parser
import model.configuration.builders.RawRouteBuilder.RawRoute
import model.configuration.builders.RegionIdentifierBuilder.RegionIdentifier
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
    /**
     * @param path the file resource path
     * @return an Iterable of the file's lines
     */
    def readLinesFromResources(path: String): Iterable[String] =
      Try(Source.fromResource(path).getLines()) match
        case Success(value) => value.toSeq
        case Failure(_) => Seq()

    /**
     * A ConfigurationFile represents a file that can be loaded and parsed.
     * @tparam T the type of the file
     */
    trait ConfigurationFile[T]:
      /**
       * @return the file path
       */
      def path: String

    /**
     * A file that contains regions as strings
     * @param path the file path
     */
    case class RegionFile(override val path: String) extends ConfigurationFile[Region]

    /**
     * A file that contains viruses as strings
     * @param path the file path
     */
    case class VirusFile(override val path: String) extends ConfigurationFile[Virus]

    /**
     * A file that contains RawRoutes as strings
     * @param path the file path
     */
    case class RouteFile(override val path: String) extends ConfigurationFile[RawRoute]

    /**
     * A file that contains RegionIdentifiers as strings
     * @param path the file path
     */
    case class RegionIdentifierFile(override val path: String) extends ConfigurationFile[RegionIdentifier]

  object ConfigurationsLoader:
    import Loader.File.{ConfigurationFile, RegionFile, VirusFile, RouteFile}
    given Parser[Region] = RegionParser()
    given Parser[Virus] = VirusParser()
    given Parser[RawRoute] = RawRouteParser()
    given Parser[RegionIdentifier] = RegionIdentifierParser()

    /**
     * read a configuration file converting each line. Correctly parsed lines are returned while bad parsed ones are discarded.
     * Lines starting with # are ignored.
     * @param file the file to be parsed
     * @param parser the parser to use to convert the file
     * @tparam T the parsing type
     * @return the objects of type T correctly parsed
     */
    def load[T](file: ConfigurationFile[T])(using parser: Parser[T]): List[T] =
      File.readLinesFromResources(file.path)
        .filter(line => !line.startsWith("#"))
        .map(line => parser.parse(line))
        .filter(opt => opt.isDefined)
        .map(opt => opt.get)
        .toList

    /**
     * create a new object of type world using the information contained in the given regionFile.
     * @param regionFile the file to convert. If not indicated use the default one.
     * @return a world containing all the regions contained in the given file.
     */
    def loadWorld(regionFile: RegionFile = RegionFile(regionFilePath)): World =
      val world: World = new World(load(regionFile))
      val routes: Iterable[RawRoute] = this.load(RouteFile(routesFilePath))
      routes.foreach(r => (world.getRegionByName(r.nameRegion1), world.getRegionByName(r.nameRegion2), r.reachableMode) match
        case (Some(a), Some(b), ReachableMode.Port) => {PortRouteManager().addRoute(a, b); PortRouteManager().addRoute(b, a)}
        case (Some(a), Some(b), ReachableMode.Airport) => {AirportRouteManager().addRoute(a, b); AirportRouteManager().addRoute(b,a)}
        case (Some(a), Some(b), ReachableMode.Border) => {a.addBorderingRegion(b); b.addBorderingRegion(a)}
        case _ =>  )
      world

    /**
     * create a new Virus from the given file.
     * @param virusFile the file to load
     * @return the first correctly parsed line of the given file. If no line can be correctly parsed, returns an empty option
     */
    def loadVirus(virusFile: VirusFile = VirusFile(virusFilePath)): Option[Virus] =
      this.load(virusFile).headOption