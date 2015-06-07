package com.epam.mentoring.lessfour.task8;

import com.epam.mentoring.lessfour.task6.util.Sleeper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Aliaksei Vihuro <aliaksei_vihuro@epam.com>
 */
public class Main {

    private static String EXIT_COMMAND = "!quit";
    private static String CANCEL_COMMAND = "!cancel";
    private static boolean running = false;

    public static void main(String[] args) {

        System.out.println("Type path to scan or \"!quit\" to quit");
        Thread scannedThread = new Thread();
        try (final Scanner scanner = new Scanner(System.in)) {
            while (true) {
                if (running) {
                    System.out.println("Type \"!cancel\" for cancel scan");
                } else {
                    System.out.println("Path:");
                }
                final String input = scanner.nextLine();
                if (EXIT_COMMAND.equalsIgnoreCase(input)) {
                    break;
                }
                if (CANCEL_COMMAND.equals(input) && scannedThread.isAlive()) {
                    scannedThread.interrupt();
                }
                scannedThread = new Thread(() -> scanFolder(input));
                scannedThread.start();
            }
        }
    }

    private static void scanFolder(final String path) {

        running = true;
        try {
            final ExecutorService executor = Executors.newFixedThreadPool(3);
            final Future<Long> filesCount = executor.submit(() -> countFiles(path));
            final Future<Long> foldersCount = executor.submit(() -> countFolders(path));
            final Future<Long> size = executor.submit(() -> getSizeOfFiles(path));
            System.out.println("filesCount = " + filesCount.get());
            System.out.println("foldersCount = " + foldersCount.get());
            System.out.println("size = " + size.get());
            while (true) {
                if (filesCount.isDone() && foldersCount.isDone() && size.isDone()) {
                    executor.shutdown();
                    break;
                }
                System.out.println("*");
                Sleeper.sleep(1000L);
            }
        } catch (Exception ex) {
            System.out.println("Can't scan this folder");
        } finally {
            running = false;
        }
    }

    private static Long countFolders(final String path) throws IOException {
        return Files
                .walk(Paths.get(path))
                .filter(file -> Files.isDirectory(file))
                .count();
    }

    private static Long countFiles(final String path) throws IOException {
        return Files
                .walk(Paths.get(path))
                .filter(file -> Files.isRegularFile(file))
                .count();
    }

    private static Long getSizeOfFiles(final String path) throws IOException {
        return Files
                .walk(Paths.get(path))
                .filter(file -> Files.isRegularFile(file))
                .reduce(0L, (size, file) -> size += getFileSize(file), (sum, size) -> sum + size);
    }

    private static Long getFileSize(final Path path) {
        try {
            return Files.size(path);
        } catch (IOException ex) {
        }
        return 0L;
    }
}
