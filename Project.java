import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a project in the Poised Property Management System.
 */

 
public class Project {
    private int projectNumber;
    private String projectName;
    private String buildingType;
    private String physicalAddress;
    private int erfNumber;
    private double totalFee;
    private double amountPaid;
    private LocalDate deadline;
    private int architectID; // Store the Architect ID
    private int contractorID; // Store the Contractor ID
    private int customerID; // Store the Customer ID

    /**
     * Default constructor for the case when you have all project details including IDs.
     *
     * @param projectNumber The project's unique number.
     * @param projectName The name of the project.
     * @param buildingType The type of building.
     * @param physicalAddress The physical address of the project.
     * @param erfNumber The ERF (Erf Registration Form) number of the project.
     * @param totalFee The total fee for the project.
     * @param amountPaid The amount paid for the project.
     * @param deadline The project deadline represented as a LocalDate.
     * @param architectID The ID of the architect associated with the project.
     * @param contractorID The ID of the contractor associated with the project.
     * @param customerID The ID of the customer associated with the project.
     /* */

    // Default constructor for the case when you only have IDs
    public Project(int projectNumber, String projectName, String buildingType, String physicalAddress,
                   int erfNumber, double totalFee, double amountPaid, LocalDate deadline,
                   int architectID, int contractorID, int customerID) {
        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.buildingType = buildingType;
        this.physicalAddress = physicalAddress;
        this.erfNumber = erfNumber;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
        this.deadline = deadline;
        this.architectID = architectID;
        this.contractorID = contractorID;
        this.customerID = customerID;
    }

    /**
     * Constructor for the case when you have class instances of Architect, Contractor, and Customer.
     *
     * @param projectNumber The project's unique number.
     * @param projectName The name of the project.
     * @param buildingType The type of building.
     * @param physicalAddress The physical address of the project.
     * @param erfNumber The ERF (Erf Registration Form) number of the project.
     * @param totalFee The total fee for the project.
     * @param amountPaid The amount paid for the project.
     * @param deadline The project deadline represented as a LocalDate.
     * @param architect The architect associated with the project.
     * @param contractor The contractor associated with the project.
     * @param customer The customer associated with the project.
     /* */

    // Constructor for the case when you have class instances
    public Project(int projectNumber, String projectName, String buildingType, String physicalAddress,
                   int erfNumber, double totalFee, double amountPaid, LocalDate deadline,
                   Architect architect, Contractor contractor, Customer customer) {
        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.buildingType = buildingType;
        this.physicalAddress = physicalAddress;
        this.erfNumber = erfNumber;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
        this.deadline = deadline;
        this.architectID = architect.getArchitectId();
        this.contractorID = contractor.getContractorId();
        this.customerID = customer.getCustomerId();
    }

    // Getters and setters for attributes

    public int getProjectNumber() {
        return projectNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public int getErfNumber() {
        return erfNumber;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getArchitectID() {
        return architectID;
    }

    public int getContractorID() {
        return contractorID;
    }

    public int getCustomerID() {
        return customerID;
    }
/**
     * Finalize the project based on a completion date.
     *
     * @param completionDate The completion date in the format "yyyy-MM-dd".
     */

     
    public void finalizeProject(String completionDate) {
        // Convert the completion date string to a LocalDate object
        LocalDate completion = LocalDate.parse(completionDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Check if the completion date is after the project's deadline
        if (completion.isAfter(deadline)) {
            System.out.println("Project has been finalized after the deadline.");
            // Additional logic for any actions needed when finalizing after the deadline
        } else {
            System.out.println("Project has been successfully finalized.");
            // Additional logic for successful project finalization
        }
    }
}


// Used definitions from Hyperion Dev Full Stack Software and Web Developer pdf resources //