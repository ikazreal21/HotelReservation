package Model;

import static hotelgui_Views.reservation.reservationNo;
import static hotelgui_Views.reservation.reservationtable;
import static hotelgui_Views.reservation.txtbedtype;
import static hotelgui_Views.reservation.txtrno;
import static hotelgui_Views.reservation.txtrtype;
import hotelgui_Views.reservation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class Reservation_Model {
    
    public static Connection conn;
    public static PreparedStatement pst;
    public static DefaultTableModel dtm;
    
    public static void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/HotelReservation", "root", "");    
             
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void autoID(){
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select MAX(reid) from reservation");
            rs.next();
            rs.getString("MAX(reid)");
            
            if(rs.getString("MAX(reid)")==null){
                reservationNo.setText("RE001");
            }
            else{
                long id = Long.parseLong(rs.getString("MAX(reid)").substring(2, rs.getString("MAX(reid)").length()));
                id++;
                reservationNo.setText("RE" + String.format("%03d", id));
            }
           
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void Roomtype(){
        try {
            pst = conn.prepareStatement("select Distinct rtype from room");
            ResultSet rs = pst.executeQuery();
            txtrtype.removeAllItems();
            
            while(rs.next()){
                txtrtype.addItem(rs.getString("rtype"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
    }
    public static void Bedtype(){
        try {
            pst = conn.prepareStatement("select Distinct bedtype from room");
            ResultSet rs = pst.executeQuery();
            txtbedtype.removeAllItems();
            
            while(rs.next()){
                txtbedtype.addItem(rs.getString("bedtype"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
    }
    public static void RoomNo(){
        try {
            pst = conn.prepareStatement("select Distinct rid from room");
            ResultSet rs = pst.executeQuery();
            txtrno.removeAllItems();
            
            while(rs.next()){
                txtrno.addItem(rs.getString("rid"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
    }
    
    
    
    public static void LoadR(){
        int col;
        
        try {
            pst = conn.prepareStatement("Select * from reservation");
            ResultSet rs = pst.executeQuery();
            
            ResultSetMetaData rsd = rs.getMetaData();
            col = rsd.getColumnCount();
            
            dtm = (DefaultTableModel)reservationtable.getModel();
            dtm.setRowCount(0);
            
            while(rs.next()){
                Vector v2 = new Vector();
                
                for(int i=1; i<=col; i++){
                    v2.add(rs.getString("reid"));
                    v2.add(rs.getString("gname"));
                    v2.add(rs.getString("phone"));
                    v2.add(rs.getString("checkin"));
                    v2.add(rs.getString("checkout"));
                    v2.add(rs.getString("rtype"));
                    v2.add(rs.getString("roomno"));
                    v2.add(rs.getString("bedtype"));
                    v2.add(rs.getString("tamount"));
                }
                
                dtm.addRow(v2); 
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
