/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Hotel;

/**
 *
 * @author DMX
 */
public class Data {
   
    /**
     * The function check is file exist.
     * @param filePath The file path of the file you want to check.
     * @return true if file exist or false if not exist.
     */
    public static boolean isFileExists(String filePath) {
        File fi = new File(filePath);
        return fi.exists();
    }
    
    /**
     * The function to store the data to file.
     * @param filePath The file path of the file you want to store data.
     * @param hotelList The hotel list data.
     */
    public static void storeData(String filePath, Map<String, Hotel> hotelList) {
        try {
            File fi = new File(filePath);
            FileOutputStream fos = new FileOutputStream(fi);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(hotelList);
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot found the file store data!");
//            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * The function to load the data to file.
     * @param filePath The file path of the file you want to load data.
     * @return The hotel list data.
     */
    public static Map<String, Hotel> loadData(String filePath) {
        Map<String, Hotel> data = null;
        try {
            File fi = new File(filePath);
            FileInputStream fis = new FileInputStream(fi);
            ObjectInputStream ois;
            if (fis.available() > 0) {
                ois = new ObjectInputStream(fis);
                data = (Map<String, Hotel>)ois.readObject();
                ois.close();
            }
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot found the file store data!");
//            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}
