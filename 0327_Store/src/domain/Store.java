package domain;

public class Store {
	private String barcode ;
	private String productname ;
	private int price ;
	private int amount ;
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "Store [barcode=" + barcode + ", productname=" + productname + ", price=" + price + ", amount=" + amount
				+ "]";
	}
	
	

}
