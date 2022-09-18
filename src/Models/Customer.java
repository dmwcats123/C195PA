package Models;

import DAO.CountryDao;
import DAO.FldDAO;

/**
 * holds and provides access to the customer data
 */
public class Customer {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;

    /**
     * blank constructor for customers
     */
    public Customer() {}

    /**
     * normal constructor for the database
     * @param customerID the customer Id to be set
     * @param customerName the customer name to be set
     * @param customerAddress the customer address to be set
     * @param customerPostalCode the customer postal code to be set
     * @param customerPhone the customer phone number to be set
     * @param createDate the date the customer was created
     * @param createdBy the user who created the customer in database
     * @param lastUpdate the user who last updated the customer
     * @param lastUpdatedBy the last time the customer was updated
     * @param divisionID the Id of the customers first level division
     */
    public Customer(int customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, String createDate,
             String createdBy, String lastUpdate, String lastUpdatedBy, int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    /**
     * sets the date the customer was created in the database
     * @param createDate the date the customer was created
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * sets the name of the user who created the customer in the database
     * @param createdBy the name of the user
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * sets the time of the customers last update
     * @param lastUpdate the time the customer was last updated
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * sets the name of the user who last updated the customer
     * @param lastUpdatedBy the user who last updated the customer
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * sets the customer id
     * @param customerID id to be set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * sets the customers address
     * @param customerAddress to be set
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * sets the customers name
     * @param customerName to be set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * sets the customers phone number
     * @param customerPhone phone number to be set
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * sets the customers postal code
     * @param customerPostalCode the postal code to be set
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**
     * sets the customers division ID
     * @param divisionID to be set
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * gets the date the customer was created in the database
     * @return the create date
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * gets the user who created the customer in the database
     * @return the user who created the customer
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * gets the last time the customer was updated in the database
     * @return the time of last update
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * get the user who last updated the customer in the datbaase
     * @return the user who performed last update
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * gets the customer id
     * @return the customers id
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * gets the division id of the customer
     * @return the division id
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * gets the customer address
     * @return the customer address
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * gets the customer name
     * @return the customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * gets the customer phone number
     * @return the customers phone number
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * gets the customer postal code
     * @return the customer postal code
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     * gets the customers division
     * @return the division
     * @throws Exception
     */
    public String getDivision() throws Exception {
        return FldDAO.get(this.divisionID).getDivision();
    }

    /**
     * gets the customers country
     * @return the country
     * @throws Exception
     */
    public String getCountry() throws Exception {
        return CountryDao.get(FldDAO.get(this.divisionID).getCountryID()).getCountry();
    }
}
