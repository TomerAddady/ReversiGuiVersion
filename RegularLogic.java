
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RegularLogic extends GameLogic {

    public RegularLogic(int size) {
        super(size);
    }

    public void setBoard(Board board) {
        this.b_ = board;
    }

    public List<Cell> getOptions(Player p) {
        int i , j;
        List<Cell> returnList= new ArrayList<>();

        for (i = 0; i < this.b_.getSize(); i++) {
            for(j = 0; j < this.b_.getSize(); j++) {
                if (this.b_.getValueAt(i, j) != p.getTeam() &&
                        this.b_.getValueAt(i, j) != ' ') {
                    if (isInRange(i, j - 1) && (this.b_.getValueAt(i, j - 1) == ' ')) {// if its empty around him
                        if (checkIfOption(i, j, 0, 1, p.getTeam())
                                && !isExsit(returnList, new Cell(i, j - 1))) {
                            returnList.add(new Cell(i, j - 1));
                        }
                    }
                    if (isInRange(i + 1, j - 1) && this.b_.getValueAt(i + 1, j - 1) == ' ') {
                        if (checkIfOption(i, j, -1, 1, p.getTeam())
                                && !isExsit(returnList, new Cell(i + 1, j - 1))) {
                            returnList.add(new Cell(i + 1, j - 1));
                        }
                    }
                    if (isInRange(i + 1, j) && this.b_.getValueAt(i + 1, j) == ' ') {
                        if (checkIfOption(i, j, -1, 0, p.getTeam())
                                && !isExsit(returnList, new Cell(i + 1, j))) {
                            returnList.add(new Cell(i + 1, j));
                        }
                    }
                    if (isInRange(i + 1, j + 1) && this.b_.getValueAt(i + 1, j + 1) == ' ') {
                        if (checkIfOption(i, j, -1, -1, p.getTeam())
                                && !isExsit(returnList, new Cell(i + 1, j + 1))) {
                            returnList.add(new Cell(i + 1, j + 1));
                        }
                    }
                    if (isInRange(i, j + 1) && this.b_.getValueAt(i, j + 1) == ' ') {
                        if (checkIfOption(i, j, 0, -1, p.getTeam())
                                && !isExsit(returnList,new Cell(i, j + 1))) {
                            returnList.add(new Cell(i, j + 1));
                        }
                    }
                    if (isInRange(i - 1, j + 1) && this.b_.getValueAt(i - 1, j + 1) == ' ') {
                        if (checkIfOption(i, j, 1, -1, p.getTeam())
                                && !isExsit(returnList,new Cell(i - 1, j + 1))) {
                            returnList.add(new Cell(i - 1, j + 1));
                        }
                    }
                    if (isInRange(i - 1, j) && this.b_.getValueAt(i - 1, j) == ' ') {
                        if (checkIfOption(i, j, 1, 0, p.getTeam())
                                && !isExsit(returnList, new Cell(i - 1, j))) {
                            returnList.add(new Cell(i - 1, j));
                        }
                    }
                    if (isInRange(i - 1, j - 1) && this.b_.getValueAt(i - 1, j - 1) == ' ') {
                        if (checkIfOption(i, j, 1, 1, p.getTeam())
                                && !isExsit(returnList,new Cell(i - 1, j - 1))) {
                            returnList.add(new Cell(i - 1, j - 1));
                        }
                    }
                }
            }
        }
        return returnList;
    }

    public void executeChoose(Player p, Cell c) {
        ((this.b_)).insertValue(c.getRow(), c.getCol(), (p).getTeam());
        flipCells((p).getTeam(), c);
    }
    /**
     * checks if a cell is an option for the player.
     * @param i row of cell we check.
     * @param j col of cell we check.
     * @param moveInR where to move in rows.
     * @param moveInC .
     * @param xORo .
     * @return true if it is option.
     */
    private boolean checkIfOption(int i , int j , int moveInR ,int moveInC , char xORo) {
        boolean shouldStop = false;
        while (!shouldStop) {
            i += moveInR;// proceed i,j in the directions.
            j += moveInC;
            if (isInRange(i, j)) { //if we are still in range
                if (this.b_.getValueAt(i, j) == ' ') {
                    return false;
                } else if (this.b_.getValueAt(i, j) == xORo) {// if we found
                    return true;
                } else {//if we dont found yet
                    continue;
                }
            } else { //if not in range
                return false;
            }
        }
        return false;
    }

    /**
     * @param i row.
     * @param j col.
     * @return true if i,j are in board size.
     */
    private boolean isInRange(int i , int j) {
        if (i >= 0 && i < this.b_.getSize()) {
            if (j >= 0 && j < this.b_.getSize()) {
                return true;
            }
        }
        return false;
    }
    /**
     * flip the nedded cells after player move checking all directions.
     * @param xORo player charcter.
     * @param c Cell choise.
     */
    private void flipCells(char xORo , Cell c) {
        flipAllNeeded(xORo, c.getRow(), c.getCol(), 0, -1); //up
        flipAllNeeded(xORo, c.getRow(), c.getCol(), -1, -1); //up left
        flipAllNeeded(xORo, c.getRow(), c.getCol(), -1, 0);// left
        flipAllNeeded(xORo, c.getRow(), c.getCol(), -1, 1);//down left
        flipAllNeeded(xORo, c.getRow(), c.getCol(), 0, 1);//down
        flipAllNeeded(xORo, c.getRow(), c.getCol(), 1, -1);//down right
        flipAllNeeded(xORo, c.getRow(), c.getCol(), 1, 0);//right
        flipAllNeeded(xORo, c.getRow(), c.getCol(), 1, 1);//up right
    }
    /**
     * flip the nedded cells after player move.
     * @param xORo player tav.
     * @param i row of player choise.
     * @param j col of player choise.
     * @param whereX where to move in X (-1 for left)
     * @param whereY where to move in Y (-1 for left)
     * @return true if there is a need to flip.
     */
    private boolean flipAllNeeded(char xORo , int i , int j , int whereX , int whereY) {
        if (!isInRange(i + whereY, j + whereX )){
            return false;
        } else if (this.b_.getValueAt(i + whereY, j + whereX) == ' ') {
            return false;
        } else if (this.b_.getValueAt(i + whereY, j + whereX) == xORo) {
            return true;
        }
        //if still the opposit char
        if (flipAllNeeded(xORo, i + whereY, j + whereX, whereX, whereY)) {
            this.b_.insertValue(i + whereY, j + whereX, xORo);
            return true;
        }
        return false;
    }

    public char getOpposite(char c) {
        if(c == 'X')
            return 'O';
        return 'X';
    }

    public boolean isExsit (List<Cell> ls , Cell c) {
        Cell itr;
        int i = 0;
        if ((ls != null)) {
            if (!ls.isEmpty()) {
                while ((i + 1) != ls.size()) {
                    itr = ls.get(i);
                    if (itr.getRow() == c.getRow() && itr.getCol() == c.getCol()) {
                        return true;
                    }
                    i++;
                }
            }
        }
        return false;
    }
}
