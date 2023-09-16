import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ProjectRepository interface for database interaction.
 */
public class ProjectRepositoryImpl implements ProjectRepository {
    private Connection connection;

    /**
     * Constructs a new ProjectRepositoryImpl instance with the given database connection.
     *
     * @param connection The database connection to use.
     */

    public ProjectRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
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
     * @param architectId     The ID of the architect for the project.
     * @param contractorId    The ID of the contractor for the project.
     * @param customerId      The ID of the customer for the project.
     * @throws SQLException If a database error occurs.
     */
 
    @Override
    public void addProject(int projectNumber, String projectName, String buildingType, String physicalAddress, int erfNumber, double totalFee, double amountPaid, LocalDate deadline, Architect architect, Contractor contractor, Customer customer) throws SQLException {
        String query = "INSERT INTO Project (projectNumber, projectName, buildingType, physicalAddress, erfNumber, " +
                "totalFee, amountPaid, deadline, ArchitectID, ContractorID, CustomerID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, projectNumber);
            statement.setString(2, projectName);
            statement.setString(3, buildingType);
            statement.setString(4, physicalAddress);
            statement.setInt(5, erfNumber);
            statement.setDouble(6, totalFee);
            statement.setDouble(7, amountPaid);
            statement.setDate(8, java.sql.Date.valueOf(deadline));
            statement.setInt(9, architect.getID()); // Use architect.getId()
            statement.setInt(10, contractor.getID()); // Use contractor.getId()
            statement.setInt(11, customer.getID());

            statement.executeUpdate();
        }
    }
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




    

    public void updateProject(int projectNumber, String projectName, String buildingType, String physicalAddress, int erfNumber, double totalFee, double amountPaid, LocalDate deadline, Architect architect, Contractor contractor, Customer customer) throws SQLException {
        String query = "UPDATE Project SET projectName = ?, buildingType = ?, physicalAddress = ?, " +
                "erfNumber = ?, totalFee = ?, amountPaid = ?, deadline = ?, ArchitectID = ?, ContractorID= ?, " +
                "CustomerID = ? WHERE projectNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, projectName);
        statement.setString(2, buildingType);
        statement.setString(3, physicalAddress);
        statement.setInt(4, erfNumber);
        statement.setDouble(5, totalFee);
        statement.setDouble(6, amountPaid);
        statement.setDate(7, java.sql.Date.valueOf(deadline));
        statement.setInt(8, architect.getID()); // Use architect.getId()
        statement.setInt(9, contractor.getID()); // Use contractor.getId()
        statement.setInt(10, customer.getID());
        statement.setInt(11, projectNumber); // Set projectNumber in the WHERE clause
            

            statement.executeUpdate();
        }
    }

    /**
     * Retrieves a project from the repository by its project number.
     *
     * @param projectNumber The project's unique number.
     * @return The retrieved project, or null if not found.
     * @throws SQLException If a database error occurs.
     */

    



    @Override
    public Project getProjectByNumber(int projectNumber) throws SQLException {
        String query = "SELECT * FROM Project WHERE projectNumber = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, projectNumber);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createProjectFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

/**
     * Retrieves a list of projects that are in progress and have not exceeded their deadline.
     *
     * @param currentDate The current date for comparison.
     * @return A list of projects in progress.
     * @throws SQLException If a database error occurs.
     */




    public List<Project> getInProgressProjects(LocalDate currentDate) throws SQLException {
        String query = "SELECT * FROM Project WHERE completionDate IS NULL AND deadline >= ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, java.sql.Date.valueOf(currentDate));
            
            try (ResultSet resultSet = statement.executeQuery()) {
                return createProjectListFromResultSet(resultSet);
            }
        }
    }

    /**
     * Retrieves a list of projects that are past their due date.
     *
     * @param currentDate The current date for comparison.
     * @return A list of past due date projects.
     * @throws SQLException If a database error occurs.
     */


    @Override
    public List<Project> getPastDueDateProjects(LocalDate currentDate) throws SQLException {
        String query = "SELECT * FROM Project WHERE completionDate IS NULL AND deadline < ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, java.sql.Date.valueOf(currentDate));
            
            try (ResultSet resultSet = statement.executeQuery()) {
                return createProjectListFromResultSet(resultSet);
            }
        }
    }
    
    /**
     * Searches for projects by project number or name keyword.
     *
     * @param searchQuery The search query (project number or name keyword).
     * @return A list of projects matching the search query.
     * @throws SQLException If a database error occurs.
     */



    @Override
    public List<Project> findProjectsByNumberOrName(String searchQuery) throws SQLException {
        String query = "SELECT * FROM Project WHERE projectNumber = ? OR projectName LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int projectNumber = -1;
            try {
                projectNumber = Integer.parseInt(searchQuery);
            } catch (NumberFormatException ignored) {
            }

            statement.setInt(1, projectNumber);
            statement.setString(2, "%" + searchQuery + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                return createProjectListFromResultSet(resultSet);
            }
        }
    }

    /**
     * Creates a Project object from a ResultSet containing project data.
     *
     * @param resultSet The ResultSet containing project data.
     * @return A Project object created from the ResultSet data.
     * @throws SQLException If a database error occurs.
     */

    private Project createProjectFromResultSet(ResultSet resultSet) throws SQLException {
        int projectNumber = resultSet.getInt("projectNumber");
        String projectName = resultSet.getString("projectName");
        String buildingType = resultSet.getString("buildingType");
        String physicalAddress = resultSet.getString("physicalAddress");
        int erfNumber = resultSet.getInt("erfNumber");
        double totalFee = resultSet.getDouble("totalFee");
        double amountPaid = resultSet.getDouble("amountPaid");
        LocalDate deadline = resultSet.getDate("deadline").toLocalDate();
        int ArchitectID = resultSet.getInt("ArchitectID");
        int ContractorID = resultSet.getInt("ContractorID");
        int CustomerID = resultSet.getInt("CustomerID");

        return new Project(  projectNumber, projectName, buildingType, physicalAddress, erfNumber,
        totalFee, amountPaid, deadline, ArchitectID, ContractorID, CustomerID);
    }

    /**
     * Creates a list of Project objects from a ResultSet containing multiple project data.
     *
     * @param resultSet The ResultSet containing multiple project data.
     * @return A list of Project objects created from the ResultSet data.
     * @throws SQLException If a database error occurs.
     */


    private List<Project> createProjectListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Project> projects = new ArrayList<>();
        while (resultSet.next()) {
            projects.add(createProjectFromResultSet(resultSet));
        }
        return projects;
    }
