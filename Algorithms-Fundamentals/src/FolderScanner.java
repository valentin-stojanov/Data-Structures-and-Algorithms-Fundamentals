import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FolderScanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Type in the path of the folder to inspect:");
        System.out.println("Or type `exit` for quit:");

        String path = "";

        while (!path.equals("exit")){

            path = scanner.nextLine();
            File folder = new File(path);
            List<File> files = new ArrayList<>();
            try {
                getFiles(folder, files);
                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < files.size(); i++) {
                    builder.append(i + 1)
                            .append(".")
                            .append("\t")
                            .append(files.get(i).getName())
                            .append(" -->[")
                            .append(files.get(i).getParentFile())
                            .append("\\")
                            .append(files.get(i).getName())
                            .append("]")
                            .append("\r\n");
                }
                builder.append("-------------------------");


//                FileWriter file = new FileWriter(path + "\\files.txt");
//                BufferedWriter writer = new BufferedWriter(file);
                BufferedWriter writer = Files.newBufferedWriter(Path.of(path + "\\files.txt"), StandardCharsets.UTF_8);

                writer.write(builder.toString());
                writer.close();

                System.out.println("Files in all sub-folders are:");
                System.out.println(builder);
                System.out.println("File: `filesList.txt` was created in: `" + path + "`");

            } catch (IOException e) {
                System.out.println("Invalid path: " + path );
            }

            System.out.println("Type in the path of the folder to inspect:");
        }

    }

    private static void getFiles (File folder, List<File> listOfFiles){
        File[] currentFiles = folder.listFiles();

        if (currentFiles != null) {
            for (File currentFile : currentFiles) {
                if (!currentFile.isDirectory()){
                    listOfFiles.add(currentFile);
                }else {
                    String folderName = currentFile.getName();
                    getFiles(new File((folder + "\\" + folderName)), listOfFiles);
                }
            }
        }
    }
}
