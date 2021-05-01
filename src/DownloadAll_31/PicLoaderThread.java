package DownloadAll_31;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class PicLoaderThread extends Thread {
    private Integer picCounter = 0;
    private Properties property;
    private URL url;

    PicLoaderThread() {
        property = new Properties();
        try {
            property.load(new FileInputStream("src/DownloadAll31v/config.properties"));
            url = new URL(property.getProperty("url") + "mainPage.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    PicLoaderThread(URL url) {
        property = new Properties();
        try {
            property.load(new FileInputStream("src/DownloadAll31v/config.properties"));
            this.url = url;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
         scanPics();
    }

    private void loadPic(String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedImage img = ImageIO.read(url);
            File file = new File(property.getProperty("pathToFolder") + "/" + ++picCounter + getName() + ".jpg");
            //System.out.println(conn.getContentLength());
            if (conn.getContentLength() >= Integer.parseInt(property.getProperty("picSize")))
                ImageIO.write(img,"jpg",file);
            //System.out.println(file.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scanPics() {
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
                            loadPic(property.getProperty("url") + WebsiteScanner.removeLastLatter(scanText));
                            scanText = "";
                            checkTagScannerFlag = false;
                            checkTextScannerFlag = false;
                        }
                    } else {
                        if (scanText.equals("<img src=\"")) {
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
}
