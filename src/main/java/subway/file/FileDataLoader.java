package subway.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileDataLoader {
    private static final String FILE_NOT_EXIST_EXCEPTION = "파일이 존재하지 않습니다.";
    private static final String SEPARATOR = ",";

    private FileDataLoader() {
    }

    public static <T> List<T> getObjects(String filePath, Function<List<String>, T> createObject) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            scanner.nextLine(); // 헤더
            List<T> objects = new ArrayList<>();
            while (scanner.hasNextLine()) {
                objects.add(loadObject(createObject, scanner.nextLine()));
            }
            scanner.close();

            return objects;
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(FILE_NOT_EXIST_EXCEPTION);
        }
    }

    private static <T> T loadObject(Function<List<String>, T> createObject, String line) {
        List<String> arguments = Arrays.stream(line.split(SEPARATOR)).collect(Collectors.toList())
                .stream().map(String::trim).collect(Collectors.toList());

        return createObject.apply(arguments);
    }
}
