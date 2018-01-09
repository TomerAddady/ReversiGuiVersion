
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;

import java.awt.*;

public class HumanPlayer extends Player {

    protected char xORo;

    /**
     * Constractor.
     * @param x - the char - the team.
     */
    public HumanPlayer(char x) {
        super(x);
        this.xORo = x;
    }

    /**
     * Choose the move by lables.
     * @return the cell that chosen.
     */
    public Cell chooseMove(Board board,Scene scene) {
        /*
        need to be change , by chosen of label.
         */
        final Cell[] c = {new Cell(0, 0)};
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = MouseInfo.getPointerInfo().getLocation().x;
                int y = MouseInfo.getPointerInfo().getLocation().y;
                c[0] = board.been_clicked(x,y);
            }
        });

        return c[0];
    }

    /**
     * Get the team of the player.
     * @return x or o.
     */
    public char getTeam() {
        return xORo;
    }
}
