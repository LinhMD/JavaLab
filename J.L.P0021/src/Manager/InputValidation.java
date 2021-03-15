package Manager;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author USER
 */
public class InputValidation {


    private static Scanner sc = new Scanner(System.in);
       //nhập vào một chuỗi kí tự, khác rỗng
    public static int getAnInteger(String inputMsg, String errorMsg) {
        if(inputMsg.isEmpty())
            inputMsg = "Input an integer: ";
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    public static int getAnInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n, tmp;
        if(inputMsg.isEmpty())
            inputMsg = "Input an integer";
        //nếu đầu vào lower > upper thì đổi chỗ
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound)
                    throw new Exception();                
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    public static double getADouble(String inputMsg, String errorMsg) {
        double n;
        if(inputMsg.isEmpty())
            inputMsg = "Input an double";
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    public static double getADouble(String inputMsg, String errorMsg, double lowerBound, double upperBound) {
        double n, tmp;
        if(inputMsg.isEmpty())
            inputMsg = "Input an double";
        //nếu đầu vào lower > upper thì đổi chỗ
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < lowerBound || n > upperBound)
                    throw new Exception();                
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    public static String getID(String inputMsg, String errorMsg, String format) {
        String id;
        if(inputMsg.isEmpty())
            inputMsg = "Input String format '" + format+ "'";
        if(errorMsg.isEmpty())
            errorMsg = "String invalid";
        boolean match;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();
            match = id.matches(format);
            if (id.length() == 0 || !match)
                System.out.println(errorMsg);
            else
                return id;
        }
    }

    public static String getString(String inputMsg, String errorMsg) {
        String id;
        if(inputMsg.isEmpty())
            inputMsg = "Input an String";
        if(errorMsg.isEmpty())
            errorMsg = "String cant be empty";
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();            
            if (id.length() == 0)
                System.out.println(errorMsg);
            else 
                return id;
        }
    }
    public static boolean getYN(String inputMsg){
        if(inputMsg.isEmpty())
            inputMsg = "Do you want to continue?[y/n, Y/N]";
        String YN = getID(inputMsg, "Input invalid. Please input Y/N, y,n only","^[YNyn]$");
        return YN.equalsIgnoreCase("y");
    }

    public static String getCourse(){
        String input;
        do{
            input = InputValidation.getString("Input Course: ", "Course can be empty");
            if(!(input.equalsIgnoreCase("Java") || input.equalsIgnoreCase(".Net")|| input.equalsIgnoreCase("C/C++"))){
                System.out.println("Course name must be Java|.Net|C/C++");
            }
        }while(!(input.equalsIgnoreCase("Java") || input.equalsIgnoreCase(".Net")|| input.equalsIgnoreCase("C/C++")));
        return input;
    }
}
