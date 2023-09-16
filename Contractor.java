/**
 * Represents a Contractor involved in a project.
 */
public class Contractor {
    private int ID;
    private String name;
    private String telephone;
    private String email;
    private String address;

    /**
     * Constructs a Contractor with the specified attributes.
     *
     * @param ID        The unique identifier of the contractor.
     * @param name      The name of the contractor.
     * @param telephone The phone number of the contractor.
     * @param email     The email address of the contractor.
     * @param address   The physical address of the contractor.
     */
    public Contractor(int ID, String name, String telephone, String email, String address) {
        this.ID = ID;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.address = address;

        
    }

    // Getter for Contractor ID
    public int getContractorId() {
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