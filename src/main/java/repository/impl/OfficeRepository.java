package repository.impl;

import mapper.OfficeMapper;
import model.entity.Office;
import util.JdbcConnection;
import util.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfficeRepository extends BaseRepository<Office, Integer> {

    public OfficeRepository() {
        super(new OfficeMapper());
    }

    @Override
    public List<Office> findAll() {
        List<Office> offices = new ArrayList<>();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_OFFICES)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Office office = getMapper().map(result);
                offices.add(office);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return offices;
    }

    @Override
    public Office findById(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_OFFICE_BY_ID)) {
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
        Office office = findById(id) ;
        if(office==null)
            return  false;
        else
            return  true ;
    }

    @Override
    public Boolean save(Office office) {
        /*
         * TODO: Implement a method which adds an employee to the employees table
         *  If the employee exists then the method should instead update the employee
         *
         */
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(Queries.INSERT_NEW_OFFICE)) {
            boolean saves=false;
            if (exists(office.getOfficeCode())==true) {
                System.out.println("OFFICE exist Update it");
            }
            else {
                pstmt.setInt(1, office.getOfficeCode());
                pstmt.setString(2, office.getCity());
                pstmt.setString(3, office.getPhone());
                pstmt.setString(4, office.getAddress1());
                pstmt.setString(5, office.getAddress2());
                pstmt.setString(6, office.getState());
                pstmt.setString(7, office.getCountry());
                pstmt.setString(8, office.getPostalCode());
                pstmt.setString(9, office.getTerritory());

                int affectedRows = pstmt.executeUpdate();


                if (affectedRows > 0) saves=true;
                else saves=false;
            }
            if(saves==true){
                System.out.println("OFFICE added sucessfully");
            }

            return saves;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public Integer update(Office office) {
        /*
         * TODO: Implement a method which updates an employee with the given Employee instance
         *  The method should then return the number of updated records
         */
        int id = 0;

        try (Connection connection = JdbcConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(Queries.UPDATE_OFFICE)) {


            if (exists(office.getOfficeCode())==true) {
                pstmt.setInt(1, office.getOfficeCode());
                pstmt.setString(2, office.getCity());
                pstmt.setString(3, office.getPhone());
                pstmt.setString(4, office.getAddress1());
                pstmt.setString(5, office.getAddress2());
                pstmt.setString(6, office.getState());
                pstmt.setString(7, office.getCountry());
                pstmt.setString(8, office.getPostalCode());
                pstmt.setString(9, office.getTerritory());
                id = pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(id>0){
            System.out.println("OFFICE UPDATED");
        }

        return id;
    }

}
