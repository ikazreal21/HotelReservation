package Controller;

import Model.Checkin_Model;
import hotelgui.checkin;
import hotelgui.reservation;
import hotelgui.room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Checkin_Controller {
    static checkin CheckinViews = new checkin();
    static Checkin_Model CheckinModel = new Checkin_Model();
    
//    public void Connect(){
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/HotelReservation", "root", "");    
//             
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public void autoID(){
        
        try {
            Statement st = CheckinModel.conn.createStatement();
            ResultSet rs = st.executeQuery("Select MAX(chid) from checkin");
            rs.next();
            rs.getString("MAX(chid)");
            
            if(rs.getString("MAX(chid)")==null){
                CheckinViews.setjLabel10("G001");
            }
            else{
                long id = Long.parseLong(rs.getString("MAX(chid)").substring(2, rs.getString("MAX(chid)").length()));
                id++;
                CheckinViews.setjLabel10("G" + String.format("%03d", id));
            }
           
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void Roomtype(){
        try {
            CheckinModel.pst = CheckinModel.conn.prepareStatement("select Distinct rtype from room");
            ResultSet rs = CheckinModel.pst.executeQuery();
            CheckinViews.txtrtype.removeAllItems();
            
            while(rs.next()){
                CheckinViews.txtrtype.addItem(rs.getString("rtype"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
    }
    public void RoomNo(){
        try {
            CheckinModel.pst = CheckinModel.conn.prepareStatement("select Distinct rid from room");
            ResultSet rs = CheckinModel.pst.executeQuery();
            CheckinViews.txtrno.removeAllItems();
            
            while(rs.next()){
                CheckinViews.txtrno.addItem(rs.getString("rid"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
