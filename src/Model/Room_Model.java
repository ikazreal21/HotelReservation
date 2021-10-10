package Model;

import hotelgui_Views.room;
import static hotelgui_Views.room.jLabel6;
import static hotelgui_Views.room.roomtable;
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


public class Room_Model {
    
    public static Connection conn;
    public static PreparedStatement pst;
    public static DefaultTableModel dtm;
    
    public static void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/HotelReservation", "root", "");    
             
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void autoID(){
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select MAX(rid) from room");
            rs.next();
            rs.getString("MAX(rid)");
            
            if(rs.getString("MAX(rid)")==null){
                jLabel6.setText("RN001");
            }
            else{
                long id = Long.parseLong(rs.getString("MAX(rid)").substring(2, rs.getString("MAX(rid)").length()));
                id++;
                jLabel6.setText("RN" + String.format("%03d", id));
            }
           
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
    public static void LoadR(){
        int col;
        
        try {
            pst = conn.prepareStatement("Select * from room");
            ResultSet rs = pst.executeQuery();
            
            ResultSetMetaData rsd = rs.getMetaData();
            col = rsd.getColumnCount();
            
            dtm = (DefaultTableModel)roomtable.getModel();
            dtm.setRowCount(0);
            
            while(rs.next()){
                Vector v2 = new Vector();
                
                for(int i=1; i<=col; i++){
                    v2.add(rs.getString("rid"));
                    v2.add(rs.getString("rtype"));
                    v2.add(rs.getString("bedtype"));
                    v2.add(rs.getString("amount"));
                }
                
                dtm.addRow(v2); 
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
