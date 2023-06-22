package model.configuration

import model.world.RegionTypes.*
object Builders:
  case class RegionBuilder(name: Option[Name],
                           population: Option[Population],
                           richness: Option[Richness],
                           climate: Option[Climate],
                           bordersControl: Option[BordersControl]):
    def setName(name: Name): RegionBuilder = new RegionBuilder(Some(name), population, richness, climate, bordersControl)
    def setPopulation(population: Population): RegionBuilder = new RegionBuilder(name, Some(population), richness, climate, bordersControl)
    def setRichness(richness: Richness): RegionBuilder = new RegionBuilder(name, population, Some(richness), climate, bordersControl)
    def setClimate(climate: Climate): RegionBuilder = new RegionBuilder(name, population, richness, Some(climate), bordersControl)
    def setBordersControl(bordersControl: BordersControl): RegionBuilder = new RegionBuilder(name, population, richness, climate, Some(bordersControl))

  object RegionBuilder:
    def apply() = new RegionBuilder(None, None, None, None, None)
