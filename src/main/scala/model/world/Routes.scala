package model.world
import RegionTypes.*

case class Route(firstRegion: Region, secondRegion: Region, reachableMode: ReachableMode)

class RouteManager()
