package com.company.testCases;

import com.company.FieldAnnotations.Container;
import com.company.FieldAnnotations.FilePath;
import com.company.FieldAnnotations.Transformation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Container
@FilePath(in="inputCapital.txt", out="outputCapital.txt")
public class CapitalCase {

    @Transformation
    public String transformText(String text) {
        return capitalizeString(text);
    }

    private static String capitalizeString(String string) {
        List<String> capitalizedWordList = Arrays.stream(string.split(" "))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.toList());

        return String.join(" ", capitalizedWordList);
    }
}
