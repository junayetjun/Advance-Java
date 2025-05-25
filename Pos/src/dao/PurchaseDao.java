
package dao;

import entity.Category;
import entity.Stock;
import java.sql.PreparedStatement;
import java.util.List;
import javax.swing.JComboBox;
import pos.utill.DbUtill;


public class PurchaseDao {
    
    DbUtill db = new DbUtill();
    PreparedStatement ps;
    String sql;
    
    CategoryDao categoryDao = new CategoryDao();
    StockDao stockDao = new StockDao();
    
    
    public void loadProduct(JComboBox<String> comboProductList, String category){
            comboProductList.removeAllItems();
            
            List<Stock> stockList = stockDao.getProductByCategory(category);
    
            if(stockList.isEmpty()){
                System.out.println("No Product found!");
                return;
            }
            
            for(Stock s: stockList){
                
                comboProductList.addItem(s.getProductName());
               
            }
            
    }
    
    
    
    public void loadCategory(JComboBox<String> comboCategoryList){

        comboCategoryList.removeAllItems();
        List<Category> catList = categoryDao.getAllCategory();
    
        if(catList.isEmpty()){
            
            System.out.println("No category found!");
            return;
        }
        for(Category cat: catList){
            comboCategoryList.addItem(cat.getName());
        
        }
        
        
    
    }
    
    
    
    
    
}
