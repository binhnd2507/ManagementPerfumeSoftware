package dataTransferObjects;

public class TrangThaiDonHang {
	private int id;
	private String trangThaiDonHang;
	public TrangThaiDonHang() {
		super();
	}
	public TrangThaiDonHang(int id, String trangThaiDonHang) {
		super();
		this.id = id;
		this.trangThaiDonHang = trangThaiDonHang;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTrangThaiDonHang() {
		return trangThaiDonHang;
	}
	public void setTrangThaiDonHang(String trangThaiDonHang) {
		this.trangThaiDonHang = trangThaiDonHang;
	}
}
