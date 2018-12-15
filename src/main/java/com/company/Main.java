package com.company;

import com.company.FieldAnnotations.FilePath;
import com.company.FieldAnnotations.Transformation;
import org.reflections.Reflections;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

public class Main {

    private final static String CASE_PACKAGE_PREFIX = "com.company.testCases";
    private final static String DIRECTORY_PATH = "\\transformed-items\\";

    public static void main(String[] args) throws Exception {
        DummyClass dummyObject = new DummyClass("Dummy", 100, 1, 30);
        replaceFieldObject(dummyObject);

        transformFileTextHandler();
    }

    /**
     * Prints all fields of the object.
     * @param object instance to print
     */
    private static void printObjectFields(Object object) {
        Arrays.stream(object.getClass().getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);

            try {
                System.out.println(field.getName() + ": " + field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Replaces the value of all fields depending on their type.
     * @param object instance of the class to replace
     * @return instance of the class with replaced field values.
     */
    private static DummyClass replaceFieldObject(DummyClass object) {
        Arrays.stream(object.getClass().getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);

            if (field.getAnnotation(FieldAnnotations.HackerAttack.class) != null) {
                try {
                    switch (field.getType().getSimpleName()) {
                        case "String":
                            field.set(object, "hacker attack");
                            break;

                        case "int":
                            field.set(object, 0);
                            break;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        printObjectFields(object);

        return object;
    }

    /**
     * Transform text based on class. There are several steps in this method.
     * The first one is to find all classes annotated with @Container
     * The second one is to get both input and output paths from @FilePath annotation
     * The third step is to invoke a method annotated with @Transform in the found class
     * Finally, write new data to the file
     */
    private static void transformFileTextHandler() throws Exception {
        Set<Class<?>> annotated = new Reflections(CASE_PACKAGE_PREFIX)
                .getTypesAnnotatedWith(FieldAnnotations.Container.class);

        for (Class annotatedClass: annotated) {
            String inputFile = null, outputFile = null;

            if (annotatedClass.isAnnotationPresent(FilePath.class)) {
                FilePath pathAnnotation = (FilePath) annotatedClass.getAnnotation(FilePath.class);

                inputFile = pathAnnotation.in();
                outputFile = pathAnnotation.out();
            }

            if (inputFile != null & outputFile != null) {
                for (Method method: annotatedClass.getMethods()) {
                    if (method.isAnnotationPresent(Transformation.class)) {
                        String className = annotatedClass.getSimpleName();

                        Class<?> c = Class.forName(String.format("%s.%s", CASE_PACKAGE_PREFIX, className));
                        Constructor<?> constructor = c.getConstructor();
                        Object obj = constructor.newInstance();

                        String transformedText = (String) method.invoke(obj, getFileContent(inputFile));
                        setFileContent(outputFile, transformedText);
                    }
                }
            }
        }
    }

    /**
     * Get all content of the file to String
     * @param filePath path where file exists
     * @return content of the file
     */
    private static String getFileContent(String filePath) throws IOException {
        String currentPath = System.getProperty("user.dir");

        File inputFile = new File(currentPath.concat(DIRECTORY_PATH + filePath));
        inputFile.createNewFile();

        FileInputStream fis = new FileInputStream(inputFile);
        byte[] data = new byte[(int) inputFile.length()];
        fis.read(data);
        fis.close();

        return new String(data, "UTF-8");
    }

    /**
     * Set a new content to the file.
     * @param filePath path where file exists. If there's no such file, create a new one.
     * @param text the new contents of the file
     */
    private static void setFileContent(String filePath, String text) throws IOException {
        String currentPath = System.getProperty("user.dir");

        File outputFile = new File(currentPath.concat(DIRECTORY_PATH + filePath));
        outputFile.createNewFile();

        FileOutputStream fos = new FileOutputStream(outputFile);
        byte[] data = text.getBytes();
        fos.write(data);
        fos.close();
    }
}
