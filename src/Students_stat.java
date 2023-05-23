/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * The second component of the Student Registration system.
 */
public class Students_stat {

    private static final String BATCH_DATA_FOLDER = "/app/data/batch/";
    private static final String DATA_FILE_PATH = "/app/data/batch/data.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("MENU");
            System.out.println("1. Show statistics");
            System.out.println("2. List batch files");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showStatistics();
                    break;
                case 2:
                    showStatistics();
                    listBatchFiles();
                    break;
                case 0:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        } while (choice != 0);

        scanner.close();
    }

private static void showStatistics() {
    List<String[]> students = readStudentsDataFromCSV();

    int numUsers = students.size();
    Map<String, Integer> courseCounts = new HashMap<>();

    for (String[] studentData : students) {
        if (studentData.length >= 3) {
            String[] courses = studentData[2].trim().split(",");
            for (String course : courses) {
                String courseName = course.trim().toUpperCase();
                int count = courseCounts.getOrDefault(courseName, 0);
                courseCounts.put(courseName, count + 1);
            }
        }
    }

    System.out.println("Number of users: " + numUsers);
    for (Map.Entry<String, Integer> entry : courseCounts.entrySet()) {
        String course = entry.getKey();
        int count = entry.getValue();
        System.out.println("Number of students registered in " + course + " course: " + count);
    }
}


    private static void listBatchFiles() {
        File folder = new File(BATCH_DATA_FOLDER);
        File[] allFiles = folder.listFiles();
        int numFiles = allFiles.length;
        int numVerifiedFiles = countVerifiedBatchFiles(allFiles);
	numFiles--;
        System.out.println("Number of batch files: " + numFiles);
        System.out.println("Number of verified batch files: " + numVerifiedFiles);
    }

    private static int countVerifiedBatchFiles(File[] files) {
        int count = 0;
        for (File file : files) {
            if (file.isFile() && file.getName().contains("verified")) {
                count++;
            }
        }
        return count;
    }


    private static List<String[]> readStudentsDataFromCSV() {
        List<String[]> students = new ArrayList<>();
        File file = new File(DATA_FILE_PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] studentData = line.split(";");
                students.add(studentData);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading data file: " + e.getMessage());
        }
        return students;
    }
}


