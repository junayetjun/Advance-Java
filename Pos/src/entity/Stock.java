
package entity;


public class Stock {
    
   private int id;
   private String productName;
   private float quantity;
   private String category;

    public Stock() {
    }

    public Stock(int id, String productName, float quantity, String category) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", productName=" + productName + ", quantity=" + quantity + ", category=" + category + '}';
    }
    
   
    
}
