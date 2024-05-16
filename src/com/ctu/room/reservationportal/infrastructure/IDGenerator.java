package com.ctu.room.reservationportal.infrastructure;

import com.ctu.room.reservationportal.model.UserInfo;

import java.util.Random;
import java.util.Scanner;

/**
 * The IDGenerator class provides methods to generate and display user IDs.
 */
public class IDGenerator {

    /**
     * Scanner object for reading user input.
     */
    private Scanner scanner;

    /**
     * UserInfo object for storing user registration information.
     */
    private UserInfo userRegistration;

    /**
     * Constructs an IDGenerator object and initializes the scanner and userRegistration fields.
     */
    public IDGenerator() {
        this.scanner = new Scanner(System.in);
        this.userRegistration = new UserInfo();
    }

    /**
     * Generates a random 6-digit user ID.
     *
     * @return The generated user ID as a String.
     */
    public static String generateUserID() {
        Random random = new Random();
        StringBuilder userID = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            userID.append(random.nextInt(10)); // Generate a random digit (0-9)
        }
        return userID.toString();
    }

    /**
     * Generates a random 6-digit admin ID.
     *
     * @return The generated admin ID as a String.
     */
    public static String generateAdminID() {
        Random random = new Random();
        StringBuilder adminID = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            adminID.append(random.nextInt(10)); // Generate a random digit (0-9)
        }
        return adminID.toString();
    }

    /**
     * Displays the generated ID along with its type.
     *
     * @param idType   The type of ID (e.g., "User ID", "Admin ID").
     * @param idNumber The generated ID number as a String.
     */
    public static void displayID(String idType, String idNumber) {
        UserInfo userInfo = new UserInfo(); // Creating a new UserInfo object
        System.out.println("Generated " + idType + " ID: " + idNumber);
        userInfo.setIdNumber(Integer.parseInt(idNumber)); // Setting the ID number in the UserInfo object
    }

}
