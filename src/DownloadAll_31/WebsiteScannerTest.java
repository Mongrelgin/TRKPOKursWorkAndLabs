package DownloadAll_31;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class WebsiteScannerTest {

    @Test
    void removeLastLatter() {
        String testWord = "Sanyaa";
        String checkWord = "Sanya";

        Assertions.assertEquals(WebsiteScanner.removeLastLatter(testWord), checkWord);
    }
}