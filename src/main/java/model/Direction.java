package model;

import geometry.IntCoordinates;

/**
 * The Direction enum represents the possible directions in which an entity can move.
 * Each direction is associated with a vector represented by IntCoordinates.
 */
public enum Direction {
    /**
     * Represents no movement in any direction.
     */
    NONE(new IntCoordinates(0, 0)),

    /**
     * Represents movement towards the north direction.
     */
    NORTH(new IntCoordinates(0, -1)),

    /**
     * Represents movement towards the east direction.
     */
    EAST(new IntCoordinates(1, 0)),

    /**
     * Represents movement towards the south direction.
     */
    SOUTH(new IntCoordinates(0, 1)),

    /**
     * Represents movement towards the west direction.
     */
    WEST(new IntCoordinates(-1, 0));

    private final IntCoordinates vector;

    /**
     * Constructs a Direction enum with the specified vector.
     *
     * @param vector the vector associated with the direction
     */
    Direction(IntCoordinates vector) {
        this.vector = vector;
    }

    /**
     * Returns the vector associated with the direction.
     *
     * @return the vector associated with the direction
     */
    public IntCoordinates getVector() {
        return this.vector;
    }

    /**
     * Converts the direction to its corresponding IntCoordinates vector.
     *
     * @return the IntCoordinates vector representing the direction
     */
    public IntCoordinates toIntCoordinates() {
        return vector;
    }

    /**
     * Returns the Direction enum corresponding to the given IntCoordinates vector.
     *
     * @param vector the IntCoordinates vector to convert to a Direction
     * @return the Direction enum corresponding to the given vector, or null if no matching direction is found
     */
    public static Direction fromIntCoordinates(IntCoordinates vector) {
        for (Direction dir : Direction.values()) {
            if (dir.vector.equals(vector)) {
                return dir;
            }
        }
        return Direction.NONE;
    }
}
      