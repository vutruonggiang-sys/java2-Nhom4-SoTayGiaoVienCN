/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_giang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author TruongGiang
 */
public class Connect {
    public Connection con=null; 
    public  void getConnect() 
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
            System.out.println("Lỗi là : không kết nối được cơ sở dữ liệu");
        }
        
    }
    
}
