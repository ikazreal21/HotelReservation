package Controller;

import Model.Checkin_Model;
import hotelgui_Views.checkin;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



interface CheckinInterface {
    
    public static void CheckinInt(){
        String resno = checkin.jLabel10.getText();
        String gname = checkin.txtgname.getText();
        String phone = checkin.txtphone.getText();
        SimpleDateFormat chin = new SimpleDateFormat("yyyy-MM-dd");
        String indate = chin.format(checkin.txtcheckin.getDate());
//      SimpleDateFormat chout = new SimpleDateFormat("yyyy-MM-dd");
//      String outdate = chout.format(txtcheckout.getDate());
        String roomtype = checkin.txtrtype.getSelectedItem().toString();
        String roomno = checkin.txtrno.getSelectedItem().toString();
        String payment = checkin.txtpayment.getText();
 
        try {
            Checkin_Model.pst = Checkin_Model.conn.prepareStatement("insert into checkin(chid, g_name, gphone, chin, roomno, rtype, payment) values(?,?,?,?,?,?,?)");
            Checkin_Model.pst.setString(1, resno);
            Checkin_Model.pst.setString(2, gname);
            Checkin_Model.pst.setString(3, phone);
            Checkin_Model.pst.setString(4, indate);
//          pst.setString(5, outdate);
            Checkin_Model.pst.setString(5, roomno);
            Checkin_Model.pst.setString(6, roomtype);
            Checkin_Model.pst.setString(7, payment);
            Checkin_Model.pst.executeUpdate();
            
            int res3 = JOptionPane.showOptionDialog(new JFrame(), "Do you Confirm the Check in of " + gname + "\nWith the Balance of " + payment,"Confirmation",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
                    JOptionPane.YES_OPTION);
                    
            switch (res3) {
                case JOptionPane.YES_OPTION:
                    JOptionPane.showMessageDialog(null, "Checkin Succesfull");
                    checkin.txtgname.setText("");
                    checkin.txtphone.setText("");
                    checkin.txtrno.setSelectedIndex(-1);
                    checkin.txtrtype.setSelectedIndex(-1);
                    checkin.txtpayment.setText("");
                    Checkin_Model.autoID();
                    break;
                case JOptionPane.NO_OPTION:
                    System.exit(0);
                default:
                    break;
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(checkin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



public class Checkin_Controller implements CheckinInterface {    
  
    public static void Checkin_Control(){
        CheckinInterface.CheckinInt();
    }
}
