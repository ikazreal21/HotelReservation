package Model;

import hotelgui_Views.checkin;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Checkin_Model {    
    
    public static Connection conn;
    public static PreparedStatement pst;
    public static DefaultTableModel dtm;
    
    public static void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/HotelReservation", "root", "");    
             
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(checkin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void autoID(){
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select MAX(chid) from checkin");
            rs.next();
            rs.getString("MAX(chid)");
            
            if(rs.getString("MAX(chid)")==null){
                checkin.setjLabel10("G001");
            }
            else{
                long id = Long.parseLong(rs.getString("MAX(chid)").substring(2, rs.getString("MAX(chid)").length()));
                id++;
                checkin.setjLabel10("G" + String.format("%03d", id));
            }    
            
        } catch (SQLException ex) {
            Logger.getLogger(checkin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void Roomtype(){
        try {
            pst = conn.prepareStatement("select Distinct rtype from room");
            ResultSet rs = pst.executeQuery();
            checkin.txtrtype.removeAllItems();
            
            while(rs.next()){
                checkin.txtrtype.addItem(rs.getString("rtype"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(checkin.class.getName()).log(Level.SEVERE, null, ex);
        }   
                
    }
     
    public static void RoomNo(){
        try {
            pst = conn.prepareStatement("select Distinct rid from room");
            ResultSet rs = pst.executeQuery();
            checkin.txtrno.removeAllItems();
            
            while(rs.next()){
                checkin.txtrno.addItem(rs.getString("rid"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(checkin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
