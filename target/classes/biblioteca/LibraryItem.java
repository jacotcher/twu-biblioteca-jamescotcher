/**
 * The LibraryItem class is a class that can be extended by different types of library item
 */
public abstract class LibraryItem {
    /**
     * All library items must have a name and year
     */
    String name;
    int year;

    /**
     * get method for getting the name of the libraryitem
     * @return name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * get method for getting the year of the library item
     * @return year of the library item
     */
    public int getYear() {
        return year;
    }


}
