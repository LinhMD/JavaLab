package DTO;

import Manager.InputValidation;

import java.util.TreeSet;

public class Intern extends Candidate{
    private String mayors;
    private String semester;
    private String university;
    public Intern(){
        super();
    }

    public String getMayors() {
        return mayors;
    }

    public String getSemester() {
        return semester;
    }

    public String getUniversity() {
        return university;
    }

    @Override
    public Candidate input(TreeSet<String> ids) {
        super.input(ids);
        this.mayors = InputValidation.getString("Input intern mayor: ", "mayor can not be empty");
        this.semester = InputValidation.getString("input semester: ", "semester can not be empty");
        this.university = InputValidation.getString("Input university: ", "University can not be empty");
        return this;
    }

    @Override
    public String toString() {
        return super.toString() + this.getClass().toString().substring(this.getClass().toString().lastIndexOf(".") + 1);
    }
}
