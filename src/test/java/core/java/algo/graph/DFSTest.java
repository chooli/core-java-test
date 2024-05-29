package core.java.algo.graph;

import core.java.CommonTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DFSTest extends CommonTest {

    private String target;
    private String start;

    @BeforeAll
    private void setUp() {
        target = "88";
        start = "000";
    }

    @Test
    void simpleSearch() {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        //visited.add(start);
        stack.add(start);
        dfs(stack, visited);
    }

    private void dfs(Stack<String> stack, Set<String> visited) {
        while (!stack.isEmpty()) {
            String val = stack.pop();
            if (visited.contains(val)) return;
            //visit the node
            println(val);

            visited.add(val);
            List<String> nextDigits = getNext(val);

            stack.addAll(nextDigits);
            dfs(stack, visited);
        }
    }

    private List<String> getNext(String root) {
        List<String> result = new ArrayList<>();
        char[] digits = root.toCharArray();
        for (int i=0;i<root.length();i++) {
            char digit = digits[i];
            char digitInc = (char)((digit + 1 - '0') % 10 + '0');
            char digitDec = (char)((digit + 9 - '0') % 10 + '0');
            result.add(root.substring(0, i) + digitInc + root.substring(i+1));
            result.add(root.substring(0, i) + digitDec + root.substring(i+1));
        }
        return result;
    }

}
