package ru.geekbrains.lesson5;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Backup {
    public static void main(String[] args) throws IOException {
        backUpDirectory(new File("D:\\1"));
    }
    public static void backUpDirectory (File directory) throws IOException {
        if (!directory.isDirectory()) {
            System.out.println("Переданный путь не является директорией!");
            return;
        }
        Path destination = Paths.get(directory.getAbsolutePath(), "/.backup/");
        if (!Files.exists(destination)){
            Files.createDirectories(destination);
        }
        if (directory.listFiles() != null) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                if (file.isFile()) {Path srcPath = Path.of(file.getAbsolutePath());
                    File destFile = new File(directory.getAbsolutePath() + "\\.backup\\" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + "_" + file.getName());
                    copyFileUsingStream(file, destFile);
                }
            }
        }
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}
