package DownloadAll;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class Main {
    public static class ImageLoader {
        public static double minImageSize = 55.4; // KB

        public static void downloadImage(String webUrl) {
            try {
                String ext = webUrl.split("[.]+")[webUrl.split("[.]+").length - 1];
                String fileName = webUrl.split("[/]+")[webUrl.split("[/]+").length - 1];

                URL url = new URL(webUrl);
                URLConnection connection = url.openConnection();
                connection.connect();
                double fileLength = (double)connection.getContentLength();

                if ((fileLength / 1024) > minImageSize) {
                    InputStream input = new BufferedInputStream(url.openStream());
                    OutputStream output = new FileOutputStream("/tmp/downloadAll/" + fileName);
            
                    byte data[] = new byte[1024];
                    double total = 0;
                    int count;
                    System.out.print("Downloading " + webUrl + ": ");
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        System.out.printf("... %.2f%%", (total / fileLength) * 100 );
                        output.write(data, 0, count);
                    }
            
                    output.flush();
                    output.close();
                    input.close();
                    System.out.println();
                }
            } catch (Exception e) {
            }
        }
    }
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                System.out.println("Please enter url and min image size (KB)");
            } else if (args.length < 2) {
                System.out.println("Please add min image size (KB) to arguments");
            } else {
                String webUrl = args[0];
                ImageLoader.minImageSize = Double.parseDouble(args[1]);

                findAndLoadImages(webUrl);

                URL url = new URL(webUrl);
                URLConnection connection = url.openConnection();
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                HTMLEditorKit htmlKit = new HTMLEditorKit();
                HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
                htmlDoc.putProperty("IgnoreCharsetDirective", true);
                htmlKit.read(br, htmlDoc, 0);

                for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.A); iterator.isValid(); iterator.next()) {
                    AttributeSet attributes = iterator.getAttributes();
                    String hrefSrc = (String) attributes.getAttribute(HTML.Attribute.HREF);
                    String nextUrl = "";

                    if (hrefSrc.startsWith("/")) {
                        nextUrl = webUrl.substring(0, webUrl.indexOf("/", 8)) + hrefSrc;
                    } else if (hrefSrc.startsWith("http")) {
                        nextUrl = hrefSrc;
                    } else {
                        nextUrl = webUrl.substring(0, webUrl.lastIndexOf("/") + 1) + hrefSrc;
                    }
                    findAndLoadImages(nextUrl);
                }
            }
        } catch(Exception e){
        }
    }

    public static void findAndLoadImages (String webUrl) {
        try {
            URL url = new URL(webUrl);
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            HTMLEditorKit htmlKit = new HTMLEditorKit();
            HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
            htmlDoc.putProperty("IgnoreCharsetDirective", true);
            htmlKit.read(br, htmlDoc, 0);

            for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.IMG); iterator.isValid(); iterator.next()) {
                AttributeSet attributes = iterator.getAttributes();
                String imgSrc = (String) attributes.getAttribute(HTML.Attribute.SRC);
                String finalImageUrl = "";

                if (imgSrc.startsWith("/")) {
                    finalImageUrl = webUrl.substring(0, webUrl.indexOf("/", 8)) + imgSrc;
                } else if (imgSrc.startsWith("http")) {
                    finalImageUrl = imgSrc;
                } else {
                    finalImageUrl = webUrl.substring(0, webUrl.lastIndexOf("/") + 1) + imgSrc;
                }

                ImageLoader.downloadImage(finalImageUrl);
            }
        } catch(Exception e){
        }
    }
}