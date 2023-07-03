package model.world
import model.world.RegionTypes.*
import model.world.TestRegionConfigurations.*
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class TestRoutes {
  var portRouteManager: PortRouteManager = PortRouteManager()
  var airportRouteManager: AirportRouteManager = new AirportRouteManager()
  var firstBasicRegion: Region = new BasicRegion(europeConfiguration)
  var secondBasicRegion: Region = new BasicRegion(russiaConfiguration)
  var firstRegionWithPort: Region = new RegionWithPort(japanConfiguration, portRouteManager)
  var secondRegionWithPort: Region = new RegionWithPort(unitedStatesConfiguration, portRouteManager)
  var firstRegionWithAirport: Region = new RegionWithAirport(australiaConfiguration, airportRouteManager)
  var secondRegionWithAirport: Region = new RegionWithAirport(northAfricaConfiguration, airportRouteManager)
  var regionWithAirportAndPort: Region = new RegionWithAirportAndPort(northAfricaConfiguration, airportRouteManager, portRouteManager)
  val testRoute: Route = Route(firstBasicRegion, secondRegionWithPort, ReachableMode.Border)
  val portTestRoute: Route = Route(firstRegionWithPort, secondRegionWithPort, ReachableMode.Port)
  val airportTestRoute: Route = Route(firstRegionWithAirport, secondRegionWithAirport, ReachableMode.Airport)

  @Test
  def testFirstRegion: Unit =
    assertEquals(firstBasicRegion, testRoute.fromRegion)

  @Test
  def testSecondRegion: Unit =
    assertEquals(secondRegionWithPort, testRoute.toRegion)

  @Test
  def testPortRoute: Unit =
    assertEquals(ReachableMode.Port, portTestRoute.reachableMode)

  @Test
  def testAirportRoute: Unit =
    assertEquals(ReachableMode.Airport, airportTestRoute.reachableMode)

  @Test
  def testAddPortRouteOfRegionWithoutPort: Unit =
    portRouteManager.addRoute(firstBasicRegion, secondRegionWithPort)
    assertEquals(List(), portRouteManager.getAllRoutesOf(firstBasicRegion))

  @Test
  def testAddOneRouteToPortRouteManager: Unit =
    portRouteManager.addRoute(firstRegionWithPort, regionWithAirportAndPort)
    assertEquals(List(Route(firstRegionWithPort, regionWithAirportAndPort, ReachableMode.Port)), portRouteManager.getAllRoutesOf(firstRegionWithPort))

  @Test
  def testAddMultipleRouteToPortRouteManager: Unit =
    portRouteManager.addRoute(secondRegionWithPort, firstRegionWithPort)
    portRouteManager.addRoute(secondRegionWithPort, regionWithAirportAndPort)
    assertEquals(List(Route(secondRegionWithPort, firstRegionWithPort, ReachableMode.Port), Route(secondRegionWithPort, regionWithAirportAndPort, ReachableMode.Port)), portRouteManager.getAllRoutesOf(secondRegionWithPort))

  @Test
  def testAddAirportRouteOfRegionWithoutAirport: Unit =
    portRouteManager.addRoute(firstBasicRegion, secondRegionWithPort)
    assertEquals(List(), portRouteManager.getAllRoutesOf(firstBasicRegion))

  @Test
  def testAddOneRouteToAirportRouteManager: Unit =
    airportRouteManager.addRoute(firstRegionWithAirport, secondRegionWithAirport)
    assertEquals(List(Route(firstRegionWithAirport, secondRegionWithAirport, ReachableMode.Airport)), airportRouteManager.getAllRoutesOf(firstRegionWithAirport))

  @Test
  def testAddMultipleRouteToAirportRouteManager: Unit =
    airportRouteManager.addRoute(firstRegionWithAirport, secondRegionWithAirport)
    airportRouteManager.addRoute(firstRegionWithAirport, regionWithAirportAndPort)
    assertEquals(List(Route(firstRegionWithAirport, secondRegionWithAirport, ReachableMode.Airport), Route(firstRegionWithAirport, regionWithAirportAndPort, ReachableMode.Airport)), airportRouteManager.getAllRoutesOf(firstRegionWithAirport))
}
