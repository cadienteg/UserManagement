package com.ctu.room.reservationportal.infrastructure;

import com.ctu.room.reservationportal.dbservices.InsertRecords;
import com.ctu.room.reservationportal.model.UserInfo;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //Creating an instance of insertrecord class
        InsertRecords insertDetails= new InsertRecords();
        //Creating an instance of createuserinfo class
        CreateUserInfo registerUserService = new CreateUserInfo();
        //Creating an instance of userinfo class
        UserInfo userInfo = registerUserService.registerUser();
        //calling inseruserrecord methods
        insertDetails.insertUserRecord(userInfo);

    }
}
