import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class Program {
  
  private final Robot robot;
  
  public Program(Robot robot) {
    this.robot = robot; //new Robot(outputStream);
  }
  
  public void executeCommands(String commands, FileWriter writer) throws IOException {
    
    try {
      for (char command : commands.toCharArray()) {
        boolean flag = true;
        switch (command) {
          case 'l':
            robot.turnLeft();
            break;
          case 'r':
            robot.turnRight();
            break;
          case 'f':
            robot.stepForward();
            break;
          case '-':
            flag = false;
            break;
          default:
            throw new IllegalArgumentException("Invalid command: " + command);
        }
        if (flag && writer != null) {
          writer.write("Coordinates: (" + robot.getX() + ", " + robot.getY() + ")\n");
        }
      }
    } finally {
      if (writer != null) {
        writer.close();
      }
    }
  }
}