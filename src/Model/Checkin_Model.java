package Model;

import hotelgui.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Checkin_Model {    
    public Connection conn;
    public PreparedStatement pst;
    public DefaultTableModel dtm;
    
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/HotelReservation", "root", "");    
             
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
