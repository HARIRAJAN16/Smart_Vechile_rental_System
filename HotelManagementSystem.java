import java.util.*;

abstract class Room {
    private boolean available;
    private String roomno;
    private String roomType;

    public Room(String roomno, String roomType) {
        this.roomno = roomno;
        this.roomType = roomType;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getRoomno() {
        return roomno;
    }

    public String getRoomType() {
        return roomType;
    }

    public abstract double getDailyRate();

    public abstract double getRentCost(int days);
}

class SingleBed extends Room {
    private double dailyRate;

    public SingleBed(String roomno, double dailyRate) {
        super(roomno, "Single Bed");
        this.dailyRate = dailyRate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public double getRentCost(int days) {
        return days * dailyRate;
    }
}

class DoubleBed extends Room {
    private double dailyRate;

    public DoubleBed(String roomno, double dailyRate) {
        super(roomno, "Double Bed");
        this.dailyRate = dailyRate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public double getRentCost(int days) {
        return days * dailyRate;
    }
}

class RoomRentalSystem {
    private List<Room> inventory;
    private Map<String, Integer> rentedRooms;

    public RoomRentalSystem() {
        this.inventory = new ArrayList<>();
        this.rentedRooms = new HashMap<>();
    }

    public void addRoom(Room room) {
        inventory.add(room);
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : inventory) {
            if (room.isAvailable()) {
                System.out.println("RoomNo: " + room.getRoomno() + ", Type: " + room.getRoomType() + ", Daily Rate: " + room.getDailyRate());
            }
        }
    }

    public void rentRoom(String roomno, int days) {
        for (Room room : inventory) {
            if (room.getRoomno().equals(roomno) && room.isAvailable()) {
                room.setAvailable(false);
                rentedRooms.put(roomno, days);
                System.out.println("Room: " + roomno + " rented for " + days + " days. Total Cost: " + room.getRentCost(days));
                return;
            }
        }
        System.out.println("Room not available or not found.");
    }

    public void vacateRoom(String roomno) {
        for (Room room : inventory) {
            if (room.getRoomno().equals(roomno) && !room.isAvailable()) {
                room.setAvailable(true);
                rentedRooms.remove(roomno);
                System.out.println("Room: " + roomno + " vacated.");
                return;
            }
        }
        System.out.println("Room not found or already vacated.");
    }

    public void calculateTotalEarnings() {
        double totalEarnings = 0;
        for (Map.Entry<String, Integer> entry : rentedRooms.entrySet()) {
            for (Room room : inventory) {
                if (room.getRoomno().equals(entry.getKey())) {
                    totalEarnings += room.getRentCost(entry.getValue());
                }
            }
        }
        System.out.println("Total Earnings: $" + totalEarnings);
    }
}

public class HotelManagementSystem {
    public static void main(String[] args) {
        RoomRentalSystem rental = new RoomRentalSystem();
        rental.addRoom(new SingleBed("S007", 500));
        rental.addRoom(new DoubleBed("D006", 700));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Room Rental System Options:");
            System.out.println("1. Display Available Rooms");
            System.out.println("2. Rent a Room");
            System.out.println("3. Vacate a Room");
            System.out.println("4. Calculate Total Earnings");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> rental.displayAvailableRooms();
                case 2 -> {
                    System.out.print("Enter Room No to Rent: ");
                    String roomno = scanner.next();
                    System.out.print("Enter Number of Days: ");
                    int days = scanner.nextInt();
                    if (days > 0) {
                        rental.rentRoom(roomno, days);
                    } else {
                        System.out.println("Invalid number of days. Must be greater than 0.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter Room No to Vacate: ");
                    String roomno = scanner.next();
                    rental.vacateRoom(roomno);
                }
                case 4 -> rental.calculateTotalEarnings();
                case 5 -> {
                    System.out.println("Thank you for using the Room Rental System.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
