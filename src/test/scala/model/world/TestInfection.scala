package model.world
import model.infection.VirusStructure.Virus
import model.infection.{InfectionLogic, InternalInfectionLogic}
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class TestInfection {

  val regionName: String = "Europe"
  val regionPopulation: Int = 746000000
  val regionRichness: Int = 9
  val regionClimate: Int = 5
  val regionBordersControl: Int = 8
  val regionGlobalization: Int = 9
  val regionPopulationDensity: Int = 8
  val regionInfectedAmount: Int = 1

  /*var testRegion: Region = new BasicRegion(regionName,
    regionPopulation,
    regionRichness,
    regionClimate,
    regionBordersControl,
    regionGlobalization,
    regionPopulationDensity,
    regionInfectedAmount)

  var infectionLogic: InfectionLogic = new InternalInfectionLogic(testRegion, new Virus("DHT11"))

  @Test
  def testInternalIncrementInfection: Unit =
    infectionLogic.compute()
    assertEquals(testRegion.infectedAmount, 2)*/

}
