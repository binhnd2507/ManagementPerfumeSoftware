package businessLogics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;

import dataTransferObjects.TrangThaiDonHang;

public class TrangThaiBL {
	private static Connection ketNoi = CSDL.getKetNoi();
	public static Statement stm = null;
	public static ResultSet rs = null;
	
	public static Map<String, Integer> docTatCa(){
		Map<String, Integer> dsTrangThai = new Hashtable<String, Integer>();
		String sql = "select * from trangthaidonhang";
		try {
			stm = ketNoi.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				dsTrangThai.put(rs.getString("tentrangthai"), rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTrangThai;
	}
	
	public static TrangThaiDonHang docTheoIdTrangThaiDH(int id) {
		TrangThaiDonHang trangThaiDH = null;
		String sql = "select * from trangthaidonhang where id = '"+id+"'";
		try {
			stm = ketNoi.createStatement();
			rs = stm.executeQuery(sql);
			if(rs.next()) {
				trangThaiDH = new TrangThaiDonHang();
				trangThaiDH.setId(rs.getInt("id"));
				trangThaiDH.setTrangThaiDonHang(rs.getString("tentrangthai"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trangThaiDH;
	}
	
	
}
