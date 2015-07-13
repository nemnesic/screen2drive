package com.nemnesic;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TimerTask;

public abstract class DirWatcher extends TimerTask {
    String path;
    Map dir = new HashMap();

    public DirWatcher(String path) {
        this.path = path;
        File[] files = new File(path).listFiles();

        // transfer to the hashmap be used a reference and keep the lastModfied value
        for (File file : files) {
            dir.put(file, file.lastModified());
        }
    }

    public final void run() {
        HashSet checkedFiles = new HashSet();
        File[] files = new File(path).listFiles();

        // scan the files and check for addition
        for (File file : files) {
            Long current = (Long) dir.get(file);
            checkedFiles.add(file);
            if (current == null) {
                // new file
                dir.put(file, new Long(file.lastModified()));
                onChange(file, "add");
            }
        }


    }

    protected abstract void onChange(File file, String action);
}
