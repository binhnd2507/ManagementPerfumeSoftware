package businessLogics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dataTransferObjects.ChiTietDonHang;

public class ChiTietDonHangBL {
	private static Connection ketNoi = CSDL.getKetNoi();
	
	public static List<ChiTietDonHang> docTheoIdDonHang(int idDonHang){
		List<ChiTietDonHang> dsChiTietDH = new ArrayList<ChiTietDonHang>();
		String sql = "select * from chitietdonhang where id_donhang = "+idDonHang;
		try {
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				ChiTietDonHang ctdh = new ChiTietDonHang();
				ctdh.setId(rs.getInt("id"));
				ctdh.setIdDonHang(rs.getInt("id_donhang"));
				ctdh.setIdSanPham(rs.getInt("id_sanpham"));
				ctdh.setSoLuong(rs.getInt("soluong"));
				dsChiTietDH.add(ctdh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietDH;
	}
	
	public static int xoaChiTietDonHang(int idChiTietDH) {
		int kq = 0;
		String sql = "delete from chitietdonhang where id = "+idChiTietDH;
		try {
			Statement stm = ketNoi.createStatement();
			kq = stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}
}
