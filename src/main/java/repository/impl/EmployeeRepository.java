package repository.impl;

import mapper.EmployeeMapper;
import model.entity.Employee;
import util.JdbcConnection;
import util.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository extends BaseRepository<Employee, Integer> {

    public EmployeeRepository() {
        super(new EmployeeMapper());
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_EMPLOYEES)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Employee employee = getMapper().map(result);
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return employees;
    }

    @Override
    public Employee findById(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_EMPLOYEE_BY_ID)) {
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
        // TODO: Implement a method which checks if an employee with the given id exists in the employees table
        Employee employee = findById(id) ;
        if(employee==null)
            return  false;
        else
            return  true ;
    }

    @Override
    public Boolean save(Employee employee) {
        /*
         * TODO: Implement a method which adds an employee to the employees table
         *  If the employee exists then the method should instead update the employee
         *
         */
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(Queries.INSERT_NEW_EMPLOYEE)) {
            boolean saves=false;
            if (exists(employee.getId())==true) {
                System.out.println("User exist Update it");
            }
            else {
                pstmt.setInt(1, employee.getId());
                pstmt.setString(2, employee.getLastName());
                pstmt.setString(3, employee.getFirstName());
                pstmt.setString(4, employee.getExtension());
                pstmt.setString(5, employee.getEmail());
                pstmt.setString(6, employee.getOfficeCode());
                pstmt.setInt(7, employee.getReportsTo());
                pstmt.setString(8, employee.getJobTitle());

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
    public Integer update(Employee employee) {
        /*
          * TODO: Implement a method which updates an employee with the given Employee instance
          *  The method should then return the number of updated records
         */
        int id = 0;

            try (Connection connection = JdbcConnection.connect();
                 PreparedStatement pstmt = connection.prepareStatement(Queries.UPDATE_EMPLOYEE)) {


                if (exists(employee.getId())==true) {
                    pstmt.setString(1, employee.getLastName());
                    pstmt.setString(2, employee.getFirstName());
                    pstmt.setString(3, employee.getExtension());
                    pstmt.setString(4, employee.getEmail());
                    pstmt.setString(5, employee.getOfficeCode());
                    pstmt.setInt(6, employee.getReportsTo());
                    pstmt.setString(7, employee.getJobTitle());
                    pstmt.setInt(8, employee.getId());
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
