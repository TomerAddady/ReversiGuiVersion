import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.sound.sampled.Line;
//import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class Board {
    private int length;
    private char board[][];
    private Stage primaryStage;

    /**
     * Constructor.
     * @param len - the size of the board.
     */
    public Board(int len , Stage p) {
        primaryStage = p;
        this.length = len;
        board = new char[len][len];
        for (int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                board[i][j] = ' ';
            }
        }
        this.board[len / 2 - 1][len / 2 - 1] = 'O';
        this.board[len / 2][len / 2 - 1] = 'X';
        this.board[len / 2 - 1][len / 2] = 'X';
        this.board[len / 2][len / 2] = 'O';
    }

    public Board(int len) {

    }

    public int getSize() {
        return this.length;
    }

    /**
     * Function that update the cell on board.
     * @param r - the row.
     * @param c - the col.
     * @param val - the value we want to set.
     */
    public void insertValue(int r , int c , char val) {
        this.board[r][c] = val;
    }

    /**
     * Get the value in specific cell.
     * @param r - the row.
     * @param c - the col.
     * @return - the value .
     */
    public char getValueAt(int r , int c) {
        return this.board[r][c];
    }

    /**
     * Function that tells us whos won.
     * @return - x if win , o if win , or t in its tie.
     */
    public char getWinner() {
        int xCount = 0 , oCount = 0;
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.length; j++) {
                if(this.board[i][j] == 'X') {
                    xCount++;
                } else if (this.board[i][j] == 'O') {
                    oCount++;
                }
            }
        }
        if (xCount > oCount)
            return 'X';
        else if (oCount > xCount) {
            return 'O';
        } else {
            return 'T'; // tie draw.
        }
    }

    public void printBoard() {
        int x = (int) primaryStage.getX() + 5;
        int y = (int) primaryStage.getY() + 5;

        int width = (int) primaryStage.getWidth();
        int he = (int) primaryStage.getHeight();

        primaryStage.setTitle("Game is on");
        int size_cell = 200/length;
        System.out.println(size_cell);
        Button button[][] = new Button[length][length];
        HBox hBox = new HBox();
        for (int i = 0; i < length; i++) {
            VBox vBox = new VBox();
            for (int j = 0; j < length; j++) {
                Rectangle rectangle1 = new Rectangle(x+(he/length)*j,y,he/length,he/length);
                rectangle1.setStroke(Color.BLACK);
                rectangle1.setFill(Color.rgb(250,200,130));

                if (board[i][j] == 'X') {
                    StackPane stackPane = new StackPane();
                    Circle circle = new Circle(rectangle1.getX() , rectangle1.getY() , 5);
                    circle.setFill(Color.BLACK);
                    stackPane.getChildren().add(rectangle1);
                    stackPane.getChildren().add(circle);
                    vBox.getChildren().add(stackPane);
                }
                else if (board[i][j] == 'O') {
                    StackPane stackPane = new StackPane();
                    Circle circle = new Circle(rectangle1.getX() , rectangle1.getY() , 5);
                    circle.setFill(Color.WHITE);
                    stackPane.getChildren().add(rectangle1);
                    stackPane.getChildren().add(circle);
                    vBox.getChildren().add(stackPane);
                } else { vBox.getChildren().add(rectangle1); }
            }
            y = (he / length) * i;
            hBox.getChildren().add(vBox);
        }
        Label l = new Label("X's: " + count_X());
        Label l2 = new Label("O's: " + count_O());

        VBox new_vbox = new VBox(l , l2);
        new_vbox.setSpacing(30);

        HBox hBox1 = new HBox(hBox , new_vbox);
        hBox1.setSpacing(20);

        Scene scene = new Scene(hBox1 , width,he);
        System.out.println("scehne " + scene.getX());
        System.out.println("scehne " + scene.getY());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public int count_X() {
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length ; j++) {
                if (board[i][j] == 'X') { count++; }
            }
        }

        return count;
    }

    public int count_O() {
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length ; j++) {
                if (board[i][j] == 'O') { count++; }
            }
        }

        return count;
    }

    public Cell been_clicked(int x , int y) {

        Scene scene = primaryStage.getScene();

        double h = scene.getHeight();

        double d= primaryStage.getX();
        double b = scene.getX();
        double f = primaryStage.getY();
        double f5 = scene.getHeight();


        int r_x = (int) (x - primaryStage.getX());
        int r_y = (int) (y - primaryStage.getY());

        int cell_x = (int) (r_x / (scene.getHeight() / length));
        int cell_y = (int) (r_y / (scene.getHeight() / length));

        System.out.println(cell_x);
        System.out.println(cell_y);

        return new Cell(cell_x ,cell_y);
    }
}
