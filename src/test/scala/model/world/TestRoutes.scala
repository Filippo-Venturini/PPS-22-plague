package model.world
import model.world.RegionTypes.*
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class TestRoutes {
  val firstTestRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 747000000, 9, 5, 8, 9, 8)
  val secondTestRegionConfiguration: RegionConfiguration = RegionConfiguration("United States", 331000000, 10, 2, 8, 1, 3)
  val thirdTestRegionConfiguration: RegionConfiguration = RegionConfiguration("Russia", 143000000, 2, 3, 6, 9, 5)
  val firstTestRegion: Region = new BasicRegion(firstTestRegionConfiguration)
  val secondTestRegion: Region = new BasicRegion(secondTestRegionConfiguration)
  val thirdTestRegion: Region = new BasicRegion(thirdTestRegionConfiguration)
  val testRoute: Route = Route(firstTestRegion, secondTestRegion, ReachableMode.Border)
  val portTestRoute: Route = Route(firstTestRegion, secondTestRegion, ReachableMode.Port)
  val airportTestRoute: Route = Route(firstTestRegion, secondTestRegion, ReachableMode.Airport)
  val portRouteManager: RouteManager = new PortRouteManager()
  val airportRouteManager: RouteManager = new AirportRouteManager()

  @Test
  def testFirstRegion: Unit =
    assertEquals(firstTestRegion, testRoute.fromRegion)

  @Test
  def testSecondRegion: Unit =
    assertEquals(secondTestRegion, testRoute.toRegion)

  @Test
  def testPortRoute: Unit =
    assertEquals(ReachableMode.Port, portTestRoute.reachableMode)

  @Test
  def testAirportRoute: Unit =
    assertEquals(ReachableMode.Airport, airportTestRoute.reachableMode)

  @Test
  def testAddOneRouteToPortRouteManager: Unit =
    portRouteManager.addRoute(firstTestRegion, secondTestRegion)
    assertEquals(List(Route(firstTestRegion, secondTestRegion, ReachableMode.Port)), portRouteManager.getAllRoutesOf(firstTestRegion))

  @Test
  def testAddMultipleRouteToPortRouteManager: Unit =
    portRouteManager.addRoute(firstTestRegion, secondTestRegion)
    portRouteManager.addRoute(firstTestRegion, thirdTestRegion)
    assertEquals(List(Route(firstTestRegion, secondTestRegion, ReachableMode.Port), Route(firstTestRegion, thirdTestRegion, ReachableMode.Port)), portRouteManager.getAllRoutesOf(firstTestRegion))

  @Test
  def testAddOneRouteToAirportRouteManager: Unit =
    airportRouteManager.addRoute(firstTestRegion, secondTestRegion)
    assertEquals(List(Route(firstTestRegion, secondTestRegion, ReachableMode.Airport)), airportRouteManager.getAllRoutesOf(firstTestRegion))

  @Test
  def testAddMultipleRouteToAirportRouteManager: Unit =
    airportRouteManager.addRoute(firstTestRegion, secondTestRegion)
    airportRouteManager.addRoute(firstTestRegion, thirdTestRegion)
    assertEquals(List(Route(firstTestRegion, secondTestRegion, ReachableMode.Airport), Route(firstTestRegion, thirdTestRegion, ReachableMode.Airport)), airportRouteManager.getAllRoutesOf(firstTestRegion))
}
