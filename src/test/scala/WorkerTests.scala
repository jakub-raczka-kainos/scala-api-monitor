
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}


class WorkerTests() extends TestKit(ActorSystem("system")) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A Worker actor" must {
    "send back messages TaskSuccess" in {
      val worker = system.actorOf(Props[Worker])
      worker ! FetchDelays("http://87.98.237.99:88/delays?stopId="+"38080")
      expectMsgType[TaskSuccess]
    }
  }
}