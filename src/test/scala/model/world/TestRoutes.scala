package model.world
import model.world.RegionTypes.*
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class TestRoutes {
  var portRouteManager: PortRouteManager = new PortRouteManager()
  var airportRouteManager: AirportRouteManager = new AirportRouteManager()

  val europeConfiguration: RegionConfiguration = RegionConfiguration("Europe", 747000000, 9, 5, 8, 9, 8)
  val unitedStatesConfiguration: RegionConfiguration = RegionConfiguration("United States", 331000000, 10, 2, 8, 1, 3)
  val russiaConfiguration: RegionConfiguration = RegionConfiguration("Russia", 143000000, 2, 3, 6, 9, 5)
  val japanConfiguration: RegionConfiguration = RegionConfiguration("Japan", 125000000, 4, 1, 7, 4, 8)
  val australiaConfiguration: RegionConfiguration = RegionConfiguration("Australia", 25000000, 6, 2, 1, 3, 5)
  val northAfricaConfiguration: RegionConfiguration = RegionConfiguration("North Africa", 178000000, 3, 7, 3, 9, 2)
  val southAfricaConfiguration: RegionConfiguration = RegionConfiguration("South Africa", 59000000, 7, 2, 5, 5, 7)
  var europe: Region = new BasicRegion(europeConfiguration)
  var russia: Region = new BasicRegion(russiaConfiguration)
  var japan: Region = new RegionWithPort(japanConfiguration, portRouteManager)
  var unitedStates: Region = new RegionWithPort(unitedStatesConfiguration, portRouteManager)
  var australia: Region = new RegionWithAirport(australiaConfiguration, airportRouteManager)
  var northAfrica: Region = new RegionWithAirport(northAfricaConfiguration, airportRouteManager)
  var southAfrica: Region = new RegionWithAirportAndPort(northAfricaConfiguration, airportRouteManager, portRouteManager)

  val testRoute: Route = Route(europe, unitedStates, ReachableMode.Border)
  val portTestRoute: Route = Route(japan, unitedStates, ReachableMode.Port)
  val airportTestRoute: Route = Route(australia, northAfrica, ReachableMode.Airport)

  @Test
  def testFirstRegion: Unit =
    assertEquals(europe, testRoute.fromRegion)

  @Test
  def testSecondRegion: Unit =
    assertEquals(unitedStates, testRoute.toRegion)

  @Test
  def testPortRoute: Unit =
    assertEquals(ReachableMode.Port, portTestRoute.reachableMode)

  @Test
  def testAirportRoute: Unit =
    assertEquals(ReachableMode.Airport, airportTestRoute.reachableMode)

  @Test
  def testAddPortRouteOfRegionWithoutPort: Unit =
    portRouteManager.addRoute(europe, unitedStates)
    assertEquals(List(), portRouteManager.getAllRoutesOf(europe))

  @Test
  def testAddOneRouteToPortRouteManager: Unit =
    portRouteManager.addRoute(japan, southAfrica)
    assertEquals(List(Route(japan, southAfrica, ReachableMode.Port)), portRouteManager.getAllRoutesOf(japan))

  @Test
  def testAddMultipleRouteToPortRouteManager: Unit =
    portRouteManager.addRoute(unitedStates, japan)
    portRouteManager.addRoute(unitedStates, southAfrica)
    assertEquals(List(Route(unitedStates, japan, ReachableMode.Port), Route(unitedStates, southAfrica, ReachableMode.Port)), portRouteManager.getAllRoutesOf(unitedStates))

  @Test
  def testAddAirportRouteOfRegionWithoutAirport: Unit =
    portRouteManager.addRoute(europe, unitedStates)
    assertEquals(List(), portRouteManager.getAllRoutesOf(europe))

  @Test
  def testAddOneRouteToAirportRouteManager: Unit =
    airportRouteManager.addRoute(australia, northAfrica)
    assertEquals(List(Route(australia, northAfrica, ReachableMode.Airport)), airportRouteManager.getAllRoutesOf(australia))

  @Test
  def testAddMultipleRouteToAirportRouteManager: Unit =
    airportRouteManager.addRoute(australia, northAfrica)
    airportRouteManager.addRoute(australia, southAfrica)
    assertEquals(List(Route(australia, northAfrica, ReachableMode.Airport), Route(australia, southAfrica, ReachableMode.Airport)), airportRouteManager.getAllRoutesOf(australia))
}
