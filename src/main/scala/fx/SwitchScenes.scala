package fx

import javafx.application.Application
import javafx.event.{Event, ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.control.{Button, Label}
import javafx.scene.layout.{StackPane, VBox}
import javafx.stage.Stage
import λHandlers._

class SwitchScenes extends Application {
  var window: Stage = null
  var scene1: Scene = null
  var scene2: Scene = null

  override def start(stage: Stage): Unit = {
    window = stage

    val label1 = new Label("Primera escena")
    val button1 = new Button("Ir a segunda escena")
    button1.setOnAction { e: ActionEvent =>
      window.setScene(scene2)
    }
    val layout1 = new VBox(20)
    layout1.getChildren.addAll(label1, button1)

    scene1 = new Scene(layout1, 300, 300)

    val button2 = new Button("Ir a primera escena")
    button2.setOnAction { e: ActionEvent =>
      window.setScene(scene1)
    }
    val layout2 = new StackPane
    layout2.getChildren.add(button2)

    scene2 = new Scene(layout2, 400, 400)

    window.setScene(scene1)
    window.setTitle("Making a scene")
    window.show()
  }

}

object SwitchScenes {
  def main(args: Array[String]): Unit = Application.launch(classOf[SwitchScenes])
}


