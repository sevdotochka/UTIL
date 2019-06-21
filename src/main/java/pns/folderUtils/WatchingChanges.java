/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.folderUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uranium
 */
public class WatchingChanges {

    private boolean dirHaveChanged = false;

    public void lookingFor(String dirName) {

        System.out.println(" ####  BEGIN!");

        Path path = Paths.get(dirName);
        WatchService watchService = null;
        try {
            watchService = path.getFileSystem().newWatchService();
            path.register(watchService,
                      StandardWatchEventKinds.ENTRY_CREATE,
                      StandardWatchEventKinds.ENTRY_DELETE,
                      StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // Бесконечный цикл
        for (;;) {
            try {
                WatchKey key = null;

                key = watchService.take();

                // Итерации для каждого события
                for (WatchEvent event : key.pollEvents()) {
                    switch (event.kind().name()) {
                        case "OVERFLOW":
                            dirHaveChanged = false;
                            System.out.println("We lost some events");
                            break;
                        case "ENTRY_CREATE":
                            dirHaveChanged = true;
                            System.out.println("File " + event.context()
                                      + " is created!");
                            break;
                        case "ENTRY_MODIFY":
                            dirHaveChanged = true;
                            System.out.println("File " + event.context()
                                      + " is modified!");
                            break;
                        case "ENTRY_DELETE":
                            dirHaveChanged = true;
                            System.out.println("File " + event.context()
                                      + " is deleted!");
                            break;
                    }
                }
                // Сброс ключа важен для получения последующих уведомлений
                key.reset();
                Thread.sleep(1000 * 60 * 2);
            } catch (InterruptedException ex) {
                Logger.getLogger(WatchingChanges.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean isDirHaveChanged() {
        return dirHaveChanged;
    }
}
