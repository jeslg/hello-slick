package org.hablapps.slick

import slick.driver.MySQLDriver.api._

import scala.concurrent._, ExecutionContext.Implicits.global
import scala.concurrent.duration._

object HelloSlick extends App {

  val db = Database.forConfig("default")

  class People(tag: Tag) extends Table[(String, Int)](tag, "people") {
    def name = column[String]("name")
    def age = column[Int]("age")
    def * = (name, age)
  }
  val people = TableQuery[People]

  println("People")
  val res = db.run(people.result).map(_.foreach {
    case (name, age) => println(s"NAME: $name, AGE: $age")
  })

  Await.result(res, 5000 millis)

  db.close
}

