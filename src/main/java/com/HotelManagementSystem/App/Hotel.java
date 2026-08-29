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
            return 1;
        }else{
            return 0;
        }
    }

    public void manage() {
        int choice;
        Scanner sc=new Scanner(System.in);

        switch(choice=sc.nextInt()){
            case 1:
        }
    }
}
