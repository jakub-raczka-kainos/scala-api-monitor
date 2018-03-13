import akka.actor.{Actor, Props}
import akka.routing.RoundRobinPool
import net.liftweb.json.JInt
import net.liftweb.json._

import scalaj.http._

class Ztm extends Actor {
  override def receive: Receive = {
    case StartTask(url) =>
      val rawJson = Http(url).asString.body

      val stops = parse(rawJson)
        .children
        .head \ "stops" \ "stopId" \ classOf[JInt]
//      stops.foreach(println)
//      println("Stops" + stops.length)

      val delays_url_prefix = "http://87.98.237.99:88/delays?stopId="
      val workerActors = context.actorOf(Props[Worker].withRouter(RoundRobinPool(10)), name = "WorkerActors")
      stops.foreach( id => workerActors ! FetchDelays(delays_url_prefix + id))
  }
}
