package com.softensy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class Doctor {

    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String position;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date dateOfBirth;
    private long phoneNamber;

    public Doctor(long id, String firstName, String lastName, String middleName, String position, Date dateOfBirth, long phoneNamber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.position = position;
        this.dateOfBirth = dateOfBirth;
        this.phoneNamber = phoneNamber;
    }

    public Doctor(String firstName, String lastName, String middleName, String position, Date dateOfBirth, long phoneNamber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.position = position;
        this.dateOfBirth = dateOfBirth;
        this.phoneNamber = phoneNamber;
    }

    public Doctor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getPhoneNamber() {
        return phoneNamber;
    }

    public void setPhoneNamber(long phoneNamber) {
        this.phoneNamber = phoneNamber;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNamber=" + phoneNamber +
                '}';
    }
}
