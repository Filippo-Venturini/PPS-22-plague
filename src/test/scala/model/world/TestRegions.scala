package model.world
import model.world.RegionParameters.*
import model.world.RegionConfigurationsForTests.*
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.{Before, Test}

class TestRegions {
  val regionInfectedAmount: Int = 0
  val newInfectedAmount: Int = 10
  var europe: Region = new BasicRegion(europeConfiguration)
  var russia: Region = new BasicRegion(russiaConfiguration)
  var portRouteManager: PortRouteManager = PortRouteManager()
  var airportRouteManager: AirportRouteManager = AirportRouteManager()
  var unitedStates: Region = new RegionWithPort(unitedStatesConfiguration, portRouteManager)
  var japan: Region = new RegionWithPort(japanConfiguration, portRouteManager)
  var australia: Region = new RegionWithAirport(australiaConfiguration, airportRouteManager)
  var northAfrica: Region = new RegionWithAirport(northAfricaConfiguration, airportRouteManager)
  var southAfrica: Region = new RegionWithAirportAndPort(northAfricaConfiguration, airportRouteManager, portRouteManager)

  def configureWorld(): Unit =
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
  def testRegionName(): Unit =
    assertEquals(europeConfiguration.name, europe.name)

  @Test
  def testRegionPopulation(): Unit =
    assertEquals(europeConfiguration.population, europe.population)

  @Test
  def testRegionRichness(): Unit =
    assertEquals(europeConfiguration.richness, europe.richness)

  @Test
  def testRegionClimate(): Unit =
    assertEquals(europeConfiguration.climate, europe.climate)

  @Test
  def testRegionBordersControl(): Unit =
    assertEquals(europeConfiguration.bordersControl, europe.bordersControl)

  @Test
  def testRegionGlobalization(): Unit =
    assertEquals(europeConfiguration.globalization, europe.globalization)

  @Test
  def testRegionPopulationDensity(): Unit =
    assertEquals(europeConfiguration.populationDensity, europe.populationDensity)

  @Test
  def testIncrementInfectAmount(): Unit =
    europe.infectedAmount = newInfectedAmount
    assertTrue(Math.abs(europe.infectedAmount - newInfectedAmount) <= 0.01)

  @Test
  def testSortRegions(): Unit =
    val regions: List[Region] = List(unitedStates, europe, japan)
    assertEquals(List(europe, japan, unitedStates), regions.sorted)

  @Test
  def testInitiallyEmptyBorderingRegions(): Unit =
    assertEquals(List(), europe.getReachableRegions)

  @Test
  def testAddOneBorderingRegion(): Unit =
    europe.addBorderingRegion(unitedStates)
    assertEquals(List((unitedStates, ReachableMode.Border)), europe.getReachableRegions)

  @Test
  def testReachableRegionsByBorders(): Unit =
    europe.addBorderingRegions(List(unitedStates, russia, japan))
    assertEquals(List((unitedStates, ReachableMode.Border), (russia, ReachableMode.Border), (japan, ReachableMode.Border)), europe.getReachableRegions)

  @Test
  def testNotReachableRegionsByBorders(): Unit =
    europe.addBorderingRegions(List(unitedStates, russia))
    assertFalse(europe.getReachableRegions.contains(japan))

  @Test
  def testReachableRegionsByPort(): Unit =
    portRouteManager.addRoute(unitedStates, japan)
    assertEquals(List((japan, ReachableMode.Port)), unitedStates.getReachableRegions)

  @Test
  def testReachableRegionsByAirport(): Unit =
    airportRouteManager.addRoute(australia, northAfrica)
    assertEquals(List((northAfrica, ReachableMode.Airport)), australia.getReachableRegions)

  @Test
  def testReachableRegionsByAirportAndPort(): Unit =
    airportRouteManager.addRoute(southAfrica, northAfrica)
    portRouteManager.addRoute(southAfrica, japan)
    assertEquals(List((northAfrica, ReachableMode.Airport),(japan, ReachableMode.Port)), southAfrica.getReachableRegions)

  @Test
  def testWorldConfiguration(): Unit =
    this.configureWorld()

    assertEquals(List((russia, ReachableMode.Border)), europe.getReachableRegions)
    assertEquals(List((southAfrica, ReachableMode.Border), (australia, ReachableMode.Airport)), northAfrica.getReachableRegions)
    assertEquals(List((northAfrica, ReachableMode.Border), (australia, ReachableMode.Airport), (unitedStates, ReachableMode.Port)), southAfrica.getReachableRegions)
    assertEquals(List((unitedStates, ReachableMode.Port), (southAfrica, ReachableMode.Port)), japan.getReachableRegions)
}