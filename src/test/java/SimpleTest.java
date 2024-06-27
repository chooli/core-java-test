import core.java.CommonTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SimpleTest extends CommonTest {

    static final String RIGHT = "right";
    static String DOWN = "down";
    static String LEFT = "left";
    static String UP = "up";
    static String DONE = "done";
    static int min = Integer.MAX_VALUE;


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

        //** Array to List **/
        // create list by array
        List<Integer> intList = IntStream.of(arr1).boxed().toList(); //immutable
        intList = IntStream.range(0, 10).boxed().toList();
        intList = Arrays.asList(arr3);  //immutable
        intList = new ArrayList<>(IntStream.of(arr1).boxed().toList()); //mutable
        intList.add(999);

        //** Int List to Array
        newArr = intList.stream().mapToInt(Integer::intValue).toArray();
        // create new array by array
        Integer[] array3 = intList.toArray(Integer[]::new);

        //** char array to list  **/
        char[] chars = {'a', 'b', 'c', 'd'};
        List<Character> charList = String.valueOf(chars).chars().mapToObj(i -> (char)i).toList(); // immutable
        charList = new ArrayList<>(String.valueOf(chars).chars().mapToObj(i -> (char)i).toList()); // mutable
        charList.add('e');

        //** String array to List **/
        String[] array1 = {"a", "b", "c", "d"};
        List<String> strList = Arrays.asList(array1);  //immutable
        strList = new ArrayList<>(Arrays.asList(array1)); //mutable
        strList.add("e");
        strList = new ArrayList<>(List.of("a", "b", "c"));
        strList.add("d");
        //** String list to array **/
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

        //** MAP **/
        Map<String, Integer> map = new HashMap<>();
        map.put("test", map.getOrDefault("test", 0) + 1);
        map.computeIfPresent("test", (k,v) -> v-1);

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
            case "c", "e" -> str += "++";
            default -> str += "--";
        }

        assertTrue(true);
    }

    private boolean isVowel(char c) {
        switch(c) {
            case 'a', 'e', 'i', 'o', 'u' -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }
}
