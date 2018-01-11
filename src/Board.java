import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class Board {
    private int length;
    private char board[][];
    private Stage primaryStage;
    private Scene scene;
    private int x;
    private int y;
    private Color color_x;
    private Color color_o;

    /**
     * Constructor.
     * @param len - the size of the board.
     */
    public Board(int len , Stage p) {
        primaryStage = p;
        this.length = len;
        scene = primaryStage.getScene();
        x = (int) primaryStage.getX() + 5;
        y = (int) primaryStage.getY() + 5;

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
        System.out.println(r + " , " + c + " , " + board[r][c]);
    }

    public Scene scene() {
        return scene;
    }

    public void set_Colors(Color color1 , Color color2) {
        color_x = color1;
        color_o = color2;
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

    public void printBoard(int tor, String f_color_name, String sec_color_name ) {
        //int x = (int) primaryStage.getX() + 5;
        //int y = (int) primaryStage.getY() + 5;
        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();

        //define the screen by the size of board.
        if (length > 8) {
            primaryStage.setHeight(29 * length + 50 );
            primaryStage.setWidth(width/8 * length);
        }

        int he = (int) primaryStage.getHeight() - 40;

        primaryStage.setTitle("Game is on");

        HBox hBox = new HBox();
        for (int i = 0; i < length; i++) {
            VBox vBox = new VBox();
            for (int j = 0; j < length; j++) {
                Rectangle rectangle1 = new Rectangle(x+(he/length)*j,y,he/length,he/length);
                rectangle1.setStroke(Color.BLACK);
                rectangle1.setFill(Color.rgb(220,240,220));

                if (board[i][j] == 'X') {
                    StackPane stackPane = new StackPane();
                    Circle circle = new Circle(rectangle1.getX() , rectangle1.getY() , 5);
                    circle.setFill(color_x);
                    stackPane.getChildren().add(rectangle1);
                    stackPane.getChildren().add(circle);
                    vBox.getChildren().add(stackPane);
                }
                else if (board[i][j] == 'O') {
                    StackPane stackPane = new StackPane();
                    Circle circle = new Circle(rectangle1.getX() , rectangle1.getY() , 5);
                    circle.setFill(color_o);
                    stackPane.getChildren().add(rectangle1);
                    stackPane.getChildren().add(circle);
                    vBox.getChildren().add(stackPane);
                } else { vBox.getChildren().add(rectangle1); }
            }
            y = (he / length) * i;
            hBox.getChildren().add(vBox);
        }
        Label cur_tor;


        if (tor == 0) {
            cur_tor = new Label("Current player: " + f_color_name);
        } else {
            cur_tor = new Label("Current player: " + sec_color_name);
        }
        Label l = new Label(f_color_name + " first player score: " + count_X());
        Label l2 = new Label(sec_color_name +" second player score : " + count_O());

        VBox new_vbox = new VBox(cur_tor , l , l2);
        new_vbox.setSpacing(15);

        HBox hBox1 = new HBox(hBox , new_vbox);
        hBox1.setSpacing(20);

        primaryStage.getScene().setRoot(hBox1);
        //System.out.println("scehne " + scene.getX());
        //System.out.println("scehne " + scene.getY());
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

        System.out.println((cell_x + 1) + " , " + (cell_y));

        return new Cell(cell_x  + 1,cell_y);
    }
}
