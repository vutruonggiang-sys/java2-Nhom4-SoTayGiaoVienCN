/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_duc;
import DuLieu.GiaoVien;
import DuLieu.CongViec;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import btl_giang.DangNhapForm;
import btl_giang.MenuChinhForm;

public class QuanLyCongViecFrom extends javax.swing.JFrame {

    private List<CongViec> listCV=new ArrayList<>();
    private Connection con=null;
    private Statement stm ;
    
    private Connection getConnection(){
        if(con==null){
            try {
                String url="jdbc:derby://localhost:1527/BaiTL";
            String user="Nhom4";
            String pass="123";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=(Connection)DriverManager.getConnection(url, user, pass);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return con;
    }
    private void getListCV(){
        con=getConnection();
        try {
            stm=con.createStatement();
            ResultSet rs=stm.executeQuery("select * from CongViec where MaGiaoVien='"+DangNhapForm.magv+"'");
            while (rs.next()) {                
                String maCV=rs.getString("MaCongViec");
                String maGV= rs.getString("MaGiaoVien");
                String noiDung = rs.getString("NoiDungToChuc");
                String ngayThucHien = rs.getString("NgayThucHien");
                String trangThai = rs.getString("TrangThai");
                CongViec c = new CongViec(maCV,noiDung,trangThai,ngayThucHien,new GiaoVien(maGV));
                listCV.add(c);
            }
            Collections.sort(listCV);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi "+e.getMessage(),"Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    public QuanLyCongViecFrom() {
        initComponents();
        getListCV();
        loadTable(listCV);
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i= jTable1.getSelectedRow();
                if(i<0)return;
                CongViec c = listCV.get(i);
                jTextField1.setEnabled(false);
                jTextArea1.setEnabled(false);
                jTextField1.setText(c.getMaCongViec());
                if(c.getTrangThai().equalsIgnoreCase("Chưa thực hiện")){
                    jComboBox1.setSelectedIndex(0);
                }
                else if(c.getTrangThai().equalsIgnoreCase("Hoàn thành")){
                    jComboBox1.setSelectedIndex(1);
                }
                else jComboBox1.setSelectedIndex(2);
                jTextField4.setText(c.getNgayThucHien());
                jTextArea1.setText(c.getNdToChucHD());
            }
        });
        
    }

    private void loadTable(List<CongViec> list){
        jTable1.setModel(new tableDataCongViec(list));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Quản Lý Công Việc");

        jLabel2.setText("Mã Công Việc:");

        jLabel4.setText("Trạng Thái:");

        jLabel5.setText("Nội Dung:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton1.setText("Thêm ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sửa ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setText("Ngày Thực Hiện:");

        jButton4.setText("Xóa Trắng");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Quay Lại");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thực hiện", "Hoàn thành", "Chưa hoàn thành", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jButton4))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(34, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(jButton5)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       jTextField1.setText("");
       jComboBox1.setSelectedIndex(0);
       jTextField4.setText("");
       jTextArea1.setText("");
       jTextField1.requestFocus();
       jTextField1.setEnabled(true);
       jTextArea1.setEnabled(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       new MenuChinhForm().setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String maCV = jTextField1.getText();
            String trangThai = jComboBox1.getSelectedItem().toString();
            String ngayThucHien = jTextField4.getText();
            String noiDung = jTextArea1.getText();
            if(maCV.equals("") || ngayThucHien.equals("") || noiDung.equals("")){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin công việc!","Chú ý",JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            CongViec congViec= new CongViec(maCV,noiDung,trangThai,ngayThucHien,new GiaoVien(DangNhapForm.magv));
            if(listCV.contains(congViec)){
                JOptionPane.showMessageDialog(this, "Công việc này đã có rồi!","chú ý",JOptionPane.WARNING_MESSAGE);
                return;
            }
            con=getConnection();
            try {
                stm=con.createStatement();
                int i= stm.executeUpdate("insert into CongViec(MaCongViec,MaGiaoVien,NoiDungToChuc,TrangThai,NgayThucHien) Values ('"+
                        maCV+"','"+DangNhapForm.magv+"','"+noiDung+"','"+trangThai+"','"+ngayThucHien+"')");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi thêm "+e.getMessage(),"lỗi",JOptionPane.ERROR_MESSAGE);
                return;
            }
            listCV.add(congViec);
            Collections.sort(listCV);
            loadTable(listCV);
            JOptionPane.showMessageDialog(this, "Thêm thành công.");
            jButton4ActionPerformed(evt);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi "+e.getMessage(),"lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String maCV = jTextField1.getText();
            if(maCV.equals("")){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã công việc!","Chú ý",JOptionPane.WARNING_MESSAGE);
                return;
            }
            int k= JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa công việc này không?","Xác Nhận",JOptionPane.YES_NO_OPTION);
            if(k==JOptionPane.YES_OPTION){
                
            CongViec c = new CongViec(maCV);
            if(listCV.contains(c)){
                con =getConnection();
                try {
                    stm= con.createStatement();
                    int i= stm.executeUpdate("delete from CongViec where MaCongViec='"+maCV+"'");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi "+e.getMessage(),"Lỗi",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                listCV.remove(c);
                loadTable(listCV);
                JOptionPane.showMessageDialog(this,"Xóa thành công");
                jButton4ActionPerformed(evt);
                return;
            }
            JOptionPane.showMessageDialog(this, "không tồn tại công việc này.","chú ý",JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi "+e.getMessage(),"Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String maCV = jTextField1.getText();
            String trangThai = jComboBox1.getSelectedItem().toString();
            String ngayThucHien =jTextField4.getText();
            CongViec c= new CongViec(maCV);
            if(listCV.contains(c)){
                con =getConnection();
                try {
                    stm=con.createStatement();
                    int i=stm.executeUpdate("update CongViec set TrangThai='"+trangThai+"',NgayThucHien='"+ngayThucHien+"' where MaCongViec='"+maCV+"'");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi cập nhật "+e.getMessage(),"Lỗi",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                listCV.get(listCV.indexOf(c)).setTrangThai(trangThai);
                listCV.get(listCV.indexOf(c)).setNgayThucHien(ngayThucHien);
                loadTable(listCV);
                JOptionPane.showMessageDialog(this, "Sửa thành công.");
                jButton4ActionPerformed(evt);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi "+e.getMessage(),"Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    
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
            java.util.logging.Logger.getLogger(QuanLyCongViecFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyCongViecFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyCongViecFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyCongViecFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyCongViecFrom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
