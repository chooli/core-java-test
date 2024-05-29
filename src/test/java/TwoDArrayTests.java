import java.util.*;

import core.java.CommonTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TwoDArrayTests extends CommonTest {

  @Test
  public void testExams() {
      char[][] sodoku = new char[][]{{'5','3','.','.','7','.','.','.','.'}
      ,{'6','.','.','1','9','5','.','.','.'}
      ,{'.','9','8','.','.','.','.','6','.'}
      ,{'8','.','.','.','6','.','.','.','3'}
      ,{'4','.','.','8','.','3','.','.','1'}
      ,{'7','.','.','.','2','.','.','.','6'}
      ,{'.','6','.','.','.','.','2','8','.'}
      ,{'.','.','.','4','1','9','.','.','5'}
      ,{'.','.','.','.','8','.','.','7','9'}};

      int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
      rotate(matrix);
      // boolean result = isValidSudoku(sodoku);
      // System.out.println(result);
      printArray(matrix);
    }

    public void rotate(int[][] matrix) {
      int l = matrix.length;
      for (int i=0;i<l;i++) {
        for (int j=i;j<l;j++) {
          if (i != j) {
            int tmp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = tmp;
          }
        }
      }

      //flip left to right
      for (int c=0;c<l/2;c++) {
        for (int r=0;r<l;r++) {
          int tmp = matrix[r][c];
          matrix[r][c] = matrix[r][l-c-1];
          matrix[r][l-c-1] = tmp;
        }
      }
    } 
  
    public boolean isValidSudoku(char[][] board) {
      int l = board.length;
      Set<Character> rowSet = new HashSet<>(), columnSet = new HashSet<>();
      Set<Character> matrix1 = new HashSet<>(), matrix2 = new HashSet<>(), matrix3 = new HashSet<>();

      for (int i=0;i<l;i++) {

        if (i % 3 == 0) {
          matrix1.clear();
          matrix2.clear();
          matrix3.clear();
        }

        for (int j=0;j<l;j++) {
          char rowValue = board[j][i];
          char columnValue = board[i][j];

          if (!checkValue(rowValue, rowSet)) { return false; }
          if (!checkValue(columnValue, columnSet)) { return false; }
          
          // check matrix
          char matrixValue = board[i][j];
          if (j / 3 == 0 && !checkValue(matrixValue, matrix1)) {
            return false;
          } else if (j / 3 == 1 && !checkValue(matrixValue, matrix2)) {
            return false;
          } else if (j / 3 == 2 && !checkValue(matrixValue, matrix3)) {
            return false;
          }

          if (j == l - 1) {
            rowSet.clear();
            columnSet.clear();
          }
        }
      }
      return true;
    }

    private boolean checkValue(char value, Set<Character> set) {
        if (value != '.') {
          if (set.contains(value)) {
            return false;
          } else {
            set.add(value);
          }
        }
        return true;
    }

}
