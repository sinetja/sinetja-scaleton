package skeleton

import sinetja.{Server, Action, Request, Response, Log}
import scalatags.Text.all._

object App {
  def main(args: Array[String]) {
    (new Server)

    .GET("/", new Action {
      def run(req: Request, res: Response) {
        val path = req.server.path(classOf[AnotherAction])
        res.respondHtml(html(
          body(
            p("Hello World"),
            p(a(href := path)("Link to another action"))
          )
        ))
      }
    })

    .GET("/hello/:name", new Action {
      def run(req: Request, res: Response) {
        val name = req.param("name")
        res.respondText(s"Hello $name")
      }
    })


    .GET("/another", classOf[AnotherAction])
    .notFound(classOf[NotFoundAction])
    .start(8000)
  }
}

class AnotherAction extends Action {
  def run(req: Request, res: Response) {
    res.respondHtml(html(
      body(p("Hi World"))
    ))
  }
}

class NotFoundAction extends Action {
  def run(req: Request, res: Response) {
    // Demo about log
    val uri = req.getUri()
    Log.info("User tried to access nonexistant path: {}", uri)

    // Response status has already been set to 404 Not Found by Sinetja
    res.respondText("Not Found: " + uri)
  }
}
