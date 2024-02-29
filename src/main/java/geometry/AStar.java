package geometry;

import config.MazeConfig;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * AStar class represents the A* algorithm for finding the shortest path in a maze.
 */
public class AStar { // TODO : mettre des commentaires

    /**
     * Comparator for the PriorityQueue used in the A* algorithm.
     * Compares the heuristics of two nodes.
     * It is used to sort the nodes in the PriorityQueue.
     * The node with the smallest heuristic is at the head of the queue.
     * If two nodes have the same heuristic, they are considered equal.
     */
    static Comparator<Noeud> comparator = new Comparator<Noeud>() {
        @Override
        public int compare(Noeud n1, Noeud n2) {
            if (n1.getHeuristique() == n2.getHeuristique()) {
                return 0;
            } else {
                return n1.getHeuristique() < n2.getHeuristique() ? -1 : 1;
            }
        }
    };

    /**
     * Comparator for the PriorityQueue used in the A* algorithm.
     * Compares the heuristics of two nodes.
     * It is used to sort the nodes in the PriorityQueue.
     * The node with the smallest heuristic is at the head of the queue.
     * If two nodes have the same heuristic, they are considered equal.
     */
    static Comparator<Noeud> comparator2 = new Comparator<Noeud>() {
        @Override
        public int compare(Noeud n1, Noeud n2) {
            if (n1.getHeuristique() == n2.getHeuristique()) {
                return 0;
            } else {
                return n1.getHeuristique() > n2.getHeuristique() ? -1 : 1;
            }
        }
    };

    /**
     * Converts a path represented by a linked list of nodes to an array of coordinates.
     * @param current The current node.
     * @param goal The goal node.
     * @param acc The accumulator.
     * @return The array of coordinates.
     */
    public static ArrayList<IntCoordinates> convertToArray(Noeud current, Noeud goal, ArrayList<IntCoordinates> acc) {
        if ((current.getCoordinates().equals(goal.getCoordinates())) || current.getParent() == null) {
            return acc;
        } else {
            acc.add(current.getCoordinates());
            return convertToArray(current.getParent(), goal, acc);
        }
    }

    /**
     * Checks if a queue contains a node.
     * @param queue The queue.
     * @param node The node.
     * @return True if the queue contains the node, false otherwise.
     */
    public static boolean queueContains(LinkedList<Noeud> queue, Noeud node) {
        for (Noeud n : queue) {
            if (n.getCoordinates().equals(node.getCoordinates())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a queue contains a node and if the node has a smaller heuristic than the one in the queue.
     * @param queue The queue.
     * @param node The node.
     * @return True if the queue contains the node and the node has a smaller heuristic than the one in the queue,
     * false otherwise.
     */
    public static boolean queueContainsAndSmaller(PriorityQueue<Noeud> queue, Noeud node) {
        for (Noeud n : queue) {
            if (n.getCoordinates().equals(node.getCoordinates())) {
                if (n.compareParHeuristique(node) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if a queue contains a node and if the node has a bigger heuristic than the one in the queue.
     * @param queue The queue.
     * @param node The node.
     * @return True if the queue contains the node and the node has a bigger heuristic than the one in the queue,
     * false otherwise.
     */
    public static boolean queueContainsAndBigger(PriorityQueue<Noeud> queue, Noeud node) {
        for (Noeud n : queue) {
            if (n.getCoordinates().equals(node.getCoordinates())) {
                if (n.compareParHeuristique(node) < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Finds the shortest path between two coordinates in a maze.
     * @param startC The start coordinates.
     * @param goalC The goal coordinates.
     * @param config The maze configuration.
     * @return The shortest path between the two coordinates.
     */
    public static ArrayList<IntCoordinates> shortestPath(IntCoordinates startC, IntCoordinates goalC,
            MazeConfig config) {
        Noeud start = new Noeud(startC, null);
        Noeud goal = new Noeud(goalC, null);
        LinkedList<Noeud> closedList = new LinkedList<>();
        PriorityQueue<Noeud> openList = new PriorityQueue<Noeud>(comparator);

        openList.add(start);

        while (!openList.isEmpty()) {
            Noeud u = openList.poll();
            if (u.getCoordinates().equals(goal.getCoordinates())) {
                return convertToArray(u, start, new ArrayList<IntCoordinates>());
            }
            for (Noeud v : u.getVoisins(config)) {
                if (!(queueContains(closedList, v) || queueContainsAndSmaller(openList, v))) {
                    v.setCout(u.getCout() + 1);
                    v.setHeuristique(v.getCout() + v.manhattanDistance(goal));
                    openList.add(v);
                }
            }
            closedList.add(u);

        }

        return null;
    }
    public static boolean isPath(IntCoordinates startC, IntCoordinates goalC, MazeConfig config) {
        ArrayList<IntCoordinates> path = shortestPath(startC, goalC, config);
        if (path == null) {
            return false;
        } else {
            return true;
        }
    }
    public static ArrayList<IntCoordinates> longestPath(IntCoordinates startC, IntCoordinates goalC,
            MazeConfig config) {

        Noeud start = new Noeud(startC, null);
        Noeud goal = new Noeud(goalC, null);
        LinkedList<Noeud> closedList = new LinkedList<>();
        PriorityQueue<Noeud> openList = new PriorityQueue<Noeud>(comparator2);

        openList.add(start);

        while (!openList.isEmpty()) {
            Noeud u = openList.poll();
            if (u.getCoordinates().equals(goal.getCoordinates())) {
                return convertToArray(u, start, new ArrayList<IntCoordinates>());
            }
            for (Noeud v : u.getVoisins(config)) {
                if (!(queueContains(closedList, v) || queueContainsAndBigger(openList, v))) {
                    v.setCout(u.getCout() + 1);
                    v.setHeuristique(v.getCout() + v.manhattanDistance(goal));
                    openList.add(v);
                }
            }
            closedList.add(u);

        }

        return null;

    }
}
