package model;
/**This is the contacts class. This is where all the contacts constructors, setters, and getters are.*/
public class Contacts {

    private int contactId;
    private String contactName;
    private String contactEmail;
    /**Contacts constructor.
     * @param contactId
     * @param contactEmail
     * @param contactName
     */
    public Contacts(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }
    /**Contacts contact ID getter.
     * @return contactId
     */
    public int getContactId() {
        return contactId;
    }
    /**Contacts contact ID setter.
     * @param contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    /**Contacts contact name getter.
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }
    /**Contacts contact name setter.
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /**Contacts contact email getter.
     * @return contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }
    /**Contacts contact email setter.
     * @param contactEmail
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    /**Contact string for combo boxes.
     * @return contactName
     */
    @Override
    public String toString() {
        return String.valueOf((contactName));
    }
}
