package core.java;

import java.util.List;


public abstract class CommonTest {

    protected static void printArray(int[] arr) {
        System.out.print("[");
        for(int i : arr) {
            System.out.printf("%d ", i);
        }
        System.out.println("]");
    }

    protected static void printArray(Integer[] arr) {
        System.out.print("[");
        for(int i : arr) {
            System.out.printf("%d ", i);
        }
        System.out.println("]");
    }

    protected static void printArray(int[][] arr) {
        System.out.print("[");
        for(int i=0;i<arr.length;i++) {
           printArray(arr[i]);
        }
        System.out.println("]");
    }

    protected void printList(List<Integer> list) {
        System.out.print("[");
        for(Object obj : list) {
            System.out.print(obj+" ");
        }
        System.out.println("]");
    }

    protected void printArray(String[] arr) {
        System.out.print("[");
        for(String obj : arr) {
            System.out.print(obj+" ");
        }
        System.out.println("]");
    }

    protected void printArray(char[] arr) {
        System.out.print("[");
        for(char c : arr) {
            System.out.print(String.format("%s ", c));
        }
        System.out.println("]");
    }

    private <T> void printout(T[] arr){
        System.out.print("[");
        for(T t : arr) {
            System.out.printf("%s ", t);
        }
        System.out.println("]");
    }

    public void println(String s) {
        System.out.println(s);
    }

    public void println(Object s) {
        System.out.println(s.toString());
    }

    public void printf(String format, Object... args) {
        System.out.printf(format, args);
    }
}
