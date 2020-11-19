package com.example.Fashionista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class CustomerRepository {

    @Autowired
    private DataSource dataSource;

    public Customer getCustomer(Long id) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT id, firstName, lastName, email, address, address2, country, state, zip FROM Customer WHERE id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rsCustomer(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Customer getCustomerByEmail(String email) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT id, firstName, lastName, email, address, address2, country, state, zip FROM Customer WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rsCustomer(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean insertCustomer(Customer customer) {
        int generatedId = -1;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Customer (firstName, lastName, email, address, address2, country, state, zip) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getAddress2());
            ps.setString(6, customer.getCountry());
            ps.setString(7, customer.getState());
            ps.setString(8, customer.getZip());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId > 0;
    }

    private Customer rsCustomer(ResultSet rs) throws SQLException {
        return new Customer(rs.getLong("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getString("address2"),
                rs.getString("country"),
                rs.getString("state"),
                rs.getString("zip")
        );
    }

}
