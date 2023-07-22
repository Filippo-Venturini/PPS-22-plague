package model.infection

import model.world.{BasicRegion, Region}
import model.world.RegionParameters.*
import org.junit.Assert.{assertEquals, assertTrue}
import org.junit.{Before, Test}
import model.infection.InfectionLogics.given

class TestInfection {

  val testVirusConfiguration: VirusConfiguration = VirusConfiguration("DHT11", 30, 30, 30, 30, 30, 30, 30, false, false)
  val virus: Virus = new BasicVirus(testVirusConfiguration)


  @Test
  def testInternalIncrementInfection: Unit =
    val testRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 646_134_245, 5, 2, 5, 4, 4)
    val testRegion: Region = new BasicRegion(testRegionConfiguration)

    testRegion.infectedAmount = 1

    val regions: List[Region] = List(testRegion)
    val infectionHandler: InfectionHandler = new InfectionHandler(virus, regions)

    infectionHandler.computeInfection(regions)

    assert(testRegion.infectedAmount > 1)


  @Test
  def testSimpleExternalIncrementInfection: Unit =
    val testInfectedRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 646_134_245, 5, 2, 5, 4, 4)
    val testInfectedRegion: Region = new BasicRegion(testInfectedRegionConfiguration)
    testInfectedRegion.infectedAmount = testInfectedRegion.population / 2

    val borderControl = 1
    val testSaneRegionConfiguration: RegionConfiguration = RegionConfiguration("North-Africa", 412_245_211, 2, 2, borderControl, 2, 2)
    val testSaneRegion: Region = new BasicRegion(testSaneRegionConfiguration)

    val regions: List[Region] = List(testInfectedRegion, testSaneRegion)
    val infectionHandler: InfectionHandler = new InfectionHandler(virus, regions)
    val infectionLogic: InfectionLogic = new ExternalInfectionLogic()

    testSaneRegion.addBorderingRegion(testInfectedRegion)
    testInfectedRegion.addBorderingRegion(testSaneRegion)
    infectionHandler.computeInfection(regions)(using infectionLogic)

    assertTrue(testSaneRegion.infectedAmount > 0)


  @Test
  def testInfectionWithBorderControl: Unit =

    val testHighBordersControl = 5
    val regionHighBordersControlConfigurator: RegionConfiguration = RegionConfiguration("CentralAmerica", 800000000, 1, 1, testHighBordersControl, 1, 1)
    val regionHighBordersControl: Region = new BasicRegion(regionHighBordersControlConfigurator)

    val testLowBordersControl = 1
    val regionLowBordersControlConfigurator: RegionConfiguration = RegionConfiguration("NorthAfrica", 800000000, 1, 1, testLowBordersControl, 1, 1)
    val regionLowBordersControl: Region = new BasicRegion(regionLowBordersControlConfigurator)

    val testInfectedRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 646_134_245, 1, 1, 1, 1, 1)
    val testInfectedRegion: Region = new BasicRegion(testInfectedRegionConfiguration)
    testInfectedRegion.infectedAmount = testInfectedRegion.population / 2

    val regionsWithHighBorderControl: List[Region] = List(testInfectedRegion, regionHighBordersControl)
    val infectionHandlerHighBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithHighBorderControl)

    regionHighBordersControl.addBorderingRegion(testInfectedRegion)
    testInfectedRegion.addBorderingRegion(regionHighBordersControl)
    infectionHandlerHighBorderControl.computeInfection(regionsWithHighBorderControl)(using new ExternalInfectionLogic())

    val infectedHighBordersControlRegion = regionHighBordersControl.infectedAmount

    val regionsWithLowBorderControl: List[Region] = List(testInfectedRegion, regionLowBordersControl)
    val infectionHandlerLowBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithLowBorderControl)

    regionLowBordersControl.addBorderingRegion(testInfectedRegion)
    testInfectedRegion.addBorderingRegion(regionLowBordersControl)
    infectionHandlerLowBorderControl.computeInfection(regionsWithLowBorderControl)(using new ExternalInfectionLogic())

    val infectedLowBordersControlRegion = regionLowBordersControl.infectedAmount

    assertTrue(infectedLowBordersControlRegion > infectedHighBordersControlRegion)

}