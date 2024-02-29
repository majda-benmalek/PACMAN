package config;

/**
 * Represents a cell in the Pacman game grid.
 */
public class Cell {
    /**
     * The possible contents of a cell.
     */
    public enum Content {
        ENERGIZER, NOTHING, DOT, FRUIT
    }

    private boolean northWall;
    private boolean eastWall;
    private boolean southWall;
    private boolean westWall;
    private boolean ghostNorthWall;
    private boolean ghostSouthWall;
    private Cell.Content initialContent;

    /**
     * Constructs a cell with the specified properties.
     *
     * @param northWall        true if the cell has a wall on the north side, false otherwise
     * @param eastWall         true if the cell has a wall on the east side, false otherwise
     * @param southWall        true if the cell has a wall on the south side, false otherwise
     * @param westWall         true if the cell has a wall on the west side, false otherwise
     * @param ghostNorthWall   true if the cell has a wall on the north side for ghosts, false otherwise
     * @param ghostSouthWall   true if the cell has a wall on the south side for ghosts, false otherwise
     * @param initialContent   the initial content of the cell
     */
    public Cell(boolean northWall, boolean eastWall, boolean southWall, boolean westWall, boolean ghostNorthWall, boolean ghostSouthWall, Cell.Content initialContent) {
        this.northWall = northWall;
        this.eastWall = eastWall;
        this.southWall = southWall;
        this.westWall = westWall;
        this.ghostNorthWall = ghostNorthWall;
        this.ghostSouthWall = ghostSouthWall;
        this.initialContent = initialContent;
    }

    /**
     * Returns the initial content of the cell.
     *
     * @return the initial content of the cell
     */
    public Cell.Content getInitialContent() {
        return initialContent;
    }

    /**
     * Sets the presence of an east wall in the cell.
     *
     * @param eastWall true if the cell has an east wall, false otherwise
     */
    public void setEastWall(boolean eastWall) {
        this.eastWall = eastWall;
    }

    /**
     * Sets the initial content of the cell.
     *
     * @param initialContent the initial content of the cell
     */
    public void setInitialContent(Cell.Content initialContent) {
        this.initialContent = initialContent;
    }

    /**
     * Sets the presence of a north wall in the cell.
     *
     * @param northWall true if the cell has a north wall, false otherwise
     */
    public void setNorthWall(boolean northWall) {
        this.northWall = northWall;
    }

    /**
     * Sets the presence of a south wall in the cell.
     *
     * @param southWall true if the cell has a south wall, false otherwise
     */
    public void setSouthWall(boolean southWall) {
        this.southWall = southWall;
    }

    /**
     * Sets the presence of a west wall in the cell.
     *
     * @param westWall true if the cell has a west wall, false otherwise
     */
    public void setWestWall(boolean westWall) {
        this.westWall = westWall;
    }

    /**
     * Sets the presence of a north wall for ghosts in the cell.
     *
     * @param ghostNorthWall true if the cell has a north wall for ghosts, false otherwise
     */
    public void setGhostNorthWall(boolean ghostNorthWall) { this.ghostNorthWall = ghostNorthWall; }

    /**
     * Sets the presence of a south wall for ghosts in the cell.
     *
     * @param ghostSouthWall true if the cell has a south wall for ghosts, false otherwise
     */
    public void setGhostSouthWall(boolean ghostSouthWall) { this.ghostSouthWall = ghostSouthWall; }

    /**
     * Returns true if the cell has a north wall, false otherwise.
     *
     * @return true if the cell has a north wall, false otherwise
     */
    public boolean getNorthWall() {
        return this.northWall;
    }

    /**
     * Returns true if the cell has a south wall, false otherwise.
     *
     * @return true if the cell has a south wall, false otherwise
     */
    public boolean getSouthWall() {
        return this.southWall;
    }

    /**
     * Returns true if the cell has a west wall, false otherwise.
     *
     * @return true if the cell has a west wall, false otherwise
     */
    public boolean getWestWall() {
        return this.westWall;
    }

    /**
     * Returns true if the cell has an east wall, false otherwise.
     *
     * @return true if the cell has an east wall, false otherwise
     */
    public boolean getEastWall() {
        return this.eastWall;
    }

    /**
     * Returns true if the cell has a north wall for ghosts, false otherwise.
     *
     * @return true if the cell has a north wall for ghosts, false otherwise
     */
    public boolean getGhostNorthWall() { return this.ghostNorthWall; }

    /**
     * Returns true if the cell has a south wall for ghosts, false otherwise.
     *
     * @return true if the cell has a south wall for ghosts, false otherwise
     */
    public boolean getGhostSouthWall() { return this.ghostSouthWall; }

    /**
     * Returns true if the cell has a dot, false otherwise.
     *
     * @return true if the cell has a dot, false otherwise
     */
    public boolean hasDot() {
        return this.initialContent == Cell.Content.DOT;
    }

    /**
     * Returns true if the cell has an energizer, false otherwise.
     *
     * @return true if the cell has an energizer, false otherwise
     */
    public boolean hasEnergizer() {
        return this.initialContent == Cell.Content.ENERGIZER;
    }

    /**
     * Returns true if the cell has a fruit, false otherwise.
     *
     * @return true if the cell has a fruit, false otherwise
     */
    public boolean hasFruit() {
        return this.initialContent == Cell.Content.FRUIT;
    }

}
