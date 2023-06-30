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

    testRegion.infectedAmount = 1

    val regions: List[Region] = List(testRegion)
    val infectionHandler: InfectionHandler = new InfectionHandler(virus, regions)

    val infectionLogic: InfectionLogic = new InternalInfectionLogic(testRegion, virus)


    infectionHandler.computeInfection(regions)(using infectionLogic)
    assert(testRegion.infectedAmount > 1)

  @Test
  def testSimpleExternalIncrementInfection: Unit =
    val testInfectedRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)
    val testInfectedRegion: Region = new BasicRegion(testInfectedRegionConfiguration)
    testInfectedRegion.infectedAmount = 3 * (testInfectedRegion.population / 4)

    val borderControl = 0
    val testSaneRegionConfiguration: RegionConfiguration = RegionConfiguration("NorthAfrica", 243000000, 2, 9, borderControl, 2, 2)
    val testSaneRegion: Region = new BasicRegion(testInfectedRegionConfiguration)

    val regions: List[Region] = List(testInfectedRegion, testSaneRegion)
    val infectionHandler: InfectionHandler = new InfectionHandler(virus, regions)
    val infectionLogic: InfectionLogic = new ExternalInfectionLogic(testSaneRegion, virus)

    testSaneRegion.addBorderingRegion(testInfectedRegion)
    infectionHandler.computeInfection(regions)(using infectionLogic)

    assert(testSaneRegion.infectedAmount > 0)

