package main.java.BLL;
import main.java.DAL.NhanVienDAL;
import main.java.DTO.NhanVienDTO;

import java.sql.*;
import java.util.Vector;

public class NhanVienBLL {
    NhanVienDAL nvDAL = new NhanVienDAL();
    public Vector<NhanVienDTO> getAllNhanVien(){
        return nvDAL.getAllNhanVien();
    }

    public String addNhanVien(NhanVienDTO nv){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (nvDAL.hasMaNV(nv.getMaNV()))
            return "Mã nhân viên đã tồn tại";
        if (nvDAL.addNhanVien(nv))
            return "Thêm nhân viên thành công";
        return "Thêm nhân viên thất bại";
    }

    public String deleteNhanVien(NhanVienDTO nv){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if(nvDAL.hasMaNVinTK(nv.getMaNV()))
            return "Bạn phải xóa tài khoản có mã nhân viên này trước để tránh xung đột!";
        if (nvDAL.deleteNhanVien(nv.getMaNV()))
            return "Xóa nhân viên thành công";
        return "Xóa nhân viên thất bại";
    }

    public String updateNhanVien(NhanVienDTO nv, String MaNV){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (nvDAL.updateNhanVien(nv, MaNV))
            return "Cập nhật viên thành công";
        return "Cập nhật nhân viên thất bại";
    }

    public String searchmaNV(Vector<NhanVienDTO> nv_arr, String MaNV){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if(!(nvDAL.hasMaNV(MaNV)))
            return "Nhân viên không tồn tại";
        if (nvDAL.searchMaNV(nv_arr, MaNV))
            return null;
        return "Không tìm thấy nhân viên";
    }
}
