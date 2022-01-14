package com.fon.hotel.config.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class HotelUserDetails implements UserDetails {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String address;

    private HotelAuthority hotelAuthority;

    public HotelUserDetails() {

    }

    public HotelUserDetails(String username, String password, String firstName, String lastName, Date dateOfBirth, String address, HotelAuthority hotelAuthority) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.hotelAuthority = hotelAuthority;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
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

    public HotelAuthority getHotelAuthority() {
        return hotelAuthority;
    }

    public void setHotelAuthority(HotelAuthority hotelAuthority) {
        this.hotelAuthority = hotelAuthority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelUserDetails that = (HotelUserDetails) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public String toString() {
        return "HotelUserDetails{" +
                "username='" + username + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", hotelAuthority=" + hotelAuthority +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<HotelAuthority> list = new LinkedList<>();
        list.add(hotelAuthority);
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
