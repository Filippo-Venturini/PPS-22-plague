package model.dnapoints

import model.dnapoints.DnaPoints.SpawnLogic.*
import model.world.{Filters, Region, World}

object DnaPoints {

  trait DnaPoint:
    def collect(): Unit

  trait DnaPointsHandler:
    def collectedPoints: Int

    def collectedPoints_=(newAmount: Int): Unit

    def spawnDnaPoint(region: Region): DnaPoint
    def addObserver(observer: DnaPointSpawnObserver): Unit
    def computeDnaPointSpawn(): Unit

  object DnaPointsHandler:
    def apply(world: World): DnaPointsHandler = new BasicDnaPointsHandler(0, world)

    private class BasicDnaPointsHandler(override var collectedPoints: Int, private val world: World) extends DnaPointsHandler :
      import model.world.Filters.given_RegionFilter
      private var spawnedPoints: List[DnaPoint] = List()
      private var observers: List[DnaPointSpawnObserver] = List()
      private val spawnPointLogic: SpawnPointLogic = ???//SpawnPointOnNewInfectedRegions(world)

      override def spawnDnaPoint(region: Region): DnaPoint =
        spawnedPoints = BasicDnaPoint(this, region.name) +: spawnedPoints
        observers.foreach(_.onDnaPointSpawn(region.name))
        spawnedPoints.head

      override def computeDnaPointSpawn(): Unit =
        spawnPointLogic.evaluate().foreach(spawnDnaPoint)


      override def addObserver(observer: DnaPointSpawnObserver): Unit = observers = observer +: observers

      case class BasicDnaPoint(handler: BasicDnaPointsHandler, regionName: String) extends DnaPoint:
        private var collected = false
        def collect(): Unit = this.collected match
          case false =>
            this.collected = true
            handler.collectedPoints = handler.collectedPoints + 1
            handler.spawnedPoints = handler.spawnedPoints.filterNot(_ == this)
          case _ =>

  object SpawnLogic:
    private var infectedRegionsOnLastStep: List[Region] = List()
    abstract class SpawnPointLogic:
      def evaluate(): List[Region]

    class EmptyLogic extends SpawnPointLogic:
      override def evaluate(): List[Region] = List()

    trait OnNewInfectedRegions(private val world: World) extends SpawnPointLogic:
      abstract override def evaluate(): List[Region] =
        val infectedRegions = world.getRegions(using Filters.infectedRegions)
        val newlyInfectedRegions = infectedRegions.diff(infectedRegionsOnLastStep)
        infectedRegionsOnLastStep = infectedRegions
        newlyInfectedRegions ::: super.evaluate()

    trait EveryXSeconds(private val world: World, private val spawnRate: Int) extends SpawnPointLogic:
      import utils.Iterables.getRandomElement
      private var lastStepTime = System.currentTimeMillis()
      abstract override def evaluate(): List[Region] = (System.currentTimeMillis()-lastStepTime) >= spawnRate match
        case true =>
          lastStepTime = System.currentTimeMillis()
          world.getRegions(using Filters.infectedRegions).getRandomElement().toList ::: super.evaluate()
        case _ => super.evaluate()

    case class OnNewInfectedRegionsLogic(world: World) extends EmptyLogic with OnNewInfectedRegions(world)
    case class EveryXSecondsLogic(world: World, spawnRate: Int) extends EmptyLogic with EveryXSeconds(world, spawnRate)
    case class BasicLogic(world: World, spawnRate: Int) extends EmptyLogic with OnNewInfectedRegions(world) with EveryXSeconds(world, spawnRate)

  trait DnaPointSpawnObserver:
    def onDnaPointSpawn(regionName: String): Unit
}