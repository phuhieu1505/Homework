package main.java.BLL;

import main.java.DAL.NhanVienDAL;
import main.java.DAL.PhieuNhapDAL;
import main.java.DTO.NhanVienDTO;
import main.java.DTO.PhieuNhapDTO;

import java.util.Vector;

public class PhieuNhapBLL {
    PhieuNhapDAL pnDAL = new PhieuNhapDAL();
    public Vector<PhieuNhapDTO> getAllPhieuNhap(){
        return pnDAL.getAllPhieuNhap();
    }
    public String addPhieuNhap(PhieuNhapDTO pn){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (pnDAL.hasMaPN(pn.getMaPN()))
            return "Mã phiếu nhập đã tồn tại";
        if (pnDAL.addPhieuNhap(pn))
            return "Thêm phiếu nhập thành công";
        return "Thêm phiếu nhập thất bại";
    }


    public String updatePhieuNhap(PhieuNhapDTO pn, String MaPN){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (pnDAL.updatePhieuNhap(pn, MaPN))
            return "Cập nhật phiếu nhập thành công";
        return "Cập nhật phiếu nhập thất bại";
    }

    public boolean searchmaPN(Vector<PhieuNhapDTO> pn_arr, String MaPN){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if(!(pnDAL.hasMaPN(MaPN)))
            return false;
        if (pnDAL.searchMaPN(pn_arr, MaPN))
            return true;
        return false;
    }

}
