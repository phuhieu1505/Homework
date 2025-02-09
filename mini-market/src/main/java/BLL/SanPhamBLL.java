package main.java.BLL;

import main.java.DAL.SanPhamDAL;
import main.java.DTO.SanPhamDTO;

import java.util.Vector;

public class SanPhamBLL {
    SanPhamDAL spDAL = new SanPhamDAL();
    public Vector<SanPhamDTO> getAllSanPham(){
        return spDAL.getAllSanPham();
    }

    public String addSP(SanPhamDTO sp) {
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (spDAL.hasMaSP(sp.getMaSP()))
            return "Mã SP đã tồn tại";
        if (spDAL.addSP(sp))
            return "Thêm thành công";
        return "Thêm thất bại";
    }

    public String deleteSP(SanPhamDTO tk){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (spDAL.deleteSP(tk.getMaSP()))
            return "Xóa sản phẩm thành công";
        return "Xóa sản phẩm thất bại";
    }

    public String updateSP(SanPhamDTO sp, String MaSP){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (spDAL.updateSP(sp, MaSP))
            return "Cập nhật sản phẩm thành công";
        return "Cập nhật sản phẩm thất bại";
    }

    public String searchMaSP(Vector<SanPhamDTO> sp_arr, String MaSP){
        if(!(spDAL.hasMaSP(MaSP))) return "Mã sản phẩm không tồn tại";
        if(spDAL.searchMaSP(sp_arr,MaSP)) return "Đã tìm thấy Sp";
        return "Không tìm thấy sản phẩm";
    }

    public boolean addSPtoCart(String MaSP){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (spDAL.addSPtoCart(MaSP))
            return true;
        return false;
    }

    public boolean deleteSPfromCart(String MaSP){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (spDAL.deleteSPfromCart(MaSP))
            return true;
        return false;
    }

}
