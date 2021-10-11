/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_giang;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author TruongGiang
 */
public class DangNhapForm extends javax.swing.JFrame {
    public ResultSet rs =null;
    public Statement st;
    Connect cn =new Connect();
    public static String magv;
    
    /**
     * Creates new form DangNhapForm
     */
    public DangNhapForm() {
        initComponents();
        txtMKAD.setEnabled(false);
        cn.getConnect();
    }
    private void dangNhap()throws Exception{
        cn.getConnect();
        magv=txtMaGV.getText();
        String mk = new String(txtMK.getPassword());
        st=cn.con.createStatement();
        rs=st.executeQuery("select MaGiaoVien,MatKhau from GVIEN");
        int d=0;
        while(rs.next()){
            if(magv.equals(rs.getString("MaGiaoVien"))&&mk.equals(rs.getString("MatKhau"))){
                d++;
                JOptionPane.showMessageDialog(this, "Bạn đăng nhập thành công vào trang Giáo Viên");
                new MenuChinhForm().setVisible(true);
                this.setVisible(false);
            }
        }
        if(d==0){
            JOptionPane.showMessageDialog(this, "kiểm tra lại mã giáo viên hoặc mật khẩu chưa đúng", "Invalid", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void dangNhapQT(){
        String mk = new String(txtMKAD.getPassword());
        if(mk.equals("administrator")){
            JOptionPane.showMessageDialog(this, "Bạn đăng nhập thành công với tư cách Administrator");
            new MenuAdsForm().setVisible(true);
            this.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(this, "Mật khẩu không đúng");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButtonlogo = new javax.swing.JButton();
        txtMaGV = new javax.swing.JTextField();
        jLabelMaGV = new javax.swing.JLabel();
        jLabelMK = new javax.swing.JLabel();
        txtMK = new javax.swing.JPasswordField();
        jButtonDangNhap = new javax.swing.JButton();
        jButtonThoat = new javax.swing.JButton();
        jCheckBoxXacNhanThoat = new javax.swing.JCheckBox();
        jLabelMKAD = new javax.swing.JLabel();
        txtMKAD = new javax.swing.JPasswordField();
        jCheckBoxAdmini = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/haui.png"))); // NOI18N

        txtMaGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaGVActionPerformed(evt);
            }
        });

        jLabelMaGV.setText("Mã Giáo Viên");

        jLabelMK.setText("Mật Khẩu");

        jButtonDangNhap.setText("Đăng Nhập");
        jButtonDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDangNhapActionPerformed(evt);
            }
        });

        jButtonThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/Thoat.png"))); // NOI18N
        jButtonThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThoatActionPerformed(evt);
            }
        });

        jCheckBoxXacNhanThoat.setText("Xác Nhận Thoát");

        jLabelMKAD.setText("Mật Khẩu Administrator");

        jCheckBoxAdmini.setText("Administrator");
        jCheckBoxAdmini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxAdminiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButtonlogo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMaGV)
                            .addComponent(jLabelMK))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaGV)
                            .addComponent(txtMK, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBoxAdmini, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(jLabelMKAD)
                                .addGap(129, 129, 129)
                                .addComponent(txtMKAD, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBoxXacNhanThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)))))
                .addGap(99, 99, 99))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonlogo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMaGV))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMK)
                            .addComponent(txtMK))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jButtonThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(77, 77, 77))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMKAD)
                            .addComponent(txtMKAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxAdmini))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonDangNhap)
                            .addComponent(jCheckBoxXacNhanThoat))
                        .addGap(103, 103, 103))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaGVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaGVActionPerformed

    private void jButtonDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDangNhapActionPerformed
        if(!jCheckBoxAdmini.isSelected()){
            try {
                dangNhap();
            } catch (Exception ex) {
                Logger.getLogger(DangNhapForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            dangNhapQT();
        }
    }//GEN-LAST:event_jButtonDangNhapActionPerformed

    private void jButtonThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThoatActionPerformed
        // TODO add your handling code here:
        if(jCheckBoxXacNhanThoat.isSelected()){
            int select=JOptionPane.showConfirmDialog(this, "Bạn muốn thoát chứ", "THOÁT", JOptionPane.YES_NO_OPTION);
            if(select==JOptionPane.YES_OPTION){
                System.exit(0);
            }else{
                return;
            }
        }else{
            return;
        }
    }//GEN-LAST:event_jButtonThoatActionPerformed

    private void jCheckBoxAdminiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxAdminiMouseClicked
        // TODO add your handling code here:
        if(jCheckBoxAdmini.isSelected()==false){
            txtMK.setEnabled(true);
            txtMaGV.setEnabled(true);
            txtMKAD.setEnabled(false);
        }else{
            txtMK.setEnabled(false);
            txtMaGV.setEnabled(false);
            txtMKAD.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBoxAdminiMouseClicked
    
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
            java.util.logging.Logger.getLogger(DangNhapForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhapForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhapForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhapForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhapForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonDangNhap;
    private javax.swing.JButton jButtonThoat;
    private javax.swing.JButton jButtonlogo;
    private javax.swing.JCheckBox jCheckBoxAdmini;
    private javax.swing.JCheckBox jCheckBoxXacNhanThoat;
    private javax.swing.JLabel jLabelMK;
    private javax.swing.JLabel jLabelMKAD;
    private javax.swing.JLabel jLabelMaGV;
    private javax.swing.JPasswordField txtMK;
    private javax.swing.JPasswordField txtMKAD;
    private javax.swing.JTextField txtMaGV;
    // End of variables declaration//GEN-END:variables
}
