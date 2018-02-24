package load

import javax.inject.Inject

import org.scalatestplus.play._
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import org.webjars.play.WebJarsUtil
import play.api.libs.ws.WSClient
import play.api.mvc.InjectedController

object StopHack {
  var uglyStopHack: Boolean = false
}

class WebjarsBootstrapLoadTestController @Inject()(webJars: WebJarsUtil) extends InjectedController {
  def index() = Action { request =>
    Ok(html.test.render(webJars))
  }
  def stop() = Action { request =>
    StopHack.uglyStopHack = true
    Ok("stopping")
  }
}

class WebjarsBootstrapLoadTest extends PlaySpec with GuiceOneServerPerSuite with OneBrowserPerSuite with HtmlUnitFactory {
  val client = app.injector.instanceOf[WSClient]

  "Calling the test page" must {
    "loadup bootstrap without errors" in {
      val request = s"/"
      val url = s"http://localhost:$port$request"
      go to url
      eventually {
        val pagecontent = for {
            errors <- find("errors")
            status <- find("status")
          } yield (errors.text, status.text)
        pagecontent must be (Some("", "Bootstrap OK!"))
      }
    }
  }

  /* uncomment to be able to see the test page in your browser
  
  "A terrible hack to test things manually" must {
    "show you an url until you request stop" in {
      val request = s"/"
      val url = s"http://localhost:$port$request"
      println(s"The test page: ${url}")
      println(s"Go to this page to continue: http://localhost:$port/stop")
      while (!StopHack.uglyStopHack) {
        Thread.sleep(1000)
      }
    }
  }

  */
}
