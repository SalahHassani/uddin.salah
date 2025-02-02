import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import static org.junit.Assert.assertEquals;

public class RobotTest {
  
  @Rule
  public TemporaryFolder folder = new TemporaryFolder();
  
  @Test
  public void testProgramRobot() throws IOException {
    // Create temporary file using TemporaryFolder
    File outputFile = folder.newFile("Robot.txt");
    try (PrintStream outputStream = new PrintStream(new FileOutputStream(outputFile))) {
      // Given
      Robot robot = new Robot(outputStream);
      String commands = "l-f-f-r-f-l-r-r-r-f-f-f";
      Program program = new Program(robot);
      FileWriter fileWriter = new FileWriter(outputFile);
      
      // When
      program.executeCommands(commands, fileWriter);
      
      // Then
      String result = Files.readString(outputFile.toPath());
      
      String expected =
        "Coordinates: (0.0, 0.0)\n" +
          "Coordinates: (0.0, 1.0)\n" +
          "Coordinates: (0.0, 2.0)\n" +
          "Coordinates: (0.0, 2.0)\n" +
          "Coordinates: (1.0, 2.0)\n" +
          "Coordinates: (1.0, 2.0)\n" +
          "Coordinates: (1.0, 2.0)\n" +
          "Coordinates: (1.0, 2.0)\n" +
          "Coordinates: (1.0, 2.0)\n" +
          "Coordinates: (0.0, 2.0)\n" +
          "Coordinates: (-1.0, 2.0)\n" +
          "Coordinates: (-2.0, 2.0)\n";
      
      assertEquals(expected.trim(), result.trim());
    }
  }
}
