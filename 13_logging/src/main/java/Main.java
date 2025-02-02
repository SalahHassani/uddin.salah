import nio.NIOUtilsImps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        NIOUtilsImps nioUtils = new NIOUtilsImps();

        try {
            logger.info("Using searchText method.");
            Path testFilePath = Paths.get("06_exceptions_io/src/main/resources/testFile.txt");
            logger.info("Searching for text in the given file: {}", testFilePath);
            String searchTextResult = nioUtils.searchText(testFilePath, 4);
            logger.info("searchText result: {}", searchTextResult);
            System.out.println("searchText result: " + searchTextResult);

            logger.info("Using search method.");
            Path searchPath = Paths.get("06_exceptions_io/src/main/resources/");
            logger.info("Searching for files with .txt extension in directory: {}", searchPath);
            String[] txtFiles = nioUtils.search(searchPath, "txt");
            logger.info("Found .txt files: {}", (Object) txtFiles);

            System.out.println("Found .txt files:");
            for (String fileName : txtFiles) {
                System.out.println(fileName);
            }

        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage(), e);
        }
    }
}
