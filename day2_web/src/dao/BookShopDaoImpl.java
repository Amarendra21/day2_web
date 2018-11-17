package dao;

import pojos.Customer;
//import all static members of the class
import static utils.DBUtils.*;

import java.sql.*;

public class BookShopDaoImpl implements BookShopDao {
	private Connection cn;
	private PreparedStatement pst1;

	public BookShopDaoImpl() throws Exception {
		// cn
		cn = getConnection();
		// pst1
		pst1 = cn.prepareStatement("select * from my_customers where email=? and password=?");
		System.out.println("dao inst created...");
	}

	public void cleanUp() throws Exception {
		if (pst1 != null)
			pst1.close();
		if (cn != null)
			cn.close();
		System.out.println("dao cleaned up");
	}

	@Override
	public Customer validateCustomer(String email, String password) throws Exception {
		// set IN params
		pst1.setString(1, email);
		pst1.setString(2, password);
		// exec query
		try (ResultSet rst = pst1.executeQuery()) {
			if (rst.next())
				return new Customer(rst.getInt(1), rst.getDouble(2), rst.getString(3), rst.getString(4),
						rst.getString(5), rst.getDate(6), rst.getString(7));
		}
		return null;
	}

}
