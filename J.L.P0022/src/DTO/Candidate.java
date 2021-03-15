package DTO;

import Manager.InputValidation;

import java.util.Calendar;
import java.util.TreeSet;

public class Candidate {
    public static final String idFormat = "^\\w{2}\\d{3}$";
    private static final String emailFormat = "^([\\w\\d_\\-\\.]+)@([\\w\\d_\\-]+)(\\.(\\w{2,6})){0,2}$";
    private static final String phoneFormat = "^0\\d{9,10}$";

    private String candidateID;
    private String firstName;
    private String lastName;
    private int birthDay;
    private String address;
    private String phone;
    private String email;

    public Candidate() {
    }

    public String getCandidateID() {
        return candidateID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Candidate input(TreeSet<String> ids){
        String id;
        do{
            id = InputValidation.getID("Input Candidate id: ", "Id invalid. Try format AB123", Candidate.idFormat);
            if(ids.contains(id))
                System.out.println("Id is duplicate");
        }while (ids.contains(id));
        this.candidateID = id;
        this.firstName = InputValidation.getString("Input Candidate first name: ", "first name can not be empty");
        this.lastName = InputValidation.getString("Input Candidate last name: ", "last name can not be empty");
        this.address =  InputValidation.getString("Input Candidate address: ", "Address can not be empty");
        this.email = InputValidation.getID("Input Candidate Email: ", "Email invalid", Candidate.emailFormat);
        this.phone = InputValidation.getID("Input Candidate phone: ", "Phone number invalid", Candidate.phoneFormat);
        this.birthDay = InputValidation.getAnInteger("Input Candidate birth day: ", "Birth day must between 1900 to current year", 1900, Calendar.getInstance().get(Calendar.YEAR));
        return this;
    }

    @Override
    public String toString() {
        return this.firstName +" " + this.lastName +
                " | " + this.birthDay +
                " | "+ this.address +
                " | " +this.phone +
                " | " + this.email +
                " | ";
    }
}
