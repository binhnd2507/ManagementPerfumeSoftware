package businessLogics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dataTransferObjects.QuangCao;

public class QuangCaoBL {

	private static Connection ketNoi = CSDL.getKetNoi();
	
	private static List<QuangCao> taoDanhSach(String sql){
		List<QuangCao> dsqc = new ArrayList<QuangCao>();
		try {
			Statement stm = ketNoi.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				QuangCao qc = new QuangCao();
				qc.setId(rs.getInt("id"));
				qc.setHinhAnh(rs.getString("hinhanh"));
				qc.setThongDiep(rs.getString("thongdiep"));
				qc.setThongTinChiTiet(rs.getString("thongtinchitiet"));
				qc.setNgayDang(rs.getDate("ngaydang"));
				dsqc.add(qc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsqc;
	}
	
	public static List<QuangCao> docToanBo(){
		String sql = "select * from quangcao";
		return taoDanhSach(sql);
	}
	
	public static List<QuangCao> docTheoHinhQuangCao(String hinhAnh){
		String sql = "select * from quangcao where hinhAnh = '"+hinhAnh+"'";
		return taoDanhSach(sql);
	}
	
	public static int themQuangCao(QuangCao qc){
		int kq = 0;
		String sql = "insert into quangcao (hinhanh, thongdiep, thongtinchitiet, ngaydang) values (?, ?, ?, ?) ";
		try {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			pst.setString(1, qc.getHinhAnh());
			pst.setString(2, qc.getThongDiep());
			pst.setString(3, qc.getThongTinChiTiet());
			pst.setDate(4, qc.getNgayDang());
			kq = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể thêm quảng cáo sản phẩm này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return kq;
	}
	
	public static int capNhatQuangCao(QuangCao qc) {
		int kq = 0;
		String sql = "update quangcao set hinhanh = ?, thongdiep = ?, thongtinchitiet = ?, ngaydang = ? where id = ?";
		try {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			pst.setString(1, qc.getHinhAnh());
			pst.setString(2, qc.getThongDiep());
			pst.setString(3, qc.getThongTinChiTiet());
			pst.setDate(4, qc.getNgayDang());
			pst.setInt(5, qc.getId());
			kq = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể cập nhật quảng cáo này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return kq;
	}
	
	public static int xoaQuangCao(int idQuangCao) {
		int kq = 0;
		String sql = "delete from quangcao where id = "+idQuangCao;
		try {
			PreparedStatement pst = ketNoi.prepareStatement(sql);
			kq = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Đã xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể xóa quảng cáo này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		return kq;
	}
}
