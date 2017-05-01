/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		try
		{
		    Matcher m=Pattern.compile("([\\w ]+):([\\d]+)[: ](.*)").matcher(line);
		    if (m.find())
		    {
			Inv.entryList[place].setItem(m.group(1));
			Inv.entryList[place].setQuantity(m.group(2));
			Inv.entryList[place].setNotes(m.group(3));
		    }
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
		    P.printf("%s:%s:%s%n",Inv.entryList[i].getItem(),Inv.entryList[i].getQuantityString(),Inv.entryList[i].getNotes());
	    }
	    P.close();
	    return;
	}
	catch(Exception e)
	{
	    return;
	}
    }
    public static int exists(String to_Search)
    {
	for (int i=0; i < Inv.numele; ++i)
	{
	    if (Inv.entryList[i].getItem().matches(to_Search))
		return i;
	}
	return -1;
    }
    
    public static int exists(String to_Search, int position)
    {
	for (int i=position; i < Inv.numele; ++i)
	{
	    if (Inv.entryList[i].getItem().matches(to_Search))
		return i;
	}
	return -1;
    }
    public static void merge()
    {
	int found_pos;
	for (int i=0; i < Inv.numele; ++i)
	{
	    found_pos=exists(Inv.entryList[i].getItem(), i+1);
	    if ( found_pos != -1)
	    {
		Inv.entryList[i].addToQuantity(Inv.entryList[found_pos].getQuantityString());
		remove(found_pos);
	    }
	}
    }
}
