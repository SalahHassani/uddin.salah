package exceptions;

import java.io.IOException;

public class FileExistsException extends IOException {
  public FileExistsException(String message) {
    super(message);
  }
}
