package model.configuration

import model.world.Region
import model.world.RegionTypes.*
object Builders:
  case class RegionBuilder(name: Option[Name],
                           population: Option[Population],
                           richness: Option[Richness],
                           climate: Option[Climate],
                           bordersControl: Option[BordersControl],
                           globalization: Option[Globalization],
                           populationDensity: Option[PopulationDensity],
                           borderingRegionsIds: List[Name]):
    private def copy(name: Option[Name] = name,
                     population: Option[Population] = population,
                     richness: Option[Richness] = richness,
                     climate: Option[Climate] = climate,
                     bordersControl: Option[BordersControl] = bordersControl,
                     globalization: Option[Globalization] = globalization,
                     populationDensity: Option[PopulationDensity] = populationDensity,
                     borderingRegionsIds: List[Name] = borderingRegionsIds): RegionBuilder =
      new RegionBuilder(name, population, richness, climate, bordersControl, globalization, populationDensity, borderingRegionsIds)
    def setName(name: Name): RegionBuilder = copy(name=Some(name))
    def setPopulation(population: Population): RegionBuilder = copy(population=Some(population))
    def setRichness(richness: Richness): RegionBuilder = copy(richness=Some(richness))
    def setClimate(climate: Climate): RegionBuilder = copy(climate=Some(climate))
    def setBordersControl(bordersControl: BordersControl): RegionBuilder = copy(bordersControl=Some(bordersControl))
    def setGlobalization(globalization: Globalization): RegionBuilder = copy(globalization=Some(globalization))
    def setPopulationDensity(populationDensity: PopulationDensity): RegionBuilder = copy(populationDensity=Some(populationDensity))
    def setBorderingRegions(borderingRegionsIds: List[Name]): RegionBuilder = copy(borderingRegionsIds=borderingRegionsIds)
  object RegionBuilder:
    def apply() = new RegionBuilder(None, None, None, None, None, None, None, List())
