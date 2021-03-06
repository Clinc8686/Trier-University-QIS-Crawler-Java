/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hochschul_installer;

import javax.swing.*;
import java.util.prefs.Preferences;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.io.InputStream;
import javax.swing.JFileChooser;
import java.io.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Mario
 */
public class Hochschul_Installer extends javax.swing.JFrame {
    /**
     * Creates new form Hochschul_Installer
     */
    public Hochschul_Installer() {
        initComponents();
    }
    String newest_Version = "";
    String absolute_Filedirectory = System.getProperty("user.home");
    private static byte[] key;
    private static SecretKeySpec secretKey;
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        Left_Panel = new javax.swing.JPanel();
        uninstall = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        statusbar = new javax.swing.JTextArea();
        Statusbar_Label = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Headline_Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Right_Panel = new javax.swing.JPanel();
        Center_Panel = new javax.swing.JPanel();
        Center_Center_Panel = new javax.swing.JPanel();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        safe_install = new javax.swing.JButton();
        QIS_Password = new javax.swing.JLabel();
        QIS_Username = new javax.swing.JLabel();
        change_directory = new javax.swing.JButton();
        InstallDirectory = new javax.swing.JLabel();
        auth_key = new javax.swing.JPasswordField();
        auth_key2 = new javax.swing.JLabel();
        QIS_Password2 = new javax.swing.JLabel();
        auth_key1 = new javax.swing.JLabel();
        RedText_Line1 = new java.awt.Label();
        RedText_Line2 = new java.awt.Label();
        RedText_Line3 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Installation: Hochschule Trier - QIS-Bot");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Left_Panel.setBackground(new java.awt.Color(255, 128, 35));

