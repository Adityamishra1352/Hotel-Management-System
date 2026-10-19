package com.HotelManagementSystem.App;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class BookingsManagement {
    String sql;
    int rows;
    private final JdbcTemplate jdbcTemplate;

    public BookingsManagement(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void checkRooms(String checkin, String checkout, int rooms) {
        sql="SELECT COUNT(*) FROM room where  status='A'";
        int rows=jdbcTemplate.queryForObject(sql, Integer.class);
        if(rows>0){
            sql="SELECT distinct room_id,room_type, price FROM room WHERE status = 'A'";
            RowMapper<Room> rowMapper=(rs, rowNum)->{
                int room_id = rs.getInt("room_id");
                String roomType=rs.getString("room_type");
                int price=rs.getInt("price");
                return new Room(roomType, price, room_id);
            };
            List<Room> availableRooms=jdbcTemplate.query(sql, rowMapper);

            for(Room room:availableRooms){
                System.out.println(room);
            }
        }
    }


    public void myBookings(String email_string) {

        sql = "SELECT COUNT(*) FROM bookings a " +
                "JOIN customers c ON a.customer_id = c.customer_id " +
                "WHERE c.email = ?";

        int rows = jdbcTemplate.queryForObject(
                sql, Integer.class, email_string
        );

        if (rows > 0) {

            sql = "SELECT a.booking_id, a.check_in, a.check_out, " +
                    "r.room_type, a.total_amount, a.status " +
                    "FROM bookings a " +
                    "JOIN customers c ON a.customer_id = c.customer_id " +
                    "JOIN room r ON a.room_id = r.room_id " +
                    "WHERE c.email = ?";

            List<Map<String, Object>> result =
                    jdbcTemplate.queryForList(sql, email_string);

            for (Map<String, Object> booking : result) {

                int bookingId = (Integer) booking.get("booking_id");
                String checkIn = (String) booking.get("check_in");
                String checkOut = (String) booking.get("check_out");
                String roomType = (String) booking.get("room_type");
                int totalAmount = (Integer) booking.get("total_amount");
                String status = (String) booking.get("status");

                System.out.println("Booking ID: " + bookingId);
                System.out.println("Check-in: " + checkIn);
                System.out.println("Check-out: " + checkOut);
                System.out.println("Room Type: " + roomType);
                System.out.println("Total Amount: " + totalAmount);
                System.out.println("Status: " + status);
                System.out.println("--------------------------");
            }

        } else {
            System.out.println("No bookings found.");
        }
    }

}
