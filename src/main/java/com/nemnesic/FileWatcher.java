package com.nemnesic;

import com.google.api.services.drive.Drive;

import java.io.File;
import java.net.URLConnection;
import java.util.Date;
import java.util.Timer;

public class FileWatcher extends DirWatcher {
    Drive googleDriveService;
    Notification notification = new Notification();

    FileWatcher(String path) {
        super(path);
    }

    void manageApp(Drive googleDriveService) {
        this.googleDriveService = googleDriveService;

        Timer timer = new Timer();
        timer.schedule(this, new Date(), 1000);
    }


    @Override
    protected void onChange(File file, String action) {
        System.out.println("File " + file.getName() + " action: " + action + " mime: " + URLConnection.guessContentTypeFromName(file.getName()));
        notification.show("Please wait", file.getName() + " is uploading...", 1000);

        GoogleDriveUploader.insertFile(
                googleDriveService,
                file.getName(),
                file.getName(),
                null,
                URLConnection.guessContentTypeFromName(file.getName()),
                file);

        notification.show("Uploaded!", file.getName() + " has been copied to your clipboard.", 3000);
    }
}