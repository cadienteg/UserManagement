package com.ctu.room.reservationportal.infrastructure;
import com.ctu.room.reservationportal.model.UserInfo;
import com.ctu.room.reservationportal.infrastructure.CreateAccount;
import com.ctu.room.reservationportal.infrastructure.PreferRole;
import com.ctu.room.reservationportal.infrastructure.RegistrationCode;
import com.ctu.room.reservationportal.model.UserInfo;

import java.util.Scanner;

/**
 * Class for getting the teaching details for the role at school of teacher
 */
public class TeachingDetails {


    // The number of subjects to collect
    private static int numberOfSubjects;

    //An array to store the names of the subjects
    private static String[] subjectNames;

    /**
     * Collects teaching details including the number of subjects and the subject names.
     */
    public static void collectTeachingDetails(Scanner scanner) {

        // Read the number of subjects entered by the user
        numberOfSubjects = readNumberOfSubjects(scanner);

        // Initialize the array to store subject names based on the number of subjects
        subjectNames = new String[numberOfSubjects];

        // Loop to input subject names based on the number of subjects
        for (int i = 0; i < numberOfSubjects; i++) {
            // Prompt the user to enter the name of each subject and to be stored in the array
            System.out.println("Enter subject name " + (i + 1) + ":");

            // Read the subject name entered by the user
            subjectNames[i] = scanner.nextLine();
        }

        // Display the collected teaching details
        displayTeachingDetails(subjectNames);

        // Redirect to RegistrationCode class
        RegistrationCode.displayRegistrationCode();

        // Redirect to CreateAccount class
        CreateAccount.createAccount(scanner);

        // Create an instance of AdminInfo
            UserInfo userInfo = new UserInfo(); // Replace UserInfo() with the appropriate constructor

        // Redirect to PreferRole class
        PreferRole.selectPreferRole(userInfo); // Pass adminInfo as parameter

        scanner.close();
    }

    /**
     * A method that reads the number of subjects input by the user with validation
     * If the input is not a valid positive integer, it prompts the user until a valid input is provided.
     *
     * @param scanner The Scanner object to use for input.
     * @return The valid number of subjects input by the user.
     */
    private static int readNumberOfSubjects(Scanner scanner) {
        while (true) {
            System.out.print("Enter the number of subjects: ");
            String input = scanner.nextLine();
            try {
                int numberOfSubjects = Integer.parseInt(input);
                if (numberOfSubjects > 0) {
                    return numberOfSubjects;
                } else {
                    System.out.println("Number of subjects must be a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive integer.");
            }
        }
    }

    /**
     * Method that displays the teaching details including the number of subjects and their names.
     *
     * @param subjectNames An array containing the names of the subjects.
     */
    private static void displayTeachingDetails(String[] subjectNames) {

        System.out.println("\nTeaching details:");

        // Prints the number of subjects
        System.out.println("Number of subjects: " + numberOfSubjects);

        // Prints the names of each subject
        System.out.println("Subject names:");
        for (String subject : subjectNames) {
            System.out.println("Subject Name: " + subject);
        }
    }
}