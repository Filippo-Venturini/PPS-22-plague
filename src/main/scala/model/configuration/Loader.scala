package model.configuration

import Parsers.Parser

import scala.io.Source
import scala.util.{Success, Failure, Using}

object File:
  def readLines(path: String): Iterable[String] =
    Using(Source.fromFile(path))(_.getLines()) match
      case Success(value) => {println("non empty"); value.toSeq}
      case Failure(exception) => {println(exception); Seq()}