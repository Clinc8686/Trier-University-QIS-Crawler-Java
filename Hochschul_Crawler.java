import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Scanner;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

//Semesterdaten mit in der Registry abspeichern

public class Hochschul_Crawler {
    private static String username = "";
    private static String password = "";

    public static void main(String[] args) throws IOException, InterruptedException {
        Hochschul_Crawler hsc = new Hochschul_Crawler();
        checkOnUpdates();
        while(true) {
            LocalDateTime localdatetime = LocalDateTime.now();
            if (!(localdatetime.getHour() >= 0 && localdatetime.getHour() <= 5)) {
                hsc.getlogin();
                hsc.read_QIS();
            }
            Thread.sleep(900000);
        }
        //System.exit(0);
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
            e.printStackTrace();
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
            JOptionPane.showMessageDialog(new JFrame(), "Hier hat etwas nicht funktioniert!\nVielleicht sind deine Anmeldedaten falsch?", "Hochschul-Crawler",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        //System.out.println(grades.asNormalizedText());
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
                        if (preference.equals(mod_reg)) {
                            pref_exists = true;
                        }
                    }
                } catch (BackingStoreException backingStoreException) {

                }
                if (pref_exists == false) {
                    prefs.put(mod_reg, "1");
                    sendWinNotification("QIS-Benachrichtigung", "Für \"" + mod + "\" sind die Noten im QIS eingetragen!", "favicon_FHTRIER.jpg");
                }
            }
            mod = s;
        }
        reader.close();
    }
}
