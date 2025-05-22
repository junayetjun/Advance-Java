
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
        
        saveStudent("Junayet", "junayet@gmail.com");
        System.out.println("After Save");
        showAllStudentDetails();
        
        
        updateStudents( "Samim", "samim@gmail.com", 1);
        System.out.println("After Update");
        showAllStudentDetails();
        
        deleteStudents(1);
        System.out.println("After Delete");
        showAllStudentDetails();
        
        
    }
    
    
    public static void saveStudent(String name, String email){
    
    sql = "insert into students(name, email) values(?,?)";
        try {
            ps = du.getCon().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2,email);
            
              ps.executeUpdate();
              
              ps.close();
              du.getCon().close();
              
              System.out.println("Data Save ");
              System.out.println("----------------------------");
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdjavaEvd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
   
     public static void showAllStudentDetails(){
     
     sql ="select * from students";
        try {
            ps = du.getCon().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                
                System.out.println("Id: "+id+ "Name: "+name
                        +"Email: "+email);
                
                        }
            rs.close();
            ps.close();
            du.getCon().close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdjavaEvd.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
     }
    
    
     public static void deleteStudents(int id){
         sql = "delete from students where id=?";
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
     
    
     
      public static void updateStudents(String name, String email, int id){
         sql = "update students set name=?, email=? where id=?";
        try {
            ps= du.getCon().prepareCall(sql);
            ps = du.getCon().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2,email);
            ps.setInt(3,id);
            
              ps.executeUpdate();
              
              ps.close();
              du.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(AdjavaEvd.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     
     
    
}
