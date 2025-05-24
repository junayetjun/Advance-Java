
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            //showAllCustomer(jt);
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
    
    
    
    
    
}
