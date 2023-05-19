package model;
/**This is the users class. This is where all the users constructors, setters, and getters are.*/
public class Users {

    private int userId;
    private String userName;
    private String userPassword;
    /**Users constructor.
     * @param userId
     * @param userName
     * @param userPassword
     */
    public Users(int userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }
    /**Users ID getter.
     * @return userId
     */
    public int getUserId() {
        return userId;
    }
    /**Users ID setter.
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**Users username getter.
     * @return userName
     */
    public String getUserName() {
        return userName;
    }
    /**Users username setter.
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**Users password getter.
     * @return userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }
    /**Users password setter.
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    /**Users string for combo boxes.
     * @return userId
     */
    @Override
    public String toString() {
        return String.valueOf((userId));
    }
}
