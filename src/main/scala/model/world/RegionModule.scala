package model.world

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

abstract class Region:
  def regionConfiguration: RegionConfiguration
  
  val name: Name = regionConfiguration.name
  val population: Population = regionConfiguration.population
  val richness: Richness = regionConfiguration.richness
  val climate: Climate = regionConfiguration.climate
  val bordersControl: BordersControl = regionConfiguration.bordersControl
  val globalization: Globalization = regionConfiguration.globalization
  val populationDensity: PopulationDensity = regionConfiguration.populationDensity
  def infectedAmount: Int
  def infectedAmount_= (newAmount: Int): Unit
  var borderingRegions: List[Region] = List()
  def addBorderingRegion(borderingRegion: Region): Unit = this.borderingRegions = this.borderingRegions :+ borderingRegion
  def addBorderingRegions(borderingRegions: List[Region]): Unit = this.borderingRegions = this.borderingRegions ::: borderingRegions

class BasicRegion (override val regionConfiguration: RegionConfiguration) extends Region:
  override var infectedAmount: Int = 0




