package model.powerUp

import model.powerUp.PowerUpType.*
import model.powerUp.PowerUpLogics.*

/**
 * Represent the Power Up type. For each possible Power Up is determinate the price and a list of mandatory prerequisite
 * for to get it.
 * The list might contain other PowerUpTypes, or it might be empty if it has no prerequisites
 */
enum PowerUpType(val price: Int, val prerequisite: List[PowerUpType], val logic: PowerUpLogic):
  case ColdResistanceI extends PowerUpType(2, List(), coldResistanceI)
  case ColdResistanceII extends PowerUpType(2, List(ColdResistanceI), coldResistanceII)
  case HotResistanceI extends PowerUpType(2, List(), hotResistanceI)
  case HotResistanceII extends PowerUpType(2, List(HotResistanceI), hotResistanceII)
  case BacterialResistance extends PowerUpType(2, List(ColdResistanceII, HotResistanceII), bacterialResistance)
  case AirportEnablement extends PowerUpType(2, List(), airportEnablement)
  case PortEnablement extends PowerUpType(2, List(), portEnablement)
  case InfectedDrinkingWater extends PowerUpType(2, List(), infectedDrinkingWater)
  case InfectionThroughAnimals extends PowerUpType(2, List(InfectedDrinkingWater), infectionThroughAnimals)
  case MedicinesResistance extends PowerUpType(2, List(), medicinesResistance)
  case InfectionThroughRespiratoryTract extends PowerUpType(2, List(MedicinesResistance), infectionThroughRespiratoryTract)
  case AlphaMutations extends PowerUpType(2, List(), alphaMutations)
  case BetaMutations extends PowerUpType(2, List(AlphaMutations), betaMutations)
  case GammaMutations extends PowerUpType(2, List(BetaMutations), gammaMutations)
  case OmegaMutations extends PowerUpType(2, List(GammaMutations), omegaMutations)
