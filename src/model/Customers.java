package model;
/**This is the customers class. This is where all the customers constructors, setters, and getters are.*/
public class Customers {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhoneNumber;
    private int divisionId;
    private String division;
    private String country;
    /**Customers constructor.
     * @param country
     * @param customerId
     * @param divisionId
     * @param customerAddress
     * @param customerName
     * @param customerPostalCode
     * @param customerPhoneNumber
     * @param division
     */
    public Customers(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber, int divisionId, String division, String country) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhoneNumber = customerPhoneNumber;
        this.divisionId = divisionId;
        this.division = division;
        this.country = country;
    }
    /**Customers overloaded constructor.
     * @param customerPostalCode
     * @param customerName
     * @param customerAddress
     * @param customerId
     * @param divisionId
     * @param customerPhone
     */
    public Customers(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhoneNumber = customerPhone;
        this.divisionId = divisionId;
    }
    /**Customers country getter.
     * @return country
     */
    public String getCountry() {
        return country;
    }
    /**Customers country setter.
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /**Customers ID getter.
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }
    /**Customers ID setter.
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /**Customers name getter.
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }
    /**Customers name setter.
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**Customers address getter.
     * @return customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }
    /**Customers address setter.
     * @param customerAddress
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    /**Customers postal code getter.
     * @return customerPostalCode
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }
    /**Customers postal code setter.
     * @param customerPostalCode
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }
    /**Customers phone number getter.
     * @return customerPhoneNumber
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }
    /**Customers phone number setter.
     * @param customerPhoneNumber
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
    /**Customers division ID getter.
     *@return divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }
    /**Customers division ID setter.
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
    /**Customers division getter.
     * @return division
     */
    public String getDivision() {
        return division;
    }
    /**Customers division setter.
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
    }
    /**Customers string for combo boxes.
     * @return customerId
     */
    @Override
    public String toString() {
        return String.valueOf((customerId));
    }
}
