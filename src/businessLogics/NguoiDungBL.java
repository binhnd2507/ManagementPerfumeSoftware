package businessLogics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dataTransferObjects.NguoiDung;

public class NguoiDungBL {
	
	private static Connection ketNoi = CSDL.getKetNoi();
	
	private static List<NguoiDung> taoDanhSach(String sql){
		List<NguoiDung> dsnd = new ArrayList<NguoiDung>();
		try {
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				NguoiDung nd = new NguoiDung();
				nd.setId(rs.getInt("id"));
				nd.setEmail(rs.getString("email"));
				nd.setPassword(rs.getString("password"));
				nd.setHoTen(rs.getString("hoten"));
				nd.setDiaChi(rs.getString("diachi"));
				nd.setDienThoai(rs.getString("dtdd"));
				nd.setIdVaiTro(rs.getInt("id_vaitro"));
				dsnd.add(nd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnd;
	}

	public static List<NguoiDung> docTheoTenNguoiDung(String tenND){
		String sql = "select * from nguoidung where hoten like '%"+tenND+"%'";
		List<NguoiDung> dsnd = taoDanhSach(sql);
		return dsnd;
	}
	
	public static NguoiDung docTheoEmailPassword(String email, String password) {
		NguoiDung nd= null;
		String sql= "select * from nguoidung where email=? and password=?";
		try {
			PreparedStatement pst= ketNoi.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet rs= pst.executeQuery();
			if(rs.next()) {
				nd= new NguoiDung();
				nd.setId(rs.getInt("id"));
				nd.setEmail(rs.getString("email"));
				nd.setPassword(rs.getString("password"));
				nd.setHoTen(rs.getString("hoten"));
				nd.setDiaChi(rs.getString("diachi"));
				nd.setDienThoai(rs.getString("dtdd"));
				nd.setIdVaiTro(rs.getInt("id_vaitro"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nd;
	}
	
	
	public static NguoiDung docTheoIdKhachHang(int idKhachHang) {
		NguoiDung nd = null;
		String sql = "select * from nguoidung where id = "+idKhachHang;
		try {
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if(rs.next()) {
				nd = new NguoiDung();
				nd.setId(rs.getInt("id"));
				nd.setEmail(rs.getString("email"));
				nd.setPassword(rs.getString("password"));
				nd.setHoTen(rs.getString("hoten"));
				nd.setDiaChi(rs.getString("diachi"));
				nd.setDienThoai(rs.getString("dtdd"));
				nd.setIdVaiTro(rs.getInt("id_vaitro"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nd;
	}

	public static int capNhatNguoiDung(NguoiDung nd) {
		int kq = 0;
		String sql = "update nguoidung set email = ?, password = ?, hoten = ?, diachi = ?, dtdd = ?, id_vaitro = ? where id = ?";
		try {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			pst.setString(1, nd.getEmail());
			pst.setString(2, nd.getPassword());
			pst.setString(3, nd.getHoTen());
			pst.setString(4, nd.getDiaChi());
			pst.setString(5, nd.getDienThoai());
			pst.setInt(6, nd.getIdVaiTro());
			pst.setInt(7, nd.getId());
			kq = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã cập nhật thành công","Thông báo",1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi cập nhật! Không thể cập nhật thông tin người dùng này","Thông báo",1);
			e.printStackTrace();
		}
		return kq;
	}
}
