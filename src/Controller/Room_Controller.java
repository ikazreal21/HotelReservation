package Controller;

import java.sql.*;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Room_Model;
import hotelgui_Views.room;
import static hotelgui_Views.room.jButton1;
import static hotelgui_Views.room.jLabel6;
import static hotelgui_Views.room.roomtable;
import static hotelgui_Views.room.textbtype;
import static hotelgui_Views.room.txtam;
import static hotelgui_Views.room.txtrtype;
import javax.swing.table.DefaultTableModel;



public class Room_Controller {
    
    public static void Room_Button(){
        String roomno = jLabel6.getText();
        String roomtype = txtrtype.getSelectedItem().toString();
        String bedtype = textbtype.getSelectedItem().toString();
        String amount = txtam.getText();
        
        
        try {
            Room_Model.pst = Room_Model.conn.prepareStatement("insert into room(rid, rtype, bedtype, amount) values(?,?,?,?)");
            Room_Model.pst.setString(1, roomno);
            Room_Model.pst.setString(2, roomtype);
            Room_Model.pst.setString(3, bedtype);
            Room_Model.pst.setString(4, amount);
            Room_Model.pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Room Added");
            
            txtrtype.setSelectedIndex(-1);
            textbtype.setSelectedIndex(-1);
            txtam.setText("");
            
            Room_Model.autoID();
            Room_Model.LoadR();
            
        } catch (SQLException ex) {
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void Table_Click(){
        Room_Model.dtm = (DefaultTableModel)roomtable.getModel();
        int select = roomtable.getSelectedRow();
       
        jLabel6.setText(Room_Model.dtm.getValueAt(select, 0).toString());
        txtrtype.setSelectedItem(Room_Model.dtm.getValueAt(select, 1).toString());
        textbtype.setSelectedItem(Room_Model.dtm.getValueAt(select, 2).toString());
        txtam.setText(Room_Model.dtm.getValueAt(select, 3).toString());
        
        jButton1.setEnabled(false);
    }
    
    public static void Room_Edit(){
        String roomno = jLabel6.getText();
        String roomtype = txtrtype.getSelectedItem().toString();
        String bedtype = textbtype.getSelectedItem().toString();
        String amount = txtam.getText();
        
        
        try {
            Room_Model.pst = Room_Model.conn.prepareStatement("update room set rtype = ?, bedtype = ?, amount = ? where rid = ? ");
            Room_Model.pst.setString(1, roomtype);
            Room_Model.pst.setString(2, bedtype);
            Room_Model.pst.setString(3, amount);
            Room_Model.pst.setString(4, roomno);
            Room_Model.pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Room Edited");
            
            txtrtype.setSelectedIndex(-1);
            textbtype.setSelectedIndex(-1);
            txtam.setText("");
            
            Room_Model.autoID();
            Room_Model.LoadR();
            jButton1.setEnabled(true);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void Room_Clear(){
        txtrtype.setSelectedIndex(-1);
        textbtype.setSelectedIndex(-1);
        txtam.setText("");

        Room_Model.autoID();
        Room_Model.LoadR();
        jButton1.setEnabled(true);
    }
    
    public static void Room_Delete(){
        String roomno = jLabel6.getText();

        try {
            Room_Model.pst = Room_Model.conn.prepareStatement("delete from room where rid = ? ");
            Room_Model.pst.setString(1, roomno);
            Room_Model.pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Room Deleted");
            
            txtrtype.setSelectedIndex(-1);
            textbtype.setSelectedIndex(-1);
            txtam.setText("");
            
            Room_Model.autoID();
            Room_Model.LoadR();
            jButton1.setEnabled(true);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
