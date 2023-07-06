package model.powerUp

import model.powerUp.PowerUpSettings.*

enum PowerUpSettings(val price: Int, val prerequisite: List[PowerUpSettings]):
  case ColdResistanceI extends PowerUpSettings(2, List())
  case ColdResistanceII extends PowerUpSettings(2, List(ColdResistanceI))
  case HotResistanceI extends PowerUpSettings(2, List())
  case HotResistanceII extends PowerUpSettings(2, List(HotResistanceI))
  case BacterialResistance extends PowerUpSettings(2, List())
  case AirportEnablement extends PowerUpSettings(2, List())
  case PortEnablement extends PowerUpSettings(2, List())
  case InfectionThroughAnimals extends PowerUpSettings(2, List(ColdResistanceII, HotResistanceI))
  case InfectionThroughRespiratoryTract extends PowerUpSettings(2, List(ColdResistanceII, HotResistanceII, AirportEnablement))
  case MedicinesResistance extends PowerUpSettings(2, List(BacterialResistance, PortEnablement, AirportEnablement))
  case InfectedDrinkingWater extends PowerUpSettings(2, List(BacterialResistance, InfectionThroughAnimals))
  case SpontaneousMutations extends PowerUpSettings(2, List(InfectionThroughRespiratoryTract, MedicinesResistance, InfectedDrinkingWater))
