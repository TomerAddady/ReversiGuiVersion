
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import static javafx.application.Application.launch;

public class main extends Application{

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("HBox Experiment 1");

        Button quick_start = new Button("Quick start");
        Button setting = new Button("Edit Setting");

        quick_start.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        start_game(primaryStage);
                                    }
        });

                setting.setOnAction(action -> {
            //System.out.println("quick start " + action.getSource());
            //System.out.println("quick start " + action.getTarget());
                    System.out.println("quick start " + MouseInfo.getPointerInfo().getLocation().x);
                    System.out.println("quick start " + MouseInfo.getPointerInfo().getLocation().y);

            edit_setting(primaryStage);
        });
        HBox hbox = new HBox(quick_start , setting);
        MouseEvent e;
        Scene scene = new Scene(hbox, 800, 400);
        //this is how we recognize click on the screen !!!!!!!!!!!!!!!!!!!
        /*scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("quick start " + MouseInfo.getPointerInfo().getLocation().x);
                System.out.println("quick start " + MouseInfo.getPointerInfo().getLocation().y);
            }
        });*/
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void edit_setting(Stage primaryStage) {
        primaryStage.setTitle("Edit setting");

        Label lbl = new Label("Starter: (black/white)");
        lbl.setMinSize(149,30);
        lbl.setMaxSize(150,30);
        TextField textField = new TextField();
        textField.setMinSize(149 , 30);
        textField.setMaxSize(151 , 30);
        Label lbl2 = new Label("Enter size: (4-20)");
        lbl2.setMinSize(150,30);
        TextField textField2 = new TextField();

        Button button = new Button("P");
        button.setMinSize(50,50);
        button.setMaxSize(70,70);
        button.setOnAction(action -> {
            System.out.println(textField.getText());
            try {
                FileWriter fileWriter = new FileWriter("new_setting");
                if (textField.getText() != "") {
                    fileWriter.write("start: " + textField.getText() + "\n");
                } else { fileWriter.write("start: black\n"); }
                if (textField2.getText() != "") {
                    fileWriter.write("size: " + textField2.getText());
                } else { fileWriter.write("sie: 8");}

                fileWriter.close();

            } catch (Exception e) { }
            start(primaryStage);
        });

        HBox hbox1 = new HBox(lbl ,textField);
        hbox1.setMinSize(300,30);

        HBox hbox2 = new HBox(lbl2, textField2 , button);
        hbox2.setTranslateY(50);
        hbox2.setTranslateX(-300);
        hbox2.setMinSize(300,30);
        HBox hbox3 = new HBox( button);
        hbox3.setTranslateY(100);
        hbox3.setTranslateX(-600);
        hbox3.setMinSize(50,50);
        hbox3.setMaxSize(70,70);
        HBox hbox = new HBox(hbox1 , hbox2 , hbox3);
        hbox.setSpacing(0);

        Scene scene = new Scene(hbox, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void start_game(Stage primaryStage) {

        FileReader file;
        try {
            file = new FileReader("new_setting");

            BufferedReader br = new BufferedReader(file);
            String s = br.readLine();

            int index = s.length();

            String starter = s.substring(7 , index);

            s = br.readLine();
            index = s.length();
            String size = s.substring(6 , index);
            int len = Integer.parseInt(size);
            System.out.println(starter);
            System.out.println(size);
            char first;
            if (starter == "black") { first = 'X'; }
            else { first = 'O'; }

         //   Game game = new Game(len , first , primaryStage);

            file.close();
            FileReader file2 = new FileReader("defult_setting");
            FileWriter file3 = new FileWriter("new_setting");

            BufferedReader br2 = new BufferedReader(file2);

            while (br2 != null) {
                String s1 = br2.readLine();
                if (s1 == null) { break; }
                file3.write(s1);
                file3.write("\n");
            }

            file2.close();
            file3.close();

        } catch (Exception e) { }

    }
}
