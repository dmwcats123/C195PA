package Models;

/**
 * holds and provides access to data for country
 */
public class Country {
    private int countryID;
    private String country;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;

    /**
     * empty constructor for countries
     */
    public Country() {}

    /**
     * normal constructor for country
     * @param countryID to be set
     * @param country to be set
     * @param createDate date the country was created
     * @param createdBy user who created the country in the database
     * @param lastUpdate user who last updated the country
     * @param lastUpdatedBy date the country was last updated
     */
    Country(int countryID, String country, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy) {
        this.countryID = countryID;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * gets the user who last updated the country
     * @return the user who updated the country
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * gets the date the country was last updated
     * @return the date
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * gets the the user who created the country
     * @return the user who created the country
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * gets the date the country was created
     * @return the date the country was created
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * gets the id of the country
     * @return the id of the country
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * gets the name of the country
     * @return the name of the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * sets the name of the user who last updated the country
     * @param lastUpdatedBy the user who last updated the country
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * sets the date the country was last updated
     * @param lastUpdate the date the country was last updated
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * sets the user who created the country
     * @param createdBy the user who created the country
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * sets the date the country was created in the database
     * @param createDate the date the country was created in the database
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * sets the name of the country
     * @param country the name of the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * sets the id of the country
     * @param countryID to be set
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
