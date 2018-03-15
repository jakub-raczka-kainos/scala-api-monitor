import akka.actor.{Actor, Props}
import akka.routing.BalancingPool
import net.liftweb.json.JInt
import net.liftweb.json._

import scalaj.http._

class Ztm extends Actor {

  val workerActors = context.actorOf(Props[Worker]
    .withDispatcher("my-blocking-dispatcher")
    .withRouter(BalancingPool(16)), name = "WorkerActors")

  override def receive: Receive = {
    case StartTask(url) =>
      val rawJson = Http(url).asString.body

      val stops = parse(rawJson)
        .children
        .head \ "stops" \ "stopId" \ classOf[JInt]
//      stops.foreach(println)
//      println("Stops" + stops.length)
      import com.typesafe.config.ConfigFactory
      val delays_url_prefix = ConfigFactory.load("application.conf").getString("urls.delays_url_prefix")

      stops.foreach( id => workerActors ! FetchDelays(delays_url_prefix + id))
  }
}
