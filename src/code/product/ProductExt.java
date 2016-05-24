package code.product;

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

	public double restockProductExt() {
		super.restock();
		return this.buyingPrice * super.getNumRestocks();
	}

	/**
	 * array in form (ID, Name, material, size, weight, stock, restock, price, buying price)
	 * return null if input element more than it have
	 *
	 * @param element
	 * 		element of array
	 * @return array with element element
	 */
	public Object[] getProductInfo(int element) {
		Object[] all = new Object[]{getProductID(), getName(), getMaterial(), getSize(), Double.valueOf(getWeight()), Integer.valueOf(getCurrNumStock()), Integer.valueOf(getNumRestocks()), Double.valueOf(getPrice()), Double.valueOf(getBuyingPrice())};
		if (element <= all.length) {
			Object[] temp = new Object[element];
			// copy all into temp with element
			System.arraycopy(all, 0, temp, 0, element);
			return temp;
		}
		return null;
	}

	/**
	 * toString only <i>name</i> and <i>material</i>
	 *
	 * @return String in format of toString
	 */
	public String toStringInformation1() {
		String format = "Name: %s, Material: %s";
		return String.format(format, super.getName(), this.material);
	}

	/**
	 * toString only <i>size</i> and <i>weight</i>
	 *
	 * @return String in format of toString
	 */
	public String toStringInformation2() {
		String format = "Size: %s, Weight: %.1f";
		return String.format(format, this.size, super.getWeight());
	}
	
	public String toString() {
		String format = "%s. %s, %.1f, %s, %s, %.0f, %d";
		return String.format(format, super.getProductID(), super.getName(), super.getWeight(), this.material, this.size, super.getPrice(), super.getCurrNumStock());
	}
}
