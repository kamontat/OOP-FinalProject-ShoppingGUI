package code;

public class ProductExt extends Product {
	private String material;
	private String size;
	private double buyingPrice;

	public ProductExt() {
		super();
		material = "";
		size = "";
		buyingPrice = 0;
	}
	
	public ProductExt(String name, double weight, double price, int currNumStock, int numRestocks, String material, String size, double buyingPrice) {
		super(name, weight, price, currNumStock, numRestocks);
		this.material = material;
		this.size = size;
		this.buyingPrice = buyingPrice;
	}

	public ProductExt(String name, String weight, String price, String currNumStock, String numRestocks, String material, String size, String buyingPrice) {
		super(name, weight, price, currNumStock, numRestocks);
		this.material = material;
		this.size = size;
		this.buyingPrice = Double.parseDouble(buyingPrice);
	}

	public String getSize() {
		return size;
	}

	public String getMaterial() {
		return material;
	}

	public double getBuyingPrice() {
		return buyingPrice;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	public void setMaterial(String material) {
		this.material = material;
	}


	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	
	public ProductExt clone() {
		ProductExt productExt = new ProductExt(super.getName(), super.getWeight(), super.getPrice(), super.getCurrNumStock(), super.getNumRestocks(), this.material, this.size, this.buyingPrice);
		return productExt;
	}
	
	public double restockProductExt() {
		super.restock();
		return this.buyingPrice * super.getNumRestocks();
	}
	
	public boolean equals(String name, String color, String size) {
		if (super.getName().equals(name) && this.material.equals(color) && this.size.equals(size)) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String format = "%s. %s, %.1f, %s, %s, %.0f, %d";
		return String.format(format, super.getProductID(), super.getName(), super.getWeight(), this.material, this.size, super.getPrice(), super.getCurrNumStock());
	}
}
