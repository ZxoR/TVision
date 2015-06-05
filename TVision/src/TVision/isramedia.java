/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TVision;

import static TVision.isramedia.getListToTableModel;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.List;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ZxoR (Yonatan)
 */
public class isramedia {
    
    public static void loadChannelsListToTable(DefaultTableModel model) throws IOException {
        model.setRowCount(0);
        String sURL = "http://www.isramedia.net/tv";
        URL url = new URL(sURL);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.addRequestProperty(
                "Host", "www.isramedia.net");
        httpCon.addRequestProperty(
                "Connection", "close");
        httpCon.addRequestProperty(
                "Cache-Control", "max-age=0");
        httpCon.addRequestProperty(
                "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpCon.addRequestProperty(
                "User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
        httpCon.addRequestProperty(
                "Accept-Language", "en-US,en;q=0.8");
        HttpURLConnection.setFollowRedirects(
                false);
        httpCon.setInstanceFollowRedirects(
                false);
        httpCon.setDoOutput(
                true);
        httpCon.setUseCaches(
                true);
        httpCon.setRequestMethod(
                "GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream(), "windows-1255"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            a.append(inputLine);
        }

        in.close();

        httpCon.disconnect();
        String sourceall = a.toString();
        String expression = "<option value=\"http://www.isramedia.net/.*?/(\\d+)/.*?\">(.*?)</option>";
        Pattern p = Pattern.compile(expression);
        Matcher m = p.matcher(sourceall);
        while (m.find()) {
            model.addRow(new Object[]{m.group(2), m.group(1)});
        }
    }

    public static void getListToTableModel(String channelNameURL, DefaultTableModel model) throws IOException {
        model.setRowCount(0);
        String sURL = "http://www.isramedia.net/%D7%9C%D7%95%D7%97-%D7%A9%D7%99%D7%93%D7%95%D7%A8%D7%99%D7%9D/" + channelNameURL;
        URL url = new URL(sURL);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.addRequestProperty(
                "Host", "www.isramedia.net");
        httpCon.addRequestProperty(
                "Connection", "close");
        httpCon.addRequestProperty(
                "Cache-Control", "max-age=0");
        httpCon.addRequestProperty(
                "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpCon.addRequestProperty(
                "User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
        httpCon.addRequestProperty(
                "Accept-Language", "en-US,en;q=0.8");
        HttpURLConnection.setFollowRedirects(
                false);
        httpCon.setInstanceFollowRedirects(
                false);
        httpCon.setDoOutput(
                true);
        httpCon.setUseCaches(
                true);
        httpCon.setRequestMethod(
                "GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream(), "windows-1255"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            a.append(inputLine);
        }

        in.close();

        httpCon.disconnect();
        String sourceall = a.toString();
        String expression = "<tr class=\"(\\{class\\}|current)\"><td class=\"tvguidetime\"><time datetime=\"\\d+-\\d+-\\d+.\\d+:\\d+:\\d+\\+\\d+:\\d+\">(\\d+:\\d+)</time></td><td class=\"tvguideshowname\">(.*?)</td><td class=\"tvshowduration\">(\\d+:\\d+)</td><td class=\"tvshowgenre\">(.*?)</td></tr>";
        Pattern p = Pattern.compile(expression);
        Matcher m = p.matcher(sourceall);

        while (m.find()) {
            String showTime = m.group(1).equals("current") ? "* " + m.group(2) : m.group(2);
            model.addRow(new Object[]{m.group(5), m.group(4), m.group(3), showTime});
        }
    }
}
