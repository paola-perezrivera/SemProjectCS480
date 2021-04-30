package application;

import java.io.IOException; 
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;
import java.util.*;


public class Controller implements Initializable{
	
    // location and resources will be automatically injected by the FXML loader 

    @FXML
    private AnchorPane connectScene, startScene;
    
    @FXML
    private Stage primaryStage = new Stage();

    @FXML
    private TextField server, user, database, port, password;

    @FXML
    private Button connect, search, party, gender, racePercent, start;
    
    @FXML
    private ComboBox<String> raceBox, yearBox, pronounsBox, officeBox, hispanicBox, partyBox;

    @FXML
    private Menu option;

    @FXML
    private PieChart pickPieChart;
    
    @FXML
    private ListView<String> ResultsList;
    
    private static ConnectionClass con;
    private ResultSet rs;
    
    // PieChart functions
    // populates an ObservableList of PieChart.Data with various attributes from the database
    // it runs a query to get the amount of people that fit in each attribute
    public ObservableList<PieChart.Data> politicalChart() {
    	ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        try {

            String query = "select PartyName, count(PartyName) as numCandidates "
                    + "from candidateInfos where PartyName != '' group by PartyName "
                    + "order by PartyName asc;";
            rs = con.getCurrentConnection().prepareStatement(query).executeQuery();
            String name; int num;
            while(rs.next()) {
                name = rs.getString("PartyName");
                num = rs.getInt("numCandidates");
                data.add( new PieChart.Data( name, num ));
            }
            rs.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return data;
    }
    
    public ObservableList<PieChart.Data> genderChart() {
    	ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        try {

            String query = "select GenderPronoun, count(GenderPronoun) as "
            		+ "numGenders from raceGenderInfo group by GenderPronoun;";
            rs = con.getCurrentConnection().prepareStatement(query).executeQuery();
            String name; int num;
            while(rs.next()) {
                name = rs.getString("GenderPronoun");
                num = rs.getInt("numGenders");
                data.add( new PieChart.Data( name, num ));
            }
            rs.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return data;
    }
    
    public ObservableList<PieChart.Data> raceChart() {
    	ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        try {

            String query = "select Race, count(Race) as "
            		+ "numRaces from raceGenderInfo "
            		+ "group by Race order by Race asc;";
            rs = con.getCurrentConnection().prepareStatement(query).executeQuery();
            String name; int num;
            while(rs.next()) {
                name = rs.getString("Race");
                num = rs.getInt("numRaces");
                data.add( new PieChart.Data( name, num ));
            }
            rs.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return data;
    }
    // ---------- END PieChart Functions ------------
    
    public ObservableList<String> raceCombo () {
    	ObservableList<String> data = FXCollections.observableArrayList(); 
    	try {
    		String query = "select Race "
    				+ "from candidateInfos "
    				+ "natural join candidateNames "
    				+ "natural join candidateRaceGender "
    				+ "group by Race;";
    		rs = con.getCurrentConnection().prepareStatement(query).executeQuery();
    		while(rs.next()) {
    			data.add(rs.getString("Race"));
    		}
    		data.add(null);
    		rs.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return data;
    }
    
    public ObservableList<String> yearCombo () {
    	ObservableList<String> data = FXCollections.observableArrayList(); 
    	try {
    		String query = "select date_format(str_to_date( ElecDate, \"%m/%d/%Y\"), \"%Y\") "
    				+ "as ElecDate from candidateInfos natural join candidateNames "
    				+ "natural join candidateRaceGender group by ElecDate;";
    		rs = con.getCurrentConnection().prepareStatement(query).executeQuery();
    		while(rs.next()) {
    			data.add(rs.getString("ElecDate"));
    		}
    		data.add(null);
    		rs.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return data;
    }
    
    public ObservableList<String> pronounsCombo () {
    	ObservableList<String> data = FXCollections.observableArrayList(); 
    	try {
    		String query = "select Pronouns "
    				+ "from candidateInfos "
    				+ "natural join candidateNames "
    				+ "natural join candidateRaceGender "
    				+ "group by Pronouns;";
    		rs = con.getCurrentConnection().prepareStatement(query).executeQuery();
    		while(rs.next()) {
    			data.add(rs.getString("Pronouns"));
    		}
    		data.add(null);
    		rs.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return data;
    }
    
    public ObservableList<String> hispanicCombo() {
    	ObservableList<String> data = FXCollections.observableArrayList(); 
    	try {
    		String query = "select Hispanic "
    				+ "from candidateInfos "
    				+ "natural join candidateNames "
    				+ "natural join candidateRaceGender "
    				+ "group by Hispanic;";
    		rs = con.getCurrentConnection().prepareStatement(query).executeQuery();
    		while(rs.next()) {
    			data.add(rs.getString("Hispanic"));
    		}
    		data.add(null);
    		rs.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return data;
    }
    
    public ObservableList<String> partyCombo() {
    	ObservableList<String> data = FXCollections.observableArrayList(); 
    	try {
    		String query = "select PartyName "
    				+ "from candidateInfos "
    				+ "natural join candidateNames "
    				+ "natural join candidateRaceGender "
    				+ "group by PartyName;";
    		rs = con.getCurrentConnection().prepareStatement(query).executeQuery();
    		while(rs.next()) {
    			data.add(rs.getString("PartyName"));
    		}
    		data.add(null);
    		rs.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return data;
    }
    
    public ObservableList<String> officeCombo() {
    	ObservableList<String> data = FXCollections.observableArrayList(); 
    	try {
    		String query = "select OfficeName "
    				+ "from candidateInfos "
    				+ "natural join candidateNames "
    				+ "natural join candidateRaceGender "
    				+ "group by OfficeName;";
    		rs = con.getCurrentConnection().prepareStatement(query).executeQuery();
    		while(rs.next()) {
    			data.add(rs.getString("OfficeName"));
    		}
    		data.add(null);
    		rs.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return data;
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
        Scene s1 = new Scene(root);
        primaryStage.setScene(s1);
        primaryStage.show();

    }
    
    public void setUpEverything(ActionEvent e) throws IOException{
    	
    	raceBox.setItems( raceCombo());
    	hispanicBox.setItems( hispanicCombo());
    	partyBox.setItems( partyCombo());
    	officeBox.setItems( officeCombo());
    	pronounsBox.setItems( pronounsCombo());
    	yearBox.setItems( yearCombo());
    	
    	search.setDisable(false);
    	party.setDisable(false);
    	racePercent.setDisable(false);
    	gender.setDisable(false);
    	start.setDisable(true);
    	
    	pickPieChart.setData( politicalChart());
    	pickPieChart.setTitle("Political Parties");
    	pickPieChart.setLabelsVisible(false);
    }
    
    public void displayInfo(ActionEvent e) throws IOException{

    	ObservableList<String> data = FXCollections.observableArrayList();
    	try {
    		CallableStatement statement = con.getCurrentConnection().prepareCall("{call searchingoverall(?,?,?,?,?,?)}");
    		statement.setString(1, raceBox.getValue());
    		statement.setString(2, pronounsBox.getValue());
    		statement.setString(3, officeBox.getValue());
    		statement.setString(4, hispanicBox.getValue());
    		statement.setString(5, partyBox.getValue());
    		statement.setString(6, yearBox.getValue());
    		statement.execute();
    		rs = statement.getResultSet();
    		while(rs.next()) {
    			data.add(rs.getString("FirstName") + " " + rs.getString("LastName"));
    		}
    		
    		rs.close();
    	} catch (SQLException e1) {
    		e1.printStackTrace();
    	}
    	
    	ResultsList.setItems( data);
    	
    }
    
    // loads the politicians that are randomly placed in the d
    public void displayCharts(ActionEvent e) throws IOException{
    	Object source = e.getSource();
    	if( source == party) {
	    	pickPieChart.setData( politicalChart());
	    	pickPieChart.setTitle("Political Parties");
    	} else if( source == gender) {
    		pickPieChart.setData( genderChart());
    		pickPieChart.setTitle("Gender Percentages");
    	} else if( source == racePercent) {
    		pickPieChart.setData( raceChart());
    		pickPieChart.setTitle("Race Percentages");
    	}
    	pickPieChart.setLabelsVisible(false);
    	pickPieChart.setLegendSide(Side.BOTTOM);
    	
    }
    
    // ----- MENU BAR ACTIONS -----
    // Allows the user to move to search or home depending on the screen they are at
    // also allows them to end the application
    public void reset(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/resources/FXML/startScreen.fxml"));
        startScene.getScene().setRoot( root);
    }
    public void exitApp(ActionEvent e) throws IOException{
    	System.exit(-1);
    }
    // --------- END MENU BAR ACTIONS --------------

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		assert partyBox != null : "not injected";
	}


}