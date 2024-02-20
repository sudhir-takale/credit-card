package org.amaap.task.birthdayreminder;


import java.time.LocalDate;

public class Friend {
    @Override
    public String toString() {
        return "Friend{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    private String fname,lname,email;
    private LocalDate birthDate;
    public Friend(String fname, String lname, LocalDate birthDate, String email) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


}
