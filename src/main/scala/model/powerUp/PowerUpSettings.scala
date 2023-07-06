package model.powerUp

import model.powerUp.PowerUpSettings.ColdResistanceI

enum PowerUpSettings(val price: Int, val prerequisite: List[PowerUpSettings]):
  case ColdResistanceI extends PowerUpSettings(2, List())
  case ColdResistanceII extends PowerUpSettings(2, List(PowerUpSettings.ColdResistanceI))
  /*case HotResistanceI extends PowerUpType(2)
  case HotResistanceII extends PowerUpType(2)
  case BacterialResistance extends PowerUpType(2)
  case AirportEnablement extends PowerUpType(2)
  case PortEnablement extends PowerUpType(2)
  case InfectionThroughAnimals extends PowerUpType(2)
  case InfectionThroughRespiratoryTract extends PowerUpType(2)
  case MedicinesResistance extends PowerUpType(2)
  case InfectedDrinkingWater extends PowerUpType(2)
  case SpontaneousMutations extends PowerUpType(2)
  */

