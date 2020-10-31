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

import dataTransferObjects.Loai;

public class LoaiBL {
	private static Connection ketNoi= CSDL.getKetNoi();
	
	private static List<Loai> taoDanhSach(String sql){
		
		List<Loai> dsLoai = new ArrayList<Loai>();
		try {
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				Loai l = new Loai();
				l.setId(rs.getInt("id"));
				l.setTenLoai(rs.getString("tenloai"));
				dsLoai.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLoai;
	}
	
	public static List<Loai> docToanBoThongTin(){
		String sql = "select * from loai";
		return taoDanhSach(sql);
	}
	
	public static List<Loai> docTheoTenLoai(String tenLoai){
		String sql = "select * from loai where tenloai = '"+tenLoai+"'";
		return taoDanhSach(sql);
	}
	
	public static int themLoai(Loai l) {
		int kq = 0;
		String sql = "insert into loai (tenloai) values (?)";
		try {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			pst.setString(1, l.getTenLoai());
			kq = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể thêm loại sản phẩm này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return kq;
	}
	
	public static int capNhatLoai(Loai l) {
		
		int kq = 0;
		String sql = "update loai set tenloai = ? where id = ?";
		try {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			pst.setString(1, l.getTenLoai());
			pst.setInt(2, l.getId());
			kq = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể cập nhật loại sản phẩm này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return kq;
	}
	
	public static int xoaLoai(int idLoai) {
		
		int kq = 0;
		String sql = "delete from loai where id = "+idLoai;
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
	
	public static Hashtable<String, Integer> docTatCa(){
		Hashtable<String, Integer> dsLoai= new Hashtable<String, Integer>();
		String sql= "select * from loai";
		try {
			Statement stm= ketNoi.createStatement();
			ResultSet rs= stm.executeQuery(sql);
			while(rs.next()) {
				dsLoai.put(rs.getString("tenloai"), rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLoai;
	}
	
	public static Loai docTheoIdLoai(int idLoai) {
		Loai loai= null;
		String sql= "select * from loai where id= ?";
		try {
			PreparedStatement pst= ketNoi.prepareStatement(sql);
			pst.setInt(1, idLoai);
			ResultSet rs= pst.executeQuery();
			if(rs.next()) {
				loai= new Loai();
				loai.setId(rs.getInt("id"));
				loai.setTenLoai(rs.getString("tenloai"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loai;
		
	}
}
