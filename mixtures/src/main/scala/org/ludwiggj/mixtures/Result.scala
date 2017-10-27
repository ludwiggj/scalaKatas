package org.ludwiggj.mixtures

case class Result(position: Position, smoke: Option[Int]) {
  def positionValue() = position.value.get
}