package javafx

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.fxml.Initializable
import javafx.event.ActionEvent
import java.net.URL
import java.util.ResourceBundle
import javafx.scene.control.TextField
import javafx.scene.control.Label
import javafx.fxml.FXML
import javafx.stage.Stage
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType

class Hello() : Application() {
    override fun start(stage: Stage) {
        val root: Parent = FXMLLoader.load(javaClass<Hello>().getResource("hello.fxml"))

        val scene: Scene = Scene(root)

        stage.setTitle("Hello Kotlin")
        stage.setScene(scene)
        stage.show()
    }
}

class HelloController() : Initializable {
    FXML var textField: TextField? = null
    FXML var label: Label? = null

    override fun initialize(url: URL?, rb: ResourceBundle?) {
    }

    FXML fun handleButton(event: ActionEvent): Unit {
        var alert = Alert(AlertType.INFORMATION);
        alert.setTitle("Show and Wait");
        alert.getDialogPane().setHeaderText("Header Text");
        alert.getDialogPane().setContentText("Content Text");
        alert.showAndWait();
    }
}

fun main(args: Array<String>) {
    Application.launch(javaClass<Hello>())
}
