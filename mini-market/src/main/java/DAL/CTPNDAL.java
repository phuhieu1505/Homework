package main.java.DAL;

import main.java.DTO.CTPN_DTO;
import main.java.DTO.PhieuNhapDTO;

import java.sql.*;
import java.util.Vector;

public class CTPNDAL {
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

        public Vector<CTPN_DTO> getAllCTPN() {
            Vector<CTPN_DTO> ctpn_arr = new Vector<CTPN_DTO>();
            if (openConnection()) {
                try {
                    String sql = "Select * from CTPN";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        CTPN_DTO ctpn = new CTPN_DTO();
                        ctpn.setMaPN(rs.getString("MaPN"));
                        ctpn.setMaSP(rs.getString("MaSP"));
                        ctpn.setTenSP(rs.getString("TenSP"));
                        ctpn.setSoLuong(rs.getInt("SoLuong"));
                        ctpn.setDonGia(rs.getFloat("DonGia"));
                        ctpn.setThanhTien(rs.getFloat("ThanhTien"));
                        ctpn_arr.add(ctpn);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.print("Lỗi ở hàm getAllCTPN trong class CTPNDAL");
                } finally {
                    closeConnection();
                }
            }
            return ctpn_arr;
        }

        public boolean addCTPN(CTPN_DTO ctpn) {
            boolean result = false;
            if (openConnection()) {
                try {
                    String sql = "Insert into CTPN values(?,?,?,?,?,?)";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setString(1, ctpn.getMaPN());
                    stmt.setString(2, ctpn.getMaSP());
                    stmt.setInt(3, ctpn.getSoLuong());
                    stmt.setFloat(4, ctpn.getDonGia());
                    stmt.setFloat(5, ctpn.getThanhTien());
                    if (stmt.executeUpdate() >= 1)
                        result = true;
                } catch (SQLException ex) {
                    System.out.println("Lỗi ở hàm add của class CTPNDAL");
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
                    System.out.println("Lỗi ở hàm hasMaPN của class CTPNDAL");
                } finally {
                    closeConnection();
                }
            }
            return result;
        }

    public boolean hasMaSP(String MaSP){
        boolean result = false;
        if(openConnection()){
            try{
                String sql ="SELECT * FROM SanPham WHERE MaSP ='" + MaSP + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                result = rs.next();
            }catch (SQLException e){
                System.out.println(e);
            }finally {
                closeConnection();
            }

        }
        return result;
    }

    public boolean updateCTPN(CTPN_DTO ctpn, String MaPN){
        boolean result = false;
        if (openConnection()) {
            try {
                System.out.println("MaPN = " + MaPN);
                String sql = "UPDATE CTPN SET MaPN = ?, MaSP = ?, TenSP = ?, SoLuong = ?, DonGia = ?, ThanhTien = ? " +
                        "WHERE MaPN = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, ctpn.getMaPN());
                stmt.setString(2, ctpn.getMaSP());
                stmt.setString(3, ctpn.getTenSP());
                stmt.setInt(4, ctpn.getSoLuong());
                stmt.setFloat(5, ctpn.getDonGia());
                stmt.setFloat(6, ctpn.getThanhTien());
                stmt.setString(7, MaPN);
                int rowCount = stmt.executeUpdate();
                System.out.print(rowCount);
                //Nếu câu truy vấn trả veef ít nhất 1 bản ghi thì cập nhật result là true
                if (rowCount > 0)
                    result = true;
            } catch (SQLException ex){
                ex.printStackTrace();
                System.out.println("Lỗi ở hàm updateCTPN của class CTPNDAL");
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean searchCTPN(Vector<CTPN_DTO> ctpn_arr, String MaPN){
        boolean result = false;
        if (openConnection()) {
            try {
                //dùng câu truy vấn để lấy bản ghi của cột MaTK trùng với MaTK truyền vào
                String sql = "SELECT * FROM CTPN WHERE MaPN = '" + MaPN + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    CTPN_DTO ctpn = new CTPN_DTO();
                    ctpn.setMaPN(rs.getString("MaPN"));
                    ctpn.setMaSP(rs.getString("MaSP"));
                    ctpn.setTenSP(rs.getString("TenSP"));
                    ctpn.setSoLuong(rs.getInt("SoLuong"));
                    ctpn.setDonGia(rs.getFloat("DonGia"));
                    ctpn.setThanhTien(rs.getFloat("ThanhTien"));
                    ctpn_arr.add(ctpn);
                }
                if(ctpn_arr.size() > 0)
                    result = true;
            } catch (SQLException ex){
                System.out.println(ex);
                System.out.println("Lỗi ở hàm searchCTPN của class CTPNDAL");
            } finally {
                closeConnection();
            }
        }
        return result;
    }

}

