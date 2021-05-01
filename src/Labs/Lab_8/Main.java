package Labs.Lab_8;

import java.util.*;

public class Main {
    public static String removePairs(String text) {
        StringBuilder finalText = new StringBuilder();
        Set<Character> pairsCheck = new LinkedHashSet<>();
        char[] subStr = text.toCharArray();

        for (char c: subStr) {
            if (pairsCheck.contains(c)) {
                pairsCheck.remove(c);
            } else {
                pairsCheck.add(c);
            }
        }

        for (char c: pairsCheck) {
            finalText.append(c);
        }
        return finalText.toString();
    }
}
