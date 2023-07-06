package model.powerUp

import model.powerUp.PowerUpType.*

enum PowerUpType(val price: Int, val prerequisite: List[PowerUpType]):
  case ColdResistanceI extends PowerUpType(2, List())
  case ColdResistanceII extends PowerUpType(2, List(ColdResistanceI))
  case HotResistanceI extends PowerUpType(2, List())
  case HotResistanceII extends PowerUpType(2, List(HotResistanceI))
  case BacterialResistance extends PowerUpType(2, List())
  case AirportEnablement extends PowerUpType(2, List())
  case PortEnablement extends PowerUpType(2, List())
  case InfectionThroughAnimals extends PowerUpType(2, List(ColdResistanceII, HotResistanceI))
  case InfectionThroughRespiratoryTract extends PowerUpType(2, List(ColdResistanceII, HotResistanceII, AirportEnablement))
  case MedicinesResistance extends PowerUpType(2, List(BacterialResistance, PortEnablement, AirportEnablement))
  case InfectedDrinkingWater extends PowerUpType(2, List(BacterialResistance, InfectionThroughAnimals))
  case SpontaneousMutations extends PowerUpType(2, List(InfectionThroughRespiratoryTract, MedicinesResistance, InfectedDrinkingWater))
