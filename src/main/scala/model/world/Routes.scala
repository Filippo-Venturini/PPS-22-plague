package model.world
import RegionParameters.*

/**
 * Case Class that represent a route between two regions
 *
 * @param fromRegion  the starting region
 * @param toRegion  the ending region
 * @param reachableMode the type of reachability (via port or via airport)
 */
case class Route(fromRegion: Region, toRegion: Region, reachableMode: ReachableMode)

/**
 * Class that represent a RouteManager that is responsible for handling routes between regions
 */
abstract class RouteManager:
  protected var allRoutes: List[Route] = List()

  /**
   * @param fromRegion the starting region
   * @param toRegion the ending region
   */
  def addRoute(fromRegion: Region, toRegion: Region): Unit

  /**
   * @param region the region of which you want to know the routes
   * @return all the routes corresponding to the region
   */
  def getAllRoutesOf(region: Region): List[Route] = this.allRoutes.filter(r => r.fromRegion equals region)

/**
 * Trait that represent a route manager that handle port routes
 */
trait PortRouteManager extends RouteManager:
  /**
   * @param region the region to be checked
   * @return true if the region has a port
   */
  def hasPort(region: Region): Boolean

/**
 * Companion Object that returns the singleton of the PortRouteManager
 */
object PortRouteManager:
  private val portRouteManager: PortRouteManager = new BasicPortRouteManger

  /**
   * @return the singleton of the PortRouteManager
   */
  def apply(): PortRouteManager = this.portRouteManager

  /**
   * Class that hide the implementation of the PortRouteManager
   */
  private class BasicPortRouteManger extends PortRouteManager:
    /**
     * Add a new port route between the two regions
     *
     * @param fromRegion the starting region
     * @param toRegion the ending region
     */
    override def addRoute(fromRegion: Region, toRegion: Region): Unit = (fromRegion, toRegion) match
      case (fromRegion, toRegion) if this.hasPort(fromRegion) && this.hasPort(toRegion) => this.allRoutes = this.allRoutes :+ Route(fromRegion, toRegion, ReachableMode.Port)
      case _ =>

    def hasPort(region: Region): Boolean = region match
      case _ : RegionWithPort => true
      case _ : RegionWithAirportAndPort => true
      case _ => false

/**
 * Trait that represent a route manager that handle airport routes
 */
trait AirportRouteManager extends RouteManager:
  /**
   * @param region the region to be checked
   * @return true if the region has an airport
   */
  def hasAirport(region: Region): Boolean

/**
 * Companion Object that returns the singleton of the AirportRouteManager
 */
object AirportRouteManager:
  private val airportRouteManager: AirportRouteManager = new BasicAirportRouteManager

  /**
   * @return the singleton of the AirportRouteManager
   */
  def apply(): AirportRouteManager = this.airportRouteManager
  /**
   * Class that hide the implementation of the AirportRouteManager
   */
  private class BasicAirportRouteManager extends AirportRouteManager:
    /**
     * Add a new airport route between the two regions
     *
     * @param fromRegion the starting region
     * @param toRegion the ending region
     */
    override def addRoute(fromRegion: Region, toRegion: Region): Unit = (fromRegion, toRegion) match
      case (fromRegion, toRegion) if this.hasAirport(fromRegion) && this.hasAirport(toRegion) => this.allRoutes = this.allRoutes :+ Route(fromRegion, toRegion, ReachableMode.Airport)
      case _ =>

    def hasAirport(region: Region): Boolean = region match
      case _ : RegionWithAirport => true
      case _ : RegionWithAirportAndPort => true
      case _ => false