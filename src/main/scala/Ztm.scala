import akka.actor.{Actor, Props}
import akka.routing.BalancingPool
import net.liftweb.json.JInt
import net.liftweb.json._

import scalaj.http._

class Ztm extends Actor {

  var stops: List[Int]  = Nil
  val conf = context.system.settings.config
  val workerActors = context.actorOf(Props[Worker]
    .withDispatcher("my-blocking-dispatcher")
    .withRouter(BalancingPool(20)), name = "WorkerActors")

  override def receive: Receive = {
    case FetchStops =>
      val rawJson = Http(conf.getString("urls.stops_url")).asString.body

      stops = (parse(rawJson)
        .children
        .head \ "stops" \ "stopId" \ classOf[JInt])
        .map(_.toInt)


    case Delays =>
      println("Stops size: " + stops.size)
      stops.foreach(id => workerActors ! FetchDelays(conf.getString("urls.delays_url_prefix") + id))

  }
}
