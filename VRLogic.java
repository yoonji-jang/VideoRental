import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VRLogic {
	private IUserInterface ui = null;

	private List<Customer> customers = new ArrayList<Customer>() ;

	private List<Video> videos = new ArrayList<Video>() ;

	public VRLogic(IUserInterface ui) {
		this.ui = ui;
	}
	public static void main(String[] args) {
		VRLogic logic = new VRLogic(new ConsoleUI()) ;

		boolean quit = false ;
		while ( ! quit ) {
			int command = logic.showCommand() ;
			switch ( command ) {
				case 0: quit = true ; break ;
				case 1: logic.listCustomers() ; break ;
				case 2: logic.listVideos() ; break ;
				case 3: logic.registerCustomer() ; break ;
				case 4: logic.registerVideo() ; break ;
				case 5: logic.rentVideo() ; break ;
				case 6: logic.returnVideo() ; break ;
				case 7: logic.getCustomerReport() ; break;
				case 8: logic.clearRentals() ; break ;
				case -1: logic.init() ; break ;
				default: break ;
			}
		}
		System.out.println("Bye");
	}

	public void clearRentals() {
		String customerName = ui.getCustomerName();

		Customer foundCustomer = findCustomer(customerName);

		if ( foundCustomer == null ) {
			System.out.println("No customer found") ;
		} else {
			ui.printRentals(foundCustomer);

			foundCustomer.clearRentals();
		}
	}

	public void returnVideo() {
		String customerName = ui.getCustomerName();

		Customer foundCustomer = findCustomer(customerName);
		if ( foundCustomer == null ) return ;

		String videoTitle = ui.getVideoTitle();

		foundCustomer.returnVideo(videoTitle);
	}

	private void init() {
		Customer james = new Customer("James") ;
		Customer brown = new Customer("Brown") ;
		customers.add(james) ;
		customers.add(brown) ;

		Video v1 = new Video("v1", Video.CD, Video.REGULAR, new Date()) ;
		Video v2 = new Video("v2", Video.DVD, Video.NEW_RELEASE, new Date()) ;
		videos.add(v1) ;
		videos.add(v2) ;

		Rental r1 = new Rental(v1) ;
		Rental r2 = new Rental(v2) ;

		james.addRental(r1) ;
		james.addRental(r2) ;
	}

	public void listVideos() {
		ui.printVideos(videos);
	}

	public void listCustomers() {
		ui.printCustomer(customers);
	}

	public void getCustomerReport() {
		String customerName = ui.getCustomerName();

		Customer foundCustomer = findCustomer(customerName);

		if ( foundCustomer == null ) {
			System.out.println("No customer found") ;
		} else {
			String result = foundCustomer.getReport() ;
			System.out.println(result);
		}
	}

	public void rentVideo() {
		String customerName = ui.getCustomerName();

		Customer foundCustomer = findCustomer(customerName);

		if ( foundCustomer == null ) return ;

		String videoTitle = ui.getVideoTitleToRent();

		Video foundVideo = findVideo(videoTitle);

		if ( foundVideo == null ) return ;

		Rental rental = new Rental(foundVideo) ;
		foundVideo.setRented(true);

		foundCustomer.addRental(rental);
	}

	private Video findVideo(String videoTitle) {
		Video foundVideo = null ;
		for ( Video video: videos ) {
			if ( video.getTitle().equals(videoTitle) && video.isRented() == false ) {
				foundVideo = video ;
				break ;
			}
		}
		return foundVideo;
	}

	private Customer findCustomer(String customerName) {
		Customer foundCustomer = null ;
		for ( Customer customer: customers ) {
			if ( customer.getName().equals(customerName)) {
				foundCustomer = customer ;
				break ;
			}
		}
		return foundCustomer;
	}

	public void registerCustomer() {
		String name = ui.getCustomerName();
		Customer customer = new Customer(name) ;
		customers.add(customer) ;
	}

	public void registerVideo() {
		String title = ui.getTitle();
		int videoType = ui.getVideoType();
		int priceCode = ui.getPriceCode();

		Date registeredDate = new Date();
		Video video = new Video(title, videoType, priceCode, registeredDate) ;
		videos.add(video) ;
	}

	public int showCommand() {
		return ui.showCommand();
	}
}

