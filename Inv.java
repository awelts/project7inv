package inv;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Inv extends Application {
    static Stage window;
    TableView<items> table;
    TextField itemInput, quantityInput, notesInput, searchField;
    private static boolean searchItems=true;
    private static boolean searchNotes=true;
    
public static items[] entryList = new items[200];
public static int numele=0;
    public static void main(String[] args)
    {	
	for (int i=0; i < 200; ++i)
	    entryList[i]=new items();
	methods.init("database.txt");
	methods.merge();
	launch(args);
    }
    
    @Override
    public void stop()
    {
	methods.WriteInventory("database.txt");
	System.exit(0);
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
       TableColumn<items, String> quantityColumn=new TableColumn<>("quantity");
        quantityColumn.setMinWidth(150);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityString"));
    
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
        hbox.getChildren().addAll(itemInput, quantityInput, notesInput, addB, deleteB);
                
        table=new TableView<>();
	table.setEditable(true);
        table.getColumns().addAll(itemColumn);
	table.getColumns().add(quantityColumn);
	table.getColumns().add(notesColumn);
        table.setItems(refresh());
	
	//Items column editable
	itemColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        itemColumn.setOnEditCommit((CellEditEvent<items, String> t) -> {
	    ((items) t.getTableView().getItems().get(
		    t.getTablePosition().getRow())
		    ).setItem(t.getNewValue());
	    table.setItems(refresh());
	});
	
	//quantity column editable
	quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        quantityColumn.setOnEditCommit((CellEditEvent<items, String> t) -> {
	    ((items) t.getTableView().getItems().get(
		    t.getTablePosition().getRow())
		    ).setQuantity(t.getNewValue());
	    table.setItems(refresh());
	});
	
	//notes column editable
	notesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        notesColumn.setOnEditCommit((CellEditEvent<items, String> t) -> {
	    ((items) t.getTableView().getItems().get(
		    t.getTablePosition().getRow())
		    ).setNotes(t.getNewValue());
	    table.setItems(refresh());
	});
	
	//search
	searchField=new TextField();
        searchField.clear();
	searchField.setPromptText("search...");
	Button BAll= new Button("all");
	BAll.setOnAction(e -> {searchItems=true; searchNotes=true;});
	Button BNotes= new Button("notes");
	BNotes.setOnAction(e -> {searchItems=false; searchNotes=true;});
	Button close= new Button("Close");
	Button BName=new Button("items");
	BName.setOnAction(e -> {searchItems=true; searchNotes=false;});
	HBox searchbox= new HBox();
	searchbox.getChildren().addAll(searchField, BAll, BName, BNotes);
	searchbox.setPadding(new Insets(10,10,10,10));
        searchbox.setSpacing(10);
	table.setItems(found(searchField.getText()));
	Timer timer = new Timer();
	timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
         table.setItems(found(searchField.getText()));   
        }
    }, 0, 20);
    

        VBox vbox=new VBox();
        vbox.getChildren().addAll(searchbox, table, hbox);
        
        Scene scene=new Scene(vbox);
        window.setScene(scene);
        window.show();
    }
    
    //add button clicked
    public void addButtonClicked(){
	int index=methods.exists(itemInput.getText());
	if (index == -1)
	{
	    entryList[numele].setItem(itemInput.getText());
	    entryList[numele].setQuantity(quantityInput.getText());
	    entryList[numele].setNotes(notesInput.getText());
	    ++numele;
	}
	else
	{
	    entryList[index].setItem(itemInput.getText());
	    entryList[index].addToQuantity(quantityInput.getText());
	    entryList[index].setNotes(notesInput.getText());
	    if (entryList[index].getQuantityInt() == 0)
		methods.remove(index);
	}
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
	    Items.add(new items(entryList[i].getItem(), entryList[i].getQuantityString(), entryList[i].getNotes()));
	}
        return Items;
    }

    public static ObservableList<items> found(String text)
    {
	ObservableList<items> results=FXCollections.observableArrayList();
	for (int i=0; i < Inv.numele; ++i)
	{
	    if (searchItems)
	    if (Inv.entryList[i].getItem().matches(".*"+text+".*"))
		results.add(new items(Inv.entryList[i].getItem(), Inv.entryList[i].getQuantityString(), Inv.entryList[i].getNotes()));
	}
	for (int i=0; i < Inv.numele; ++i)
	{
	    if (searchNotes)
	    if (Inv.entryList[i].getNotes().matches(".*"+text+".*"))
		results.add(new items(Inv.entryList[i].getItem(), Inv.entryList[i].getQuantityString(), Inv.entryList[i].getNotes()));
	}
	return results;
    }

}