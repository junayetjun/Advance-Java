
package dao;

import entity.Category;
import java.sql.PreparedStatement;
import java.util.List;
import javax.swing.JComboBox;
import pos.utill.DbUtill;


public class PurchaseDao {
    
    DbUtill db = new DbUtill();
    PreparedStatement ps;
    String sql;
    
    CategoryDao categoryDao = new CategoryDao();
    
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
