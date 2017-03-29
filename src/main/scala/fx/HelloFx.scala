package fx

import javafx.application.Application
import javafx.event.{Event, ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import λHandlers._

class HelloFx extends Application {

  override def start(stage: Stage): Unit = {
    stage.setTitle("Mi hermosa ventana")

    val button = new Button("Hazme clic")
    button.setOnAction { e: ActionEvent =>
      println(":)")
    }

    val layout = new StackPane
    layout.getChildren.add(button)

    val scene = new Scene(layout, 300, 250)
    stage.setScene(scene)
    stage.show()
  }

}

object HelloFx {
  def main(args: Array[String]): Unit = Application.launch(classOf[HelloFx])
}

object λHandlers {
  import scala.language.implicitConversions
  implicit def handleEvent[E <: Event](h: E ⇒ Unit): EventHandler[E] = {
    new EventHandler[E] {
      override def handle(event: E) = h(event)
    }
  }
}

