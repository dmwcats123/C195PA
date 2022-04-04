package DAO;

import Models.Database;
import Models.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FldDAO {

    public static FirstLevelDivision get(int fldDao) throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM first_level_divsions WHERE  Division_ID = " + fldDao;
        ResultSet result = statement.executeQuery(sqlStatement);
        FirstLevelDivision fldResult = new FirstLevelDivision();
        while (result.next()) {
            fldResult.setDivisionID(result.getInt("Division_ID"));
            fldResult.setDivision(result.getString("Division"));
            fldResult.setCountryID(result.getInt("Country_ID"));
            fldResult.setCreatedBy(result.getString("Created_By"));
            fldResult.setLastUpdate(result.getString("Last_Update"));
            fldResult.setLastUpdatedBy(result.getString("Last_Updated_By"));

            Database.closeConnection();
            return fldResult;
        }
        Database.closeConnection();
        return null;
    }

    public static FirstLevelDivision get(String division) throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM first_level_divisions WHERE Divsion = '" + division +"'";
        ResultSet result = statement.executeQuery(sqlStatement);
        FirstLevelDivision fldResult = new FirstLevelDivision();
        while (result.next()) {
            fldResult.setDivisionID(result.getInt("Division_ID"));
            fldResult.setDivision(result.getString("Division"));
            fldResult.setCountryID(result.getInt("Country_ID"));
            fldResult.setCreatedBy(result.getString("Created_By"));
            fldResult.setLastUpdate(result.getString("Last_Update"));
            fldResult.setLastUpdatedBy(result.getString("Last_Updated_By"));

            Database.closeConnection();
            return fldResult;
        }
        Database.closeConnection();
        return null;
    }

    public static ObservableList<FirstLevelDivision> getAllDivisions() throws SQLException, Exception{
        ObservableList<FirstLevelDivision> allDivisions = FXCollections.observableArrayList();
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM first_level_divisions";
        ResultSet result = statement.executeQuery(sqlStatement);
        while(result.next()){
            FirstLevelDivision divisionResult = new FirstLevelDivision(result.getInt("Division_ID"), result.getString("Division"),
                    result.getString("Create_Date"), result.getString("Created_By"),
                    result.getString("Last_Update"), result.getString("Last_Updated_By"),
                    result.getInt("Country_ID"));
            allDivisions.add(divisionResult);

        }
        Database.closeConnection();
        return allDivisions;
    }
}
