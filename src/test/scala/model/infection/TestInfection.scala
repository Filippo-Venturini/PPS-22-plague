package model.infection

import model.world.{BasicRegion, Region}
import model.world.RegionTypes.*
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class TestInfection {

  val testVirusConfiguration: VirusConfiguration = VirusConfiguration("DHT11", 0, 0, 0, 0, 0, 0, 0, false, false)
  val virus: Virus = new BasicVirus(testVirusConfiguration)


  @Test
  def testInternalIncrementInfection: Unit =
    val testRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)
    val testRegion: Region = new BasicRegion(testRegionConfiguration)

    val infectionLogic: InfectionLogic = new InternalInfectionLogic(testRegion, virus)

    testRegion.infectedAmount = 1
    infectionLogic.compute()
    assertEquals(2, testRegion.infectedAmount)

  /*
  @Test
  def testExternalIncrementInfection: Unit =
    val testInfectedRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)
    val testInfectedRegion: Region = new BasicRegion(testInfectedRegionConfiguration)
    testInfectedRegion.infectedAmount = 3 * (testInfectedRegion.population / 4)

    val borderControl = 0
    val testSaneRegionConfiguration: RegionConfiguration = RegionConfiguration("NorthAfrica", 243000000, 2, 9, borderControl, 2, 2)
    val testSaneRegion: Region = new BasicRegion(testInfectedRegionConfiguration)

    val infectionLogic: InfectionLogic = new ExternalInfectionLogic(testSaneRegion, virus)
    testSaneRegion.addBorderingRegion(testInfectedRegion)

    infectionLogic.compute()
    assert(testSaneRegion.infectedAmount > 0)
  */

}
