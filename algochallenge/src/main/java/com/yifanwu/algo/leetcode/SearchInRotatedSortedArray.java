package com.yifanwu.algo.leetcode;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * binary search
 * @author Yifan.Wu on 3/10/2021
 */
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        return rotatedBinarySearch(target, nums, 0, nums.length - 1);
    }

    private int rotatedBinarySearch(int target, int[] nums, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }
        int mid = (hi - lo) / 2 + lo;
        if (target == nums[mid]) {
            return mid;
        }
        if (lo <= mid - 1 && isSubArraySorted(nums, lo, mid - 1)) { // left sub array exist and sorted
            if (target <= nums[mid - 1] && target >= nums[lo]) { // must in left sub sorted array, if any
                return binarySearch(target, nums, lo, mid - 1);
            } else { // might be in right rotated array
                return rotatedBinarySearch(target, nums, mid + 1, hi);
            }
        }
        if (mid + 1 <= hi && isSubArraySorted(nums, mid + 1, hi)) { //right sub array exist and sorted
            if (target >= nums[mid + 1] && target <= nums[hi]) { // must in right sub sorted array, if any
                return binarySearch(target, nums, mid + 1, hi);
            } else { // might be in left rotated array
                return rotatedBinarySearch(target, nums, lo, mid -1);
            }
        }
        return -1;
    }

    // lo & hi inclusive
    private int binarySearch(int target, int[] nums, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }
        int mid = (hi - lo) / 2 + lo;
        if (target == nums[mid]) {
            return mid;
        }
        if (target < nums[mid]) { // left sub array
            return binarySearch(target, nums, lo, mid - 1);
        }
        if (target > nums[mid]) {
            return binarySearch(target, nums, mid + 1, hi);
        }
        return -1;
    }

    private boolean isSubArraySorted(int[] nums, int lo, int hi) {
        return nums[lo] <= nums[hi];
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray o = new SearchInRotatedSortedArray();
        o.search(new int[]{4,5,6,7,0,1,2}, 0);
    }
}
