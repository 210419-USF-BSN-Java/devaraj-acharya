package com.shopapi.revature.service;

import java.util.HashMap;
import java.util.List;

import com.shopapi.revature.dao.OfferedMadeDAOImpl;
import com.shopapi.revature.dao.PaymentDAOImpl;
import com.shopapi.revature.dao.ProductDAOImpl;
import com.shopapi.revature.model.AccountCollection;
import com.shopapi.revature.model.OfferedMade;
import com.shopapi.revature.model.Product;

public class EmployeeService {

	ProductDAOImpl productDAO = new ProductDAOImpl();
	OfferedMadeDAOImpl offerMadeDAO = new OfferedMadeDAOImpl();
	PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
	HashMap<Integer, OfferedMade> receivedOffers;

	public boolean addProductToList(Product product) {
		return productDAO.add(product);
	}

	public List<Product> getAllProducts() {
		return productDAO.getAll();
	}

	public boolean deleteProduct(Product product_id) {
		return productDAO.delete(product_id);
	}

	public List<OfferedMade> getAllOfferMade() {
		List<OfferedMade> offerDetail = offerMadeDAO.getAll();
		receivedOffers = new HashMap<Integer, OfferedMade>();
		for (OfferedMade offer : offerDetail) {
			receivedOffers.put(offer.getOffer_no(), offer);
		}
		return offerDetail;
	}

	public boolean acceptOffer(OfferedMade offer) {

		boolean isOfferAccepted = false;

		int offered_quantity = 0;
		double offered_price = 0;

		Product offeredProduct = null;

		List<Product> products = productDAO.getAll();
		for (Product product : products) {
			if (product.getProduct_id() == offer.getProduct().getProduct_id()) {
				offeredProduct = product;
			}
		}

		for (OfferedMade offerDetail : receivedOffers.values()) {
			if (offerDetail.getProduct().getProduct_id() == offer.getProduct().getProduct_id()) {
				offered_quantity = offerDetail.getOffer_quantity();
				offered_price = offerDetail.getOffered_price_per_unit();
			}
		}

		if (offeredProduct.getProduct_quantity() >= offered_quantity
				&& offered_price >= offeredProduct.getexpected_price_per_unit()) {
			offerMadeDAO.acceptOffer(offer);
			int remainingQuantity = (offeredProduct.getProduct_quantity() - offered_quantity);
			offeredProduct.setProduct_quantity(remainingQuantity);
			productDAO.update(offeredProduct);
			isOfferAccepted = true;
		} else if (offeredProduct.getProduct_quantity() < offered_quantity
				&& offered_price >= offeredProduct.getexpected_price_per_unit()) {
			// TODO
		}
		return isOfferAccepted;
	}

	public List<AccountCollection> viewAllPaymentMade() {
		return paymentDAO.viewAllPayment();
	}

}