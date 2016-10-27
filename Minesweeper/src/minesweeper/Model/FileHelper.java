/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A toolkit for creating, deleting, reading and writing Tiles from a board to a file. 
 * Prints exception messages to System.out
 * @author Viggo Lundén <vlunden@kth.se>
 */
public class FileHelper {
    /**
     * Creates a new file
     * @param filename the path and name of the file
     */
    public static void newFile(String filename)
    {
            
        try {
            File file = new File(filename);
            file.delete();
            file.createNewFile();
        } catch (IOException iOException) {
            System.out.println("Failed to create new file.");
            System.out.print(iOException.getMessage());
            System.out.println("");
        } 
    }
    
    /**
     * Deletes a file
     * @param filename The path and name of the file to delete
     */
    public static void deleteFile(String filename)
    {
        File file = new File(filename);
        file.delete();
    }
    

    /**
     * Writes a List of Tiles to a file 
     * @param tiles to be written
     * @param filename path
     */
    public static void write(List<Tile> tiles, String filename)
    {
        FileOutputStream out = null;
        try {
            File file = new File(filename);
            out = new FileOutputStream(file);
            ObjectOutputStream obout = new ObjectOutputStream(out);
            obout.writeObject(tiles);
        }
        catch (FileNotFoundException fnfex)
        {
            System.out.println("Could not find the file containing books!");
            System.out.print(fnfex.getMessage());
            System.out.println("");
        }
        catch (IOException ioex)
        {
            System.out.println("Could not write to the file, "
                    + "please make sure that it's not "
                    + "already in use by another process.");
            System.out.print(ioex.getMessage());
            System.out.println("");
        }
        finally {
            try {
                out.close();
            } catch (IOException iOException) {
                System.out.println("Failed to close file stream");
                System.out.println(iOException.getMessage());
            }
        }
    }
    /**
     * Reads a List of Tiles from file
     * @param filename the path and name of the file to read
     * @return ArrayList<Tile>. Null if the file is empty.
     */
    public static ArrayList<Tile> read(String filename)
    {
       ArrayList<Tile> tiles = new ArrayList<>();
       FileInputStream fin = null;
       try {
            fin = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fin);
            tiles = (ArrayList<Tile>) in.readObject();
       }
       catch (FileNotFoundException fnfex)
       {
            System.out.println("Could not find the file!");
            System.out.print(fnfex.getMessage());
            System.out.println("");
       }
       catch (IOException ioex)
       {
            System.out.println("Could not read from the file, "
                    + "please make sure that it's not "
                    + "already in use by another process.");
            System.out.print(ioex.getMessage());
            System.out.println("");
       }
       catch (ClassNotFoundException cnfex)
       {
           System.out.println("Failed to deserialize object");
           System.out.print(cnfex.getMessage());
           System.out.println("");
       }
       finally {
           try {
               fin.close();
           } catch (IOException iOException) {
               System.out.println("Failed to close file stream");
               System.out.println(iOException.getMessage());
           }
           return tiles;
       }
    }
}
