/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import hotelgui.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Checkin_Model {
    static checkin CheckinViews = new checkin();
    
    Connection conn;
    PreparedStatement pst;
    DefaultTableModel dtm;
    
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/HotelReservation", "root", "");    
             
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void autoID(){
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select MAX(chid) from checkin");
            rs.next();
            rs.getString("MAX(chid)");
            
            if(rs.getString("MAX(chid)")==null){
                CheckinViews.jLabel10.setText("G001");
            }
            else{
                long id = Long.parseLong(rs.getString("MAX(chid)").substring(2, rs.getString("MAX(chid)").length()));
                id++;
                CheckinViews.jLabel10.setText("G" + String.format("%03d", id));
            }
           
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void Roomtype(){
        try {
            pst = conn.prepareStatement("select Distinct rtype from room");
            ResultSet rs = pst.executeQuery();
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
            pst = conn.prepareStatement("select Distinct rid from room");
            ResultSet rs = pst.executeQuery();
            CheckinViews.txtrno.removeAllItems();
            
            while(rs.next()){
                CheckinViews.txtrno.addItem(rs.getString("rid"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(reservation.class.getName()).log(Level.SEVERE, null, ex);
        }

}
