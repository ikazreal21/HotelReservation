package Model;

import static hotelgui_Views.checkout.checkout;
import hotelgui_Views.checkout;
import hotelgui_Views.room;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Checkout_Model {
    
    public static Connection conn;
    public static PreparedStatement pst;
    public static DefaultTableModel dtm;
    
    public static void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/HotelReservation", "root", "");    
             
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(checkout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void LoadR(){
        int col;
        
        try {
            pst = conn.prepareStatement("Select * from checkin");
            ResultSet rs = pst.executeQuery();
            
            ResultSetMetaData rsd = rs.getMetaData();
            col = rsd.getColumnCount();
            
            dtm = (DefaultTableModel)checkout.getModel();
            dtm.setRowCount(0);
            
            while(rs.next()){
                Vector v2 = new Vector();
                
                for(int i=1; i<=col; i++){
                    v2.add(rs.getString("chid"));
                    v2.add(rs.getString("g_name"));
                    v2.add(rs.getString("chin"));
                    v2.add(rs.getString("chout"));
                    v2.add(rs.getString("payment"));
                }
                
                dtm.addRow(v2); 
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
