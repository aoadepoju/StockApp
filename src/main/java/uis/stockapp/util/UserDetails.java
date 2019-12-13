package uis.stockapp.util;

import java.time.LocalDateTime;

import uis.stockapp.model.User;

public class UserDetails {
	public static User currentUser;
	public static boolean logout= false;
	public static LocalDateTime refreshTime;
}
