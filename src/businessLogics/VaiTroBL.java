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

import dataTransferObjects.VaiTro;

public class VaiTroBL {
	
	private static Connection ketNoi = CSDL.getKetNoi();
	
	private static List<VaiTro> taoDanhSach(String sql){
		List<VaiTro> dsvt = new ArrayList<VaiTro>();
		try {
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				VaiTro vt = new VaiTro();
				vt.setId(rs.getInt("id"));
				vt.setTenVaiTro(rs.getString("tenvaitro"));
				vt.setMoTa(rs.getString("mota"));
				dsvt.add(vt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsvt;
	}
	
	public static List<VaiTro> docToanBoThongTin(){
		String sql = "select * from vaitro";
		return taoDanhSach(sql);
	}
	
	public static List<VaiTro> docTheoTenVaiTro(String tenVaiTro){
		String sql = "select * from vaitro where tenvaitro = '"+tenVaiTro+"'";
		return taoDanhSach(sql);
	}
	
	public static VaiTro docTheoIdVaiTro(int idVaiTro) {
		VaiTro vaiTro= null;
		String sql= "select * from vaitro where id= ?";
		try {
			PreparedStatement pst= ketNoi.prepareStatement(sql);
			pst.setInt(1, idVaiTro);
			ResultSet rs= pst.executeQuery();
			if(rs.next()) {
				vaiTro= new VaiTro();
				vaiTro.setId(rs.getInt("id"));
				vaiTro.setTenVaiTro(rs.getString("tenvaitro"));
				vaiTro.setMoTa(rs.getString("mota"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vaiTro;
	}
	
	public static Hashtable<String, Integer> docTatCa(){
		Hashtable<String, Integer> dsVaiTro = new Hashtable<String, Integer>();
		String sql = "select * from vaitro";
		try {
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				dsVaiTro.put(rs.getString("tenvaitro"), rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsVaiTro;
	}
	
	public static int themVaiTro(VaiTro vt) {
		int kq = 0;
		String sql = "insert into vaitro (tenvaitro, mota) values(?, ?)";
		try {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			pst.setString(1, vt.getTenVaiTro());
			pst.setString(2, vt.getMoTa());
			kq = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể thêm vai trò này", "Thông báo", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return kq;
	}
	
	public static int capNhatVaiTro(VaiTro vt) {
		int kq = 0;
		String sql = "update vaitro set tenvaitro = ?, mota = ? where id = ?";
		try {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			pst.setString(1, vt.getTenVaiTro());
			pst.setString(2, vt.getMoTa());
			pst.setInt(3, vt.getId());
			kq = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể cập nhật vai trò này", "Thông báo", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return kq;
	}
	
	public static int xoaVaiTro(int idVaiTro) {
		int kq = 0;
		String sql = "delete from vaitro where id = "+idVaiTro;
		try {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			kq = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể xóa vai trò này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return kq;
	}
}
