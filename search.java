/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inv;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author wmc
 */
public abstract class search extends Application {
    
    private static TextField searchField=new TextField();
    private static TableView<items> table2= new TableView<items>();
    
    public static void findButtonClicked()
    {
	searchField.clear();
	Stage searchbox=new Stage();
	searchbox.setTitle("Search");
	searchField.setPromptText("search...");
	Button b= new Button("search!");
	b.setOnAction(e-> table2.setItems(found(searchField.getText())));
	
    
	TableColumn<items, String> itemColumn=new TableColumn<>("Item");
        itemColumn.setMinWidth(150);
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("Item"));
    
        // quantity column
        TableColumn<items, Integer> quantityColumn=new TableColumn<>("quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    
        // notes column
        TableColumn<items, String> notesColumn=new TableColumn<>("notes");
        notesColumn.setMinWidth(300);
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
	
	table2=new TableView<>();
        table2.getColumns().addAll(itemColumn, quantityColumn, notesColumn);
	
	HBox hbox= new HBox();
	hbox.getChildren().addAll(searchField, b);
	hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(10);
	VBox vbox= new VBox();
	vbox.getChildren().addAll(hbox, table2 );
	
	Scene scene= new Scene(vbox);
	searchbox.setScene(scene);
	searchbox.show();
	
    }
    public static ObservableList<items> found(String text)
    {
	ObservableList<items> results=FXCollections.observableArrayList();
	for (int i=0; i < Inv.numele; ++i)
	{
	    if (Inv.entryList[i].getItem().matches(".*"+text+".*"))
		results.add(new items(Inv.entryList[i].getItem(), Inv.entryList[i].getQuantity(), Inv.entryList[i].getNotes()));
	}
	for (int i=0; i < Inv.numele; ++i)
	{
	    if (Inv.entryList[i].getNotes().matches(".*"+text+".*"))
		results.add(new items(Inv.entryList[i].getItem(), Inv.entryList[i].getQuantity(), Inv.entryList[i].getNotes()));
	}
	return results;
    }
}
