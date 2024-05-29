import core.java.CommonTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DivideAndConquer extends CommonTest {

    int[] sorted, unsorted = new int[]{-23, 1, 3, -2, -8, 0, 9, 4, -7};

    @BeforeAll
    public void setUp() {
        sorted = new int[]{1, 3, 9, 11, 12};

        assertTrue(true);
    }

    @Test
    public void quickSort() {
        int[] result = _quickSort(unsorted);
        printArray(result);

        assertTrue(true);
    }

    int[] _quickSort(int[] array) {
        int l = array.length;

        if (l < 2) return array;
        if (l == 2) {
            int temp = array[0];
            if (array[1] < temp) {
                array[0] = array[1];
                array[1] = temp;
            }
            return array;
        }
        int pivot = array[l/2];
        List<Integer> less = new ArrayList<>();
        List<Integer> larger = new ArrayList<>();
        for (int i=0;i<l;i++) {
            if (array[i] < pivot) less.add(array[i]);
            if (array[i] > pivot) larger.add(array[i]);
        }
        int[] lessArr = _quickSort(less.stream().mapToInt(i -> i).toArray());
        int[] largerArr = _quickSort(larger.stream().mapToInt(i -> i).toArray());
        int[] newArray = new int[lessArr.length + largerArr.length + 1];
        System.arraycopy(lessArr, 0, newArray, 0, lessArr.length);
        newArray[lessArr.length] = pivot;
        System.arraycopy(largerArr, 0, newArray, lessArr.length+1, largerArr.length);
        return newArray;
    }

    private int binarySearch(int[] arr, int target) {
        if (arr.length == 1) return 0;
        int mid = arr.length / 2;
        if (arr[mid] == target || mid == arr.length) return mid;

        if (arr[mid] > target) {
            int[] rest = Arrays.stream(arr, 0, mid).toArray();
            return binarySearch(rest, target);
        } else {
            int[] rest = Arrays.stream(arr, mid + 1, arr.length).toArray();
            return mid + 1 + binarySearch(rest, target);
        }
    }

    private int max(int[] arr) {
        if (arr.length == 1) return arr[0];
        int[] rest = IntStream.range(1, arr.length).map(i -> arr[i]).toArray();
        return Math.max(arr[0], max(rest));
    }

    private int count(int[] arr) {
        if (arr.length == 0) return 0;
        int[] rest = IntStream.range(1, arr.length).map(i -> arr[i]).toArray();
        return 1 + count(rest);
    }

    private int sum(int[] arr) {
        if (arr.length == 0) return 0;
        int[] rest = IntStream.range(1, arr.length).map(i -> arr[i]).toArray();
        return arr[0] + sum(rest);
    }

}
