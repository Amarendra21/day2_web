package dao;

import pojos.Customer;

public interface BookShopDao {
   Customer validateCustomer(String email,String password)
		   throws Exception;
}
