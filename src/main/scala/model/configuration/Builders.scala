package model.configuration

import model.world.{BasicRegion, Name, Population, Region, RegionConfiguration, Richness}

/*import model.world.Region
import model.world.BasicRegion*/

object Builders {

  class RegionBuilder():
    private var name: Option[Name] = None
    private var population: Option[Population] = None
    private var richness: Option[Richness] = None
    def setName(name: Name): RegionBuilder = {this.name = Some(name); this}
    def setPopulation(population: Population): RegionBuilder = {this.population = Some(population); this}
    def setRichness(richness: Richness): RegionBuilder = {this.richness = Some(richness); this}
    def build(): Option[Region] = List(name, population, richness).exists(_.isEmpty) match
      case true => None
      case false => Some(new BasicRegion(RegionConfiguration(name.get, population.get, 0, 0, 0, 0, 0)))

}
