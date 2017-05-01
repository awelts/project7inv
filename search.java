package inv;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public abstract class search extends Application {
    
    
    private static TableView<items> table2= new TableView<items>();
    private static TextField searchField=new TextField();
    Stage searchbox= new Stage();
    private static boolean searchItems=true;
    private static boolean searchNotes=true;
    
    public static void findButtonClicked()
    {
	searchField.clear();
	Stage searchbox=new Stage();
	searchbox.setTitle("Search");
	searchField.setPromptText("search...");
	Button BAll= new Button("all");
	BAll.setOnAction(e -> {searchItems=true; searchNotes=true;});
	Button BNotes= new Button("notes");
	BNotes.setOnAction(e -> {searchItems=false; searchNotes=true;});
	Button close= new Button("Close");
	close.setOnAction(e -> searchbox.close());
	Button BName=new Button("items");
	BName.setOnAction(e -> {searchItems=true; searchNotes=false;});
	
	//item column
	TableColumn<items, String> itemColumn=new TableColumn<>("Item");
	itemColumn.setMinWidth(150);
	itemColumn.setCellValueFactory(new PropertyValueFactory<>("Item"));
	
	// quantity column
	TableColumn<items, Integer> quantityColumn=new TableColumn<>("quantity");
	quantityColumn.setMinWidth(100);
	quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityString"));
	
	// notes column
	TableColumn<items, String> notesColumn=new TableColumn<>("notes");
	notesColumn.setMinWidth(300);
	notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
	
	table2=new TableView<>();
	table2.getColumns().addAll(itemColumn, quantityColumn, notesColumn);
	
	HBox hbox= new HBox();
	hbox.getChildren().addAll(searchField, BAll, BName, BNotes);
	hbox.setPadding(new Insets(10,10,10,10));
	hbox.setSpacing(10);
	VBox vbox= new VBox();
	vbox.getChildren().addAll(hbox, table2);
	table2.setItems(found(searchField.getText()));
	Scene scene= new Scene(vbox);
	searchbox.setScene(scene);
	searchbox.show();
	
	Timer timer = new Timer();
	timer.scheduleAtFixedRate(new TimerTask() {
	    @Override
	    public void run() {
		table2.setItems(found(searchField.getText()));
	    }
	}, 0, 20);
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