
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

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
                                        System.out.println("fff");
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
        VBox hbox = new VBox(quick_start , setting);
        hbox.setSpacing(40);
       
        Scene scene = new Scene(hbox, 400, 250);
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
/*
        System.out.println(primaryStage.getX());
        System.out.println(primaryStage.getY());

        System.out.println(primaryStage.getX() + primaryStage.getWidth());
        System.out.println(primaryStage.getY() + primaryStage.getHeight());
*/
    }

    public void edit_setting(Stage primaryStage) {

        Label l = new Label("Pick colors for two users : ");

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Black", "Blue" , "Green" , "Pink" ,
                        "White", "Clion" ,
                        "Gray" , "Red" ,
                        "Yello"
                );

        ComboBox comboBox = new ComboBox(options);
        ComboBox comboBox1 = new ComboBox(options);

        VBox vBox1 = new VBox(comboBox , comboBox1);
        vBox1.setSpacing(10);
        HBox h = new HBox(l , vBox1);


        primaryStage.setTitle("Edit setting");
        Label firstPlayer = new Label("Whos gonna start? plyer : ");
        CheckBox checkBox = new CheckBox("1");
        CheckBox checkBox2 = new CheckBox("2");
        VBox b = new VBox(checkBox , checkBox2);
        b.setSpacing(8);
        HBox hBox_fist = new HBox(firstPlayer , b);
        hBox_fist.setSpacing(20);
        VBox starter = new VBox(h , hBox_fist);
        starter.setSpacing(30);

        /*Label lbl = new Label("Starter: (black/white)");

        TextField textField = new TextField();
        HBox starter = new HBox(lbl , textField);
        starter.setSpacing(30);*/

        Label lbl2 = new Label("Enter size: (4-20)");
        TextField textField2 = new TextField();
        HBox size = new HBox(lbl2 , textField2);
        size.setSpacing(30);

        Button button = new Button("Sumbit changes");

        VBox vBox = new VBox(starter , size , button);
        vBox.setSpacing(30);

        button.setOnAction(action -> {
            try {
                System.out.println(comboBox.getSelectionModel().getSelectedItem().toString());
                FileWriter fileWriter = new FileWriter("new_setting");
                if (checkBox.isSelected()) {
                    fileWriter.write("start: first\n");
                } else { fileWriter.write("start: second\n"); }
                if (Integer.parseInt(textField2.getText()) >= 4 &&
                        Integer.parseInt(textField2.getText()) <= 20) {
                    fileWriter.write("size: " + textField2.getText() + "\n");
                } else { fileWriter.write("size: 8\n"); }
                if (!comboBox.getSelectionModel().isEmpty()) { fileWriter.write("" +
                        "first: " + comboBox.getSelectionModel().getSelectedItem().toString() + "\n");
                }
                if (!comboBox1.getSelectionModel().isEmpty()) { fileWriter.write("" +
                        "second: " + comboBox1.getSelectionModel().getSelectedItem().toString()+ "\n");
                }
                fileWriter.close();

            } catch (Exception e) { }
            start(primaryStage);
        });

        Scene scene = new Scene(vBox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void start_game(Stage primaryStage) {
        /*
            only if "Edit setting" wad pressed we will read from new_setting o.w we will read from
            defult_setting.
         */
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

            s = br.readLine();
            index = s.length();
            String first_color_name = s.substring(7 , index);

            s = br.readLine();
            index = s.length();
            String sec_color_name = s.substring(8 , index);
            Color color_first;
            Color color_sec;
            if (starter.equals(new String("first"))) {
                color_first = Color.web(first_color_name);
                color_sec = Color.web(sec_color_name);
            } else {
                color_sec = Color.web(first_color_name);
                color_first = Color.web(sec_color_name);
            }
            System.out.println("start game");
            Game game = new Game(len, primaryStage , color_first, color_sec, first_color_name, sec_color_name);

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
