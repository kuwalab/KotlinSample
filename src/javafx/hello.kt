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

// http://pekokun.hatenablog.jp/entry/2013/12/08/190540
class Hello() : javafx.application.Application() {

    override fun start(primaryStage: javafx.stage.Stage?) {
        val root: Parent = FXMLLoader.load(javaClass<Hello>().getResource("hello.fxml"))

        val scene: Scene = Scene(root)

        primaryStage!!
        primaryStage.setTitle("Hello KotlinFX")
        primaryStage.setWidth(300.0)
        primaryStage.setHeight(250.0)
        primaryStage.setScene(scene)
        primaryStage.show()
    }
}

class HelloController() : Initializable {

    FXML var textField: TextField? = null
    FXML var label: Label? = null

    override fun initialize(url: URL?, rb: ResourceBundle?) {
    }

    FXML fun handle(event: ActionEvent): Unit {
        label?.setText("Hello, " + textField?.getText() + "!");
    }

}

fun main(args: Array<String>) {
    Application.launch(javaClass<Hello>(), args.makeString(" "))
}
