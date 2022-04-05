/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OBJ;

/**
 *
 * @author ADMIN
 */
public class KhachHang {

    private String maKH;
    private String tenKH;
    private String gioiTinhKH;
    private String emailKH;
    private String sdtKH;
    private String diaChiKH;
    private int trangThaiKH;

    public KhachHang() {
    }

    public KhachHang(String maKH, String tenKH, String gioiTinhKH, String emailKH, String sdtKH, String diaChiKH, int trangThaiKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.gioiTinhKH = gioiTinhKH;
        this.emailKH = emailKH;
        this.sdtKH = sdtKH;
        this.diaChiKH = diaChiKH;
        this.trangThaiKH = trangThaiKH;
    }

    

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getGioiTinhKH() {
        return gioiTinhKH;
    }

    public void setGioiTinhKH(String gioiTinhKH) {
        this.gioiTinhKH = gioiTinhKH;
    }

    public String getEmailKH() {
        return emailKH;
    }

    public void setEmailKH(String emailKH) {
        this.emailKH = emailKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getDiaChiKH() {
        return diaChiKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        this.diaChiKH = diaChiKH;
    }

    public int getTrangThaiKH() {
        return trangThaiKH;
    }

    public void setTrangThaiKH(int trangThaiKH) {
        this.trangThaiKH = trangThaiKH;
    }

   

    

}
