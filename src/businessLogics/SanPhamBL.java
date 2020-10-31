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

import dataTransferObjects.SanPham;

public class SanPhamBL {
	public static Connection ketNoi= CSDL.getKetNoi();

	private static List<SanPham> taoDanhSach(String sql){
		List<SanPham> dssp = new ArrayList<SanPham>();
		try {
			Statement stm= ketNoi.createStatement();
			ResultSet rs= stm.executeQuery(sql);
			while(rs.next()) {
				SanPham sp= new SanPham();
				sp.setId(rs.getInt("id"));
				sp.setTenSanPham(rs.getString("tensanpham"));
				sp.setMoTa(rs.getString("mota"));
				sp.setHinhAnh(rs.getString("hinhanh"));
				sp.setDonGia(rs.getDouble("dongia"));
				sp.setDonGiaKM(rs.getDouble("dongiaKM"));
				sp.setSoLuong(rs.getInt("soluong"));
				sp.setNgayTao(rs.getDate("ngaytao"));
				sp.setHienThi(rs.getInt("hienthi"));
				sp.setIdLoai(rs.getInt("id_loai"));
				sp.setIdThuongHieu(rs.getInt("id_thuonghieu"));
				dssp.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dssp;
	}
	
	public static List<SanPham> docTheoTenSanPham(String tenSP){
		String sql = "select * from sanpham where tensanpham = '"+tenSP+"'";
		return taoDanhSach(sql);
	}
	
	public static List<SanPham> docToanBoSP(){
		String sql = "select * from sanpham";
		return taoDanhSach(sql);
	}
	
	public static SanPham docTheoIdSanPham(int idSanPham) {
		SanPham sp = null;
		String sql = "select * from sanpham where id = "+idSanPham;
		List<SanPham> dssp = taoDanhSach(sql);
		if(dssp.size() > 0) {
			sp = dssp.get(0);
		}
		return sp;
	}
	
	public static Hashtable<String, SanPham> docTenSanPham(String tenSp){
		Hashtable<String, SanPham> dsSanPham= new Hashtable<String, SanPham>();
		String sql= "select * from sanpham where tensanpham = '"+tenSp+"'";
		try {
			Statement stm= ketNoi.createStatement();
			ResultSet rs= stm.executeQuery(sql);
			while(rs.next()) {
				SanPham sp= new SanPham();
				sp.setId(rs.getInt("id"));
				sp.setTenSanPham(rs.getString("tensanpham"));
				sp.setMoTa(rs.getString("mota"));
				sp.setHinhAnh(rs.getString("hinhanh"));
				sp.setDonGia(rs.getDouble("dongia"));
				sp.setDonGiaKM(rs.getDouble("dongiaKM"));
				sp.setSoLuong(rs.getInt("soluong"));
				sp.setNgayTao(rs.getDate("ngaytao"));
				sp.setHienThi(rs.getInt("hienthi"));
				sp.setIdLoai(rs.getInt("id_loai"));
				sp.setIdThuongHieu(rs.getInt("id_thuonghieu"));
				dsSanPham.put(tenSp, sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSanPham;
	}
	
	public static int themSanPham(SanPham sp) {
		int ret= 0;
		String sql= "insert into sanpham(tensanpham,mota,hinhanh,dongia,dongiaKM,soluong,ngaytao,hienthi,id_loai,id_thuonghieu) values(?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst= ketNoi.prepareStatement(sql);
			pst.setString(1, sp.getTenSanPham());
			pst.setString(2, sp.getMoTa());
			pst.setString(3, sp.getHinhAnh());
			pst.setDouble(4, sp.getDonGia());
			pst.setDouble(5, sp.getDonGiaKM());
			pst.setInt(6, sp.getSoLuong());
			pst.setDate(7, sp.getNgayTao());
			pst.setInt(8, sp.getHienThi());
			pst.setInt(9, sp.getIdLoai());
			pst.setInt(10, sp.getIdThuongHieu());
			ret= pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể thêm sản phẩm này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return ret;
	}
	
	public static int capNhatSanPham(SanPham sp) {
		int kq=0;
		String sql="UPDATE `sanpham` SET `tensanpham` = ?, `mota` = ?, `hinhanh` = ?, `dongia` = ?, `dongiaKM` = ?, `soluong` = ?, `ngaytao` = ?,"
				+ " `hienthi` = ?, `id_loai` = ?, `id_thuonghieu` = ? WHERE `sanpham`.`id` = ?";
		try {
			PreparedStatement pst= ketNoi.prepareStatement(sql);
			pst.setString(1, sp.getTenSanPham());
			pst.setString(2, sp.getMoTa());
			pst.setString(3, sp.getHinhAnh());
			pst.setDouble(4, sp.getDonGia());
			pst.setDouble(5, sp.getDonGiaKM());
			pst.setInt(6, sp.getSoLuong());
			pst.setDate(7, sp.getNgayTao());
			pst.setInt(8, sp.getHienThi());
			pst.setInt(9, sp.getIdLoai());
			pst.setInt(10, sp.getIdThuongHieu());
			pst.setInt(11, sp.getId());
			kq= pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã cập nhật thành công","Thông báo",1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi cập nhật! Không thể cập nhật sản phẩm này","Thông báo",1);
			e.printStackTrace();
		}
		return kq;
	}
	
	public static int xoaSanPham(int idSanPham) {
		int kq = 0;
		String sql = "delete from sanpham where id = "+idSanPham;
		try {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			kq = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể xóa sản phẩm này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return kq;
	}
}
