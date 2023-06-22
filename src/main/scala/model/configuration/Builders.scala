package model.configuration

import model.world.{BasicRegion, Name, Population, Region, RegionConfiguration, Richness}

object Builders:
  case class RegionBuilder(name: Option[Name], 
                           population: Option[Population], 
                           richness: Option[Richness]):
    def setName(name: Name): RegionBuilder = new RegionBuilder(Some(name), this.population, this.richness)

  object RegionBuilder:
    def apply() = new RegionBuilder(None, None, None)
