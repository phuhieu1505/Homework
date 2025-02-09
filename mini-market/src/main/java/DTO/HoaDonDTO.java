package main.java.DTO;

import java.sql.Date;

public class HoaDonDTO {
    private String HD_ID;

    private String KH;

    private String NV;

    private float TongTien;
    private Date NgayTao;
    private String TrangThai;


    public String getHD_ID(){
        return HD_ID;
    }

    public void setHD_ID(String HD_ID){
        this.HD_ID = HD_ID;
    }

    public String getKH(){ return KH; }

    public void setKH(String KH){ this.KH = KH; }

    public String getNV(){ return NV; }

    public void setNV(String NV){ this.NV = NV; }

    public float getTongTien(){return TongTien;}

    public void setTongTien(float TongTien){this.TongTien = TongTien;}

    public Date getNgayTao(){ return NgayTao; }

    public void setNgayTao(Date NgayTao){ this.NgayTao = NgayTao; }

    public String getTrangThai(){ return TrangThai; }

    public void setTrangThai(String TrangThai){ this.TrangThai = TrangThai; }


}
