package com.company.testCases;

import com.company.FieldAnnotations.*;

@Container
@FilePath(in="inputUpper.txt", out="outputUpper.txt")
public class UpperCase {

    @Transformation
    public String tranformText(String text) {
        return text.toUpperCase();
    }
}
