package model.configuration.builders

import model.configuration.builders.ConfigurationBuilder
import model.world.{AirportRouteManager, PortRouteManager, BasicRegion, Region, RegionWithAirport, RegionWithAirportAndPort, RegionWithPort}
import model.world.RegionParameters.*

/**
 * an object that allows to easily create a Region using the builder pattern
 * @param name the name of the region as optional
 * @param population the population of the region as optional
 * @param richness the richnes of the region as optional
 * @param climate the climate of the region as optional
 * @param bordersControl the bordersControl of the region as optional
 * @param globalization the globalization of the region as optional
 * @param populationDensity the population density of the region as optional
 * @param borderingRegionsIds the bordering regions' names of the region
 * @param hasAirport true if the region should have an airport
 * @param hasPort true if the region should have a port
 */
case class RegionBuilder private (name: Option[Name],
                         population: Option[Population],
                         richness: Option[Richness],
                         climate: Option[Climate],
                         bordersControl: Option[BordersControl],
                         globalization: Option[Globalization],
                         populationDensity: Option[PopulationDensity],
                         borderingRegionsIds: List[Name],
                         hasAirport: Boolean,
                         hasPort: Boolean) extends ConfigurationBuilder[Region]:
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

  /**
   * set the region name if the given name does not contains digits
   *
   * @param name the name of the region. It can't contains digits
   * @return a new RegionBuilder like the previous one but with the name set if it satisfies the requisites
   */
  def setName(name: Name): RegionBuilder = if name.exists(c => c.isDigit) then this else copy(name = Some(name))

  /**
   * set the region population
   *
   * @param population the population of the region
   * @return a new RegionBuilder like the previous one but with the population set
   */
  def setPopulation(population: Population): RegionBuilder = copy(population = Some(population))

  /**
   * set the region richness if it is positive and less equals than 5
   *
   * @param richness the richness of the region
   */
  def setRichness(richness: Richness): RegionBuilder =
    if richness >= 1 && richness <= maxRichnessValue then copy(richness = Some(richness)) else this

  /**
   * set the region climate if it is positive and less equals than 3
   *
   * @param richness the climate of the region
   * @return a RegionBuilder with the climate field set if the value is admissible. Otherwise return the same builder with no changes
   */
  def setClimate(climate: Climate): RegionBuilder =
    if climate >= 1 && climate <= maxClimateValue then copy(climate = Some(climate)) else this

  /**
   * set the region borders control if it is positive and less equals than 5
   *
   * @param bordersControl the borders control of the region
   * @return a RegionBuilder with the borders control field set if the value is admissible. Otherwise return the same builder with no changes
   */
  def setBordersControl(bordersControl: BordersControl): RegionBuilder =
    if bordersControl >= 1 && bordersControl <= maxBorderControlValue then copy(bordersControl = Some(bordersControl)) else this

  /**
   * set the region globalization if it is positive and less equals than 5
   *
   * @param globalization the globalization of the region
   * @return a RegionBuilder with the globalization field set if the value is admissible. Otherwise return the same builder with no changes
   */
  def setGlobalization(globalization: Globalization): RegionBuilder =
    if globalization >= 1 && globalization <= maxGlobalizationValue then copy(globalization = Some(globalization)) else this

  /**
   * set the region population density if it is positive and less equals than 5
   *
   * @param populationDensity the population density of the region
   * @return a RegionBuilder with the population density field set if the value is admissible. Otherwise return the same builder with no changes
   */
  def setPopulationDensity(populationDensity: PopulationDensity): RegionBuilder =
    if populationDensity >= 1 && populationDensity <= maxPopulationDensityValue then copy(populationDensity = Some(populationDensity)) else this

  /**
   * set the bordering regions
   *
   * @param borderingRegionsIds the list of the names of the bordering regions
   * @return a RegionBuilder with the borderingRegionsIds field set
   */
  def setBorderingRegions(borderingRegionsIds: List[Name]): RegionBuilder = copy(borderingRegionsIds = borderingRegionsIds)

  /**
   * set the hasPort field to true
   *
   * @return a RegionBuilder with the hasPort field set true
   */
  def addPort: RegionBuilder = copy(hasPort = true)

  /**
   * set the hasAirport field to true
   *
   * @return a RegionBuilder with the hasAirport field set true
   */
  def addAirport: RegionBuilder = copy(hasAirport = true)

  /**
   * build, if possible a new Region using the fields that have been previously set
   *
   * @return
   *  - None if at least one of the mandatory fields has not been set
   *  - a new BasicRegion if both hasPort and hasAirport fields have not been set
   *  - a new RegionWithPort if hasPort has been set and hasAirport has not
   *  - a new RegionWithAirport if hasAirport has been set and hasPort has not
   *  - a new RegionWithAirportAndPort if both hasAirport and hasPort fields have been set
   */
  override def build(): Option[Region] = this match
    case RegionBuilder(Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), _, false, false) =>
      Some(new BasicRegion(RegionConfiguration(name.get, population.get, richness.get, climate.get, bordersControl.get, globalization.get, populationDensity.get)))
    case RegionBuilder(Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), _, true, false) =>
      Some(new RegionWithAirport(RegionConfiguration(name.get, population.get, richness.get, climate.get, bordersControl.get, globalization.get, populationDensity.get), AirportRouteManager()))
    case RegionBuilder(Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), _, false, true) =>
      Some(new RegionWithPort(RegionConfiguration(name.get, population.get, richness.get, climate.get, bordersControl.get, globalization.get, populationDensity.get), PortRouteManager()))
    case RegionBuilder(Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), _, true, true) =>
      Some(new RegionWithAirportAndPort(RegionConfiguration(name.get, population.get, richness.get, climate.get, bordersControl.get, globalization.get, populationDensity.get), AirportRouteManager(), PortRouteManager()))
    case _ => None

/**
 * an object that allows to easily create a Region using the builder pattern
 */
object RegionBuilder:
  /**
   * @return a RegionBuilder with all the fields unset by default
   */
  def apply() = new RegionBuilder(None, None, None, None, None, None, None, List(), false, false)
