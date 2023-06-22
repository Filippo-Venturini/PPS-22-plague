package model.infection

import model.infection.VirusStructure.Virus
import model.infection.{InfectionLogic, InternalInfectionLogic}
import model.world.{BasicRegion, Region, RegionConfiguration}
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class TestInfection {

  val testRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)

  var testRegion: Region = new BasicRegion(testRegionConfiguration)

  var infectionLogic: InfectionLogic = new InternalInfectionLogic(testRegion, new Virus("DHT11"))

  @Test
  def testInternalIncrementInfection: Unit =
    testRegion.infectedAmount = 1
    infectionLogic.compute()
    assertEquals(testRegion.infectedAmount, 2)
}
