/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Scanner;

/**
 *
 * @author DMX
 */
public class Inputter {
    public static Scanner sc =  new Scanner(System.in);
    
    /**
     * The function use to input string.
     * @param msg The instruction message when input string.
     * @return String that users input.
     */
    public static String inputStr(String msg) {
        System.out.print(msg);
        String data = sc.nextLine().trim();
        return data;
    }
    
    /**
     * The function use to input integer.
     * @param msg The instruction message when input integer.
     * @return Integer that users input.
     */
    public static int inputInt(String msg) {
        int data = 0;
        boolean accept = false;
        
        do {
            try {
                System.out.print(msg);
                data = Integer.parseInt(sc.nextLine());
                accept = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter number!");
            }
            
        }while(!accept);
        
        return data;
    }
    
    /**
     * The function use to input integer with the limit.
     * @param msg The instruction message when input integer.
     * @param msgLimit The instruction message when users input integer wrong with the limit.
     * @param min The minimum of input integer.
     * @param max The maximum of input integer.
     * @return Integer that users input.
     */
    public static int inputIntLimit(String msg, String msgLimit, int min, int max) {
        if (min > max) {
            int t = min;
            min = max;
            max = t;
        }
        int data;
        do {
            data = inputInt(msg);
            if (data < min || data > max) {
                System.out.println(msgLimit);
            }
        } while(data < min || data > max);
        return data;
    }
    
    
    /**
     * The function use to input string that not blank.
     * @param msg The instruction message when input string.
     * @param msgBlank The instruction message when users input blank string.
     * @return String that users input.
     */
    public static String inputNonBlankStr(String msg, String msgBlank) {
        String data;
        do {
            System.out.print(msg);
            data = sc.nextLine().trim();
            if (data.length()==0) {
                System.out.println(msgBlank);
            }
        } while(data.length()==0);
        return data;
    }
    
    /**
     * The function use to input string that match with the pattern.
     * @param msg The instruction message when input string.
     * @param pattern The pattern of string.
     * @param msgNotMatch The instruction message when users input string that not match with the pattern.
     * @return String that users input.
     */
    public static String inputValidate(String msg, String pattern, String msgNotMatch) {
        String data;
        do {
            System.out.print(msg);
            data = sc.nextLine().trim();
            if (!data.matches(pattern)) {
                System.out.println(msgNotMatch);
            } 
        } while(!data.matches(pattern));
        return data;
    }
    
}
