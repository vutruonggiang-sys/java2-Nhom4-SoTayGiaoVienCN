/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuLieu;

/**
 *
 * @author Admin
 */
public class Nguoi {
    protected String hoTen , quocTich , xa , huyen , tinh , soCMND , sdt , maBHYT;

    public Nguoi(String hoTen, String quoctich, String xa, String huyen, String tinh, String soCMND, String sdt, String maBHYT) {
        this.hoTen = hoTen;
        this.quocTich = quoctich;
        this.xa = xa;
        this.huyen = huyen;
        this.tinh = tinh;
        this.soCMND = soCMND;
        this.sdt = sdt;
        this.maBHYT = maBHYT;
    }

    public Nguoi() {
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quoctich) {
        this.quocTich = quoctich;
    }

    public String getXa() {
        return xa;
    }

    public void setXa(String xa) {
        this.xa = xa;
    }

    public String getHuyen() {
        return huyen;
    }

    public void setHuyen(String huyen) {
        this.huyen = huyen;
    }

    public String getTinh() {
        return tinh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMaBHYT() {
        return maBHYT;
    }

    public void setMaBHYT(String maBHYT) {
        this.maBHYT = maBHYT;
    }
    
}
