package Controller;

import Model.Reservation_Model;
import hotelgui_Views.reservation;
import static hotelgui_Views.reservation.jButton1;
import static hotelgui_Views.reservation.reservationNo;
import static hotelgui_Views.reservation.reservationtable;
import static hotelgui_Views.reservation.txtbedtype;
import static hotelgui_Views.reservation.txtcheckin;
import static hotelgui_Views.reservation.txtcheckout;
import static hotelgui_Views.reservation.txtname;
import static hotelgui_Views.reservation.txtpay;
import static hotelgui_Views.reservation.txtphone;
import static hotelgui_Views.reservation.txtrno;
import static hotelgui_Views.reservation.txtrtype;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Reservation_Controller {

    
    public static void Reservation_Button(){
        String resno = reservationNo.getText();
        String gname = txtname.getText();
        String phone = txtphone.getText();
        SimpleDateFormat chin = new SimpleDateFormat("yyyy-MM-dd");
        String indate = chin.format(txtcheckin.getDate());
        SimpleDateFormat chout = new SimpleDateFormat("yyyy-MM-dd");
        String outdate = chout.format(txtcheckout.getDate());
        String roomtype = txtrtype.getSelectedItem().toString();
        String bedtype = txtbedtype.getSelectedItem().toString();
        String roomno = txtrno.getSelectedItem().toString();
        String payment = txtpay.getText();
 
        try {
            Reservation_Model.pst = Reservation_Model.conn.prepareStatement("insert into reservation(reid, gname, phone, checkin, checkout, bedtype, roomno, rtype, tamount) values(?,?,?,?,?,?,?,?,?)");
            Reservation_Model.pst.setString(1, resno);
            Reservation_Model.pst.setString(2, gname);
            Reservation_Model.pst.setString(3, phone);
            Reservation_Model.pst.setString(4, indate);
            Reservation_Model.pst.setString(5, outdate);
            Reservation_Model.pst.setString(6, bedtype);
            Reservation_Model.pst.setString(7, roomno);
            Reservation_Model.pst.setString(8, roomtype);
            Reservation_Model.pst.setString(9, payment);
            Reservation_Model.pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Reservation Added");

            txtname.setText("");
            txtphone.setText("");
            txtname.setText("");
            txtrno.setSelectedIndex(-1);
            txtrtype.setSelectedIndex(-1);
            txtbedtype.setSelectedIndex(-1);
            txtpay.setText("");
            Reservation_Model.autoID();
            Reservation_Model.LoadR();
                
          
        } catch (SQLException ex) {
            Logger.getLogger(reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void Table_Click(){
        try {
            Reservation_Model.dtm = (DefaultTableModel)reservationtable.getModel();
            int select = reservationtable.getSelectedRow();
            SimpleDateFormat chin = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat chout = new SimpleDateFormat("yyyy-MM-dd");
            
            
            reservationNo.setText(Reservation_Model.dtm.getValueAt(select, 0).toString());
            txtname.setText(Reservation_Model.dtm.getValueAt(select, 1).toString());
            txtphone.setText(Reservation_Model.dtm.getValueAt(select, 2).toString());
  
            String in = Reservation_Model.dtm.getValueAt(select, 3).toString();
            String out = Reservation_Model.dtm.getValueAt(select, 4).toString();
            Date indate = chin.parse(in);
            Date outdate = chout.parse(out);

            
            txtcheckin.setDate(indate);
            txtcheckout.setDate(outdate);
            txtrtype.setSelectedItem(Reservation_Model.dtm.getValueAt(select, 5).toString());
            txtrno.setSelectedItem(Reservation_Model.dtm.getValueAt(select, 6).toString());
            txtbedtype.setSelectedItem(Reservation_Model.dtm.getValueAt(select, 7).toString());
            txtpay.setText(Reservation_Model.dtm.getValueAt(select, 8).toString());
            
            jButton1.setEnabled(false);
        } catch (ParseException ex) {
            Logger.getLogger(reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void Reservation_Edit(){
        String resno = reservationNo.getText();
        String gname = txtname.getText();
        String phone = txtphone.getText();
        SimpleDateFormat chin = new SimpleDateFormat("yyyy-MM-dd");
        String indate = chin.format(txtcheckin.getDate());
        SimpleDateFormat chout = new SimpleDateFormat("yyyy-MM-dd");
        String outdate = chout.format(txtcheckout.getDate());
        String roomtype = txtrtype.getSelectedItem().toString();
        String bedtype = txtbedtype.getSelectedItem().toString();
        String roomno = txtrno.getSelectedItem().toString();
        String payment = txtpay.getText();
 
        try {
            Reservation_Model.pst = Reservation_Model.conn.prepareStatement("update reservation set gname = ?, phone = ?, checkin = ?, checkout = ?, bedtype = ?, roomno = ?, rtype = ?, tamount = ? where reid = ? ");
            Reservation_Model.pst.setString(1, resno);
            Reservation_Model.pst.setString(2, gname);
            Reservation_Model.pst.setString(3, phone);
            Reservation_Model.pst.setString(4, indate);
            Reservation_Model.pst.setString(5, outdate);
            Reservation_Model.pst.setString(6, bedtype);
            Reservation_Model.pst.setString(7, roomno);
            Reservation_Model.pst.setString(8, roomtype);
            Reservation_Model.pst.setString(9, payment);
            Reservation_Model.pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Reservation Edited");

            txtname.setText("");
            txtphone.setText("");
            txtrno.setSelectedIndex(-1);
            txtrtype.setSelectedIndex(-1);
            txtbedtype.setSelectedIndex(-1);
            txtpay.setText("");
            Reservation_Model.autoID();
            Reservation_Model.LoadR();
            jButton1.setEnabled(true);
                
          
        } catch (SQLException ex) {
            Logger.getLogger(reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void Reservation_Clear(){
        txtname.setText("");
        txtphone.setText("");
        txtrno.setSelectedIndex(-1);
        txtrtype.setSelectedIndex(-1);
        txtbedtype.setSelectedIndex(-1);
        txtpay.setText("");
        Reservation_Model.autoID();
        Reservation_Model.LoadR();
        jButton1.setEnabled(true);
    }
    
    public static void Reservation_Delete(){
        String resno = reservationNo.getText();
        
        try {
            Reservation_Model.pst = Reservation_Model.conn.prepareStatement("delete from reservation where reid = ? ");
            Reservation_Model.pst.setString(1, resno);
            Reservation_Model.pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Reservation Deleted");
            
            txtname.setText("");
            txtphone.setText("");
            txtrno.setSelectedIndex(-1);
            txtrtype.setSelectedIndex(-1);
            txtbedtype.setSelectedIndex(-1);
            txtpay.setText("");
            Reservation_Model.autoID();
            Reservation_Model.LoadR();
            jButton1.setEnabled(true);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
