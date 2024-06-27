package core.java.algo.string;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StringTest {

    @BeforeAll
    public void setup() {
        System.out.println("start learning tests ... ");
    }

    @Test
    void test() {

        String s1 = "helello", s2 = "l";
        String[] strs = {"ca", "a"};
        int Y = 75;
        float _Y = (float)Y;
        System.out.println( Math.ceil (_Y / 30));

        assertTrue(true);
    }

    @Test
    void test1() {
        String s1 = "AAAA", s2 = "AAAAAA";
        int result = findMatchSub(s1, s2);
        assertEquals(4, result);
    }

    private int findMatchSub(String s1, String s2) {
        int count = 1;
        List<Point> matchPoints = new ArrayList<>();
        for (int i=0;i<s2.length();i++) {
            for (int j=0;j<s1.length();j++) {
                if (s1.charAt(j) == s2.charAt(i)) {
                    matchPoints.add(new Point(i, j));
                }
            }
        }
        printList(matchPoints);
        int max = count;
        for (int i=0;i<matchPoints.size();i++) {
            int x = matchPoints.get(i).x, y = matchPoints.get(i).y;
            for (int j=i+1;j<matchPoints.size();j++) {
                if (matchPoints.get(j).x == x + 1 && matchPoints.get(j).y == y + 1) {
                    System.out.println(matchPoints.get(j));
                    count++;
                    x++;
                    y++;
                }

            }
            if (max < count) max = count;
            count = 1;
        }

        return max;
    }

    private class Point {
        int x, y;

        Point(int x, int y) { this.x = x; this.y = y; }

        @Override
        public String toString() {
            return String.format("(%d, %d) ", x, y);
        }
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";

        String prefix = "";
        int i = 0;
        for (int j=0;j<strs.length;j++) {
            if (j == 0) {
                prefix += (strs[0].charAt(i));
            } else if (!strs[j].startsWith(prefix.toString())) {
                prefix = prefix.substring(0, prefix.length() - 1);
                return prefix;
            }
        }
        return prefix;
    }

    public List<String> fizzBuzz(int n) {
        List<String> lst = new ArrayList<>();
        String result;
        boolean isMultiple = false;
        for(int i=1;i<n+1;i++) {
            result = String.valueOf(i);
            if(i % 3 == 0) {
                result = "Fizz";
                isMultiple = true;
            }
            if(i % 5 == 0) {
                result = isMultiple ? result + "Buzz" : "Buzz";
            }
            isMultiple = false;
            lst.add(result);
        }
        return lst;
    }

    public int strStr(String haystack, String needle) {
        if(needle.length() == 0) return 0;
        if(needle.length() > haystack.length()) return -1;
        int idx = -1;
        for(int i=0;i<=haystack.length()-needle.length();i++) {
            if(needle.equals(haystack.substring(i, i + needle.length()))) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    public int myAtoi(String str) {
        if(str.trim().length() == 0)
            return 0;
        Integer result = null;
        int sign = 0;
        char[] chars = str.toCharArray();
        for(int i=0;i<chars.length;i++) {
            if(chars[i] == ' '){
                if(result == null && sign == 0){
                    continue;
                }else{
                    break;
                }
            }

            if(chars[i] == '-') {
                if(sign==0 && result == null) {
                    sign = -1;
                    continue;
                } else {
                  break;
                }
            }

            if(chars[i] == '+'){
                if(sign==0 && result == null) {
                    sign = 1;
                    continue;
                } else {
                    break;
                }
            }

            if(Character.isDigit(chars[i])) {
                if (result == null) {
                    result = Character.getNumericValue(chars[i]);
                } else {
                    if ( ((long)result * 10 + Character.getNumericValue(chars[i])) > Integer.MAX_VALUE )
                        return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                    result = result * 10 + Character.getNumericValue(chars[i]);
                }
            }else{
                break;
            }
        }
        if(result == null)
            return 0;
        else
            return sign == -1 ? - result : result;
    }

    public boolean isPalindrome(String s) {
        if(s.trim().isEmpty()) {
            return true;
        }
        s = removeNonalphanumeric(s);
        for(int i=0, j=s.length();i<j;i++,j--) {
            if(s.charAt(i) != s.charAt(j-1))
                return false;
        }

        return true;
    }

    private String removeNonalphanumeric(String s) {
        return s.toLowerCase().replaceAll("[^a-z0-9]", "");
    }

    public int firstUniqChar(String s) {
        int l = s.length();
        Map<Character, Integer> charMap = new HashMap<>();
        for(int i=0;i<l;i++) {
            char c = s.charAt(i);
            if(charMap.containsKey(c)) {
                charMap.put(c, -1);
            } else {
                charMap.put(c, i);
            }
        }

        Iterator<Integer> itr = charMap.values().iterator();
        int minIdx = -1;
        while(itr.hasNext()){
            Integer idx = itr.next();
            if(idx != -1) {
                if(minIdx == -1)
                    minIdx = idx;
                else if(idx < minIdx)
                    minIdx = idx;
            }
        }

        return minIdx;
    }

    public String convert(String s, int numRows) {
        if(s.trim().isEmpty())
            return "";
        if(numRows<=1)
            return s;
        String[] matrix = new String[numRows];

        int idx=0, col=0;
        while(idx<s.length()) {
            for(int i=0;i<numRows;i++) {
                if(matrix[i] == null)
                    matrix[i] = "";

                if(idx==s.length())
                    break;

                if(col % (numRows-1) == 0) {
                    matrix[i] += s.charAt(idx);
                    idx++;
                }else if((numRows-i-1) == col % (numRows-1)){
                    matrix[i] += s.charAt(idx);
                    idx++;
                }
            }
            col++;
        }

        //print out matrix
        String result = "";
        for(int i=0;i<numRows;i++) {
            result += matrix[i] != null ? matrix[i] : "";
        }

        return result;
    }

    public String longestPalindrome(String s) {
        String result = "", subStr = "";

        s = s.trim();
        if(s.isEmpty() || s.length() == 1)
            return s;

        String reversedStr = reverseString(s);

        if(s.equals(reversedStr)) {
            return s;
        }

        for(int i=0;i<s.length();i++) {
            if(subStr.equals(String.valueOf(s.charAt(i)))){
                result = subStr + s.charAt(i);
            }
            subStr += s.charAt(i);
            if(!reversedStr.contains(subStr)){
                if(subStr.charAt(0) == subStr.charAt(subStr.length()-2) && result.length() < subStr.length() - 1) {
                    result = subStr.substring(0, subStr.length()-1);
                }
                subStr = subStr.substring(1);
            } else if(i==s.length()-1 && result.length() < subStr.length()) {
                result = subStr;
            }
        }

        return result;
    }

    private String reverseString(String s){
        String reverse = "";
        for(int i=s.length()-1;i>=0;i--) {
            reverse += s.charAt(i);
        }
        return reverse;
    }

    private boolean isAnagram(String s, String t) {
        if(s.length()!=t.length())
            return false;
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        for(char tc : tArr){
            int idx = s.indexOf(tc);

            if(idx < 0) {
                return false;
            } else if(idx == 0) {
                s = s.substring(1);
            } else if(idx == s.length() - 1) {
                s = s.substring(0, idx);
            } else {
                s = s.substring(0, idx) + s.substring(idx + 1);
            }
        }
        return s.trim().isEmpty();
    }

    protected void printList(List<Point> list) {
        System.out.print("[");
        for(Object obj : list) {
            System.out.print(obj+" ");
        }
        System.out.println("]");
    }


}
