package NIO;

import com.nix.jtc.exceptions_io.NIOUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class NIOUtilsImps implements NIOUtils {

  /**
   * Looks for text in a file using following rules:
   * <ul>
   *     <li>The file contains text and integers (both positive and
   *     negative).</li>
   *     <li>Space is used as a delimiter.</li>
   *     <li>There are no more than 10 bytes between delimiters.</li>
   *     <li>If the method reads an integer number N (not a digit, a whole
   *     number) then the next read occurs after N bytes relative to
   *     the current position.</li>
   *     <li>The position is moved forward for positive numbers and
   *     backward for negative numbers.</li>
   *     <li>If the method reads some text then this text is returned
   *     as a result of the method call.</li>
   * </ul>
   * <p>For example, for the file with the following content the call to
   * {@code searchText(path, 4)} will return "a":<br/>
   * {@code 1 7 -2 a -2}
   *
   * @param file   path to the file
   * @param offset initial offset, measured from the beginning of the file,
   *               at which the first read occurs
   * @return text found in the file
   * @throws IllegalArgumentException if {@code file} doesn't exist
   */
  @Override
  public String searchText(Path file, int offset) throws IllegalArgumentException {

    if(file == null || !Files.exists(file) ) {
      throw  new IllegalArgumentException("Error: The file Path is null or it does not exist (in SearchText).");
    }

    try (FileChannel fileChannel = FileChannel.open(file, StandardOpenOption.READ)) {
      ByteBuffer buffer = ByteBuffer.allocate(1); // Read one byte at a time
      int fileSize = (int) fileChannel.size();

//    StringBuilder result = new StringBuilder();
      StringBuilder currentText = new StringBuilder();
      int position = offset;

      while (position >= 0 && position < fileSize) {

        fileChannel.position(position);

        while (position < fileSize) {
          buffer.clear();
          int bytes = fileChannel.read(buffer);
          if(bytes == -1) {
            break;
          }

          buffer.flip();
          byte readByte = buffer.get();

          if(readByte == 32) {
            break;
          }

          currentText.append((char) readByte);
          position++;
        }

        if (currentText.length() > 0) {
          String text = currentText.toString();

          // accepting both - and + numbers...
          if (text.matches("-?\\d+")) {

            int count = Integer.parseInt(text);
            offset += count;
            position = offset;
            // empty it to avoid duplicate text...
            currentText.setLength(0);

          } else {
            return text;
          }
        }
      }
    } catch (IOException e) {
      System.err.println("Error: While reading file " + file + ": " + e.getMessage());
    }

      return "";
  }

  /**
   * Looks for all files with {@code ext} extension in {@code folder}
   * directory as well as in its subdirectories and then returns files'
   * absolute paths as a string array. If {@code ext == null} then the method
   * treats it as an empty string and looks for all files.
   *
   * @param folder root directory to search for files in
   * @param ext    file extension
   * @return list, of absolute paths of the files that were found
   * @throws IllegalArgumentException if {@code folder} doesn't exist
   */
  @Override
  public String[] search(Path folder, String ext) throws IllegalArgumentException {
    if (folder == null || !Files.exists(folder) || !Files.isDirectory(folder)) {
      throw new IllegalArgumentException("The given folder doesn't exist or is not a folder.");
    }

    if (ext == null || ext.isEmpty()) {
      throw new IllegalArgumentException("The given ext is null.");
    }

    List<String> filePaths = new ArrayList<>();

    try {
      Files.walkFileTree(folder, new SimpleFileVisitor<>() {
          @Override
          public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (file.toString().endsWith("." + ext)) {
              filePaths.add(file.toAbsolutePath().toString());
            }
            return FileVisitResult.CONTINUE;
          }
        });
    } catch (IOException e) {
      System.err.println("Error while searching files in " + folder);
    }

    return filePaths.toArray(new String[0]);
  }
}


