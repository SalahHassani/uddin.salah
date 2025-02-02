package nio;

import com.nix.jtc.exceptions_io.NIOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger logger = LogManager.getLogger(NIOUtilsImps.class);

    @Override
    public String searchText(Path file, int offset) throws IllegalArgumentException {
        logger.trace("Entering searchText() with file: {} and offset: {}", file, offset);

        if (file == null || !Files.exists(file)) {
            logger.error("File is null or does not exist: {}", file);
            throw new IllegalArgumentException("Error: The file path is null or it does not exist.");
        }

        try (FileChannel fileChannel = FileChannel.open(file, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(1);
            int fileSize = (int) fileChannel.size();
            logger.debug("File size: {}", fileSize);

            StringBuilder currentText = new StringBuilder();
            int position = offset;

            while (position >= 0 && position < fileSize) {
                logger.debug("Reading at position: {}", position);
                fileChannel.position(position);

                while (position < fileSize) {
                    buffer.clear();
                    int bytes = fileChannel.read(buffer);
                    if (bytes == -1) {
                        logger.warn("End of file");
                        break;
                    }

                    buffer.flip();
                    byte readByte = buffer.get();

                    if (readByte == 32) { // Space character as delimiter
                        logger.trace("Space found at position: {}", position);
                        break;
                    }

                    currentText.append((char) readByte);
                    position++;
                }

                if (currentText.length() > 0) {
                    String text = currentText.toString();
                    logger.info("Read text: {}", text);

                    if (text.matches("-?\\d+")) {
                        int count = Integer.parseInt(text);
                        logger.debug("Parsed integer: {}. Updating position by {}", count, count);
                        offset += count;
                        position = offset;
                        currentText.setLength(0);
                    } else {
                        logger.trace("Exiting searchText() with result: {}", text);
                        return text;
                    }
                }
            }
        } catch (IOException e) {
            logger.error("IOException occurred while reading the file: {}", file, e);
        }

        logger.trace("Exiting searchText() with no result.");
        return "";
    }

    @Override
    public String[] search(Path folder, String ext) throws IllegalArgumentException {
        logger.trace("Entering search() with folder: {} and extension: {}", folder, ext);

        if (folder == null || !Files.exists(folder) || !Files.isDirectory(folder)) {
            logger.error("Invalid folder: {}", folder);
            throw new IllegalArgumentException("The given folder doesn't exist or is not a folder.");
        }

        if (ext == null || ext.isEmpty()) {
            logger.warn("The given extension is null or empty.");
            throw new IllegalArgumentException("The given extension is null.");
        }

        List<String> filePaths = new ArrayList<>();
        logger.debug("Starting file traversal in folder: {}", folder);

        try {
            Files.walkFileTree(folder, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (file.toString().endsWith("." + ext)) {
                        filePaths.add(file.toAbsolutePath().toString());
                        logger.info("File found: {}", file);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    logger.warn("Failed to visit file: {}", file, exc);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            logger.error("IOException occurred while searching files in folder: {}", folder, e);
        }

        logger.trace("Exiting search() with result: {}", filePaths);
        return filePaths.toArray(new String[0]);
    }
}
