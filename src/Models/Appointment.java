package Models;

public class Appointment {

    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private String start;
    private String end;
    private String createdBy;
    private String createDate;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;
    private String contact;


    public Appointment(){}

    public Appointment(int appointmentID, String title, String description, String location,
                            String type, String start, String end, String createdBy, String createDate,
                            String lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createdBy = createdBy;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEnd() {
        return end;
    }

    public int getUserID() {
        return userID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public int getContactID() {
        return contactID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getDescription() {
        return description;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public String getLocation() {
        return location;
    }

    public String getStart() {
        return start;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getContact() {
        return contact;
    }
}
