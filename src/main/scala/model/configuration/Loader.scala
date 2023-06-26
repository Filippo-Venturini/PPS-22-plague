package model.configuration

import Parsers.Parser

import scala.io.Source
import scala.util.{Success, Failure, Using, Try}

object File:
  def readLinesFromResources(path: String): Iterable[String] =
    Try(Source.fromResource(path).getLines()) match
      case Success(value) => value.toSeq
      case Failure(_) => Seq()