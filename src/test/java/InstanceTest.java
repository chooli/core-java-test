

import core.java.CommonTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InstanceTest extends CommonTest {

    @BeforeAll
    public void setup() {
        System.out.println("start reuters tests ... ");
    }

    @Test
    public void test1() {
        String s = "aaabaaa";
        assertTrue(checkPalindromic(s));
    }

    @Test
    public void test2() {
        String s = "aaaaaa";
        assertTrue(checkPalindromic(s));
    }

    @Test
    public void test3() {
        String s = "aaabbb";
        assertFalse(checkPalindromic(s));
    }

    @Test
    public void test4() {
        String s = "aaa";
        assertTrue(checkPalindromic(s));
    }

    @Test
    public void test5() {
        String s = "i";
        assertTrue(checkPalindromic(s));
    }

    @Test
    public void testExams() {
        int[] unsorted0 = {0, 0, 1, 1, 0, 0};
        int[] unsorted1 = {8, 6, 4, 3, 1, 2, 4, 0};
        int[] unsorted2 = {5, 2, 99, 3, 4, 1, 100};

        String s1 = "aaabccc", s2 = "wallo";
        assertTrue(checkPalindromic(s1));
        char[][] carrs = {
                {'B', 'B', 'B', 'B', 'B', 'B'},
                {'B', 'W', 'W', 'B', 'W', 'B'},
                {'B', 'W', 'B', 'B', 'W', 'B'},
                {'B', 'B', 'W', 'W', 'W', 'W'},
                {'B', 'B', 'B', 'B', 'B', 'B'}
        };

        char[][] sudoku = {
                {'5', '6', '.', '7', '8', '.', '.', '.', '.'},
                {'1', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '8', '.', '.', '.', '.', '.', '.'},
                {'2', '1', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '3', '.', '.', '.', '.', '.', '.'},
                {'4', '.', '5', '.', '.', '.', '.', '.', '.'},
                {'6', '7', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '9', '.', '.', '.', '.', '.', '.', '.'}
        };
        System.out.println(countPalindromic(s1.length(), s1));

        //printArray(mergeSort(unsorted, 0, l - 1));
        //System.out.println(findMaxSubset(s1, s2));
    }

    private boolean checkPalindromic(String s) {
        int middle = s.length() / 2;
        for (int i=0;i<middle;i++){
            if(s.charAt(i) != s.charAt(s.length()-i-1)) return false;
        }
        return true;
    }

    private boolean isValid(char[][] sudoku) {
        Set<Character> rowCheck = new HashSet<>(), columnCheck = new HashSet<>(),
        matrix1 = new HashSet<>(), matrix2 = new HashSet<>(), matrix3 = new HashSet<>();

        for (int r=0;r<sudoku.length;r++) {

            if (r % 3 == 0) {
                matrix1.clear();
                matrix2.clear();
                matrix3.clear();
            }

            for (int c=0;c<sudoku[0].length;c++) {
                char rowChar = sudoku[r][c], colChar = sudoku[c][r];

                if (rowChar == '.') continue;

                if (c / 3 == 0) {
                    if (matrix1.contains(rowChar)) return false;
                    else matrix1.add(rowChar);
                } else if (c / 3 == 1) {
                    if (matrix2.contains(rowChar)) return false;
                    else matrix2.add(rowChar);
                } else if (c / 3 == 2) {
                    if (matrix3.contains(rowChar)) return false;
                    else matrix3.add(rowChar);
                }

                if (rowCheck.contains(rowChar)) return false;
                else rowCheck.add(rowChar);

                if (colChar == '.') continue;
                if (columnCheck.contains(colChar)) return false;
                else columnCheck.add(colChar);
            }

            columnCheck.clear();
            rowCheck.clear();

        }

        return true;
    }


    private int countPalindromic(int n, String s) {
        int count = 1, result=n;
        char prev = s.charAt(0);
        int[] sameCharCount = new int[n];
        for (int i=0;i<s.length();i++) {
            if (i!=s.length()-1 && s.charAt(i) == s.charAt(i+1)) count++;
            else if (count > 0) {
                result += (count * (count - 1)) / 2;
                for (int j=i;j>i-count;j--) sameCharCount[j] = count;
                count = 1;
            }
        }

        printArray(sameCharCount);

        for (int i=0;i<sameCharCount.length-1;i++) {
            if (i > 0 && sameCharCount[i] == 1 && s.charAt(i-1) == s.charAt(i+1)) {
                result += Math.min(sameCharCount[i-1], sameCharCount[i+1]);
            }
        }

        return result;
    }


}
