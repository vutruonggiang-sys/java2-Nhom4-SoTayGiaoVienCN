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
public class GiaoVien extends Nguoi{
    private String maGV,matKhau ;

    public GiaoVien(String maGV, String matKhau, String hoTen, String quoctich, String xa, String huyen, String tinh, String soCMND, String sdt, String maBHYT) {
        super(hoTen, quoctich, xa, huyen, tinh, soCMND, sdt, maBHYT);
        this.maGV = maGV;
        this.matKhau = matKhau;
    }

    public GiaoVien(String maGV) {
        this.maGV = maGV;
    }

    public GiaoVien() {
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.maGV);
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
        final GiaoVien other = (GiaoVien) obj;
        if (!Objects.equals(this.maGV, other.maGV)) {
            return false;
        }
        return true;
    }
    
}
