package model.configuration

import model.configuration.Builders.RegionBuilder
import model.world.{BasicRegion, Region}
import model.world.RegionTypes.*
import model.infection.{ColdRegionsInfectivity, WarmRegionsInfectivity, LowDensityRegionInfectivity, HighDensityRegionsInfectivity, RichRegionsInfectivity, PoorRegionsInfectivity, VaccineResistance, AirportEnabled, PortEnabled}
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
    def setName(name: Name): RegionBuilder = copy(name=Some(name))
    def setPopulation(population: Population): RegionBuilder = copy(population=Some(population))
    def setRichness(richness: Richness): RegionBuilder = copy(richness=Some(richness))
    def setClimate(climate: Climate): RegionBuilder = copy(climate=Some(climate))
    def setBordersControl(bordersControl: BordersControl): RegionBuilder = copy(bordersControl=Some(bordersControl))
    def setGlobalization(globalization: Globalization): RegionBuilder = copy(globalization=Some(globalization))
    def setPopulationDensity(populationDensity: PopulationDensity): RegionBuilder = copy(populationDensity=Some(populationDensity))
    def setBorderingRegions(borderingRegionsIds: List[Name]): RegionBuilder = copy(borderingRegionsIds=borderingRegionsIds)
    def build(): Option[Region] = this match
      case RegionBuilder(Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), _, _, _) =>
        Some(new BasicRegion(RegionConfiguration(name.get,
          population.get,
          richness.get,
          climate.get,
          bordersControl.get,
          globalization.get,
          populationDensity.get)))
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
                          airportEnabled: Option[AirportEnabled]) extends ConfigurationBuilder:
    private def copy(coldRegionsInfectivity: Option[ColdRegionsInfectivity] = coldRegionsInfectivity,
                     warmRegionsInfectivity: Option[WarmRegionsInfectivity] = warmRegionsInfectivity,
                     lowDensityRegionsInfectivity: Option[LowDensityRegionInfectivity],
                     highDensityRegionsInfectivity: Option[HighDensityRegionsInfectivity],
                     richRegionsInfectivity: Option[RichRegionsInfectivity],
                     poorRegionsInfectivity: Option[PoorRegionsInfectivity],
                     vaccineResistance: Option[VaccineResistance],
                     airportEnabled: Option[AirportEnabled]): VirusBuilder =
      new VirusBuilder(coldRegionsInfectivity, warmRegionsInfectivity, lowDensityRegionsInfectivity, highDensityRegionsInfectivity,
        richRegionsInfectivity, poorRegionsInfectivity, vaccineResistance, airportEnabled)

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
  object VirusBuilder:
    def apply() = new VirusBuilder(None, None, None, None, None, None, None, None)