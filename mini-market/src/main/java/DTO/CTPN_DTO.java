package main.java.DTO;

public class CTPN_DTO {
    private String MaPN, MaSP, TenSP;
    private Integer SoLuong;
    private Float DonGia, ThanhTien;

    public String getMaPN(){
        return MaPN;
    }
    public void setMaPN(String MaPN){
        this.MaPN = MaPN;
    }

    public String getMaSP(){
        return MaSP;
    }
    public void setMaSP(String MaSP){
        this.MaSP = MaSP;
    }

    public String getTenSP(){
        return TenSP;
    }
    public void setTenSP(String TenSP){
        this.TenSP = TenSP;
    }

    public Integer getSoLuong(){
        return SoLuong;
    }
    public void setSoLuong(Integer SoLuong){
        this.SoLuong = SoLuong;
    }

    public Float getDonGia(){
        return DonGia;
    }
    public void setDonGia(Float DonGia){
        this.DonGia = DonGia;
    }

    public Float getThanhTien(){
        return ThanhTien;
    }
    public void setThanhTien(Float ThanhTien){
        this.ThanhTien = ThanhTien;
    }


}
