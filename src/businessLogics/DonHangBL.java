package businessLogics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dataTransferObjects.DonHang;

public class DonHangBL {
	private static Connection ketNoi= CSDL.getKetNoi();
	
	private static List<DonHang> taoDanhSachDonHang(String sql){
		List<DonHang> dsdh= new ArrayList<DonHang>();
		try {
			Statement stm= ketNoi.createStatement();
			ResultSet rs= stm.executeQuery(sql);
			while(rs.next()) {
				DonHang dh = new DonHang();
				dh.setId(rs.getInt("id"));
				dh.setIdKhachHang(rs.getInt("id_khachhang"));
				dh.setNgayDatHang(rs.getDate("ngaydathang"));
				dh.setTenNguoiNhanHang(rs.getString("tennguoinhanhang"));
				dh.setDienThoaiNguoiNhan(rs.getString("dienthoainguoinhan"));
				dh.setDiaChiGiaoHang(rs.getString("diachigiaohang"));
				dh.setGhiChu(rs.getString("ghichu"));
				dh.setThanhToan(rs.getBoolean("thanhtoan"));
				dh.setIdTrangThai(rs.getInt("id_trangthai"));
				dsdh.add(dh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsdh;
	}
	
	public static List<DonHang> docTheoTenKhachHang(String tenKH){
		String sql = "select * from donhang dh inner join nguoidung nd on nd.id = dh.id_khachhang where hoten like '%"+tenKH+"%'";
		return taoDanhSachDonHang(sql);
	}
	
	public static List<DonHang> docTheoNgayDatHang(Date ngayDatHang){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String sql = "select * from donhang where ngaydathang = '"+sdf.format(ngayDatHang)+"'";
		return taoDanhSachDonHang(sql);
	}
	
	public static List<DonHang> docTheoTrangThaiDonHang(int idTrangThai){
		String sql = "select * from donhang where id_trangthai = '"+idTrangThai+"'";
		return taoDanhSachDonHang(sql);
	}
	
	public static DonHang docTheoIdDonHang(int idDonHang) {
		DonHang dh = null;
		String sql = "select * from donhang where id = "+idDonHang;
		List<DonHang> dsdh = taoDanhSachDonHang(sql);
		if(dsdh.size()>0) {
			dh = dsdh.get(0);
		}
		return dh;
	}
	
	public static int capNhatDonHang(DonHang dh) {
		int kq = 0;
		String sql = "UPDATE `donhang` SET `id_khachhang` = ?, `ngaydathang` = ?, `tennguoinhanhang` = ?, "
				+ "`dienthoainguoinhan` = ?, `diachigiaohang` = ?, `ghichu` = ?, `thanhtoan` = ?, `id_trangthai` = ? WHERE `donhang`.`id` = ?";
		try {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			pst.setInt(1, dh.getIdKhachHang());
			pst.setDate(2, dh.getNgayDatHang());
			pst.setString(3, dh.getTenNguoiNhanHang());
			pst.setString(4, dh.getDienThoaiNguoiNhan());
			pst.setString(5, dh.getDiaChiGiaoHang());
			pst.setString(6, dh.getGhiChu());
			pst.setBoolean(7, dh.isThanhToan());
			pst.setInt(8, dh.getIdTrangThai());
			pst.setInt(9, dh.getId());
			kq = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã cập nhật thành công","Thông báo",1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi cập nhật! Không thể cập nhật đơn hàng này","Thông báo",1);
			e.printStackTrace();
		}
		return kq;
	}
	
	public static int xoaTheoIdDonHang(int idDonHang) {
		int kq = 0;
		String sql = "delete from donhang where id = "+idDonHang;
		try {
			Statement stm = ketNoi.createStatement();
			kq = stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}
}
