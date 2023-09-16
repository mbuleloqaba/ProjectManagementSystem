/**
 * Represents a Customer involved in a construction project.
 */
public class Customer {
    private int ID;
    private String name;
    private String telephone;
    private String email;
    private String address;

    /**
     * Constructs a Customer object with the provided details.
     *
     * @param ID        The unique ID of the customer.
     * @param name      The name of the customer.
     * @param telephone The phone number of the customer.
     * @param email     The email address of the customer.
     * @param address   The physical address of the customer.
     */
    public Customer(int ID, String name, String telephone, String email, String address) {
        this.ID = ID;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
    }
    public int getCustomerId() {
        return ID; 
    }
    // Getters and setters for all attributes

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


// Used definitions from Hyperion Dev Full Stack Software and Web Developer pdf resources //