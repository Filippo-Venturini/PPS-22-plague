package utils

import scala.util.Random

object Iterables {

  extension [T](l: Iterable[T])

    /**
     * if the Iterable is empty returns None, otherwise return a random element from the collection
     */
    def getRandomElement(): Option[T] = l.isEmpty match
      case true => None
      case _ =>
        val index: Int = Random.nextInt(l.size)
        Some(l.zipWithIndex.find(_._2 == index).map(_._1).get)

}
