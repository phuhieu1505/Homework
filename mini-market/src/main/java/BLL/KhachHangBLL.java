package main.java.BLL;
import main.java.DTO.KhachHangDTO;
import main.java.DAL.KhachHangDAL;
import java.util.Vector;
public class KhachHangBLL {
    KhachHangDAL khDAL = new KhachHangDAL();

    public Vector<KhachHangDTO> getAllKhachHang(){
        return khDAL.getAllKhachHang();
    }

    public String addKhachHang(KhachHangDTO kh){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if(khDAL.hasMaKH(kh.getMaKH()))
            return "Mã khách hàng đã tồn tại";
        if(khDAL.addKhachHang(kh))
            return "Thêm thành công!";
        return "Thêm thất bại!";
    }

    public String deleteKhachHang(KhachHangDTO kh){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if(khDAL.deleteKhachHang(kh.getMaKH()))
            return "Xóa khách hàng thành công!";
        return "Xóa thất bại!";
    }

    public String updateKhachHang(KhachHangDTO kh,String MaKH){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if(khDAL.updateKhachHang(kh,MaKH))
            return "Cập nhật thành công!";
        return "Cập nhật thất bại!";
    }

    public String searchMaKH(Vector<KhachHangDTO> arr, String MaKH){
        // BLL xử lý logic và đưa xuống DAL để DAL add dữ liệu vào csdl
        if(!(khDAL.hasMaKH(MaKH)))
            return "Nhân viên không tồn tại";
        if(khDAL.searchMaKH(arr, MaKH))
            return null;
        return "Không tìm thấy khách hàng";
    }
}
