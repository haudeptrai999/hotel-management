/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import models.Hotel;

/**
 *
 * @author DMX
 */
public interface HotelManagementable {
    Hotel search(String id);
    void addHotel();
    void checkHotelExist();
    void updateHotelInfo();
    void deleteHotel();
    void searchHotelById();
    void searchHotelByName();
    void printHotelData();
}
