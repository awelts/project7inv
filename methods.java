/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author wmc
 */
public class methods {
    
    public static void init(String filename)
    {
	try
	{
	int i=0;
	FileReader fr=new FileReader(filename);
	BufferedReader br= new BufferedReader(fr);
	String line;
	line=br.readLine();
	do
	{
	    process(line, i);
	    ++Inv.numele;
	    line=br.readLine();
	}while ( ++i< 200 && line !=null);
	br.close();
	return;		
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	    System.exit(0);
	}
    }
    
    public static void process (String line, int place) /*takes lines, pastes into class entry*/
	{
		try {
		int where, where2;
		where2=line.indexOf(" ");
		Inv.entryList[place].setItem(line.substring(0, where2));
		where=where2;
		where2=line.indexOf(" ", where+1);
		Inv.entryList[place].setQuantity(Integer.parseInt(line.substring(where+1, where2)));
		where=where2;
		Inv.entryList[place].setNotes(line.substring(where+1));
		} catch (NullPointerException e) {
			System.err.print(e);
		}
	}
    public static void remove(int index)
    {
	items temp;
	temp=Inv.entryList[index];
	Inv.entryList[index]=Inv.entryList[Inv.numele-1];
	Inv.entryList[Inv.numele-1]=temp;
	--Inv.numele;
	
    }
    
    public static void WriteInventory(String FileName) /*Writes to the file*/
    {
	try
	{
	    PrintStream P  = new PrintStream(FileName);
	    for (int i=0; i < Inv.numele; i++) {
		    P.println(Inv.entryList[i].getItem()+ " " +Inv.entryList[i].getQuantity()  + " " +Inv.entryList[i].getNotes());
	    }
	    P.close();
	    return;
	}
	catch(Exception e)
	{
	    return;
	}
    }
}
