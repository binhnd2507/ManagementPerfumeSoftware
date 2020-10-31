package presentationLayers;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import businessLogics.ChiTietDonHangBL;
import businessLogics.DonHangBL;
import businessLogics.NguoiDungBL;
import businessLogics.SanPhamBL;
import businessLogics.TrangThaiBL;
import dataTransferObjects.ChiTietDonHang;
import dataTransferObjects.DonHang;
import dataTransferObjects.NguoiDung;
import dataTransferObjects.TrangThaiDonHang;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class FrmQuanLyDonHang extends JInternalFrame {
	private JTextField txtTenKH1;
	private JTextField txtNgayDat1;
	private JTable tbKetQuaDH;
	private JTextField txtMaDH;
	private JTextField txtTenKH2;
	private JTextField txtNgayDat2;
	private JTextField txtTenNguoiNhanHang;
	private JTextField txtDiaChiGiaoHang;
	private JTextField txtDienThoaiNguoiNhan;
	private JTable tbKetQuaChiTietDH;
	private JComboBox cboTrangThai1;
	
	private DefaultComboBoxModel<String> dcm;
	private DefaultComboBoxModel<String> dcmCT;
	private DefaultTableModel dtm;
	private DefaultTableModel dtmCT;
	private JComboBox cboTimTheo;
	
	private Map<String, Integer> dsTrangThaiDonHang = TrangThaiBL.docTatCa();
	private List<DonHang> dsdh;
	private List<ChiTietDonHang> dsChiTietDH;
	
	private int chiMucChon;
	private JComboBox cboTrangThai2;
	private JRadioButton rdbThanhToan;
	private int dongChon;
	private DonHang dhChon;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Locale lc = new Locale("vi", "VN");
	private NumberFormat nf = NumberFormat.getNumberInstance(lc);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmQuanLyDonHang frame = new FrmQuanLyDonHang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmQuanLyDonHang() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {

				txtTenKH1.setEnabled(true);
				txtNgayDat1.setEnabled(false);
				cboTrangThai1.setEnabled(false);
				cboTrangThai1.setSelectedIndex(-1);

				dcm = (DefaultComboBoxModel<String>) cboTrangThai1.getModel();
				dcmCT = (DefaultComboBoxModel<String>) cboTrangThai2.getModel();

				for (String key : dsTrangThaiDonHang.keySet()) {
					dcm.addElement(key);
					dcmCT.addElement(key);
				}
			}
		});
		setClosable(true);
		setTitle("Quản lý đơn hàng");
		setBounds(100, 100, 751, 540);
		getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 715, 488);
		getContentPane().add(tabbedPane);

		JPanel pnlTimKiemDH = new JPanel();
	
		tabbedPane.addTab("Tìm kiếm đơn hàng", null, pnlTimKiemDH, null);
		pnlTimKiemDH.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tìm kiếm theo");
		lblNewLabel.setBounds(71, 39, 128, 14);
		pnlTimKiemDH.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Tên khách hàng");
		lblNewLabel_1.setBounds(71, 76, 128, 14);
		pnlTimKiemDH.add(lblNewLabel_1);

		txtTenKH1 = new JTextField();
		txtTenKH1.setBounds(222, 73, 228, 20);
		pnlTimKiemDH.add(txtTenKH1);
		txtTenKH1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Ngày đặt hàng");
		lblNewLabel_2.setBounds(71, 115, 128, 14);
		pnlTimKiemDH.add(lblNewLabel_2);

		txtNgayDat1 = new JTextField();
		txtNgayDat1.setBounds(222, 112, 81, 20);
		pnlTimKiemDH.add(txtNgayDat1);
		txtNgayDat1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Trạng thái đơn hàng");
		lblNewLabel_3.setBounds(71, 155, 128, 14);
		pnlTimKiemDH.add(lblNewLabel_3);

		cboTimTheo = new JComboBox();
		cboTimTheo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtTenKH1.setEnabled(false);
				txtNgayDat1.setEnabled(false);
				cboTrangThai1.setEnabled(false);

				chiMucChon = cboTimTheo.getSelectedIndex();

				switch (chiMucChon) {
				case 0:
					txtTenKH1.setEnabled(true);
					txtNgayDat1.setText(null);
					cboTrangThai1.setSelectedIndex(-1);
					break;
				case 1:
					txtTenKH1.setText(null);
					;
					txtNgayDat1.setEnabled(true);
					cboTrangThai1.setSelectedIndex(-1);
					break;
				case 2:
					txtTenKH1.setEnabled(false);
					txtNgayDat1.setText(null);
					;
					cboTrangThai1.setEnabled(true);
					break;
				}
			}
		});
		cboTimTheo.setModel(
				new DefaultComboBoxModel(new String[] { "Tên khách hàng", "Ngày đặt hàng", "Trạng thái đơn hàng" }));
		cboTimTheo.setBounds(222, 35, 176, 18);
		pnlTimKiemDH.add(cboTimTheo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 227, 577, 183);
		pnlTimKiemDH.add(scrollPane);

		tbKetQuaDH = new JTable();
		tbKetQuaDH.setBackground(new Color(255, 228, 181));
		tbKetQuaDH.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 \u0110H", "T\u00EAn kh\u00E1ch h\u00E0ng", "Ng\u00E0y \u0111\u1EB7t", "T\u00ECnh tr\u1EA1ng", "Thanh to\u00E1n"
			}
		));
		tbKetQuaDH.getColumnModel().getColumn(0).setResizable(false);
		tbKetQuaDH.getColumnModel().getColumn(0).setPreferredWidth(30);
		tbKetQuaDH.getColumnModel().getColumn(1).setResizable(false);
		tbKetQuaDH.getColumnModel().getColumn(1).setPreferredWidth(180);
		tbKetQuaDH.getColumnModel().getColumn(3).setPreferredWidth(85);
		tbKetQuaDH.getColumnModel().getColumn(4).setPreferredWidth(60);
		scrollPane.setViewportView(tbKetQuaDH);

		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				chiMucChon = cboTimTheo.getSelectedIndex();

				switch (chiMucChon) {
				case 0:
					String tenKH = txtTenKH1.getText().trim();
					if (tenKH.isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Hãy nhập tên khách hàng cần tìm", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						txtTenKH1.grabFocus();
						return;
					}
					dsdh = DonHangBL.docTheoTenKhachHang(tenKH);
					break;
				case 1:
					if (txtNgayDat1.getText().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Hãy nhập ngày đặt hàng cần tìm", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						txtNgayDat1.grabFocus();
						return;
					}
					try {
						Date ngayDat = sdf.parse(txtNgayDat1.getText());
						dsdh = DonHangBL.docTheoNgayDatHang(ngayDat);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(rootPane, "Ngày đặt hàng không hợp lệ", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						txtNgayDat1.grabFocus();
						return;
					}
					break;
				case 2:
					if (cboTrangThai1.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(rootPane, "Hãy chọn trạng thái cần tìm", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					int idTrangThaiDH = TrangThaiBL.docTatCa().get(cboTrangThai1.getSelectedItem());
					dsdh = DonHangBL.docTheoTrangThaiDonHang(idTrangThaiDH);
					break;
				}
				
				dtm = (DefaultTableModel) tbKetQuaDH.getModel();
				dtm.setRowCount(0);
				for (DonHang dh : dsdh) {
					TrangThaiDonHang ttDonHang = TrangThaiBL.docTheoIdTrangThaiDH(dh.getIdTrangThai());
					
					// lấy tên khách hàng ở bản table nguoidung tại vị trí id_khachhang
					NguoiDung nd = NguoiDungBL.docTheoIdKhachHang(dh.getIdKhachHang());
																						
					dtm.addRow(new Object[] { dh.getId(), nd.getHoTen(), dh.getNgayDatHang(),
							ttDonHang.getTrangThaiDonHang(), (dh.isThanhToan()? "X" : "") });
				}
			}
		});
		btnTim.setBounds(559, 35, 89, 23);
		pnlTimKiemDH.add(btnTim);

		JButton btnXemChiTietDH = new JButton("Xem chi tiết đơn hàng đang chọn");
		btnXemChiTietDH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dongChon = tbKetQuaDH.getSelectedRow();
									
				if(dongChon < 0) {
					dhChon = null;
				}
				else {
					dhChon = dsdh.get(dongChon);
				}
				
				if(dhChon != null) {
					txtMaDH.setText(String.valueOf(dhChon.getId()));

					// truyền id khách hàng của đơn hàng vào phương thức để tìm và trả về đối tượng
					// người dùng của đơn hàng đó, rồi lấy giá trị họ tên của đối tượng đó gán vào textfield
					String tenNguoiNhan = NguoiDungBL.docTheoIdKhachHang(dhChon.getIdKhachHang()).getHoTen();
					
					txtTenKH2.setText(tenNguoiNhan);
					txtNgayDat2.setText(sdf.format(dhChon.getNgayDatHang()));
					txtTenNguoiNhanHang.setText(dhChon.getTenNguoiNhanHang());
					txtDiaChiGiaoHang.setText(dhChon.getDiaChiGiaoHang());
					txtDienThoaiNguoiNhan.setText(dhChon.getDienThoaiNguoiNhan());

					// truyền id trạng thái của đơn hàng vào phương thức để tìm và trả về đối tượng
					// trạng thái đơn hàng đó, rồi lấy giá trị tên trạng thái gán vào combobox
					String tenTrangThaiDH = TrangThaiBL.docTheoIdTrangThaiDH(dhChon.getIdTrangThai()).getTrangThaiDonHang();
					cboTrangThai2.setSelectedItem(tenTrangThaiDH);
					
					rdbThanhToan.setSelected(dhChon.isThanhToan());

					dsChiTietDH = ChiTietDonHangBL.docTheoIdDonHang(dhChon.getId());
					
					// chuyển sang tab chi tiết đơn hàng
					tabbedPane.setSelectedIndex(1);
					
					dtmCT = (DefaultTableModel) tbKetQuaChiTietDH.getModel();
					dtmCT.setRowCount(0);
					for (ChiTietDonHang ctdh : dsChiTietDH) {
						
						// truyền id sản phẩm vào phương thức để tìm và trả về đối tượng sản phẩm, rồi lấy giá trị tên sản phẩm đó
						String tenSP = SanPhamBL.docTheoIdSanPham(ctdh.getIdSanPham()).getTenSanPham();
															
						// truyền id sản phẩm vào phương thức để tìm và trả về đối tượng sản phẩm, rồi lấy giá trị đơn giá sản phẩm đó
						double donGiaSP = SanPhamBL.docTheoIdSanPham(ctdh.getIdSanPham()).getDonGia();
																										
						double thanhTien = donGiaSP * ctdh.getSoLuong();
						dtmCT.addRow(new Object[] { ctdh.getId(), tenSP, ctdh.getSoLuong(), nf.format(donGiaSP),nf.format(thanhTien) });
					}
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Hãy chọn một đơn hàng để xem", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		});
		btnXemChiTietDH.setBounds(108, 426, 251, 23);
		pnlTimKiemDH.add(btnXemChiTietDH);

		cboTrangThai1 = new JComboBox();
	
		cboTrangThai1.setBounds(222, 151, 137, 22);
		pnlTimKiemDH.add(cboTrangThai1);

		JPanel pnlChiTietDH = new JPanel();

		tabbedPane.addTab("Chi tiết đơn hàng", null, pnlChiTietDH, null);
		pnlChiTietDH.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Mã ĐH");
		lblNewLabel_4.setBounds(88, 29, 99, 14);
		pnlChiTietDH.add(lblNewLabel_4);

		txtMaDH = new JTextField();
		txtMaDH.setEditable(false);
		txtMaDH.setBounds(197, 26, 89, 20);
		pnlChiTietDH.add(txtMaDH);
		txtMaDH.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Tên khách hàng");
		lblNewLabel_5.setBounds(88, 64, 99, 14);
		pnlChiTietDH.add(lblNewLabel_5);

		txtTenKH2 = new JTextField();
		txtTenKH2.setEditable(false);
		txtTenKH2.setBounds(197, 61, 170, 20);
		pnlChiTietDH.add(txtTenKH2);
		txtTenKH2.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Ngày đặt hàng");
		lblNewLabel_6.setBounds(415, 29, 89, 14);
		pnlChiTietDH.add(lblNewLabel_6);

		txtNgayDat2 = new JTextField();
		txtNgayDat2.setEditable(false);
		txtNgayDat2.setBounds(520, 26, 108, 20);
		pnlChiTietDH.add(txtNgayDat2);
		txtNgayDat2.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Thông tin giao hàng");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(134, 92, 152, 32);
		pnlChiTietDH.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Tình hình đơn hàng");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setBounds(442, 92, 159, 32);
		pnlChiTietDH.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Tên ");
		lblNewLabel_9.setBounds(89, 138, 98, 14);
		pnlChiTietDH.add(lblNewLabel_9);

		txtTenNguoiNhanHang = new JTextField();
		txtTenNguoiNhanHang.setBounds(198, 135, 170, 20);
		pnlChiTietDH.add(txtTenNguoiNhanHang);
		txtTenNguoiNhanHang.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Địa chỉ");
		lblNewLabel_10.setBounds(89, 172, 98, 14);
		pnlChiTietDH.add(lblNewLabel_10);

		txtDiaChiGiaoHang = new JTextField();
		txtDiaChiGiaoHang.setBounds(198, 169, 191, 20);
		pnlChiTietDH.add(txtDiaChiGiaoHang);
		txtDiaChiGiaoHang.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Điện thoại");
		lblNewLabel_11.setBounds(88, 208, 99, 14);
		pnlChiTietDH.add(lblNewLabel_11);

		txtDienThoaiNguoiNhan = new JTextField();
		txtDienThoaiNguoiNhan.setBounds(198, 205, 121, 20);
		pnlChiTietDH.add(txtDienThoaiNguoiNhan);
		txtDienThoaiNguoiNhan.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Trạng thái");
		lblNewLabel_12.setBounds(415, 135, 89, 14);
		pnlChiTietDH.add(lblNewLabel_12);

		cboTrangThai2 = new JComboBox();
		cboTrangThai2.setBounds(520, 135, 121, 22);
		pnlChiTietDH.add(cboTrangThai2);

		rdbThanhToan = new JRadioButton("Đã thanh toán");
		rdbThanhToan.setBounds(520, 183, 109, 23);
		pnlChiTietDH.add(rdbThanhToan);

		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String tenNguoiNhan, diaChiNhanHang, dienThoai;
				int idTrangThai;
				boolean thanhToan;

				tenNguoiNhan = txtTenNguoiNhanHang.getText().trim();
				diaChiNhanHang = txtDiaChiGiaoHang.getText().trim();
				dienThoai = txtDienThoaiNguoiNhan.getText().trim();
				idTrangThai = dsTrangThaiDonHang.get(cboTrangThai2.getSelectedItem());
				thanhToan = rdbThanhToan.isSelected();

				if (tenNguoiNhan.isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Tên của người nhận không được để trống","Thông báo", JOptionPane.WARNING_MESSAGE);
					txtTenNguoiNhanHang.grabFocus();
					return;
				} 
				else if (diaChiNhanHang.isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Thông tin của địa chỉ nhận hàng không được để trống","Thông báo", JOptionPane.WARNING_MESSAGE);
					txtDiaChiGiaoHang.grabFocus();
					return;
				} 
				else if (dienThoai.isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Số điện thoại người nhận không được để trống","Thông báo", JOptionPane.WARNING_MESSAGE);
					txtDienThoaiNguoiNhan.grabFocus();
					return;
				}
				else if (dhChon.getTenNguoiNhanHang().equals(tenNguoiNhan)
						&& dhChon.getDiaChiGiaoHang().equals(diaChiNhanHang)
						&& dhChon.getDienThoaiNguoiNhan().equals(dienThoai) && dhChon.getIdTrangThai() == idTrangThai
						&& dhChon.isThanhToan() == thanhToan) {
					JOptionPane.showMessageDialog(rootPane, "Thông tin đơn hàng không có gì thay đổi", "Thông báo", JOptionPane.WARNING_MESSAGE);
					return;
				}

				dhChon.setTenNguoiNhanHang(tenNguoiNhan);
				dhChon.setDiaChiGiaoHang(diaChiNhanHang);
				dhChon.setDienThoaiNguoiNhan(dienThoai);
				dhChon.setIdTrangThai(idTrangThai);
				dhChon.setThanhToan(thanhToan);

				DonHangBL.capNhatDonHang(dhChon);
				
				//cập nhật lại bản table 1
				dtm = (DefaultTableModel) tbKetQuaDH.getModel();
				dtm.setRowCount(0);
				for (DonHang dh : dsdh) {
					TrangThaiDonHang ttDonHang = TrangThaiBL.docTheoIdTrangThaiDH(dh.getIdTrangThai());
					
					NguoiDung nd = NguoiDungBL.docTheoIdKhachHang(dh.getIdKhachHang());
																						
					dtm.addRow(new Object[] { dh.getId(), nd.getHoTen(), dh.getNgayDatHang(),
							ttDonHang.getTrangThaiDonHang(), (dh.isThanhToan()? "X" : "") });
				}
			}
		});
		btnCapNhat.setBounds(197, 248, 89, 23);
		pnlChiTietDH.add(btnCapNhat);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(44, 294, 626, 106);
		pnlChiTietDH.add(scrollPane_1);

		tbKetQuaChiTietDH = new JTable();
		tbKetQuaChiTietDH.setBackground(new Color(255, 228, 181));
		tbKetQuaChiTietDH.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "T\u00EAn s\u1EA3n ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1", "Th\u00E0nh ti\u1EC1n"
			}
		));
		tbKetQuaChiTietDH.getColumnModel().getColumn(0).setResizable(false);
		tbKetQuaChiTietDH.getColumnModel().getColumn(0).setPreferredWidth(15);
		tbKetQuaChiTietDH.getColumnModel().getColumn(1).setResizable(false);
		tbKetQuaChiTietDH.getColumnModel().getColumn(1).setPreferredWidth(300);
		tbKetQuaChiTietDH.getColumnModel().getColumn(2).setPreferredWidth(20);
		tbKetQuaChiTietDH.getColumnModel().getColumn(3).setPreferredWidth(30);
		tbKetQuaChiTietDH.getColumnModel().getColumn(4).setPreferredWidth(30);
		
		//canh các ô số lượng, đơn giá, thành tiền qua phải
		DefaultTableCellRenderer cellrender = new DefaultTableCellRenderer();
		cellrender.setHorizontalAlignment(JLabel.RIGHT);
		
		scrollPane_1.setViewportView(tbKetQuaChiTietDH);

		JButton btnXoaChiTietDH = new JButton("Xóa chi tiết đơn hàng đang chọn");
		btnXoaChiTietDH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(dhChon != null) {
					int dongXoa = tbKetQuaChiTietDH.getSelectedRow();
					if(dongXoa < 0) {
						JOptionPane.showMessageDialog(rootPane, "Hãy chọn một đơn hàng để xóa", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					int idChiTiet = (int) tbKetQuaChiTietDH.getValueAt(dongXoa, 0);
					
					int kqChon = JOptionPane.showConfirmDialog(rootPane, "Bạn thật sự muốn xóa chi tiết đơn hàng này","Chú ý", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(kqChon == JOptionPane.YES_OPTION) {
						ChiTietDonHangBL.xoaChiTietDonHang(idChiTiet);
						dtmCT.removeRow(dongXoa);
						
						//Kiểm tra lại danh sách chi tiết đơn hàng, nếu đã xóa hết các chi tiết đơn hàng thì sẽ báo danh sách rỗng và tự xóa mã đơn hàng đó
						dsChiTietDH = ChiTietDonHangBL.docTheoIdDonHang(dhChon.getId());
						
						if(dsChiTietDH.isEmpty()) {
							DonHangBL.xoaTheoIdDonHang(Integer.parseInt(txtMaDH.getText()));
							JOptionPane.showMessageDialog(rootPane, "Danh sách chi tiết đơn hàng đã trống! Phần mềm sẽ tự động xóa mã đơn hàng "+dhChon.getId(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		btnXoaChiTietDH.setBounds(98, 415, 221, 23);
		pnlChiTietDH.add(btnXoaChiTietDH);

	}
}
