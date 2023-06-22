package model.configuration

import model.world.{BasicRegion, Name, Population, Climate, Region, RegionConfiguration, Richness}

object Builders:
  case class RegionBuilder(name: Option[Name],
                           population: Option[Population],
                           richness: Option[Richness],
                           climate: Option[Climate]):
    def setName(name: Name): RegionBuilder = new RegionBuilder(Some(name), population, richness, climate)
    def setPopulation(population: Population): RegionBuilder = new RegionBuilder(name, Some(population), richness, climate)
    def setRichness(richness: Richness): RegionBuilder = new RegionBuilder(name, population, Some(richness), climate)
    def setClimate(climate: Climate): RegionBuilder = new RegionBuilder(name, population, richness, Some(climate))

  object RegionBuilder:
    def apply() = new RegionBuilder(None, None, None, None)