        uninstall.setBackground(new java.awt.Color(255, 255, 255));
        uninstall.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        uninstall.setForeground(new java.awt.Color(255, 102, 0));
        uninstall.setText("uninstall");
        uninstall.setToolTipText("Alle Dateien deinstallieren");
        uninstall.setBorder(null);
        uninstall.setBorderPainted(false);
        uninstall.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        uninstall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uninstallActionPerformed(evt);
            }
        });

        statusbar.setEditable(false);
        statusbar.setColumns(20);
        statusbar.setLineWrap(true);
        statusbar.setRows(5);
        statusbar.setToolTipText("Status Anzeige f??r durchgef??hrte Aktionen");
        jScrollPane2.setViewportView(statusbar);

        Statusbar_Label.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        Statusbar_Label.setForeground(new java.awt.Color(255, 255, 255));
        Statusbar_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Statusbar_Label.setText("Statusbar");

        update.setBackground(new java.awt.Color(255, 255, 255));
        update.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        update.setForeground(new java.awt.Color(255, 102, 0));
        update.setText("update");
        update.setToolTipText("Pr??fe auf Updates & installiere diese gegebenenfalls");
        update.setBorder(null);
        update.setBorderPainted(false);
        update.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        jLabel3.setText("?? Mario Lampert");
        jLabel3.setToolTipText("I bims!");

        javax.swing.GroupLayout Left_PanelLayout = new javax.swing.GroupLayout(Left_Panel);
        Left_Panel.setLayout(Left_PanelLayout);
        Left_PanelLayout.setHorizontalGroup(
            Left_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Left_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Left_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(uninstall, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(Statusbar_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(update, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(Left_PanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Left_PanelLayout.setVerticalGroup(
            Left_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Left_PanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uninstall, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Statusbar_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        getContentPane().add(Left_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 500));

        Headline_Panel.setBackground(new java.awt.Color(255, 153, 51));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Installation: Hochschule Trier - QIS-Bot");

        javax.swing.GroupLayout Headline_PanelLayout = new javax.swing.GroupLayout(Headline_Panel);
        Headline_Panel.setLayout(Headline_PanelLayout);
        Headline_PanelLayout.setHorizontalGroup(
            Headline_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Headline_PanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        Headline_PanelLayout.setVerticalGroup(
            Headline_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Headline_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(Headline_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 650, 70));

        Right_Panel.setBackground(new java.awt.Color(255, 164, 35));

        javax.swing.GroupLayout Right_PanelLayout = new javax.swing.GroupLayout(Right_Panel);
        Right_Panel.setLayout(Right_PanelLayout);
        Right_PanelLayout.setHorizontalGroup(
            Right_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Right_PanelLayout.setVerticalGroup(
            Right_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        getContentPane().add(Right_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, 30, 430));

        Center_Panel.setBackground(new java.awt.Color(255, 204, 153));

        Center_Center_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Center_Center_Panel.setPreferredSize(new java.awt.Dimension(500, 289));

        username.setBackground(new java.awt.Color(233, 233, 233));
        username.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        username.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        username.setToolTipText("Dein QIS-Username von der Hochschule");
        username.setBorder(null);
        username.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        password.setBackground(new java.awt.Color(233, 233, 233));
        password.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        password.setToolTipText("Dein QIS-Passwort von der Hochschule");
        password.setBorder(null);

        safe_install.setBackground(new java.awt.Color(230, 133, 36));
        safe_install.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        safe_install.setForeground(new java.awt.Color(255, 255, 255));
        safe_install.setText("download and install");
        safe_install.setToolTipText("Installiere den Crawler im gew??hlten Verzeichnis");
        safe_install.setBorder(null);
        safe_install.setBorderPainted(false);
        safe_install.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        safe_install.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                safe_installActionPerformed(evt);
            }
        });

        QIS_Password.setBackground(new java.awt.Color(255, 255, 255));
        QIS_Password.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        QIS_Password.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        QIS_Password.setText("QIS-Passwort:");

        QIS_Username.setBackground(new java.awt.Color(255, 255, 255));
        QIS_Username.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        QIS_Username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        QIS_Username.setText("QIS-Username:");

        change_directory.setBackground(new java.awt.Color(230, 133, 36));
        change_directory.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        change_directory.setForeground(new java.awt.Color(255, 255, 255));
        change_directory.setText("change installation directory");
        change_directory.setName(""); // NOI18N
        change_directory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                change_directoryActionPerformed(evt);
            }
        });

        InstallDirectory.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        InstallDirectory.setText(System.getProperty("user.home"));
        InstallDirectory.setToolTipText("Dein aktuell gew??hltes Installations- und Update Verzeichnis");
        InstallDirectory.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        auth_key.setBackground(new java.awt.Color(233, 233, 233));
        auth_key.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        auth_key.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        auth_key.setToolTipText("Ein neuer, von dir ausgedachter, Authentifizierungs Schl??ssel");
        auth_key.setBorder(null);

        auth_key2.setBackground(new java.awt.Color(255, 255, 255));
        auth_key2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        auth_key2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        auth_key2.setText("fizierungs Schl??ssel:");

        QIS_Password2.setBackground(new java.awt.Color(255, 255, 255));
        QIS_Password2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        QIS_Password2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        auth_key1.setBackground(new java.awt.Color(255, 255, 255));
        auth_key1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        auth_key1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        auth_key1.setText("Ein neuer Authenti-");

        javax.swing.GroupLayout Center_Center_PanelLayout = new javax.swing.GroupLayout(Center_Center_Panel);
        Center_Center_Panel.setLayout(Center_Center_PanelLayout);
        Center_Center_PanelLayout.setHorizontalGroup(
            Center_Center_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Center_Center_PanelLayout.createSequentialGroup()
                .addGroup(Center_Center_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Center_Center_PanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(Center_Center_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(QIS_Password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(QIS_Username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Center_Center_PanelLayout.createSequentialGroup()
                                .addComponent(QIS_Password2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(161, 161, 161))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Center_Center_PanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(auth_key1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(Center_Center_PanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(auth_key2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Center_Center_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(auth_key, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
            .addGroup(Center_Center_PanelLayout.createSequentialGroup()
                .addGroup(Center_Center_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Center_Center_PanelLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(safe_install, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Center_Center_PanelLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(InstallDirectory, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Center_Center_PanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(change_directory)
                .addGap(157, 157, 157))
        );
        Center_Center_PanelLayout.setVerticalGroup(
            Center_Center_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Center_Center_PanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(InstallDirectory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(change_directory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Center_Center_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QIS_Username))
                .addGap(18, 18, 18)
                .addGroup(Center_Center_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QIS_Password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(Center_Center_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(auth_key, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Center_Center_PanelLayout.createSequentialGroup()
                        .addComponent(auth_key1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(auth_key2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QIS_Password2)))
                .addGap(21, 21, 21)
                .addComponent(safe_install, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        RedText_Line1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        RedText_Line1.setForeground(new java.awt.Color(255, 0, 0));
        RedText_Line1.setText("Sobald du auf den oberen Button \"download and install\" klickst, wird der Crawler");

        RedText_Line2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        RedText_Line2.setForeground(new java.awt.Color(255, 0, 0));
        RedText_Line2.setText("im oben gew??hlten Verzeichnis installiert.");

        RedText_Line3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        RedText_Line3.setForeground(new java.awt.Color(255, 0, 0));
        RedText_Line3.setText("Du musst beim Updaten auch das Installationsverzeichnis mit angeben!");

        javax.swing.GroupLayout Center_PanelLayout = new javax.swing.GroupLayout(Center_Panel);
        Center_Panel.setLayout(Center_PanelLayout);
        Center_PanelLayout.setHorizontalGroup(
            Center_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Center_PanelLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(Center_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(RedText_Line3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Center_Center_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addComponent(RedText_Line1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RedText_Line2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(59, 59, 59))
        );
        Center_PanelLayout.setVerticalGroup(
            Center_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Center_PanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Center_Center_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(RedText_Line1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(RedText_Line2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(RedText_Line3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );

        getContentPane().add(Center_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 620, 430));

        pack();
    }// </editor-fold>                        

    private void uninstallActionPerformed(java.awt.event.ActionEvent evt) {                                          
        try {
            Preferences prefs = Preferences.userRoot().node("Hochschul-Scraper");
            if(prefs.get("newest_Version", "").isEmpty() || prefs.get("QIS_name", "").isEmpty() || prefs.get("QIS_pwd", "").isEmpty()) {
                statusbar.append("Nix zum deinstallieren!\nDer Crawler war noch\nnie installiert!\n");
                statusbar.update(statusbar.getGraphics());
                throw new Exception("UninstallException");
            }
            
            statusbar.append("Starte deinstallation...\n");
                statusbar.update(statusbar.getGraphics());
            
            for(String preference : prefs.keys()) {
                prefs.remove(preference);
            }
            
            deleteHKEY();
            executeCommand("REG DELETE HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run /v Hochschul-Bot");
            try {
                Thread.sleep(500);
                executeCommand("Ja");
                Thread.sleep(500);
            } catch (Exception e) {
                 throw new Exception("Commandline Error");
            } finally {
                executeCommand("exit");
            }
            //Runtime.getRuntime().exit(0);           
            statusbar.append("Erfolgreich deinstalliert. \nDu kannst jetzt die .jar\nDateien l??schen.\n");
            statusbar.update(statusbar.getGraphics());
        } catch (Exception e) {
            statusbar.append("Deinstallation \nfehlgeschlagen! \n");
            statusbar.update(statusbar.getGraphics());
        }
    }                                         

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {                                       
        statusbar.append("Pr??fe auf Updates... \n");
        statusbar.update(statusbar.getGraphics());
        
        try {
            URL version_website = new URL ("https://mariolampert.de/HS-Bot/newest_version.txt");
            String newest_Version = new Scanner(version_website.openStream()).next();
            Preferences prefs = Preferences.userRoot().node("Hochschul-Scraper");
            String reg_Version = prefs.get("newest_Version", "");
            if(reg_Version.equals(newest_Version)) {
                statusbar.append("Es sind keine neuen\nUpdates verf??gbar.\n");
                statusbar.update(statusbar.getGraphics());
                
            } else if(prefs.get("newest_Version", "").isEmpty() || prefs.get("QIS_name", "").isEmpty() || prefs.get("QIS_pwd", "").isEmpty()) {
                statusbar.append("Nix zum updaten!\nDer Crawler war noch\nnie installiert!\n");
            } else {
                newest_Version = new Scanner(version_website.openStream()).next();
          
                deleteHKEY();
                Process p = Runtime.getRuntime().exec("REG ADD HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run /v Hochschul-Bot /d \"\\\""+absolute_Filedirectory+"\\"+newest_Version + "\"\\\"");
                prefs.put("newest_Version", newest_Version);
                
                statusbar.append("Neuste Updates\nwurden installiert.\nNach dem n??chsten\nNeustart funktionieren\ndiese.\n");
                statusbar.update(statusbar.getGraphics());
            }

        } catch (Exception e) {
            statusbar.append("Konnte nicht auf Updates\nPr??fen\n");
            statusbar.update(statusbar.getGraphics());
        }
    }                                      

    private void change_directoryActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.seperator")));
            chooser.showOpenDialog(null);
            absolute_Filedirectory = chooser.getSelectedFile().getAbsolutePath();
            InstallDirectory.setText(absolute_Filedirectory);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Fehler!\nKonnte das Verzeichnis nicht ??ndern!.", "Hochschul-Crawler",
                    JOptionPane.ERROR_MESSAGE);
        }

    }                                                

    private void safe_installActionPerformed(java.awt.event.ActionEvent evt) {                                             
        try {
            if(username.getText().isEmpty() || password.getText().isEmpty()) {
                statusbar.append("Die Felder d??rfen\nnicht leer sein!\n");
                statusbar.update(statusbar.getGraphics());
                throw new Exception("InstallationException");
            }

            try {
                Preferences prefs = Preferences.userRoot().node("Hochschul-Scraper");
                prefs.put("QIS_name", username.getText());
                //prefs.put("QIS_pwd", password.getText());
                
                String tmp_pswd = encrypt(password.getText(), auth_key.getText());
                prefs.put("QIS_pwd", tmp_pswd);
                
                PrintWriter key_writer = new PrintWriter(absolute_Filedirectory+"\\tmp_key.txt");
                key_writer.println(auth_key.getText());
                key_writer.close();
            } catch (Exception e) {
                statusbar.append("Installation \nfehlgeschlagen! \nKonnte Daten nicht speichern!");
                statusbar.update(statusbar.getGraphics());
                throw new Exception("InstallationException");
            }
            statusbar.append("Starte Download... \n");
            statusbar.update(statusbar.getGraphics());

            try {
                URL version_website = new URL ("https://mariolampert.de/HS-Bot/newest_version.txt");
                newest_Version = new Scanner(version_website.openStream()).next();
                Preferences prefs = Preferences.userRoot().node("Hochschul-Scraper");
                prefs.put("newest_Version", newest_Version);

                URL website = new URL("https://mariolampert.de/HS-Bot/"+newest_Version);
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());

                FileOutputStream fos = new FileOutputStream(absolute_Filedirectory + "/"+newest_Version);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            } catch (Exception e) {
                statusbar.append("Installation \nfehlgeschlagen! \nKonnte n??tige Dateien \nnicht downloaden!\n");
                statusbar.update(statusbar.getGraphics());
                throw new Exception("InstallationException");
            }
            try {
                deleteHKEY();
                Process p = Runtime.getRuntime().exec("REG ADD HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run /v Hochschul-Bot /d \"\\\""+absolute_Filedirectory+"\\"+newest_Version + "\"\\\"");
            } catch (Exception e) {
                statusbar.append("Installation \nfehlgeschlagen! \nKonnte Autostart nicht hinzuf??gen!");
                statusbar.update(statusbar.getGraphics());
                throw new Exception("InstallationException");
            }
            statusbar.append("Erfolgreich installiert!\nDer Crawler l??uft.\n");
            statusbar.update(statusbar.getGraphics());

            ProcessBuilder pb = new ProcessBuilder("java", "-jar", absolute_Filedirectory + "/"+newest_Version);
            Process p = pb.start();
        } catch (Exception e) {
            statusbar.append("Probier es noch ein mal!\n");
            statusbar.update(statusbar.getGraphics());
        }
    }                                            

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Hochschul_Installer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Hochschul_Installer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Hochschul_Installer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Hochschul_Installer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
         
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hochschul_Installer().setVisible(true);
            }
        });
    }
    
    private BufferedWriter p_stdin;
    public void deleteHKEY() {
        // init shell
        ProcessBuilder builder = new ProcessBuilder("C:/Windows/System32/cmd.exe");
        Process p = null;
        try {
            p = builder.start();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Registryfehler! REGx1", "Hochschul-Crawler",
                    JOptionPane.ERROR_MESSAGE);
        }

        p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

        executeCommand("REG DELETE HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run /v Hochschul-Bot");
        try {
            Thread.sleep(100);
            executeCommand("Ja");
            Thread.sleep(100);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Registryfehler! REGx2", "Hochschul-Crawler",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            executeCommand("exit");
        }
        
        Scanner s = new Scanner(p.getInputStream());
        while (s.hasNext()) {
            s.next();
        }
        s.close();
    }
    
    private void executeCommand(String command) {
        try {
            p_stdin.write(command);
            p_stdin.newLine();
            p_stdin.flush();
        } catch (IOException e) {
            //JOptionPane.showMessageDialog(new JFrame(), "Registryfehler! REGx3", "Hochschul-Crawler",
              //      JOptionPane.ERROR_MESSAGE);
        }
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
            JOptionPane.showMessageDialog(new JFrame(), "Registryfehler! PWDx1.", "Hochschul-Crawler",
                    JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Registryfehler! PWDx2.", "Hochschul-Crawler",
                    JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace();
        }
    }
 
    public static String encrypt(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(new JFrame(), "Registryfehler! PWDx3.", "Hochschul-Crawler",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    
    // Variables declaration - do not modify                     
    private javax.swing.JPanel Center_Center_Panel;
    private javax.swing.JPanel Center_Panel;
    private javax.swing.JPanel Headline_Panel;
    private javax.swing.JLabel InstallDirectory;
    private javax.swing.JPanel Left_Panel;
    private javax.swing.JLabel QIS_Password;
    private javax.swing.JLabel QIS_Password2;
    private javax.swing.JLabel QIS_Username;
    private java.awt.Label RedText_Line1;
    private java.awt.Label RedText_Line2;
    private java.awt.Label RedText_Line3;
    private javax.swing.JPanel Right_Panel;
    private javax.swing.JLabel Statusbar_Label;
    private javax.swing.JPasswordField auth_key;
    private javax.swing.JLabel auth_key1;
    private javax.swing.JLabel auth_key2;
    private javax.swing.JButton change_directory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton safe_install;
    private javax.swing.JTextArea statusbar;
    private javax.swing.JButton uninstall;
    private javax.swing.JButton update;
    private javax.swing.JTextField username;
    // End of variables declaration                   
    private String statusbar_text = "";
}
