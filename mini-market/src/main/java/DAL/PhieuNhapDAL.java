package main.java.DAL;

import main.java.DTO.NhanVienDTO;
import main.java.DTO.PhieuNhapDTO;
import java.sql.*;
import java.util.Vector;
public class PhieuNhapDAL {
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

    public Vector<PhieuNhapDTO> getAllPhieuNhap() {
        Vector<PhieuNhapDTO> pn_arr = new Vector<PhieuNhapDTO>();
        if (openConnection()) {
            try {
                String sql = "Select * from PhieuNhap";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    PhieuNhapDTO pn = new PhieuNhapDTO();
                    pn.setMaPN(rs.getString("MaPN"));
                    pn.setNgayNhap(rs.getDate("NgayNhap"));
                    pn.setMaNCC(rs.getString("MaNCC"));
                    pn.setTongTien(rs.getFloat("TongTien"));
                    pn.setTrangThai(rs.getString("TrangThai"));
                    pn_arr.add(pn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.print("Lỗi ở hàm getAllPhieuNhap trong class PhieuNhapDAL");
            } finally {
                closeConnection();
            }
        }
        return pn_arr;
    }

    public boolean addPhieuNhap(PhieuNhapDTO pn) {
        boolean result = false;
        if (openConnection()) {
            try {
                String sql = "Insert into PhieuNhap values(?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, pn.getMaPN());
                stmt.setDate(2, pn.getNgayNhap());
                stmt.setString(3, pn.getMaNCC());
                stmt.setFloat(4, pn.getTongTien());
                stmt.setString(5, pn.getTrangThai());
                if (stmt.executeUpdate() >= 1)
                    result = true;
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Lỗi ở hàm add của class PhieuNhapDAL");
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean hasMaPN(String MaPN){
        boolean result = false;
        if (openConnection()) {
            try {
                //dùng câu truy vấn để lấy bản ghi của cột MaTK trùng với MaTK truyền vào
                String sql = "SELECT * FROM PhieuNhap WHERE MaPN = '" + MaPN + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //Nếu câu truy vấn trả veef ít nhất 1 bản ghi thì cập nhật result là true
                result = rs.next();
            } catch (SQLException ex){
                System.out.println("Lỗi ở hàm hasMaPN của class PhieuNhapDAL");
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean hasMaNCC(String MaNCC){
        boolean result = false;
        if (openConnection()) {
            try {
                //dùng câu truy vấn để lấy bản ghi của cột MaTK trùng với MaTK truyền vào
                String sql = "SELECT * FROM NCC WHERE MaNCC = '" + MaNCC + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //Nếu câu truy vấn trả veef ít nhất 1 bản ghi thì cập nhật result là true
                result = rs.next();
            } catch (SQLException ex){
                System.out.println("Lỗi ở hàm hasMaNCC của class PhieuNhapDAL");
            } finally {
                closeConnection();
            }
        }
        return result;
    }


    public boolean updatePhieuNhap(PhieuNhapDTO pn, String MaPN){
        boolean result = false;
        if (openConnection()) {
            try {
                System.out.println("MaPN = " + MaPN);
                String sql = "UPDATE PhieuNhap SET MaPN = ?, NgayNhap = ?, MaNCC = ?, TongTien = ?, TrangThai = ? " +
                        "WHERE MaPN = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, pn.getMaPN());
                stmt.setDate(2, pn.getNgayNhap());
                stmt.setString(3, pn.getMaNCC());
                stmt.setFloat(4, pn.getTongTien());
                stmt.setString(5, pn.getTrangThai());
                stmt.setString(6, MaPN);
                int rowCount = stmt.executeUpdate();
                System.out.print(rowCount);
                //Nếu câu truy vấn trả veef ít nhất 1 bản ghi thì cập nhật result là true
                if (rowCount > 0)
                    result = true;
            } catch (SQLException ex){
                ex.printStackTrace();
                System.out.println("Lỗi ở hàm updatePhieuNhap của class PhieuNhapDAL");
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean searchMaPN(Vector<PhieuNhapDTO> pn_arr, String MaPN){
        boolean result = false;
        if (openConnection()) {
            try {
                //dùng câu truy vấn để lấy bản ghi của cột MaTK trùng với MaTK truyền vào
                String sql = "SELECT * FROM PhieuNhap WHERE MaPN = '" + MaPN + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    PhieuNhapDTO pn = new PhieuNhapDTO();
                    pn.setMaPN(rs.getString("MaPN"));
                    pn.setNgayNhap(rs.getDate("NgayNhap"));
                    pn.setMaNCC(rs.getString("MaNCC"));
                    pn.setTongTien(rs.getFloat("TongTien"));
                    pn.setTrangThai(rs.getString("TrangThai"));
                    pn_arr.add(pn);
                }
                if(pn_arr.size() > 0)
                    result = true;
            } catch (SQLException ex){
                System.out.println(ex);
                System.out.println("Lỗi ở hàm searchMaPN của class PhieuNhapDAL");
            } finally {
                closeConnection();
            }
        }
        return result;
    }
}
