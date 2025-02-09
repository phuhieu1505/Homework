package main.java.BLL;


import main.java.DAL.NCCDAL;
import main.java.DTO.NCCDTO;

import java.util.Vector;

public class NCCBLL {
    NCCDAL nccDAL = new NCCDAL();
    public Vector<NCCDTO> getAllNCC(){
        return nccDAL.getAllNCC();
    }

    public String addNCC(NCCDTO ncc) {
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (nccDAL.hasMaNCC(ncc.getMaNCC()))
            return "Mã NCC đã tồn tại";
        if (nccDAL.addNCC(ncc))
            return "Thêm thành công";
        return "Thêm thất bại";
    }

    public String deleteNCC(NCCDTO ncc){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (nccDAL.deleteNCC(ncc.getMaNCC()))
            return "Xóa NCC thành công";
        return "Xóa NCC thất bại";
    }

    public String updateNCC(NCCDTO ncc, String MaNCC){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if (nccDAL.updateNCC(ncc, MaNCC))
            return "Cập nhật NCC thành công";
        return "Cập nhật NCC thất bại";
    }

    public String searchNCC(Vector<NCCDTO> ncc_arr, String MaNCC){
        if(!(nccDAL.hasMaNCC(MaNCC))) return "Mã NCC không tồn tại";
        if(nccDAL.searchMaNCC(ncc_arr,MaNCC)) return "Đã tìm thấy NCC";
        return "Không tìm thấy NCC";
    }
}
