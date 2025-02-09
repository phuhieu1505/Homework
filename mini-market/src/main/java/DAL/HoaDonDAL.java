package main.java.DAL;
import main.java.DTO.HoaDonDTO;
import main.java.DTO.KhachHangDTO;
import java.sql.Date;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;
public class HoaDonDAL {
    private Connection con;

    public boolean openConnection(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbUrl="jdbc:sqlserver://localhost\\PD:1433;database=mini_market;encrypt=false;";
            String username="sa"; String password="12345678";
            con = DriverManager.getConnection(dbUrl, username, password);
            return true;
        }catch(Exception ex){
            System.out.println(ex);
            return false;
        }
    }
    public void closeConnection(){
        try{
            if(con!=null)
                con.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }

    public Vector<HoaDonDTO> getAllHoaDon(){
        Vector<HoaDonDTO> arr = new Vector<HoaDonDTO>();
        if(openConnection()){
            try{
                String sql = "Select * from HoaDon";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    HoaDonDTO hoadon = new HoaDonDTO();
                    hoadon.setHD_ID(rs.getString("MaHD"));
                    hoadon.setKH(rs.getString("MaKH"));
                    hoadon.setNV(rs.getString("MaNV"));
                    hoadon.setTongTien(rs.getFloat("TongTien"));
                    hoadon.setNgayTao(rs.getDate("NgayTao"));
                    hoadon.setTrangThai(rs.getString("TrangThai"));
                    arr.add(hoadon);
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }
        }
        return arr;
    }

    public boolean addHoaDon(HoaDonDTO hd){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "Insert into HoaDon values(?,?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1,hd.getHD_ID());
                stmt.setString(2, hd.getKH());
                stmt.setString(3, hd.getNV());
                stmt.setFloat(4,hd.getTongTien());
                stmt.setDate(5, hd.getNgayTao());
                stmt.setString(6,hd.getTrangThai());
                if(stmt.executeUpdate() >= 1)
                    return true;
            }catch(SQLException ex){
                System.out.println(ex);
            }
        }
        return result;
    }

    public boolean hasHoaDonID(String mahd){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "SELECT * FROM HoaDon WHERE MaHD = '" + mahd + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                result = rs.next();
            }catch(SQLException ex){
                System.out.println(ex);
            }finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean deleteHoaDon(String mahd){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "DELETE FROM HoaDon WHERE MaHD = '" + mahd + "'";
                PreparedStatement stmt = con.prepareStatement(sql);
                int rowCount = stmt.executeUpdate(sql);
                if(rowCount > 0)
                    return true;
            }catch(SQLException ex){
                ex.printStackTrace();
                System.out.println(ex);
            }finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean updateHoaDon(HoaDonDTO hd, String mahd){
        boolean result = false;
        if(openConnection()){
            try{
                System.out.println("HD_ID : " + mahd);
                String sql = "Update HoaDon SET MaHD = ?, MaKH = ?, MaNV = ?, TongTien = ?, NgayTao = ?, TrangThai = ? where MaHD= ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, hd.getHD_ID());
                stmt.setString(2, hd.getKH());
                stmt.setString(3, hd.getNV());
                stmt.setFloat(4, hd.getTongTien());
                stmt.setDate(5, hd.getNgayTao());
                stmt.setString(6,hd.getTrangThai());
                stmt.setString(7, mahd);
                int rowCount = stmt.executeUpdate();
                System.out.println(rowCount);
                if(rowCount > 0)
                    return true;
            }catch(SQLException ex){
                ex.printStackTrace();
                System.out.println(ex);
            }finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean searchMaHD(Vector<HoaDonDTO> arr, String MaHD){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "SELECT * FROM HoaDon WHERE MaHD= '" + MaHD +"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    HoaDonDTO hoadon = new HoaDonDTO();
                    hoadon.setHD_ID(rs.getString("MaHD"));
                    hoadon.setKH(rs.getString("MaKH"));
                    hoadon.setNV(rs.getString("MaNV"));
                    hoadon.setTongTien(rs.getFloat("TongTien"));
                    hoadon.setNgayTao(rs.getDate("NgayTao"));
                    hoadon.setTrangThai(rs.getString("TrangThai"));
                    arr.add(hoadon);
                }
                if(arr.size() > 0)
                    result = true;
            }catch(SQLException ex){
                System.out.println("Hàm searchMaHD bị lỗi");
            }finally {
                closeConnection();
            }
        }
        return result;
    }
}
