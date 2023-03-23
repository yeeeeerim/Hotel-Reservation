package org.hotel.back.config.exception;

public class HotelNotFoundException extends RuntimeException {
	public HotelNotFoundException(String message) {
		super(message);
	}
}