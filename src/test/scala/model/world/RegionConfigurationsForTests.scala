package model.world

import model.world.RegionParameters.RegionConfiguration

object RegionConfigurationsForTests :
  val europeConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)
  val unitedStatesConfiguration: RegionConfiguration = RegionConfiguration("United States", 746000000, 9, 5, 8, 9, 8)
  val russiaConfiguration: RegionConfiguration = RegionConfiguration("Russia", 143000000, 2, 3, 6, 9, 5)
  val japanConfiguration: RegionConfiguration = RegionConfiguration("Japan", 125000000, 4, 1, 7, 4, 8)
  val australiaConfiguration: RegionConfiguration = RegionConfiguration("Australia", 25000000, 6, 2, 1, 3, 5)
  val northAfricaConfiguration: RegionConfiguration = RegionConfiguration("North Africa", 178000000, 3, 7, 3, 9, 2)
  val southAfricaConfiguration: RegionConfiguration = RegionConfiguration("South Africa", 59000000, 7, 2, 5, 5, 7)
