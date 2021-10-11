/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_duc;

import DuLieu.CongViec;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class tableDataCongViec extends AbstractTableModel{
    private List<CongViec> list;
    private List<String> tieuDe= Arrays.asList("Mã Công Việc","Ngày Thực Hiện","Nội Dung","Trạng Thái");
    
    public tableDataCongViec(List<CongViec> list){
        this.list=list;
    }
    @Override
    public int getRowCount() {
        return list.size();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return tieuDe.size();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       CongViec congViec= list.get(rowIndex);
       switch(columnIndex){
            case 0: return congViec.getMaCongViec();
            case 1: return congViec.getNgayThucHien();
            case 2: return congViec.getNdToChucHD();
            default: return congViec.getTrangThai();
       }
    }

    @Override
    public String getColumnName(int column) {
        return tieuDe.get(column);//To change body of generated methods, choose Tools | Templates.
    }
}
