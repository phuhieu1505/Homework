package main.java.BLL;
import main.java.DAL.HoaDonDAL;
import main.java.DTO.HoaDonDTO;

import java.util.Vector;
public class HoaDonBLL {
    HoaDonDAL hdDal = new HoaDonDAL();

    public Vector<HoaDonDTO> getAllHoaDon(){
        return hdDal.getAllHoaDon();
    }

    public String addHoaDon(HoaDonDTO hd){
        if(hdDal.hasHoaDonID(hd.getHD_ID()))
            return "Ma hoa don da ton tai";
        if(hdDal.addHoaDon(hd))
            return "Them thanh cong";
        return "Them that bai";
    }
/*
    public String deleteHoaDon(HoaDonDTO hd){
        if(hdDal.deleteHoaDon(hd.getHD_ID()))
            return "Xoa thanh cong";
        return "Xoa that bai !";
    }
*/
    public String updateHoaDon(HoaDonDTO hd, String mahd){
        if(hdDal.updateHoaDon(hd,mahd))
            return "Cap nhat thanh cong";
        return "CAp nhat that bai !";
    }

    public String searchMaHD(Vector<HoaDonDTO> arr, String MaHD){
        if(!(hdDal.hasHoaDonID(MaHD)))
            return "Hóa đơn không tồn tại ";
        if(hdDal.searchMaHD(arr, MaHD))
            return null;
        return "Không tìm thấy hóa đơn khách hàng";
    }
}
