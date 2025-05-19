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

public class CustomersDao {

    DbUtill db = new DbUtill();

    PreparedStatement ps;

    public void saveCustomer(String name, String email, String cell, String address, JTable jt) {

        String sql = "insert into customer(name, cell, email, address) values (?,?,?,?)";
        try {
            ps = db.getCon().prepareCall(sql);
            ps.setString(1, name);
            ps.setString(2, cell);
            ps.setString(3, email);
            ps.setString(4, address);

            ps.executeUpdate();

            ps.close();
            db.getCon().close();

            JOptionPane.showMessageDialog(null, "customer Saved Succcessfully");
            showAllCustomer(jt);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "customer Saved not Succcessfully");
            Logger.getLogger(CustomersDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showAllCustomer(JTable jt) {

        String[] columnsName = {"ID", "Name", "Email", "Cell", "Address"};
        DefaultTableModel tableModel = new DefaultTableModel(columnsName, 0);
        jt.setModel(tableModel);

        String sql = "select * from customer";

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

                Object[] rowData = {id, name, email, cell, address};
                tableModel.addRow(rowData);
            }
            rs.close();
            ps.close();
            db.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      

    }

      public void deleteCustomer(int id, JTable jt){
          //problm ase solve korte hobe
      
      String sql = "delete from customer where id=?";
        try {
            ps = db.getCon().prepareCall(sql);
            ps.setInt(1, id);
            
            ps.executeQuery();
            ps.close();
            db.getCon().close();
            
            JOptionPane.showMessageDialog(null, "Customer delete successfully");
            showAllCustomer(jt);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Customer not delete successfully");
            Logger.getLogger(CustomersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      }
    
}


