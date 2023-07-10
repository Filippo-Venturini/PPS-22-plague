package model.dnapoints

object DnaPoints {

  trait DnaPointsHandler:
    def collectedPoints: Int

    def collectedPoints_=(newAmount: Int): Unit

    def spawnDnaPoint(): DnaPoint

  object DnaPointsHandler:
    def apply(): DnaPointsHandler = new SimpleDnaPointsHandler(0)

    private class SimpleDnaPointsHandler(override var collectedPoints: Int) extends DnaPointsHandler :
      override def spawnDnaPoint(): DnaPoint = DnaPoint(this)

  case class DnaPoint(handler: DnaPointsHandler):
    private var collected = false
    def collect(): Unit = this.collected match
      case false => {this.collected = true; handler.collectedPoints = handler.collectedPoints + 1}
      case _ => 
}