/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class init {
    
    public static void readIn(String filename)
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
	    ++main.numele;
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
		System.out.println(line);
		try {
		int where, where2;
		where2=line.indexOf(" ");
		main.entryList[place].setItem(line.substring(0, where2));
		where=where2;
		where2=line.indexOf(" ", where+1);
		main.entryList[place].setQuantity(Integer.parseInt(line.substring(where+1, where2)));
		where=where2;
		main.entryList[place].setNotes(line.substring(where+1));
		} catch (NullPointerException e) {
			System.err.print(e);
		}
	}
}