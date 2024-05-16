package com.ctu.room.reservationportal.infrastructure;

import java.util.Random;
import java.util.Scanner;

public class RegistrationCode {
    private static final int CODE_LENGTH = 6;
    private static int attempts = 3; // Number of attempts allowed

    /**
     * Generates a random 6-digit registration code
     *
     * @return a random 6-digit registration code
     */
    public static String generateCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();

        // Generates each digit of the registration code by appending a random digit (0-9) to the codeBuilder StringBuilder
        // The loop runs for CODE_LENGTH times.
        for (int i = 0; i < CODE_LENGTH; i++) {
            int digit = random.nextInt(10);
            codeBuilder.append(digit);
        }

        // Returns the generated registration code as a string
        return codeBuilder.toString();
    }

    /**
     * The main method generates a registration code and prints it to the console.
     */
    public static void displayRegistrationCode() {
        Scanner scanner = new Scanner(System.in);

        String code = generateCode();
        System.out.println("Registration Code :" + code);

        boolean codeMatched = false;

        do {
            System.out.print("Enter Registration Code: ");
            String inputCode = scanner.nextLine();

            if (inputCode.equals(code)) {
                codeMatched = true;
                System.out.println("Registration code is valid.");
            } else {
                attempts--;
                if (attempts == 0) {
                    System.out.println("Maximum attempts reached. User registration failed.");
                    return; // Exit the registration process
                }

                System.out.println("Registration code does not match. Please try again.");
                System.out.println("You have " + attempts + " attempts remaining.");
            }
        } while (!codeMatched);

        System.out.println("User Registration Successful!");
        System.out.println("You can now Create An Account");
    }

    public static void main(String[] args) {
        displayRegistrationCode();
    }
}
