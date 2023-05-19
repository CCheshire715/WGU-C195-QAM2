package model;
/**This is the first level divisions class. This is where all the first level divisions constructors, setters, and getters are.*/
public class FirstLevelDivisions {

    private int divisionId;
    private String division;
    private int countryId;
    /**First level divisions constructor.
     * @param division
     * @param divisionId
     * @param countryId
     */
    public FirstLevelDivisions(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }
    /**First level divisions division ID getter.
     * @return divisionID
     */
    public int getDivisionId() {
        return divisionId;
    }
    /**First level divisions division ID setter.
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
    /**First level divisions division name getter.
     * @return division
     */
    public String getDivision() {
        return division;
    }
    /**First level divisions division name setter.
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
    }
    /**First level divisions country ID getter.
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }
    /**First level divisions country ID setter.
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    /**First level divisions string for combo boxes.
     * @return division name
     */
    @Override
    public String toString() {
        return (division);
    }
}
