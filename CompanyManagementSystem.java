import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CompanyManagementSystem {
    private List<Department> departments;
    private Scanner scanner;

    public CompanyManagementSystem() {
        this.departments = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addDepartment() {
        System.out.print("Enter department name: ");
        String name = scanner.nextLine();
        Department department = new Department(name);
        departments.add(department);
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║ Department '" + name + "' added successfully");
        System.out.println("╚══════════════════════════════════════════╝");
        pressEnterToContinue();
    }

    public void addEmployeeToDepartment() {
        if (departments.isEmpty()) {
            System.out.println("No departments available. Please add a department first.");
            pressEnterToContinue();
            return;
        }

        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee id: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Available departments:");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". " + departments.get(i).getName());
        }
        System.out.print("Select department (enter department number): ");
        int deptNumber = scanner.nextInt();
        scanner.nextLine(); 
        if (deptNumber >= 1 && deptNumber <= departments.size()) {
            Department department = departments.get(deptNumber - 1);
            Employee employee = new Employee(name, id);
            department.addEmployee(employee);
            System.out.println("╔══════════════════════════════════════════╗");
            System.out.println("║ Employee '" + name + "' added to department '" + department.getName() + "'");
            System.out.println("╚══════════════════════════════════════════╝");
        } else {
            System.out.println("Invalid department number.");
        }
        pressEnterToContinue();
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
    }

    public void pressEnterToContinue() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    public void run() {
        int choice;
        do {
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
            choice = scanner.nextInt();
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
                    System.out.println("Invalid choice. Please select again.");
            }
        } while (choice != 4);
    }

    public static void main(String[] args) {
        CompanyManagementSystem system = new CompanyManagementSystem();
        system.run();
    }
}