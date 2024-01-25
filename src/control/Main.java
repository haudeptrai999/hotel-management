/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import bussiness.HotelManagement;
import tools.Menu;

/**
 *
 * @author DMX
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int mainMenuChoice, subMenuChoice;
        HotelManagement hm = new HotelManagement("Hotel.dat");
        
        do {
            mainMenuChoice = Menu.getChoice(7, "MAIN MENU", "Enter choice : ", "Please choose from 1-7!", "[1] Adding new Hotel", "[2] Checking exits Hotel", "[3] Updating Hotel information", "[4] Deleting Hotel", "[5] Searching Hotel", "[6] Displaying a hotel list", "[7] Quit");
            
            switch(mainMenuChoice) {
                case 1:
                    hm.addHotel();
                    break;
                case 2:
                    hm.checkHotelExist();
                    break;
                case 3:
                    hm.updateHotelInfo();
                    break;
                case 4:
                    hm.deleteHotel();
                    break;
                case 5:
                    do {
                        subMenuChoice = Menu.getChoice(3, "SEARCH MENU", "Enter choice : ", "Please choose from 1-3!", "[1] Seach by id", "[2] Seach by name", "[3] Return the main menu");
                       
                        switch (subMenuChoice) {
                            case 1:
                                hm.searchHotelById();
                                break;
                            case 2:
                                hm.searchHotelByName();
                                break;
                            case 3:
                                break;
                        }
                       
                    } while(subMenuChoice!=3);
                    break;
                case 6:
                    hm.printHotelData();
                    break;
                case 7:
                    System.out.println("Thank for using the application");
                    System.out.println("@Dev by haudeptrai999");
            }
        } while(mainMenuChoice!=7);
    }
    
}
