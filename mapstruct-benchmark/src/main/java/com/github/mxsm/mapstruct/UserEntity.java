package com.github.mxsm.mapstruct;

public class UserEntity {
    private String userName;
    private String address;
    private String pwd;
    private String email;
    private short age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
            "userName='" + userName + '\'' +
            ", address='" + address + '\'' +
            ", pwd='" + pwd + '\'' +
            ", email='" + email + '\'' +
            ", age=" + age +
            '}';
    }
}
