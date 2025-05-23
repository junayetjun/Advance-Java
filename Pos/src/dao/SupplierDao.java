
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


public class SupplierDao {
    
     DbUtill db = new DbUtill();

    PreparedStatement ps;

    public void saveSupplier(String name, String email, String cell, String address,String contactPerson, String contactPPost, JTable jt) {

        String sql = "insert into suppliers(name, cell, email, address, contactPerson, contactPPost) values (?,?,?,?,?,?)";
        try {
            ps = db.getCon().prepareCall(sql);
            ps.setString(1, name);
            ps.setString(2, cell);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setString(5, contactPerson);
            ps.setString(6, contactPPost);

            ps.executeUpdate();

            ps.close();
            db.getCon().close();

            JOptionPane.showMessageDialog(null, "Supplier Saved Succcessfully");
           showAllSupplier(jt);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Supplier Saved not Succcessfully");
            Logger.getLogger(CustomersDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void showAllSupplier(JTable jt) {

        String[] columnsName = {"ID", "Name", "Email", "Cell", "Address", "ContactPerson","ContactPPost "};
        DefaultTableModel tableModel = new DefaultTableModel(columnsName, 0);
        jt.setModel(tableModel);

        String sql = "select * from suppliers ";

        PreparedStatement ps;
        try {
            ps = db.getCon().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String cell = rs.getString("cell");
                String address = rs.getString("address");
                String contactPerson = rs.getString("contactPerson");
                String contactPPost = rs.getString("contactPPost");

                Object[] rowData = {id, name, email, cell, address, contactPerson,contactPPost};
                tableModel.addRow(rowData);
            }
            rs.close();
            ps.close();
            db.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      
}
    
     public void deleteSupplier(int id, JTable jt){
          
      
      String sql = "delete from suppliers where id=?";
        try {
            ps = db.getCon().prepareCall(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            ps.close();
            db.getCon().close();
            
            JOptionPane.showMessageDialog(null, "Supplier delete successfully");
            showAllSupplier(jt);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Supplier not delete successfully");
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      }
    
    
     
      public void editSupplier(int id ,String name, String email, String cell, String address, String contactPerson, String contactPPost ,JTable jt) {

        String sql = "update suppliers set name=?, cell=?, email=?, address=?, contactPerson=?, contactPPost=? where id=?";
        try {
            ps = db.getCon().prepareCall(sql);
            ps.setString(1, name);
            ps.setString(2, cell);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setString(5, contactPerson);
            ps.setString(6, contactPPost);
            ps.setInt(7, id);

            ps.executeUpdate();

            ps.close();
            db.getCon().close();

            JOptionPane.showMessageDialog(null, "Supplier Updated Succcessfully");
            showAllSupplier(jt);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Supplier Updated not Succcessfully");
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
      
      
    
}