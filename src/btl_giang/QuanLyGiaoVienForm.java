/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_giang;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author TruongGiang
 */
public class QuanLyGiaoVienForm extends javax.swing.JFrame {
    public static ResultSet rs =null;
    public Statement st=null;
    Connect cn =new Connect();
    /**
     * Creates new form QuanLyGiaoVienForm
     */
    public QuanLyGiaoVienForm() {
        initComponents();
        cn.getConnect();
        xoaTrang();
        textCanhBao.setEnabled(false);
        loadData();
    }
    private void xoaTrang(){
        txtDC.setText("");
        txtHoTen.setText("");
        txtMK.setText("");
        txtMaBHYT.setText("");
        txtMaGV.setText("");
        txtSDT.setText("");
        txtSoCMND.setText("");
        txtQuocTich.setText("");
    }
    private void loadData(){
        cn.getConnect();
        jTable1.removeAll();
        String []noiDung={"Mã Giáo Viên","Họ Tên","Quốc Tịch","Xã","Quận","Tỉnh","Số CMND","Số Điện Thoại","Mã BHYT","Mật Khẩu"};
        DefaultTableModel table =new DefaultTableModel(noiDung,0);
        try{
            st=cn.con.createStatement();
            rs=st.executeQuery("select MaGiaoVien,HoTen,QuocTich,Xa,Quan,Tinh,SoCMND,SoDienThoai,MaBHYT,MatKhau from GVIEN ");
            while(rs.next()){
                String []noiDung1={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)
                            ,rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
                            ,rs.getString(9),rs.getString(10)};
                    table.addRow(noiDung1);
            }
            rs.close();
        }catch(Exception e){
            System.out.println("Lỗi là : "+e.getMessage());
        }
        jTable1.setModel(table);
    }
    private void them(){
        cn.getConnect();
        int d=0;
        try{
            st=cn.con.createStatement();
            rs=st.executeQuery("select MaGiaoVien from GVIEN ");
        while(rs.next()){
            if(txtMaGV.getText().equals(rs.getString("MaGiaoVien"))) d++;
        }
        }catch(Exception e){}
        if(d!=0){
            JOptionPane.showMessageDialog(this, "Mã Giáo Viên bị trùng");
        }else{
        if(!txtMaGV.getText().trim().equals("")&&!txtMK.getText().trim().equals("")){
            String []diaChi = txtDC.getText().split(",");
            int dem=0;
            for(int i=0;i<txtDC.getText().length();i++)
            if(txtDC.getText().charAt(i)==',') dem++;
        String sql = "insert into GVIEN (MaGiaoVien,Hoten,QuocTich,Xa,Quan,Tinh,SoCMND,SoDienThoai,MaBHYT,MatKhau)"
                + " values (?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pre = cn.con.prepareStatement(sql);
            pre.setString(1, txtMaGV.getText());
            pre.setString(2, txtHoTen.getText());
            pre.setString(3, txtQuocTich.getText());
            if(dem>=2){
                pre.setString(4, diaChi[0]);
                pre.setString(5, diaChi[1]);
                pre.setString(6, diaChi[2]);
            }else{ if(dem==1){
                        pre.setString(4, diaChi[0]);
                        pre.setString(5, diaChi[1]);
                        pre.setString(6, "");
                    }else {
                        pre.setString(4, txtDC.getText());
                        pre.setString(5, "");
                        pre.setString(6, "");
                    }
                }
            pre.setString(7, txtSoCMND.getText());
            pre.setString(8, txtSDT.getText());
            pre.setString(9, txtMaBHYT.getText());
            pre.setString(10, txtMK.getText());
            pre.execute();
            JOptionPane.showMessageDialog(this, "Bạn đã thêm thành công giáo viên chủ nhiệm mới ( "+txtMaGV.getText()+")");
        }catch(Exception e){ 
            System.out.println("Lỗi là : "+e.getMessage());
        }
        }else{    
            JOptionPane.showMessageDialog(this, "Cần đủ mã giáo viên và mật khẩu", "CẢNH BÁO", JOptionPane.ERROR_MESSAGE);
        }
        }
        loadData();
    }
    private void xoa(){
        cn.getConnect();
        if(txtMaGV.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Không có Mã Giáo Viên để xóa", "Cảnh Báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int d=0;
        try{
            st=cn.con.createStatement();
            rs=st.executeQuery("select MaGiaoVien from GVIEN ");
        while(rs.next()){
            if(txtMaGV.getText().equals(rs.getString("MaGiaoVien"))) d++;
        }
        }catch(Exception e){}
        if(d==0){
            JOptionPane.showMessageDialog(this, "Không tồn tại Mã Giáo Viên này");
        }else{
            int select=JOptionPane.showConfirmDialog(this, "Bạn chắc muốn xóa chứ !!!", "Thông Báo", JOptionPane.YES_NO_OPTION);
            if(select==JOptionPane.YES_OPTION){
            String sql ="delete from GVIEN where MaGiaoVien=?";
            try{
                PreparedStatement pre = cn.con.prepareStatement(sql);
                pre.setString(1, txtMaGV.getText());
                pre.execute();
                JOptionPane.showMessageDialog(this, "xóa thành công");
            }catch(Exception e){
                System.out.println("Lỗi là : "+e.getMessage());
            }
            }
        }
        loadData();
    }
    private void sua(){
        cn.getConnect();
        if(txtMaGV.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Mã Giáo Viên Trống", "CẢNH BÁO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int d=0;
        try{
            st=cn.con.createStatement();
            rs=st.executeQuery("select MaGiaoVien from GVIEN ");
        while(rs.next()){
            if(txtMaGV.getText().equals(rs.getString("MaGiaoVien"))) d++;
        }
        }catch(Exception e){}
        if(d==0){
            JOptionPane.showMessageDialog(this, "Không tồn tại Mã Giáo Viên này");
        }else{
            String []diaChi = txtDC.getText().split(",");
            int dem=0;
            for(int i=0;i<txtDC.getText().length();i++)
            if(txtDC.getText().charAt(i)==',') dem++;
        String sql = "update GVIEN set HoTen=?,QuocTich=?,Xa=?,Quan=?,Tinh=?,SoCMND=?,SoDienThoai=?,MaBHYT=?,MatKhau=? where MaGiaoVien=?";
        try{
            PreparedStatement pre = cn.con.prepareStatement(sql);
            pre.setString(1, txtHoTen.getText());
            pre.setString(2, txtQuocTich.getText());
            if(dem>=2){
                pre.setString(3, diaChi[0]);
                pre.setString(4, diaChi[1]);
                pre.setString(5, diaChi[2]);
            }else{ if(dem==1){
                        pre.setString(3, diaChi[0]);
                        pre.setString(4, diaChi[1]);
                        pre.setString(5, "");
                    }else {
                        pre.setString(3, txtDC.getText());
                        pre.setString(4, "");
                        pre.setString(5, "");
                    }
                }
            pre.setString(6, txtSoCMND.getText());
            pre.setString(7, txtSDT.getText());
            pre.setString(8, txtMaBHYT.getText());
            pre.setString(9, txtMK.getText());
            pre.setString(10, txtMaGV.getText());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(this, "Bạn đã Sửa thành công giáo viên chủ nhiệm mới ( "+txtMaGV.getText()+")");
        }catch(Exception e){
            System.out.println("Lỗi là : "+e.getMessage());
        }
        }
        loadData();
    }
    private void timKiem(){
        cn.getConnect();
        if(txtMaGV.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Không có Mã Giáo viên để tìm kiếm");
            return;
        }else{
            int d=0;
        try{
            st=cn.con.createStatement();
            rs=st.executeQuery("select MaGiaoVien from GVIEN ");
        while(rs.next()){
            if(txtMaGV.getText().equals(rs.getString("MaGiaoVien"))) d++;
        }
        }catch(Exception e){}
        
        if(d==0){
            JOptionPane.showMessageDialog(this, "Không tồn tại mã giáo viên này");
            return;
        }else{
            jTable1.removeAll();
            String []noiDung={"Mã Giáo Viên","Họ Tên","Quốc Tịch","Xã","Quận","Tỉnh","Số CMND","Số Điện Thoại","Mã BHYT","Mật Khẩu"};
            DefaultTableModel table =new DefaultTableModel(noiDung,0);
            try {
                st=cn.con.createStatement();
                rs=st.executeQuery("select MaGiaoVien,HoTen,QuocTich,Xa,Quan,Tinh,SoCMND,SoDienThoai,MaBHYT,MatKhau from GVIEN"
                        + " where MaGiaoVien='"+txtMaGV.getText()+"'");
                while(rs.next()){
                    String []noiDung1={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)
                            ,rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
                            ,rs.getString(9),rs.getString(10)};
                    table.addRow(noiDung1);
                }
                } catch (Exception e) {
                    System.out.println("Lỗi là : "+e.getMessage());
                }
            jTable1.setModel(table);
        }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButtonThem = new javax.swing.JButton();
        jButtonSua = new javax.swing.JButton();
        jButtonXoa = new javax.swing.JButton();
        jButtonXoaTrang = new javax.swing.JButton();
        jButtonThoat = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtMaGV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDC = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSoCMND = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMaBHYT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMK = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtQuocTich = new javax.swing.JTextField();
        textCanhBao = new javax.swing.JLabel();
        jButtonTimKiem = new javax.swing.JButton();
        jButtonLoad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ GIÁO VIÊN CHỦ NGHIỆM");

        jButtonThem.setText("Thêm");
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemActionPerformed(evt);
            }
        });

        jButtonSua.setText("Sửa");
        jButtonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaActionPerformed(evt);
            }
        });

        jButtonXoa.setText("Xóa");
        jButtonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaActionPerformed(evt);
            }
        });

        jButtonXoaTrang.setText("Xóa trắng");
        jButtonXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaTrangActionPerformed(evt);
            }
        });

        jButtonThoat.setText("Thoát");
        jButtonThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThoatActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã Giáo Viên");

        jLabel3.setText("Họ Tên");

        jLabel4.setText("Địa Chỉ");

        txtDC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDCMouseClicked(evt);
            }
        });

        jLabel5.setText("Số CMND");

        jLabel6.setText("Mã HBYT");

        jLabel7.setText("Mật Khẩu");

        jLabel8.setText("Số Điện Thoại");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        jLabel9.setText("Quốc Tịch");

        textCanhBao.setText("Nhập dạng xa,quan,tinh");

        jButtonTimKiem.setText("Tìm Kiếm");
        jButtonTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimKiemActionPerformed(evt);
            }
        });

        jButtonLoad.setText("Load");
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6))
                                .addGap(72, 72, 72)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                    .addComponent(txtMaBHYT))
                                .addGap(145, 145, 145))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(93, 93, 93)
                                        .addComponent(txtSoCMND))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(78, 78, 78)
                                        .addComponent(txtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel9))
                                        .addGap(93, 93, 93)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMK)
                                            .addComponent(txtQuocTich)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGap(104, 104, 104)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                            .addComponent(txtDC)
                                            .addComponent(textCanhBao, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonSua, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonThoat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonXoaTrang, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonTimKiem, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonLoad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(158, 158, 158))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonThem)
                    .addComponent(jLabel2)
                    .addComponent(txtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButtonSua)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonXoa)
                    .addComponent(textCanhBao))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtSoCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtMaBHYT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButtonXoaTrang)
                        .addGap(28, 28, 28)
                        .addComponent(jButtonTimKiem)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoad))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtQuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonThoat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int select=jTable1.getSelectedRow();
        txtMaGV.setText(jTable1.getValueAt(select, 0)+"");
        txtHoTen.setText(jTable1.getValueAt(select, 1)+"");
        txtQuocTich.setText(jTable1.getValueAt(select, 2)+"");
        txtDC.setText(jTable1.getValueAt(select, 3)+", "+jTable1.getValueAt(select, 4)+", "+jTable1.getValueAt(select, 5));
        txtSoCMND.setText(jTable1.getValueAt(select, 6)+"");
        txtSDT.setText(jTable1.getValueAt(select, 7)+"");
        txtMaBHYT.setText(jTable1.getValueAt(select, 8)+"");
        txtMK.setText(jTable1.getValueAt(select, 9)+"");
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButtonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaActionPerformed
        // TODO add your handling code here:
        xoa();
        xoaTrang();
    }//GEN-LAST:event_jButtonXoaActionPerformed

    private void jButtonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaActionPerformed
        // TODO add your handling code here:
        sua();
    }//GEN-LAST:event_jButtonSuaActionPerformed

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        // TODO add your handling code here:
        them();
    }//GEN-LAST:event_jButtonThemActionPerformed

    private void jButtonXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaTrangActionPerformed
        // TODO add your handling code here:
        xoaTrang();
    }//GEN-LAST:event_jButtonXoaTrangActionPerformed

    private void jButtonThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThoatActionPerformed
        // TODO add your handling code here:
        new MenuAdsForm().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonThoatActionPerformed

    private void txtDCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDCMouseClicked
        // TODO add your handling code here:
        textCanhBao.setEnabled(true);
    }//GEN-LAST:event_txtDCMouseClicked

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        // TODO add your handling code here:
        loadData();
        xoaTrang();
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void jButtonTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimKiemActionPerformed
        // TODO add your handling code here:
        timKiem();
    }//GEN-LAST:event_jButtonTimKiemActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyGiaoVienForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyGiaoVienForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyGiaoVienForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyGiaoVienForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyGiaoVienForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonSua;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JButton jButtonThoat;
    private javax.swing.JButton jButtonTimKiem;
    private javax.swing.JButton jButtonXoa;
    private javax.swing.JButton jButtonXoaTrang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel textCanhBao;
    private javax.swing.JTextField txtDC;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtMaBHYT;
    private javax.swing.JTextField txtMaGV;
    private javax.swing.JTextField txtQuocTich;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoCMND;
    // End of variables declaration//GEN-END:variables
}
