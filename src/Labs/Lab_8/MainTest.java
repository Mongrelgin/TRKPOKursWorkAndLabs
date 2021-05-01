package Labs.Lab_8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void removePairs() {
        String testString = "abaacdezecdf";
        String checkString = "bazf";

        testString = Main.removePairs(testString);

        Assertions.assertEquals(testString, checkString);
    }
}