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
    @Autowired
    private Customers customers;
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

        String sql = "SELECT COUNT(*) FROM customers " +
                "WHERE LOWER(TRIM(email)) = LOWER(TRIM(?)) " +
                "AND TRIM(password) = TRIM(?)";

        System.out.println("Email received: [" + email + "]");
        System.out.println("Password length: " + password.length());

        Integer rows = jdbcTemplate.queryForObject(
                sql, Integer.class, email.trim(), password.trim()
        );

        System.out.println("Matching rows: " + rows);

        if (rows != null && rows > 0) {
            email_string = email.trim();
            System.out.println("Login successful!");
            return 1;
        }

        System.out.println("Incorrect email or password");
        return 0;
    }

    public void manage() {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            //System.out.println("Enter your choice:");
            System.out.println("1. Search Rooms\n 2. My Bookings \n 3. My Profile \n 4.Change Password \n 5.Cancel Bookings" + " \n 6. Logout");
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
                    System.out.println("Enter your desired room number");
                    int room_number=sc.nextInt();
                    bookings.bookRoom(room_number, checkin, checkout, guests, rooms, email_string);
                    break;
                case 2:
                    bookings.myBookings(email_string);
                    break;
                case 3:
                    customers.getDetails(email_string);
                    break;
                case 4:
                    System.out.println("Enter your current password");
                    String password_old= sc.next();
                    System.out.println("Enter your new password");
                    String password_new = sc.next();
                    System.out.println("Enter your new password again");
                    String password_new2 = sc.next();
                    if(password_new.equals(password_new2)){
                        customers.changePassword(email_string, password_old, password_new, password_new2);
                    }else{
                        System.out.println("Password doesn't match, try again!!");
                    }
                    break;
                case 5:
                    bookings.myBookings(email_string);
                    System.out.println("Enter the room number for which you wish to cancel");
                    int roomNumber=sc.nextInt();
                    bookings.cancelBooking(roomNumber, email_string);
                    break;
            }
        }while(choice!=6);
    }
}
