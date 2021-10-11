/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuLieu;

import DuLieu.Nguoi;
import DuLieu.Lop;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class SinhVien extends Nguoi{
    private String maSV ,hanhKiem;
    private Lop lop;
    private float diemTichLuy,diemDG;

    public SinhVien(String maSV, Lop lop, float diemTichLuy, float diemDG, String hoTen, String quoctich, String xa, String huyen, String tinh, String soCMND, String sdt, String maBHYT) {
        super(hoTen, quoctich, xa, huyen, tinh, soCMND, sdt, maBHYT);
        this.maSV = maSV;
        this.lop = lop;
        this.diemTichLuy = diemTichLuy;
        this.diemDG = diemDG;
    }

    public SinhVien(String maSV, Lop lop, String hoTen, String quoctich, String xa, String huyen, String tinh, String soCMND, String sdt, String maBHYT) {
        super(hoTen, quoctich, xa, huyen, tinh, soCMND, sdt, maBHYT);
        this.maSV = maSV;
        this.lop = lop;
    }
    
    public SinhVien(String maSV) {
        this.maSV = maSV;
    }

    public SinhVien() {
    }

    public String getHanhKiem() {
        return hanhKiem;
    }

    public void setHanhKiem(String hanhKiem) {
        this.hanhKiem = hanhKiem;
    }
    
    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }

    public float getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(float diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    public float getDiemDG() {
        return diemDG;
    }

    public void setDiemDG(float diemDG) {
        this.diemDG = diemDG;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.maSV);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SinhVien other = (SinhVien) obj;
        if (!Objects.equals(this.maSV, other.maSV)) {
            return false;
        }
        return true;
    }
    
}
