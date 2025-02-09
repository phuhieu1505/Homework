package main.java.DAL;
import main.java.DTO.HoaDonDTO;
import main.java.DTO.ThongKeDTO;

import java.sql.*;
import java.util.Vector;

public class ThongKeDAL {
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

    public boolean ThongKeSP(Vector<ThongKeDTO> arr, Date NgayDau, Date NgayCuoi){
        boolean result = false;

        if(openConnection()) {
            try {
                String sql = "SELECT HD.MaHD, TongTien, MaSP, SLmua\n" +
                        "FROM HoaDon AS HD, CTHD AS cthd\n" +
                        "WHERE HD.MaHD = cthd.MaHD AND\n" +
                        "\t  NgayTao BETWEEN ? AND ? ";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setDate(1, NgayDau);
                stmt.setDate(2, NgayCuoi);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()){
                    ThongKeDTO tke = new ThongKeDTO();
                    tke.setMaHD(rs.getString("MaHD"));
                    tke.setTongTien(rs.getFloat("TongTien"));
                    tke.setMaSP(rs.getString("MaSP"));
                    tke.setSLmua(rs.getInt("SLmua"));
                    arr.add(tke);
                }
                if(arr.size() > 0)
                    result = true;
                else
                    System.out.println("Mảng trả về 0");
            } catch (SQLException ex) {
                System.out.println("Hàm ThongKeSP bị lỗi");
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean ThongKePN(Vector<ThongKeDTO> arr_pn, Date NgayDau, Date NgayCuoi){
        boolean result = false;
        if(openConnection()) {
            try {
                String sql = "SELECT PN.MaPN, TongTien, MaSP, SoLuong\n" +
                        "FROM PhieuNhap AS PN, CTPN AS ctpn\n" +
                        "WHERE PN.MaPN = ctpn.MaPN AND\n" +
                        "\t  NgayNhap BETWEEN ? AND ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setDate(1, NgayDau);
                stmt.setDate(2, NgayCuoi);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()){
                    ThongKeDTO tke = new ThongKeDTO();
                    tke.setMaPN(rs.getString("MaPN"));
                    tke.setMaSP(rs.getString("MaSP"));
                    tke.setSoLuong(rs.getInt("SoLuong"));
                    tke.setTongTienPN(rs.getFloat("TongTien"));
                    arr_pn.add(tke);
                }
                if(arr_pn.size() > 0)
                    result = true;
            } catch (SQLException ex) {
                System.out.println("Hàm ThongKePN bị lỗi");
            } finally {
                closeConnection();
            }
        }
        return result;
    }
}
