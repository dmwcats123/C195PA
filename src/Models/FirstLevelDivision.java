package Models;

/**
 * holds and provides access to division data
 */
public class FirstLevelDivision {
    private int divisionID;
    private String division;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int countryID;

    /**
     * empty constructor for first level division
     */
    public FirstLevelDivision() {}

    /**
     * Constructor for first level division
     * @param divisionID the division id to be set
     * @param division the name of the division
     * @param createDate the date the division was created
     * @param createdBy the user who created the division
     * @param lastUpdate the date the user was last updated
     * @param lastUpdatedBy the user who last updated the division
     * @param countryID the country id for the associated country
     */
    public FirstLevelDivision(int divisionID, String division, String createDate, String createdBy, String lastUpdate,
                       String lastUpdatedBy, int countryID) {
        this.divisionID =divisionID;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }

    /**
     * gets the division id
     * @return the id
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * gets the date the division was created
     * @return the creation date
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * gets the user who created the division
     * @return the user who create the division
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * gets the name of the division
     * @return the name of the division
     */
    public String getDivision() {
        return division;
    }

    /**
     * gets the date of the last time the division was updated
     * @return the date of the last update
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * gets the country ID of the country associated with the division
     * @return the country ID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * get the user who last updated the division
     * @return the user
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * sets the date of the divsion creation
     * @param createDate the date the division was created
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * sets the user who creaeted the divsion
     * @param createdBy the user who created the divsion
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * sets the name of the division
     * @param division the name of the division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * sets the id of the division
     * @param divisionID the id of the division
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * sets the date of the last update to the division
     * @param lastUpdate date of last update
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * sets the id of the associated country
     * @param countryID the country's ID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * sets the date of the last update
     * @param lastUpdatedBy the date of last update
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
