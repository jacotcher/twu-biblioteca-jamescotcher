/**
 * Interface that can be implemented to make an item Checkoutable. I created this in case an item was going to be added to the library that couldn't be checked out (for example past-papers).
 */
public interface Checkoutable {


    /**
     * This method marks the item as checked out. It also stored the user that is passed in as the "checkedOutBy" variable.
     * @param user
     */
    public void checkout(User user);

    /**
     * This method marks the item as returned. It also removed the user associated with checking it out.
     */
    public void returnItem();

    /**
     * get method for seeing if the item is checked out
     * @return True if the item has been checked out, false if it hasn't.
     */
    public Boolean getCheckedOut();

    /**
     * get method for checking who the user is that checked the item out
     * @return The user that has checked out the book.
     */
    public User getCheckedOutBy();
}
