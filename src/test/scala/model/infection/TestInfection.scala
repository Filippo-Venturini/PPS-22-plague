package model.infection

import model.world.{BasicRegion, Region}
import model.world.RegionTypes.*
import org.junit.Assert.assertEquals
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

/*
  @Test
  def testSimpleExternalIncrementInfection: Unit =
    val testInfectedRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 646_134_245, 5, 2, 5, 4, 4)
    val testInfectedRegion: Region = new BasicRegion(testInfectedRegionConfiguration)
    testInfectedRegion.infectedAmount = testInfectedRegion.population
    //testInfectedRegion.infectedAmount = 3 * (testInfectedRegion.population / 4)

    val borderControl = 1
    val testSaneRegionConfiguration: RegionConfiguration = RegionConfiguration("North-Africa", 412_245_211, 2, 2, borderControl, 2, 2)
    val testSaneRegion: Region = new BasicRegion(testSaneRegionConfiguration)

    val regions: List[Region] = List(testInfectedRegion, testSaneRegion)
    val infectionHandler: InfectionHandler = new InfectionHandler(virus, regions)
    val infectionLogic: InfectionLogic = new ExternalInfectionLogic()

    testSaneRegion.addBorderingRegion(testInfectedRegion)
    infectionHandler.computeInfection(regions)(using infectionLogic)

    testInfectedRegion.getReachableRegions.foreach((region, mode) => println(region))

    assert(testSaneRegion.infectedAmount > 0)


  @Test
  def testInfectionWithBorderControl: Unit =

    val testHighBordersControl = 5
    val regionHighBordersControlConfigurator: RegionConfiguration = RegionConfiguration("CentralAmerica", 800000000, 8, 5, testHighBordersControl, 3, 3)
    val regionHighBordersControl: Region = new BasicRegion(regionHighBordersControlConfigurator)

    val testLowBordersControl = 1
    val regionLowBordersControlConfigurator: RegionConfiguration = RegionConfiguration("NorthAfrica", 800000000, 2, 9, testLowBordersControl, 3, 3)
    val regionLowBordersControl: Region = new BasicRegion(regionLowBordersControlConfigurator)

    val testInfectedRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 646_134_245, 5, 2, 5, 4, 4)
    val testInfectedRegion: Region = new BasicRegion(testInfectedRegionConfiguration)
    testInfectedRegion.infectedAmount = 3 * (testInfectedRegion.population / 4)

    val regionsWithHighBorderControl: List[Region] = List(testInfectedRegion, regionHighBordersControl)
    val infectionHandlerHighBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithHighBorderControl)
    val infectionLogicHighBorderControl: InfectionLogic = new ExternalInfectionLogic()

    regionHighBordersControl.addBorderingRegion(testInfectedRegion)
    infectionHandlerHighBorderControl.computeInfection(regionsWithHighBorderControl)(using infectionLogicHighBorderControl)

    val infectedHighBordersControlRegion = regionHighBordersControl.infectedAmount

    val regionsWithLowBorderControl: List[Region] = List(testInfectedRegion, regionLowBordersControl)
    val infectionHandlerLowBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithLowBorderControl)
    val infectionLogicLowBorderControl: InfectionLogic = new ExternalInfectionLogic()

    regionLowBordersControl.addBorderingRegion(testInfectedRegion)
    infectionHandlerLowBorderControl.computeInfection(regionsWithLowBorderControl)(using infectionLogicLowBorderControl)

    val infectedLowBordersControlRegion = regionLowBordersControl.infectedAmount
    
    assert(infectedLowBordersControlRegion > infectedHighBordersControlRegion)


  @Test
  def testInfectionWithPopulationDensity: Unit =

    val testHighPopulationDensity = 5
    val regionHighPopulationDensityConfigurator: RegionConfiguration = RegionConfiguration("CentralAmerica", 800000000, 8, 5, 3, 8, testHighPopulationDensity)
    val regionHighPopulationDensity: Region = new BasicRegion(regionHighPopulationDensityConfigurator)

    val testLowPopulationDensity = 1
    val regionLowPopulationDensityConfigurator: RegionConfiguration = RegionConfiguration("NorthAfrica", 800000000, 2, 9, 3, 2, testLowPopulationDensity)
    val regionLowPopulationDensity: Region = new BasicRegion(regionLowPopulationDensityConfigurator)

    val testInfectedRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 646_134_245, 5, 2, 5, 4, 4)
    val testInfectedRegion: Region = new BasicRegion(testInfectedRegionConfiguration)
    testInfectedRegion.infectedAmount = 3 * (testInfectedRegion.population / 4)

    val regionsWithHighBorderControl: List[Region] = List(testInfectedRegion, regionHighPopulationDensity)
    val infectionHandlerHighBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithHighBorderControl)
    val infectionLogicHighBorderControl: InfectionLogic = new ExternalInfectionLogic()

    regionHighPopulationDensity.addBorderingRegion(testInfectedRegion)
    infectionHandlerHighBorderControl.computeInfection(regionsWithHighBorderControl)(using infectionLogicHighBorderControl)

    val infectedHighPopulationDensityRegion = regionHighPopulationDensity.infectedAmount

    val regionsWithLowBorderControl: List[Region] = List(testInfectedRegion, regionLowPopulationDensity)
    val infectionHandlerLowBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithLowBorderControl)
    val infectionLogicLowBorderControl: InfectionLogic = new ExternalInfectionLogic()

    regionLowPopulationDensity.addBorderingRegion(testInfectedRegion)
    infectionHandlerLowBorderControl.computeInfection(regionsWithLowBorderControl)(using infectionLogicLowBorderControl)

    val infectedLowPopulationDensityRegion = regionLowPopulationDensity.infectedAmount
    
    assert(infectedLowPopulationDensityRegion < infectedHighPopulationDensityRegion)


  @Test
  def testInfectionWithGlobalization: Unit =

    val testHighGlobalization = 5
    val regionHighGlobalizationConfigurator: RegionConfiguration = RegionConfiguration("CentralAmerica", 800000000, 8, 5, 3, testHighGlobalization, 3)
    val regionHighGlobalization: Region = new BasicRegion(regionHighGlobalizationConfigurator)

    val testLowGlobalization = 1
    val regionLowGlobalizationConfigurator: RegionConfiguration = RegionConfiguration("NorthAfrica", 800000000, 2, 9, 3, testLowGlobalization, 3)
    val regionLowGlobalization: Region = new BasicRegion(regionLowGlobalizationConfigurator)

    val testInfectedRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 646_134_245, 5, 2, 5, 4, 4)
    val testInfectedRegion: Region = new BasicRegion(testInfectedRegionConfiguration)
    testInfectedRegion.infectedAmount = 3 * (testInfectedRegion.population / 4)

    val regionsWithHighBorderControl: List[Region] = List(testInfectedRegion, regionHighGlobalization)
    val infectionHandlerHighBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithHighBorderControl)
    val infectionLogicHighBorderControl: InfectionLogic = new ExternalInfectionLogic()

    regionHighGlobalization.addBorderingRegion(testInfectedRegion)
    infectionHandlerHighBorderControl.computeInfection(regionsWithHighBorderControl)(using infectionLogicHighBorderControl)

    val infectedHighGlobalizationRegion = regionHighGlobalization.infectedAmount

    val regionsWithLowBorderControl: List[Region] = List(testInfectedRegion, regionLowGlobalization)
    val infectionHandlerLowBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithLowBorderControl)
    val infectionLogicLowBorderControl: InfectionLogic = new ExternalInfectionLogic()

    regionLowGlobalization.addBorderingRegion(testInfectedRegion)
    infectionHandlerLowBorderControl.computeInfection(regionsWithLowBorderControl)(using infectionLogicLowBorderControl)

    val infectedLowGlobalizationRegion = regionLowGlobalization.infectedAmount

    assert(infectedLowGlobalizationRegion < infectedHighGlobalizationRegion)


  @Test
  def testInfectionWithRichness: Unit =

    val testHighRichness = 5
    val regionHighRichnessConfigurator: RegionConfiguration = RegionConfiguration("CentralAmerica", 800000000, testHighRichness, 5, 3, 3, 3)
    val regionHighRichness: Region = new BasicRegion(regionHighRichnessConfigurator)

    val testLowRichness = 1
    val regionLowRichnessConfigurator: RegionConfiguration = RegionConfiguration("NorthAfrica", 800000000, testLowRichness, 9, 3, 3, 3)
    val regionLowRichness: Region = new BasicRegion(regionLowRichnessConfigurator)

    val testInfectedRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 646_134_245, 5, 2, 5, 4, 4)
    val testInfectedRegion: Region = new BasicRegion(testInfectedRegionConfiguration)
    testInfectedRegion.infectedAmount = 3 * (testInfectedRegion.population / 4)

    val regionsWithHighBorderControl: List[Region] = List(testInfectedRegion, regionHighRichness)
    val infectionHandlerHighBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithHighBorderControl)
    val infectionLogicHighBorderControl: InfectionLogic = new ExternalInfectionLogic()

    regionHighRichness.addBorderingRegion(testInfectedRegion)
    infectionHandlerHighBorderControl.computeInfection(regionsWithHighBorderControl)(using infectionLogicHighBorderControl)

    val infectedHighRichnessRegion = regionHighRichness.infectedAmount

    val regionsWithLowBorderControl: List[Region] = List(testInfectedRegion, regionLowRichness)
    val infectionHandlerLowBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithLowBorderControl)
    val infectionLogicLowBorderControl: InfectionLogic = new ExternalInfectionLogic()

    regionLowRichness.addBorderingRegion(testInfectedRegion)
    infectionHandlerLowBorderControl.computeInfection(regionsWithLowBorderControl)(using infectionLogicLowBorderControl)

    val infectedLowRichnessRegion = regionLowRichness.infectedAmount

    assert(infectedLowRichnessRegion > infectedHighRichnessRegion)
*/

}






