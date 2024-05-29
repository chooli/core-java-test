import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.java.CommonTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StringsTests extends CommonTest {
  
    @Test
    void tests() {
      String[] words = {"bc","ba","ab"};
      String s = "abbcba";
      for (String word : words) {
        int wl = word.length(), end = 0;
        int l = s.length();
        for (int i=0;i<l;i+=wl) {
          end = i+wl;
          System.out.println(s.substring(i, end));
          if ( s.substring(i, end).equals(word) ) {
            s = s.substring(0, i) + (end < l ? s.substring(end) : "");
            break;
          }
        }
      }
      assertTrue(true);
    }

    @Test
    void calculateMinutes() {
      String str = "1:30am-11:00pm";
      int result = 0;

      String[] times = str.split("-");
      String[] time0Parts = times[0].split(":");
      String[] time1Parts = times[1].split(":");
      int hour0 = Integer.parseInt(time0Parts[0]);
      int minute0 = Integer.parseInt(time0Parts[1].substring(0,2));
      String ampm0 = time0Parts[1].substring(2, 4);
      int hour1 = Integer.parseInt(time1Parts[0]);
      int minute1 = Integer.parseInt(time1Parts[1].substring(0,2));
      String ampm1 = time1Parts[1].substring(2, 4);

      if (ampm0.equals("am") && ampm1.equals("pm")) {
        if (hour0 == 12) hour0 = 0; //patch the 12am confuse
        result = (hour1+12 - hour0) * 60 + (minute1 - minute0);
      } else if ((ampm0.equals("am") && ampm1.equals("am")) || (ampm0.equals("pm") && ampm1.equals("pm"))) {
        if (hour1 > hour0 || (hour1 == hour0 && minute1 > minute0)) result = (hour1 - hour0) * 60 + (minute1 - minute0);
        else result = (24 - hour0) * 60 + (-minute0) + hour1 * 60 + minute1;
      } else if (ampm0.equals("pm") && ampm1.equals("am")) {
        result = (12 - hour0) * 60 + (-minute0) + hour1 * 60 + minute1;
      }

      System.out.println(result);
    }

    @Test
    void regexTest() {
      String s = "cat * is not a * dog";
      s = s.replace("*", "|");
      Pattern pattern = Pattern.compile("^.*a.*a.*$");
      Matcher matcher= pattern.matcher("a cat is not a dog");

      System.out.println(s);
      Assertions.assertTrue(matcher.find());
    }

    @Test
    void MinWindowSubstring() {
      // code goes here
      String N = "aaabaaddae", K = "aed";
      Map<Character, Integer> motherMap = buildMap(K);
      Map<Character, Integer> checkMap = new HashMap<>(motherMap);
      int minCount = Integer.MAX_VALUE, start = 0, end = 0, l = N.length();
      String minStr = null;
      while (start < l && end <= l) {
        System.out.println("start:"+start+" end:"+end);
        if (checkMap.isEmpty()) {
          checkMap = new HashMap<>(motherMap);
          int count = end - start;
          if (minCount > count) {
            minStr = N.substring(start, end);
            minCount = count;
          }
          start++;
          end = start;
        } else {
          char endChar = end == l ? '!' : N.charAt(end);
          Integer val = checkMap.computeIfPresent(endChar, (k,v) -> v-1);
          if (val != null && val.equals(0)) checkMap.remove(endChar);
          end++;
        }
      }

      println(minStr);

      assertTrue(true);
    }

    private Map<Character, Integer> buildMap(String K) {
      Map<Character, Integer> map = new HashMap<>();
      for (char c : K.toCharArray()) {
        Integer val = map.putIfAbsent(c, 1);
        if (val != null) map.computeIfPresent(c, (k,v) -> v+1);
      }
      return map;
    }

    private List<Integer> findItems(String s, int[] pipe1, int[] pipe2) {
      List<Integer> result = new ArrayList<>(List.of(0, 0));
      int counter1 = 0;
      boolean counterStart1 = false;
      for (int i=0;i<s.length();i++) {
        char c = s.charAt(i);
        if (c == '|') {
          if (!counterStart1 && pipe1[0] <= i) counterStart1 = true;
          if (counterStart1 && pipe1[1] >= i) {
            result.set(0, result.get(0) + counter1);
            counter1 = 0;
          }
        } else {
          if (counterStart1) counter1++;
        }
      }
      return result;
    }

    private void reverseString(char[] s) {
      int l = s.length;
      for (int i=0;i<l/2;i++) {
        char tmp = s[i];
        s[i] = s[l-i-1];
        s[l-i-1] = tmp;
      }
      printArray(s);
    }

    public String longestCommonPrefix(String[] strs) {
      int l = strs.length;
      String result = strs[0];
      for (int i=1;i<l;i++) {
        if (result.length() == 0 || strs[i].length() == 0) {
          return "";
        }

        for (int j=0;j<strs[i].length();j++) {
          if (j >= result.length()) {
            continue;
          } else if (result.charAt(j) != strs[i].charAt(j)) {
            result = result.substring(0, j);
            continue;
          } 

          if (result.length() > strs[i].length()) {
            result = result.substring(0, strs[i].length());
            continue;
          }
        }
      }
      return result;
    }

    public int strStr(String haystack, String needle) {
      int nl = needle.length(), hl = haystack.length();
      if ( nl > hl) { return -1; }

      for (int i=0;i<hl;i++) {
        if (haystack.charAt(i) == needle.charAt(0)) {
          String subStr = haystack.substring(i, i+nl-1);
          if (subStr.equals(needle)) {
            return i;
          }
        }
      }

      return -1;
    }

}