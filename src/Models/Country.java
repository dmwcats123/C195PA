package Models;


import java.sql.Date;

public class Country {
    private int countryID;
    private String country;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdatedBy;

    Country() {}
    Country(int countryID, String country, Date createDate, String createdBy, Date lastUpdate, String lastUpdatedBy) {
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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreateDate() {
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

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
