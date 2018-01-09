import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       /* primaryStage.setTitle("HBox Experiment 1");

        Label lbl = new Label("Username: ");

        TextField textField = new TextField();

        Button button = new Button("Click to get text");

        button.setOnAction(action -> {
            System.out.println(textField.getText());
        });

        HBox hbox = new HBox(lbl ,textField, button);

        Scene scene = new Scene(hbox, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();*/

        primaryStage.setTitle("HBox Experiment 1");

        Button quick_start = new Button("Quick start");
        Button setting = new Button("Edit Setting");


        quick_start.setOnAction(action -> {

        });

        setting.setOnAction(action -> {

        });

        HBox hbox = new HBox(quick_start , setting);

        Scene scene = new Scene(hbox, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
