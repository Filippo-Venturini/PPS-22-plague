package model.world
import RegionTypes.*

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
  
trait PortRouteManager extends RouteManager:
  def hasPort[P <: Port](region: Region): Boolean 
  
object PortRouteManager:
  private val portRouteManager: PortRouteManager = new BasicPortRouteManger

  def apply(): PortRouteManager = this.portRouteManager

  /**
   * Class that represent a route manager that handle only port routes
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
  
    /**
     * @param region the region to be checked
     * @tparam P check that the type of the region is allowed to have a port
     * @return true if the region has a port
     */
    def hasPort[P <: Port](region: Region): Boolean = region match
      case _ : P => true
      case _ => false

trait AirportRouteManager extends RouteManager:
  def hasAirport[A <: Airport](region: Region): Boolean
  
object AirportRouteManager:

  private val airportRouteManager: AirportRouteManager = new BasicAirportRouteManager

  def apply(): AirportRouteManager = this.airportRouteManager
  /**
   * Class that represent a route manager that handle only airport routes
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
  
    /**
     * @param region the region to be checked
     * @tparam A check that the type of the region is allowed to have an airport
     * @return true if the region has an airport
     */
    def hasAirport[A <: Airport](region: Region): Boolean = region match
      case _ : A => true
      case _ => false