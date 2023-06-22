package model.world
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class TestRegion {
  val regionInfectedAmount: Int = 0
  val newInfectedAmount: Int = 10
  
  val testRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)

  val testRegion: Region = new BasicRegion(testRegionConfiguration)
  val borderingRegion: Region = new BasicRegion(testRegionConfiguration)

  @Test
  def testRegionName: Unit =
    assertEquals(testRegionConfiguration.name, testRegion.name)

  @Test
  def testRegionPopulation: Unit =
    assertEquals(testRegionConfiguration.population, testRegion.population)

  @Test
  def testRegionRichness: Unit =
    assertEquals(testRegionConfiguration.richness, testRegion.richness)

  @Test
  def testRegionClimate: Unit =
    assertEquals(testRegionConfiguration.climate, testRegion.climate)

  @Test
  def testRegionBordersControl: Unit =
    assertEquals(testRegionConfiguration.bordersControl, testRegion.bordersControl)

  @Test
  def testRegionGlobalization: Unit =
    assertEquals(testRegionConfiguration.globalization, testRegion.globalization)

  @Test
  def testRegionPopulationDensity: Unit =
    assertEquals(testRegionConfiguration.populationDensity, testRegion.populationDensity)

  @Test
  def testIncrementInfectAmount: Unit =
    testRegion.infectedAmount = newInfectedAmount
    assertEquals(newInfectedAmount, testRegion.infectedAmount)

  @Test
  def testInitiallyEmptyBorderingRegions: Unit =
    assertEquals(List(), testRegion.borderingRegions)

  @Test
  def testAddOneBorderingRegion: Unit =
    testRegion.addBorderingRegion(borderingRegion)
    assertEquals(List(borderingRegion), testRegion.borderingRegions)

}
