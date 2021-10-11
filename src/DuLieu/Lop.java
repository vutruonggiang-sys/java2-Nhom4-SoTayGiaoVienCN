/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuLieu;

import DuLieu.Khoa;
import DuLieu.GiaoVien;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class Lop {
    private String maLop , tenLop ;
    private Khoa khoa;
    private GiaoVien giaoVien;

    public Lop(String maLop, String tenLop, Khoa khoa, GiaoVien giaoVien) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.khoa = khoa;
        this.giaoVien = giaoVien;
    }

    public Lop(String maLop) {
        this.maLop = maLop;
    }

    public Lop() {
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }

    public GiaoVien getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVien giaoVien) {
        this.giaoVien = giaoVien;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.maLop);
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
        final Lop other = (Lop) obj;
        if (!Objects.equals(this.maLop, other.maLop)) {
            return false;
        }
        return true;
    }
    
}
