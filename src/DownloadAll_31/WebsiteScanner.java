package DownloadAll_31;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class WebsiteScanner {
    private Properties property;
    private URL url;

    WebsiteScanner() {
        property = new Properties();
        try {
            property.load(new FileInputStream("src/DownloadAll31v/config.properties"));
            url = new URL(property.getProperty("url") + "mainPage.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printHTMLCode() {
        try (InputStream inputStream = url.openStream()) {
            byte[] buffer = inputStream.readAllBytes();
            String str = new String(buffer);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startNewPicLoaderThread(String page) {
        try {
            new PicLoaderThread(new URL(property.getProperty("url") + page)).start();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void scanWebsite() {
        String scanText = "";
        boolean checkTagScannerFlag = false;
        boolean checkTextScannerFlag = false;

        try (InputStream inputStream = url.openStream()) {
            byte[] buffer = inputStream.readAllBytes();
            String str = new String(buffer);
            char[] subStr = str.toCharArray();
            for (char c: subStr) {

                if (c == '<')
                    checkTagScannerFlag = true;

                if (checkTagScannerFlag) {

                    scanText += c;

                    if (checkTextScannerFlag) {
                        if (c == '\"') {
                            startNewPicLoaderThread(removeLastLatter(scanText));
                            scanText = "";
                            checkTagScannerFlag = false;
                            checkTextScannerFlag = false;
                        }
                    } else {
                        if (scanText.equals("<a href=\"")) {
                            scanText = "";
                            checkTextScannerFlag = true;
                        }

                    }
                }

                if (c == '>') {
                    checkTagScannerFlag = false;
                    scanText = "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String removeLastLatter(String word) {
        String returnWord = "";
        char[] subStr = word.toCharArray();
        for (char c: subStr ) {
            if (returnWord.length() + 1 == word.length())
                break;
            returnWord += c;
        }
        return returnWord;
    }
}
