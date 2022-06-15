package repository.impl;

import mapper.CustomerMapper;
import model.entity.Customer;
import util.JdbcConnection;
import util.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends BaseRepository<Customer, Integer> {

    public CustomerRepository() {
        super(new CustomerMapper());
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_CUSTOMERS)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Customer customer = getMapper().map(result);
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return customers;
    }

    @Override
    public Customer findById(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_CUSTOMER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return getMapper().map(result);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;
    }

    @Override
    public Boolean exists(Integer id) {
        // TODO: Implement a method which checks if an CUSTOMER with the given id exists in the CUSTOMERS table
        Customer customer = findById(id) ;
        if(customer==null)
            return  false;
        else
            return  true ;
    }

    @Override
    public Boolean save(Customer customer) {
        /*
         * TODO: Implement a method which adds an CUSTOMER to the CUSTOMERS table
         *  If the CUSTOMER exists then the method should instead update the CUSTOMER
         *
         */
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(Queries.INSERT_NEW_CUSTOMER)) {
            boolean saves=false;
            if (exists(customer.getCustomerNumber())==true) {
                System.out.println("User exist Update it");
            }
            else {
                pstmt.setInt(1, customer.getCustomerNumber());
                pstmt.setString(2, customer.getCustomerName());
                pstmt.setString(3, customer.getContactLastName());
                pstmt.setString(4, customer.getContactFirstName());
                pstmt.setString(5, customer.getPhone());
                pstmt.setString(6, customer.getAddress1());
                pstmt.setString(7, customer.getAddress2());
                pstmt.setString(8, customer.getCity());
                pstmt.setString(9, customer.getState());
                pstmt.setString(10, customer.getPostalCode());
                pstmt.setString(11, customer.getCountry());
                pstmt.setInt(12, customer.getSalesRepEmployeeNumber());
                pstmt.setDouble(13, customer.getCreditLimit());

                int affectedRows = pstmt.executeUpdate();


                if (affectedRows > 0) saves=true;
                else saves=false;
            }
            if(saves==true){
                System.out.println("User added sucessfully");
            }

            return saves;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public Integer update(Customer customer) {
        /*
         * TODO: Implement a method which updates an CUSTOMER with the given CUSTOMER instance
         *  The method should then return the number of updated records
         */
        int id = 0;

        try (Connection connection = JdbcConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(Queries.UPDATE_CUSTOMER)) {


            if (exists(customer.getCustomerNumber())==true) {
                pstmt.setString(1, customer.getCustomerName());
                pstmt.setString(2, customer.getContactLastName());
                pstmt.setString(3, customer.getContactFirstName());
                pstmt.setString(4, customer.getPhone());
                pstmt.setString(5, customer.getAddress1());
                pstmt.setString(6, customer.getAddress2());
                pstmt.setString(7, customer.getCity());
                pstmt.setString(8, customer.getState());
                pstmt.setString(9, customer.getPostalCode());
                pstmt.setString(10, customer.getCountry());
                pstmt.setInt(11, customer.getSalesRepEmployeeNumber());
                pstmt.setDouble(12, customer.getCreditLimit());
                pstmt.setInt(13, customer.getCustomerNumber());
                id = pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(id>0){
            System.out.println("USER UPDATED");
        }
        return id;
    }

}
