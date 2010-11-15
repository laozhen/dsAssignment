package zhen.ds;

public class ProductManager {

	ProductManager p = null;
	private ProductManager()
	{
	}
	
	
	public ProductManager getProductManager ()
	{
		if(p==null)
		{
			p = new ProductManager();
		}
		return p;
	}
	
	
	public void addProduct(Product p)
	{
		
	}
}
