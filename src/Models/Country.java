package Models;

public class Country {
    private int countryID;
    private String country;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;

    Country() {}
    Country(int countryID, String country, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy) {
        this.countryID = countryID;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public int getCountryID() {
        return countryID;
    }

    public String getCountry() {
        return country;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
