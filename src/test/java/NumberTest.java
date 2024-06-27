

import core.java.CommonTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NumberTest extends CommonTest {

    @BeforeAll
    public void setup() {
        System.out.println("start learning tests ... ");
    }

    @Test
    void testExams() {

        int a = 12, b = 1111112111;
        int[] intArr = {4,9,5,7,3,1,4}, intArr2 = {9,4,9,8};
        Set<Integer> set = new HashSet<>();

        //testBitManipulation(a, b);
//        printArray(intersect(intArr, intArr2));
        //System.out.println(countPrimes(643466));
        int[] newArr = Arrays.copyOfRange(intArr, 0 , 2);

        int result = reverse(b);
        System.out.println(result);
    }

    public int solution(int n) {
        String binStr = Integer.toBinaryString(n);
        char[] binChars = binStr.toCharArray();
        int max = 0;
        int count = 0;
        boolean countStart = false;
        for (int i=0;i<binChars.length;i++) {
            if (binChars[i] == '1') {
                if (count == 0) countStart = true;
                else {
                    countStart = false;
                    if (count > max) max = count;
                }
            } else if (countStart) {
                count++;
            }
        }
        return max;
    }

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }

        return count;
    }

    public int _hammingWeight(int n) {
        int count = 0;
        while(n!=0){
            if((n & 1) == 1) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    public boolean isPowerOfThree(int n) {
        int result = n;
        while(result > 1) {
            if(result % 3 == 0) {
                result = result / 3;
            } else {
              return false;
            }
        }
        return true;
    }

    public int countPrimes(int n) {
        if(n < 2) return 0;
        int count = 0;

        for(int i=2;i<n;i++) {
            int j = 2;
            boolean isPrime = true;
            while(j != i) {
                if(i % j == 0) {
                    isPrime = false;
                    break;
                }
                j++;
            }
            if(isPrime) count++;
        }

        return count;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Set<Integer> idxSet = new HashSet<>();
        for(int i=0;i<nums1.length;i++) {
            for(int j=0;j<nums2.length;j++){
                if(nums1[i] == nums2[j] && !idxSet.contains(j)){
                    idxSet.add(j);
                    break;
                }
            }
        }

        int[] result = new int[idxSet.size()];
        int k = 0;
        Iterator<Integer> itr = idxSet.iterator();
        while (itr.hasNext()){
            result[k] = nums2[itr.next()];
            k++;
        }

        return result;
    }

    public int singleNumber(int[] nums) {
        if(nums.length==1) return nums[0];
        Set<Integer> numberSet = new HashSet<>();
        int halfLen = nums.length / 2 + nums.length % 2;
        for(int i=0;i<halfLen;i++) {
            checkSet(numberSet, nums[i]);
            if(nums.length-i>halfLen)
                checkSet(numberSet, nums[nums.length-i-1]);
        }
        return numberSet.iterator().next();
    }

    private void checkSet(Set<Integer> numberSet, int num) {
        if(numberSet.contains(num))
            numberSet.remove(num);
        else
            numberSet.add(num);
    }

    public int reverse(int x) {
        if(x < 10 && x > -10)
            return x;

        long result=0;
        while(x > 9 || x < -9) {
            int lastNumber = x % 10;
            x = x / 10;
            result = result * 10 + lastNumber;
        }

        result = result * 10 + x;
        return (int)result;
    }

    private int lengthOfLongestSubstring(String s) {
        int max = 0;
        String subStr = "";
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            int idx = subStr.indexOf(c);
            if(idx != -1){
                if(idx == subStr.length() - 1)
                    subStr = ""; //reset
                else
                    subStr = subStr.substring(subStr.indexOf(c)+1);
            }

            subStr += c;

            if(subStr.length() > max){
                max = subStr.length();
            }

        }

        return max;
    }

    private Direction changeDirection(Direction direction) {
        if (direction == Direction.RIGHT){

        }
        switch (direction) {
            case RIGHT -> {
                return Direction.DOWN;
            }
            case DOWN -> {
                return Direction.LEFT;
            }
            case LEFT -> {
                return Direction.UP;
            }
            case UP -> {
                return Direction.RIGHT;
            }
        }
        return direction;
    }

    static enum Direction {
        RIGHT, DOWN, LEFT, UP;
    }
}
