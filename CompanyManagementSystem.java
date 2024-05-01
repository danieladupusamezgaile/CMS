import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompanyManagementSystem {
    private List<Department> departments = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addDepartment() {
        System.out.print("Enter department name: ");
        String name = scanner.nextLine();
        Department department = new Department(name); // create new department
        departments.add(department); // add it to departments arrayList
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║ Department " + name + " added successfully  ");
        System.out.println("╚══════════════════════════════════════════╝");
        pressEnterToContinue();
        this.run(); // return to menu
    }

    public void addEmployeeToDepartment() {
        if (departments.isEmpty()) { // if no departments available
            System.out.println("No departments available. Please add a department first.");
            pressEnterToContinue();
            this.run();
        }

        // get employee data
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee id: ");
        while (!scanner.hasNextInt()) { // validate int input
            System.out.println("Invalid ID");
            System.out.print("Enter employee id: ");
            scanner.next();
        }
        int id = scanner.nextInt();

        System.out.println("Available departments:");
        for (int i = 0; i < departments.size(); i++) { // print available departments
            System.out.println((i + 1) + ". " + departments.get(i).getName());
        }
        System.out.print("Select department (enter department number): ");
        // validate input
        int deptNumber = this.validateOption("Select department (enter department number): ", departments.size());

        Employee employee = new Employee(name, id); // create new employee
        departments.get(deptNumber - 1).addEmployee(employee); // add employee to ArrayList

        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println(
                "║ Employee '" + name + "' added to department '" + departments.get(deptNumber - 1).getName() + "'");
        System.out.println("╚═════════════════════════════════════════════════════╝");

        scanner.nextLine();
        pressEnterToContinue();
        this.run(); // return to menu
    }

    public void displayCompanyStructure() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║              Company Structure           ║");
        System.out.println("╠══════════════════════════════════════════╣");
        for (Department department : departments) {
            System.out.println("║ Department: " + String.format("%-34s", department.getName()));
            System.out.println("║ Employees:                               ║");
            List<Employee> employees = department.getEmployees();
            for (Employee employee : employees) {
                System.out.println("║   - Name: " + String.format("%-30s", employee.getName()));
                System.out.println("║     ID: " + String.format("%-32d", employee.getId()));
            }
            if (employees.isEmpty()) {
                System.out.println("║     No employees                         ║");
            }
            System.out.println("╠══════════════════════════════════════════╣");
        }
        if (departments.isEmpty()) {
            System.out.println("║             No departments               ║");
        }
        System.out.println("╚══════════════════════════════════════════╝");
        pressEnterToContinue();
        this.run(); // return to menu
    }

    public void pressEnterToContinue() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    public int validateOption(String message, int numOfOptions) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid option!");
            System.out.print(message);
            scanner.next();
        }
        int option = scanner.nextInt();
        while (option < 1 || option > numOfOptions) {
            System.out.println("Invalid option!");
            System.out.print(message);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid option!");
                System.out.print(message);
                scanner.next();
            }
            option = scanner.nextInt();
        }
        return option;
    }

    public void run() {
        int choice;
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║      Company Management      ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1. Add Department            ║");
        System.out.println("║ 2. Add Employee to Department║");
        System.out.println("║ 3. Display Company Structure ║");
        System.out.println("║ 4. Exit                      ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("Enter your choice: ");
        System.out.print("--> ");

        choice = validateOption("Please select again --> ", 4);
        scanner.nextLine();
        switch (choice) {
            case 1:
                addDepartment();
                break;
            case 2:
                addEmployeeToDepartment();
                break;
            case 3:
                displayCompanyStructure();
                break;
            case 4:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid option. Please select again --> ");
        }
    }

    public static void main(String[] args) {
        CompanyManagementSystem system = new CompanyManagementSystem();
        system.run();
    }
}