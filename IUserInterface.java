import java.util.List;

public interface IUserInterface {
    int showCommand();

    String getTitle();

    int getVideoType();

    int getPriceCode();

    String getCustomerName();

    String getVideoTitle();

    String getVideoTitleToRent();

    void printRentals(Customer customer);

    void printCustomer(List<Customer> customerList);

    void printVideos(List<Video> videoList);
}
