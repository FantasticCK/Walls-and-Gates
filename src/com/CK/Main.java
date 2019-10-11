package com.CK;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[][] rooms = {{2147483647, -1, 0, 2147483647}, {2147483647, 2147483647, 2147483647, -1}, {2147483647, -1, 2147483647, -1}, {0, -1, 2147483647, 2147483647}};
        new Solution().wallsAndGates(rooms);
    }
}

// bfs
class Solution {
    private static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0)
            return;
        int r = rooms.length, c = rooms[0].length;
        int dis = 0;
        Queue<int[]> list = new LinkedList<>();
        boolean[][] visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (rooms[i][j] == 0) {
                    list.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!list.isEmpty()) {
            int size = list.size();
            dis++;
            for (int s = 0; s < size; s++) {
                int[] curr = list.poll();
                for (int i = 0; i < 4; i++) {
                    int nextR = curr[0] + dir[i][0];
                    int nextC = curr[1] + dir[i][1];
                    if (isValid(rooms, nextR, nextC, visited)) {
                        visited[nextR][nextC] = true;
                        rooms[nextR][nextC] = Math.min(rooms[nextR][nextC], dis);
                        list.offer(new int[]{nextR, nextC});
                    }
                }
            }
        }
    }

    private boolean isValid(int[][] rooms, int nextR, int nextC, boolean[][] visited) {
        int r = rooms.length, c = rooms[0].length;
        return nextR >= 0 && nextR < r && nextC >= 0 && nextC < c && rooms[nextR][nextC] > 0 && !visited[nextR][nextC];
    }
}


//Dfs
class Solution {
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        int row = rooms.length, col = rooms[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    update(rooms, i, j, 0);
                }
            }
        }
    }

    public void update(int[][] rooms, int i, int j, int d) {
        int row = rooms.length, col = rooms[0].length;
        if (i < 0 || i >= row || j < 0 || j >= col || rooms[i][j] < d) return;

        rooms[i][j] = d;
        for (int[] dir: dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            update(rooms, x, y, d + 1);
        }
    }
}