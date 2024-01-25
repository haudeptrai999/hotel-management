/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Hotel;
import tools.Data;
import tools.Inputter;

/**
 *
 * @author DMX
 */
public class HotelManagement implements HotelManagementable{
    private Map<String, Hotel> hotelList;
    private String filePath;
    
    public HotelManagement(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     * This function will find and return the Hotel you want to search. 
     * @param id The id of hotel you want to search.
     * @return Hotel or null if don't find the hotel have the same id with @param id.
     */
    @Override
    public Hotel search(String id) {
        if (this.hotelList.containsKey(id)) {
            return this.hotelList.get(id);
        } else {
            return null;
        }
    }
    
    
    /**
     * This function use to adding hotel.
     */
    @Override
    public void addHotel() {
        boolean countinue = false;
        boolean IdDuplicated = false;
        String id, name, address, phone;
        int roomAvailable, rating;
        this.hotelList = Data.loadData(filePath);
        
        if (this.hotelList == null) {
            if (!Data.isFileExists(filePath)) {
                System.out.println("The system will auto create file to store data after you adding hotel!");
            }
            this.hotelList = new HashMap<>();
        }
        
        do {
            do {
                id = Inputter.inputNonBlankStr("Enter id : ", "Id cannot be empty!").toUpperCase();
                IdDuplicated = this.hotelList.containsKey(id);
                if (IdDuplicated) {
                    System.out.println("Id is duplicated!");
                }
            } while (IdDuplicated);
            name = Inputter.inputNonBlankStr("Enter name : ", "Name cannot be empty!");
            roomAvailable = Integer.parseInt(Inputter.inputValidate("Enter room available : ", "[0-9]*", "Please enter right number format!"));
            address = Inputter.inputNonBlankStr("Enter address : ", "Address cannot be empty!");
            phone = Inputter.inputValidate("Enter phone number : ", "0[0-9]{9}", "Please enter number with right format (10 number)!");
            rating = Inputter.inputIntLimit("Enter rating : ", "Please rate from 1-6 star", 1, 6);
            this.hotelList.put(id, new Hotel(id, name, roomAvailable, address, phone, rating));
            System.out.println("Adding hotel successfully!");
            countinue = Inputter.inputValidate("Do you want to countinue adding hotel?[Y(To countinue)/N(To go back the main menu)] : ", "[Yy]|[Nn]", "Please choose Y(To countinue) or N(To go back the main menu)").matches("Y|y");
        } while (countinue);
        Data.storeData(filePath, this.hotelList);
        
    }
    
    /**
     * This function will check is the hotel exist.
     */
    @Override
    public void checkHotelExist() {
        boolean countinue = false;
        this.hotelList = Data.loadData(filePath);
        
        do {
            if (this.hotelList!=null && !this.hotelList.isEmpty()) {
                String id = Inputter.inputNonBlankStr("Enter id : ", "Id to check hotel cannot be empty!").toUpperCase();
                Hotel hotel = search(id);
                if (hotel != null) {
                    System.out.println("Exist Hotel");
                } else {
                    System.out.println("No Hotel Found!");
                }
                countinue = Inputter.inputValidate("Do you want to countinue check hotel exist?[Y(To countinue)/N(To go back the main menu)] : ", "[Yy]|[Nn]", "Please choose Y(To countinue) or N(To go back the main menu)").matches("Y|y");
            } else {
                if (!Data.isFileExists(filePath)) {
                    System.out.println("Cannot check data from file not exist!");
                } else {
                    System.out.println("Hotel list is empty to check exist!");
                }
            }
        } while (countinue);
            
    }
    
    /**
     * This function use to update the hotel information.
     */
    @Override
    public void updateHotelInfo() {
        String id, name, address, phone, roomAvailable, rating;
        boolean roomAvailableCheck = false;
        this.hotelList = Data.loadData(filePath);
        
        if (this.hotelList!=null && !this.hotelList.isEmpty()) {
            id = Inputter.inputNonBlankStr("Enter id : ", "Id cannot be empty, it require to update hotel information!").toUpperCase();
            Hotel hotel = search(id);
            if (hotel!=null) {
                name = Inputter.inputStr("Enter name : ");
                if (!name.isEmpty()) {
                    hotel.setName(name);
                }
                roomAvailable = Inputter.inputValidate("Enter room available : ", "[0-9]*|", "Please enter number!");
                if (!roomAvailable.isEmpty()) {
                    hotel.setRoomAvailable(Integer.parseInt(roomAvailable));
                }
                address = Inputter.inputStr("Enter adress : ");
                if (!address.isEmpty()) {
                    hotel.setAddress(address);
                }
                phone = Inputter.inputValidate("Enter phone number : ", "0[0-9]{9}|", "Please enter number with right format (10 number)!");
                if (!phone.isEmpty()) {
                    hotel.setPhone(phone);
                }
                rating = Inputter.inputValidate("Enter rating : ", "[1-6]|", "Please rate from 1-6 star");
                if (!rating.isEmpty()) {
                    hotel.setRating(Integer.parseInt(rating));
                }
                System.out.println("Update information of hotel " + id + " successfull!");
//                System.out.println(" -----------------------------------------------------------------------------------------------");
//                System.out.println("|                                Information of hotel after update                              |");
//                System.out.println(" -----------------------------------------------------------------------------------------------");
//                System.out.println(String.format("| %-6s | %-10s | %-14s | %-30s | %-10s | %-8s |", "  ID", "   NAME", "ROOM AVAILABLE", "             ADDRESS", "   PHONE", " RATING"));
//                System.out.println(" -----------------------------------------------------------------------------------------------");
//                System.out.println(hotel.toString());
//                System.out.println(" -----------------------------------------------------------------------------------------------");
                Data.storeData(filePath, this.hotelList);
            } else {
                System.out.println("Hotel does not exist");
            }
        } else {
            if (!Data.isFileExists(filePath)) {
                System.out.println("Cannot update hotel data from file not exist!");
            } else {
                System.out.println("Hotel list is empty!");
            }
        }
    }
    
    /**
     * This function use to delete the hotel.
     */
    @Override
    public void deleteHotel() {
        this.hotelList = Data.loadData(filePath);
        String deleteAccept;
        
        if (this.hotelList != null && !this.hotelList.isEmpty()) {
            String id = Inputter.inputNonBlankStr("Enter id : ", "Id to check hotel cannot be empty!").toUpperCase();
            Hotel hotel = search(id);
            if (hotel != null) {
                deleteAccept = Inputter.inputValidate("Do you ready want to delete this hotel?[Y/N] : ", "[Yy]|[Nn]", "Please choose Y(Yes) or N(No)");
                if (deleteAccept.matches("Y|y")) {
                    this.hotelList.remove(hotel.getId());
                    System.out.println("Delete hotel with id" + " " + id + " " + "successfully!");
                    Data.storeData(filePath, this.hotelList);
                } else {
                    System.out.println("Delete hotel failed!");
                }
            } else {
                System.out.println("No Hotel Found!");
                
            }
        } else {
            if (!Data.isFileExists(filePath)) {
                System.out.println("Cannot delete hotel data from file not exist!");
            } else {
                System.out.println("Hotel list is empty to check exist!");
            }
        }
    }
    
    /**
     * This function use to search hotel by id.
     */
    @Override
    public void searchHotelById() {
        this.hotelList = Data.loadData(filePath);
        List<Hotel> hotelMatchList = new ArrayList<>();
        
        if (this.hotelList != null && !this.hotelList.isEmpty()) {
            String id = Inputter.inputStr("Enter id : ").toUpperCase();
            for (Hotel i : this.hotelList.values()) {
                if (i.getId().contains(id)) {
                    hotelMatchList.add(i);
                }
            }
            if (!hotelMatchList.isEmpty()) {
                Collections.sort(hotelMatchList, new Comparator<Hotel>() {
                    @Override
                    public int compare(Hotel o1, Hotel o2) {
                        if (o1.getId().compareToIgnoreCase(o2.getId()) < 0) {
                            return 1;
                        } else {
                            return -1;
                        }
                    } 
                });
                displayData(hotelMatchList);
            } else {
                System.out.println("No Hotel Found!");
            }
        } else {
            if (!Data.isFileExists(filePath)) {
                System.out.println("Cannot search hotel data from file not exist!");
            } else {
                System.out.println("Hotel list is empty to check exist!");
            }
        }
    }
    
    /**
     * This function use to search hotel by name.
     */
    @Override
    public void searchHotelByName() {
        this.hotelList = Data.loadData(filePath);
        List<Hotel> hotelMatchList = new ArrayList<>();
        
        if (this.hotelList != null && !this.hotelList.isEmpty()) {
            String name = Inputter.inputStr("Enter name : ");
            for (Hotel i : this.hotelList.values()) {
                if (i.getName().toLowerCase().equals(name.toLowerCase())) {
                    hotelMatchList.add(i);
                }
            }
            if (!hotelMatchList.isEmpty()) {
                displayData(hotelMatchList);
            } else {
                System.out.println("No Hotel Found!");
            }
        } else {
            if (!Data.isFileExists(filePath)) {
                System.out.println("Cannot search hotel data from file not exist!");
            } else {
                System.out.println("Hotel list is empty to check exist!");
            }
        }
    }
    
    /**
     * This function use to display all hotel data after sort by name.
     */
    @Override
    public void printHotelData() {
        this.hotelList = Data.loadData(filePath);
        
        if (this.hotelList!=null && !this.hotelList.isEmpty()) {
            ArrayList<Hotel> sortHotelList = new ArrayList<>(this.hotelList.values());
            Collections.sort(sortHotelList, new Comparator<Hotel>() {
                @Override
                public int compare(Hotel o1, Hotel o2) {
                    if (o1.getName().compareToIgnoreCase(o2.getName()) < 0) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                
            });
            displayData(sortHotelList);
        } else {
            if (!Data.isFileExists(filePath)) {
                System.out.println("Cannot read data from file not exist!");
            } else {
                System.out.println("Hotel list is empty!");
            }
        }
    }
    
    /**
     * This function use to display hotel list.
     * @param hotelData The hotel list you want to display.
     */
    public void displayData(List<Hotel> hotelData) {
        System.out.println(" -----------------------------------------------------------------------------------------------");
        System.out.println(String.format("|   %s   |    %s    | %-14s | %-30s |    %s   |  %s  |", "ID", "NAME", "ROOM AVAILABLE", "             ADDRESS", "PHONE", "RATING"));
        System.out.println(" -----------------------------------------------------------------------------------------------");
        for (Hotel i : hotelData) {
            System.out.println(i.toString());
        }
        System.out.println(" -----------------------------------------------------------------------------------------------");
    }
}
