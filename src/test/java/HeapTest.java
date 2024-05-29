import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

public class HeapTest {

    @Test
    void heapSortTest() {
        Heap heap = new Heap(5, (a, b) -> a - b);
        int[] nums = new int[]{1,2,3,4};
        Queue<Integer> sumQueue = new PriorityQueue<Integer>(2, (a, b) -> a - b);

        heap.insert(4);
        heap.insert(2);
        heap.insert(6);
        heap.insert(1);
        heap.insert(7);

        System.out.println(heap.peek());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());

        Assertions.assertTrue(true);
    }

    /**
     * Rules of a heap here
     * - if a node is at index i
     * - its left child is: 2*i
     * - its right child is: 2*i+1
     * - its parent is: i/2
     */
    static class Heap {

        private int[] heap;
        private int capacity;
        private int index;
        private Comparator<Integer> compareTwo;

        public Heap(int size, Comparator<Integer> compareTwo) {
            this.heap = new int[size];
            this.index = 0;
            this.capacity = size;
            this.compareTwo = compareTwo;
        }

        public void insert(int value) {
            if (index > capacity - 1) {
                System.out.println(">_FULL_<");
            } else {
                heap[index] = value;
                heapifyUp(index);  //shift the new value to right position
                if (index < capacity - 1) index++;
            }
        }

        private void heapifyUp(int idx) {
            while(idx > 0 && compareTwo.compare(heap[idx], heap[idx/2]) > 0) {
                swap(idx/2, idx);
                idx = idx/2;
            }
        }

        public int poll() {
            if (index < 0) {
                System.out.println(">_FULL_<");
                return -1;
            }
            //take the root out
            int result = heap[0];
            //swap the end and the root
            heap[0] = heap[index];
            index--;
            heapifyDown(0);

            return result;
        }

        private void heapifyDown(int idx) {
            int leftChildIdx = 2*idx;
            int rightChildIdx = 2*idx+1;

            if (leftChildIdx >= capacity || rightChildIdx>= capacity) return;

            if (compareTwo.compare(heap[leftChildIdx], heap[rightChildIdx]) > 0 && compareTwo.compare(heap[leftChildIdx], heap[idx]) > 0) {
                swap(idx, leftChildIdx);
                heapifyDown(leftChildIdx);
            } else if (compareTwo.compare(heap[rightChildIdx], heap[leftChildIdx]) > 0 && compareTwo.compare(heap[rightChildIdx], heap[idx]) > 0) {
                swap(idx, rightChildIdx);
                heapifyDown(rightChildIdx);
            }
        }

        public int peek() {
            return heap[0];
        }

        private void swap(int idx1, int idx2) {
            int temp = heap[idx1];
            heap[idx1] = heap[idx2];
            heap[idx2] = temp;
        }
    }

}
