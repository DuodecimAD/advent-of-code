package AoC_2025.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Buffer {

    public static BufferedReader reader(String filePath) throws IOException {
        return Files.newBufferedReader(getPath(filePath));
    }

    public static BufferedWriter writer(String filePath) throws IOException {
        return Files.newBufferedWriter(
                getPath(filePath),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );
    }

    private static Path getPath(String filePath){
        var cwd = System.getProperty("user.dir");
        return Path.of(cwd,"src","AoC_2025","resources", filePath);
    }
}
