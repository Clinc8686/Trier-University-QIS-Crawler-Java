import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.crypto.Cipher;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Properties;
import java.util.Scanner;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Hochschul_Crawler {
    private static String username = "";
    private static String password = "";
    private static SecretKeySpec secretKey;
    private static String secret_Key = "";
    private static byte[] key;

    public static void main(String[] args) throws IOException, InterruptedException {
        Hochschul_Crawler hsc = new Hochschul_Crawler();
        hsc.getlogin();
        secret_Key = read_secret_key();
        password = decrypt(password, secret_Key);
        add_Shutdown_Hook();
        checkOnUpdates();
        while(true) {
            LocalDateTime localdatetime = LocalDateTime.now();
            if (!(localdatetime.getHour() >= 0 && localdatetime.getHour() <= 5)) {

                hsc.read_QIS();
            }
            Thread.sleep(900000);
        }
        //System.exit(0);
    }

    private static void add_Shutdown_Hook() {
        Runtime.getRuntime().addShutdownHook(
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            PrintWriter key_writer = new PrintWriter(System.getProperty("user.dir") + "\\tmp_key.txt");
                            key_writer.println(secret_Key);
                            key_writer.close();
                        } catch (Exception e) {
                        }
                    }
                }));
    }

    private static String read_secret_key() {
        String secret_key = "";
        try {
            File key_reader = null;
            Scanner key_scanner = null;
            String path = "";

            path = Hochschul_Crawler.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(0, path.lastIndexOf("/") + 1)+"\\tmp_key.txt";
            path = URLDecoder.decode(path, "UTF-8");

            key_reader = new File(path);
            key_scanner = new Scanner(key_reader);
            secret_key = key_scanner.nextLine();
            key_scanner.close();

            if(!(key_reader.delete())) {
                throw new IllegalAccessException("Konnte Datei nicht löschen");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Hier hat etwas nicht funktioniert!\nBitte Installiere das Programm über den Installer noch mal neu. Fehler: ReadSeFile", "Hochschul-Crawler",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return secret_key;
    }

    private static void checkOnUpdates() {
        try {
            URL version_website = new URL ("https://mariolampert.de/HS-Bot/newest_version.txt");
            String newest_Version = new Scanner(version_website.openStream()).next();
            Preferences prefs = Preferences.userRoot().node("Hochschul-Scraper");
            String reg_Version = prefs.get("newest_Version", "");
            if(!(reg_Version.equals(newest_Version))) {
                sendWinNotification("Hochschul-Crawler", "Es ist ein Update verfügbar!\nÜber den Installer kannst du das neue Update installieren.", "");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Fehler!\nIch konnte keine Update-Windows Benachrichtigung senden!.", "Hochschul-Crawler",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public static void sendWinNotification(String title, String subtitle, String pathToIcon) {
        SystemTray mainTray = SystemTray.getSystemTray();
        Image trayIconImage = Toolkit.getDefaultToolkit().getImage(pathToIcon);
        TrayIcon mainTrayIcon = new TrayIcon(trayIconImage);
        mainTrayIcon.setImageAutoSize(true);
        try {
            mainTray.add(mainTrayIcon);
            mainTrayIcon.displayMessage(title,  subtitle, TrayIcon.MessageType.NONE);
        }
        catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), "Fehler!\nIch konnte keine Windows Benachrichtigung senden!.", "Hochschul-Crawler",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public void getlogin() {
        Preferences prefs = Preferences.userRoot().node("Hochschul-Scraper");
        username = prefs.get("QIS_name", "");
        password = prefs.get("QIS_pwd", "");
    }

    public static void read_QIS() throws IOException {
        HtmlPage grades = null;
        try {
            WebClient webClient = new WebClient();
            webClient.getOptions().setTimeout(10000);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setUseInsecureSSL(true);
            webClient.getOptions().setRedirectEnabled(true);

            HtmlPage hs_Login = webClient.getPage("https://qis.hochschule-trier.de/qisserver/rds?state=user&type=0&category=menu.browse&startpage=portal.vm");
            HtmlForm hs_login_form = hs_Login.getFormByName("login");
            HtmlTextInput hs_login_username = hs_login_form.getInputByName("j_username");
            HtmlPasswordInput hs_login_password = hs_login_form.getInputByName("j_password");

            hs_login_username.setValueAttribute(username);
            hs_login_password.setValueAttribute(password);

            HtmlButton button = hs_Login.getFirstByXPath("//button[@type='submit']");
            HtmlPage qis_login_page = button.click();
            HtmlForm qis_login_form = qis_login_page.getFormByName("loginform");
            HtmlTextInput qis_login_username = qis_login_form.getInputByName("asdf");
            HtmlPasswordInput qis_login_password = qis_login_form.getInputByName("fdsa");

            qis_login_username.setValueAttribute(username);
            qis_login_password.setValueAttribute(password);
            HtmlInput qis_login_button = qis_login_form.getInputByName("submit");
            HtmlPage qis_homepage = qis_login_button.click();
            //System.out.println(qis_homepage.asText());

            HtmlAnchor a = qis_homepage.getAnchorByText("Prüfungsverwaltung");
            HtmlPage b = a.click();
            HtmlAnchor c = b.getAnchorByText("Notenspiegel");
            HtmlPage d = c.click();
            HtmlAnchor e = d.getAnchorByText("Abschluss Bachelor of Science");
            HtmlPage f = e.click();
            HtmlAnchor g = f.getAnchorByText("Informatik - Digitale Medien und Spiele (PO-Version 2019)");
            grades = g.click();
            webClient.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Hier hat etwas nicht funktioniert!\nKonnte keine Daten aus dem QIS abrufen!", "Hochschul-Crawler",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        String grade_page_s = grades.asNormalizedText();
        BufferedReader reader = new BufferedReader(new StringReader(grade_page_s));

        int year = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yy")));
        String semester = "";
        if (LocalDate.now().getMonthValue() >= 10 || LocalDate.now().getMonthValue() <= 4) {
            if(LocalDate.now().getMonthValue() >= 10 && LocalDate.now().getMonthValue() <= 12) {
                semester = "WiSe " + year + "/" + (year+1);
            } else {
                semester = "WiSe " + year + "/" + (year-1);
            }
        } else {
            semester = "SoSe " + year;
        }
        int counter = 1;
        String s;
        String mod = "";
        Preferences prefs = Preferences.userRoot().node("Hochschul-Scraper");
        while ((s = reader.readLine()) != null) {

            if(((s.contains("BE") || s.contains("NB") || s.contains("NE")) && s.contains(semester)) && (!(mod.contains("PV") || mod.contains("Studienleistung")))) {
                mod = mod.replace("\t", " ");
                mod = mod.replace("  ", " ");

                s = s.replace("\t", " ");
                s = s.replace("  ", " ");
                String[] s_splitted = s.split("\\s+");

                String mod_reg = mod;
                mod_reg = mod_reg.replace("ä", "ae");
                mod_reg = mod_reg.replace("ö", "oe");
                mod_reg = mod_reg.replace("ü", "ue");
                mod_reg = mod_reg.replace("Ä", "AE");
                mod_reg = mod_reg.replace("Ö", "OE");
                mod_reg = mod_reg.replace("Ü", "UE");

                boolean pref_exists = false;
                try {
                    for (String preference : prefs.keys()) {
                        if (preference.equals(mod_reg + "|" + s_splitted[1] + " " + s_splitted[2])) {
                            pref_exists = true;
                        }
                    }
                } catch (BackingStoreException backingStoreException) {

                }

                if (pref_exists == false) {
                    prefs.put(mod_reg + "|" + s_splitted[1] + " " + s_splitted[2], "1");
                    sendWinNotification("QIS-Benachrichtigung", "Für \"" + mod + "\" sind die Noten im QIS eingetragen!", "favicon_FHTRIER.jpg");

                    try {
                        String jsonInputString = "{ \"modul\": \"" + mod_reg + "|" + s_splitted[1] + " " + s_splitted[2] + "\" }";
                        System.out.println("sende jetzt: " + jsonInputString);
                        StringEntity entity = new StringEntity(jsonInputString, ContentType.APPLICATION_FORM_URLENCODED);
                        HttpClient httpClient = HttpClientBuilder.create().build();
                        HttpPost request = new HttpPost("https://mariolampert.de/HS-Bot/API/index.php");
                        request.setEntity(entity);
                        HttpResponse response = httpClient.execute(request);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(new JFrame(), "Fehler!\nIch konnte keine Nachricht an den Server senden!.", "Hochschul-Crawler",
                                JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                }
            }
            mod = s;
        }
        reader.close();
    }

    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), "Hier hat etwas nicht funktioniert!\nBitte Installiere das Programm über den Installer noch mal neu. Fehler: NSAE", "Hochschul-Crawler",
                    JOptionPane.ERROR_MESSAGE);
        }
        catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), "Hier hat etwas nicht funktioniert!\nBitte Installiere das Programm über den Installer noch mal neu. Fehler: UEE", "Hochschul-Crawler",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(new JFrame(), "Hier hat etwas nicht funktioniert!\nBitte Installiere das Programm über den Installer noch mal neu. Fehler: decryption", "Hochschul-Crawler",
                    JOptionPane.ERROR_MESSAGE);
            //System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}
