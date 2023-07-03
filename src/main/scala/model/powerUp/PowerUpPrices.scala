package model.powerUp

class PowerUpPrices:
  import PowerUpType.*
  val powerUpWithPrices: Map[PowerUpType, Int] = Map(
    ColdResistanceI -> 2,
    ColdResistanceII -> 2,
    HotResistanceI -> 2,
    HotResistanceII -> 2,
    BacterialResistance -> 2,
    BacterialResistance -> 2,
    AirportEnablement -> 2,
    PortEnablement -> 2,
    InfectionThroughAnimals -> 2,
    InfectionThroughRespiratoryTract -> 2,
    MedicinesResistance -> 2,
    InfectedDrinkingWater -> 2,
    SpontaneousMutations -> 2,
  )