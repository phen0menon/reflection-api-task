package com.company.testCases;

import com.company.FieldAnnotations.Container;
import com.company.FieldAnnotations.FilePath;
import com.company.FieldAnnotations.Transformation;

@Container
@FilePath(in="inputLower.txt", out="outputLower.txt")
public class LowerCase {

    @Transformation
    public String transformText(String text) {
        return text.toLowerCase();
    }
}
