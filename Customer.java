import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
	private String name;

	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);

	}

	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		List<Rental> rentals = getRentals();

		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental each : rentals) {
			int daysRented = each.getDaysRented();
			double eachCharge = getEachCharge(each, daysRented);
			int eachPoint = getEachPoint(each, daysRented);

			result += AddEachResult(each, eachCharge, eachPoint, daysRented);

			totalCharge += eachCharge;
			totalPoint += eachPoint;
		}

		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";

		printCouponBonus(totalPoint);
		return result ;
	}

	private void printCouponBonus(int totalPoint) {
		if ( totalPoint >= 10 ) {
			System.out.println("Congrat! You earned one free coupon");
		}
		else if ( totalPoint >= 30 ) {
			System.out.println("Congrat! You earned two free coupon");
		}
	}

	private String AddEachResult(Rental each, double eachCharge, int eachPoint, int daysRented) {
		String result = "\t" + each.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
				+ "\tPoint: " + eachPoint + "\n";
		return result;
	}

	private int getEachPoint(Rental each, int daysRented) {
		int eachPoint = 1;

		if ((each.getVideo().getPriceCode() == Video.NEW_RELEASE) )
			eachPoint++;

		if ( daysRented > each.getDaysRentedLimit() )
			eachPoint -= Math.min(eachPoint, each.getVideo().getLateReturnPointPenalty()) ;
		return eachPoint;
	}

	private double getEachCharge(Rental each, int daysRented) {
		double eachCharge = 0;
		switch (each.getVideo().getPriceCode()) {
		case Video.REGULAR:
			eachCharge += 2;
			if (daysRented > 2)
				eachCharge += (daysRented - 2) * 1.5;
			break;
		case Video.NEW_RELEASE:
			eachCharge = daysRented * 3;
			break;
		}
		return eachCharge;
	}
}
