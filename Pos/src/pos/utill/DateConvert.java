
package pos.utill;

import java.sql.Date;


public class DateConvert {
    
    
    public Date utilDateToAqlDate(java.util.Date date){
        
        if(date == null){
            return null;
        
        }
        
        return new java.sql.Date(date.getTime());
    
    
    
    }
    
    
}
