package com.example.lists_stacks_queues;

import javafx.application.Application;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class part0 extends Application {
    private static DecimalFormat formatter = new DecimalFormat("#,###");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        int numElements = 100000;

        // ArrayList performance tests
        ArrayList<Integer> arrayList = new ArrayList<>();
        testInsertionBeginning(arrayList, numElements, "ArrayList");
        testAccessByIndex(arrayList, numElements, "ArrayList");
        testRemoveFirst(arrayList, numElements, "ArrayList");

        // LinkedList performance tests
        LinkedList<Integer> linkedList = new LinkedList<>();
        testInsertionBeginning(linkedList, numElements, "LinkedList");
        testAccessByIndex(linkedList, numElements, "LinkedList");
        testRemoveFirst(linkedList, numElements, "LinkedList");

        // Optionally, you can close the application after tests are done
        // Platform.exit();
    }

    public static void testInsertionBeginning(List<Integer> list, int numElements, String structure) {
        long startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            list.add(0, i);
        }
        long endTime = System.nanoTime();
        System.out.println(structure + " insertion into beginning time: " + formatter.format(endTime - startTime) + " ns");
    }

    public static void testAccessByIndex(List<Integer> list, int numElements, String structure) {
        long startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            list.get(i);
        }
        long endTime = System.nanoTime();
        System.out.println(structure + " access by index time: " + formatter.format(endTime - startTime) + " ns");
    }

    public static void testRemoveFirst(List<Integer> list, int numElements, String structure) {
        long startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            list.remove(0);
        }
        long endTime = System.nanoTime();
        System.out.println(structure + " remove first element time: " + formatter.format(endTime - startTime) + " ns");
    }
}
