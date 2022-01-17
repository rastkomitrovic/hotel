package com.fon.hotel.dto;

import com.fon.hotel.config.auth.HotelAuthority;
import com.fon.hotel.config.auth.HotelUserDetails;

import javax.validation.constraints.*;
import java.util.Date;

public class UserDTO {

    private long userId;

    @Size(min = 2, max = 20, message = "Korisnicko ime mora imati minimum 2 a maksimum 20 karaktera")
    private String username;

    @Size(min = 4,max = 50, message = "Sifra mora imati minimum 4 a maksimum 50 karaktera")
    private String password;

    @Size(min = 4, max = 50, message = "Sifra mora imati minimum 4 a maksimum 50 karaktera")
    private String repeatPassword;

    @Size(min=4,max = 50,message = "Stara sifra mora imati minimum 4 a maksimum 50 karaktera")
    private String oldPassword;

    @Size(min = 2, max = 40, message = "Ime mora sadrzati minimum 2 a maksimum 40 karaktera")
    private String firstName;

    @Size(min = 2, max = 40, message = "Prezime mora sadrzati minimum 2 a maksimum 40 karaktera")
    private String lastName;

    @Past(message = "Datum rodjenja ne moze biti trenutni datum ili neki buduci datum")
    private Date dateOfBirth;

    @Size(min = 5, max = 50, message = "Adresa mora sadrzati barem 5 a maksimum 50 karaktera")
    private String address;

    @Size(min = 5,max = 15,message = "Broj pasosa mora sadrzati minimum 5 a maksimum 15 karaktera")
    private String passportNumber;

    @Size(min = 5, max = 50, message = "Email mora imati minimum 5 a maksimum 50 karaktera")
    @Email(message = "Niste uneli email adresu u validnom formatu")
    private String email;

    @Size(min = 5,max = 30, message = "Broj telefona mora imati minimum  5 a maksimum 50 karaktera")
    private String phoneNumber;

    private RoleDTO role;

    public UserDTO() {

    }

    public UserDTO(long userId, String username, String password, String repeatPassword, String oldPassword, String firstName, String lastName, Date dateOfBirth, String address, String passportNumber, String email, String phoneNumber, RoleDTO role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.oldPassword = oldPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.passportNumber = passportNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public HotelUserDetails toHotelUserDetails() {
        return new HotelUserDetails(username, password, firstName, lastName, dateOfBirth, address, new HotelAuthority(role.getRoleName()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO user = (UserDTO) o;
        return userId == user.userId;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                '}';
    }
}
