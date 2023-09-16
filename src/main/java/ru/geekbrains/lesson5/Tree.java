package ru.geekbrains.lesson5;

import java.io.File;

public class Tree {

    public static void main(String[] args) {
        File[] x = new File(".").listFiles();
        print(new File("."), "", true);

    }

    /**
     * TODO: Доработать метод print, необходимо распечатывать директории и файлы
     *
     * @param file
     * @param indent
     * @param isLast
     */
    public static void print(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());
        if (file.isDirectory()) {
            File[] filesDir = new File(file.getAbsolutePath()).listFiles();
            for (int i = 0; i < filesDir.length; i++) {
                if (filesDir[i].isFile()) {
                    if (i != filesDir.length - 1) {
                        System.out.print(indent);
                        System.out.print("├─");
                        System.out.println(filesDir[i].getName());
                    } else {
                        System.out.print(indent);
                        System.out.print("└─");
                        System.out.println(filesDir[i].getName());
                    }
                }
            }
        }

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory())
                subDirTotal++;
        }

        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                subDirCounter++;
                print(files[i], indent, subDirCounter == subDirTotal);
            }
        }
    }
}
