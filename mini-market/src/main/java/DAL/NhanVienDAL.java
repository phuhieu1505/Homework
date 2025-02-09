package main.java.DAL;

import main.java.DTO.NhanVienDTO;
import java.sql.*;
import java.util.Vector;

public class NhanVienDAL {
    private Connection con;

    public boolean openConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String DBurl = "jdbc:sqlserver://localhost\\PD:1433;database=mini_market;encrypt=false;";
            String username = "sa";
            String password = "12345678";
            con = DriverManager.getConnection(DBurl, username, password);
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public void closeConnection(){
        try {
            if(con != null){
                con.close();
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    public Vector<NhanVienDTO> getAllNhanVien(){
        Vector<NhanVienDTO> nv_arr = new Vector<NhanVienDTO>();
        if(openConnection()){
            try {
                String sql = "Select * from NhanVien";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    NhanVienDTO nv = new NhanVienDTO();
                    nv.setMaNV(rs.getString("MaNV"));
                    nv.setTenNV(rs.getString("TenNV"));
                    nv.setSdtNV(rs.getString("SdtNV"));
                    nv.setEmailNV(rs.getString("EmailNV"));
                    nv.setChucvu(rs.getString("Chucvu"));
                    nv_arr.add(nv);
                }
            } catch (SQLException ex) {
                System.out.print("Lỗi ở hàm getAllNV trong class NhanVienDAL");
            }  finally {
                closeConnection();
            }
        }
        return nv_arr;
    }

    public boolean addNhanVien(NhanVienDTO nv) {
        boolean result = false;
        if (openConnection()) {
            try {
                String sql = "Insert into NhanVien values(?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, nv.getMaNV());
                stmt.setString(2, nv.getTenNV());
                stmt.setString(3, nv.getSdtNV());
                stmt.setString(4, nv.getEmailNV());
                stmt.setString(5, nv.getChucvu());
                if (stmt.executeUpdate() >= 1)
                    result = true;
            } catch (SQLException ex) {
                System.out.println("Lỗi ở hàm add của class NhanVienDAL");
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean hasMaNV(String MaNV){
        boolean result = false;
        if (openConnection()) {
            try {
                //dùng câu truy vấn để lấy bản ghi của cột MaTK trùng với MaTK truyền vào
                String sql = "SELECT * FROM NhanVien WHERE MaNV = '" + MaNV + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //Nếu câu truy vấn trả veef ít nhất 1 bản ghi thì cập nhật result là true
                result = rs.next();
            } catch (SQLException ex){
                System.out.println("Lỗi ở hàm hasMaNV của class NhanVienDAL");
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean hasMaNVinTK(String MaNV){
        boolean result = false;
        if (openConnection()) {
            try {
                //dùng câu truy vấn để lấy bản ghi của cột MaTK trùng với MaTK truyền vào
                String sql = "SELECT * FROM TaiKhoan WHERE MaNV = '" + MaNV + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //Nếu câu truy vấn trả veef ít nhất 1 bản ghi thì cập nhật result là true
                result = rs.next();
            } catch (SQLException ex){
                System.out.println("Lỗi ở hàm hasMaNVinTK của class NhanVienDAL");
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean deleteNhanVien(String MaNV){
        boolean result = false;
        if (openConnection()) {
            try {
                //dùng câu truy vấn để lấy bản ghi của cột MaTK trùng với MaTK truyền vào
                String sql = "DELETE FROM NhanVien WHERE MaNV = '" + MaNV + "'";
                Statement stmt = con.createStatement();
                int rowCount = stmt.executeUpdate(sql);
                //Nếu câu truy vấn trả veef ít nhất 1 bản ghi thì cập nhật result là true
                if (rowCount > 0)
                    result = true;
            } catch (SQLException ex){
                System.out.println("Lỗi ở hàm deleteNhanVien của class NhanVienDAL");
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean updateNhanVien(NhanVienDTO nv, String MaNV){
        boolean result = false;
        if (openConnection()) {
            try {
                System.out.println("MaNV = " + MaNV);
                //dùng câu truy vấn để lấy bản ghi của cột MaTK trùng với MaTK truyền vào
                String sql = "UPDATE NhanVien SET MaNV = ?, TenNV = ?, SdtNV = ?, EmailNV = ?, Chucvu = ? " +
                        "WHERE MaNV = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, nv.getMaNV());
                stmt.setString(2, nv.getTenNV());
                stmt.setString(3, nv.getSdtNV());
                stmt.setString(4, nv.getEmailNV());
                stmt.setString(5, nv.getChucvu());
                stmt.setString(6, MaNV);
                int rowCount = stmt.executeUpdate();
                System.out.print(rowCount);
                //Nếu câu truy vấn trả veef ít nhất 1 bản ghi thì cập nhật result là true
                if (rowCount > 0)
                    result = true;
            } catch (SQLException ex){
                ex.printStackTrace();
                System.out.println("Lỗi ở hàm updateNhanVien của class NhanVienDAL");
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean searchMaNV(Vector<NhanVienDTO> nv_arr, String MaNV){
        boolean result = false;
        if (openConnection()) {
            try {
                //dùng câu truy vấn để lấy bản ghi của cột MaTK trùng với MaTK truyền vào
                String sql = "SELECT * FROM NhanVien WHERE MaNV = '" + MaNV + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    NhanVienDTO nv = new NhanVienDTO();
                    nv.setMaNV(rs.getString("MaNV"));
                    nv.setTenNV(rs.getString("TenNV"));
                    nv.setSdtNV(rs.getString("SdtNV"));
                    nv.setEmailNV(rs.getString("EmailNV"));
                    nv.setChucvu(rs.getString("Chucvu"));
                    nv_arr.add(nv);
                }
                if(nv_arr.size() > 0)
                    result = true;
            } catch (SQLException ex){
                System.out.println("Lỗi ở hàm searchMaNV của class NhanVienDAL");
            } finally {
                closeConnection();
            }
        }
        return result;
    }

}
