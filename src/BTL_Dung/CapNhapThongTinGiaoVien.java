/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTL_Dung;

import java.sql.ResultSet;

/**
 *
 * @author TONG LE TU VAN
 */
public class CapNhapThongTinGiaoVien {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Connect cn=new Connect();
        cn.getConnect();
        ResultSet r;
        int t;
        
        //select
        
        r=cn.executeQuery("Select * from GiaoVien");
        while(r.next())
        {
            System.out.println(r.getString("MaGiaoVien") + "  " + r.getString("HoTen") + "  " +r.getString("QuocTich") + "  " +
                    r.getString("Xa") + "  " +r.getString("Quan") + "  " +r.getString("Tinh") + "  " +
                    r.getString("SoCMND") + "  " +r.getString("SoDienThoai"));
        }
      
        //update
        
        t=cn.executeUpdate("update GiaoVien set HoTen=Nguyễn Văn A where MaGiaoVien='GV01'");
        cn.getConnect();
        r=cn.executeQuery("select * from GiaoVien ");
        while(r.next())
        {
            System.out.println(r.getString("MaGiaoVien") + "  " + r.getString("HoTen") + "  " +r.getString("QuocTich") + "  " +
                    r.getString("Xa") + "  " +r.getString("Quan") + "  " +r.getString("Tinh") + "  " +
                    r.getString("SoCMND") + "  " +r.getString("SoDienThoai"));
        }
        cn.closeConnect();
    }
    
}
