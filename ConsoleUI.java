import java.util.Scanner;

public class ConsoleUI {
    static Scanner scanner = new Scanner(System.in) ;

    public ConsoleUI() {
    }

    public int showCommand() {
        System.out.println("\nSelect a command !");
        System.out.println("\t 0. Quit");
        System.out.println("\t 1. List customers");
        System.out.println("\t 2. List videos");
        System.out.println("\t 3. Register customer");
        System.out.println("\t 4. Register video");
        System.out.println("\t 5. Rent video");
        System.out.println("\t 6. Return video");
        System.out.println("\t 7. Show customer report");
        System.out.println("\t 8. Show customer and clear rentals");

        int command = scanner.nextInt();

        return command;

    }

    String getTitle() {
        System.out.println("Enter video title to register: ") ;
        String title = scanner.next() ;
        return title;
    }

    int getVideoType() {
        System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):") ;
        int videoType = scanner.nextInt();
        return videoType;
    }

    int getPriceCode() {
        System.out.println("Enter price code( 1 for Regular, 2 for New Release ):") ;
        int priceCode = scanner.nextInt();
        return priceCode;
    }

    String getCustomerName() {
        System.out.println("Enter customer name: ") ;
        String name = scanner.next();
        return name;
    }

    String getVideoTitleToRent() {
        System.out.println("Enter video title to rent: ") ;
        String videoTitle = scanner.next() ;
        return videoTitle;
    }

    void printRentals(Customer customer) {
        System.out.println("Name: " + customer.getName() +
                "\tRentals: " + customer.getRentals().size()) ;
        for ( Rental rental: customer.getRentals() ) {
            System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ") ;
            System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode()) ;
        }
    }
}