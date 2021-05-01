package DownloadAll_31;

public class Main {
    public static void main(String[] args) {
        PicLoaderThread thread = new PicLoaderThread();
        thread.start();
        WebsiteScanner websiteScanner = new WebsiteScanner();
        websiteScanner.scanWebsite();
    }
}
