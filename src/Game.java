import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private Player xPlayer;
    private Player oPlayer;
    private Stage primaryStage;
    private String first_color_name;
    private String sec_color_name;
    GameLogic gameLogic;
    Board board;

    /**
     * Constracotr.
     * @param size - size of board.
     * @param p - stage .
     * @param first - starter color .
     * @param sec - second color.
     * @param first_color_name - string first color.
     * @param sec_color_name - string second color.
     */
    public Game(int size , Stage p , Color first , Color sec, String first_color_name, String sec_color_name) {
        this.gameLogic = new RegularLogic(size);
        this.oPlayer = new HumanPlayer('O' , sec);
        this.xPlayer = new HumanPlayer('X' , first);
        primaryStage = p;
        this.first_color_name = first_color_name;
        this.sec_color_name = sec_color_name;
        Board b = new Board(size,primaryStage);
        this.gameLogic.setBoard(b);
        b.set_Colors(xPlayer.getColor() , oPlayer.getColor());
        b.printBoard(0 , first.toString() , sec.toString() , null);
        board = b;
    }

    public void run() {
        //final for using as global.
        final int[] tor = {0};
        //get list of x player becuase he is the starter.
        List<Cell> ls = new ArrayList<Cell>();
        ls.addAll(gameLogic.getOptions(xPlayer));
        board.printBoard(0, this.first_color_name, this.sec_color_name , ls);
        //when click on the screen.
        primaryStage.getScene().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //int tor = 0;
                List<Cell> ls_o = new ArrayList<Cell>();
                ls_o.addAll(gameLogic.getOptions(oPlayer));

                List<Cell> ls_x = new ArrayList<Cell>();
                ls_x.addAll(gameLogic.getOptions(xPlayer));

                int x = MouseInfo.getPointerInfo().getLocation().x;
                int y = MouseInfo.getPointerInfo().getLocation().y;
                //if its the x turn , or the second player doesnt have any moves.
                if (tor[0] == 0 || ls_o.size() == 0) {
                    System.out.println("now its going to press first second");

                    List<Cell> ls = new ArrayList<Cell>();
                    ls.addAll(gameLogic.getOptions(xPlayer));
                    //if there is options to move.
                    if (ls.size() != 0) {
                        board.printBoard(tor[0], first_color_name, sec_color_name, ls);

                        Cell c = xPlayer.chooseMove(board, x, y);
                        int exits = 0;
                        Cell choice = new Cell(c.getRow() - 1, c.getCol() - 1);
                        //tap till we click on something good.
                        for (int i = 0; i < ls.size(); i++) {
                            if (choice.equals(ls.get(i)) == true) {
                                exits = 1;
                                break;
                            }
                        }
                        //while clicking something good.
                        if (exits == 1) {
                            gameLogic.executeChoose(xPlayer, choice);
                            ls.clear();
                            ls.addAll(gameLogic.getOptions(oPlayer));
                            if (ls.size() != 0) {
                                tor[0] = 1;
                            } else {
                                ls.clear();
                                ls.addAll(gameLogic.getOptions(xPlayer));
                            }
                            board.printBoard(tor[0], first_color_name, sec_color_name, ls);
                        }
                    } else {
                        List<Cell> ls2 = new ArrayList<Cell>();
                        ls2.addAll(gameLogic.getOptions(xPlayer));
                        if (ls2.size() == 0) { }
                        tor[0]=1;
                    }
                }
                else if (tor[0] == 1 || ls_x.size() == 0) {
                    List<Cell> ls = new ArrayList<Cell>();
                    ls.addAll(gameLogic.getOptions(oPlayer));
                    if (ls.size() != 0) {
                        board.printBoard(tor[0], first_color_name, sec_color_name, ls);
                        Cell c = oPlayer.chooseMove(board, x, y);
                        int exits = 0;
                        Cell choice = new Cell(c.getRow() - 1, c.getCol() - 1);
                        //wait for good choice.
                        for (int i = 0; i < ls.size(); i++) {
                            if (choice.equals(ls.get(i)) == true) {
                                exits = 1;
                                break;
                            }
                        }
                        //when get good choice.
                        if (exits == 1) {
                            //make the move.
                            gameLogic.executeChoose(oPlayer, choice);
                            ls.clear();
                            ls.addAll(gameLogic.getOptions(xPlayer));
                            if (ls.size() != 0) {
                                tor[0] = 0;
                            } else {
                                ls.clear();
                                ls.addAll(gameLogic.getOptions(oPlayer));
                            }
                            board.printBoard(tor[0], first_color_name, sec_color_name, ls);
                        }
                    } else {
                        //if there is not moves for second player.
                        List<Cell> ls2 = new ArrayList<Cell>();
                        ls2.addAll(gameLogic.getOptions(oPlayer));

                        if (ls2.size() == 0) { }
                        tor[0] = 0;
                    }
                }
                ls_x.clear();
                ls_o.clear();

                ls_x.addAll(gameLogic.getOptions(xPlayer));
                ls_o.addAll(gameLogic.getOptions(oPlayer));
                //if there is not moves for any player - end the game !
                if (ls_o.size() == 0 && ls_x.size() == 0) {
                    String s = board.getWinner();
                    //build the alert .
                    Alert alert = new Alert(Alert.AlertType.NONE
                            ,s , new ButtonType("ok"));

                    alert.showAndWait();
                    primaryStage.close();
                }
            }
        });
    }
}
