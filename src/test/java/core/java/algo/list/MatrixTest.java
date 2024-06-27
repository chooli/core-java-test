package core.java.algo.list;

import core.java.CommonTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MatrixTest extends CommonTest {

    @BeforeAll
    public void setup() {
        System.out.println("start learning tests ... ");
    }

    @Test
    void test() {

        int[] intArr1 = {-1,3,0,0,0,0,0};
        int[] intArr2 = {0,0,1,2,3};
        //int profile = maxProfit(intArr);
        char[][] sudoku = {
                {'1', '3', '2', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        merge(intArr1, 2, intArr2, 5);
//        System.out.println(intArr1);
        printArray(intArr1);
        printArray(intArr2);

        assertTrue(true);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i=0;i<nums1.length;i++) {
            if (i >= m && i - m < n) {
                nums1[i] = nums2[i-m];
            } else {
                for (int j=0;j<n;j++) {
                    if (j == 0) {
                        if (nums1[i] > nums2[0]) {
                            int tmp = nums2[0];
                            nums2[0] = nums1[i];
                            nums1[i] = tmp;
                        }
                    } else if (nums2[j - 1] > nums2[j]) {
                        int tmp = nums2[j - 1];
                        nums2[j - 1] = nums2[j];
                        nums2[j] = tmp;
                    }
                }
            }
        }
    }

    public boolean isValidSudoku(char[][] board) {
        if(!validateRowCol(board))
            return false;

        for(int x=0;x<3;x++) {
            for(int y=0;y<3;y++) {
                if(!validateBox(board, x*3, y*3))
                    return false;
            }
        }

        return true;
    }

    private boolean validateBox(char[][] board, int x, int y) {
        Set<Integer> boxSet = new HashSet<>();
        for(int i=x;i<x+3;i++){
            for(int j=y;j<y+3;j++) {
                char value = board[i][j];
                if(!validateCell(value, boxSet))
                    return false;
            }
        }
        return true;
    }

    private boolean validateRowCol(char[][] board) {
        for (int i=0;i<9;i++) {
            Set<Integer> rowSet = new HashSet<>();
            Set<Integer> colSet = new HashSet<>();
            for (int j=0;j<9;j++) {
                char rowValue = board[i][j];
                if(!validateCell(rowValue, rowSet))
                    return false;
                char colValue = board[j][i];
                if(!validateCell(colValue, colSet))
                    return false;
            }
            rowSet.clear();
            colSet.clear();
        }
        return true;
    }

    private boolean validateCell(char value, Set<Integer> digitSet) {
        if (value!='.') {
            int digit = Integer.parseInt(String.valueOf(value));
            if (digit>0 && digit<10 && !digitSet.contains(digit)) {
                digitSet.add(digit);
            } else {
                return false;
            }
        }
        return true;
    }

    public void moveZeroes(int[] nums) {
        int count=0;
        for(int i=0;i<nums.length;i++) {
            if(nums[i] == 0 && count<nums.length){
                shiftOneToEnd(nums, i);
                count++;
                i--;
            }
        }
    }

    private void shiftOneToEnd(int[] nums, int idx) {
        for(int i=idx;i<nums.length;i++){
            if(i==nums.length-1)
                nums[i] = 0;
            else
                nums[i] = nums[i+1];
        }
    }

    public int[] plusOne(int[] digits) {
        int i = digits.length, intResult = 10;
        while(i >= 0 && intResult > 9) {
            i--;
            if(i<0){
                int[] newResult = new int[digits.length + 1];
                newResult[0] = 1;
                for(int j=1;j<digits.length+1;j++) {
                    newResult[j] = digits[j-1];
                }
                return newResult;
            }
            intResult = digits[i] + 1;
            digits[i] = intResult % 10;
        }
        return digits;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergeArrays = mergeArrays(nums1, nums2);

        printArray(mergeArrays);

        if(mergeArrays.length % 2 == 1) {
            return mergeArrays[mergeArrays.length / 2];
        } else {
            return (double)(mergeArrays[mergeArrays.length / 2] + mergeArrays[mergeArrays.length / 2 - 1]) / 2;
        }
    }

    private int[] mergeArrays(int[] nums1, int[] nums2){
        int[] mergeedArr = new int[nums1.length + nums2.length];
        int j=0, k=0;
        for(int i=0;i<nums1.length;i++) {
            if(j == nums2.length || nums1[i] <= nums2[j]) {
                mergeedArr[k] = nums1[i];
            }else{
                if(j != nums2.length) {
                    mergeedArr[k] = nums2[j];
                    i--;
                    j++;
                }
            }
            k++;
        }

        for(;j<nums2.length;j++){
            mergeedArr[k] = nums2[j];
            k++;
        }

        return mergeedArr;
    }

    public boolean containsDuplicate(int[] nums) {
        if(nums.length<2)
            return false;
        Set<Integer> intSets = new HashSet<>();
        int len = nums.length >> 1;
        for(int i=0;i<len;i++){
            int n1 = nums[i], n2 = nums[nums.length - i - 1];
            if(n1 == n2)
                return true;
            if(intSets.contains(n1))
                return true;
            intSets.add(n1);
            if(intSets.contains(n2))
                return true;
            intSets.add(n2);
        }
        if(nums.length % 2 == 1) {
            intSets.contains(nums[len]);
        }
        return false;
    }

    private int maxProfit(int[] prices) {
        int buy = -1, sell = 0, result = 0;
        for(int i=0;i<prices.length;i++) {
            if(buy == -1 && i<prices.length-1 && prices[i] < prices[i+1]) {
                buy = prices[i];
                continue;
            }

            if(buy > -1 && i==prices.length-1)
                sell = prices[i];
            else if(buy > -1 && prices[i] > prices[i+1])
                sell = prices[i];

            if(sell > 0){
                result += sell - buy;
                buy = -1;
                sell = 0;
            }

        }
        return result;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            int opp = target - nums[i];
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            } else if (!map.containsKey(opp)) {
                map.put(opp, i);
            }
        }
        return new int[]{};
    }

    private boolean checkSubarraySum(int[] nums){
        Set<Integer> set = new HashSet<>();
        set.add(0);
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
            if (set.contains(sum)) {
                return true;
            }
            set.add(sum);
        }
        return false;
    }

    private void findPairNums(int[] nums, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(sum - nums[i], i);
            if(map.containsKey(nums[i])) {
                System.out.println("found pair " + i + ", " + map.get(nums[i]));
            }
        }
    }

    public void rotateArray(int k, int[] nums) {
        int temp;
        for(int i=1;i<=k;i++){
            temp = nums[nums.length-1];
            swiftArrayToRight(nums); //shit array to one element on the left
            nums[0] = temp;
        }


    }

    private int partitionArray(int[] nums, int k) {
        int temp;
        for(int i=0;i<nums.length-1;i++) {
            if(nums[i] > nums[i+1]) {
                temp = nums[i];
                nums[i] = nums[i+1];
                nums[i+1] = temp;
            }
        }
        printArray(nums);
        return nums.length;
    }

    public void reverseString(char[] s) {
        int halfLen = s.length / 2 + s.length % 1;
        char temp;
        for(int i=0;i<halfLen;i++) {
            temp = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = temp;
        }
    }

    private int removeDuplicates(int[] nums) {
        if(nums.length==0)
            return 0;
        int i = 0;
        for(int j=1;j<nums.length;j++){
            if(nums[j] != nums[i]){
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    private void swiftArrayToLeft(int[] nums){
        for(int i=0;i<nums.length-1;i++){
            nums[i] = nums[i+1];
        }
    }

    private void swiftArrayToLeft(int[] nums, int k, boolean checkDuplicated){
        int last = nums[nums.length-1];
        for(int i=k;i<nums.length-1;i++) {
            nums[i] = nums[i+1];
        }
        if(checkDuplicated && nums[k] == nums[k+1] && nums[k]!=last) {
            swiftArrayToLeft(nums, k, true);
        }
    }

    private void swiftArrayToLeft(int[] nums, int start){
        for(int i=start;i<nums.length-1;i++){
            nums[i] = nums[i+1];
        }
    }

    private void swiftArrayToRight(int[] nums){
        for(int i=nums.length-1;i>0;i--){
            nums[i] = nums[i-1];
        }
    }

    private void removeElFromArray(int[] my_array){

        System.out.println("Original Array : "+Arrays.toString(my_array));

        // Remove the second element (index->1, value->14) of the array
        int removeIndex = 1;

        for(int i = removeIndex; i < my_array.length -1; i++){
            my_array[i] = my_array[i + 1];
        }
// We cannot alter the size of an array , after the removal, the last and second last element in the array will exist twice
        System.out.println("After removing the second element: "+ Arrays.toString(my_array));
    }

}