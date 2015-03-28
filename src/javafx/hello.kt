package javafx

import javafx.application.Application
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.ButtonBar.ButtonData
import javafx.scene.layout.GridPane
import javafx.stage.Stage
import java.net.URL
import java.util.ResourceBundle

class Hello() : Application() {
    override fun start(stage: Stage) {
        val root: Parent = FXMLLoader.load(javaClass<Hello>().getResource("hello.fxml"))

        val scene: Scene = Scene(root)

        stage.setTitle("Hello Kotlin")
        stage.setScene(scene)
        stage.show()
    }
}

// 参考 http://code.makery.ch/blog/javafx-dialogs-official/
class HelloController() : Initializable {
    FXML var textField: TextField? = null
    FXML var label: Label? = null

    override fun initialize(url: URL?, rb: ResourceBundle?) {
    }

    FXML fun handleNoneButton(event: ActionEvent): Unit {
        var alert = Alert(AlertType.NONE)
        alert.setTitle("Show and Wait")
        alert.getDialogPane().setHeaderText("Header Text")
        alert.getDialogPane().setContentText("Content Text")
        var buttonTypeCancel = ButtonType("閉じる", ButtonData.CANCEL_CLOSE)
        alert.getButtonTypes().setAll(buttonTypeCancel)

        alert.showAndWait()
    }

    FXML fun handleConfirmationButton(event: ActionEvent): Unit {
        var alert = Alert(AlertType.CONFIRMATION)
        alert.setTitle("Show and Wait")
        alert.getDialogPane().setHeaderText("Header Text")
        alert.getDialogPane().setContentText("Content Text")
        alert.showAndWait()
    }

    FXML fun handleInformationButton(event: ActionEvent): Unit {
        var alert = Alert(AlertType.INFORMATION)
        alert.setTitle("Show and Wait")
        alert.getDialogPane().setHeaderText("Header Text")
        alert.getDialogPane().setContentText("Content Text")
        alert.showAndWait()
    }

    FXML fun handleWarningButton(event: ActionEvent): Unit {
        var alert = Alert(AlertType.WARNING)
        alert.setTitle("Show and Wait")
        alert.getDialogPane().setHeaderText("Header Text")
        alert.getDialogPane().setContentText("Content Text")
        alert.showAndWait()
    }

    FXML fun handleErrorButton(event: ActionEvent): Unit {
        var alert = Alert(AlertType.ERROR)
        alert.setTitle("Show and Wait")
        alert.getDialogPane().setHeaderText("Header Text")
        alert.getDialogPane().setContentText("Content Text")
        alert.showAndWait()
    }

    FXML fun handleTextInputButton(event: ActionEvent): Unit {
        var dialog = TextInputDialog("サンプル")
        dialog.setTitle("Text Input Dialog")
        dialog.setHeaderText("Look, a Text Input Dialog")
        dialog.setContentText("Please enter your name:")

        var result = dialog.showAndWait()
    }

    FXML fun handleChoiceButton(event: ActionEvent): Unit {
        var choices = arrayListOf("a", "b", "c")

        var dialog = ChoiceDialog<String>("b", choices)
        dialog.setTitle("Choice Dialog")
        dialog.setHeaderText("Look, a Choice Dialog")
        dialog.setContentText("Choose your letter:")

        var result = dialog.showAndWait()
    }

    FXML fun handleLoginButton(event: ActionEvent): Unit {
        var dialog = Dialog<Login>()
        dialog.setTitle("Login Dialog")
        dialog.setHeaderText("Look, a Custom Login Dialog")


        var loginButtonType = ButtonType("Login", ButtonData.OK_DONE)
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL)

        var grid = GridPane()
        grid.setHgap(10.0)
        grid.setVgap(10.0)
        grid.setPadding(Insets(20.0, 150.0, 10.0, 10.0))

        var username = TextField()
        username.setPromptText("Username")
        var password = PasswordField()
        password.setPromptText("Password")

        grid.add(Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        var loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        username.textProperty().addListener({ observable, oldValue, newValue ->
            loginButton.setDisable(newValue.trim().isEmpty()
            )
        })

        dialog.getDialogPane().setContent(grid)

        Platform.runLater({ -> username.requestFocus() })

        dialog.setResultConverter({ dialogButton ->
            if (dialogButton == loginButtonType) {
                Login(username.getText(), password.getText())
            } else {
                null
            }
        })

        var result = dialog.showAndWait()

        result.ifPresent({ login ->
                       println("Username=" + login.userName + ", Password=" + login.password)
        })
    }
}

fun main(args: Array<String>) {
    Application.launch(javaClass<Hello>(), *args)
}

class Login(var userName: String, var password: String) {
}
