package models;

public class User {
    private String firstName;
    private String lastName;
    private String address1;
    private String postcode;
    private String city;
    private String country;
    private String email;
    private String phone;
    private String password;
    private String confirmedPassword;

    public User(String firstName, String lastName, String address1, String postcode, String city, String country, String email, String phone, String desiredPassword, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.password = desiredPassword;
        this.confirmedPassword = confirmPassword;
    }

    public User(String firstName, String lastName, String address1, String postcode, String city, String country, String email, String desiredPassword, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
        this.email = email;
        this.password = desiredPassword;
        this.confirmedPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress1() {
        return address1;
    }

    public User setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getPostcode() {
        return postcode;
    }

    public User setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public User setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public User setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
