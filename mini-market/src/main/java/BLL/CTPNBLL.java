package main.java.BLL;

import main.java.DAL.CTPNDAL;
import main.java.DTO.CTPN_DTO;
import main.java.DTO.PhieuNhapDTO;

import java.util.Vector;

public class CTPNBLL {
    CTPNDAL ctpnDAL = new CTPNDAL();
    public Vector<CTPN_DTO> getAllCTPN(){
        return ctpnDAL.getAllCTPN();
    }
    public String addCTPN(CTPN_DTO ctpn){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (ctpnDAL.hasMaPN(ctpn.getMaPN()) && ctpnDAL.hasMaSP(ctpn.getMaSP()))
            return "Mã phiếu nhập và mã sản phẩm đã tồn tại";
        if (ctpnDAL.addCTPN(ctpn))
            return "Thêm CTPN thành công";
        return "Thêm CTPN thất bại";
    }

    public String updateCTPN(CTPN_DTO ctpn, String MaPN){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (ctpnDAL.updateCTPN(ctpn, MaPN))
            return "Cập nhật phiếu nhập thành công";
        return "Cập nhật phiếu nhập thất bại";
    }

    public boolean searchCTPN(Vector<CTPN_DTO> pn_arr, String MaPN){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if(!(ctpnDAL.hasMaPN(MaPN)))
            return false;
        if (ctpnDAL.searchCTPN(pn_arr, MaPN))
            return true;
        return false;
    }
}
