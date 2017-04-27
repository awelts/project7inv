package inv;

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

public class Inv extends Application {
    static Stage window;
    TableView<items> table;
    TextField itemInput, quantityInput, notesInput;
    
public static items[] entryList = new items[200];
public static int numele=0;
    public static void main(String[] args)
    {	
	for (int i=0; i < 200; ++i)
	    entryList[i]=new items();
	methods.init("database.txt");
	launch(args);
    }
    
    @Override
    public void stop()
    {
	methods.WriteInventory("database.txt");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window=primaryStage;
        window.setTitle("Inventory Manager");
    
         // name column
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
        
        //item input
        itemInput=new TextField();
        itemInput.setPromptText("item");
        
        //quantity input
        quantityInput=new TextField();
        quantityInput.setPromptText("quantity");
        
        //notes input
        notesInput=new TextField();
        notesInput.setPromptText("notes");
        
        //Button
        Button addB=new Button("add");
        addB.setOnAction(e -> addButtonClicked());
        Button deleteB=new Button("delete");
        deleteB.setOnAction(e -> deleteButtonClicked());
        Button findB=new Button("search");
        findB.setOnAction(e -> search.findButtonClicked());
        
        
        //hbox
        HBox hbox=new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(itemInput, quantityInput, notesInput, addB, deleteB, findB);
                
        table=new TableView<>();
        table.setItems(refresh());
        table.getColumns().addAll(itemColumn, quantityColumn, notesColumn);
        
                
        VBox vbox=new VBox();
        vbox.getChildren().addAll(table, hbox);
        
        Scene scene=new Scene(vbox);
        window.setScene(scene);
        window.show();
    }
    
    //add button clicked
    public void addButtonClicked(){
	entryList[numele].setItem(itemInput.getText());
	entryList[numele].setQuantity(Integer.parseInt(quantityInput.getText()));
	entryList[numele].setNotes(notesInput.getText());
	++numele;
	table.getItems().clear();
        table.setItems(refresh());
        itemInput.clear();
        quantityInput.clear();
        notesInput.clear();
    }
    
    //delete button clicked
    public void deleteButtonClicked(){
	items temp;
	int i=0;
        temp=table.getItems().get(table.getSelectionModel().getSelectedIndex());
	System.out.println(temp.getItem());
	for (; !temp.getItem().equals(entryList[i].getItem()); ++i);
	methods.remove(i);
	table.getItems().clear();
        table.setItems(refresh());
    }
    
    
    
    public static ObservableList<items> refresh(){
        ObservableList<items> Items=FXCollections.observableArrayList();
	for (int i=0; i < numele; ++i)
	{
	    Items.add(new items(entryList[i].getItem(), entryList[i].getQuantity(), entryList[i].getNotes() ));
	}
        return Items;
    }


}