/*
  @Test
  def testWithBorderControl: Unit =

    val testHighBordersControl = 5
    val regionHighBordersControlConfigurator: RegionConfiguration = RegionConfiguration("CentralAmerica", 800000000, 8, 5, testHighBordersControl, 8, 3)
    val regionHighBordersControl: Region = new BasicRegion(regionHighBordersControlConfigurator)

    val testLowBordersControl = 1
    val regionLowBordersControlConfigurator: RegionConfiguration = RegionConfiguration("NorthAfrica", 800000000, 2, 9, testLowBordersControl, 2, 3)
    val regionLowBordersControl: Region = new BasicRegion(regionLowBordersControlConfigurator)

    val testInfectedRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)
    val testInfectedRegion: Region = new BasicRegion(testInfectedRegionConfiguration)
    testInfectedRegion.infectedAmount = 3 * (testInfectedRegion.population / 4)

    val regionsWithHighBorderControl: List[Region] = List(testInfectedRegion, regionHighBordersControl)
    val infectionHandlerHighBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithHighBorderControl)
    val infectionLogicHighBorderControl: InfectionLogic = new ExternalInfectionLogic(regionHighBordersControl, virus)

    regionHighBordersControl.addBorderingRegion(testInfectedRegion)
    infectionHandlerHighBorderControl.computeInfection(regionsWithHighBorderControl)(using infectionLogicHighBorderControl)

    val infectedHighBordersControlRegion = regionHighBordersControl.infectedAmount

    val regionsWithLowBorderControl: List[Region] = List(testInfectedRegion, regionLowBordersControl)
    val infectionHandlerLowBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithLowBorderControl)
    val infectionLogicLowBorderControl: InfectionLogic = new ExternalInfectionLogic(regionLowBordersControl, virus)

    regionLowBordersControl.addBorderingRegion(testInfectedRegion)
    infectionHandlerLowBorderControl.computeInfection(regionsWithLowBorderControl)(using infectionLogicLowBorderControl)

    val infectedLowBordersControlRegion = regionLowBordersControl.infectedAmount

    println(regionHighBordersControl.name+": " + infectedHighBordersControlRegion)
    println(regionLowBordersControl.name+": " + infectedLowBordersControlRegion)
    
    assert(infectedLowBordersControlRegion > infectedHighBordersControlRegion)

*/
  @Test
  def testWithPopulationDensity: Unit =

    val testHighPopulationDensity = 5
    val regionHighPopulationDensityConfigurator: RegionConfiguration = RegionConfiguration("CentralAmerica", 800000000, 8, 5, 3, 8, testHighPopulationDensity)
    val regionHighPopulationDensity: Region = new BasicRegion(regionHighPopulationDensityConfigurator)

    val testLowPopulationDensity = 1
    val regionLowPopulationDensityConfigurator: RegionConfiguration = RegionConfiguration("NorthAfrica", 800000000, 2, 9, 3, 2, testLowPopulationDensity)
    val regionLowPopulationDensity: Region = new BasicRegion(regionLowPopulationDensityConfigurator)

    val testInfectedRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)
    val testInfectedRegion: Region = new BasicRegion(testInfectedRegionConfiguration)
    testInfectedRegion.infectedAmount = 3 * (testInfectedRegion.population / 4)

    val regionsWithHighBorderControl: List[Region] = List(testInfectedRegion, regionHighPopulationDensity)
    val infectionHandlerHighBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithHighBorderControl)
    val infectionLogicHighBorderControl: InfectionLogic = new ExternalInfectionLogic(regionHighPopulationDensity, virus)

    regionHighPopulationDensity.addBorderingRegion(testInfectedRegion)
    infectionHandlerHighBorderControl.computeInfection(regionsWithHighBorderControl)(using infectionLogicHighBorderControl)

    val infectedHighPopulationDensityRegion = regionHighPopulationDensity.infectedAmount

    val regionsWithLowBorderControl: List[Region] = List(testInfectedRegion, regionLowPopulationDensity)
    val infectionHandlerLowBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithLowBorderControl)
    val infectionLogicLowBorderControl: InfectionLogic = new ExternalInfectionLogic(regionLowPopulationDensity, virus)

    regionLowPopulationDensity.addBorderingRegion(testInfectedRegion)
    infectionHandlerLowBorderControl.computeInfection(regionsWithLowBorderControl)(using infectionLogicLowBorderControl)

    val infectedLowPopulationDensityRegion = regionLowPopulationDensity.infectedAmount
    
    assert(infectedLowPopulationDensityRegion < infectedHighPopulationDensityRegion)


  @Test
  def testWithGlobalization: Unit =

    val testHighGlobalization = 5
    val regionHighGlobalizationConfigurator: RegionConfiguration = RegionConfiguration("CentralAmerica", 800000000, 8, 5, 3, testHighGlobalization, 3)
    val regionHighGlobalization: Region = new BasicRegion(regionHighGlobalizationConfigurator)

    val testLowGlobalization = 1
    val regionLowGlobalizationConfigurator: RegionConfiguration = RegionConfiguration("NorthAfrica", 800000000, 2, 9, 3, testLowGlobalization, 3)
    val regionLowGlobalization: Region = new BasicRegion(regionLowGlobalizationConfigurator)

    val testInfectedRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)
    val testInfectedRegion: Region = new BasicRegion(testInfectedRegionConfiguration)
    testInfectedRegion.infectedAmount = 3 * (testInfectedRegion.population / 4)

    val regionsWithHighBorderControl: List[Region] = List(testInfectedRegion, regionHighGlobalization)
    val infectionHandlerHighBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithHighBorderControl)
    val infectionLogicHighBorderControl: InfectionLogic = new ExternalInfectionLogic(regionHighGlobalization, virus)

    regionHighGlobalization.addBorderingRegion(testInfectedRegion)
    infectionHandlerHighBorderControl.computeInfection(regionsWithHighBorderControl)(using infectionLogicHighBorderControl)

    val infectedHighGlobalizationRegion = regionHighGlobalization.infectedAmount

    val regionsWithLowBorderControl: List[Region] = List(testInfectedRegion, regionLowGlobalization)
    val infectionHandlerLowBorderControl: InfectionHandler = new InfectionHandler(virus, regionsWithLowBorderControl)
    val infectionLogicLowBorderControl: InfectionLogic = new ExternalInfectionLogic(regionLowGlobalization, virus)

    regionLowGlobalization.addBorderingRegion(testInfectedRegion)
    infectionHandlerLowBorderControl.computeInfection(regionsWithLowBorderControl)(using infectionLogicLowBorderControl)

    val infectedLowGlobalizationRegion = regionLowGlobalization.infectedAmount

    assert(infectedLowGlobalizationRegion < infectedHighGlobalizationRegion)
}






