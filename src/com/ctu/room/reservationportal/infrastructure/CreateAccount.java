package com.ctu.room.reservationportal.infrastructure;

import com.ctu.room.reservationportal.model.UserInfo;
import com.ctu.room.reservationportal.infrastructure.PreferRole;
import com.ctu.room.reservationportal.infrastructure.Validators;
import com.ctu.room.reservationportal.model.UserInfo;

import java.util.Scanner;

public class CreateAccount {
    public static UserInfo createAccount(Scanner scanner) {
        String username, password, confirmPassword;
        UserInfo userInfo = new UserInfo();

        // Prompt the user to enter a username
        username = promptForUsername(scanner);
        userInfo.setUserName(username);

        // Inner do-while loop ensures the password and its confirmation are validated
        do {
            System.out.println("Password must be 8-20 characters long, contain uppercase, lowercase, digits, and special characters.");
            System.out.print("Password: ");
            password = scanner.nextLine();

            System.out.print("Confirm Password: ");
            confirmPassword = scanner.nextLine();

            // Check if passwords match
            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match. Please try again.");
            } else if (!Validators.isValidPassword(password)) { // Check if password meets complexity requirements
                System.out.println("Password does not meet complexity requirements. Please try again.");
            }
        } while (!password.equals(confirmPassword) || !Validators.isValidPassword(password));

        System.out.println("Congrats! You can now freely reserve preferred room.");

        // Call PreferRole.selectPreferRole() with the userInfo object
        PreferRole.selectPreferRole(userInfo);

        return userInfo; // Return the populated UserInfo object
    }


    /**
     * Prompts the user to enter a username.
     *
     * @param scanner The Scanner object to read user input.
     * @return The username entered by the user.
     */
    private static String promptForUsername(Scanner scanner) {
        System.out.print("Enter a Username: ");
        return scanner.nextLine();
    }
}
