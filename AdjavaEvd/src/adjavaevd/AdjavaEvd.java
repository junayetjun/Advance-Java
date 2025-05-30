
package adjavaevd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utill.DbUtill;


public class AdjavaEvd {

    static DbUtill du = new DbUtill();
    static PreparedStatement ps;
    static String sql = "";
    
    
    public static void main(String[] args) {
        
        saveClinicDetails("Masud", "masud@gmail.com", "01786555277", "Sleeping Disorder");
        System.out.println("---------After Save----------");
        System.out.println("-------------------------------");
        showAllClinicDetails();
        System.out.println("--------------------------------- \n");
        
        
        updateClinicDetails("Imran", "imran@gmail.com", "01515203269", "Broken Heart", 1);
        System.out.println("---------After Update----------");
        System.out.println("---------------------------------");
        showAllClinicDetails();
        System.out.println("---------------------------------- \n");
        
        
        deleteClinicDetails(1);
        System.out.println("---------After Delete----------");
        System.out.println("---------------------------------- \n");
        showAllClinicDetails();        
        System.out.println("-------------------------------------- ");
    }
    
    
    public static void saveClinicDetails(String paitentName, String email, String contactNumber, String paitentCondition){
    
    sql = "insert into clinic(paitentName, email, contactNumber, paitentCondition) values(?,?,?,?)";
        try {
            ps = du.getCon().prepareStatement(sql);
            ps.setString(1, paitentName);
            ps.setString(2,email);
            ps.setString(3,contactNumber);
            ps.setString(4,paitentCondition);
            
              ps.executeUpdate();
              
              ps.close();
              du.getCon().close();
              
              System.out.println("Data Saved ");
              System.out.println("---------------------------- \n");
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdjavaEvd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
   
     public static void showAllClinicDetails(){
     
     sql ="select * from clinic";
        try {
            ps = du.getCon().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String paitentName = rs.getString("paitentName");
                String email = rs.getString("email");
                String contactNumber = rs.getString("contactNumber");
                String paitentCondition = rs.getString("paitentCondition");
                
                System.out.println("Id: "+id+"\n"+ "Paitent Name: "+paitentName+"\n"
                        +"Email: "+email+"\n"+ "Contact Number: "+ contactNumber+"\n"+"Patient Condition: "+ paitentCondition);
                
                        }
            rs.close();
            ps.close();
            du.getCon().close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdjavaEvd.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
     }
    
    
     public static void deleteClinicDetails(int id){
         sql = "delete from clinic where id=?";
        try {
            ps= du.getCon().prepareCall(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
            ps.close();
            du.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(AdjavaEvd.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     
    
     
      public static void updateClinicDetails(String paitentName, String email, String contactNumber, String paitentCondition, int id){
         sql = "update clinic set paitentName=?, email=?, contactNumber=?, paitentCondition=? where id=?";
        try {
            ps= du.getCon().prepareCall(sql);
            ps = du.getCon().prepareStatement(sql);
            ps.setString(1, paitentName);
            ps.setString(2,email);
            ps.setString(3,contactNumber);
            ps.setString(4,paitentCondition);
            ps.setInt(5,id);
            
              ps.executeUpdate();
              
              ps.close();
              du.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(AdjavaEvd.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     
     
    
}
