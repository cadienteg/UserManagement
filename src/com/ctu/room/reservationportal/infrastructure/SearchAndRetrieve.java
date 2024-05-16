package com.ctu.room.reservationportal.infrastructure;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
/**
 * Class for searching and retrieving details from database
 * Variables are declared final because these are constants
 * Encapsaluted for this is a connection to the database so must be private
 */
public class SearchAndRetrieve {
    /**
     * Database URL.
     */
    private static final String URL = "jdbc:mysql://localhost:3306/roomportaldb";
    // Username of the database
    private static final String USERNAME = "root";
    // Password for acessing the database
    private static final String PASSWORD = "admin123$";
    /**
     * Method for executing the search and retrieve
     * @param args
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
             // Declares an integer variable named choice without assigning it a value yet.
            int choice;
            do {
                // A menu for you to choose of what type of details you will use to search
                System.out.println("Choose search criteria:");
                System.out.println("1. Search by username");
                System.out.println("2. Search by first name, middle name, last name, or ID number");
                System.out.println("3. Search by other details");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                //Reads an integer input from the user and assigns it to the variable choice.
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                //Starts switch statement, evaluates the value of the variable choice to determine which case to execute.
                switch (choice) {
                    /*
                     * connecntion is passed because it represents a connection to the database
                     * scanner is used to collect user input from the console
                     */
                    case 1:
                        usernameSearch(connection, scanner);
                        break;
                    case 2:
                        filteredSearch(connection, scanner);
                        break;
                    case 3:
                        editProfileSearch(connection, scanner);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 4);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * A method for searching using username only
     * @param connection
     * @param scanner
     * @throws SQLException
     */
    private static void usernameSearch (Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        // Requesting for data from a database
        // adminfo is the TABLE FROM database
        String sql = "SELECT * FROM userinfo WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                displayResult(resultSet);
            } else {
                System.out.println("No details found for the provided username.");
            }
        }
    }

    /**
     * A method for filtered searches (first name, middle name, last name, and ID Number)
     * @param connection
     * @param scanner
     * @throws SQLException
     */
    private static void filteredSearch(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Choose field to search:");
        System.out.println("1. First Name");
        System.out.println("2. Middle Name");
        System.out.println("3. Last Name");
        System.out.println("4. ID Number");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String field;
        switch (choice) {
            case 1:
                field = "firstName";
                break;
            case 2:
                field = "middleName";
                break;
            case 3:
                field = "lastName";
                break;
            case 4:
                field = "idNumber";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Enter search term:");
        String searchTerm = scanner.nextLine();

        String sql = "SELECT * FROM userinfo WHERE " + field + " = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, searchTerm);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                displayResult(resultSet);
            } else {
                System.out.println("No details found for the provided search term.");
            }
        }
    }

    /**
     * A method for searching those updatable profile details aside from those in filtered search
     * @param connection
     * @param scanner
     * @throws SQLException
     */
    private static void editProfileSearch (Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Choose search query:");
        System.out.println("1. Search by birthdate");
        System.out.println("2. Search by email");
        System.out.println("3. Search by phone number");
        System.out.println("4. Search by street");
        System.out.println("5. Search by barangay");
        System.out.println("6. Search by municipality");
        System.out.println("7. Search by city");
        System.out.println("8. Search by nationality");
        System.out.println("9. Search by gender");
        System.out.println("10. Search by role at school");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String column;
        switch (choice) {
            case 1:
                column = "birthdate";
                break;
            case 2:
                column = "email";
                break;
            case 3:
                column = "phoneNumber";
                break;
            case 4:
                column = "street";
                break;
            case 5:
                column = "barangay";
                break;
            case 6:
                column = "municipality";
                break;
            case 7:
                column = "city";
                break;
            case 8:
                column = "nationality";
                break;
            case 9:
                column = "gender";
                break;
            case 10:
                column = "roleAtSchool";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Enter search term:");
        String searchTerm = scanner.nextLine();

        String sql = "SELECT * FROM userinfo WHERE " + column + " = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, searchTerm);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    displayResult(resultSet);
                } else {
                    System.out.println("No details found for the provided search term.");
                }
            }
        }
    }

    /**
     * A method to retrieve display the matched result from database
     * ResultSet represents a set of rows retrieved from a database after executing a query
     * @param resultSet
     * @throws SQLException
     */
    private static void displayResult(ResultSet resultSet) throws SQLException {
        // Retrieve and display data from the result set
        String firstName = resultSet.getString("firstName");
        String middleName = resultSet.getString("middleName");
        String lastName = resultSet.getString("lastName");
        String birthDate = resultSet.getString("birthDate");
        String email = resultSet.getString("email");
        String phoneNumber = resultSet.getString("phoneNumber");
        String street = resultSet.getString("street");
        String barangay = resultSet.getString("barangay");
        String municipality = resultSet.getString("municipality");
        String city = resultSet.getString("city");
        String ZIPcode = resultSet.getString("zipcode");
        String nationality = resultSet.getString("nationality");
        String gender = resultSet.getString("gender");
        String roleAtschool = resultSet.getString("roleAtschool");
        String idNumber = resultSet.getString("idNumber");
        String userName = resultSet.getString("userName");

        // Retrieve other columns as needed

        // Display the retrieved details
        System.out.println("First Name: " + firstName);
        System.out.println("Middle Name: " + middleName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Birthdate: " +birthDate);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Home Address: " + street + ", " + barangay + ", " + municipality + ", " + city + ", " + ZIPcode);
        System.out.println("Nationality: " + nationality);
        System.out.println("Gender: " + gender);
        System.out.println("Role at School: " + roleAtschool);
        System.out.println("ID Number: " + idNumber);
        System.out.println("Username: " + userName);


        // Display other details as needed
    }
}