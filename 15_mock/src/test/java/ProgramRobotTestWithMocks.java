import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProgramRobotTestWithMocks {
  
  @Mock
  private PrintStream mockOutputStream;
  
  @Mock
  private FileWriter mockFileWriter;
  
  @InjectMocks
  private Robot robot;
  
  @InjectMocks
  private Program program;
  
  @BeforeEach
  void setup() throws IOException {
    // Initialize mocks
    mockOutputStream = mock(PrintStream.class);
    mockFileWriter = mock(FileWriter.class);
    
    robot = new Robot(mockOutputStream);
    program = new Program(robot);
  }
  
  @Test
  public void testProgramRobotWithMocks() throws IOException {
    
    String expectedOutput =
        "Coordinates: (0.0, 0.0)\n" +
        "Coordinates: (0.0, 1.0)\n" +
        "Coordinates: (0.0, 2.0)\n" +
        "Coordinates: (0.0, 2.0)\n" +
        "Coordinates: (1.0, 2.0)\n";
    
    program.executeCommands("l-f-f-r-f", mockFileWriter);
    
    String[] lines = expectedOutput.split("\n");
    verify(mockFileWriter, times(1)).write(lines[0] + "\n");
    verify(mockFileWriter, times(1)).write(lines[1] + "\n");
    verify(mockFileWriter, times(2)).write(lines[2] + "\n");
    verify(mockFileWriter, times(2)).write(lines[3] + "\n");
    verify(mockFileWriter, times(1)).write(lines[4] + "\n");
    
    
    verify(mockFileWriter).close();
  }
}
