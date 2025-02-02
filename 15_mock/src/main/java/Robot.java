import java.io.PrintStream;

public class Robot {
  
  private double x;
  private double y;
  private int directionIndex;
  private final PrintStream outputStream;
  private final String[] directions = {"E", "N", "W", "S"};
  
  public Robot(PrintStream outputStream) {
    this.x = 0.0;
    this.y = 0.0;
    this.directionIndex = 0;
    this.outputStream = outputStream;
  }
  
  public void turnLeft() {
    directionIndex = (directionIndex + 1) % 4;
  }
  
  public void turnRight() {
    directionIndex = (directionIndex + 3) % 4;
  }
  
  public void stepForward() {
    switch (directions[directionIndex]) {
      case "E":
        x += 1.0;
        break;
      case "W":
        x -= 1.0;
        break;
      case "N":
        y += 1.0;
        break;
      case "S":
        y -= 1.0;
        break;
    }
    outputStream.println("Coordinates: (" + x + ", " + y + ")");
  }
  
  public double getX() {
    return x;
  }
  
  public double getY() {
    return y;
  }
}
