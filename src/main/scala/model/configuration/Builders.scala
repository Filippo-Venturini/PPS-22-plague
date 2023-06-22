package model.configuration

import model.world.{BasicRegion, Name, Population, Region, RegionConfiguration, Richness}

object Builders:
  case class RegionBuilder(name: Option[Name],
                           population: Option[Population],
                           richness: Option[Richness]):
    def setName(name: Name): RegionBuilder = new RegionBuilder(Some(name), population, richness)
    def setPopulation(population: Population): RegionBuilder = new RegionBuilder(name, Some(population), richness)
    def setRichness(richness: Richness): RegionBuilder = new RegionBuilder(name, population, Some(richness))

  object RegionBuilder:
    def apply() = new RegionBuilder(None, None, None)
