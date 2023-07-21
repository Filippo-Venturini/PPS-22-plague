package model.configuration

import model.configuration.builders.RawRouteBuilder.RawRoute
import model.configuration.builders.RegionIdentifierBuilder.RegionIdentifier
import model.configuration.Parsers.RawRoute.RawRouteParser
import model.configuration.Parsers.Region.RegionParser
import model.configuration.Parsers.RegionIdentifier.RegionIdentifierParser
import model.configuration.Parsers.Virus.VirusParser
import model.infection.{Virus, VirusConfiguration}
import model.world.Region
import model.world.RegionParameters.RegionConfiguration
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.{Before, Test}
import model.world.RegionParameters.ReachableMode

object TestReaders {

  class TestRegionParser {
    
    val parser: RegionParser = RegionParser()

    @Test
    def testCorrectRowRead(): Unit =
      val conf = RegionConfiguration("Central-Europe", 60_000_000, 1, 2, 3, 4, 5)
      val region: Region = parser.parse(s"${conf.name},${conf.population},${conf.populationDensity},${conf.climate},${conf.richness},${conf.bordersControl},${conf.globalization}").get
      assertEquals(conf.name, region.name)
      assertEquals(conf.population, region.population)
      assertEquals(conf.populationDensity, region.populationDensity)
      assertEquals(conf.climate, region.climate)
      assertEquals(conf.richness, region.richness)
      assertEquals(conf.bordersControl, region.bordersControl)
      assertEquals(conf.globalization, region.globalization)

    @Test
    def testBadComposedString(): Unit =
      assertEquals(None, parser.parse("Central-Europe,foo,foo,foo,0,foo,0"))

    @Test
    def testUncompletedString(): Unit =
      assertEquals(None, parser.parse("Central-Europe,60000000"))

    @Test
    def testReadIgnoresAdditionalFields(): Unit =
      val conf = RegionConfiguration("Central-Europe", 60_000_000, 1, 2, 3, 4, 5)
      val region: Region = parser.parse(s"${conf.name},${conf.population},${conf.populationDensity},${conf.climate},${conf.richness},${conf.bordersControl},${conf.globalization},a,b,c,d,e").get
      assertEquals(conf.name, region.name)
      assertEquals(conf.population, region.population)
      assertEquals(conf.populationDensity, region.populationDensity)
      assertEquals(conf.climate, region.climate)
      assertEquals(conf.richness, region.richness)
      assertEquals(conf.bordersControl, region.bordersControl)
      assertEquals(conf.globalization, region.globalization)
  }

  class TestVirusParser {
    val parser: VirusParser = VirusParser()

    @Test
    def testCorrectRowRead(): Unit =
      val conf: VirusConfiguration = VirusConfiguration("foo", 1, 2, 3, 4, 5, 6, 7, false, true)
      val virus: Virus = parser.parse(s"${conf.coldRegionsInfectivity},${conf.hotRegionsInfectivity},${conf.richRegionsInfectivity},${conf.poorRegionsInfectivity},${conf.lowDensityRegionInfectivity},${conf.highDensityRegionsInfectivity},${conf.vaccineResistance},${conf.airportEnabled.toString},${conf.portEnabled.toString}").get
      assertEquals(conf.coldRegionsInfectivity, virus.coldRegionsInfectivity)
      assertEquals(conf.hotRegionsInfectivity, virus.hotRegionsInfectivity)
      assertEquals(conf.richRegionsInfectivity, virus.richRegionsInfectivity)
      assertEquals(conf.poorRegionsInfectivity, virus.poorRegionsInfectivity)
      assertEquals(conf.lowDensityRegionInfectivity, virus.lowDensityRegionInfectivity)
      assertEquals(conf.highDensityRegionsInfectivity, virus.highDensityRegionsInfectivity)
      assertEquals(conf.vaccineResistance, virus.vaccineResistance)
      assertEquals(conf.airportEnabled, virus.airportEnabled)
      assertEquals(conf.portEnabled, virus.portEnabled)

    @Test
    def testBadComposedString(): Unit =
      assertEquals(None, parser.parse("1,2,3,4,5,7,true,foo"))

    @Test
    def testUncompletedString(): Unit =
      assertEquals(None, parser.parse("1"))

  }

  class TestRawRouteParser() {
    val parser: RawRouteParser = RawRouteParser()

    @Test
    def testCorrectRowRead(): Unit =
      val conf: RawRoute = RawRoute("Europe", "Balkans", ReachableMode.Border)
      val rawRoute: RawRoute = parser.parse(s"${conf.nameRegion1},${conf.nameRegion2},${conf.reachableMode.toString}").get
      assertEquals(conf.nameRegion1, rawRoute.nameRegion1)
      assertEquals(conf.nameRegion2, rawRoute.nameRegion2)
      assertEquals(conf.reachableMode, rawRoute.reachableMode)

    @Test
    def testBadComposedString(): Unit =
      assertEquals(None, parser.parse("Europe,Balkans,ByFly"))

    @Test
    def testUncompletedString(): Unit =
      assertEquals(None, parser.parse("Europe"))

  }

  class TestRegionIdentifierParser() {
    val parser: RegionIdentifierParser = RegionIdentifierParser()

    @Test
    def testCorrectRowRead(): Unit =
      val conf: RegionIdentifier = RegionIdentifier("Europe", "#AB12EF")
      assertEquals(conf, parser.parse(s"${conf.regionName},${conf.identifier}").get)

    @Test
    def testBadComposedString(): Unit =
      assertEquals(None, parser.parse("Europe,foo"))

    @Test
    def testUncompletedString(): Unit =
      assertEquals(None, parser.parse("Europe"))
  }

}
