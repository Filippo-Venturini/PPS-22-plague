package model.vaccine

object VaccineLogics:
  given VaccineLogic = new BasicVaccineLogic

class VaccineHandler:
  private var isResearchStarted: Boolean = false
  
  def getVaccineProgress: Double = 

  def computeResearchStep(worldInfectionPercentage: Double)(using vaccineLogic: VaccineLogic): Unit =
    if !isResearchStarted then tryStartResearch(worldInfectionPercentage)(vaccineLogic) else vaccineLogic.researchStep()

  private def tryStartResearch(worldInfectionPercentage: Double)(vaccineLogic: VaccineLogic): Unit =
    if vaccineLogic.canResearchStart(worldInfectionPercentage) then vaccineLogic.researchStep(); this.isResearchStarted = true
