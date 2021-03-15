package Manager;

import DTO.Student;

import java.util.ArrayList;

public class Students {
    private final ArrayList<Student> students;

    public Students(){
        this.students = new ArrayList<>();
    }

    public Students menu(){
        System.out.println("1. Created Students.");
        System.out.println("2. Find or sort student.");
        System.out.println("3. Update or delete student.");
        System.out.println("4. Report.");
        System.out.println("5. Exit.");
        switch (InputValidation.getAnInteger("Input your option: ", "Option invalid", 1,5)){
            case 1: this.addStudent(); break;
            case 2: this.findOrSortMenu(); break;
            case 3: this.updateOrDeleteMenu(); break;
            case 4: this.printAllReport(); break;
            case 5: return this;
            default:
                System.out.println("how did you get here?");
        }
        try{
            return this.menu();
        }catch (StackOverflowError ignore){
            return this.menu();
        }
    }

    private void addStudent(){
        boolean YN;
        do{
            if(this.students.add(this.inputStudent()))
                System.out.println("Add student successful");
            else
                System.out.println("Add student fail");
            if(this.students.size() < 3)
                YN = true;
            else
                YN = InputValidation.getYN("Do you want to add more student? [Y/N, y/n]");
        }while(YN);
    }

    private Student inputStudent(){
        String id;
        boolean check;
        do{
            id = InputValidation.getID("Input student ID: " ,"Student id invalid!!", Student.idFormat).toUpperCase();
            if(check = this.isIdDuplicate(id))
                System.out.println("Id is duplicate");
        }while (check);
        String name = InputValidation.getString("Input student name: ", "Student name can't be empty");
        return new Student(id.toUpperCase(), name);
    }

    private boolean isIdDuplicate(String id){
        for (Student student : this.students) if (student.getId().equalsIgnoreCase(id)) return true;
        return false;
    }

    private Students findOrSortMenu(){
        System.out.println("F. Find student by name");
        System.out.println("S. Sort student by name");
        System.out.println("X. Exit");
        switch (InputValidation.getID("input your option: ", "Option invalid", "F|S|X|f|s|x").toUpperCase()){
            case "F":
                this.findStudentByName();
                break;
            case "S":
                this.sortStudentByName();
                this.printAllStudent();
                break;
            case "X":
                return this;
        }
        return this.findOrSortMenu();
    }

    private void printAllStudent(){
        if(this.students.isEmpty())
            System.out.println("Student list is empty");
        else{
            System.out.printf("|%10s|%30s|%20s|%20s|\n", "Student Id", "Student Name", "Course", "Semester");
            for (Student student : this.students)
                student.printStudent();
        }
    }

    private Student getStudentByID(String id){
        for (Student student:this.students)
            if (student.getId().equalsIgnoreCase(id))
                return student;
        return null;
    }
    private void findStudentByName(){
        String name = InputValidation.getString("Input Student Name: ", "Name can not be empty");
        System.out.printf("|%10s|%30s|%20s|%20s|\n", "Student Id", "Student Name", "Course", "Semester");
        for (Student student : students)
            if (student.getName().contains(name)) student.printStudent();
    }

    private void sortStudentByName(){
        this.students.sort(Student.byName);
    }

    private void updateOrDeleteMenu(){
        String id = InputValidation.getID("Input student ID: " ,"Student id invalid!!", Student.idFormat).toUpperCase();
        if(!this.isIdDuplicate(id))
            System.out.println("Student not found");
        else {
            System.out.println("U. Update student");
            System.out.println("D. Delete student");
            switch (InputValidation.getID("input your option: ", "Option invalid", "U|D|X|u|d|x").toUpperCase()){
                case "U": this.updateStudentMenu(this.getStudentByID(id)); break;
                case "D": this.deleteStudent(this.getStudentByID(id)); break;
                default:
                    System.out.println("how did you get here??");
            }
        }
    }

    private Students updateStudentMenu(Student student){
        if(student == null){
            System.out.println("Student not found");
            return this;
        }
        System.out.printf("|%10s|%30s|%20s|%20s|\n", "Student Id", "Student Name", "Course", "Semester");
        student.printStudent();
        System.out.println("1. Change student name");
        System.out.println("2. Add course and semester");
        System.out.println("3. Delete course");
        System.out.println("4. Delete semester");
        System.out.println("5. Exit");
        switch (InputValidation.getAnInteger("input your option: ", "Option invalid",1, 5)){
            case 1: student.setName(InputValidation.getString("Input student name", "Student name can't be empty")); break;
            case 2: student.addCourse(); break;
            case 3: student.removeCourse(); break;
            case 4: student.removeSemester(); break;
            case 5: return this;
            default:
                System.out.println("how did you get here??");
        }
        return this.updateStudentMenu(student);
    }

    private void deleteStudent(Student student){
        if(this.students.remove(student))
            System.out.println("remove student " + student.getName() + " successful!");
        else
            System.out.println("Failed to remove student " + student.getName());
    }

    private void printAllReport(){
        for (Student student: this.students) student.report();
    }
}
