package main.java.DTO;

public class ThongKeDTO {
    private String MaHD, MaSP, MaPN;
    private Float TongTien, TongTienPN;
    private Integer SLmua, SoLuong;

    public String getMaHD(){
        return MaHD;
    }
    public void setMaHD(String MaHD){
        this.MaHD = MaHD;
    }

    public Float getTongTien(){
        return TongTien;
    }
    public void setTongTien(Float TongTien){
        this.TongTien = TongTien;
    }

    public String getMaSP(){
        return MaSP;
    }
    public void setMaSP(String MaSP){
        this.MaSP = MaSP;
    }

    public Integer getSLmua(){
        return SLmua;
    }
    public void setSLmua(Integer SLmua){
        this.SLmua = SLmua;
    }

    public String getMaPN(){
        return MaPN;
    }
    public void setMaPN(String MaPN){
        this.MaPN = MaPN;
    }

    public Float getTongTienPN(){
        return TongTienPN;
    }
    public void setTongTienPN(Float TongTienPN){
        this.TongTienPN = TongTienPN;
    }

    public Integer getSoLuong(){
        return SoLuong;
    }
    public void setSoLuong(Integer SoLuong){
        this.SoLuong = SoLuong;
    }
}
