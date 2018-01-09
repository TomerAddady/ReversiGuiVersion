
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private Player xPlayer;
    private Player oPlayer;
    private Stage primaryStage;
    GameLogic gameLogic;
    Board board;

    public Game(int size , char first , Stage p) {
        this.gameLogic = new RegularLogic(size);
        this.oPlayer = new HumanPlayer('O');
        this.xPlayer = new HumanPlayer('X');
        primaryStage = p;

        Board b = new Board(size,primaryStage);
        this.gameLogic.setBoard(b);

        b.printBoard();
        board = b;
        run();
    }

    public void run() {
        /*boolean flag = true;
        List<Cell> ls;
        ls = this.gameLogic.getOptions(this.xPlayer);
        this.gameLogic.printBoard();
        while(!ls.isEmpty() || !this.gameLogic.getOptions(this.oPlayer).isEmpty()) { // if no more possible moves
            //ls = this->gameLogic_->getOptions(this->xPlayer_)
            printMoves(ls, this.xPlayer.getTeam());
            if (!ls.isEmpty()) {
                Cell choise = this.xPlayer.chooseMove(board , primaryStage.getScene());
                while (!isExsit(ls, choise)) {
                    printMoves(ls, this.xPlayer.getTeam());
                    choise = this.xPlayer.chooseMove(board , primaryStage.getScene());
                }
                this.gameLogic.executeChoose(this.xPlayer, choise);
                this.gameLogic.printBoard();
            } else {
                flag = false;
            }

            ls = this.gameLogic.getOptions(this.oPlayer);
            if (ls.isEmpty() && !flag) { // if both lists are empty
                break;
            }
            flag = true;
            printMoves(ls, this.oPlayer.getTeam());
            if(!ls.isEmpty()) {
                Cell choise = this.oPlayer.chooseMove(board , primaryStage.getScene());
                while (!isExsit(ls, choise)) {
                    printMoves(ls, this.oPlayer.getTeam());
                    choise = this.oPlayer.chooseMove(board , primaryStage.getScene());
                }
                this.gameLogic.executeChoose(this.oPlayer, choise);
                this.gameLogic.printBoard();
            }
            ls = this.gameLogic.getOptions(this.xPlayer);
        }*/
        final int[] tor = {0};
        //while (true) {
        board.printBoard();
        primaryStage.getScene().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //int tor = 0;
                int x = MouseInfo.getPointerInfo().getLocation().x;
                int y = MouseInfo.getPointerInfo().getLocation().y;
                System.out.println("mouse click! ");
                if (tor[0] == 0) {
                    List<Cell> ls = new ArrayList<Cell>();
                    ls.addAll(gameLogic.getOptions(xPlayer));
                    Cell c = xPlayer.chooseMove(board, x, y);
                    int exits = 0;
                    Cell choice = new Cell(c.getRow() - 1 , c.getCol() - 1);
                    for (int i = 0; i < ls.size(); i++) {
                        if (choice.equals(ls.get(i)) == true) { exits = 1; break; }
                    }
                    if (exits == 1) {
                        gameLogic.executeChoose(xPlayer, choice);
                        board.printBoard();
                        System.out.println("ffffff");
                        tor[0]++;
                    }
                }
                else if (tor[0] == 1) {
                    List<Cell> ls = new ArrayList<Cell>();
                    ls.addAll(gameLogic.getOptions(oPlayer));
                    Cell c = oPlayer.chooseMove(board, x, y);
                    int exits = 0;
                    Cell choice = new Cell(c.getRow() - 1 , c.getCol() - 1);
                    for (int i = 0; i < ls.size(); i++) {
                        if (choice.equals(ls.get(i)) == true) { exits = 1; break; }
                    }
                    if (exits == 1) {
                        gameLogic.executeChoose(oPlayer, choice);
                        board.printBoard();
                        System.out.println("ffffff");
                        tor[0] = 0;
                    }
                }
            }
        });
    }

        /*char res = this.gameLogic.getWinner();
        if(res == 'T') {

        } else {

        }*/
    //}
    public boolean isExsit (List<Cell> ls , Cell c) {
        Cell itr;
        int i = 0;

        if (!ls.isEmpty()) {
            while ((i+1) != ls.size()) {
                itr = ls.get(i);
                if (itr.getRow() == c.getRow() && itr.getCol() == c.getCol()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printMoves(List<Cell> l, char xORo) {
        System.out.println(": Its your move ");
        System.out.println("your possible moves:");
        if (l.isEmpty()) {
            System.out.println("No possible moves. Play passes back to the other player. Press any key to continue");
        }
        System.out.print("\n");
    }
}
