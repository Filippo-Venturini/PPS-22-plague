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
  var firstRegion: Region = new BasicRegion(firstRegionConfiguration)
  var secondRegion: Region = new BasicRegion(secondRegionConfiguration)
  var portRouteManager: PortRouteManager = new PortRouteManager
  var thirdRegion: Region = new RegionWithPort(thirdTestRegionConfiguration, portRouteManager)
  var fourthRegion: Region = new RegionWithPort(fourthTestRegionConfiguration, portRouteManager)

  @Before
  def init: Unit =
    firstRegion = new BasicRegion(firstRegionConfiguration)
    secondRegion = new BasicRegion(secondRegionConfiguration)
    portRouteManager = new PortRouteManager
    thirdRegion = new RegionWithPort(thirdTestRegionConfiguration, portRouteManager)
    fourthRegion = new RegionWithPort(fourthTestRegionConfiguration, portRouteManager)

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

}
