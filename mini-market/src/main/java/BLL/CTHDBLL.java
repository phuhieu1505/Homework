package main.java.BLL;
import main.java.DTO.CTHDDTO;
import main.java.DAL.CTHDDAL;
import java.util.Vector;
public class CTHDBLL {
    CTHDDAL cthddal = new CTHDDAL();

    public Vector<CTHDDTO> getAllCTHD(){ return cthddal.getAllCTHD(); }

    public String addCTHD(CTHDDTO cthd){
        if(cthddal.hasHoaDonID(cthd.getHD()))
            return "Mã hóa đơn đã tồn tại";
        if(cthddal.addCTHD(cthd))
            return "Thêm thành công";
        return "Thêm thất bại";
    }

    public String updateCTHD(CTHDDTO cthd, String ct){
        if(cthddal.updateCTHD(cthd,ct))
            return "Thêm thành công";
        return "Thêm thất bại";
    }

    public String searchCTHD(Vector<CTHDDTO> arrHD, String mahd){
        if(!cthddal.hasHoaDonID(mahd)) {
            return "Hóa đơn không tồn tại";
        }
        if(cthddal.searchCTHD(arrHD, mahd))
            return "Có CTHD";
        return "Không tìm thấy hóa đơn";
    }
}
