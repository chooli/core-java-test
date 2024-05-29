package core.java.algo;

import core.java.CommonTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SortingTest extends CommonTest {
    int[] unsorted;

    @BeforeAll
    public void setup() {
        unsorted = new int[]{12, -2, -12, 3, 5, 7, 12, 4, -9, 3};
        println("start sorting tests with ");
        printArray(unsorted);
        println("----------------------------");
    }

    private boolean wordPattern(String pattern, String s) {
        String[] words = s.split("\\W");
        String[] map = new String[150]; 
        int l = pattern.length();
        if (words.length != l) { return false; }

        for (int i=0;i<l;i++) {
            int sc = (int)pattern.charAt(i);
            System.out.println(sc);
            System.out.println(map[sc]);
            System.out.println(words[i]);
            if (map[sc] != null && !map[sc].equals(words[i])) { return false; }
            map[sc] = words[i];
        }

        return true;
    }

    @Test
    void bubbleSortTest() {
        int l = unsorted.length;
        for (int i=0;i<l;i++) {
            for (int j=i+1;j<l;j++) {
                if (unsorted[i] < unsorted[j]) {
                    int tmp = unsorted[i];
                    unsorted[i] = unsorted[j];
                    unsorted[j] = tmp;
                }
            }
        }

        printArray(unsorted);
        assertTrue(true);
    }

    @Test
    void selectionSortTest() {
        int l = unsorted.length;
        for (int i=0;i<l;i++) {
            int min = i;
            for (int j=i+1;j<l;j++) {
                if (unsorted[min] > unsorted[j]) min = j;
                if (j == l-1 && min != i) {
                    int tmp = unsorted[min];
                    unsorted[min] = unsorted[i];
                    unsorted[i] = tmp;
                }
            }
            //printArray(unsorted);
        }

        assertTrue(true);
    }

    @Test
    void insertionSortTest() {
        int l = unsorted.length;
        for (int i=1;i<l;i++) {
            int iVal = unsorted[i];
            for (int j=i-1;j>-1;j--) {
                if (iVal < unsorted[j]) {
                    unsorted[j+1] = unsorted[j];
                    unsorted[j] = iVal;
                } else {
                    break;
                }
            }
            printArray(unsorted);
        }

        assertTrue(true);
    }

    @Test
    void quickSortTest() {
        //List<Integer> list = IntStream.of(unsorted).boxed().toList();
        //quickSortList(list);
        quickSortArray(unsorted);
        printArray(unsorted);

        assertTrue(true);
    }

    private void quickSortArray(int[] list) {
        if (list.length == 2) {
            int tmp = list[1];
            list[1] = Math.max(list[0], list[1]);
            list[0] = Math.min(list[0], tmp);
        } else if (list.length >= 3) {
            int pivot = list[0];
            int[] smaller = IntStream.of(list).filter(val -> val < pivot).toArray();
            int[] larger = IntStream.of(list).filter(val -> val > pivot).toArray();

            list = new int[smaller.length+larger.length+1];
            System.arraycopy(smaller, 0, list, 0, smaller.length);
            list[smaller.length] = pivot;
            System.arraycopy(larger, 0, list, smaller.length+1, larger.length);
        }
    }

    private void quickSortList(List<Integer> list) {
        if (list.size() == 2) {
            if (list.get(0) > list.get(1)) {
                int tmp = list.get(1);
                list.set(1, list.get(0));
                list.set(0, tmp);
            }
        } else if (list.size() >= 3) {
            int pivot = list.get(0);
            List<Integer> smaller = list.stream().filter(val -> val < pivot).toList();
            quickSortList(smaller);
            List<Integer> larger = list.stream().filter(val -> val > pivot).toList();
            quickSortList(larger);

            List<Integer> result = new ArrayList<>(smaller);
            result.add(pivot);
            result.addAll(larger);
            list = result;
        }
    }

    @Test
    void mergeSort() {
        mergeSortArray(unsorted);
        printArray(unsorted);

        assertTrue(true);
    }

    private void mergeSortArray(int[] list) {
        if (list.length == 2) {
            if (list[0] > list[1]) {
                int tmp = list[0];
                list[0] = list[1];
                list[1] = tmp;
            }
        } else if (list.length >= 3) {
            int middle = list.length / 2;
            int[] left = new int[middle];
            System.arraycopy(list, 0, left, 0, middle);
            int[] right = new int[list.length - middle];
            System.arraycopy(list, middle, right, 0, list.length - middle);
            mergeSortArray(left);
            mergeSortArray(right);

            list = mergeArrays(left, right);
        }
    }

    private int[] mergeArrays(int[] left, int[] right) {
        int i = 0, ll =  left.length, j = 0, rl = right.length, count = 0;
        int[] result = new int[ll + rl];
        while (i<ll && j<rl) {
            if (left[i] < right[j]) {
                result[count++] = left[i++];
            } else {
                result[count++] = right[j++];
            }
        }
        while (i < ll) {
            result[count++] = left[i++];
        }
        while (j < rl) {
            result[count++] = right[j++];
        }

        return result;
    }

    int[] _mergeSort(int[] unsorted, int start, int end) {
        if (start == end) {
            return unsorted;
        }

        int middle = (end + start) / 2;
        _mergeSort(unsorted, start,  middle);
        _mergeSort(unsorted, middle + 1, end);

        return mergeTwoSortedArrays(unsorted, start, middle + 1, end);
    }

    private int[] mergeTwoSortedArrays(int[] a, int start, int middle, int end){
        int[] merged = new int[end - start + 1];

        int i = start, j = middle, k = 0;

        while (i<middle && j<=end) {
            if (a[i] <= a[j]) {
                merged[k++] = a[i++];
            } else {
                merged[k++] = a[j++];
            }
        }
        
        while(i<middle) {
            merged[k++] = a[i++];
        }
        while(j<=end){
            merged[k++] = a[j++];
        }

        printArray(merged);

        for (int h=0;h<merged.length;h++) {
            a[start+h] = merged[h];
        }
        return a;
    }

    private int[] mergeTwoSortedArrays(int[] a, int[] b) {
        int la = a.length;
        int lb = b.length;
        int[] merged = new int[la + lb];

        int i = 0, j= 0, k = 0;
        while (i<la && j<lb) {
            if (a[i] <= b[j]) {
                merged[k++] = a[i++];
            } else {
                merged[k++] = b[j++];
            }
        }
        while (i<la) {
            merged[k++] = a[i++];
        }
        while (j<lb) {
            merged[k++] = b[j++];
        }

        return merged;
    }

    private List<Integer> distinctMergeSort(int[] unsorted, int start, int end) {
        printf("start: %d, end: %d ", start, end);
        if (start == end) {
            return new ArrayList<>(Arrays.asList(unsorted[start]));
        }

        int middle = (end + start) / 2;
        List<Integer> leftList = distinctMergeSort(unsorted, start, middle);
        List<Integer> rightList = distinctMergeSort(unsorted, middle + 1, end);
        leftList.stream().filter(i -> i < 0 || i > 9).toList();

        return distinctMerge(leftList, rightList);
    }

    private List<Integer> distinctMerge(List<Integer> leftList, List<Integer> rightList) {
        int ll = leftList.size(), rl = rightList.size();
        List<Integer> merged = new ArrayList<>();

        int i = 0, j= 0;
        while (i<ll && j<rl) {
            if (leftList.get(i) < rightList.get(j)) {
                merged.add(leftList.get(i++));
            } else if (leftList.get(i).equals(rightList.get(j))) {
                merged.add(leftList.get(i++));
                j++;
            } else {
                merged.add(rightList.get(j++));
            }
        }
        while (i<ll) {
            merged.add(leftList.get(i++));
        }
        while (j<rl) {
            merged.add(rightList.get(j++));
        }

        return merged;
    }

    private List<Integer> distinctMerge(int[] a, int start, int middle, int end) {
        List<Integer> merged = new ArrayList<>();

        int i = start, j = middle;

        while (i<middle && j<=end) {
            if (a[i] < a[j]) {
                merged.add(a[i++]);
            } else if (a[i] == a[j]) {
                merged.add(a[i++]);
                j++;
            } else {
                merged.add(a[j++]);
            }
        }

        while (i<middle) {
            merged.add(a[i++]);
        }
        while (j<middle) {
            merged.add(a[j++]);
        }
        printList(merged);
        return merged;
    }

}
