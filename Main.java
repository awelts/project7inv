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

public class Main extends Application {
    Stage window;
    TableView<items> table;
    TextField itemInput, quantityInput, notesInput;
        
    
    public static void main(String[] args){
        launch(args);
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
        //Button findB=new Button("find");
        //findB.setOnAction(e -> findButtonClicked());
        
        
        //hbox
        HBox hbox=new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(itemInput, quantityInput, notesInput, addB, deleteB);
                
        table=new TableView<>();
        table.setItems(getItems());
        table.getColumns().addAll(itemColumn, quantityColumn, notesColumn);
        
                
        VBox vbox=new VBox();
        vbox.getChildren().addAll(table, hbox);
        
        Scene scene=new Scene(vbox);
        window.setScene(scene);
        window.show();
    }
    
    //add button clicked
    public void addButtonClicked(){
        items listedItems=new items();
        listedItems.setItem(itemInput.getText());
        listedItems.setQuantity(Integer.parseInt(quantityInput.getText()));
        listedItems.setNotes(notesInput.getText());
        table.getItems().add(listedItems);
        itemInput.clear();
        quantityInput.clear();
        notesInput.clear();
    }
    
    //delete button clicked
    public void deleteButtonClicked(){
        ObservableList<items> itemSelected, allItems;
        allItems=table.getItems();
        itemSelected=table.getSelectionModel().getSelectedItems();
        
        itemSelected.forEach(allItems::remove);
    }
    
    //find button clicked
    //public void findButtonClicked(){
    //txtField= TextFields.createSearchField();
    //}
    
    
    public ObservableList<items> getItems(){
        ObservableList<items> Items=FXCollections.observableArrayList();
        Items.add(new items("cookie", 12, "soft"));
        return Items;
    }
}