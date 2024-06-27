package core.java.algo.list;

import java.util.*;
import java.util.stream.IntStream;

import core.java.CommonTest;
import org.junit.jupiter.api.Test;

class ArrayTests extends CommonTest {

    @Test
    void testExams() {
        int[] testArray1 = new int[]{2,7,11,15};
        int[] testArray2 = new int[]{9,4,9};
        Arrays.sort(testArray2);
        int[] result = IntStream.range(0, 10).map(x -> 1).toArray();
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);

        List<Integer> testList = new ArrayList<>();
        testList.reversed();

        printArray(stack.reversed().toArray(new Integer[4]));

        // int[] results = rotate(new int[]{1}, 3);
        // int[] results = reverse(new int[]{1,2,3,4,5,6}, 2,4);
        // int[] results = intersect(testArray1, testArray2);
        // int[] results = plusOne(testArray1);
        // int[] result = twoSum(testArray1, 18);
        // boolean result = checkDuplication(testArray1);
        // moveZeroes(testArray1);

        // printArray(result);
        // System.out.println(Arrays.toString(results));
    }

    private int[] rotate(int[] nums, int k) {
        int l = nums.length, count = 0;
        k %= l;
        int[] shriftList = new int[k];
        for (int i=l-1 ; i>=0; i--) {
            if (count < k) {
                shriftList[count] = nums[i];
            }

            if (i > k - 1) {
                nums[i] = nums[i-k];
            } else {
                nums[i] = shriftList[k - i - 1];
            }

            count++;
        }

        System.out.println(Arrays.toString(shriftList));

        return nums;
    }

    private int[] reverse(int[] nums) {
        int l = nums.length;
        for (int i=0;i<l/2;i++) {
            int tmp = nums[l-i-1];
            nums[l-i-1] = nums[i];
            nums[i] = tmp; 
        }

        return nums;
    }

    private int[] reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
        return nums;
    }

    private boolean checkDuplication(int[] nums) {
        Set<Integer> uniqueSet = new HashSet<>();
        int l = nums.length;
        for (int i=0;i<l;i++) {
            if (uniqueSet.contains(nums[i])) {
                return true;
            } else {
                uniqueSet.add(nums[i]);
            }
        }
        return false;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        int[] arrayToMap = nums1.length < nums2.length ? nums2 : nums1;
        int[] arrayToLoop = nums1.length < nums2.length ? nums1 : nums2;

        Map<Integer, Integer> uniqueMap = new HashMap<>();
        for (int i=0;i<arrayToMap.length;i++) {
            Integer key = arrayToMap[i];
            if (!uniqueMap.containsKey(key)) {
                uniqueMap.put(key, 1);
            } else {
                uniqueMap.put(key, uniqueMap.get(key) + 1);
            }
        }

        List<Integer> results = new ArrayList<>(uniqueMap.size()); 
        for (int j=0;j<arrayToLoop.length;j++) {
            int key = arrayToLoop[j];
            if (uniqueMap.containsKey(key)) {
                results.add(key);
                if (uniqueMap.get(key).equals(1)) uniqueMap.remove(key);
                else uniqueMap.put(key, uniqueMap.get(key) - 1);
            }
        }

        int[] intResults = new int[results.size()];
        for (int i=0;i<results.size();i++) {
            intResults[i] = results.get(i);
        }

        return intResults;
    }

    private int[] plusOne(int[] digits) {
        int l = digits.length;
        boolean carry = false;
        for (int i=l-1; i>=0; i--) {
            if (i == l-1) {
                digits[i] += 1;
                if (digits[i] > 9) {
                    carry = true;
                    digits[i] = digits[i] % 10;
                }
            } else if (carry) {
                digits[i] += 1;
                if (digits[i] > 9) {
                    carry = true;
                    digits[i] = digits[i] % 10;
                } else {
                    carry = false;
                }
            }
             
            if (i == 0 && carry) {
                digits = joinArrays(new int[]{1}, digits);
            } 
        }
        return digits;
    }

    public void moveZeroes(int[] nums) {
        int l = nums.length;
        for (int i=0;i<l;i++) {
            for (int j=i+1;j<l;j++) {
                if (nums[i] == 0 && nums[j] != 0) {
                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                }
            }
        }
        printArray(nums);
    }

    public int[] twoSum(int[] nums, int target) {
        int l = nums.length;
        Map<Integer, Integer> checkMap = new HashMap<>();
        for (int i=0;i<l;i++) {
            int part = target - nums[i];
            if (checkMap.containsKey(nums[i])) {
                return new int[]{checkMap.get(nums[i]), i};
            } else {
                checkMap.put(part, i);
            }
        }
        return new int[]{};
    }

    private int[] joinArrays(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int[] result = new int[len1+len2];
        System.arraycopy(arr1, 0, result, 0, len1);
        System.arraycopy(arr2, 0, result, len1, len2);
        return result;
    }

}
