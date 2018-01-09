import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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

        System.out.println("fuck");


        primaryStage.setTitle("Game is on");
        int size_cell = 200/length;
        System.out.println(size_cell);
        Button button[][] = new Button[length][length];
        StackPane stackPane = new StackPane();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                button[i][j] = new Button("*");
                //button[i][j].localToScene(i * size_cell , j * size_cell);
                //button[i][j].relocate(i * size_cell , j * size_cell);
                button[i][j].setTranslateX(j * size_cell);
                button[i][j].setTranslateY(i * size_cell);
                stackPane.getChildren().add(button[i][j]);
                System.out.println("*");
            }
        }

        HBox h = new HBox(stackPane);
        Scene scene = new Scene(h , 400,200);
        primaryStage.setScene(scene);
        primaryStage.show();

        System.out.println("fuck");
        /*
        for (int i = 0; i < this.length; i++) {
            System.out.print(" | " + (i + 1));
        }
        System.out.println(" |");
        //loops to print the rest of the board
        for(int j = 0; j < this.length; j++) {
            System.out.println("----------------------------------");
            System.out.print((j + 1) + "|");
            for(int l = 0; l < this.length; l++) {
                System.out.print(" " + board[j][l] + " |");
            }
            System.out.print("\n");
        }
        System.out.println("----------------------------------");*/
    }
}
