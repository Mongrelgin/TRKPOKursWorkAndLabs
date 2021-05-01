package Labs.Lab_4;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> text = new ArrayList<>();
        text.add("Text 1");
        text.add("Text 2");
        text.add("Text 3");
        text.add("Text 4");

        System.out.println(text);

        new MyGenericClass<String>().swapSomething(text, 1, 3);

        System.out.println(text);
    }
}
