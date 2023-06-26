package model.world
import model.world.RegionTypes.*
import org.junit.Assert.{assertEquals, assertFalse}
import org.junit.{Before, Test}

class TestRegion {
  val regionInfectedAmount: Int = 0
  val newInfectedAmount: Int = 10
  val firstRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)
  val secondRegionConfiguration: RegionConfiguration = RegionConfiguration("United States", 746000000, 9, 5, 8, 9, 8)
  val thirdTestRegionConfiguration: RegionConfiguration = RegionConfiguration("Russia", 143000000, 2, 3, 6, 9, 5)
  val fourthTestRegionConfiguration: RegionConfiguration = RegionConfiguration("Japan", 125000000, 4, 1, 7, 4, 8)
  val fifthTestRegionConfiguration: RegionConfiguration = RegionConfiguration("Australia", 25000000, 6, 2, 1, 3, 5)
  val sixthTestRegionConfiguration: RegionConfiguration = RegionConfiguration("North Africa", 178000000, 3, 7, 3, 9, 2)
  val seventhTestRegionConfiguration: RegionConfiguration = RegionConfiguration("South Africa", 59000000, 7, 2, 5, 5, 7)
  var firstRegion: Region = new BasicRegion(firstRegionConfiguration)
  var secondRegion: Region = new BasicRegion(secondRegionConfiguration)
  var portRouteManager: PortRouteManager = new PortRouteManager()
  var airportRouteManager: AirportRouteManager = new AirportRouteManager()
  var thirdRegion: Region = new RegionWithPort(thirdTestRegionConfiguration, portRouteManager)
  var fourthRegion: Region = new RegionWithPort(fourthTestRegionConfiguration, portRouteManager)
  var fifthRegion: Region = new RegionWithAirport(fifthTestRegionConfiguration, airportRouteManager)
  var sixthRegion: Region = new RegionWithAirport(sixthTestRegionConfiguration, airportRouteManager)
  var seventhRegion: Region = new RegionWithAirportAndPort(sixthTestRegionConfiguration, airportRouteManager, portRouteManager)

  @Before
  def init: Unit =
    firstRegion = new BasicRegion(firstRegionConfiguration)
    secondRegion = new BasicRegion(secondRegionConfiguration)
    portRouteManager = new PortRouteManager()
    airportRouteManager = new AirportRouteManager()
    thirdRegion = new RegionWithPort(thirdTestRegionConfiguration, portRouteManager)
    fourthRegion = new RegionWithPort(fourthTestRegionConfiguration, portRouteManager)
    fifthRegion = new RegionWithAirport(fifthTestRegionConfiguration, airportRouteManager)
    sixthRegion = new RegionWithAirport(sixthTestRegionConfiguration, airportRouteManager)
    seventhRegion = new RegionWithAirportAndPort(sixthTestRegionConfiguration, airportRouteManager, portRouteManager)


  @Test
  def testRegionName: Unit =
    assertEquals(firstRegionConfiguration.name, firstRegion.name)

  @Test
  def testRegionPopulation: Unit =
    assertEquals(firstRegionConfiguration.population, firstRegion.population)

  @Test
  def testRegionRichness: Unit =
    assertEquals(firstRegionConfiguration.richness, firstRegion.richness)

  @Test
  def testRegionClimate: Unit =
    assertEquals(firstRegionConfiguration.climate, firstRegion.climate)

  @Test
  def testRegionBordersControl: Unit =
    assertEquals(firstRegionConfiguration.bordersControl, firstRegion.bordersControl)

  @Test
  def testRegionGlobalization: Unit =
    assertEquals(firstRegionConfiguration.globalization, firstRegion.globalization)

  @Test
  def testRegionPopulationDensity: Unit =
    assertEquals(firstRegionConfiguration.populationDensity, firstRegion.populationDensity)

  @Test
  def testIncrementInfectAmount: Unit =
    firstRegion.infectedAmount = newInfectedAmount
    assertEquals(newInfectedAmount, firstRegion.infectedAmount)

  @Test
  def testInitiallyEmptyBorderingRegions: Unit =
    assertEquals(List(), firstRegion.getReachableRegions)

  @Test
  def testAddOneBorderingRegion: Unit =
    firstRegion.addBorderingRegion(secondRegion)
    assertEquals(List((secondRegion, ReachableMode.Border)), firstRegion.getReachableRegions)

  @Test
  def testReachableRegionsByBorders: Unit =
    firstRegion.addBorderingRegions(List(secondRegion, thirdRegion, fourthRegion))
    assertEquals(List((secondRegion, ReachableMode.Border), (thirdRegion, ReachableMode.Border), (fourthRegion, ReachableMode.Border)), firstRegion.getReachableRegions)

  @Test
  def testNotReachableRegionsByBorders: Unit =
    firstRegion.addBorderingRegions(List(secondRegion, thirdRegion))
    assertFalse(firstRegion.getReachableRegions.contains(fourthRegion))

  @Test
  def testReachableRegionsByPort: Unit =
    portRouteManager.addRoute(thirdRegion, fourthRegion)
    assertEquals(List((fourthRegion, ReachableMode.Port)), thirdRegion.getReachableRegions)

  @Test
  def testReachableRegionsByBordersAndPort: Unit =
    thirdRegion.addBorderingRegion(secondRegion)
    portRouteManager.addRoute(thirdRegion, fourthRegion)
    assertEquals(List((secondRegion, ReachableMode.Border), (fourthRegion, ReachableMode.Port)), thirdRegion.getReachableRegions)

  @Test
  def testReachableRegionsByAirport: Unit =
    airportRouteManager.addRoute(fifthRegion, sixthRegion)
    assertEquals(List((sixthRegion, ReachableMode.Airport)), fifthRegion.getReachableRegions)

  @Test
  def testReachableRegionsByAirportAndPort: Unit =
    airportRouteManager.addRoute(seventhRegion, sixthRegion)
    portRouteManager.addRoute(seventhRegion, fourthRegion)
    assertEquals(List((sixthRegion, ReachableMode.Airport),(fourthRegion, ReachableMode.Port)), seventhRegion.getReachableRegions)
}
