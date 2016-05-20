package code.product;

public class Product {
	private String productID;
	private String name;
	private double price;
	private double weight;
	private int currNumStock;
	private boolean restock;
	private int numRestocks;
	private static int numProducts = 1000;

	public Product() {
		numProducts++;
		productID = Integer.toString(numProducts);
		name = "";
		price = 0;
		weight = 0;
		currNumStock = 0;
		numRestocks = 0;
		restock = false;
	}
	
	public Product(String name, double weight, double price, int currNumStock, int numRestocks) {
		numProducts++;
		productID = Integer.toString(numProducts);
		this.name = name;
		this.weight = weight;
		this.price = price;
		this.currNumStock = currNumStock;
		this.numRestocks = numRestocks;
		restock = false;
	}

	public Product(String name, String weight, String price, String currNumStock, String numRestocks) {
		numProducts++;
		productID = Integer.toString(numProducts);
		this.name = name;
		this.weight = Double.parseDouble(weight);
		this.price = Double.parseDouble(price);
		this.currNumStock = Integer.parseInt(currNumStock);
		this.numRestocks = Integer.parseInt(numRestocks);
		restock = false;
	}
	
	public String getProductID() {
		return productID;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public double getWeight() {
		return weight;
	}

	public int getCurrNumStock() {
		return currNumStock;
	}

	public boolean isRestock() {
		return restock;
	}

	public static int getNumProducts() {
		return numProducts;
	}

	public int getNumRestocks() {
		return numRestocks;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setCurrNumStock(int currNumStock) {
		this.currNumStock = currNumStock;
	}

	public void setRestock(boolean restock) {
		this.restock = restock;
	}

	public static void setNumProducts(int numProducts) {
		Product.numProducts = numProducts;
	}

	public void setNumRestocks(int numRestocks) {
		this.numRestocks = numRestocks;
	}

	public void withdrawStock(int num) {
		currNumStock -= num;
	}
	
	public void returnStock(int num) {
		currNumStock += num;
	}
	
	public void restock() {
		if (restock) {
			currNumStock += numRestocks;
			restock = false;
		}
	}

	public String toString() {
		String format = "%s. %s, %.3f, %.2f, %d, %d";
		return String.format(format, this.productID, this.name, this.weight, this.price, this.currNumStock, this.numRestocks);
	}
}
