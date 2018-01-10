import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class Player {

    public Cell chooseMove(Board board,int x , int y) { return new Cell(0,0); }

    protected char xORo;
    protected  Color color;

    /**
     * Constractor.
     * @param x - the char - the team.
     */
    public Player(char x) {
        this.xORo = x;
    }
    public Player(char x , Color color) {
        this.xORo = x;
        this.color = color;
    }

    public Color getColor() { return this.color;}

    /**
     * Function that we choose move and return the cell that chosen.
     * @return - chosen move.
     */
    public Cell chooseMove() {
        return new Cell(0,0);
    }

    /**
     * Function that tells us the team.
     * @return x or o.
     */
    public char getTeam() {
        return this.xORo;
    }
}
