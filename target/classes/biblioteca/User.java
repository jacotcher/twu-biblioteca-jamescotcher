public class User {

    private String name;
    private String id;
    private String password;

    public User(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this. password = password;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    private boolean comparePassword(String password) {
        if(this.password.equals(password)) {
            return true;
        } else { return false; }
    }

    public boolean checkPassword(String password) {
        return comparePassword(password);
    }
}
