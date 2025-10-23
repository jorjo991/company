package org.solvd.company.person;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public abstract class Person {

    private int age;
    private String name;
    private String surname;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;

    public Person(int age, String name, String surname, String email, Date birthday) {
        this.age = age;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
    }

    public Person() {

    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", birthDay=" + birthday +
                '}';
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDay() {
        return birthday;
    }

    public void setBirthDay(Date birthDay) {
        this.birthday = birthDay;
    }
}
