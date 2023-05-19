package model;

import java.time.LocalDateTime;
/**This is the appointments class. This is where all the appointment constructors, setters, and getters are.*/
public class Appointments {

    private int appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private LocalDateTime appointmentStart;
    private LocalDateTime appointmentEnd;
    private int customerId;
    private int userId;
    private int contactId;
    private int total;
    private String appointmentMonth;
    private int appointmentTotal;

    /**This is the constructor for the appointments.
     * @param customerId
     * @param contactId
     * @param appointmentId
     * @param userId
     * @param appointmentDescription
     * @param appointmentEnd
     * @param appointmentLocation
     * @param appointmentStart
     * @param appointmentTitle
     * @param appointmentType
     */
    public Appointments(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }
    /**This is the overloaded appointment constructor.
     * @param appointmentType
     * @param appointmentMonth
     * @param appointmentTotal
     */
    public Appointments(String appointmentMonth, String appointmentType, int appointmentTotal) {
        this.appointmentMonth = appointmentMonth;
        this.appointmentType = appointmentType;
        this.appointmentTotal = appointmentTotal;
    }
    /**Appointment month getter.
     * @return appointmentMonth
     */
    public String getAppointmentMonth() {
        return appointmentMonth;
    }
    /**Appointment month setter.
     * @param appointmentMonth
     */
    public void setAppointmentMonth(String appointmentMonth) {
        this.appointmentMonth = appointmentMonth;
    }
    /**Appointment total getter.
     * @return appointmentTotal
     */
    public int getAppointmentTotal() {
        return appointmentTotal;
    }
    /**Appointment Total setter.
     * @param appointmentTotal
     */
    public void setAppointmentTotal(int appointmentTotal) {
        this.appointmentTotal = appointmentTotal;
    }
    /**Appointment total getter.
     * @return total
     */
    public int getTotal() {
        return total;
    }
    /**Appointment total setter.
     * @param total
     */
    public void setTotal(int total) {
        this.total = total;
    }
    /**Appointment ID getter.
     * @return appointmentId
     */
    public int getAppointmentId() {
        return appointmentId;
    }
    /**Appointment ID setter.
     * @param appointmentId
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    /**Appointment title getter.
     * @return appointmentTitle
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }
    /**Appointment title setter.
     * @param appointmentTitle
     */
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }
    /**Appointment description getter.
     * @return appointmentDescription
     */
    public String getAppointmentDescription() {
        return appointmentDescription;
    }
    /**Appointment description setter.
     * @param appointmentDescription
     */
    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }
    /**Appointment location getter
     * @return appointmentLocation
     */
    public String getAppointmentLocation() {
        return appointmentLocation;
    }
    /**Appointment location setter.
     * @param appointmentLocation
     */
    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }
    /**Appointment type getter.
     * @return appointmentType
     */
    public String getAppointmentType() {
        return appointmentType;
    }
    /**Appointment type setter.
     * @param appointmentType
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }
    /**Appointment start getter.
     * @return appointmentStart
     */
    public LocalDateTime getAppointmentStart() {
        return appointmentStart;
    }
    /**Appointment start setter.
     * @param appointmentStart
     */
    public void setAppointmentStart(LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }
    /**Appointment end getter.
     * @return appointmentEnd
     */
    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }
    /**Appointment end setter.
     * @param appointmentEnd
     */
    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }
    /**Appointment customer ID getter.
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }
    /**Appointment customer ID setter.
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /**Appointment user ID getter.
     * @return userId
     */
    public int getUserId() {
        return userId;
    }
    /**Appointment user ID setter.
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**Appointment contact ID getter.
     * @return contactId
     */
    public int getContactId() {
        return contactId;
    }
    /**Appointment contact ID setter.
     * @param contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    /**Appointment string for combo boxes.
     * @return appointmentType
     */
    public String toString() {
        return (appointmentType);
    }
}
