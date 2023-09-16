import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Poised Project Management System Application.
 */


/**
 * Displays the main menu options.
 */

public class PoisedPMSApp {

    public static void displayMenu() {
        System.out.println("Poised Project Management System Menu");
        System.out.println("1. Create a new project");
        System.out.println("2. Update project details");
        System.out.println("3. Finalize a project");
        System.out.println("4. View in-progress projects");
        System.out.println("5. View past due date projects");
        System.out.println("6. Search projects by number or name");
        System.out.println("7. View project details");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
        
    }

    /**
     * Creates a new project based on user input.
     *
     * @param input            The Scanner object to read user input.
     * @param projectRepository The repository to interact with project data.
     */
    
     private static void createProject(Scanner input, ProjectRepository projectRepository) {
        try {
            System.out.print("Enter project number: ");
            int projectNumber = input.nextInt();
            input.nextLine();

            System.out.print("Enter project name: ");
            String projectName = input.nextLine();

            System.out.print("Enter building type: ");
            String buildingType = input.nextLine();

            System.out.print("Enter physical address: ");
            String physicalAddress = input.nextLine();

            System.out.print("Enter ERF number: ");
            int erfNumber = input.nextInt();
            input.nextLine();

            System.out.print("Enter total fee: ");
            double totalFee = input.nextDouble();
            input.nextLine();

            System.out.print("Enter amount paid: ");
            double amountPaid = input.nextDouble();
            input.nextLine();

            System.out.print("Enter deadline (yyyy-MM-dd): ");
            String deadlineString = input.nextLine();
            LocalDate deadline = LocalDate.parse(deadlineString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            System.out.println("Enter Architect details:");
            System.out.print("Architect ID: ");
            int architectId = input.nextInt();
            input.nextLine();
            System.out.print("Architect Name: ");
            String architectName = input.nextLine();
            System.out.print("Architect Telephone: ");
            String architectTelephone = input.nextLine();
            System.out.print("Architect Email: ");
            String architectEmail = input.nextLine();
            System.out.print("Architect Address: ");
            String architectAddress = input.nextLine();

            // Create Architect object
            Architect architect = new Architect(architectId, architectName, architectTelephone, architectEmail, architectAddress);

            System.out.println("Enter Contractor details:");
            System.out.print("Contractor ID: ");
            int contractorId = input.nextInt();
            input.nextLine();
            System.out.print("Contractor Name: ");
            String contractorName = input.nextLine();
            System.out.print("Contractor Telephone: ");
            String contractorTelephone = input.nextLine();
            System.out.print("Contractor Email: ");
            String contractorEmail = input.nextLine();
            System.out.print("Contractor Address: ");
            String contractorAddress = input.nextLine();

            // Create Contractor object
            Contractor contractor = new Contractor(contractorId, contractorName, contractorTelephone, contractorEmail, contractorAddress);

            // Capture Customer details
            System.out.println("Enter Customer details:");
            System.out.print("Customer ID: ");
            int customerId = input.nextInt();
            input.nextLine();
            System.out.print("Customer Name: ");
            String customerName = input.nextLine();
            System.out.print("Customer Telephone: ");
            String customerTelephone = input.nextLine();
            System.out.print("Customer Email: ");
            String customerEmail = input.nextLine();
            System.out.print("Customer Address: ");
            String customerAddress = input.nextLine();

            // Create Customer object
            Customer customer = new Customer(customerId, customerName, customerTelephone, customerEmail, customerAddress);

            // Add Architect, Contractor, and Customer to their respective tables
            projectRepository.addArchitect(architect);
            projectRepository.addContractor(contractor);
            projectRepository.addCustomer(customer);

            // Add the project to the repository
            projectRepository.addProject(projectNumber, projectName, buildingType, physicalAddress, erfNumber, totalFee, amountPaid, deadline, architect, contractor, customer);

            System.out.println("Project added successfully.");
        } catch (SQLException e) {
            System.out.println("An error occurred while interacting with the database: " + e.getMessage());
        }
    }


/**
 * Updates an existing project's details based on user input.
 *
 * @param input            The Scanner object to read user input.
 * @param projectRepository The repository to interact with project data.
 */

private static void updateProject(Scanner input, ProjectRepository projectRepository) {
    try {
        System.out.print("Enter project number to update: ");
        int projectNumber = input.nextInt();
        input.nextLine(); // Consume the newline character

        Project existingProject = projectRepository.getProjectByNumber(projectNumber);

        if (existingProject == null) {
            System.out.println("Project not found.");
        } else {
            System.out.print("Enter updated project name: ");
            String projectName = input.nextLine();

            System.out.print("Enter updated building type: ");
            String buildingType = input.nextLine();

            System.out.print("Enter updated physical address: ");
            String physicalAddress = input.nextLine();

            System.out.print("Enter updated ERF number: ");
            int erfNumber = input.nextInt();
            input.nextLine(); // Consume the newline character

            System.out.print("Enter updated total fee: ");
            double totalFee = input.nextDouble();
            input.nextLine(); // Consume the newline character

            System.out.print("Enter updated amount paid: ");
            double amountPaid = input.nextDouble();
            input.nextLine(); // Consume the newline character

            System.out.print("Enter updated deadline (yyyy-MM-dd): ");
            String deadlineString = input.nextLine();
            LocalDate deadline = LocalDate.parse(deadlineString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Capture updated Architect details
            System.out.println("Enter updated Architect details:");
            System.out.print("Architect ID: ");
            int architectId = input.nextInt();
            input.nextLine(); // Consume the newline character
            System.out.print("Architect Name: ");
            String architectName = input.nextLine();
            System.out.print("Architect Telephone: ");
            String architectTelephone = input.nextLine();
            System.out.print("Architect Email: ");
            String architectEmail = input.nextLine();
            System.out.print("Architect Address: ");
            String architectAddress = input.nextLine();

            // Create updated Architect object
            Architect updatedArchitect = new Architect(architectId, architectName, architectTelephone, architectEmail, architectAddress);

            // Capture updated Contractor details
            System.out.println("Enter updated Contractor details:");
            System.out.print("Contractor ID: ");
            int contractorId = input.nextInt();
            input.nextLine(); // Consume the newline character
            System.out.print("Contractor Name: ");
            String contractorName = input.nextLine();
            System.out.print("Contractor Telephone: ");
            String contractorTelephone = input.nextLine();
            System.out.print("Contractor Email: ");
            String contractorEmail = input.nextLine();
            System.out.print("Contractor Address: ");
            String contractorAddress = input.nextLine();

            // Create updated Contractor object
            Contractor updatedContractor = new Contractor(contractorId, contractorName, contractorTelephone, contractorEmail, contractorAddress);

            // Capture updated Customer details
            System.out.println("Enter updated Customer details:");
            System.out.print("Customer ID: ");
            int customerId = input.nextInt();
            input.nextLine(); // Consume the newline character
            System.out.print("Customer Name: ");
            String customerName = input.nextLine();
            System.out.print("Customer Telephone: ");
            String customerTelephone = input.nextLine();
            System.out.print("Customer Email: ");
            String customerEmail = input.nextLine();
            System.out.print("Customer Address: ");
            String customerAddress = input.nextLine();

            // Create updated Customer object
            Customer updatedCustomer = new Customer(customerId, customerName, customerTelephone, customerEmail, customerAddress);

            // Use ProjectRepository to update the project and associated details
            projectRepository.updateProject(projectNumber, projectName, buildingType, physicalAddress, erfNumber, totalFee, amountPaid, deadline, updatedArchitect, updatedContractor, updatedCustomer);
            System.out.println("Project updated successfully.");

            // Update the Architect, Contractor, and Customer in the repository
            projectRepository.updateArchitect(updatedArchitect);
            projectRepository.updateContractor(updatedContractor);
            projectRepository.updateCustomer(updatedCustomer);
        }
    } catch (SQLException e) {
        System.out.println("An error occurred while interacting with the database: " + e.getMessage());
    }
}
 /**
 * Finalizes a project based on user input and marks it as completed.
 *
 * @param input            The Scanner object to read user input.
 * @param projectRepository The repository to interact with project data.
 */


 private static void finalizeProject(Scanner input, ProjectRepository projectRepository) {
    try {
        System.out.print("Enter project number to finalize: ");
        int projectNumber = input.nextInt();
        input.nextLine(); // Consume the newline character

        Project project = projectRepository.getProjectByNumber(projectNumber);
        if (project == null) {
            System.out.println("Project not found.");
        } else {
            System.out.println("Enter completion date:");
            String completionDate = input.nextLine().trim();

            // Finalize the project
            project.finalizeProject(completionDate);

            // Collect updated architect data from user input
            System.out.println("Enter updated Architect details:");
            System.out.print("Architect ID: ");
            int updatedArchitectId = input.nextInt();
            input.nextLine(); // Consume the newline character
            System.out.print("Architect Name: ");
            String updatedArchitectName = input.nextLine();
            System.out.print("Architect Telephone: ");
            String updatedArchitectTelephone = input.nextLine();
            System.out.print("Architect Email: ");
            String updatedArchitectEmail = input.nextLine();
            System.out.print("Architect Address: ");
            String updatedArchitectAddress = input.nextLine();

            System.out.println("Enter updated Contractor details:");
            System.out.print("Contractor ID: ");
            int updatedContractorId = input.nextInt();
            input.nextLine(); // Consume the newline character
            System.out.print("Contractor Name: ");
            String updatedContractorName = input.nextLine();
            System.out.print("Contractor Telephone: ");
            String updatedContractorTelephone = input.nextLine();
            System.out.print("Contractor Email: ");
            String updatedContractorEmail = input.nextLine();
            System.out.print("Contractor Address: ");
            String updatedContractorAddress = input.nextLine();

            System.out.print("Customer ID: ");
            int updatedCustomerId = input.nextInt();
            input.nextLine(); // Consume the newline character
            System.out.print("Customer Name: ");
            String updatedCustomerName = input.nextLine();
            System.out.print("Customer Telephone: ");
            String updatedCustomerTelephone = input.nextLine();
            System.out.print("Customer Email: ");
            String updatedCustomerEmail = input.nextLine();
            System.out.print("Customer Address: ");
            String updatedCustomerAddress = input.nextLine();


            // Create the updated Architect object
            Architect updatedArchitect = new Architect(updatedArchitectId, updatedArchitectName, updatedArchitectTelephone, updatedArchitectEmail, updatedArchitectAddress);
            Contractor updatedContractor = new Contractor(updatedContractorId, updatedContractorName, updatedContractorTelephone, updatedContractorEmail, updatedContractorAddress);
            Customer updatedCustomer = new Customer(updatedCustomerId, updatedCustomerName, updatedCustomerTelephone, updatedCustomerEmail, updatedCustomerAddress);
            // Repeat the same process for Contractor and Customer objects
            // ...

            // Update the project status in the repository
            projectRepository.updateProject(
                project.getProjectNumber(),
                project.getProjectName(),
                project.getBuildingType(),
                project.getPhysicalAddress(),
                project.getErfNumber(),
                project.getTotalFee(),
                project.getAmountPaid(),
                project.getDeadline(),
                updatedArchitect,
                updatedContractor,
                updatedCustomer
            );

            System.out.println("Project finalized successfully.");
        }
    } catch (SQLException e) {
        System.out.println("An error occurred while interacting with the database: " + e.getMessage());
    }
}
    /**
     * Displays projects that are currently in progress.
     *
     * @param projectRepository The repository to interact with project data.
     * @param currentDate      The current date for checking project status.
     */

    
    
    private static void viewInProgressProjects(ProjectRepository projectRepository, LocalDate currentDate) {
        try {
            List<Project> inProgressProjects = projectRepository.getInProgressProjects(currentDate);
            if (inProgressProjects.isEmpty()) {
                System.out.println("No projects are currently in progress.");
            } else {
                System.out.println("In-Progress Projects:");
                for (Project project : inProgressProjects) {
                    System.out.println(project.getProjectNumber() + ": " + project.getProjectName());
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while interacting with the database: " + e.getMessage());
        }
    }
    
    /**
     * Displays projects that are past their due date.
     *
     * @param projectRepository The repository to interact with project data.
     * @param currentDate      The current date for checking project status.
     */
    

    private static void viewPastDueDateProjects(ProjectRepository projectRepository, LocalDate currentDate) {
        
        
        try {
            List<Project> pastDueDateProjects = projectRepository.getPastDueDateProjects(currentDate);
            if (pastDueDateProjects.isEmpty()) {
                System.out.println("No projects are past due date.");
            } else {
                System.out.println("Past Due Date Projects:");
                for (Project project : pastDueDateProjects) {
                    System.out.println(project.getProjectNumber() + ": " + project.getProjectName());
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while interacting with the database: " + e.getMessage());
        }
    }
    /**
     * Searches for projects based on project number or name keyword.
     *
     * @param input            The Scanner object to read user input.
     * @param projectRepository The repository to interact with project data.
     */
    
    
    private static void searchProjectsByNumberOrName(Scanner input, ProjectRepository projectRepository) {
        try {
            System.out.print("Enter project number or name keyword: ");
            String searchQuery = input.nextLine();
            List<Project> matchingProjects = projectRepository.findProjectsByNumberOrName(searchQuery);
            if (matchingProjects.isEmpty()) {
                System.out.println("No projects found matching the search query.");
            } else {
                System.out.println("Matching Projects:");
                for (Project project : matchingProjects) {
                    System.out.println(project.getProjectNumber() + ": " + project.getProjectName());
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while interacting with the database: " + e.getMessage());
        }
    }


/**
 * Searches for projects based on project number or name keyword.
 *
 * @param input            The Scanner object to read user input.
 * @param projectRepository The repository to interact with project data.
 */
    
    private static void viewProjectDetails(Scanner input, ProjectRepository projectRepository) {
        try {
            System.out.print("Enter project number: ");
            int projectNumber = input.nextInt();
            input.nextLine(); // Consume the newline character

            Project project = projectRepository.getProjectByNumber(projectNumber);
            if (project == null) {
                System.out.println("Project not found.");
            } else {
                System.out.println("Project Details:");
                System.out.println("Project Number: " + project.getProjectNumber());
                System.out.println("Project Name: " + project.getProjectName());
                System.out.println("Building Type: " + project.getBuildingType());
                System.out.println("Physical Address: " + project.getPhysicalAddress());
                System.out.println("ERF Number: " + project.getErfNumber());
                System.out.println("Total Fee: " + project.getTotalFee());
                System.out.println("Amount Paid: " + project.getAmountPaid());
                System.out.println("Deadline: " + project.getDeadline());
                System.out.println("Architect ID: " + project.getArchitectID());
                System.out.println("Contractor ID: " + project.getContractorID());
                System.out.println("Customer ID: " + project.getCustomerID());
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while interacting with the database: " + e.getMessage());
        }
    }
    
   /**
    * Database Connection 
    * 
    * @param args  The Database connection to "poisepms" database 
    */

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/poisepms";
        String username = "root";
        String password = "NondyeboG1962";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            ProjectRepository projectRepository = new ProjectRepositoryImpl(connection);
            

            /**
             * Main method to execute the Poised Project Management System.
             *
             * @param args The command-line arguments.
             */

            Scanner input = new Scanner(System.in);

            while (true) {
                displayMenu();
                int choice = input.nextInt();
                input.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        createProject(input, projectRepository);
                        break;
                    case 2:
                        updateProject(input, projectRepository);
                        break;
                    case 3:
                        finalizeProject(input, projectRepository);
                        break;
                    case 4:
                    LocalDate currentDate = LocalDate.now();

                        viewInProgressProjects(projectRepository, currentDate);
                        break;
                    case 5:
                    currentDate = LocalDate.now();
                        viewPastDueDateProjects(projectRepository, currentDate);
                        break;
                    case 6:
                        searchProjectsByNumberOrName(input, projectRepository);
                        break;
                    case 7:
                        viewProjectDetails(input, projectRepository);
                        break;
                    case 8:
                        System.out.println("Exiting the application.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }
}
// Used definitions from Hyperion Dev Full Stack Software and Web Developer pdf resources //
