package Controller;

import hotelgui_Views.checkout;
import Model.Checkout_Model;
import static hotelgui_Views.checkout.checkout;
import static hotelgui_Views.checkout.txtcheckout;
import static hotelgui_Views.checkout.txtpayment;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


interface CheckoutInterface{
    
    public static void Checkout_Button_Int(){
         Checkout_Model.dtm = (DefaultTableModel)checkout.getModel();
        int select = checkout.getSelectedRow();
        String chno = Checkout_Model.dtm.getValueAt(select, 0).toString();
        
        try {
            Checkout_Model.pst = Checkout_Model.conn.prepareStatement("delete from checkin where chid = ? ");
            Checkout_Model.pst.setString(1, chno);
            Checkout_Model.pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Check Out Complete");
            txtpayment.setText("");
            
           Checkout_Model.LoadR();   
        } catch (SQLException ex) {
            Logger.getLogger(checkout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void Table_Click_Int(){
        try {
            Checkout_Model.dtm = (DefaultTableModel)checkout.getModel();
            int select = checkout.getSelectedRow();
           
            SimpleDateFormat chout = new SimpleDateFormat("yyyy-MM-dd");

            String out = Checkout_Model.dtm.getValueAt(select, 3).toString();  
            Date outdate = chout.parse(out);
            
            txtcheckout.setDate(outdate);
            txtpayment.setText(Checkout_Model.dtm.getValueAt(select, 4).toString());
            
        } catch (ParseException ex) {
            Logger.getLogger(checkout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



public class Checkout_Controller implements CheckoutInterface{
    
    public static void Checkout_Button(){
       CheckoutInterface.Checkout_Button_Int();
    }
    
    public static void Table_Click(){
        CheckoutInterface.Table_Click_Int();
    }
}
