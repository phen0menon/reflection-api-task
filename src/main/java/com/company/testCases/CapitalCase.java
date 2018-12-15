package com.company.testCases;

import com.company.FieldAnnotations.Container;
import com.company.FieldAnnotations.FilePath;
import com.company.FieldAnnotations.Transformation;

@Container
@FilePath(in="inputCapital.txt", out="outputCapital.txt")
public class CapitalCase {

    @Transformation
    public String transformText(String text) {
        return capitalizeString(text);
    }

    private static String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') {
                found = false;
            }
        }
        return String.valueOf(chars);
    }
}
