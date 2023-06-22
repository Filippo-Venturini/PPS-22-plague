package model.world
import RegionTypes.*

/**
 * Class that represent the definition of a region
 */
abstract class Region:
  def regionConfiguration: RegionConfiguration
  
  val name: Name = regionConfiguration.name
  val population: Population = regionConfiguration.population
  val richness: Richness = regionConfiguration.richness
  val climate: Climate = regionConfiguration.climate
  val bordersControl: BordersControl = regionConfiguration.bordersControl
  val globalization: Globalization = regionConfiguration.globalization
  val populationDensity: PopulationDensity = regionConfiguration.populationDensity
  protected var borderingRegions: List[Region] = List()
  def infectedAmount: Int
  def infectedAmount_= (newAmount: Int): Unit

  /**
   *
   * @param borderingRegion a region that is directly connected by border
   */
  def addBorderingRegion(borderingRegion: Region): Unit = this.borderingRegions = this.borderingRegions :+ borderingRegion

  /**
   *
   * @param borderingRegions a List of regions that will be add to the bordering regions
   */
  def addBorderingRegions(borderingRegions: List[Region]): Unit = this.borderingRegions = this.borderingRegions ::: borderingRegions

  /**
   *
   * @return the list of all the region reachable
   */
  def getReachableRegions: List[ReachableRegion]

/**
 * Class that represent a basic region without ports or airports
 *
 * @param regionConfiguration the configuration that contains all the region's characteristics
 */
class BasicRegion (override val regionConfiguration: RegionConfiguration) extends Region:
  override var infectedAmount: Int = 0

  /**
   *
   *  @return the list of all the region reachable, so only the bordering ones
   */
  override def getReachableRegions: List[ReachableRegion] = borderingRegions.map(r => (r, ReachableMode.Border))

/**
 * Mixin that add the possibility of having a port
 */
trait WithPort extends Region:
  def portRoutes: List[Route] = List()

  /**
   *
   *  @return the list of all the region reachable, so the bordering and also the reachable with port
   */
  abstract override def getReachableRegions: List[ReachableRegion] = super.getReachableRegions

/**
 * Class that represent a region with a port
 *
 * @param regionConfiguration the configuration that contains all the region's characteristics
 */
class RegionWithPort(regionConfiguration: RegionConfiguration) extends BasicRegion(regionConfiguration) with WithPort



