import java.util.Scanner;

// Class representing a Patient
class Patient {
    int id;
    String name;
    int age;
    String disease;
    Patient left, right;

    public Patient(int id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
        this.left = this.right = null;
    }
}

// Binary Search Tree for managing Patients
class PatientBST {
    private Patient root;

    // Insert a new patient
    public void insert(int id, String name, int age, String disease) {
        root = insertRec(root, id, name, age, disease);
    }

    private Patient insertRec(Patient root, int id, String name, int age, String disease) {
        if (root == null) {
            return new Patient(id, name, age, disease);
        }
        if (id < root.id) {
            root.left = insertRec(root.left, id, name, age, disease);
        } else if (id > root.id) {
            root.right = insertRec(root.right, id, name, age, disease);
        }
        return root;
    }

    // In-order traversal to display patients
    public void display() {
        if (root == null) {
            System.out.println("No patients found.");
        } else {
            displayRec(root);
        }
    }

    private void displayRec(Patient root) {
        if (root != null) {
            displayRec(root.left);
            System.out.println("ID: " + root.id + " | Name: " + root.name + " | Age: " + root.age + " | Disease: " + root.disease);
            displayRec(root.right);
        }
    }

    // Search for a patient by ID
    public void search(int id) {
        Patient result = searchRec(root, id);
        if (result != null) {
            System.out.println("Patient Found: ID: " + result.id + " | Name: " + result.name + " | Age: " + result.age + " | Disease: " + result.disease);
        } else {
            System.out.println("Patient with ID " + id + " not found.");
        }
    }

    private Patient searchRec(Patient root, int id) {
        if (root == null || root.id == id) {
            return root;
        }
        if (id < root.id) {
            return searchRec(root.left, id);
        }
        return searchRec(root.right, id);
    }
}

// Main class
public class HospitalManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientBST bst = new PatientBST();
        
        while (true) {
            System.out.println("\n--- Hospital Patient Management System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Display Patients");
            System.out.println("3. Search Patient by ID");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Disease: ");
                    String disease = scanner.nextLine();
                    bst.insert(id, name, age, disease);
                    System.out.println("Patient added successfully!");
                    break;

                case 2:
                    System.out.println("\n--- Patient Records ---");
                    bst.display();
                    break;

                case 3:
                    System.out.print("Enter Patient ID to search: ");
                    int searchId = scanner.nextInt();
                    bst.search(searchId);
                    break;

                case 4:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