/**
     * Retrieves an architect by their ID.
     *
     * @param architectId The ID of the architect.
     * @return The retrieved architect, or null if not found.
     * @throws SQLException If a database error occurs.
     */


    @Override
    public Architect getArchitectById(int architectId) throws SQLException {
        String query = "SELECT * FROM Architect WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, architectId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createArchitectFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param customerId The ID of the customer.
     * @return The retrieved customer, or null if not found.
     * @throws SQLException If a database error occurs.
     */


    
    @Override
    public Customer getCustomerById(int customerId) throws SQLException {
        String query = "SELECT * FROM Customer WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createCustomerFromResultSet(resultSet);
                }
            }
        }
        return null;
    }
    
    /**
     * Retrieves a contractor by their ID.
     *
     * @param contractorId The ID of the contractor.
     * @return The retrieved contractor, or null if not found.
     * @throws SQLException If a database error occurs.
     */



    @Override
    public Contractor getContractorById(int contractorId) throws SQLException {
        String query = "SELECT * FROM Contractor WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, contractorId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createContractorFromResultSet(resultSet);
                }
            }
        }
        return null;
    }
/**
     * Creates an Architect object from a ResultSet containing architect data.
     *
     * @param resultSet The ResultSet containing architect data.
     * @return An Architect object created from the ResultSet data.
     * @throws SQLException If a database error occurs.
     */

    

    private Architect createArchitectFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String telephone = resultSet.getString("telephone");
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
    
        return new Architect(id, name, telephone, email, address);


    }
