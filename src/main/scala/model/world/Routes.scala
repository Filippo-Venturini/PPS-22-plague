package model.world
import RegionTypes.*

case class Route(firstRegion: Region, secondRegion: Region, reachableMode: ReachableMode)

abstract class RouteManager:
  protected var allRoutes: List[Route] = List()
  def addRoute(firstRegion: Region, secondRegion: Region): Unit
  def getAllRoutesOf(region: Region): List[Route] = this.allRoutes.filter(r => r.firstRegion equals region)

class PortRouteManager extends RouteManager:
  override def addRoute(firstRegion: Region, secondRegion: Region): Unit = this.allRoutes = this.allRoutes :+ Route(firstRegion, secondRegion, ReachableMode.Port)

class AirportRouteManager extends RouteManager:
  override def addRoute(firstRegion: Region, secondRegion: Region): Unit = this.allRoutes = this.allRoutes :+ Route(firstRegion, secondRegion, ReachableMode.Airport)