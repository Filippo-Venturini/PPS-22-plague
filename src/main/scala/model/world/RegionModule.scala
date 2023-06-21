package model.world

abstract class Region:
  def name: String
  def population: Int
  def richness: Int
  def climate: Int
  def bordersControl: Int
  def globalization: Int
  def populationDensity: Int
  def infectedAmount: Int
  def infectedAmount_= (newAmount: Int): Unit

class BasicRegion (override val name: String,
                  override val population: Int,
                   override val richness: Int,
                   override val climate: Int,
                   override val bordersControl: Int,
                   override val globalization: Int,
                   override val populationDensity: Int,
                   override var infectedAmount: Int) extends Region



