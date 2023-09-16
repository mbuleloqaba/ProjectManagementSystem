import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/*The ProjectRepository interface defines methods for managing project-related data.
/* It provides operations for projects, architects, customers, and contractors.
 */



public interface ProjectRepository {
    // Project methods
	/**
     * Adds a new project to the repository.
     *
     * @param projectNumber   The project's unique number.
     * @param projectName     The name of the project.
     * @param buildingType    The type of building for the project.
     * @param physicalAddress The physical address of the project.
     * @param erfNumber       The ERF number of the project.
     * @param totalFee        The total fee for the project.
     * @param amountPaid      The amount paid for the project.
     * @param deadline        The project's deadline.
     * @param architect     The ID of the architect for the project.
     * @param contractor    The ID of the contractor for the project.
     * @param customer      The ID of the customer for the project.
     * @throws SQLException If a database error occurs.
     */
    void addProject(int projectNumber, String projectName, String buildingType, String physicalAddress, int erfNumber, double totalFee, double amountPaid, LocalDate deadline, Architect architect, Contractor contractor, Customer customer) throws SQLException;
    /**
     * Updates an existing project in the repository.
     *
     * @param projectNumber   The project's unique number.
     * @param projectName     The updated name of the project.
     * @param buildingType    The updated building type.
     * @param physicalAddress The updated physical address.
     * @param erfNumber       The updated ERF number.
     * @param totalFee        The updated total fee.
     * @param amountPaid      The updated amount paid.
     * @param deadline        The updated project deadline.
     * @param architectId     The updated architect ID.
     * @param contractorId    The updated contractor ID.
     * @param customerId      The updated customer ID.
     * @throws SQLException If a database error occurs.
     */
    
    void updateProject( int projectNumber, String projectName, String buildingType, String physicalAddress,
    int erfNumber, double totalFee, double amountPaid, LocalDate deadline,
    Architect architect, Contractor contractor, Customer customer) throws SQLException;
   /**
     * Retrieves a project from the repository by its project number.
     *
     * @param projectNumber The project's unique number.
     * @return The retrieved project, or null if not found.
     * @throws SQLException If a database error occurs.
     */
    
    Project getProjectByNumber(int projectNumber) throws SQLException;
     /**
     * Retrieves a list of projects that are in progress as of the given current date.
     *
     * @param currentDate The current date for comparison.
     * @return A list of in-progress projects.
     * @throws SQLException If a database error occurs.
     */
    
    List<Project> getInProgressProjects(LocalDate currentDate) throws SQLException;

      /**
     * Retrieves a list of projects that are past their due date.
     *
     * @param currentDate The current date for comparison.
     * @return A list of past due date projects.
     * @throws SQLException If a database error occurs.
     */
   
    List<Project> getPastDueDateProjects(LocalDate currentDate) throws SQLException;

    /**
     * Retrieves a project from the repository by its project number.
     *
     * @param projectNumber The project's unique number.
     * @return The retrieved project, or null if not found.
     * @throws SQLException If a database error occurs.
     */

    List<Project> findProjectsByNumberOrName(String searchQuery) throws SQLException;
    

    // Architect methods
    
    /**
     * Adds a new architect to the repository.
     *
     * @param architect The architect to be added.
     * @throws SQLException If a database error occurs.
     */

    void addArchitect(Architect architect) throws SQLException;
    
    /**
     * Updates an existing architect in the repository.
     *
     * @param architect The updated architect.
     * @throws SQLException If a database error occurs.
     */
    void updateArchitect(Architect architect) throws SQLException;
   
    /**
     * Retrieves an architect from the repository by their ID.
     *
     * @param architectId The architect's unique ID.
     * @return The retrieved architect, or null if not found.
     * @throws SQLException If a database error occurs.
     */


    Architect getArchitectById(int architectId) throws SQLException;
   
    /**
     * Retrieves a list of all architects in the repository.
     *
     * @return A list of architects.
     * @throws SQLException If a database error occurs.
     */

    List<Architect> getAllArchitects() throws SQLException;
    
    // Customer methods

    /**
     * Adds a new customer to the repository.
     *
     * @param customer The customer to be added.
     * @throws SQLException If a database error occurs.
     */

    void addCustomer(Customer customer) throws SQLException;
    
    /**
     * Updates an existing customer in the repository.
     *
     * @param customer The updated customer.
     * @throws SQLException If a database error occurs.
     */

    void updateCustomer(Customer customer) throws SQLException;
    /**
     * Retrieves a customer from the repository by their ID.
     *
     * @param customerId The customer's unique ID.
     * @return The retrieved customer, or null if not found.
     * @throws SQLException If a database error occurs.
     */
    Customer getCustomerById(int customerId) throws SQLException;
    /**
     * Retrieves a list of all customers in the repository.
     *
     * @return A list of customers.
     * @throws SQLException If a database error occurs.
     */

    List<Customer> getAllCustomers() throws SQLException;
    /**
    * Adds a new contractor to the repository.
    *
    * @param contractor The contractor to be added.
    * @throws SQLException If a database error occurs.
    */

    void addContractor(Contractor contractor) throws SQLException;
    /**
     * Updates an existing contractor in the repository.
     *
     * @param contractor The updated contractor.
     * @throws SQLException If a database error occurs.
     */

    void updateContractor(Contractor contractor) throws SQLException;

    /**
     * Retrieves a contractor from the repository by their ID.
     *
     * @param contractorId The contractor's unique ID.
     * @return The retrieved contractor, or null if not found.
     * @throws SQLException If a database error occurs.
     */
    Contractor getContractorById(int contractorId) throws SQLException;
    /**
     * Retrieves a list of all contractors in the repository.
     *
     * @return A list of contractors.
     * @throws SQLException If a database error occurs.
     */
    List<Contractor> getAllContractors() throws SQLException;
}
// Used definitions from Hyperion Dev Full Stack Software and Web Developer pdf resources //