/**
     * Creates a Customer object from a ResultSet containing customer data.
     *
     * @param resultSet The ResultSet containing customer data.
     * @return A Customer object created from the ResultSet data.
     * @throws SQLException If a database error occurs.
     */


    private Customer createCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String telephone = resultSet.getString("telephone");
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
    
        return new Customer(id, name, telephone, email, address);
    }

    /**
     * Creates a Contractor object from a ResultSet containing contractor data.
     *
     * @param resultSet The ResultSet containing contractor data.
     * @return A Contractor object created from the ResultSet data.
     * @throws SQLException If a database error occurs.
     */

    private Contractor createContractorFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String telephone = resultSet.getString("telephone");
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
    
        return new Contractor(id, name, telephone, email, address);
    }
 /**
     * Retrieves a list of all architects from the database.
     *
     * @return A list of all architects stored in the database.
     * @throws SQLException If a database error occurs.
     */

    
        @Override
        public List<Architect> getAllArchitects() throws SQLException {
            List<Architect> architects = new ArrayList<>();
            String query = "SELECT * FROM Architect";
    
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String telephone = resultSet.getString("telephone");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
    
                    Architect architect = new Architect(id, name, telephone, email, address);
                    architects.add(architect);
                }
            }
    
            return architects;
        }

    /**
     * Adds a new architect to the database.
     *
     * @param architect The Architect object to be added.
     * @throws SQLException If a database error occurs.
     */
    
        @Override
        public void addArchitect(Architect architect) throws SQLException {
            String query = "INSERT INTO Architect (id, name, telephone, email, address) " +
                           "VALUES (?, ?, ?, ?, ?)";
    
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, architect.getID());
                statement.setString(2, architect.getName());
                statement.setString(3, architect.getTelephone());
                statement.setString(4, architect.getEmail());
                statement.setString(5, architect.getAddress());
                
    
                statement.executeUpdate();
            }
        }
    /**
     * Updates the information of an existing architect in the database.
     *
     * @param architect The Architect object with updated information.
     * @throws SQLException If a database error occurs.
     */
    public void updateArchitect(Architect architect) throws SQLException {
        String query = "UPDATE Architect SET name = ?, telephone = ?, email = ?, " +
                       "address = ? WHERE id = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, architect.getID());
            statement.setString(2, architect.getName());
            statement.setString(3, architect.getTelephone());
            statement.setString(4, architect.getEmail());
            statement.setString(5, architect.getAddress());
    
            statement.executeUpdate();
        }
    }
    

          /**
     * Retrieves a list of all customers from the database.
     *
     * @return A list of all customers stored in the database.
     * @throws SQLException If a database error occurs.
     */
    
        @Override
        public List<Customer> getAllCustomers() throws SQLException {
            List<Customer> customers = new ArrayList<>();
            String query = "SELECT * FROM Customer";
    
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                  int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String telephone = resultSet.getString("telephone");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
    
                    Customer customer = new Customer(id, name, telephone, email, address);
                    customers.add(customer);
                }
            }
    
            return customers;
        }

     /**
     * Adds a new customer to the database.
     *
     * @param customer The Customer object to be added.
     * @throws SQLException If a database error occurs.
     */
    
        @Override
        public void addCustomer(Customer customer) throws SQLException {
            String query = "INSERT INTO Customer (id, name, telephone, email, address) " +
                           "VALUES (?, ?, ?, ?, ?)";
    
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, customer.getID());
                statement.setString(2, customer.getName());
                statement.setString(3, customer.getTelephone());
                statement.setString(4, customer.getEmail());
                statement.setString(5, customer.getAddress());
    
                statement.executeUpdate();
            }
        }

/**
     * Updates the information of an existing customer in the database.
     *
     * @param customer The Customer object with updated information.
     * @throws SQLException If a database error occurs.
     */

    
        @Override
        public void updateCustomer(Customer customer) throws SQLException {
            String query = "UPDATE Contractor SET name = ?, telephone = ?, email = ?," +"address= ? WHERE id = ?";
    
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, customer.getID());
                statement.setString(2, customer.getName());
                statement.setString(3, customer.getTelephone());
                statement.setString(4, customer.getEmail());
                statement.setString(5, customer.getAddress());
    
                statement.executeUpdate();
            }
        }

        /**
     * Retrieves a list of all contractors from the database.
     *
     * @return A list of all contractors stored in the database.
     * @throws SQLException If a database error occurs.
     */
    
        @Override
        public List<Contractor> getAllContractors() throws SQLException {
            List<Contractor> contractors = new ArrayList<>();
            String query = "SELECT * FROM Contractor";
    
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String telephone = resultSet.getString("telephone");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
    
                    Contractor contractor = new Contractor(id, name, telephone, email, address);
                    contractors.add(contractor);
                }
            }
    
            return contractors;

        }

        /**
     * Adds a new contractor to the database.
     *
     * @param contractor The Contractor object to be added.
     * @throws SQLException If a database error occurs.
     */
    
        @Override
        public void addContractor(Contractor contractor) throws SQLException {
            String query = "INSERT INTO Contractor (id, name, telephone, email, address) " +
                           "VALUES (?, ?, ?, ?, ?)";
    
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, contractor.getID());
                statement.setString(2, contractor.getName());
                statement.setString(3, contractor.getTelephone());
                statement.setString(4, contractor.getEmail());
                statement.setString(5, contractor.getAddress());
    
                statement.executeUpdate();
            }
        }

        /**
     * Updates the information of an existing contractor in the database.
     *
     * @param contractor The Contractor object with updated information.
     * @throws SQLException If a database error occurs.
     */
    
        @Override
        public void updateContractor(Contractor contractor) throws SQLException {
            String query = "UPDATE Contractor SET name = ?, telephone = ?, email = ?," +"address= ? WHERE id = ?";
    
            try (PreparedStatement statement = connection.prepareStatement(query)) {
               statement.setInt(1, contractor.getID());
                statement.setString(2, contractor.getName());
                statement.setString(3, contractor.getTelephone());
                statement.setString(4, contractor.getEmail());
                statement.setString(5, contractor.getAddress());
    
                statement.executeUpdate();
            }
        }
    
     
    }
    
// Used definitions from Hyperion Dev Full Stack Software and Web Developer pdf resources //
