package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 4/8/2021
 */
public class LongestLineOfConsecutiveInMatrix {
    public int longestLine(int[][] M) {
        // defense from invnalid and corner cases
        if (M == null || M.length == 0 || M[0].length == 0) return 0;

        // 4 2-d array: 0-horizontal; 1-vertical; 2-diagonal; 3-anti-dia
        boolean[][][] visited = new boolean[4][M.length][M[0].length]; // use descriptive name in real code

        int longest = 0;
        for (int row = 0; row < M.length; row++) {
            for (int col = 0; col < M[0].length; col++) {
                if (M[row][col] == 1) {
                    for (int dir = 0; dir < 4; dir++) {
                        if (!visited[dir][row][col]) {
                            visited[dir][row][col] = true;
                            longest = Math.max(longest,
                                    dfs(M, visited, 0, dir, row, col));
                        }
                    }
                }
            }
        }
        return longest;
    }

    // dirction:  0-horizontal; 1-vertical; 2-diagonal; 3-anti-dia
    private int dfs(int[][] M, boolean[][][] visited, int length, int dir, int row, int col) {
        int nLen = length + 1;
        int nRow = row;
        int nCol = col;
        // move downward for diagonal and vertical; move right for horizontal
        if (dir == 0) {
            nCol = col + 1;
        } else if (dir == 1) {
            nRow = row + 1;
        } else if (dir == 2) {
            nRow = row + 1;
            nCol = col + 1;
        } else {
            nRow = row + 1;
            nCol = col - 1;
        }
        // check inbound
        if (nRow >= 0 && nRow < M.length && nCol >= 0 && nCol < M[0].length
                && M[nRow][nCol] == 1 && !visited[dir][nRow][nCol]) { // check one and probably don't need check visited
            visited[dir][nRow][nCol] = true;
            nLen = dfs(M, visited, nLen, dir, nRow, nCol);
        }
        return nLen;
    }
}
