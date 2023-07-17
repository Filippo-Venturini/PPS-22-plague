package model.vaccine

object VaccineLogics:
  given VaccineLogic = new BasicVaccineLogic

class VaccineHandler:
  private var isResearchStarted: Boolean = false
  var vaccineProgression: Double = 0.0

  def computeResearchStep(worldInfectionPercentage: Double)(using vaccineLogic: VaccineLogic): Unit =
    if !isResearchStarted then tryStartResearch(worldInfectionPercentage)(vaccineLogic) 
    else vaccineProgression = vaccineLogic.researchStep(vaccineProgression)

  private def tryStartResearch(worldInfectionPercentage: Double)(vaccineLogic: VaccineLogic): Unit =
    if vaccineLogic.canResearchStart(worldInfectionPercentage) then vaccineProgression = vaccineLogic.researchStep(vaccineProgression)
    this.isResearchStarted = true
