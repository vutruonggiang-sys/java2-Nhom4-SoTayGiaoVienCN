/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTL_Dung;

import btl_giang.DangNhapForm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author TONG LE TU VAN
 */
public class Connect {
    Connection con=null;    //khoi tao Connection bang null
    Statement sta=null;     //khoi tao Statement de thuc thi
    ResultSet res=null;     //khoi tao ResultSet de chua du lieu
    
    //ket noi den database
   
    public void getConnect() 
    {
        try{
            String url="jdbc:derby://localhost:1527/BaiTL";
            String user="Nhom4";
            String pass="123";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=(Connection)DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException|SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Khong the ket noi voi database \n"+e);
        }
        
    }
    protected  Statement getStatement()throws Exception
    {
        if(this.sta==null || this.sta.isClosed())
        {
            //khoi tao statement moi
            this.sta=this.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
        }
        return this.sta;
    }
    public ResultSet executeQuery(String qr) throws Exception
    {
        try{
            //thuc thi cau lenh
            this.res=getStatement().executeQuery(qr);
        }
        catch(Exception e)
        {
            throw new Exception("Cau lenh Query khong duoc thuc thi");
        }
        return this.res;
        //van mo ket noi de lay thong tin
    }
   
     
    public int executeUpdate(String qr) throws Exception
    {
        int ketqua=0;
        try{
            // thuc thi cau lenh
            ketqua=getStatement().executeUpdate(qr);
        }
        catch(Exception e)
        {
            throw new Exception("Loi queryUpdate khong duoc thuc hien");
        }
        finally{
            this.con.close();
        }
        return ketqua;
    }
    
    public ResultSet GetData(String jtable) throws SQLException
    {
        ResultSet kq=null;
        Statement st=con.createStatement();
        kq=st.executeQuery("select * from GVien where MaGiaoVien='"+DangNhapForm.magv+"'");
        return kq;
    }
    public int update(String maGV, String hoTen, String quocTich, String xa, String quan, String tinh, String soCMND, String sdt) throws Exception
    {
        int t;
        t=getStatement().executeUpdate("update GVien set MaGiaoVien='"+maGV+"',HoTen='"+hoTen+"',QuocTich='"+quocTich+"',Xa='"+xa+"',Quan='"+quan+"',Tinh='"+tinh+"',SoCMND='"+soCMND+"',SoDienThoai='"+sdt+"' where MaGiaoVien='"+maGV+"'");
        return   t;
    }

    public void closeConnect() throws SQLException
    {
        //dong tu nho den lon
        if(this.res!=null&&!this.res.isClosed())
        try{
            this.res.close();
            this.res=null;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Loi dong ket qua");
        }
        
        if(this.sta!=null&&!this.sta.isClosed())
        try{
            this.sta.close();
            this.sta=null;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Loi dong lenh thuc thi");
        }
        
        if(this.con!=null&&!this.con.isClosed())
        try{
            this.con.close();
            this.con=null;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Loi dong ket noi");
        }
        
    }

    

}
