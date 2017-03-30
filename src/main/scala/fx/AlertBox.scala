package fx

import javafx.application.Application
import javafx.event._
import javafx.scene._
import javafx.scene.control._
import javafx.scene.layout._
import javafx.stage._
import javafx.geometry._
import λHandlers._

class AlertBox extends Application {
  var window: Stage = null
  override def start(stage: Stage) = {
    window = stage
    window.setTitle("Holi")

    val button = new Button("Clicadme")
    button.setOnAction { e: ActionEvent ⇒ AlertBox.display("Ojo", "Caja de alerta") }

    val layout = new StackPane
    layout.getChildren.add(button)

    val scene = new Scene(layout, 300, 250)

    window.setScene(scene)
    window.show()
  }
}


object AlertBox {
  def main(args: Array[String]): Unit = Application.launch(classOf[AlertBox])

  def display(title: String, message: String) = {

    val window = new Stage
    window.initModality(Modality.APPLICATION_MODAL)
    window.setTitle(title)
    window.setMinWidth(250)

    val label = new Label
    label.setText(message)

    val closeButton = new Button("Close")
    closeButton.setOnAction { e: ActionEvent ⇒ window.close() }

    val layout = new VBox(10)
    layout.getChildren.addAll(label, closeButton)
    layout.setAlignment(Pos.CENTER)

    val scene = new Scene(layout)
    window.setScene(scene)
    window.showAndWait()
  }

}


