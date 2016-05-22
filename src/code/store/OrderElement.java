package code.store;

import code.product.ProductExt;

public class OrderElement {
	private ProductExt product;
	private int num;

	public OrderElement(ProductExt product, int num) {
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
	
	public String getName() {
		return product.getName();
	}
	
	public OrderElement clone() {
		return new OrderElement(this.product, this.num);
	}

	@Override
	public String toString() {
		return "OrderElement{" + "product=" + product.toString() + " \nnum=" + num + '}';
	}
}
