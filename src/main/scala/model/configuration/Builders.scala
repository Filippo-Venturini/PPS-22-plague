package model.configuration

import model.configuration.Builders.RegionBuilder
import model.world.{AirportRouteManager, BasicRegion, PortRouteManager, Region, RegionWithAirport, RegionWithPort, RegionWithAirportAndPort}
import model.world.RegionTypes.*
import model.infection.{AirportEnabled, ColdRegionsInfectivity, HighDensityRegionsInfectivity, LowDensityRegionInfectivity, PoorRegionsInfectivity, PortEnabled, RichRegionsInfectivity, VaccineResistance, WarmRegionsInfectivity}
import model.infection.{BasicVirus, Virus, VirusConfiguration}
object Builders:
  trait ConfigurationBuilder
  case class RegionBuilder(name: Option[Name],
                           population: Option[Population],
                           richness: Option[Richness],
                           climate: Option[Climate],
                           bordersControl: Option[BordersControl],
                           globalization: Option[Globalization],
                           populationDensity: Option[PopulationDensity],
                           borderingRegionsIds: List[Name],
                           hasAirport: Boolean,
                           hasPort: Boolean) extends ConfigurationBuilder:
    private def copy(name: Option[Name] = name,
                     population: Option[Population] = population,
                     richness: Option[Richness] = richness,
                     climate: Option[Climate] = climate,
                     bordersControl: Option[BordersControl] = bordersControl,
                     globalization: Option[Globalization] = globalization,
                     populationDensity: Option[PopulationDensity] = populationDensity,
                     borderingRegionsIds: List[Name] = borderingRegionsIds,
                     hasAirport: Boolean = hasAirport,
                     hasPort: Boolean = hasPort): RegionBuilder =
      new RegionBuilder(name, population, richness, climate, bordersControl, globalization, populationDensity, borderingRegionsIds, hasAirport, hasPort)
    def setName(name: Name): RegionBuilder = if name.exists(c => c.isDigit) then this else copy(name=Some(name))
    def setPopulation(population: Population): RegionBuilder = copy(population=Some(population))
    def setRichness(richness: Richness): RegionBuilder = copy(richness=Some(richness))
    def setClimate(climate: Climate): RegionBuilder = copy(climate=Some(climate))
    def setBordersControl(bordersControl: BordersControl): RegionBuilder = copy(bordersControl=Some(bordersControl))
    def setGlobalization(globalization: Globalization): RegionBuilder = copy(globalization=Some(globalization))
    def setPopulationDensity(populationDensity: PopulationDensity): RegionBuilder = copy(populationDensity=Some(populationDensity))
    def setBorderingRegions(borderingRegionsIds: List[Name]): RegionBuilder = copy(borderingRegionsIds=borderingRegionsIds)
    def addPort: RegionBuilder = copy(hasPort = true)
    def addAirport: RegionBuilder = copy(hasAirport = true)
    def build(): Option[Region] = this match
      case RegionBuilder(Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), _, false, false) =>
        Some(new BasicRegion(RegionConfiguration(name.get, population.get, richness.get, climate.get, bordersControl.get, globalization.get, populationDensity.get)))
      case RegionBuilder(Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), _, true, false) =>
        Some(new RegionWithAirport(RegionConfiguration(name.get, population.get, richness.get, climate.get, bordersControl.get, globalization.get, populationDensity.get), AirportRouteManager()))
      case RegionBuilder(Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), _, false, true) =>
        Some(new RegionWithPort(RegionConfiguration(name.get, population.get, richness.get, climate.get, bordersControl.get, globalization.get, populationDensity.get), PortRouteManager()))
      case RegionBuilder(Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), _, true, true) =>
        Some(new RegionWithAirportAndPort(RegionConfiguration(name.get, population.get, richness.get, climate.get, bordersControl.get, globalization.get, populationDensity.get), AirportRouteManager(), PortRouteManager()))
      case _ => None
  object RegionBuilder:
    def apply() = new RegionBuilder(None, None, None, None, None, None, None, List(), false, false)

  case class VirusBuilder(coldRegionsInfectivity: Option[ColdRegionsInfectivity],
                          warmRegionsInfectivity: Option[WarmRegionsInfectivity],
                          lowDensityRegionsInfectivity: Option[LowDensityRegionInfectivity],
                          highDensityRegionsInfectivity: Option[HighDensityRegionsInfectivity],
                          richRegionsInfectivity: Option[RichRegionsInfectivity],
                          poorRegionsInfectivity: Option[PoorRegionsInfectivity],
                          vaccineResistance: Option[VaccineResistance],
                          airportEnabled: Option[AirportEnabled],
                          portEnabled: Option[PortEnabled]) extends ConfigurationBuilder:
    private def copy(coldRegionsInfectivity: Option[ColdRegionsInfectivity] = coldRegionsInfectivity,
                     warmRegionsInfectivity: Option[WarmRegionsInfectivity] = warmRegionsInfectivity,
                     lowDensityRegionsInfectivity: Option[LowDensityRegionInfectivity],
                     highDensityRegionsInfectivity: Option[HighDensityRegionsInfectivity],
                     richRegionsInfectivity: Option[RichRegionsInfectivity],
                     poorRegionsInfectivity: Option[PoorRegionsInfectivity],
                     vaccineResistance: Option[VaccineResistance],
                     airportEnabled: Option[AirportEnabled],
                     portEnabled: Option[PortEnabled]): VirusBuilder =
      new VirusBuilder(coldRegionsInfectivity, warmRegionsInfectivity, lowDensityRegionsInfectivity, highDensityRegionsInfectivity,
        richRegionsInfectivity, poorRegionsInfectivity, vaccineResistance, airportEnabled, portEnabled)

    def setColdRegionInfectivity(coldRegionsInfectivity: ColdRegionsInfectivity): VirusBuilder =
      copy(coldRegionsInfectivity = Some(coldRegionsInfectivity))

    def setWarmRegionInfectivity(warmRegionsInfectivity: WarmRegionsInfectivity): VirusBuilder =
      copy(warmRegionsInfectivity = Some(warmRegionsInfectivity))

    def setLowDensityRegionsInfectivity(lowDensityRegionsInfectivity: LowDensityRegionInfectivity): VirusBuilder =
      copy(lowDensityRegionsInfectivity = Some(lowDensityRegionsInfectivity))

    def setHighDensityRegionsInfectivity(highDensityRegionsInfectivity: HighDensityRegionsInfectivity): VirusBuilder =
      copy(highDensityRegionsInfectivity = Some(highDensityRegionsInfectivity))

    def setRichRegionsInfectivity(richRegionsInfectivity: RichRegionsInfectivity): VirusBuilder =
      copy(richRegionsInfectivity = Some(richRegionsInfectivity))

    def setPoorRegionsInfectivity(poorRegionsInfectivity: PoorRegionsInfectivity): VirusBuilder =
      copy(poorRegionsInfectivity = Some(poorRegionsInfectivity))

    def setVaccineResistance(vaccineResistance: VaccineResistance): VirusBuilder =
      copy(vaccineResistance = Some(vaccineResistance))

    def setAirportEnabled(airportEnabled: AirportEnabled): VirusBuilder =
      copy(airportEnabled = Some(airportEnabled))

    def setPortEnabled(portEnabled: PortEnabled): VirusBuilder =
      copy(portEnabled = Some(portEnabled))

    def build(): Option[Virus] = this match
      case VirusBuilder(Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), Some(_)) =>
        Some(new BasicVirus(VirusConfiguration(
          "",
          coldRegionsInfectivity.get,
          warmRegionsInfectivity.get,
          lowDensityRegionsInfectivity.get,
          highDensityRegionsInfectivity.get,
          richRegionsInfectivity.get,
          poorRegionsInfectivity.get,
          vaccineResistance.get,
          airportEnabled.get,
          portEnabled.get
        )))
      case _ => None
  object VirusBuilder:
    def apply() = new VirusBuilder(None, None, None, None, None, None, None, None, None)

  case class RawRoute(nameRegion1: String, nameRegion2: String, reachableMode: ReachableMode)
  case class RawRouteBuilder(nameRegion1: Option[String], nameRegion2: Option[String], reachableMode: Option[ReachableMode]):
    private def copy(nameRegion1: Option[String] = nameRegion1,
                     nameRegion2: Option[String] = nameRegion2,
                     reachableMode: Option[ReachableMode] = reachableMode): RawRouteBuilder =
      RawRouteBuilder(nameRegion1, nameRegion2, reachableMode)

    def setNameRegion1(name: String): RawRouteBuilder = copy(nameRegion1 = Some(name))
    def setNameRegion2(name: String): RawRouteBuilder = copy(nameRegion2 = Some(name))
    def setReachableMode(reachableMode: ReachableMode): RawRouteBuilder = copy(reachableMode = Some(reachableMode))

    def build(): Option[RawRoute] = this match
      case RawRouteBuilder(Some(_), Some(_), Some(_)) => Some(RawRoute(nameRegion1.get, nameRegion2.get, reachableMode.get))
      case _ => None

  object RawRouteBuilder:
    def apply() = new RawRouteBuilder(None, None, None)