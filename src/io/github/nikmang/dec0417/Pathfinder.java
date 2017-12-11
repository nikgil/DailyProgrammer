package io.github.nikmang.dec0417;

import java.util.HashMap;
import java.util.Map;

/**
 * This is not from reddit.
 * I am comparing Dijkstra vs A* in a 2D maze
 */
public class Pathfinder {

    int iterations;
    private int startX,startY, endX, endY;
    private int[][] map = new int[][] { {1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                        {1,1,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,1},
                                        {1,1,0,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1},
                                        {1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,0,0,0,0,1},
                                        {1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

    Pathfinder() {
        this.startX = 0;
        this.startY = 2;

        this.endX = 16;
        this.endY = 2;

        iterations = 0;
    }

    String a_star() {
        Node finalNode = new Node(endX, endY);

        Map<Node, NodeInfo> closed = new HashMap<>();
        MinHeap<Node> heap = new MinHeap<>();

        Node start = new Node(startX, startY);

        heap.add(start, 0);
        closed.put(start, new NodeInfo());
        while(!heap.isEmpty()) {
            Node head = heap.poll();

            if(head.x == endX && head.y == endY) {
                return getPath(head, closed);
            }

            for(int x=-1; x<=1; x++) {
                for(int y=-1; y<=1; y++) {
                    int newX = head.x+x;
                    int newY = head.y+y;

                    if(isValid(newX, newY)) {
                        iterations++;
                        Node frontier = new Node(newX, newY);
                        int dist = closed.get(head).distance + 1 + euclid(frontier, finalNode);
                        NodeInfo info = closed.get(frontier);

                        if(info != null) {
                            if(info.distance > dist) {
                                info.parent = head;
                                info.distance = dist;

                                heap.changePriority(frontier, dist);
                            }
                        } else {
                            info = new NodeInfo();
                            info.parent = head;
                            info.distance = dist;

                            closed.put(frontier,  info);
                            heap.add(frontier, dist);
                        }
                    }
                }
            }
        }

        return "";
    }

    private int euclid(Node start, Node end) {
        int dx = Math.abs(start.x - end.x);
        int dy = Math.abs(start.y - end.y);

        return (int) Math.sqrt(dx*dx + dy*dy); //There will only be small figures for testing
    }

    String dijkstra() {
        Map<Node, NodeInfo> closed = new HashMap<>();
        MinHeap<Node> heap = new MinHeap<>();

        Node start = new Node(startX, startY);

        heap.add(start, 0);
        closed.put(start, new NodeInfo());
        while(!heap.isEmpty()) {
            Node head = heap.poll();

            if(head.x == endX && head.y == endY) {
                return getPath(head, closed);
            }

            for(int x=-1; x<=1; x++) {
                for(int y=-1; y<=1; y++) {
                    int newX = head.x+x;
                    int newY = head.y+y;

                    if(isValid(newX, newY)) {
                        iterations++;
                        Node frontier = new Node(newX, newY);
                        int dist = closed.get(head).distance + 1; // 1 is because all node distance is 1 either way
                        NodeInfo info = closed.get(frontier);

                        if(info != null) {
                            if(info.distance > dist) {
                                info.parent = head;
                                info.distance = dist;

                                heap.changePriority(frontier, dist);
                            }
                        } else {
                            info = new NodeInfo();
                            info.parent = head;
                            info.distance = dist;

                            closed.put(frontier,  info);
                            heap.add(frontier, dist);
                        }
                    }
                }
            }
        }

        return "";
    }

    private String getPath(Node n, Map<Node, NodeInfo> map) {
        StringBuilder sb = new StringBuilder();

        while(n != null) {
            sb.insert(0, n);
            n = map.get(n).parent;
        }

        return sb.toString();
    }

    /*private int getDistance(Node start, Node finish) {
        return Math.abs((finish.x - start.x) + (finish.y - start.y));
    }*/

    private double getStraightDistance(Node start, Node finish) {
        return Math.sqrt(Math.pow(finish.x-start.x,2) + Math.pow(finish.y-start.y,2));
    }

    private boolean isValid(int x, int y) {
        if(y < 0 || y >= map.length)
            return false;

        if(x < 0 || x >= map[y].length)
            return false;

        return map[y][x] != 0;
    }

    private class Node {
        private int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }

        @Override
        public int hashCode() {
            return 37*(x^y);
        }

        @Override
        public boolean equals(Object o) {
            if(!(o instanceof Node))
                return false;

            Node n = (Node) o;

            return n.x == x && n.y == y;
        }
    }

    private class NodeInfo {
        private Node parent;
        private int distance;

        NodeInfo() {
            this.parent = null;
            this.distance = 0;
        }
    }
}
