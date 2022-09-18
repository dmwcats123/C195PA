package Models;

/**
 * holds and provides access to data for users
 */
public class User {
    private int userID;
    private String username;
    private String password;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;


    /**
     * blank constructor for user
     */
    public User(){}

    /**
     * constructor for user
     * @param userID the user id to be set
     * @param username to be set
     * @param password to be set
     * @param createDate the date the user was created
     * @param createdBy the user who created this user
     * @param lastUpdate the date of last update
     * @param lastUpdatedBy the user who last updated this user
     */
    public User(int userID, String username, String password, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy) {
        this.userID=userID;
        this.username = username;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * retrieves the user who last updated this user
     * @return the last updater
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * retrieves the time of the last update
     * @return the time of last update
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * retrieves the user who created this user
     * @return the creator
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * get the time the user was created
     * @return the time of user creation
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * gets the user id
     * @return the user id
     */
    public int getUserID() {
        return userID;
    }

    /**
     * gets the user password
     * @return the user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * gets the users username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * set the user who last updated this user
     * @param lastUpdatedBy the user who last updated this user
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * sets the time of the last update
     * @param lastUpdate the time of the last update
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * sets the user who created this user
     * @param createdBy the creator
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * sets the id of the user
     * @param userID the id to be set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * sets the date of creation
     * @param createDate the date of creation
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * sets the users password
     * @param password the users password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * sets the username
     * @param username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
