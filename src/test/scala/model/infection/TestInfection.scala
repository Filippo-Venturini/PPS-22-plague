package model.infection

import model.world.{BasicRegion, Region, RegionConfiguration}
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class TestInfection {

  val testRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)
  var testRegion: Region = new BasicRegion(testRegionConfiguration)

  val testVirusConfiguration: VirusConfiguration = VirusConfiguration("DHT11", 0, 0, 0, 0, 0, 0, 0, false, false)
  val virus: Virus = new BasicVirus(testVirusConfiguration)
  var infectionLogic: InfectionLogic = new InternalInfectionLogic(testRegion, virus)

  @Test
  def testInternalIncrementInfection: Unit =
    testRegion.infectedAmount = 1
    infectionLogic.compute()
    assertEquals(testRegion.infectedAmount, 2)

  /*
  @Test
  def testExternalIncrementInfection: Unit =

  */

}
