/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Todolist.Todo.src.main.LoginRegister;

import Todolist.Board.Board;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class saverLoader {
    public static void saveObjectToFile(ArrayList<Board> obj, String filename) {
        File f = new File(filename+".dat");
        try (FileOutputStream fos = new FileOutputStream(f);ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            System.out.println("Object successfully saved to " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Board> loadObjectFromFile(String filename) {
            ArrayList<Board> obj = new ArrayList<>();
            File f = new File(filename+".dat");
            if (f.exists()){
            try (FileInputStream fis = new FileInputStream(f);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                obj = (ArrayList<Board>) ois.readObject();
                System.out.println("Object successfully loaded from " + filename);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            }
            return obj;
    }
    
}
