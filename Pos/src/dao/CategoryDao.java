
package dao;

import entity.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pos.utill.DbUtill;


public class CategoryDao {
    
    
     DbUtill db = new DbUtill();

    PreparedStatement ps;

    public void saveCategory(String name, JTable jt) {

        String sql = "insert into categories(name) values (?)";
        try {
            ps = db.getCon().prepareCall(sql);
            ps.setString(1, name);
           

            ps.executeUpdate();

            ps.close();
            db.getCon().close();

            JOptionPane.showMessageDialog(null, "Category Saved Succcessfully");
            showAllCategory(jt);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Category Saved not Succcessfully");
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public void showAllCategory(JTable jt) {

        String[] columnsName = {"ID","Name"};
        DefaultTableModel tableModel = new DefaultTableModel(columnsName, 0);
        jt.setModel(tableModel);

        String sql = "select * from categories";

        PreparedStatement ps;
        try {
            ps = db.getCon().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
               

                Object[] rowData = {id, name};
                tableModel.addRow(rowData);
            }
            rs.close();
            ps.close();
            db.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
    
    public void deleteCategory(int id, JTable jt){
          
      
      String sql = "delete from categories where id=?";
        try {
            ps = db.getCon().prepareCall(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            ps.close();
            db.getCon().close();
            
            JOptionPane.showMessageDialog(null, "Category delete successfully");
            showAllCategory(jt);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Category not delete successfully");
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      }
    
    
    public void editCategory(int id ,String name, JTable jt) {

        String sql = "update categories set name=? where id=?";
        try {
            ps = db.getCon().prepareCall(sql);
            ps.setString(1, name);
            
            ps.setInt(2, id);

            ps.executeUpdate();

            ps.close();
            db.getCon().close();

            JOptionPane.showMessageDialog(null, "Category Updated Succcessfully");
            showAllCategory(jt);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Category Updated not Succcessfully");
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
    
    public List<Category> getAllCategory(){
    
        List<Category> categoryList = new ArrayList<>();
        
     String sql = "select * from categories";
     
         try {
             ps= db.getCon().prepareStatement(sql);
             
             ResultSet rs = ps.executeQuery();
             
             while(rs.next()){
                 
                 int id = rs.getInt("id");
                 String name = rs.getString("name");
                 
             categoryList.add(new Category(id, name));
             }
             
             
         } catch (SQLException ex) {
             Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
         }
     
         return categoryList;
    
    }
    
    
    
    
}
