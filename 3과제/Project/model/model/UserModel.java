package model;

import java.util.Vector;

public class UserModel {

	public static Vector<String> userData;

	public static void login(Vector<String> userData) {
		// TODO Auto-generated method stub
		UserModel.userData = userData;

	}

	public static void logout() {
		// TODO Auto-generated method stub
		UserModel.userData = null;

	}

}
