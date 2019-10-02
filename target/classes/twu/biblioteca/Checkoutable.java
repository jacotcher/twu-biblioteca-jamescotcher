public interface Checkoutable {


    public void checkout(User user);


    public void returnItem();


    public Boolean getCheckedOut();

    public User getCheckedOutBy();
}
