import java.util.Date;

public class Rental {
	private Video video ;
	private int status ; // 0 for Rented, 1 for Returned
	private Date rentDate ;
	private Date returnDate ;

	public static final int RENTED = 0 ;
	public static final int RETURNED = 1 ;

	public Rental(Video video) {
		this.video = video ;
		status = RENTED ;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public int getStatus() {
		return status;
	}

	public void returnVideo() {
		if ( status == RENTED ) {
			this.status = RETURNED;
			returnDate = new Date() ;
		}
	}
	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getDaysRentedLimit() {
		int limit = 0 ;
		int daysRented ;
		daysRented = getDaysRented();
		if ( daysRented <= 2) return limit ;

		limit = video.getVideoLimit();
		return limit ;
	}



	public int getDaysRented() {
		int daysRented;
		long diff = 0;
		if (getStatus() == RETURNED) { // returned Video
			diff = returnDate.getTime() - rentDate.getTime();
		} else { // not yet returned
			diff = new Date().getTime() - rentDate.getTime();
		}
		daysRented = calcDaysRented(diff);
		return daysRented;
	}

	private int calcDaysRented(long diff) {
		return (int) (diff / (1000 * 60 * 60 * 24)) + 1;
	}

	public double getCharge() {
		double charge = 0;
		switch (getVideo().getPriceCode()) {
			case Video.REGULAR:
				charge += 2;
				if (getDaysRented() > 2)
					charge += (getDaysRented() - 2) * 1.5;
				break;
			case Video.NEW_RELEASE:
				charge = getDaysRented() * 3;
				break;
		}
		return charge;
	}

	public int getPoint(Rental each) {
		int point = 1;

		if ((getVideo().getPriceCode() == Video.NEW_RELEASE) )
			point++;

		if ( getDaysRented() > getDaysRentedLimit() )
			point -= Math.min(point, getVideo().getLateReturnPointPenalty()) ;
		return point;
	}

	public String getReport() {
		String result = "\t" + getVideo().getTitle() + "\tDays rented: " + getDaysRented() + "\tCharge: " + getChange()
				+ "\tPoint: " + getPoint() + "\n";
		return result;
	}
}
