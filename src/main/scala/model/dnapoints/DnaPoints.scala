package model.dnapoints

import model.world.Region

object DnaPoints {

  trait DnaPoint:
    def collect(): Unit

  trait DnaPointsHandler:
    def collectedPoints: Int

    def collectedPoints_=(newAmount: Int): Unit

    def spawnDnaPoint(region: Region): DnaPoint
    def addObserver(observer: DnaPointSpawnObserver): Unit

  object DnaPointsHandler:
    def apply(): DnaPointsHandler = new BasicDnaPointsHandler(0)

    private class BasicDnaPointsHandler(override var collectedPoints: Int) extends DnaPointsHandler :
      private var spawnedPoints: List[DnaPoint] = List()
      private var observers: List[DnaPointSpawnObserver] = List()

      override def spawnDnaPoint(region: Region): DnaPoint =
        spawnedPoints = BasicDnaPoint(this, region.name) +: spawnedPoints
        observers.foreach(_.onDnaPointSpawn(region.name))
        spawnedPoints.head

      override def addObserver(observer: DnaPointSpawnObserver): Unit = observers = observer +: observers

      case class BasicDnaPoint(handler: BasicDnaPointsHandler, regionName: String) extends DnaPoint:
        private var collected = false
        def collect(): Unit = this.collected match
          case false =>
            this.collected = true
            handler.collectedPoints = handler.collectedPoints + 1
            handler.spawnedPoints = handler.spawnedPoints.filterNot(_ == this)
          case _ =>

  trait DnaPointSpawnObserver:
    def onDnaPointSpawn(regionName: String): Unit
}