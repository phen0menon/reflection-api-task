package test;

import com.company.FieldAnnotations;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;

import static junit.framework.TestCase.*;

public class MainTest {

    private String PACKAGE_PREFIX = "com.company";

    @Test
    public void hasMethodWithContainerOrFilePathAnnotations() throws SecurityException {
        Reflections reflections = new Reflections(PACKAGE_PREFIX, new MethodAnnotationsScanner());

        assertEquals(0, reflections.getMethodsAnnotatedWith(FieldAnnotations.FilePath.class).size());
        assertEquals(0, reflections.getMethodsAnnotatedWith(FieldAnnotations.Container.class).size());
    }


    @Test
    public void isFilePathAnnotationInitialized() throws SecurityException {
        Reflections reflections = new Reflections(PACKAGE_PREFIX);

        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(FieldAnnotations.FilePath.class);

        for (Class annotatedClass: annotatedClasses) {
            String inputFile = null, outputFile = null;

            FieldAnnotations.FilePath pathAnnotation = (FieldAnnotations.FilePath)
                    annotatedClass.getAnnotation(FieldAnnotations.FilePath.class);

            inputFile = pathAnnotation.in();
            outputFile = pathAnnotation.out();

            assertFalse(inputFile.isEmpty());
            assertFalse(outputFile.isEmpty());
        }
    }
}


