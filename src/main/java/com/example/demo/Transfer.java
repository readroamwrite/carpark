package com.example.demo;

import net.codej.Booking;
import net.codej.TUser;

public class Transfer {

	private static int id;
	private static Booking booking;
	private static double price;
	private static TUser user;
	

	public static TUser getUser() {
		return user;
	}

	public static void setUser(TUser user) {
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
