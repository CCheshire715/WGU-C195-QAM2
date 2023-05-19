package model;
/**This is the countries class. This is where all the countries constructors, setters, and getters are.*/
public class Countries {

    private int countryId;
    private String country;
    private int total;
    /**Countries constructor.
     * @param country
     * @param countryId
     */
    public Countries(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }
    /**Countries overloaded constructor.
     * @param country
     * @param total
     */
    public Countries(String country, int total) {
        this.country = country;
        this.total = total;
    }
    /**Countries total getter.
     * @return total
     */
    public int getTotal() {
        return total;
    }
    /**Countries total setter.
     * @param total
     */
    public void setTotal(int total) {
        this.total = total;
    }
    /**Countries ID getter.
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }
    /**Countries ID setter.
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    /**Countries name getter.
     * @return country
     */
    public String getCountry() {
        return country;
    }
    /**Countries name setter.
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /**Countries string for combo boxes.
     * @return country name
     */
    @Override
    public String toString() {
        return (country);
    }
}
