package net.codej;

import net.codej.model.User;

/*import com.example.demo.Booking.Booking;*/
/*import com.example.demo.User.User;*/

public class Transfer {

	private static int id;
	private static Booking booking;
	private static double price;
	private static User user;
	

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Transfer.user = user;
	}

	public static double getPrice() {
		return price;
	}

	public static void setPrice(double price) {
		Transfer.price = price;
	}

	public static Booking getBooking() {
		return booking;
	}

	public static void setBooking(Booking booking) {
		Transfer.booking = booking;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Transfer.id = id;
	}
	
}
