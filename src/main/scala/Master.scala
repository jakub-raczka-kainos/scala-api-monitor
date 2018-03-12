import akka.actor.{Actor, ActorRef, Props, ActorSystem}
import scala.concurrent.duration._
import akka.util.Timeout

import scala.concurrent.Future
import scala.util.{Failure, Success}

class Master extends App {
  val system = ActorSystem("system")
  val stops_url = "http://91.244.248.30/dataset/c24aa637-3619-4dc2-a171-a23eec8f2172/resource/cd4c08b5-460e-40db-b920-ab9fc93c1a92/download/stops.json"
  val actor = system.actorOf(Props(classOf[Worker], this))

  val cancellable = system.scheduler.schedule (
    0 milliseconds,
    10000 milliseconds,
    actor,
    StartTask(stops_url))
}
