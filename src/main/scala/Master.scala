import akka.actor.Props
import scala.concurrent.duration._


object Master extends App {
  val system = akka.actor.ActorSystem("system")
  val ztm = system.actorOf(Props(classOf[Ztm]))

  import system.dispatcher
  val cancellable = system.scheduler.schedule (
    0 milliseconds,
    100000 milliseconds,
    ztm,
    FetchStops
  )


  system.scheduler.schedule (
    5000 milliseconds,
    20000 milliseconds,
    ztm,
    Delays
  )

}
