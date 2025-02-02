package IO;

import exceptions.FileExistsException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOHandler {

    public String ReadALineFromConsole() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a Line: ");
        String line = reader.readLine();
        System.out.println("You Typed :=> " + line);
        return line;
    }


    /**
     * Writes the specified content to a file.
     * @param fileName the name of the file
     * @param content the content to be written to
     * @param append if ture will append the text
     * @throws IOException if an I/O error occurs during writing
     */
    public void writeToFile(String fileName, String content, boolean append) throws IOException {

        try(FileWriter fileWriter = new FileWriter(fileName, append)) {
            if (append) {
                fileWriter.write("\n" + content);
            } else {
                fileWriter.write(content);
            }
        }
    }


    /**
     * Copies the content of the source file to the target file.
     *
     * @param source the path of the source file to read from
     * @param target the path of the target file
     * @throws IOException if an I/O error occurs during reading or writing
     * @throws FileExistsException if the target file already exists
     */

    public void copyFile(String source, String target) throws IOException {

        if (new File(target).exists()) {
            throw new FileExistsException("The target file already exists: " + target);
        }

        try (
                BufferedReader fileReader = new BufferedReader(new FileReader(source));
                BufferedWriter fileWriter = new BufferedWriter(new FileWriter(target))
        ) {

            String line;
            while ((line = fileReader.readLine()) != null) {
                fileWriter.write(line);
                fileWriter.newLine();
            }
        }
    }

}
