/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuLieu;

import java.util.Objects;

/**
 *
 * @author Admin
 */
public class CongViec implements Comparable<CongViec>{
    private String maCongViec ,ndToChucHD,trangThai,ngayThucHien  ;
    private GiaoVien giaoVien;
    

    public CongViec(String maCongViec, String ndToChucHD, String trangThai,String ngayThucHien, GiaoVien giaoVien) {
        this.maCongViec = maCongViec;
        this.ndToChucHD = ndToChucHD;
        this.trangThai = trangThai;
        this.ngayThucHien=ngayThucHien;
        this.giaoVien = giaoVien;
    }
    

    public CongViec(String maCongViec) {
        this.maCongViec = maCongViec;
    }

    public CongViec() {
    }

    public String getMaCongViec() {
        return maCongViec;
    }

    public void setMaCongViec(String maCongViec) {
        this.maCongViec = maCongViec;
    }

    public String getNdToChucHD() {
        return ndToChucHD;
    }

    public void setNdToChucHD(String ndToChucHD) {
        this.ndToChucHD = ndToChucHD;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public GiaoVien getGiaoVien() {
        return giaoVien;
    }

    public String getNgayThucHien() {
        return ngayThucHien;
    }

    public void setNgayThucHien(String ngayThucHien) {
        this.ngayThucHien = ngayThucHien;
    }
    
    public void setGiaoVien(GiaoVien giaoVien) {
        this.giaoVien = giaoVien;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.maCongViec);
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
        final CongViec other = (CongViec) obj;
        if (!Objects.equals(this.maCongViec, other.maCongViec)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(CongViec o) {
         return ngayThucHien.compareToIgnoreCase(o.ngayThucHien);
    }
    
}
