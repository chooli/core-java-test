package core.java.algo.graph;

import core.java.CommonTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BFSTest extends CommonTest {

    private String[] deadEnds;
    private String target;
    private String start;

    @BeforeAll
    private void setUp() {
        deadEnds = new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"};
        target = "8888";
        start = "0000";
    }

    @Test
    void openTheLock() {
        String separator = "|";
        int count = 0;
        if (target.equals(start)) return;

        Set<String> visited = new HashSet<>(Arrays.asList(deadEnds));
        Queue<String> queue = new ArrayDeque<>();
        visited.add(start);

        while(!queue.isEmpty()) {
            String next = queue.poll();
            if (next.equals(separator)) {
                count++;
                if (queue.peek() == null || queue.peek().equals(separator)) {
                    count = -1;
                    break;
                }
                queue.add(separator);
                continue;
            }
            List<String> nextWheels = getNextDigits(next);
            for (String wheel : nextWheels) {
                if (wheel.equals(target)) {
                    count++;
                    queue.clear();
                    break;
                }
                if (!visited.contains(wheel)) {
                    queue.offer(wheel);
                    visited.add(wheel);
                }
            }
        }

        println("count:" + count);
        assertTrue(true);
    }

    private List<String> getNextDigits(String wheel) {
        List<String> list = new ArrayList<>();
        for (int i=0;i<wheel.length();i++) {
            char digit = wheel.charAt(i);
            char digitInc = (char)((digit + 1 - '0') % 10 + '0');
            char digitDec = (char)((digit + 9 - '0') % 10 + '0'); // 9 = -1 + 10
            StringBuilder sb = new StringBuilder(wheel);
            sb.setCharAt(i, digitInc);
            list.add(sb.toString());
            sb.setCharAt(i, digitDec);
            list.add(sb.toString());
        }

        return list;
    }

    @Test
    void shortestMutation() {
        String startGene = "AAAAAAAA", endGene = "CCCCCCCC", SEPARATOR = "|";
        String[] bank = new String[]{"AAAAAAAA","AAAAAAAC","AAAAAACC","AAAAACCC","AAAACCCC","AACACCCC","ACCACCCC","ACCCCCCC","CCCCCCCA"};
        Set<String> bankSet = Set.of(bank);
        List<Character> geneCharList = List.of('A', 'C', 'G', 'T');

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.add(startGene);
        queue.add(SEPARATOR);
        visited.add(startGene);
        int count = 0;

        while (!queue.isEmpty()) {
            String gene = queue.poll();
            if (gene.equals(SEPARATOR)) {
                count++;
                if (queue.peek() == null) {
                    count = -1;
                    break;
                }
                queue.add(SEPARATOR);
                continue;
            }
            List<String> nextMutations = getNextMutations(gene, geneCharList);
            for (String nextMutation : nextMutations) {
                if (nextMutation.equals(endGene)) break;
                if (bankSet.contains(nextMutation) && !visited.contains(nextMutation)) {
                    queue.add(nextMutation);
                    visited.add(nextMutation);
                }
            }
        }

        println("count:" + count);
        assertTrue(true);
    }

    private List<String> getNextMutations(String gene, List<Character> geneCharList) {
        List<String> result = new ArrayList<>();
        for (int i=0; i<gene.length(); i++) {
            char geneChar = gene.charAt(i);
            StringBuilder sb = new StringBuilder(gene);
            for (char geneValidChar : geneCharList) {
                if (geneValidChar != sb.charAt(i)) {
                    sb.setCharAt(i, geneValidChar);
                    result.add(sb.toString());
                }
            }
        }
        return result;
    }
}
