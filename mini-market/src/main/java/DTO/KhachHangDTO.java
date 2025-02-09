package main.java.DTO;

public class KhachHangDTO {
    private String MaKH;
    private String TenKH;
    private String sdtKH;
    private String KHEmail;


    public String getMaKH(){
        return MaKH;
    }

    public void setMaKH(String MaKH){
        this.MaKH = MaKH;
    }

    public String getTenKH(){
        return TenKH;
    }

    public void setTenKH(String TenKH){
        this.TenKH = TenKH;
    }

    public String getSdtKH() { return sdtKH; }

    public void setSdtKH(String sdtKH){ this.sdtKH = sdtKH; }

    public String getKHEmail(){
        return KHEmail;
    }

    public void setKHEmail(String KHEmail){
        this.KHEmail = KHEmail;
    }
}
