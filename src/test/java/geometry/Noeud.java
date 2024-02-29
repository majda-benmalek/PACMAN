package geometry;

import config.*;
import java.util.ArrayList;

/**
 * Represents a node in a graph.
 */
public class Noeud {
    private final IntCoordinates coordinates;
    private final Noeud parent;
    private int cout;
    private int heuristique;

    /**
     * Creates a new node.
     * @param coordinates The coordinates of the node.
     * @param parent The parent node.
     */
    public Noeud(IntCoordinates coordinates, Noeud parent) {
        this.coordinates = coordinates;
        this.parent = parent;
    }

    /**
     * Gets the neighbors of the node.
     * @param config The maze configuration.
     * @return The neighbors of the node.
     */
    public ArrayList<Noeud> getVoisins(MazeConfig config) {
        ArrayList<Noeud> arr = new ArrayList<>();
        Cell currCell = config.getCell(this.coordinates);
        if (!currCell.getNorthWall()) {
            if (this.coordinates.y() - 1 >= 0) {
                arr.add(new Noeud(new IntCoordinates(this.coordinates.x(), this.coordinates.y() - 1), this));
            }
        }
        if (!currCell.getSouthWall()) {
            if (this.coordinates.y() + 1 < config.getHeight()) {
                arr.add(new Noeud(new IntCoordinates(this.coordinates.x(), this.coordinates.y() + 1), this));
            }
        }
        if (!currCell.getWestWall()) {
            if (this.coordinates.x() - 1 >= 0) {
                arr.add(new Noeud(new IntCoordinates(this.coordinates.x() - 1, this.coordinates.y()), this));
            }
        }
        if (!currCell.getEastWall()) {
            if (this.coordinates.x() + 1 < config.getWidth()) {
                arr.add(new Noeud(new IntCoordinates(this.coordinates.x() + 1, this.coordinates.y()), this));
            }
        }

        return arr;
    }

    /**
     * Gets the coordinates of the node.
     * @return The coordinates of the node.
     */
    public IntCoordinates getCoordinates() {
        return this.coordinates;
    }

    /**
     * Gets the parent node.
     * @return The parent node.
     */
    public Noeud getParent() {
        return parent;
    }

    /**
     * Gets the cost of the node.
     * @return The cost of the node.
     */
    public int getCout() {
        return this.cout;
    }

    /**
     * Sets the cost of the node.
     * @param cout The cost of the node.
     */
    public void setCout(int cout) {
        this.cout = cout;
    }

    /**
     * Gets the heuristic of the node.
     * @return The heuristic of the node.
     */
    public int getHeuristique() {
        return this.heuristique;
    }

    /**
     * Sets the heuristic of the node.
     * @param heuristique The heuristic of the node.
     */
    public void setHeuristique(int heuristique) {
        this.heuristique = heuristique;
    }

    /**
     * Compares two nodes by their cost.
     * @param n The node to compare to.
     * @return 0 if the nodes have the same cost, -1 if the node to compare to has a higher cost, 1 otherwise.
     */
    public int compareParHeuristique(Noeud n) {
        if (n.heuristique == this.heuristique) {
            return 0;
        } else {
            return n.heuristique < this.heuristique ? -1 : 1;
        }
    }

    /**
     * Compares two nodes by their heuristic.
     * @param n The node to compare to.
     * @return 0 if the nodes have the same heuristic, -1 if 
     * the node to compare to has a higher heuristic, 1 otherwise.
     */
    public int manhattanDistance(Noeud n) {
        return Math.abs(this.coordinates.x() - n.coordinates.x()) + Math.abs(this.coordinates.y() - n.coordinates.y());
    }

    /**
     * Changes the ToString method of the node.
     * @return a string representing the node with its coordinates.
     */
    public String toString() {
        return "" + this.coordinates.x() + ", " + this.coordinates.y();
    }

}
