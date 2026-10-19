package com.HotelManagementSystem.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Scanner;

@Component
@Scope("singleton")
public class Hotel {
    String sql;
    int rows;
    String email_string;
    @Autowired
    private BookingsManagement bookings;
    private JdbcTemplate jdbcTemplate;

    public Hotel(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int signup(String name, String phoneNumber, String email2, String password2) {
        sql="INSERT INTO customers (name, email, phone, password) VALUES (?,?,?,?)";
        rows=jdbcTemplate.update(sql, name, email2, phoneNumber,password2);
        if(rows>0){
            return 1;
        }
        else{
            return 0;
        }
    }

    public int login(String email, String password) {
        sql="SELECT COUNT(*) FROM CUSTOMERS WHERE EMAIL=? AND PASSWORD=?";
        rows=jdbcTemplate.queryForObject(sql, Integer.class, email, password);
        if(rows==1){
            email_string=email;
            return 1;

        }else{
            return 0;
        }
    }

    public void manage() {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            //System.out.println("Enter your choice:");
            System.out.println("1. Search Rooms\n 2. My Bookings \n 3. My Profile \n 4.Change Password \n 5.Cancel Booking \n 6. Logout");
            switch (choice = sc.nextInt()) {
                case 1:
                    System.out.println("Enter your check in date(DDMMYYYY): ");
                    String checkin = sc.next();
                    System.out.println("Enter your check out date(DDMMYYYY): ");
                    String checkout = sc.next();
                    System.out.println("Enter the number of guests: ");
                    int guests = sc.nextInt();
                    System.out.println("Enter the number of rooms: ");
                    int rooms = sc.nextInt();
                    bookings.checkRooms(checkin, checkout, rooms);
                    break;
                case 2:
                    bookings.myBookings(email_string);
                    break;

            }
        }while(choice!=6);
    }
}
