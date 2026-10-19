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
}
