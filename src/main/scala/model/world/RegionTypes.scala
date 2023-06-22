package model.world

object RegionTypes :
  type Name = String
  type Population = Int
  type Richness = Int
  type Climate = Int
  type BordersControl = Int
  type Globalization = Int
  type PopulationDensity = Int

  case class RegionConfiguration(name: Name,
                                 population: Population,
                                 richness: Richness,
                                 climate: Climate,
                                 bordersControl: BordersControl,
                                 globalization: Globalization,
                                 populationDensity: PopulationDensity)
  enum ReachableMode:
    case Border
    case Airport
    case Port

  type ReachableRegion = (Region, ReachableMode)
