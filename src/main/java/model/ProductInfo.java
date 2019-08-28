package model;
import entity.Product;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ProductInfo {

	private String code;
	private String name;
	private double price;
	
	private boolean newProduct = false;
	
	//upload file
	private CommonsMultipartFile fileData;
	
	//Constructors
	public ProductInfo(){}
	
	public ProductInfo(Product product) {
		this.code = product.getCode();
		this.name = product.getName();
		this.price = product.getPrice();
	}
	
	public ProductInfo(String code, String name, double price) {
		this.code =code;
		this.name=name;
		this.price = price;
	}
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name =name;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	public boolean isNewProduct() {
		return newProduct;
	}
	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}
}
