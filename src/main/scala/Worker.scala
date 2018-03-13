import akka.actor.Actor
import net.liftweb.json.JInt
import net.liftweb.json._

import scalaj.http._
import spray.json._
import spray.json.DefaultJsonProtocol._

class Worker extends Actor {
  override def receive: Receive = {
    case StartTask(url) =>
      val content = scala.io.Source.fromURL(url)
      val rawJson = Http(url).asString.body
//      val allIds = 'lastUpdate / 'book / * / 'author
//      println(rawJson)

      val stops = parse(rawJson)
        .children
        .head \ "stops" \ "stopId" \ classOf[JInt]
      stops.foreach(println)
  }
}
