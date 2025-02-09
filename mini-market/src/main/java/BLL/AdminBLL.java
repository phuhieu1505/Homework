package main.java.BLL;

import main.java.DAL.NhanVienDAL;
import main.java.DTO.AdminDTO;
import main.java.DAL.AdminDAL;
import main.java.DTO.NhanVienDTO;

import java.util.Vector;
public class AdminBLL {
    AdminDAL tkDAL = new AdminDAL();
    public Vector<AdminDTO> getAllTK(){
        return tkDAL.getAllTK();
    }

    public String addTaiKhoan(AdminDTO tkn) {
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (tkDAL.hasMaTK(tkn.getMaTK()))
            return "Mã NV đã tồn tại";
        if (tkDAL.addTaiKhoan(tkn))
            return "Thêm thành công";
        return "Thêm thất bại";
    }

    public String deleteTaiKhoan(AdminDTO tk){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (tkDAL.deleteTaiKhoan(tk.getMaTK()))
            return "Xóa tài khoản thành công";
        return "Xóa tài khoản thất bại";
    }

    public String updateTaiKhoan(AdminDTO tk, String MaTK_old){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (tkDAL.updateTaiKhoan(tk, MaTK_old))
            return "Cập nhật tài khoản thành công";
        return "Cập nhật tài khoản thất bại";
    }

    public boolean CheckLogin(String MaNV, String MatKhau){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        System.out.println("Đang check đăng nhập");
        if (tkDAL.hasMaNVinTK(MaNV) && tkDAL.checkMatKhau(MatKhau)) {
            return true;
        }
        return false;
    }

}

