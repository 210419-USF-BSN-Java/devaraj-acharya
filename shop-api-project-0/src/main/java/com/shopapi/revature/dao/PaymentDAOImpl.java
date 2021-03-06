package com.shopapi.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shopapi.revature.model.AccountCollection;
import com.shopapi.revature.model.Customer;
import com.shopapi.revature.model.Product;
import com.shopapi.revature.model.Sales;
import com.shopapi.revature.model.WeeklyCollection;
import com.shopapi.revature.utility.ConnectionUtility;

public class PaymentDAOImpl implements PaymentDAO {

	public static Logger log = LogManager.getLogger(UserDAOImpl.class);
	PreparedStatement ps = null;
	Statement stmt = null;
	ResultSet rs = null;

	@Override
	public List<AccountCollection> viewAllPayment() {
		log.info("view all payments invoked");
		List<AccountCollection> accountCollection = new ArrayList<>();
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			String query = "select * from shopapi.account_collection "
					+ "join shopapi.sales on shopapi.sales.order_no = shopapi.account_collection.product_order_no "
					+ "join shopapi.customers on shopapi.customers.customer_id = shopapi.sales.customer;";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				AccountCollection accountColl = new AccountCollection();
				accountColl.setCollection_id(rs.getInt("collection_id"));
				accountColl.setSales_order_no(new Sales(rs.getInt("order_no"),
						new Customer(rs.getString("customer_fname"), rs.getString("customer_lname"))));
				accountColl.setTotal_price(rs.getDouble("total_price"));
				accountColl.setPayment_made(rs.getDouble("payment_made"));
				accountColl.setRemaining_balance(rs.getDouble("remaining_payment"));
				accountColl.setPayment_date(rs.getDate("payment_date"));
				accountCollection.add(accountColl);
			}

		} catch (SQLException e) {
			log.debug("view all payments invoked");
			e.printStackTrace();
			return null;
		}
		log.info("view all payments completed");
		return accountCollection;
	}

	@Override
	public List<AccountCollection> viewAllPaymentForCustomer(AccountCollection customer) {
		log.info("view all paymnent status invoked");
		List<AccountCollection> paymentList = new ArrayList<>();
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			String query = "select * from shopapi.account_collection "
					+ "join shopapi.sales on shopapi.sales.order_no = shopapi.account_collection.product_order_no "
					+ "join shopapi.product on shopapi.product.product_id = shopapi.sales.product "
					+ "where shopapi.sales.customer = ?;";
			ps = conn.prepareStatement(query);
			ps.setInt(1, customer.getSales_order_no().getCustomer().getCustomer_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				AccountCollection payment = new AccountCollection();
				payment.setCollection_id(rs.getInt("collection_id"));
				payment.setSales_order_no(
						new Sales(rs.getInt("order_no"), null, new Product(rs.getString("product_name"))));
				payment.setTotal_price(rs.getDouble("total_price"));
				payment.setPayment_made(rs.getDouble("payment_made"));
				payment.setPayment_date(rs.getDate("payment_date"));
				payment.setRemaining_balance(rs.getDouble("remaining_payment"));
				paymentList.add(payment);
			}
		} catch (SQLException e) {
			log.debug("view all paymnent status failed");
			e.printStackTrace();
			return null;
		}
		log.info("view all paymnent status completed");
		return paymentList;
	}

	@Override
	public AccountCollection viewRemainingPayment(int order_no) {
		log.info("view remaining payment by order no invoked");
		AccountCollection payment = new AccountCollection();
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			String query = "select * from shopapi.account_collection where product_order_no = ? order by remaining_payment asc limit 1;";
			ps = conn.prepareStatement(query);
			ps.setInt(1, order_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				payment.setCollection_id(rs.getInt("collection_id"));
				payment.setSales_order_no(new Sales(rs.getInt("product_order_no"), null));
				payment.setTotal_price(rs.getDouble("total_price"));
				payment.setPayment_made(rs.getDouble("payment_made"));
				payment.setPayment_date(rs.getDate("payment_date"));
				payment.setRemaining_balance(rs.getDouble("remaining_payment"));
			}
		} catch (SQLException e) {
			log.debug("view remaining payment by order no failed");
			e.printStackTrace();
			return null;
		}
		log.info("view remaining payment by order no completed");
		return payment;
	}

	@Override
	public boolean makePayment(int order_no, double amount) {
		log.info("make payment method invoked");
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			String query = "insert into shopapi.account_collection (product_order_no, offered_price_per_unit, total_price, payment_made, remaining_payment, payment_date) "
					+ "select product_order_no, offered_price_per_unit, total_price, ?, (remaining_payment-?), payment_date from shopapi.account_collection "
					+ "where product_order_no = ? order by remaining_payment asc limit 1;";
			ps = conn.prepareStatement(query);
			ps.setDouble(1, amount);
			ps.setDouble(2, amount);
			ps.setInt(3, order_no);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.debug("make payment method failed");
			e.printStackTrace();
			return false;
		}
		log.info("make payment method completed");
		return true;
	}

	@Override
	public List<WeeklyCollection> getWeeklyCollection() {
		log.info("get weekly collection invoked");
		List<WeeklyCollection> weeklyCollections = new ArrayList<>();
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			String query = "SELECT date_trunc('week', payment_date) as week_of, date_part('week', payment_date) as week_number, sum(payment_made ) as weekly_collection "
					+ "FROM   shopapi.account_collection "
					+ "group by date_trunc('week', payment_date), date_part('week', payment_date);";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				WeeklyCollection weeklyCollection = new WeeklyCollection();
				weeklyCollection.setWeek_Start_Date(rs.getDate("week_of"));
				weeklyCollection.setWeek_number(rs.getInt("week_number"));
				weeklyCollection.setWeekly_collection(rs.getDouble("weekly_collection"));
				weeklyCollections.add(weeklyCollection);
			}

		} catch (SQLException e) {
			log.debug("get weekly collection failed");
			e.printStackTrace();
			return null;
		}
		log.info("get weekly collection completed");
		return weeklyCollections;
	}

}
