package model.powerUp
import model.infection.Virus

trait PowerUpLogic:
  def applyTo(virus: Virus): Unit

object PowerUpLogics:
  val coldResistanceI: PowerUpLogic = new PowerUpLogic:
    override def applyTo(virus: Virus): Unit =
      virus.coldRegionsInfectivity = virus.coldRegionsInfectivity + 5

  val coldResistanceII: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Virus): Unit =
      virus.coldRegionsInfectivity = virus.coldRegionsInfectivity + 3

  val warmResistanceI: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Virus): Unit =
      virus.warmRegionsInfectivity = virus.warmRegionsInfectivity + 5

  val warmResistanceII: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Virus): Unit =
      virus.warmRegionsInfectivity = virus.warmRegionsInfectivity + 3

  val BacterialResistance: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Virus): Unit =
      virus.richRegionsInfectivity = virus.richRegionsInfectivity + 3
      virus.highDensityRegionsInfectivity = virus.highDensityRegionsInfectivity + 1

  val AirportEnablement: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Virus): Unit =
      virus.airportEnabled = true

  val PortEnablement: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Virus): Unit =
      virus.portEnabled = true

  val InfectionThroughAnimals: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Virus): Unit =
      virus.warmRegionsInfectivity = virus.warmRegionsInfectivity + 1
      virus.poorRegionsInfectivity = virus.poorRegionsInfectivity + 3
      virus.highDensityRegionsInfectivity = virus.highDensityRegionsInfectivity + 1

  val InfectionThroughRespiratoryTract: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Virus): Unit =
      virus.poorRegionsInfectivity = virus.poorRegionsInfectivity + 3
      virus.richRegionsInfectivity = virus.richRegionsInfectivity + 2

  val MedicinesResistance: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Virus): Unit =
      virus.highDensityRegionsInfectivity = virus.highDensityRegionsInfectivity + 1
      virus.richRegionsInfectivity = virus.richRegionsInfectivity + 4
      virus.vaccineResistance = virus.vaccineResistance + 2

  val InfectedDrinkingWater: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Virus): Unit =
      virus.richRegionsInfectivity = virus.poorRegionsInfectivity + 2
      virus.poorRegionsInfectivity = virus.poorRegionsInfectivity + 1

  val SpontaneousMutations: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Virus): Unit =
      virus.vaccineResistance = virus.vaccineResistance + 3
      virus.poorRegionsInfectivity = virus.poorRegionsInfectivity + 1
      virus.richRegionsInfectivity = virus.poorRegionsInfectivity + 1
