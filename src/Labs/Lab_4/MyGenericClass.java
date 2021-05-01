package Labs.Lab_4;

import java.util.ArrayList;

public class MyGenericClass <T> {
    public void swapSomething(ArrayList<T> list, int firstIndex, int secondIndex) {
        T swapper = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, swapper);
    }
}
