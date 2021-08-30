package com.fresco.hikaricp;

import com.fresco.hikaricp.model.Customer;
import com.fresco.hikaricp.service.CustomerService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class HikariCpApplicationTests {

    @Autowired
    CustomerService customerService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    static String name;

    @Test
    public void test1_checkingHikari() throws SQLException {
        assertEquals(dataSource.toString(), "HikariDataSource (HikariPool-1)");
        assertEquals(dataSource.getLoginTimeout(), 60);
    }
    @Test
    public void test2_checkingData() {
        List<Customer> customers = customerService.getAllCustomers();
        assertEquals(customers.get(0).getName(), "fresco");
        assertEquals(customers.get(0).getEmail(), "fresco@tcs.com");
        assertEquals(customers.get(1).getName(), "play");
        assertEquals(customers.get(1).getEmail(), "play@tcs.com");
    }
    @Test
    public void test3_insertingData() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        name = sb.toString();
        customerService.addCustomer(name, name + "@tcs.com");
        List<Customer> customers = customerService.getAllCustomers();
        assertEquals(customers.get(2).getName(), name);
        assertEquals(customers.get(2).getEmail(), name + "@tcs.com");
    }
    @Test
    public void test4_checkingDB() {
        List<Customer> customers = jdbcTemplate.query("SELECT id, name, email, created_date FROM customer",
                (rs, rowNum) -> new Customer(rs.getInt("id"),
                        rs.getString("name"), rs.getString("email"), rs.getDate("created_date"))
        );
        assertEquals(customers.get(2).getName(), name);
        assertEquals(customers.get(2).getEmail(), name + "@tcs.com");
    }

}
