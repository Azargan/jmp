package com.epam.mentoring.troubleshooting;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LineReader {
    public static void main(String[] args) throws URISyntaxException, IOException {
        final Stream<String> lines = Files.lines(Paths.get(ClassLoader.getSystemResource("Data.txt").toURI()));
        final List<String> resultList = lines.map(line -> line.substring(0, 3)).collect(Collectors.toList());

        System.out.println("Size of result list is " + resultList.size());
        System.out.println("First 10 elements from result list are: ");
        resultList.stream().limit(10).forEach(System.out::println);
    }
}
