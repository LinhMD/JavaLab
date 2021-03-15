package DTO;

import Manager.InputValidation;

import java.util.Calendar;
import java.util.TreeSet;

public class Experience extends Candidate {
    private int yearOfExp;
    private String proSkill;

    public Experience() {
        super();
    }

    public int getYearOfExp() {
        return yearOfExp;
    }

    public String getProSkill() {
        return proSkill;
    }

    @Override
    public Candidate input(TreeSet<String> ids) {
        super.input(ids);
        this.yearOfExp = InputValidation.getAnInteger("Input year of experience: ", "Experience invalid", 0, Calendar.getInstance().get(Calendar.YEAR) - getBirthDay());
        this.proSkill = InputValidation.getString("Input candidate skill: ", "skill can not be empty");
        return this;
    }
    @Override
    public String toString() {
        return super.toString() + this.getClass().toString().substring(this.getClass().toString().lastIndexOf(".") + 1);
    }
}
