package skeleton

import sinetja.{Server, Action, Request, Response, Log}
import scala.xml.Xhtml

object App {
  def main(args: Array[String]) {
    (new Server)

    .GET("/", new Action {
      def run(req: Request, res: Response) {
        val href = req.server.path(classOf[AnotherAction])
        res.respondHtml(Xhtml.toXhtml(
          <body>
            <p>Hello World</p>
            <p><a href={href}>Link to another action</a></p>
          </body>
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
    res.respondHtml(Xhtml.toXhtml(
      <body>
        <p>Hi World</p>
      </body>
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
