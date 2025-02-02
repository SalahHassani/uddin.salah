import IO.IOHandler;
import NIO.NIOUtilsImps;
import exceptions.FileExistsException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Main {
  public static void main(String[] args) {

    NIOUtilsImps NioUtils = new NIOUtilsImps();
    IOHandler ioUtils = new IOHandler();

    String filePath = "06_exceptions_io/src/main/resources/";

    try {

      // Part-1
      File file = new File(filePath + "file.txt");
      if (!file.exists()) {
        // it is a IO exception so no need to catch it explicitly...
        throw new FileNotFoundException("The file.txt does not exist on path :=> " + file.getAbsolutePath());
      }

      // - Reads a line from the console (use System.in and IO classes, don't use scanner!).
      String line = ioUtils.ReadALineFromConsole();

      // - After that, it writes the "I love this IO Task" text to a file, called file.txt.
      ioUtils.writeToFile(filePath + "file.txt", "I love this IO Task", false);

      // - Then copies the content of this file (file.txt) to a new file, called file_copy.txt.
      ioUtils.copyFile(filePath + "file.txt", filePath + "file_copy.txt");

      // - Then appends the line read from the console to the original file.
      ioUtils.writeToFile(filePath + "file.txt", line, true);



      // Part-2
      // searchText function...
      Path path = Paths.get(filePath + "testFile.txt");
      System.out.println(NioUtils.searchText(path, 4));

      // search function... Please provide the path accordingly
      System.out.println();
      path = Paths.get("../");
      String[] allFiles = NioUtils.search(path, "java");

      for(String s : allFiles) {
        System.out.println(s);
      }

    }
    catch (FileExistsException e) {
      System.err.println("Exception FileExistsException: " + e.getMessage());
    }
    catch (IOException | IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }
}
