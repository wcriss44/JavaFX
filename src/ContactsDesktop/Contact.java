package ContactsDesktop;

/***********************************************************
 * Unused setters and getters are declared for JSON purposes
 *
 ***********************************************************/

public class Contact {
    /*********************
     * Variables
     ********************/
    String name;
    String phoneNumber;
    String email;

    /*********************
     * Constructors
     ********************/

    public Contact(){

    }

    public Contact(String name, String phoneNumber, String email){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /*********************
     *Setters
     ********************/

    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    /*********************
     *Getters
     ********************/

    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    /*********************
     *Other methods
     ********************/

    @Override
    public String toString(){
        return "Name: " + name + "\tPhone:  " + phoneNumber + "\tEmail: " + email;
    }
}
