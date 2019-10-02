/**
 * This class is the user class. It defines the information that is stored by a user in the library system.
 */
public class User {


    private String name;
    private String id;
    private String password;
    private String email;
    private String phoneNo;
    /**
     * librarianStatus is a boolean of whether or not the user is a librarian
     */
    private boolean librarianStatus = false;

    /**
     * Constructor method for User. This is the main constructor used, and is used for normal users of the system.
     * @param name Users name
     * @param id Users ID
     * @param password Users password
     * @param email Users email
     * @param phoneNo Users phone number
     */
    public User(String name, String id, String password, String email, String phoneNo) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    /**
     * Constructor method for user. This constructor is used to create Librarians.
     * @param name Users name
     * @param id Users ID
     * @param password Users password
     * @param librarianStatus A boolean to describe whether or not the user is a librarian.
     */
    public User(String name, String id, String password, Boolean librarianStatus) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.librarianStatus = librarianStatus;
    }

    /**
     * Contructor method for user. This constructor is only used for testing purposes to keep tests working from pre-contact information stages of the project.
     * @param name Users name
     * @param id Users ID
     * @param password Users password
     */
    public User(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    /**
     * Get method for the users name
     * @return The users name
     */
    public String getName() {
        return name;
    }

    /**
     * Get method for the users ID
     * @return The users ID
     */
    public String getId() {
        return id;
    }

    /**
     * A private method to compare the password that was supplied by the user to the password that is stored. Can only be accessed from the "checkPassword" method in this class for security reasons.
     * @param password the password that the user entered (it is passed through checkPassword first).
     * @return True if the password matches, false if it doesn't.
     */
    private boolean comparePassword(String password) {
        if(this.password.equals(password)) {
            return true;
        } else { return false; }
    }

    /**
     * Checks whether the user-entered password is the correct password.
     * @param password the entered password
     * @return True if the passwords match, false if they don't.
     */
    public boolean checkPassword(String password) {
        return comparePassword(password);
    }

    /**
     * Get method for the librarian status
     * @return True if the user is a librarian, false if the user isn't a librarian.
     */
    public boolean getLibrarianStatus() {
        return librarianStatus;
    }

    /**
     * get method for email
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * get method for phone number
     * @return the phone number of the user
     */
    public String getPhoneNo() {
        return phoneNo;
    }
}
