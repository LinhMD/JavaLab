package DTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

class Courses {
    // thầy kêu là một môn học có thể bị rớt rồi học lại kỳ khác nên TreeSet<String> là để lưu trữ semester, key là môn học
    public final HashMap<String, TreeSet<String>> course;
    public static final String semesterFormat = "(spring|fall|summer|winter) (20)\\d{2}";
    public Courses(){
        this.course = new HashMap<>();
    }

    public boolean removeSemester(String course, String semester){
        if(this.course.containsKey(course))
            return this.course.get(course).remove(semester);
        else
            return false;
    }

    public boolean removeCourse(String course){
        return this.course.remove(course) != null;
    }

    public boolean addCourse(String course, String semester){
        TreeSet<String> semesters;
        if(this.course.containsKey(course))
            semesters = this.course.get(course);
        else
            semesters = new TreeSet<>();
        boolean check = semesters.add(semester);
        this.course.put(course, semesters);
        return check;
    }
    //ex:Java | 2, .Net | 2
    public ArrayList<String> getCourseAndNum(){
        Collection<String> courseKey = this.course.keySet();
        ArrayList<String> courseAndRetakeNum = new ArrayList<>();
        for (String course: courseKey)
            courseAndRetakeNum.add(course + " | " + this.course.get(course).size());
        return courseAndRetakeNum;
    }
}
