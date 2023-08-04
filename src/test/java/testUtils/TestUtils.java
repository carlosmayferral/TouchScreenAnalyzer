package testUtils;

import java.io.File;
import java.util.stream.Stream;

public final class TestUtils {

    public static Stream<File> getFilesFromFolder(String folderPath){
        File[] files = (new File(folderPath).listFiles());
        return Stream.of(files);
    }
}
