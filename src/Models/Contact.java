package Models;

/**
 * holds and provides access to contact data
 */
public class Contact {

    private int contactID;
    private String contactName;
    private String email;

    /**
     * blank constructor for a contact
     */
    public Contact() {}

    /**
     * constructor for contact
     * @param contactID the contact ID
     * @param contactName the contact name
     * @param email the contact Email
     */
    public Contact(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * gets the contactID
     * @return the contact ID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * gets the contact name
     * @return the contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * gets the contact email
     * @return the contact email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the contact ID
     * @param contactID to be set
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * sets the contact name
     * @param contactName to be set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * sets the contact email
     * @param email to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
