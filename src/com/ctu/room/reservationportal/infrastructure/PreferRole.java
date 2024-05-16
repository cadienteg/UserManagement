package com.ctu.room.reservationportal.infrastructure;

import com.ctu.room.reservationportal.model.UserInfo;

import java.util.Scanner;

// Import the IDGenerator class
import com.ctu.room.reservationportal.infrastructure.IDGenerator;

public class PreferRole {

    public PreferRole(Scanner scanner) {
    }

    // Method to select preferred role and register user or admin
    public static void selectPreferRole(UserInfo userInfo) {
        Scanner scanner = new Scanner(System.in);
        int preferredRole;

        do {
            System.out.println("Enter Preferred Role (1 = User, 2 = Admin):");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid 1 for User and 2 for Admin.");
                scanner.next(); // Consume the invalid input
            }
            preferredRole = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
        } while (preferredRole != 1 && preferredRole != 2);
        // Loop until valid email is entered

        if (preferredRole == 1) {


            //user set info

            String idNumber = IDGenerator.generateUserID();
            IDGenerator.displayID("User", idNumber);

            userInfo.setIdNumber(userInfo.getIdNumber());



        } else if (preferredRole == 2) {

            // Admin registration

            // Generate admin ID number
            String idNumber = IDGenerator.generateUserID();
            IDGenerator.displayID("Admin", idNumber);

            userInfo.setIdNumber(Integer.parseInt(idNumber));


        } else {
            System.out.println("Invalid role selected.");
        }

        // Exit the program
        System.out.println("You have successfully registered in this portal.");
        System.exit(0);
    }
}
