package model.powerUp


trait PowerUp:
  def typePowerUp: PowerUpType
  def getPrice: Int
  var hasBeenBought: Boolean = false

object PowerUp:
  def apply(typePowerUp: PowerUpType): PowerUp =
    PowerUpImpl(typePowerUp)

  private class PowerUpImpl(override val typePowerUp: PowerUpType) extends PowerUp:
    import PowerUpType.*
    override def getPrice: Int = typePowerUp match
      case ColdResistanceI => 2
      case ColdResistanceII => 2
      case HotResistanceI => 2
      case HotResistanceII => 2
      case BacterialResistance => 2
      case AirportEnablement => 2
      case PortEnablement => 2
      case InfectionThroughAnimals => 2
      case InfectionThroughRespiratoryTract => 2
      case MedicinesResistance => 2
      case InfectedDrinkingWater => 2
      case SpontaneousMutations => 2
