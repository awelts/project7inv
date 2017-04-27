/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inv;


import javafx.application.Application;

/**
 *
 * @author wmc
 */
public abstract class main extends Application{
    
public static items[] entryList = new items[200];
public static int numele=0;
    public static void main(String[] args)
    {	
	init.readIn("database.txt");
    }
    
}
