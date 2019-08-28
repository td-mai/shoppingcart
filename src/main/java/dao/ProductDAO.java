package dao;
import entity.Product;
import model.ProductInfo;

public interface ProductDAO {

	public Product findProduct(String code);
	public ProductInfo findproductInfo(String code);
	
	public void save(ProductInfo productInfo);
}
