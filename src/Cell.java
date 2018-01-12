import javafx.scene.Scene;

public class Cell {

    private int row;
    private int col;

    /**
     * Constractor.
     * @param row_ - the row of the cell.
     * @param col_ - the columm of the cell.
     */
    public Cell(int row_ , int col_) {
        this.row = row_;
        this.col = col_;
    }

    /**
     * get function - get the column.
     * @return the column of the cell.
     */
    public int getCol() {
        return this.col;
    }

    /**
     * get function - get the row.
     * @return the row of the cell.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Print the current cell.
     */
    public void printCell() {
        System.out.print("(" + (row + 1) + "," + (col + 1) + ")");
    }

    public boolean equals(Cell cell) {

        if (row == cell.getRow() && col == cell.getCol()) { return true; }
        return false;
    }
}
