package model;

import java.io.Serializable;
/*
 * Shohinデータの入るJavaBeans
 */
public class Shohin implements Serializable{

	private String productId;
	private String productName;
	private String productGroup;
	private int salesPrice;
	private int unitPrice;

	public Shohin () {}
	
	public Shohin(String productId, String productName, String productGroup, int salesPrice, int unitPrice) {
		this.productId = productId;
		this.productName = productName;
		this.productGroup = productGroup;
		this.salesPrice = salesPrice;
		this.unitPrice = unitPrice;
	}

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductGroup() {
		return productGroup;
	}

	public int getSalesPrice() {
		return salesPrice;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	
}
