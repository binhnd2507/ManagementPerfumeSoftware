package dataTransferObjects;

public class VaiTro {
	private int id;
	private String tenVaiTro;
	private String moTa;
	public VaiTro() {
		super();
	}
	public VaiTro(int id, String tenVaiTro, String moTa) {
		super();
		this.id = id;
		this.tenVaiTro = tenVaiTro;
		this.moTa = moTa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTenVaiTro() {
		return tenVaiTro;
	}
	public void setTenVaiTro(String tenVaiTro) {
		this.tenVaiTro = tenVaiTro;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
}
