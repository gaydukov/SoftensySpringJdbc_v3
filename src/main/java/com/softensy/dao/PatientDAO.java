package com.softensy.dao;

import com.softensy.entity.Patient;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PatientDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/softensyFirst";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    private static Connection connection;

    private Patient createPatient(ResultSet resultSet) throws SQLException {

        Patient patient = new Patient();

        patient.setId(resultSet.getLong("id"));
        patient.setFirstName(resultSet.getString("firstName"));
        patient.setLastName(resultSet.getString("secondName"));
        patient.setMiddleName(resultSet.getString("middleName"));
        patient.setDoctorId(resultSet.getLong("doctorId"));
        patient.setDateOfBirth(resultSet.getDate("dateOfBirth"));
        patient.setPhoneNamber(resultSet.getLong("phoneNamber"));

        return patient;
    }

    private void creatStatement(Patient patient, PreparedStatement statement) throws SQLException {
        statement.setString(1,patient.getFirstName());
        statement.setString(2,patient.getLastName());
        statement.setString(3,patient.getMiddleName());
        statement.setLong(4,patient.getDoctorId());
        statement.setDate(5,patient.getDateOfBirth());
        statement.setLong(6,patient.getPhoneNamber());

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

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            String SQL = "SELECT * FROM patient";
            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next()) {
                patients.add(createPatient(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patients;
    }


    public Patient getPatientById(long id) {
        List<Patient> patients = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            String SQL = "SELECT * FROM patient where id="+id;
            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next()) {
                patients.add(createPatient(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patients.get(0);
    }



    public void save(Patient patient) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO patient (\"firstName\",\"secondName\",\"middleName\"," +
                "\"doctorId\",\"dateOfBirth\",\"phoneNamber\") VALUES(?,?,?,?,?,?)")){
            creatStatement(patient, statement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void update(Long id, Patient patient) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE patient SET \"firstName\"=?,\"secondName\"=?,\"middleName\"=?,\"doctorId\"=?," +
                "\"dateOfBirth\"=?,\"phoneNamber\"=? WHERE id="+id)){
            creatStatement(patient, statement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM patient WHERE id="+id)) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
