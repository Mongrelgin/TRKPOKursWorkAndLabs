package Labs.Lab_4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MyGenericClassTest {

    @Test
    void swapSomething() {
        ArrayList<String> textTest = new ArrayList<>();
        ArrayList<String> textCheck = new ArrayList<>();
        textTest.add("Hi");
        textTest.add("Hello");
        textCheck.add("Hello");
        textCheck.add("Hi");
        new MyGenericClass<String>().swapSomething(textTest, 0, 1);
        Assertions.assertEquals(textTest, textCheck);
    }
}