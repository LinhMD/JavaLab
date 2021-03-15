package DTO;

import Manager.InputValidation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Student {
    public static final String idFormat = "\\w{2}\\d{3}";
    private final String id;
    private String name;
    private final Courses courses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new Courses();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCourse(){
        boolean YN;
        do {
            String course = InputValidation.getCourse().toLowerCase();
            String semester = InputValidation.getID("Input Semester: ", "Semester invalid", Courses.semesterFormat);
            if(!this.courses.addCourse(course, semester))
                System.out.println("Course and semester already existed");
            else
                System.out.println("Add new course successful");
            YN = InputValidation.getYN("Do you want to add more course [Y/N, y/n]");
        }while (YN);
    }

    public void removeSemester(){
        String course = InputValidation.getCourse().toLowerCase();
        String semester = InputValidation.getID("Input Semester: ", "Semester invalid", Courses.semesterFormat);
        if(this.courses.removeSemester(course, semester)){
            System.out.println("Remove semester successfully");
        }else
            System.out.println("Student did not have" + semester + "semester");
    }

    public void removeCourse(){
        String course = InputValidation.getCourse().toLowerCase();
        if (this.courses.removeCourse(course)){
            System.out.println("Delete course successful");
        }else
            System.out.println("Student did not have " + course + "course");
    }
    //ex: Nguyen Van A | Java | 2
    public void report(){
        ArrayList<String> courseAndNum = courses.getCourseAndNum();
        if(courseAndNum.isEmpty())
            System.out.println("Student " + this.getName() + "did not have any course");
        else
            for (String s : courseAndNum) {
                System.out.println(this.getName() + " | " + s);
            }
    }

    public void printStudent(){
        Set<String> courses = this.courses.course.keySet();
        if(courses.isEmpty()){
            System.out.printf("|%10s|%30s|%20s|%20s|\n", this.getId(), this.getName(),"", "");
        }else {
            boolean isFirstCourse = true;
            for (String course : courses) {
                boolean isFirstSemester = true;
                TreeSet<String> semesters = this.courses.course.get(course);
                for (String semester : semesters)
                    if (isFirstSemester && isFirstCourse) {
                        System.out.printf("|%10s|%30s|%20s|%20s|\n", this.getId(), this.getName(), course, semester);
                        isFirstCourse = false;
                        isFirstSemester = false;
                    } else if (isFirstSemester) {
                        System.out.printf("|%10s|%30s|%20s|%20s|\n", "", "", course, semester);
                        isFirstSemester = false;
                    } else
                        System.out.printf("|%10s|%30s|%20s|%20s|\n", "", "", "", semester);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getId().equals(student.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(getId(), getName()); }

    public static Comparator<Student> byName = Comparator.comparing(Student::getName);
}
