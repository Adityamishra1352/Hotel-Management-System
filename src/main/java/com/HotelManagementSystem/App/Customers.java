package com.HotelManagementSystem.App;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class Customers {
    String sql;
    private final JdbcTemplate jdbcTemplate;
    public Customers(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public void getDetails(String email_add){
        sql="select customer_id, name, email, phone from customers where email=?";
        Map<String, Object> result= jdbcTemplate.queryForMap(sql,email_add);

        System.out.println("\n------My Profile------");
        System.out.println("Customer ID: "+result.get("customer_id"));
        System.out.println("Name       : "+result.get("name"));
        System.out.println("Email      : " + result.get("email"));
        System.out.println("Phone      : "+result.get("phone"));
        System.out.println("------------------------");
    }

    public void changePassword(String emailString, String password_old, String password_new, String password_new2) {
        sql="SELECT COUNT(*) FROM CUSTOMERS WHERE email=? AND PASSWORD=?";
        int rows= jdbcTemplate.queryForObject(sql, Integer.class,emailString, password_old);
        if(rows>0){
            sql="UPDATE customers set password=? WHERE email=?";
            int rowsUpdate = jdbcTemplate.update(sql, password_new, emailString);
            if (rowsUpdate>0){
                System.out.println("Password updated successfully!!");
            }else{
                System.out.println("Password update unsuccessfull");
            }
        }
    }
}
