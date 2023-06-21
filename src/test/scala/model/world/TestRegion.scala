package model.world
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class TestRegion {

  val regionName: String = "Europe"
  val regionPopulation: Int = 746000000
  val regionRichness: Int = 9
  val regionClimate: Int = 5
  val regionBordersControl: Int = 8
  val regionGlobalization: Int = 9
  val regionPopulationDensity: Int = 8
  val regionInfectedAmount: Int = 0

  var testRegion: Region = new BasicRegion(regionName,
    regionPopulation,
    regionRichness,
    regionClimate,
    regionBordersControl,
    regionGlobalization,
    regionPopulationDensity,
    regionInfectedAmount)

  @Test
  def testRegionName: Unit =
    assertEquals(regionName, testRegion.name)

  @Test
  def testRegionPopulation: Unit =
    assertEquals(regionPopulation, testRegion.population)

  @Test
  def testRegionRichness: Unit =
    assertEquals(regionRichness, testRegion.richness)

  @Test
  def testRegionClimate: Unit =
    assertEquals(regionClimate, testRegion.climate)

  @Test
  def testRegionBordersControl: Unit =
    assertEquals(regionBordersControl, testRegion.bordersControl)

  @Test
  def testRegionGlobalization: Unit =
    assertEquals(regionGlobalization, testRegion.globalization)

  @Test
  def testRegionPopulationDensity: Unit =
    assertEquals(regionPopulationDensity, testRegion.populationDensity)

  @Test
  def testIncrementInfectAmount: Unit =
    testRegion.infectedAmount = 10
    assertEquals(regionInfectedAmount + 10, testRegion.infectedAmount)

}
