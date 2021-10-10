package Model;

import hotelgui_Views.guestlist;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class Guestlist_Model {
    
    public static Connection conn;
    public static PreparedStatement pst;
    public static DefaultTableModel dtm;
    
    
    
    public static void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/HotelReservation", "root", "");    
             
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(guestlist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
