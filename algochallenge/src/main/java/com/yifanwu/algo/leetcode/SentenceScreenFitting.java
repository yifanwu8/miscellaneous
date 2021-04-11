package com.yifanwu.algo.leetcode;

/**
 * @author Yifan.Wu on 4/10/2021
 */
public class SentenceScreenFitting {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        // defense from invalid and corner cases
        // check null or empty sentence
        int ind = 0;
        int ct = 0;
        int[] rowsForInd = new int[cols];
        int[] nextColInd = new int[cols];
        int startingColInd = 0;
        int startingRow = 0;
        for (int row = 0; row < rows; row++) {
            int remCol = cols; //reset remaining col
            while (true) {
                if (remCol >= sentence[ind].length()) { // can hold next word
                    remCol = remCol - sentence[ind].length() - 1; // range (-1 , cols)
                    ind++;
                    if (ind >= sentence.length) {
                        ct++;
                        ind = 0;
                        // remember result
                        rowsForInd[startingColInd] = row - startingRow + 1;
                        nextColInd[startingColInd] = cols - remCol;  // remCol can -1 or 0
                        startingColInd = nextColInd[startingColInd];
                        if (startingColInd >= cols) {
                            startingColInd = 0;
                            row++;
                            if (row >= rows) return ct;
                        }
                        startingRow = row;
                        while (rowsForInd[startingColInd] != 0) { // we remember previous result
                            int rowsToTake = rowsForInd[startingColInd] - 1;
                            if (row + rowsToTake >= rows) return ct;  // cannot finish next sentence
                            // can finish
                            ct++;
                            row += rowsToTake;
                            startingColInd = nextColInd[startingColInd];
                            if (startingColInd >= cols) {
                                startingColInd = 0;
                                row++;
                                if (row >= rows) return ct;
                            }
                            remCol = cols - startingColInd;
                            startingRow = row;
                        }
                    }
                } else {  // current row ends
                    break;
                }
            }
        }
        return ct;
    }
}
