package model.world

/**
 * Object that contains all the type necessary for the regions' parameters
 */
object RegionParameters :
  type Name = String
  type Population = Int
  type Richness = Int
  type Climate = Int
  type BordersControl = Int
  type Globalization = Int
  type PopulationDensity = Int

  /**
   * max value that richness can assume
   */
  val maxRichnessValue: Richness = 5
  val maxClimateValue: Climate = 3
  val maxBorderControlValue: BordersControl = 5
  val maxGlobalizationValue: Globalization = 5
  val maxPopulationDensityValue: PopulationDensity = 5
  
  /**
   * Case class that hold the configuration characteristics of a region
   *
   * @param name the name of the region
   * @param population the total population of the region
   * @param richness the level of richness of the region
   * @param climate the climate level of the region
   * @param bordersControl the level of borders control of the region
   * @param globalization the level of globalization
   * @param populationDensity the amount of population density
   */
  case class RegionConfiguration(name: Name,
                                 population: Population,
                                 richness: Richness,
                                 climate: Climate,
                                 bordersControl: BordersControl,
                                 globalization: Globalization,
                                 populationDensity: PopulationDensity)

  /**
   * It specify the modality in which a region is reachable
   */
  enum ReachableMode:
    case Border
    case Airport
    case Port

  type ReachableRegion = (Region, ReachableMode)
