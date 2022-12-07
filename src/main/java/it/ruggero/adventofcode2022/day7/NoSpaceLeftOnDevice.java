package it.ruggero.adventofcode2022.day7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static it.ruggero.adventofcode2022.util.ParseFileUtility.getLinesFromFile;

public class NoSpaceLeftOnDevice {
    final static String FILE_PATH = ".\\src\\main\\resources\\adventofcode2022\\day7\\Day7.txt";

    private static int logLineIndex = 0;

    private static Directory root;

    private static Directory currentDirectory;

    private static final List<String> logs = getLinesFromFile(FILE_PATH);

    private static final List<Directory> allDirectories = new ArrayList<>();

    private static void run() {
        do {
            processLine();
        } while (logLineIndex < logs.size());
        collectDirectories(root);
    }

    public static long partOne() {
        run();
        return findSumOfSizeOfAllSubdirectoriesSmallerThan();
    }

    private static void processLine() {
        String logLine = logs.get(logLineIndex);
        if (logLine.contains("$")) {
            if (logLine.contains("cd")) {
                moveToOrCreateAndMoveTo(logLine.split(" ")[2]);
                logLineIndex++;
            } else if (logLine.contains("ls")) {
                processListOutput();
            }
        }
    }

    private static void processListOutput() {
        int previousLogLineIndex = logLineIndex;
        do {
            logLineIndex++;
        } while (logLineIndex < logs.size() - 1 && !logs.get(logLineIndex).contains("$"));
        int limit = (logLineIndex < logs.size() - 1) ? logLineIndex - previousLogLineIndex - 1 : logLineIndex++ - previousLogLineIndex;
        List<String> readings = logs.stream().skip(previousLogLineIndex + 1)
                .limit(limit).collect(Collectors.toList());

        List<String> fileLogList = readings.stream().filter(s -> !s.contains("dir")).collect(Collectors.toList());
        List<String> directoriesLogList = readings.stream().filter(s -> s.contains("dir"))
                .collect(Collectors.toList());

        var files = fileLogList.stream().map(s -> {
            String[] data = s.split(" ");
            return File.builder()
                    .name(data[1])
                    .size(Long.parseLong(data[0]))
                    .build();
        }).collect(Collectors.toList());

        currentDirectory.setFiles(files);

        var subDirectories = directoriesLogList.stream().map(s -> {
            String[] data = s.split(" ");
            return Directory.builder()
                    .name(data[1])
                    .files(new ArrayList<>())
                    .subdirectories(new ArrayList<>())
                    .parent(currentDirectory)
                    .directorySize(Long.MIN_VALUE)
                    .build();
        }).collect(Collectors.toList());
        currentDirectory.setSubdirectories(subDirectories);
    }

    private static void moveToOrCreateAndMoveTo(String directoryName) {
        if (directoryName.equals("/")) {
            root = Directory.builder()
                    .name("")
                    .files(new ArrayList<>())
                    .subdirectories(new ArrayList<>())
                    .parent(null)
                    .directorySize(Long.MIN_VALUE)
                    .build();
            currentDirectory = root;
            return;
        }

        if (directoryName.equals("..")) {
            currentDirectory = currentDirectory.getParent();
        } else {
            currentDirectory = currentDirectory.getSubdirectories().stream()
                    .filter(s -> s.getName().equals(directoryName)).findFirst().orElseThrow();
        }
    }
    private static long findSumOfSizeOfAllSubdirectoriesSmallerThan() {
        return allDirectories.stream().filter(d -> d.getDirectorySize() < 100001)
                .mapToLong(Directory::getDirectorySize).sum();
    }

    public static long partTwo() {
        run();
        return findTheSmallestDirectory();
    }

    private static Long findTheSmallestDirectory() {
        long spaceToBeFreed = root.getDirectorySize() - 40000000;
        return allDirectories.stream().filter(d -> d.getDirectorySize() >= spaceToBeFreed)
                .min(Comparator.comparingLong(Directory::getDirectorySize)).orElseThrow().getDirectorySize();
    }

    private static void collectDirectories(Directory thisDirectory) {
        if (thisDirectory.getSubdirectories() != null) {
            thisDirectory.getSubdirectories().forEach(d -> {
                allDirectories.add(d);
                collectDirectories(d);
            });
        }
    }
}
