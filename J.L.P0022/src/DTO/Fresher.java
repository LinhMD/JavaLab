package DTO;

import Manager.InputValidation;

import java.util.Calendar;
import java.util.TreeSet;

public class Fresher extends Candidate {
    private int graduationDate;
    private String graduationRank;

    public Fresher(){
        super();
    }

    public int getGraduationDate() {
        return graduationDate;
    }

    public String getGraduationRank() {
        return graduationRank;
    }

    @Override
    public Candidate input(TreeSet<String> ids) {
        super.input(ids);
        this.graduationDate = InputValidation.getAnInteger("Input graduation date: ", "Graduation date invalid", this.getBirthDay(), Calendar.getInstance().get(Calendar.YEAR));
        this.graduationRank = InputValidation.getID("Input graduation rank: ", "Rank invalid. Try Excellence|Good|Fair|Poor", "Excellence|Good|Fair|Poor");
        return this;
    }
    @Override
    public String toString() {
        return super.toString() + this.getClass().toString().substring(this.getClass().toString().lastIndexOf(".") + 1);
    }
}
