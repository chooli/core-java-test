import core.java.CommonTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SimpleTest extends CommonTest {

    static final String RIGHT = "right";
    static String DOWN = "down";
    static String LEFT = "left";
    static String UP = "up";
    static String DONE = "done";


    @BeforeAll
    void setup() {
        // setup here
    }

    @Test
    void test() {
        int[] arr1 = {1, 2, 3, 4, 59, 6, 7, 8, 9, 1};
        int[] arr2 = {12, -2, -12, 3, 5, 7, 12, 4, -9, 3};
        Integer[] arr3 = {12, -2, -12, 3, 5, 7, 12, 4, -9, 3};

        //merge two array
        int[] newArr = new int[arr1.length+arr2.length];
        System.arraycopy(arr1, 0, newArr, 0, arr1.length);
        System.arraycopy(arr2, 0, newArr, arr1.length, arr2.length);

        //** Int Array to List **/
        // create list by array
        List<Integer> intList = IntStream.of(arr1).boxed().toList();
        printList(intList);
        intList = Arrays.asList(arr3);
        printList(intList);
        List<Integer> sublist = new ArrayList<>(3);

        //** Int List to int Array
        newArr = intList.stream().mapToInt(Integer::intValue).toArray();
        // create new array by array
        Integer[] array3 = intList.toArray(Integer[]::new);

        //** String array to List **/
        String[] array1 = {"a", "b", "c", "d"};
        List<String> strList = new ArrayList<>(List.of(""));

        String[] array2 = strList.toArray(String[]::new);
        StringBuilder sb = new StringBuilder();

        //** QUEUE **/
        Queue<String> queue = new ArrayDeque<>();
        queue.addAll(List.of("a", "b", "c"));
        queue.offer("d");
        String str = queue.poll();
        str = queue.peek();

        //** STACK **/
        Stack<String> stack = new Stack<>();
        stack.add("a");
        stack.addAll(List.of("a", "b", "c"));
        String str1 = stack.pop();
        str1 = stack.peek();
        stack.clear();

        //** SET **/
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.clear();
        int[] setToInts = set.stream().mapToInt(Integer::intValue).toArray();

        //** SWITCH CASE **/
        switch(str1) {
            case RIGHT:
                str1 += "right";
                break;
            case "b":
            case "c":
                str1 += "++";
                break;
            default:
                break;
        }

        switch(Objects.requireNonNull(str)) {
            case "a" -> str += "-+";
            case "b" -> str += "+-";
            case "c" -> str += "++";
            default -> str += "--";
        }

        assertTrue(true);
    }

    private int maxNum(int idx, int[] list, int max) {
        max = Math.max(max, list[idx]);
        if (idx < list.length-1) return maxNum(++idx, list, max);
        return max;
    }
}
