package dataTransferObjects;

public class ChiTietDonHang {
	private int id;
	private int idDonHang;
	private int idSanPham;
	private int soLuong;
	public ChiTietDonHang() {
		super();
	}
	public ChiTietDonHang(int id, int idDonHang, int idSanPham, int soLuong) {
		super();
		this.id = id;
		this.idDonHang = idDonHang;
		this.idSanPham = idSanPham;
		this.soLuong = soLuong;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdDonHang() {
		return idDonHang;
	}
	public void setIdDonHang(int idDonHang) {
		this.idDonHang = idDonHang;
	}
	public int getIdSanPham() {
		return idSanPham;
	}
	public void setIdSanPham(int idSanPham) {
		this.idSanPham = idSanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
}
