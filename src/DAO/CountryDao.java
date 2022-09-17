package DAO;

import Models.Country;
import Models.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * CountryDao is used to interact with countries in the database
 */
public class CountryDao {
    /**
     * gets a country from the database with a specified country ID
     * @param countryID the country ID of the country to be retrieved from the database
     * @return a country with the specified country ID
     * @throws Exception
     */
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
