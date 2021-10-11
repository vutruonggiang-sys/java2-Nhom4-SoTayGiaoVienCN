/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_giang;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import DuLieu.SinhVien;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
/**
 *
 * @author TruongGiang
 */
public class DanhGiaForm extends javax.swing.JFrame {
    public static ResultSet rs=null;
    public Statement st=null;
    Connect cn=new Connect();
    /**
     * Creates new form DanhGiaForm
     */
    public DanhGiaForm()  {
        initComponents();
        cn.getConnect();
        jComboBoxLop.removeAllItems();
        jComboBoxLop.addItem("Chọn Lớp");
        ArrayList<String> lop =new ArrayList<>();
        lop=timLop();
        for(int i=0;i<lop.size();i++){
            jComboBoxLop.addItem(lop.get(i));
        }
        jComboBoxHanhKiem.removeAllItems();
        jComboBoxHanhKiem.addItem("Hạnh Kiểm");
        jComboBoxHanhKiem.addItem("Tốt");
        jComboBoxHanhKiem.addItem("Khá");
        jComboBoxHanhKiem.addItem("Trung Bình");
        jComboBoxHanhKiem.addItem("Yếu");
        jComboBoxDiemDG.removeAllItems();
        jComboBoxDiemDG.addItem("Chọn Lựa");
        jComboBoxDiemDG.addItem("Đã Đánh Giá");
        jComboBoxDiemDG.addItem("Chưa Đánh Giá");
        try {
            loadData();
        } catch (Exception ex) {
            System.out.println("Lỗi là : "+ex.getMessage());
        }
    }
    private ArrayList timLop(){
        ArrayList<String> lop= new ArrayList<>();
        try{
            st=cn.con.createStatement();
            rs=st.executeQuery("select MaGiaoVien,MaLop from LOP where MaGiaoVien='"+DangNhapForm.magv+"'");
        while(rs.next()){
            lop.add(rs.getString("MaLop"));
        }
        }catch(Exception e){
            System.out.println("lỗi là : "+e.getMessage());
        }
        return lop;
    }
    private void loadData() throws Exception{
        cn.getConnect();
        jTable2.removeAll();
        String []tieuDe={"Mã Sinh Viên","Họ Tên","Mã Lớp","Điểm Tích Lũy","Điểm Đánh Giá","Hạnh Kiểm"};
        DefaultTableModel table =new DefaultTableModel(tieuDe,0);
        ArrayList<String> lop= new ArrayList<>();
        lop=timLop();
        if(jComboBoxLop.getSelectedItem().equals("Chọn Lớp")){
            if(jComboBoxDiemDG.getSelectedItem().equals("Chọn Lựa")){
                for(int i=0;i<lop.size();i++){
                    st=cn.con.createStatement();
                    rs=st.executeQuery("select MaSinhVien,HoTen,MaLop,DiemTichLuy,DiemDanhGia,HanhKiem "
                    + "from SVIEN where MaLop='"+lop.get(i)+"' order by HoTen,DiemTichLuy");
                while(rs.next()){
                    String []noiDung={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
                    table.addRow(noiDung);
                }
                }
            }
            if(jComboBoxDiemDG.getSelectedItem().equals("Đã Đánh Giá")){
                for(int i=0;i<lop.size();i++){
                    st=cn.con.createStatement();
                    rs=st.executeQuery("select MaSinhVien,HoTen,MaLop,DiemTichLuy,DiemDanhGia,HanhKiem "
                    + "from SVIEN where MaLop='"+lop.get(i)+"' and DiemDanhGia is not null order by HoTen,DiemTichLuy");
                while(rs.next()){
                    String []noiDung={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
                    table.addRow(noiDung);
                }
                }
            }
            if(jComboBoxDiemDG.getSelectedItem().equals("Chưa Đánh Giá")){
                for(int i=0;i<lop.size();i++){
                    st=cn.con.createStatement();
                    rs=st.executeQuery("select MaSinhVien,HoTen,MaLop,DiemTichLuy,DiemDanhGia,HanhKiem "
                    + "from SVIEN where MaLop='"+lop.get(i)+"' and DiemDanhGia is null order by HoTen,DiemTichLuy");
                while(rs.next()){
                    String []noiDung={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
                    table.addRow(noiDung);
                }
                }
            }
        }else{
            String chonLop = jComboBoxLop.getSelectedItem().toString();
            if(jComboBoxDiemDG.getSelectedItem().equals("Chọn Lựa")){
                st=cn.con.createStatement();
                rs=st.executeQuery("select MaSinhVien,HoTen,MaLop,DiemTichLuy,DiemDanhGia,HanhKiem "
                + "from SVIEN where MaLop='"+chonLop+"' order by HoTen,DiemTichLuy");
                while(rs.next()){
                    String []noiDung={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
                    table.addRow(noiDung);
                }
            }
            if(jComboBoxDiemDG.getSelectedItem().equals("Đã Đánh Giá")){
                st=cn.con.createStatement();
                rs=st.executeQuery("select MaSinhVien,HoTen,MaLop,DiemTichLuy,DiemDanhGia,HanhKiem "
                + "from SVIEN where MaLop='"+chonLop+"' and DiemDanhGia is not null order by HoTen,DiemTichLuy");
                while(rs.next()){
                    String []noiDung={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
                    table.addRow(noiDung);
                }
            }
            if(jComboBoxDiemDG.getSelectedItem().equals("Chưa Đánh Giá")){
                st=cn.con.createStatement();
                rs=st.executeQuery("select MaSinhVien,HoTen,MaLop,DiemTichLuy,DiemDanhGia,HanhKiem "
                + "from SVIEN where MaLop='"+chonLop+"' and DiemDanhGia is null order by HoTen,DiemTichLuy");
                while(rs.next()){
                    String []noiDung={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
                    table.addRow(noiDung);
                }
            }
        }
        jTable2.setModel(table);
    }
    private void update(){
        cn.getConnect();
        if(txtMaSV.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Không có sinh viên để cập nhập", "Cảnh Báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(txtDiemDG.getText().trim().equals("")||txtDiemDG.getText().trim().equals("null")){
            if(jComboBoxHanhKiem.getSelectedItem().equals("Hạnh Kiểm")){
                String sql = "update SVIEN set HanhKiem=? where MaSinhVien=?";
                try{
                    PreparedStatement pre = cn.con.prepareStatement(sql);
                    pre.setString(1, "");
                    pre.setString(2, txtMaSV.getText());
                    pre.executeUpdate();
                }catch(Exception e){
                    System.out.println("lối là "+e.getMessage());
                }
                JOptionPane.showMessageDialog(this, "Bạn chưa đánh giá và xếp loại hạnh kiểm cho sinh viên "+txtMaSV.getText()+" "+txtHoTen.getText());
            }else{
                String sql = "update SVIEN set HanhKiem=? where MaSinhVien=?";
                try{
                    PreparedStatement pre = cn.con.prepareStatement(sql);
                    pre.setString(1, jComboBoxHanhKiem.getSelectedItem().toString());
                    pre.setString(2, txtMaSV.getText());
                    pre.executeUpdate();
                }catch(Exception e){
                    System.out.println("lối là "+e.getMessage());
                }
                JOptionPane.showMessageDialog(this, "Bạn chưa đánh giá cho sinh viên "+txtMaSV.getText()+" "+txtHoTen.getText());
            }
        }else{
            if(jComboBoxHanhKiem.getSelectedItem().equals("Hạnh Kiểm")){
                String sql = "update SVIEN set DiemDanhGia=?,HanhKiem=? where MaSinhVien=?";
                try{
                    PreparedStatement pre = cn.con.prepareStatement(sql);
                    pre.setDouble(1, Double.parseDouble(txtDiemDG.getText()));
                    pre.setString(2, "");
                    pre.setString(3, txtMaSV.getText());
                    pre.executeUpdate();
                }catch(Exception e){
                    System.out.println("lỗi là : "+e.getMessage());
                }
                JOptionPane.showMessageDialog(this, "Bạn chưa xếp loại hạnh kiểm cho sinh viên "+txtMaSV.getText()+" "+txtHoTen.getText());
            }else{
                String sql = "update SVIEN set DiemDanhGia=?, HanhKiem=? where MaSinhVien=?";
                try{
                    PreparedStatement pre = cn.con.prepareStatement(sql);
                    pre.setDouble(1, Double.parseDouble(txtDiemDG.getText()));
                    pre.setString(2, jComboBoxHanhKiem.getSelectedItem().toString());
                    pre.setString(3, txtMaSV.getText());
                    pre.executeUpdate();
                }catch(Exception e){
                    System.out.println("lối là "+e.getMessage());
                }
                JOptionPane.showMessageDialog(this, "Bạn đã cập nhập xong");
            }
        }    
        try {
            loadData();
        } catch (Exception ex) {
        }
    }
    private void timKiem(){
        cn.getConnect();
        String ma=txtMaSV.getText() , hoTen=txtHoTen.getText();
        ArrayList<String> lop= new ArrayList<>();
        lop=timLop();
        jTable2.removeAll();
        String []tieuDe={"Mã Sinh Viên","Họ Tên","Mã Lớp","Điểm Tích Lũy","Điểm Đánh Giá","Hạnh Kiểm"};
        DefaultTableModel table =new DefaultTableModel(tieuDe,0);
        if(!ma.equals("")){
        int d=0; // tìm kiếm theo mã sinh viên
        try{
            for(int i=0;i<lop.size();i++){
                st=cn.con.createStatement();
                rs=st.executeQuery("select * from SVIEN where MaLop='"+lop.get(i)+"' and MaSinhVien='"+ma+"'");
            while(rs.next()){
                if(ma.equals(rs.getString("MaSinHVien"))) {
                    d++;
                    break;
                }
            }
            }
        }catch(Exception e){
            System.out.println("Lỗi là : "+e.getMessage());
        }
        if(d==0){
            JOptionPane.showMessageDialog(this, "Không Có sinh viên này trong các lớp chủ nghiệm");
            return;
        }else{
            try{
                st=cn.con.createStatement();
                rs=st.executeQuery("select MaSinhVien,HoTen,MaLop,DiemTichLuy,DiemDanhGia,HanhKiem from SVIEN where MaSinhVien='"+ma+"'");
            while(rs.next()){
                    String []noiDung={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
                    table.addRow(noiDung);
            }
            rs.close();
        }catch(Exception e){System.out.println("Lỗi là : "+e.getMessage());}
            jTable2.setModel(table);
            return;
        }
        }
        if(!hoTen.equals("")){// tìm kiếm theo tên sinh viên
            int d1=0;
        try{
            for(int i=0;i<lop.size();i++){
                st=cn.con.createStatement();
                rs=st.executeQuery("select * from SVIEN where MaLop='"+lop.get(i)+"' and Hoten='"+hoTen+"'");
            while(rs.next()){
                if(hoTen.equals(rs.getString("HoTen"))) {
                    d1++;
                    break;
                }
            }
            }
        }catch(Exception e){
            System.out.println("Lỗi là : "+e.getMessage());
        }
        if(d1==0){
            JOptionPane.showMessageDialog(this, "Không Có sinh viên này trong các lớp chủ nghiệm");
            return;
        }else{
            try{
            for(int i=0;i<lop.size();i++){
                st=cn.con.createStatement();
                rs=st.executeQuery("select MaSinhVien,HoTen,MaLop,DiemTichLuy,DiemDanhGia,HanhKiem"
                        + " from SVIEN where MaLop='"+lop.get(i)+"' and HoTen='"+hoTen+"'");
            while(rs.next()){
                    String []noiDung={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
                    table.addRow(noiDung);
            }
            }
        }catch(Exception e){System.out.println("Lỗi là : "+e.getMessage());}
            jTable2.setModel(table);
            return;
        }
        }
        if(ma.equals("")&&hoTen.equals("")){
            JOptionPane.showMessageDialog(this, "Không có mã sinh viên tìm kiếm");
        }
    }
    private void xoaTrang(){
        txtDiemDG.setText("");
        txtDiemTL.setText("");
        txtHoTen.setText("");
        txtMaLop.setText("");
        txtMaSV.setText("");
        jComboBoxHanhKiem.setSelectedIndex(0);
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
        jComboBoxLop = new javax.swing.JComboBox<>();
        jComboBoxDiemDG = new javax.swing.JComboBox<>();
        txtMaSV = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtMaLop = new javax.swing.JTextField();
        txtDiemTL = new javax.swing.JTextField();
        txtDiemDG = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonDienDG = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButtonHienThi = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonQuayLai = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxHanhKiem = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jButtonTimKiem = new javax.swing.JButton();
        jButtonXoaTrang = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBoxLop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxDiemDG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Mã Sinh Viên");

        jLabel2.setText("Họ Tên");

        jLabel3.setText("Mã Lớp");

        jLabel4.setText("Điểm Tích Lũy");

        jButtonDienDG.setText("Cập Nhập ");
        jButtonDienDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDienDGActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel5.setText("Điểm Đánh Giá");

        jButtonHienThi.setText("Hiển Thị");
        jButtonHienThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHienThiActionPerformed(evt);
            }
        });

        jButtonQuayLai.setText("Quay Lại");
        jButtonQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuayLaiActionPerformed(evt);
            }
        });

        jLabel6.setText("Hạnh Kiểm");

        jComboBoxHanhKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("ĐÁNH GIÁ SINH VIÊN");

        jButtonTimKiem.setText("Tìm Kiếm ");
        jButtonTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimKiemActionPerformed(evt);
            }
        });

        jButtonXoaTrang.setText("Xóa Trắng");
        jButtonXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaTrangActionPerformed(evt);
            }
        });

        jLabel8.setText("Chọn Lớp");

        jLabel9.setText("Đánh Giá");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addGap(27, 27, 27)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMaSV, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                .addComponent(txtHoTen)
                                .addComponent(txtMaLop)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(txtDiemTL)))
                    .addComponent(jLabel3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonDienDG)
                            .addComponent(jButtonHienThi, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDiemDG, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(jComboBoxHanhKiem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonQuayLai, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))
                                .addGap(52, 52, 52)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBoxDiemDG, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxLop, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonXoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(jLabel7)
                .addGap(0, 306, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiemDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonDienDG)
                            .addComponent(jButtonTimKiem)
                            .addComponent(jButtonXoaTrang))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonQuayLai, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonHienThi))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(jComboBoxHanhKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtDiemTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxDiemDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuayLaiActionPerformed
        // TODO add your handling code here:
        new MenuChinhForm().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonQuayLaiActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        int selectRow =jTable2.getSelectedRow();
        txtMaSV.setText(jTable2.getValueAt(selectRow, 0)+"");
        txtHoTen.setText(jTable2.getValueAt(selectRow, 1)+"");
        txtMaLop.setText(jTable2.getValueAt(selectRow, 2)+"");
        txtDiemTL.setText(jTable2.getValueAt(selectRow, 3)+"");
        txtDiemDG.setText(jTable2.getValueAt(selectRow, 4)+"");
        String hanhKiem = jTable2.getValueAt(selectRow, 5)+"";
        switch(hanhKiem){
            case "Tốt": jComboBoxHanhKiem.setSelectedIndex(1);
            break;
            case "Khá": jComboBoxHanhKiem.setSelectedIndex(2);
            break;
            case "Trung Bình": jComboBoxHanhKiem.setSelectedIndex(3);
            break;
            case "Yếu": jComboBoxHanhKiem.setSelectedIndex(4);
            break;
            default:jComboBoxHanhKiem.setSelectedIndex(0);
            break;
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButtonHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHienThiActionPerformed
        try {
            // TODO add your handling code here:
            loadData();
        } catch (Exception ex) {
            Logger.getLogger(DanhGiaForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonHienThiActionPerformed

    private void jButtonDienDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDienDGActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_jButtonDienDGActionPerformed

    private void jButtonTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimKiemActionPerformed
        // TODO add your handling code here:
        timKiem();
    }//GEN-LAST:event_jButtonTimKiemActionPerformed

    private void jButtonXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaTrangActionPerformed
        // TODO add your handling code here:
        xoaTrang();
    }//GEN-LAST:event_jButtonXoaTrangActionPerformed

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
            java.util.logging.Logger.getLogger(DanhGiaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DanhGiaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DanhGiaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DanhGiaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DanhGiaForm().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDienDG;
    private javax.swing.JButton jButtonHienThi;
    private javax.swing.JButton jButtonQuayLai;
    private javax.swing.JButton jButtonTimKiem;
    private javax.swing.JButton jButtonXoaTrang;
    private javax.swing.JComboBox<String> jComboBoxDiemDG;
    private javax.swing.JComboBox<String> jComboBoxHanhKiem;
    private javax.swing.JComboBox<String> jComboBoxLop;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtDiemDG;
    private javax.swing.JTextField txtDiemTL;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaLop;
    private javax.swing.JTextField txtMaSV;
    // End of variables declaration//GEN-END:variables
}
