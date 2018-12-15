package com.company.testCases;

import com.company.FieldAnnotations.Container;
import com.company.FieldAnnotations.FilePath;
import com.company.FieldAnnotations.Transformation;

@Container
@FilePath(in="inputDot.txt", out="outputDot.txt")
public class DotCase {

    @Transformation
    public String transformText(String text) {
        return text.replace('.', '-');
    }
}
