/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author DMX
 */
public class Menu {
    
    /**
     * The function to make menu design.
     * @param num The numbers of character.
     * @param msg The character of design.
     * @return The design of character.
     */
    public static String menuDesign(int num, String msg) {
        String result = "";
        for (int i = 0; i < num; i++) {
            result = result + msg;
        }
        return result;
    }
    
    /**
     * 
     * @param numChoice The numbers of choice of the menu.
     * @param menuTitle The menu title.
     * @param msg The instruction message enter choice.
     * @param msgLimit The instruction message if enter choice not match with any choice.
     * @param choice The choice of the menu.
     * @return The choice of users.
     */
    public static int getChoice(int numChoice, String menuTitle, String msg, String msgLimit, String... choice) {
        int wordCount = 0;
        for (String i : choice) {
            if (i.length() > wordCount) {
                wordCount = i.length();
            }
        }
        
        System.out.println(" " + menuDesign(wordCount + 6, "-"));
        System.out.println(String.format("|%" + -(wordCount + 6) + "s|", menuDesign((int)Math.ceil(((wordCount + 6)-menuTitle.length())/2), " ") + menuTitle));
        System.out.println(" " + menuDesign(wordCount + 6, "-"));
        for (String i : choice) {
            System.out.println(String.format("| %" + -(wordCount + 5) + "s|", i));
        }
        System.out.println(" " + menuDesign(wordCount + 6, "-"));
        
        int getChoice = Inputter.inputIntLimit(msg, msgLimit, 1, numChoice);
        return getChoice;
    }
}
