
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class Game {

    private Player xPlayer;
    private Player oPlayer;
    private Stage primaryStage;
    GameLogic gameLogic;

    public Game(int size , char first , Stage p) {
        this.gameLogic = new RegularLogic(8);
        this.oPlayer = new HumanPlayer('O');
        this.xPlayer = new HumanPlayer('X');
        primaryStage = p;

        Board b = new Board(size,primaryStage);

        b.printBoard();
    }

    public void run() {
        boolean flag = true;
        List<Cell> ls;
        ls = this.gameLogic.getOptions(this.xPlayer);
        this.gameLogic.printBoard();
        while(!ls.isEmpty() || !this.gameLogic.getOptions(this.oPlayer).isEmpty()) { // if no more possible moves
            //ls = this->gameLogic_->getOptions(this->xPlayer_)
            printMoves(ls, this.xPlayer.getTeam());
            if (!ls.isEmpty()) {
                Cell choise = this.xPlayer.chooseMove();
                while (!isExsit(ls, choise)) {
                    printMoves(ls, this.xPlayer.getTeam());
                    choise = this.xPlayer.chooseMove();
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
                Cell choise = this.oPlayer.chooseMove();
                while (!isExsit(ls, choise)) {
                    printMoves(ls, this.oPlayer.getTeam());
                    choise = this.oPlayer.chooseMove();
                }
                this.gameLogic.executeChoose(this.oPlayer, choise);
                this.gameLogic.printBoard();
            }
            ls = this.gameLogic.getOptions(this.xPlayer);
        }

        char res = this.gameLogic.getWinner();
        if(res == 'T') {

        } else {

        }
    }

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
