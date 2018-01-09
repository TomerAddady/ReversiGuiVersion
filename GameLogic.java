
import java.util.List;

public class GameLogic {

    protected Board b_;

    public GameLogic(int size) {
        this.b_ = new Board(size);
    }

    /**
     * Get the option of the player.
     * @param p - the player.
     * @return list of the cells.
     */
    public List<Cell> getOptions(Player p) { return null; }

    /**
     * Commit the move.
     * @param p - the player.
     * @param c - the cell.
     */
    public void executeChoose(Player p , Cell c) { }

    /**
     * Function that print the board.
     */
    public void printBoard() { b_.printBoard(); }

    /**
     * Function that gives us the winner.
     * @return x or o or t.
     */
    public char getWinner() { return this.b_.getWinner(); }

    public void setBoard(Board b) {
        this.b_ = b;
    }

}
