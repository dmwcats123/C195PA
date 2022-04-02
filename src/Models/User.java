package Models;

public class User {
    private int userID;
    private String username;
    private String password;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;


    public User(){}
    public User(int userID, String username, String password, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy) {
        this.userID=userID;
        this.username = username;
        this.password = password;
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

    public int getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
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

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
