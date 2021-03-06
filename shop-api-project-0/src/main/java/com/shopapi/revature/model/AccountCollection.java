package com.shopapi.revature.model;

import java.util.Date;

public class AccountCollection {

	private int collection_id;
	private Sales sales_order_no;
	private double offered_price_per_unit;
	private double total_price;
	private double payment_made;
	private double remaining_balance;
	private Date payment_date;

	public AccountCollection() {
	}

	public AccountCollection(int collection_id, Sales sales_order_no, double offered_price_per_unit,
			double total_price, double payment_made, double remaining_balance, Date payment_date) {
		super();
		this.collection_id = collection_id;
		this.sales_order_no = sales_order_no;
		this.offered_price_per_unit = offered_price_per_unit;
		this.total_price = total_price;
		this.payment_made = payment_made;
		this.remaining_balance = remaining_balance;
		this.payment_date = payment_date;
	}

	public int getCollection_id() {
		return collection_id;
	}

	public void setCollection_id(int collection_id) {
		this.collection_id = collection_id;
	}

	public Sales getSales_order_no() {
		return sales_order_no;
	}

	public void setSales_order_no(Sales sales_order_no) {
		this.sales_order_no = sales_order_no;
	}

	public double getOffered_price_per_unit() {
		return offered_price_per_unit;
	}

	public void setOffered_price_per_unit(double offered_price_per_unit) {
		this.offered_price_per_unit = offered_price_per_unit;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public double getPayment_made() {
		return payment_made;
	}

	public void setPayment_made(double payment_made) {
		this.payment_made = payment_made;
	}

	public double getRemaining_balance() {
		return remaining_balance;
	}

	public void setRemaining_balance(double remaining_balance) {
		this.remaining_balance = remaining_balance;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + collection_id;
		long temp;
		temp = Double.doubleToLongBits(offered_price_per_unit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((sales_order_no == null) ? 0 : sales_order_no.hashCode());
		result = prime * result + ((payment_date == null) ? 0 : payment_date.hashCode());
		temp = Double.doubleToLongBits(payment_made);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(remaining_balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(total_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountCollection other = (AccountCollection) obj;
		if (collection_id != other.collection_id)
			return false;
		if (Double.doubleToLongBits(offered_price_per_unit) != Double.doubleToLongBits(other.offered_price_per_unit))
			return false;
		if (sales_order_no == null) {
			if (other.sales_order_no != null)
				return false;
		} else if (!sales_order_no.equals(other.sales_order_no))
			return false;
		if (payment_date == null) {
			if (other.payment_date != null)
				return false;
		} else if (!payment_date.equals(other.payment_date))
			return false;
		if (Double.doubleToLongBits(payment_made) != Double.doubleToLongBits(other.payment_made))
			return false;
		if (Double.doubleToLongBits(remaining_balance) != Double.doubleToLongBits(other.remaining_balance))
			return false;
		if (Double.doubleToLongBits(total_price) != Double.doubleToLongBits(other.total_price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccountCollection [collection_id=" + collection_id + ", sales_order_no=" + sales_order_no
				+ ", offered_price_per_unit=" + offered_price_per_unit + ", total_price=" + total_price
				+ ", payment_made=" + payment_made + ", remaining_balance=" + remaining_balance + ", payment_date="
				+ payment_date + "]";
	}
}