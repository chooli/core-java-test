

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InnerClassTests {

    @Test
    void test() {
        Cat cat = new Tiger();

        System.out.println(Math.ceil(-82.4));
    }

    static class Cat {

        static final Cat instance = new Cat();
        long test = 12_32;

        String sound;

        public Cat() {
            sound = "myao";
        }
        public String originalSound() { return sound; }

        protected String shout() {
            return sound;
        }

    }

    static class Tiger extends Cat {

        @Override
        public String shout() {
            return sound;
        }

    }

}
