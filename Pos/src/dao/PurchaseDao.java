
package dao;

import entity.Category;
import entity.Stock;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import pos.utill.DbUtill;


public class PurchaseDao {
    
    DbUtill db = new DbUtill();
    PreparedStatement ps;
    String sql;
    ResultSet rs;
    
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
    
    public void savePurchase(String name, float unitPrice, float quantity,
            float totalPrice, String category, String supplier){
        
        sql = "insert into purchase(name, unitPrice, quantity, totalPrice, category, supplier, date)"
                + "values(?,?,?,?,?,?,now())";
        
        try {
            ps= db.getCon().prepareCall(sql);
            ps.setString(1, name);
            ps.setFloat(2, unitPrice);
            ps.setFloat(3, quantity);
            ps.setFloat(4, totalPrice);
            ps.setString(5, category);
            ps.setString(6, supplier);
            
            
            ps.executeUpdate();
            
            ps.close();
            db.getCon().close();
            
            JOptionPane.showMessageDialog(null, "Purchase saved Successfully.");
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Purchase not saved.");
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void purchaseReportByDate(Date from, Date to,JTable jt ){
        
        String[] columnsName = {"Product Name", "Unit Price","Quantity","Total Price", "Category","Supplier","Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columnsName, 0);
        jt.setModel(tableModel);
        
        sql ="select * from purchase where date between ? and ?";
        
        try {
            ps = db.getCon().prepareStatement(sql);
            
            ps.setDate(1, from);
            ps.setDate(2, to);
            
           rs =  ps.executeQuery();
            
            while(rs.next()){
                String productName = rs.getString("name");
                String category = rs.getString("category");
                String supplier = rs.getString("supplier");
                float unitPrice = rs.getFloat("unitPrice");
                float quantity = rs.getFloat("quantity");
                float totalPrice = rs.getFloat("totalPrice");
                Date date = rs.getDate("date");
                
                Object[] rowData = {productName, unitPrice,quantity,totalPrice
                ,category,supplier,date};
                tableModel.addRow(rowData);
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
