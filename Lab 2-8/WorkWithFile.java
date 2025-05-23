import java.io.File;
import java.io.IOException;

public class WorkWithFile {
    public static void main(String[] args){
        
        // Крок 1: Створити папку з імʼям inner_directory
        File Dir = new File("/Users/m/Downloads/Мережеве програмування/Lab 2-8");
        File dir = new File(Dir, "inner_directory");
        if(dir.mkdir()){
            try{
                System.out.println("Folder was created: " + dir.getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Крок 2: Вивести абсолютний шлях
        System.out.println("\nAbsolute directory: " + dir.getAbsolutePath());

        // Крок 3: Вивести імʼя батьківської директорії
        System.out.println("\nParent directory: " + dir.getParent());

        // Крок 4: Створити два текстових файли всередині папці inner_directory
        File file1 = new File(dir, "file1.txt");
        File file2 = new File(dir, "file2.txt");

        try{
            file1.createNewFile();
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Крок 5: Один файл видалити
        if (file1.delete()){
            System.out.println("\nFile <<file1.txt>> was deleted");
        }

        // Крок 6: Перейменувати папку inner_directory в renamed_inner_directory
        File newDir = new File("renamed_inner_directory");

        if(dir.renameTo(newDir)) {
            System.out.println("\nFolder was renamed from" + dir.getName() + " to " + newDir.getName());
        }

        // Крок 7: Вивести список файлів та папок в папці directory_for_files, їх розмір та тип (файл, папка)

        // Створення папки directory_for_files
        File outerDir = new File(Dir, "directory_for_files");
        if (!outerDir.exists()) {
            if (outerDir.mkdir()){
                System.out.println("\nFolder <<directory_for_files>> was created");
            }
        }

        // Переміщення папки renamed_inner_directory
        File movedDir = new File(outerDir, "renamed_inner_directory");

        if (newDir.exists() && newDir.isDirectory()) {
            if (newDir.renameTo(movedDir)) {
                System.out.println("\nFolder <<renamed_inner_directory>> was moved to <<directory_for_files>>");
            } else {
                System.out.println("\nFailed to move a folder");
            }
        } else {
            System.out.println("\nFolder <<renamed_inner_directory>> could not find");
        }

        // Вивід вмісту папки directory_for_files
        System.out.println("\nContent of the <<directory_for_files>> folder:");

        File[] files = outerDir.listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                System.out.println(
                    (f.isFile() ? "File: " : "Folder: ") +
                    f.getName() + "\nSize: " + f.length() + " bytes"
                );
            }
        } else {
            System.out.println("\nThe folder is empty");
        }
    }
}
