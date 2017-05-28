package skeleton

import io.netty.handler.codec.http.HttpResponseStatus
import sinetja.{Action, Log, Request, Response, Server}

import scalatags.Text.all._

class NotFoundAction extends Action {
  def run(req: Request, res: Response) {
    val uri = req.uri()
    Log.info("User tried to access nonexistant path: {}", uri)

    res.setStatus(HttpResponseStatus.NOT_FOUND)
    res.respondText("Not Found: " + uri)
  }
}

class AnotherAction extends Action {
  def run(req: Request, res: Response) {
    res.respondHtml(html(
      body(p("Hi World"))
    ))
  }
}

object App {
  def main(args: Array[String]) {
    // Create action from a class
    val anotherAction = new AnotherAction

    val server = new Server(8000)

    server
      // Create and mount action inline
      .GET("/", (req, res) => {
        // Reverse routing
        val uri = server.uri(anotherAction)

        res.respondHtml(html(
          body(
            p("Hello World"),
            p(a(href := uri)("Link to another action"))
          )
        ))
      })

      // Another inline action
      .GET("/hello/:name", (req, res) => {
        val name = req.param("name")
        res.respondText(s"Hello $name")
      })

      // Mount the precreated action
      .GET("/another", anotherAction)

      .notFound(new NotFoundAction)

    server.start()
    server.stopAtShutdown()
  }
}
