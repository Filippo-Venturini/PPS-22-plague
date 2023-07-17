package model.vaccine

object VaccineLogics:
  given VaccineLogic = new BasicVaccineLogic

class VaccineHandler:
  private var isResearchStarted: Boolean = false
  
  def computeResearchStep(using vaccineLogic: VaccineLogic): Unit =
    if !isResearchStarted then tryStartResearch(vaccineLogic) else vaccineLogic.researchStep()
  
  def tryStartResearch(vaccineLogic: VaccineLogic): Unit = 
    if vaccineLogic.canResearchStart(10.0) then vaccineLogic.researchStep(); this.isResearchStarted = true
