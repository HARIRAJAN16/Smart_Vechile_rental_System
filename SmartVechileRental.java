```java
import java.util.*;

abstract class Vehicle {
    private boolean available;
    private String vehicleNo;
    private String vehicleType;

    public Vehicle(String vehicleNo, String vehicleType) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public abstract double getDailyRate();

    public abstract double getRentCost(int days);
}

class Bike extends Vehicle {
    private double dailyRate;

    public Bike(String vehicleNo, double dailyRate) {
        super(vehicleNo, "Bike");
        this.dailyRate = dailyRate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public double getRentCost(int days) {
        return days * dailyRate;
    }
}

class Car extends Vehicle {
    private double dailyRate;

    public Car(String vehicleNo, double dailyRate) {
        super(vehicleNo, "Car");
        this.dailyRate = dailyRate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public double getRentCost(int days) {
        return days * dailyRate;
    }
}

class SmartVehicleRentalSystem {
    private List<Vehicle> inventory;
    private Map<String, Integer> rentedVehicles;

    public SmartVehicleRentalSystem() {
        this.inventory = new ArrayList<>();
        this.rentedVehicles = new HashMap<>();
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void displayAvailableVehicles() {
        System.out.println("Available Vehicles:");
        for (Vehicle vehicle : inventory) {
            if (vehicle.isAvailable()) {
                System.out.println("Vehicle No: " + vehicle.getVehicleNo() +
                        ", Type: " + vehicle.getVehicleType() +
                        ", Daily Rate: " + vehicle.getDailyRate());
            }
        }
    }

    public void rentVehicle(String vehicleNo, int days) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleNo().equals(vehicleNo) && vehicle.isAvailable()) {
                vehicle.setAvailable(false);
                rentedVehicles.put(vehicleNo, days);

                System.out.println("Vehicle " + vehicleNo +
                        " rented for " + days +
                        " days. Total Cost: ₹" + vehicle.getRentCost(days));
                return;
            }
        }
        System.out.println("Vehicle not available or not found.");
    }

    public void returnVehicle(String vehicleNo) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleNo().equals(vehicleNo) && !vehicle.isAvailable()) {
                vehicle.setAvailable(true);
                rentedVehicles.remove(vehicleNo);

                System.out.println("Vehicle " + vehicleNo + " returned successfully.");
                return;
            }
        }
        System.out.println("Vehicle not found or already returned.");
    }

    public void calculateTotalEarnings() {
        double totalEarnings = 0;

        for (Map.Entry<String, Integer> entry : rentedVehicles.entrySet()) {
            for (Vehicle vehicle : inventory) {
                if (vehicle.getVehicleNo().equals(entry.getKey())) {
                    totalEarnings += vehicle.getRentCost(entry.getValue());
                }
            }
        }

        System.out.println("Total Earnings: ₹" + totalEarnings);
    }
}

public class SmartVehicleRentalMain {
    public static void main(String[] args) {

        SmartVehicleRentalSystem rental = new SmartVehicleRentalSystem();

        rental.addVehicle(new Bike("B101", 500));
        rental.addVehicle(new Car("C202", 2000));

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== SMART VEHICLE RENTAL SYSTEM =====");
            System.out.println("1. Display Available Vehicles");
            System.out.println("2. Rent a Vehicle");
            System.out.println("3. Return a Vehicle");
            System.out.println("4. Calculate Total Earnings");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1 -> rental.displayAvailableVehicles();

                case 2 -> {
                    System.out.print("Enter Vehicle No to Rent: ");
                    String vehicleNo = scanner.next();

                    System.out.print("Enter Number of Days: ");
                    int days = scanner.nextInt();

                    if (days > 0) {
                        rental.rentVehicle(vehicleNo, days);
                    } else {
                        System.out.println("Invalid number of days.");
                    }
                }

                case 3 -> {
                    System.out.print("Enter Vehicle No to Return: ");
                    String vehicleNo = scanner.next();

                    rental.returnVehicle(vehicleNo);
                }

                case 4 -> rental.calculateTotalEarnings();

                case 5 -> {
                    System.out.println("Thank you for using Smart Vehicle Rental System.");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
```

Based on your uploaded file 
