package management;

import models.*;
import core.*;

import java.util.*;
import java.io.*;

public class VehicleManagement {
    private static List<Motorcycle> motorcycles = new ArrayList<>();
    private static List<Car> cars = new ArrayList<>();
    private static List<Truck> trucks = new ArrayList<>();

    private static final String MOTORCYCLE_FILE = "motorcycles.ser";
    private static final String CAR_FILE = "cars.ser";
    private static final String TRUCK_FILE = "trucks.ser";

    // Main method to run the application
    public static void main(String[] args) {
        loadData();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("----- Vehicle Management System -----");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Delete Vehicle");
            System.out.println("3. Update Vehicle");
            System.out.println("4. Search Vehicle");
            System.out.println("5. Display All Vehicles");
            System.out.println("6. Modify/View Body Serial Number");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addVehicle(scanner);
                case 2 -> deleteVehicle(scanner);
                case 3 -> updateVehicle(scanner);
                case 4 -> searchVehicle(scanner);
                case 5 -> displayAllVehicles();
                case 6 -> modifyBodySerialNumber(scanner);
                case 7 -> {
                    saveData();
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Save data to file
    private static void saveData() {
        try {
            ObjectOutputStream out1 = new ObjectOutputStream(new FileOutputStream(MOTORCYCLE_FILE));
            out1.writeObject(motorcycles);
            out1.close();

            ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream(CAR_FILE));
            out2.writeObject(cars);
            out2.close();

            ObjectOutputStream out3 = new ObjectOutputStream(new FileOutputStream(TRUCK_FILE));
            out3.writeObject(trucks);
            out3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load data from file
    private static void loadData() {
        try {
            FileInputStream file1 = new FileInputStream(MOTORCYCLE_FILE);
            motorcycles = (List<Motorcycle>) new ObjectInputStream(file1).readObject();

            FileInputStream file2 = new FileInputStream(CAR_FILE);
            cars = (List<Car>) new ObjectInputStream(file2).readObject();

            FileInputStream file3 = new FileInputStream(TRUCK_FILE);
            trucks = (List<Truck>) new ObjectInputStream(file3).readObject();
        } catch (Exception e) {
            motorcycles = new ArrayList<>();
            cars = new ArrayList<>();
            trucks = new ArrayList<>();
        }
    }

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
    private static void searchVehicle(Scanner scanner) {
        System.out.println("Search by: 1. Plate Number 2. Manufacture Name 3. Manufacture Date");
        int option = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;

        if (option == 1) {
            System.out.print("Enter plate number: ");
            int plate = scanner.nextInt();
            found = searchByPlate(plate);
        } else if (option == 2) {
            System.out.print("Enter manufacture name: ");
            String name = scanner.nextLine();
            found = searchByName(name);
        } else if (option == 3) {
            System.out.print("Enter manufacture date: ");
            String date = scanner.nextLine();
            found = searchByDate(date);
        }

        if (!found) {
            System.out.println("Vehicle not found.");
        }
    }

    private static boolean searchByPlate(int plateNum) {
        return searchInList(motorcycles, plateNum) || searchInList(cars, plateNum) || searchInList(trucks, plateNum);
    }

    private static boolean searchByName(String name) {
        return searchByField(motorcycles, name, "name") || searchByField(cars, name, "name") || searchByField(trucks, name, "name");
    }

    private static boolean searchByDate(String date) {
        return searchByField(motorcycles, date, "date") || searchByField(cars, date, "date") || searchByField(trucks, date, "date");
    }

    private static <T> boolean searchByField(List<T> vehicles, String value, String type) {
        for (T vehicle : vehicles) {
            if (vehicle instanceof Automobile auto) {
                if ((type.equals("name") && auto.getManufactureCompany().equalsIgnoreCase(value)) ||
                    (type.equals("date") && auto.getManufactureDate().equals(value))) {
                    System.out.println(auto);
                    return true;
                }
            }
        }
        return false;
    }

    private static <T> boolean searchInList(List<T> vehicles, int plateNum) {
        for (T vehicle : vehicles) {
            if (vehicle instanceof Automobile auto && auto.getPlateNum() == plateNum) {
                System.out.println(auto);
                return true;
            }
        }
        return false;
    }

    private static void displayAllVehicles() {
        motorcycles.forEach(System.out::println);
        cars.forEach(System.out::println);
        trucks.forEach(System.out::println);
    }

    private static void modifyBodySerialNumber(Scanner scanner) {
        System.out.print("Enter plate number: ");
        int plateNum = scanner.nextInt();

        for (Automobile vehicle : getAllVehicles()) {
            if (vehicle.getPlateNum() == plateNum) {
                System.out.println("Current body serial number: " + vehicle.getBodySerialNum());
                System.out.print("Enter new body serial number: ");
                int newSerial = scanner.nextInt();
                vehicle.setBodySerialNum(newSerial);
                System.out.println("Updated successfully.");
                return;
            }
        }

        System.out.println("Vehicle not found.");
    }

    private static List<Automobile> getAllVehicles() {
        List<Automobile> all = new ArrayList<>();
        all.addAll(motorcycles);
        all.addAll(cars);
        all.addAll(trucks);
        return all;
    }
}
