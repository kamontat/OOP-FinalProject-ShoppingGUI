package code.store;

import code.product.ProductExt;

public class OrderElement {
	private String code;
	private ProductExt product;
	private int num;

	public OrderElement(ProductExt product, int num) {
		for (int i = 0; i < 12; i++) {
			code += (char) (97 + Math.floor(Math.random() * 25));
		}
		this.product = product;
		this.num = num;
	}

	private OrderElement(ProductExt product, int num, String code) {
		this.code = code;
		this.product = product;
		this.num = num;
	}

	public ProductExt getProduct() {
		return product;
	}

	public int getNum() {
		return num;
	}

	public void setProduct(ProductExt product) {
		this.product = product;
	}

	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * Warning: code is some String that defined orderElement is same or not.
	 *
	 * @param code
	 * 		some String with 12 char
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return product.getName();
	}

	public double getPrice() {
		return product.getPrice() * num;
	}

	public String getCode() {
		return code;
	}

	public Object[] getProductInfoExtra() {
		int productLength = product.getProductInfoExtra().length;
		Object[] temp = new Object[productLength + 1];
		for (int i = 0; i < productLength; i++) {
			temp[i] = product.getProductInfoExtra()[i];
		}
		temp[temp.length - 1] = num;
		return temp;
	}

	public boolean equals(OrderElement element) {
		return element.getCode().equals(code);
	}

	public OrderElement clone() {
		return new OrderElement(this.product, this.num, code);
	}

	@Override
	public String toString() {
		return "OrderElement{" + "product=" + product.toString() + " \nnum=" + num + '}';
	}
}
