package com.nemnesic;

import ch.swingfx.twinkle.NotificationBuilder;
import ch.swingfx.twinkle.event.NotificationEvent;
import ch.swingfx.twinkle.event.NotificationEventAdapter;
import ch.swingfx.twinkle.style.INotificationStyle;
import ch.swingfx.twinkle.style.theme.DarkDefaultNotification;
import ch.swingfx.twinkle.window.Positions;
import com.google.api.services.drive.Drive;

import javax.swing.*;
import java.io.IOException;

public class Boss {
    static Drive googleDriveService;

    public static void main(String[] args) {
        authorizeGoogle();
        new FileWatcher("/tmp/").manageApp(googleDriveService);
    }

    public static void authorizeGoogle() {
        try {
            googleDriveService = GoogleDriveUploader.getDriveService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
