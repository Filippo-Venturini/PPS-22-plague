package model.powerUp

import model.powerUp.PowerUpType.*

/**
 * Represent the Power Up type. For each possible Power Up is determinate the price and a list of mandatory prerequisite
 * for to get it.
 * The list might contain other PowerUpTypes, or it might be empty if it has no prerequisites
 */
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
