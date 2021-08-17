package com.softensy.dao;

import com.softensy.entity.Doctor;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/softensyFirst";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    private static Connection connection;

    private Doctor createDoctor(ResultSet resultSet) throws SQLException {

        Doctor doctor = new Doctor();

        doctor.setId(resultSet.getLong("id"));
        doctor.setFirstName(resultSet.getString("firstName"));
        doctor.setLastName(resultSet.getString("secondName"));
        doctor.setMiddleName(resultSet.getString("middleName"));
        doctor.setPosition(resultSet.getString("position"));
        doctor.setDateOfBirth(resultSet.getDate("dateOfBirth"));
        doctor.setPhoneNamber(resultSet.getLong("phoneNamber"));

        return doctor;
    }

    private void creatStatement(Doctor doctor, PreparedStatement statement) throws SQLException {
        statement.setString(1,doctor.getFirstName());
        statement.setString(2,doctor.getLastName());
        statement.setString(3,doctor.getMiddleName());
        statement.setString(4,doctor.getPosition());
        statement.setDate(5,doctor.getDateOfBirth());
        statement.setLong(6,doctor.getPhoneNamber());

        statement.executeUpdate();
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            String SQL = "SELECT * FROM Doctor";
            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next()) {
                doctors.add(createDoctor(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return doctors;
    }


    public Doctor getDoctorById(long id) {
        List<Doctor> doctors = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            String SQL = "SELECT * FROM Doctor where id="+id;
            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next()) {
                doctors.add(createDoctor(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return doctors.get(0);
    }



    public void save(Doctor doctor) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Doctor (\"firstName\",\"secondName\",\"middleName\"," +
                "\"position\",\"dateOfBirth\",\"phoneNamber\") VALUES(?,?,?,?,?,?)")){
            creatStatement(doctor, statement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void update(Long id, Doctor doctor) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE Doctor SET \"firstName\"=?,\"secondName\"=?,\"middleName\"=?,\"position\"=?," +
                "\"dateOfBirth\"=?,\"phoneNamber\"=? WHERE id="+id)){
            creatStatement(doctor, statement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Doctor WHERE id="+id)) {
       statement.executeUpdate();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    }
}

