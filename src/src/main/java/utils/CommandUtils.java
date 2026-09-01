package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandUtils {

    public static List<String> checkCommandInPaths(String arg) {
        List<String> paths = getPaths();

        // "/usr/bin" "/usr/local/bin"
        // "/usr/bin/cat" "/usr/local/bin/cat"
        List<String> commandLocations = new ArrayList<>();

        for (String dir : paths) {
            File executable = new File(dir, arg);

            // Windows executables usually have .exe
            if (System.getProperty("os.name").toLowerCase().contains("win")
                    && !arg.endsWith(".exe")) {
                executable = new File(dir, arg + ".exe");
            }

            if (executable.exists() && executable.canExecute()) {
                commandLocations.add(executable.getAbsolutePath());
            }
        }

        return commandLocations;
    }

    public static List<String> getPaths() {
        String path = System.getenv("PATH");

        return Arrays.stream(path.split(File.pathSeparator))
                .collect(Collectors.toList());
    }

    public static File resolvePath(String targetPath) throws IOException {
        File targetDir = new File(targetPath);

        if (!targetDir.isAbsolute()) {
            targetDir = new File(System.getProperty("user.dir"), targetPath);
        }

        targetDir = targetDir.getCanonicalFile();

        return targetDir;
    }
}