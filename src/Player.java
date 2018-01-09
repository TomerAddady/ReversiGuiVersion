import javafx.scene.Scene;

public class Player {

    public Cell chooseMove(Board board,int x , int y) { return new Cell(0,0); }

    protected char xORo;

    /**
     * Constractor.
     * @param x - the char - the team.
     */
    public Player(char x) {
        this.xORo = x;
    }

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
