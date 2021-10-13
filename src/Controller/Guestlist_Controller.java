package Controller;


import Model.Guestlist_Model;
import hotelgui_Views.guestlist;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


interface GuestlistInterface {
    
    public static void LoadRInt(){
        int col;
        
        try {
            Guestlist_Model.pst = Guestlist_Model.conn.prepareStatement("Select * from checkin");
            ResultSet rs = Guestlist_Model.pst.executeQuery();
            
            ResultSetMetaData rsd = rs.getMetaData();
            col = rsd.getColumnCount();
            
            Guestlist_Model.dtm = (DefaultTableModel)guestlist.guestlist.getModel();
            Guestlist_Model.dtm.setRowCount(0);
            
            while(rs.next()){
                Vector v2 = new Vector();
                
                for(int i=1; i<=col; i++){
                    v2.add(rs.getString("chid"));
                    v2.add(rs.getString("g_name"));
                    v2.add(rs.getString("gphone"));
                    v2.add(rs.getString("chin"));
                    v2.add(rs.getString("chout"));
                    v2.add(rs.getString("roomno"));
                    v2.add(rs.getString("rtype"));
                    v2.add(rs.getString("payment"));
                }
                
                Guestlist_Model.dtm.addRow(v2); 
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(guestlist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


public class Guestlist_Controller implements GuestlistInterface{
    
    public static void LoadR(){
        GuestlistInterface.LoadRInt();
    }
}
