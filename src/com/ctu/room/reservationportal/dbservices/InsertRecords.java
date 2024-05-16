package com.ctu.room.reservationportal.dbservices;

import com.ctu.room.reservationportal.model.UserInfo;

import java.sql.*;

public class InsertRecords {

    private static final String INSERT_USERS_SQL = "INSERT INTO userinfo (" +
            " firstName, middleName, lastName, birthDate, email, phoneNumber, street, barangay, municipality, city, zipcode, nationality, gender, roleAtschool, idNumber, userName) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;

    public InsertRecords() {
        this.jdbcURL = "jdbc:mysql://localhost:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true";
        this.jdbcUsername = "root";
        this.jdbcPassword = "admin123$";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading JDBC MySQL Driver", e);
        }
    }

    public void insertUserRecord(UserInfo userInfo) {
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

            preparedStatement.setString(1, userInfo.getFirstName());
            preparedStatement.setString(2, userInfo.getMiddleName());
            preparedStatement.setString(3, userInfo.getLastName());
            preparedStatement.setString(4, userInfo.getBirthdate());
            preparedStatement.setString(5, userInfo.getEmail());
            preparedStatement.setLong(6, userInfo.getPhoneNumber());
            preparedStatement.setString(7, userInfo.getStreet());
            preparedStatement.setString(8, userInfo.getBarangay());
            preparedStatement.setString(9, userInfo.getMunicipality());
            preparedStatement.setString(10, userInfo.getCity());
            preparedStatement.setInt(11, userInfo.getZIPcode());
            preparedStatement.setString(12, userInfo.getNationality());
            preparedStatement.setString(13, userInfo.getGender());
            preparedStatement.setString(14, userInfo.getRoleAtschool());
            preparedStatement.setString(15, userInfo.getUserName());
            preparedStatement.setInt(16, userInfo.getIdNumber());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            } else {
                System.out.println("Failed to insert user data.");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting user data to DB: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        // Create a new instance of UserInfo with sample data
        UserInfo userInfo = new UserInfo();

        userInfo.setFirstName(userInfo.getFirstName());
        userInfo.setMiddleName(userInfo.getMiddleName());
        userInfo.setLastName(userInfo.getLastName());
        userInfo.setBirthDate(String.valueOf(userInfo.getBirthdate()));
        userInfo.setEmail(userInfo.getEmail());
        userInfo.setPhoneNumber(String.valueOf(userInfo.getPhoneNumber()));
        userInfo.setStreet(userInfo.getStreet());
        userInfo.setBarangay(userInfo.getBarangay());
        userInfo.setMunicipality(userInfo.getMunicipality());
        userInfo.setCity(userInfo.getCity());
        userInfo.setZIPcode(userInfo.getZIPcode());
        userInfo.setNationality(userInfo.getNationality());
        userInfo.setGender(userInfo.getGender());
        userInfo.setRoleAtschool(userInfo.getRoleAtschool());
        userInfo.setUserName(userInfo.getUserName());
        userInfo.setIdNumber((userInfo.getIdNumber()));

        // Create an instance of InsertRecords
        InsertRecords insertRecords = new InsertRecords();

        // Call insertUserRecord method to insert the user info into the database
        insertRecords.insertUserRecord(userInfo);
    }

}
