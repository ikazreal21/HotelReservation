/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelgui_Views;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author expzak
 */
public class reservation extends javax.swing.JFrame {

    /**
     * Creates new form reservation
     */
    public reservation() {
        initComponents();
        Connect();
        autoID();
        Roomtype();
        Bedtype();
        RoomNo();
        LoadR();
                
    }
    
    
    
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
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Roomtype(){
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
    public void Bedtype(){
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
    public void RoomNo(){
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
    
    
    
    public void LoadR(){
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
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        reservationNo = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        txtphone = new javax.swing.JTextField();
        txtcheckin = new com.toedter.calendar.JDateChooser();
        txtcheckout = new com.toedter.calendar.JDateChooser();
        txtrtype = new javax.swing.JComboBox<>();
        txtrno = new javax.swing.JComboBox<>();
        txtbedtype = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        reservationtable = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtpay = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 153)));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(95, 117, 141));
        jLabel1.setText("Date Reservation");

        jLabel2.setText("Reservation No.");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setText("Guest Name");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setText("Phone");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel5.setText("Check In");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setText("Check Out");

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel7.setText("Room Type");

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setText("Room Number");

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel9.setText("Bed Type");

        reservationNo.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        reservationNo.setForeground(new java.awt.Color(114, 152, 139));
        reservationNo.setText("jLabel10");

        txtname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnameActionPerformed(evt);
            }
        });

        txtrtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrtypeActionPerformed(evt);
            }
        });

        txtrno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));

        jButton1.setBackground(new java.awt.Color(114, 152, 139));
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(114, 152, 139));
        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(114, 152, 139));
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(114, 152, 139));
        jButton4.setText("Reset");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        reservationtable.setAutoCreateRowSorter(true);
        reservationtable.setForeground(new java.awt.Color(237, 183, 147));
        reservationtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reservation No.", "Name", "Phone", "Check In", "Check Out", "Room Type", "Room Number", "Bed Type", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        reservationtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reservationtableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(reservationtable);

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel11.setText("Payment");

        jButton5.setText("Go back");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(reservationNo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtcheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtcheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtrno, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtrtype, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtbedtype, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtpay, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel1)))
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(97, 97, 97)
                        .addComponent(jButton3)
                        .addGap(131, 131, 131)
                        .addComponent(jButton5)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(reservationNo))
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5))
                            .addComponent(txtcheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtcheckout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtrtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtrno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtbedtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtpay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
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
            pst = conn.prepareStatement("insert into reservation(reid, gname, phone, checkin, checkout, bedtype, roomno, rtype, tamount) values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, resno);
            pst.setString(2, gname);
            pst.setString(3, phone);
            pst.setString(4, indate);
            pst.setString(5, outdate);
            pst.setString(6, bedtype);
            pst.setString(7, roomno);
            pst.setString(8, roomtype);
            pst.setString(9, payment);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Reservation Added");

            txtname.setText("");
            txtphone.setText("");
            txtname.setText("");
            txtrno.setSelectedIndex(-1);
            txtrtype.setSelectedIndex(-1);
            txtbedtype.setSelectedIndex(-1);
            txtpay.setText("");
            autoID();
            LoadR();
                
          
        } catch (SQLException ex) {
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnameActionPerformed

    private void reservationtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reservationtableMouseClicked
        try {
            dtm = (DefaultTableModel)reservationtable.getModel();
            int select = reservationtable.getSelectedRow();
            SimpleDateFormat chin = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat chout = new SimpleDateFormat("yyyy-MM-dd");
            
            
            reservationNo.setText(dtm.getValueAt(select, 0).toString());
            txtname.setText(dtm.getValueAt(select, 1).toString());
            txtphone.setText(dtm.getValueAt(select, 2).toString());
  
            String in = dtm.getValueAt(select, 3).toString();
            String out = dtm.getValueAt(select, 4).toString();
            Date indate = chin.parse(in);
            Date outdate = chout.parse(out);

            
            txtcheckin.setDate(indate);
            txtcheckout.setDate(outdate);
            txtrtype.setSelectedItem(dtm.getValueAt(select, 5).toString());
            txtrno.setSelectedItem(dtm.getValueAt(select, 6).toString());
            txtbedtype.setSelectedItem(dtm.getValueAt(select, 7).toString());
            txtpay.setText(dtm.getValueAt(select, 8).toString());
            
            jButton1.setEnabled(false);
        } catch (ParseException ex) {
            Logger.getLogger(reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_reservationtableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
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
            pst = conn.prepareStatement("update reservation set gname = ?, phone = ?, checkin = ?, checkout = ?, bedtype = ?, roomno = ?, rtype = ?, tamount = ? where reid = ? ");
            pst.setString(1, resno);
            pst.setString(2, gname);
            pst.setString(3, phone);
            pst.setString(4, indate);
            pst.setString(5, outdate);
            pst.setString(6, bedtype);
            pst.setString(7, roomno);
            pst.setString(8, roomtype);
            pst.setString(9, payment);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Reservation Edited");

            txtname.setText("");
            txtphone.setText("");
            txtrno.setSelectedIndex(-1);
            txtrtype.setSelectedIndex(-1);
            txtbedtype.setSelectedIndex(-1);
            txtpay.setText("");
            autoID();
            LoadR();
            jButton1.setEnabled(true);
                
          
        } catch (SQLException ex) {
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        txtname.setText("");
        txtphone.setText("");
        txtrno.setSelectedIndex(-1);
        txtrtype.setSelectedIndex(-1);
        txtbedtype.setSelectedIndex(-1);
        txtpay.setText("");
        autoID();
        LoadR();
        jButton1.setEnabled(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String resno = reservationNo.getText();
        
        try {
            pst = conn.prepareStatement("delete from reservation where reid = ? ");
            pst.setString(1, resno);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Room Deleted");
            
            txtname.setText("");
            txtphone.setText("");
            txtrno.setSelectedIndex(-1);
            txtrtype.setSelectedIndex(-1);
            txtbedtype.setSelectedIndex(-1);
            txtpay.setText("");
            autoID();
            LoadR();
            jButton1.setEnabled(true);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.setVisible(false);
        openingform open = new openingform();
        open.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtrtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrtypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrtypeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reservation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel reservationNo;
    private javax.swing.JTable reservationtable;
    private javax.swing.JComboBox<String> txtbedtype;
    private com.toedter.calendar.JDateChooser txtcheckin;
    private com.toedter.calendar.JDateChooser txtcheckout;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtpay;
    private javax.swing.JTextField txtphone;
    private javax.swing.JComboBox<String> txtrno;
    private javax.swing.JComboBox<String> txtrtype;
    // End of variables declaration//GEN-END:variables
}
