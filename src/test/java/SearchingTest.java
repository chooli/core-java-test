

import core.java.CommonTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SearchingTest extends CommonTest {

    @BeforeAll
    static void setup() {
        System.out.println("== start searching tests == \n");
    }

    @Test
    void testExams() {
        int[] arr = {-2, -1, 1, 3, 5, 7, 9, 12, 43, 67, 86, 856};
        //arr = Arrays.stream(arr).sorted().distinct().collect();
        //System.out.println(binarySearch1(arr, 0, arr.length - 1, -2));
        System.out.println(binarySearch2(arr, 13));
    }

    //assume array is sorted
    private boolean binarySearch1(int[] arr, int l, int r, int x) {
        if (arr == null) return false;
        if (arr.length == 1) return x == arr[0];
        if (x == arr[0] || x == arr[arr.length-1]) {
            return true;
        }

        int middle = l + (r - l) / 2;
        if (middle > l) {
            if (arr[middle] == x) return true;
            else if (arr[middle] > x) return binarySearch1(arr, l, middle, x);
            else return binarySearch1(arr, middle, r, x);
        }
        return false;
    }

    private boolean binarySearch2(int[] arr, int x) {
        int start = 0, end = arr.length;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == x) return true;
            else if (arr[mid] < x) start = mid + 1;
            else end = mid - 1;
        }
        return false;
    }

}
