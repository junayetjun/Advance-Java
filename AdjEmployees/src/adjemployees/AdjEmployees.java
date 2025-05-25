
package adjemployees;

import Utill.DbUtill;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdjEmployees {

    
    static DbUtill du = new DbUtill();
    static PreparedStatement ps;
    static String sql = "";
    
    
    
    
    public static void main(String[] args) {
        
        
        saveEmployeesDetails("Masud", "Manager", "60,000");         
        System.out.println("---------After Save----------");
        System.out.println("-------------------------------");
        showAllEmployeesDetails();
        System.out.println("--------------------------------- \n");
        
        
        updateEmployeesDetails("Masud", "Senior Manager", "90,000", 1);        
        System.out.println("---------After Update----------");
        System.out.println("---------------------------------");
        showAllEmployeesDetails();
        System.out.println("---------------------------------- \n");
        
        
        deleteEmployeesDetails(1);
        System.out.println("---------After Delete----------");
        System.out.println("---------------------------------- \n");
        showAllEmployeesDetails();        
        System.out.println("-------------------------------------- ");
        
        
        
    }
    
    
     public static void saveEmployeesDetails(String name, String designation, String salary){
    
    sql = "insert into employees(name, designation, salary) values(?,?,?)";
        try {
            ps = du.getCon().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2,designation);
            ps.setString(3,salary);
            
            
              ps.executeUpdate();
              
              ps.close();
              du.getCon().close();
              
              System.out.println("        Data Saved ");
              System.out.println("---------------------------- \n");
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdjEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public static void showAllEmployeesDetails(){
     
     sql ="select * from employees";
        try {
            ps = du.getCon().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String designation = rs.getString("designation");
                String salary = rs.getString("salary");
               
                
                System.out.println("Id: "+id+"\n"+ "Name: "+name+"\n"
                        +"Designation: "+designation+"\n"+ "Salary: "+ salary
                +".");
                
                        }
            rs.close();
            ps.close();
            du.getCon().close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdjEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
     } 
     
     
     public static void deleteEmployeesDetails(int id){
         sql = "delete from employees where id=?";
        try {
            ps= du.getCon().prepareCall(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
            ps.close();
            du.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(AdjEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     
     
     
      public static void updateEmployeesDetails(String name, String designation, String salary, int id){
         sql = "update employees set name=?, designation=?, salary=? where id=?";
        try {
            ps= du.getCon().prepareCall(sql);
            ps = du.getCon().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2,designation);
            ps.setString(3,salary);            
            ps.setInt(4,id);
            
              ps.executeUpdate();
              
              ps.close();
              du.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(AdjEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     
     
    
    
    
    
    
}
