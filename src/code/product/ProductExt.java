package code.product;

import code.constant.ProductType;

public class ProductExt extends Product {
	private ProductType type;
	private String material;
	private String size;
	private double buyingPrice;

	public ProductExt() {
		super();
		type = null;
		material = "";
		size = "";
		buyingPrice = 0;
	}
	
	public ProductExt(String name, ProductType type, String material, String size, double weight, int currNumStock, int numRestocks, double price, double buyingPrice) {
		super(name, weight, price, currNumStock, numRestocks);
		this.type = type;
		this.material = material;
		this.size = size;
		this.buyingPrice = buyingPrice;
	}

	public ProductExt(String name, String type, String material, String size, String weight, String currNumStock, String numRestocks, String price, String buyingPrice) {
		super(name, weight, price, currNumStock, numRestocks);

		for (ProductType productType : ProductType.values()) {
			if (productType.getName().equalsIgnoreCase(type)) {
				this.type = productType;
			}
		}

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

	public ProductType getType() {
		return type;
	}

	public String getTypeToString() {
		return type.getName();
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

	public void setType(ProductType type) {
		this.type = type;
	}

	public double restockProductExt() {
		super.restock();
		return this.buyingPrice * super.getNumRestocks();
	}

	/**
	 * array in form (ID, Name, type, material, size, weight, stock, restock, price, buying price)
	 * return null if input element more than it have
	 *
	 * @param element
	 * 		element of array include id everytime
	 * @param id
	 * 		if true will return id too; otherwise, return without id
	 * @return array with element element
	 */
	public Object[] getProductInfo(int element, boolean id) {
		Object[] all = null;

		if (id)
			all = new Object[]{getProductID(), getName(), getTypeToString(), getMaterial(), getSize(), Double.valueOf(getWeight()), Integer.valueOf(getCurrNumStock()), Integer.valueOf(getNumRestocks()), Double.valueOf(getPrice()), Double.valueOf(getBuyingPrice())};
		else {
			all = new Object[]{getName(), getTypeToString(), getMaterial(), getSize(), Double.valueOf(getWeight()), Integer.valueOf(getCurrNumStock()), Integer.valueOf(getNumRestocks()), Double.valueOf(getPrice()), Double.valueOf(getBuyingPrice())};
			element--;
		}

		if (element <= all.length) {
			Object[] temp = new Object[element];
			// copy all into temp with element
			System.arraycopy(all, 0, temp, 0, element);
			return temp;
		}


		return null;
	}

	/**
	 * Extra use to print in basket only
	 *
	 * @return product information in form of array
	 */
	public Object[] getProductInfoExtra() {
		return new Object[]{getProductID(), getName(), getTypeToString(), getMaterial(), getSize(), Double.valueOf(getWeight()), Double.valueOf(getPrice())};
	}

	/**
	 * toString only <i>name</i> and <i>material</i>
	 *
	 * @return String in format of toString
	 */
	public String toStringInformation1() {
		String format = "Name: %s, Type: %s, Material: %s";
		return String.format(format, super.getName(), this.type.getName(), this.material);
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
		String format = "%s. %s, %s, %.1f, %s, %s, %.0f, %d";
		return String.format(format, super.getProductID(), super.getName(), getType(), super.getWeight(), this.material, this.size, super.getPrice(), super.getCurrNumStock());
	}
}
