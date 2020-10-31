package businessLogics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JOptionPane;

import dataTransferObjects.ThuongHieu;

public class ThuongHieuBL {
	private static Connection ketNoi= CSDL.getKetNoi();
	
	private static List<ThuongHieu> taoDanhSach(String sql){
		List<ThuongHieu> dsThuongHieu = new ArrayList<ThuongHieu>();
		try {
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				ThuongHieu th = new ThuongHieu();
				th.setId(rs.getInt("id"));
				th.setTenThuongHieu(rs.getString("tenthuonghieu"));
				th.setHinhAnh(rs.getString("hinhanh"));
				dsThuongHieu.add(th);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsThuongHieu;
	}
	
	public static List<ThuongHieu> docToanBoThongTin(){
		String sql = "select * from thuonghieu";
		return taoDanhSach(sql);
	}
	
	public static List<ThuongHieu> docTheoTenThuongHieu(String tenThuongHieu){
		String sql = "select * from thuonghieu where tenthuonghieu = '"+tenThuongHieu+"'";
		return taoDanhSach(sql);
	}
	
	public static Hashtable<String, Integer> docTatCa(){
		Hashtable<String, Integer> dsThuongHieu= new Hashtable<String, Integer>();
		String sql= "select * from thuonghieu";
		try {
			Statement stm= ketNoi.createStatement();
			ResultSet rs= stm.executeQuery(sql);
			while(rs.next()) {
				dsThuongHieu.put(rs.getString("tenthuonghieu"), rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsThuongHieu;
	}
	
	public static ThuongHieu docTheoIdThuongHieu(int idThuongHieu) {
		ThuongHieu thuongHieu= null;
		String sql= "select * from thuonghieu where id= ?";
		try {
			PreparedStatement pst= ketNoi.prepareStatement(sql);
			pst.setInt(1, idThuongHieu);
			ResultSet rs= pst.executeQuery();
			if(rs.next()) {
				thuongHieu= new ThuongHieu();
				thuongHieu.setId(rs.getInt("id"));
				thuongHieu.setTenThuongHieu(rs.getString("tenthuonghieu"));
				thuongHieu.setHinhAnh(rs.getString("hinhanh"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thuongHieu;
	}
	
	public static int themThuongHieu(ThuongHieu th) {
		int kq = 0;
		String sql = "insert into thuonghieu (tenthuonghieu, hinhanh) values(?, ?)";
		try {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			pst.setString(1, th.getTenThuongHieu());
			pst.setString(2, th.getHinhAnh());
			kq = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể thêm thương hiệu sản phẩm này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return kq;
	}
	
	public static int capNhatThuongHieu(ThuongHieu th) {
		int kq = 0;
		String sql = "update thuonghieu set tenthuonghieu = ?, hinhanh = ? where id = ?";
		try {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			pst.setString(1, th.getTenThuongHieu());
			pst.setString(2, th.getHinhAnh());
			pst.setInt(3, th.getId());
			kq = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể cập nhật thương hiệu sản phẩm này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return kq;
	}
	
	public static int xoaThuongHieu(int idThuongHieu) {
		int kq = 0;
		String sql = "delete from thuonghieu where id = "+idThuongHieu;
		try {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			kq = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể xóa loại sản phẩm này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return kq;
	}
}
