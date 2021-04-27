package application;

import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class Controller implements Initializable{

    @FXML
    private AnchorPane connectScene, startScene, politicianScene, partyScene, searchScene;

    @FXML
    private TextField server, user, database, port, password;

    @FXML
    private Button connect, search;

    @FXML
    private Menu option;

    @FXML
    private PieChart political_party;
    private ObservableList<PieChart.Data> partyData;
    
    @FXML
    private ListView<String> poliInParty;

    @FXML
    private MenuItem homePS, homeParty, homeSearch,searchPS, searchStart, searchParty;

    @FXML
    private ImageView partyIconOne, partyIconTwo;

    @FXML
    private Image iconOne, iconTwo;

    @FXML
    private Label fullname, poliParty, offices, elecYear, elecType, race, hispanic, pronouns;
    
    private static ConnectionClass con;
    private ResultSet rs;
    
    int randOne, randTwo;
    
    // counts the number of candidates in the database and returns that number
    public int upperLimit() {
    	int num = 0;
    	try {
    		String query = "select count(C_ID) as numCandidates from candidateInfos;";
            rs = con.getCurrentConnection().prepareStatement(query).executeQuery();
            while( rs.next()){
                num = rs.getInt("numCandidates");
            }
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return num;
    }
    
    
    // assigns random numbers to variables so that we 
    public void setUpRandomNums() {
        Random rand = new Random();
        int upperBound = upperLimit();
        randOne = rand.nextInt( upperBound);
        randTwo = rand.nextInt( upperBound);
    }
    
    public ObservableList<PieChart.Data> pieChartData() {
        partyData = FXCollections.observableArrayList();
        try {

            String query = "select PartyName, count(PartyName) as numCandidates "
                    + "from candidateInfos where PartyName != '' group by PartyName "
                    + "order by PartyName asc;";
            rs = con.getCurrentConnection().prepareStatement(query).executeQuery();
            String name; int num;
            while(rs.next()) {
                name = rs.getString("PartyName");
                num = rs.getInt("numCandidates");
                partyData.add( new PieChart.Data( name, num ));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return partyData;
    }
    
    public ObservableList<String> politiciansParty( String PartyName){
    	ObservableList<String> poliList = FXCollections.observableArrayList(); 
    	try {

            String query = "select FirstName, LastName from candidateInfos natural join candidateNames where PartyName =\"REPUBLICAN\" order by LastName asc;";
            rs = con.getCurrentConnection().prepareStatement(query).executeQuery(); 
            String name;
            while(rs.next()) {
                name = rs.getString("FirstName") + " " + rs.getString("LastName");
                poliList.add(name);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    	return poliList;
    }


    // connectToDatabase
    // event handler that waits for the button to be clicked. Once it's clicked and the fields are
    // filled, it uses that data to connect to the MySQL database.
    public void connectToDatabase(ActionEvent e) throws IOException{
    	
    	// collects the data for the connection
        String serverStr = server.getText();
        String userStr = user.getText();
        String pwd = password.getText();
        String databaseName = database.getText();
        String portStr = port.getText();
        Integer portNum = Integer.parseInt( portStr);

        String url = "jdbc:mysql://" + serverStr + ":" + portNum + "/" + databaseName;
        Controller.con = new ConnectionClass( url, userStr, pwd);
        
        // moves onto the next panel if connection was successful
        Parent root = FXMLLoader.load(getClass().getResource("/resources/FXML/startScreen.fxml"));
        connect.getScene().setRoot(root);

    }
    
    public void goToParty(ActionEvent e) throws IOException{
    	poliInParty.setItems( politiciansParty("REPUBLICAN"));
    	Parent root = FXMLLoader.load(getClass().getResource("/resources/FXML/partyScreen.fxml"));
        politicianScene.getScene().setRoot( root);
    }
    
    // loads the politicians that are randomly placed in the d
    public void randPolitician(ActionEvent e) throws IOException{
    	
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resources/FXML/politicianScreen.fxml"));
            startScene.getScene().setRoot( root);
        } catch (IOException e2) {

            e2.printStackTrace();
        }
    }
    
    // ----- MENU BAR ACTIONS -----
    // Allows the user to move to search or home depending on the screen they are at
    // also allows them to end the application
    public void goToHome(ActionEvent e) throws IOException{
        Object source = e.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/FXML/startScreen.fxml"));
        if (source == homePS) { politicianScene.getScene().setRoot( root);}
    	if( source == homeSearch) { searchScene.getScene().setRoot(root);}
    	if( source == homeParty) { partyScene.getScene().setRoot(root);}

    }
    
    public void goToSearch(ActionEvent e) throws IOException{
    	Object source = e.getSource();
    	Parent root = FXMLLoader.load(getClass().getResource("/resources/FXML/searchScreen.fxml"));
    	if( source == searchPS) { politicianScene.getScene().setRoot(root);}
    	if( source == searchStart) { startScene.getScene().setRoot(root);}
    	if( source == searchParty) { partyScene.getScene().setRoot(root);}
    }
    
    public void exitApp(ActionEvent e) throws IOException{
    	System.exit(-1);
    }
    // --------- END MENU BAR ACTIONS --------------


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		poliInParty = new ListView<String>();
	}

}