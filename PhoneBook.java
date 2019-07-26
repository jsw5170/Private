package PhoneBook;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {

	public static void main(String[] args) {
		List<Data> db = new ArrayList<Data>();
		while(true) {
			int mn = Menu.menu(db);
			
			if(mn == 4 )break;
		}
	}

}
