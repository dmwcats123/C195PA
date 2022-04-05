package DAO;

import Models.Country;
import Models.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CountryDao {
    public static Country get(int countryID) throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM countries WHERE Country_ID = " + countryID;
        ResultSet result = statement.executeQuery(sqlStatement);
        Country countryResult = new Country();
        while (result.next()) {
            countryResult.setCountryID(result.getInt("Country_ID"));
            countryResult.setCountry(result.getString("Country"));
            countryResult.setCreatedBy(result.getString("Created_By"));
            countryResult.setLastUpdate(result.getString("Last_Update"));
            countryResult.setLastUpdatedBy(result.getString("Last_Updated_By"));

            Database.closeConnection();
            return countryResult;
        }
        Database.closeConnection();
        return null;
    }
}
