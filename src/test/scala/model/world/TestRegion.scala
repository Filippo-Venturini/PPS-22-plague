package model.world
import model.world.RegionTypes.*
import org.junit.Assert.{assertEquals, assertFalse}
import org.junit.{Before, Test}

class TestRegion {
  val regionInfectedAmount: Int = 0
  val newInfectedAmount: Int = 10
  val europeConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)
  val unitedStatesConfiguration: RegionConfiguration = RegionConfiguration("United States", 746000000, 9, 5, 8, 9, 8)
  val russiaConfiguration: RegionConfiguration = RegionConfiguration("Russia", 143000000, 2, 3, 6, 9, 5)
  val japanConfiguration: RegionConfiguration = RegionConfiguration("Japan", 125000000, 4, 1, 7, 4, 8)
  val australiaConfiguration: RegionConfiguration = RegionConfiguration("Australia", 25000000, 6, 2, 1, 3, 5)
  val northAfricaConfiguration: RegionConfiguration = RegionConfiguration("North Africa", 178000000, 3, 7, 3, 9, 2)
  val southAfricaConfiguration: RegionConfiguration = RegionConfiguration("South Africa", 59000000, 7, 2, 5, 5, 7)
  var europe: Region = new BasicRegion(europeConfiguration)
  var russia: Region = new BasicRegion(russiaConfiguration)
  var portRouteManager: PortRouteManager = new PortRouteManager()
  var airportRouteManager: AirportRouteManager = new AirportRouteManager()
  var unitedStates: Region = new RegionWithPort(unitedStatesConfiguration, portRouteManager)
  var japan: Region = new RegionWithPort(japanConfiguration, portRouteManager)
  var australia: Region = new RegionWithAirport(australiaConfiguration, airportRouteManager)
  var northAfrica: Region = new RegionWithAirport(northAfricaConfiguration, airportRouteManager)
  var southAfrica: Region = new RegionWithAirportAndPort(northAfricaConfiguration, airportRouteManager, portRouteManager)

  @Before
  def init: Unit =
    europe = new BasicRegion(europeConfiguration)
    russia = new BasicRegion(russiaConfiguration)
    portRouteManager = new PortRouteManager()
    airportRouteManager = new AirportRouteManager()
    unitedStates = new RegionWithPort(unitedStatesConfiguration, portRouteManager)
    japan = new RegionWithPort(japanConfiguration, portRouteManager)
    australia = new RegionWithAirport(australiaConfiguration, airportRouteManager)
    northAfrica = new RegionWithAirport(northAfricaConfiguration, airportRouteManager)
    southAfrica = new RegionWithAirportAndPort(northAfricaConfiguration, airportRouteManager, portRouteManager)

  def configureWorld: Unit =
    europe.addBorderingRegion(russia)
    russia.addBorderingRegion(europe)
    northAfrica.addBorderingRegion(southAfrica)
    southAfrica.addBorderingRegion(northAfrica)
    portRouteManager.addRoute(japan, unitedStates)
    portRouteManager.addRoute(japan, southAfrica)
    portRouteManager.addRoute(southAfrica, unitedStates)
    airportRouteManager.addRoute(northAfrica, australia)
    airportRouteManager.addRoute(southAfrica, australia)

  @Test
  def testRegionName: Unit =
    assertEquals(europeConfiguration.name, europe.name)

  @Test
  def testRegionPopulation: Unit =
    assertEquals(europeConfiguration.population, europe.population)

  @Test
  def testRegionRichness: Unit =
    assertEquals(europeConfiguration.richness, europe.richness)

  @Test
  def testRegionClimate: Unit =
    assertEquals(europeConfiguration.climate, europe.climate)

  @Test
  def testRegionBordersControl: Unit =
    assertEquals(europeConfiguration.bordersControl, europe.bordersControl)

  @Test
  def testRegionGlobalization: Unit =
    assertEquals(europeConfiguration.globalization, europe.globalization)

  @Test
  def testRegionPopulationDensity: Unit =
    assertEquals(europeConfiguration.populationDensity, europe.populationDensity)

  @Test
  def testIncrementInfectAmount: Unit =
    europe.infectedAmount = newInfectedAmount
    assertEquals(newInfectedAmount, europe.infectedAmount)

  @Test
  def testInitiallyEmptyBorderingRegions: Unit =
    assertEquals(List(), europe.getReachableRegions)

  @Test
  def testAddOneBorderingRegion: Unit =
    europe.addBorderingRegion(unitedStates)
    assertEquals(List((unitedStates, ReachableMode.Border)), europe.getReachableRegions)

  @Test
  def testReachableRegionsByBorders: Unit =
    europe.addBorderingRegions(List(unitedStates, russia, japan))
    assertEquals(List((unitedStates, ReachableMode.Border), (russia, ReachableMode.Border), (japan, ReachableMode.Border)), europe.getReachableRegions)

  @Test
  def testNotReachableRegionsByBorders: Unit =
    europe.addBorderingRegions(List(unitedStates, russia))
    assertFalse(europe.getReachableRegions.contains(japan))

  @Test
  def testReachableRegionsByPort: Unit =
    portRouteManager.addRoute(unitedStates, japan)
    assertEquals(List((japan, ReachableMode.Port)), unitedStates.getReachableRegions)

  @Test
  def testReachableRegionsByAirport: Unit =
    airportRouteManager.addRoute(australia, northAfrica)
    assertEquals(List((northAfrica, ReachableMode.Airport)), australia.getReachableRegions)

  @Test
  def testReachableRegionsByAirportAndPort: Unit =
    airportRouteManager.addRoute(southAfrica, northAfrica)
    portRouteManager.addRoute(southAfrica, japan)
    assertEquals(List((northAfrica, ReachableMode.Airport),(japan, ReachableMode.Port)), southAfrica.getReachableRegions)

  @Test
  def testWorldConfiguration: Unit =
    this.configureWorld

    assertEquals(List((russia, ReachableMode.Border)), europe.getReachableRegions)
    assertEquals(List((southAfrica, ReachableMode.Border), (australia, ReachableMode.Airport)), northAfrica.getReachableRegions)
    assertEquals(List((northAfrica, ReachableMode.Border), (australia, ReachableMode.Airport), (unitedStates, ReachableMode.Port)), southAfrica.getReachableRegions)
    assertEquals(List((unitedStates, ReachableMode.Port), (southAfrica, ReachableMode.Port)), japan.getReachableRegions)
}
