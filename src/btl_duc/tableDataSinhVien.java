/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_duc;

import DuLieu.SinhVien;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class tableDataSinhVien extends AbstractTableModel{
    private List<SinhVien> list;
    private List<String> tieuDe= Arrays.asList("Mã SV","Họ Tên","Quốc Tịch","Số CMND","SDT","Mã Lớp","Xã","Huyện","Tỉnh","Mã BHYT","Điểm Đánh Giá");
    public tableDataSinhVien(List<SinhVien> list){
        this.list=list;
    }
    
    @Override
    public int getRowCount() {
        return list.size(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return tieuDe.size();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SinhVien sinhVien= list.get(rowIndex);
        switch(columnIndex){
            case 0: return sinhVien.getMaSV();
            case 1: return sinhVien.getHoTen();
            case 2: return sinhVien.getQuocTich();   
            case 3: return sinhVien.getSoCMND();
            case 4: return sinhVien.getSdt();
            case 5: return sinhVien.getLop().getMaLop();
            case 6: return sinhVien.getXa();
            case 7: return sinhVien.getHuyen();
            case 8: return sinhVien.getTinh();
            case 9: return sinhVien.getMaBHYT();
            default:return sinhVien.getDiemDG();
        }
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnName(int column) {
        return tieuDe.get(column); //To change body of generated methods, choose Tools | Templates.
    }
    public List<SinhVien> getList(){
        return list;
    }

    public void setList(List<SinhVien> list) {
        this.list = list;
    }
    public SinhVien getRowAt(int row){
        return list.get(row);
    }
}
