
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.awt.*;

public class HumanPlayer extends Player {

    protected char xORo;
    protected Color color;

    /**
     * Constractor.
     * @param x - the char - the team.
     */
    public HumanPlayer(char x) {
        super(x);
        this.xORo = x;
    }
    public HumanPlayer(char x , Color color) {
        super(x , color);
        this.xORo = x;
        this.color = color;
    }
    /**
     * Choose the move by lables.
     * @return the cell that chosen.
     */
    public Cell chooseMove(Board board,int x , int y) {
        /*
        need to be change , by chosen of label.
         */

        return board.been_clicked(x,y);
    }

    public Color getColor() { return this.color;}

    /**
     * Get the team of the player.
     * @return x or o.
     */
    public char getTeam() {
        return xORo;
    }
}
