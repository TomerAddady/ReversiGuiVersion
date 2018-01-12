import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;
//import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class Board {
    private int length;
    private char board[][];
    private Stage primaryStage;
    private int size_cell;
    private int x;
    private int y;
    private int scene_max;
    private Color color_x;
    private Color color_o;
    private String color_x_string;
    private String color_o_string;
    /**
     * Constructor.
     * @param len - the size of the board.
     * @param p - the stage.
     */
    public Board(int len , Stage p) {
        primaryStage = p;
        this.length = len;
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

    /**
     * Def constructor.
     * @param len - doing nothing.
     */
    public Board(int len) {

    }

    /**
     * Function that get the size of board.
     * @return size of board.
     */
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
     * Function than sets the colors.
     * @param color1 - the starter player color.
     * @param color2 - the second player.
     */
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
    public String getWinner() {
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
            return color_x_string + " WON";
        else if (oCount > xCount) {
            return color_o_string + " WON";
        } else {
            return "Its a tie ! "; // tie draw.
        }
    }

    /**
     * Function that prints the board.
     * @param tor - the turn of whos player.
     * @param f_color_name - the color of first.
     * @param sec_color_name - the color of sec.
     * @param ls - the list of options to move.
     */
    public void printBoard(int tor, String f_color_name, String sec_color_name , List<Cell> ls) {

        color_x_string = f_color_name;
        color_o_string = sec_color_name;

        //define the screen by the size of board.
        if (length >= 8 && primaryStage.isMaximized() == false) {
            primaryStage.setHeight(29 * length + 50 );
            primaryStage.setWidth(primaryStage.getHeight() + 150);
        }
        scene_max = 0;
        if (primaryStage.isMaximized() == true) {
            scene_max = 1;
        }
        int he = (int) primaryStage.getHeight() - 40;
        size_cell = he/length;
        primaryStage.setTitle("Game is on");
        //all of objects will be in the hbox - the board himself.
        HBox hBox = new HBox();
        /*
        now we create the board , by building w/ rectangles .
        when found an optional move , also paint the cell and mark it with 's'
        after that we change all the 's' to ' ' - mean , empty cell.
         */
        for (int i = 0; i < length; i++) {
            VBox vBox = new VBox();
            for (int j = 0; j < length; j++) {
                //first create the rectangle.
                Rectangle rectangle1 = new Rectangle(x+(he/length)*j,y,he/length,he/length);
                rectangle1.setStroke(Color.rgb(225,225,245));
                rectangle1.setFill(Color.rgb(235,235,250));
                //if we got X - first player. O - the nemesis .
                if (board[i][j] == 'X') {
                    //stackpane for circle on rectangle.
                    StackPane stackPane = new StackPane();
                    Circle circle = new Circle(rectangle1.getX() , rectangle1.getY() , 10);
                    circle.setFill(color_x);
                    circle.setStroke(Color.rgb(120, 120, 120));
                    stackPane.getChildren().add(rectangle1);
                    stackPane.getChildren().add(circle);
                    vBox.getChildren().add(stackPane);
                }
                else if (board[i][j] == 'O') {
                    //stackpane for circle on rectangle.
                    StackPane stackPane = new StackPane();
                    Circle circle = new Circle(rectangle1.getX() , rectangle1.getY() , 10);
                    circle.setFill(color_o);
                    circle.setStroke(Color.rgb(120, 120, 120));
                    stackPane.getChildren().add(rectangle1);
                    stackPane.getChildren().add(circle);
                    vBox.getChildren().add(stackPane);
                } else {
                    int flag = 0;
                    /*
                    goint to all the cells and paint the cells whos optinal moves.
                    again we using stack pane and draw circle on rectangle.
                     */
                    if (ls != null) {
                        for (int k = 0; k < ls.size(); k++) {
                            if (ls.get(k).getRow() == i &&
                                    ls.get(k).getCol() == j) {
                                if (board[i][j] == ' ') {
                                    board[i][j] = 's';
                                    Circle c = new Circle(rectangle1.getX(), rectangle1.getY(), 10);
                                    c.setStroke(Color.rgb(200, 200, 200));
                                    c.setFill(Color.rgb(235, 235, 240));
                                    StackPane stackPane = new StackPane();
                                    stackPane.getChildren().add(rectangle1);
                                    stackPane.getChildren().add(c);
                                    vBox.getChildren().add(stackPane);
                                    flag = 1;
                                }
                            }
                        }
                    }
                    if (flag == 0) { vBox.getChildren().add(rectangle1); }
                }
            }
            y = (he / length) * i;
            hBox.getChildren().add(vBox);
        }
        Label cur_tor;

        if (tor == 0) {
            cur_tor = new Label("Current player: " + f_color_name);
        } else { cur_tor = new Label("Current player: " + sec_color_name); }

        Label l = new Label(f_color_name + " score: " + count_X());
        Label l2 = new Label(sec_color_name +" score : " + count_O());

        VBox new_vbox = new VBox(cur_tor , l , l2);
        new_vbox.setSpacing(15);

        HBox hBox1 = new HBox(hBox , new_vbox);
        hBox1.setSpacing(20);

        primaryStage.getScene().setRoot(hBox1);
        primaryStage.show();
        //now all the optinal moves going to change to empty cells .
        for(int i =0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board[i][j] == 's') { board[i][j] = ' ';}
            }
        }

    }

    /**
     * Function that counts the x's .
     * @return number of x's - first player cells .
     */
    public int count_X() {
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length ; j++) {
                if (board[i][j] == 'X') { count++; }
            }
        }

        return count;
    }

    /**
     * Function that counts the o's .
     * @return number of o's - first player cells .
     */
    public int count_O() {
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length ; j++) {
                if (board[i][j] == 'O') { count++; }
            }
        }

        return count;
    }

    /**
     * Function that return which cell is been clicked.
     * @param x - the x value.
     * @param y - the y value.
     * @return Cell which clicked.
     */
    public Cell been_clicked(int x , int y) {

        Scene scene = primaryStage.getScene();

        int i = 0;
        if (length >= 8) {  i = 0; }
        int r_x = (int) (x - primaryStage.getX());
        int r_y = (int) (y - primaryStage.getY() + i);

        int cell_x = (int) (r_x / size_cell);
        int cell_y = (int) (r_y / size_cell);
        int r = scene_max;
        System.out.println((cell_x +1)+ " , " + (cell_y + r));
        return new Cell(cell_x +1,cell_y + r);
    }
}