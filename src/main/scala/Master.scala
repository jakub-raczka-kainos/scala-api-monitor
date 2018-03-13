import akka.actor.Props
import scala.concurrent.duration._


object Master extends App {
  val stops_url = "http://91.244.248.30/dataset/c24aa637-3619-4dc2-a171-a23eec8f2172/resource/cd4c08b5-460e-40db-b920-ab9fc93c1a92/download/stops.json"
  val system = akka.actor.ActorSystem("system")
  val actor = system.actorOf(Props(classOf[Ztm]))


  import system.dispatcher
//  val cancellable = system.scheduler.schedule (
//    0 milliseconds,
//    10000 milliseconds,
//    actor,
//    StartTask(stops_url))

  system.scheduler.scheduleOnce(50 milliseconds) {
    actor ! StartTask(stops_url)
  }
}
