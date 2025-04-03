package management;

import models.*;
import core.*;

import java.util.*;

public class VehicleManagement {
    private static List<Motorcycle> motorcycles = new ArrayList<>();
    private static List<Car> cars = new ArrayList<>();
    private static List<Truck> trucks = new ArrayList<>();

    // Main method to run the application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Display the initial menu
        while (true) {
            System.out.println("----- Vehicle Management System -----");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Delete Vehicle");
            System.out.println("3. Update Vehicle");
            System.out.println("4. Search Vehicle");
            System.out.println("5. Display All Vehicles");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addVehicle(scanner);
                    break;
                case 2:
                    deleteVehicle(scanner);
                    break;
                case 3:
                    updateVehicle(scanner);
                    break;
                case 4:
                    searchVehicle(scanner);
                    break;
                case 5:
                    displayAllVehicles();
                    break;
                case 6:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Add a vehicle
    private static void addVehicle(Scanner scanner) {
        // Get common vehicle details first
        System.out.print("Enter manufacture company: ");
        String manufactureCompany = scanner.nextLine();
        System.out.print("Enter manufacture date (YYYY-MM-DD): ");
        String manufactureDate = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter plate number: ");
        int plateNum = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter body serial number: ");
        int bodySerialNum = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Get engine details
        System.out.print("Enter engine manufacture: ");
        String engineManufacture = scanner.nextLine();
        System.out.print("Enter engine manufacture date: ");
        String engineManufactureDate = scanner.nextLine();
        System.out.print("Enter engine model: ");
        String engineModel = scanner.nextLine();
        System.out.print("Enter engine capacity: ");
        int engineCapacity = scanner.nextInt();
        System.out.print("Enter engine cylinders: ");
        int engineCylinders = scanner.nextInt();
        System.out.print("Enter fuel type (1 for DIESEL, 2 for GASOLINE): ");
        FuelType fuelType = (scanner.nextInt() == 1) ? FuelType.DIESEL : FuelType.GASOLINE;
        scanner.nextLine(); // Consume newline
        Engine engine = new Engine(engineManufacture, engineManufactureDate, engineModel, engineCapacity, engineCylinders, fuelType);

        // Get gear type input
        System.out.println("Choose gear type for the vehicle (1. Normal, 2. Automatic): ");
        int gearTypeChoice = scanner.nextInt();
        GearType gearType = (gearTypeChoice == 1) ? GearType.NORMAL : GearType.AUTOMATIC;
        scanner.nextLine(); // Consume newline

        // Get vehicle common attributes
        System.out.print("Enter vehicle length: ");
        int length = scanner.nextInt();
        System.out.print("Enter vehicle width: ");
        int width = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter vehicle color: ");
        String color = scanner.nextLine();

        // Ask for specific vehicle type
        System.out.println("Choose vehicle type to add (1. Motorcycle, 2. Car, 3. Truck): ");
        int vehicleType = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Add the vehicle based on type
        switch (vehicleType) {
            case 1: // Motorcycle
                System.out.print("Enter tier diameter: ");
                double tierDiameter = scanner.nextDouble();
                System.out.print("Enter motorcycle length: ");
                double motorcycleLength = scanner.nextDouble();
                Motorcycle motorcycle = new Motorcycle(manufactureCompany, manufactureDate, model, engine, plateNum, gearType, bodySerialNum, tierDiameter, motorcycleLength);
                motorcycles.add(motorcycle);
                System.out.println("Motorcycle added!");
                break;
            case 2: // Car
                System.out.print("Enter number of chairs: ");
                int chairNum = scanner.nextInt();
                System.out.print("Is the furniture leather? (1 for yes, 0 for no): ");
                int isLeatherInput = scanner.nextInt();
                boolean isLeather = (isLeatherInput == 1);
                // Create the car object using the user input values
                Car car = new Car(manufactureCompany, manufactureDate, model, engine, plateNum, gearType, bodySerialNum, length, width, color, chairNum, isLeather);
                cars.add(car);
                System.out.println("Car added!");
                break;
            case 3: // Truck
                System.out.print("Enter free weight: ");
                double freeWeight = scanner.nextDouble();
                System.out.print("Enter full weight: ");
                double fullWeight = scanner.nextDouble();
                // Create the truck object using the user input values
                Truck truck = new Truck(manufactureCompany, manufactureDate, model, engine, plateNum, gearType, bodySerialNum, length, width, color, freeWeight, fullWeight);
                trucks.add(truck);
                System.out.println("Truck added!");
                break;
            default:
                System.out.println("Invalid vehicle type!");
        }
    }

    // Delete a vehicle
    private static void deleteVehicle(Scanner scanner) {
        System.out.print("Enter the plate number of the vehicle to delete: ");
        int plateNum = scanner.nextInt();
        boolean found = false;

        // Check in all lists
        found = deleteFromList(motorcycles, plateNum) || deleteFromList(cars, plateNum) || deleteFromList(trucks, plateNum);

        if (found) {
            System.out.println("Vehicle deleted successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    // Delete from specific list
    private static <T> boolean deleteFromList(List<T> vehicles, int plateNum) {
        for (T vehicle : vehicles) {
            if (vehicle instanceof Automobile) {
                Automobile auto = (Automobile) vehicle;
                if (auto.getPlateNum() == plateNum) {
                    vehicles.remove(vehicle);
                    return true;
                }
            }
        }
        return false;
    }

    // Update a vehicle
    private static void updateVehicle(Scanner scanner) {
        System.out.print("Enter the plate number of the vehicle to update: ");
        int plateNum = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the vehicle
        boolean found = false;
        for (Car car : cars) {
            if (car.getPlateNum() == plateNum) {
                found = true;
                System.out.print("Enter new model: ");
                String newModel = scanner.nextLine();
                car.setModel(newModel);
                System.out.println("Car updated!");
                break;
            }
        }

        if (!found) {
            System.out.println("Vehicle not found.");
        }
    }

    // Search for a vehicle
    private static void searchVehicle(Scanner scanner) {
        System.out.print("Enter the plate number of the vehicle to search: ");
        int plateNum = scanner.nextInt();

        boolean found = false;

        // Check in all lists
        found = searchInList(motorcycles, plateNum) || searchInList(cars, plateNum) || searchInList(trucks, plateNum);

        if (!found) {
            System.out.println("Vehicle not found.");
        }
    }

    // Search in specific list
    private static <T> boolean searchInList(List<T> vehicles, int plateNum) {
        for (T vehicle : vehicles) {
            if (vehicle instanceof Automobile) {
                Automobile auto = (Automobile) vehicle;
                if (auto.getPlateNum() == plateNum) {
                    System.out.println("Vehicle found: " + auto.getModel());
                    return true;
                }
            }
        }
        return false;
    }

    // Display all vehicles
    private static void displayAllVehicles() {
        System.out.println("All vehicles:");
        System.out.println("Motorcycles: " + motorcycles.size());
        System.out.println("Cars: " + cars.size());
        System.out.println("Trucks: " + trucks.size());
    }
}
