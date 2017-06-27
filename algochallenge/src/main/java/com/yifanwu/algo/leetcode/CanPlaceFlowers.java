package com.yifanwu.algo.leetcode;

/**
 * Created by WYF on 6/27/2017.
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

 Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

 Example 1:

 Input: flowerbed = [1,0,0,0,1], n = 1
 Output: True

 Example 2:

 Input: flowerbed = [1,0,0,0,1], n = 2
 Output: False

 Note:

 The input array won't violate no-adjacent-flowers rule.
 The input array size is in the range of [1, 20000].
 n is a non-negative integer which won't exceed the input array size.

 */
public class CanPlaceFlowers {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
//        greedy
        int startPos = 0;
        while (startPos < flowerbed.length) {
            if (canPlant(flowerbed, startPos)) {
                flowerbed[startPos] = 1;
                n--;
            }
            if (n <= 0) {
                return true;
            }
            startPos++;
        }
        return false;
    }

//    private boolean canPlaceHelper(int[] flowerbed, int startPos, int n) {
//        if (n == 0) {
//            return true;
//        }
//        if (startPos >= flowerbed.length) {
//            return false;
//        }
//        if (canPlant(flowerbed, startPos)) {
//            return canPlaceHelper(flowerbed, startPos + 1, n - 1);
//        } else {
//            return canPlaceHelper(flowerbed, startPos + 1, n);
//        }
//    }

    private boolean canPlant(int[] flowerbed, int pos) {
        // corner case start of array
        if (flowerbed.length == 1 && pos == 0) {
            return flowerbed[0] != 1;
        }
        if (pos > 0 && pos <flowerbed.length - 1) {
            return flowerbed[pos] != 1 && flowerbed[pos - 1] != 1 && flowerbed[pos + 1] != 1;
        }
        if (pos == 0) {
            return flowerbed[pos] != 1 && flowerbed[pos + 1] != 1;
        }
        if (pos == flowerbed.length - 1) {
            return flowerbed[pos] != 1 && flowerbed[pos - 1] != 1;
        }
        return false;
    }
}
