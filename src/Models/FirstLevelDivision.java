package Models;

public class FirstLevelDivision {
    private int divisionID;
    private String division;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int countryID;

    public FirstLevelDivision() {}
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
    public int getDivisionID() {
        return divisionID;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getDivision() {
        return division;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public int getCountryID() {
        return countryID;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
