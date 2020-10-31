package presentationLayers;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import businessLogics.LoaiBL;
import businessLogics.SanPhamBL;
import businessLogics.ThuongHieuBL;
import dataTransferObjects.Loai;
import dataTransferObjects.SanPham;
import dataTransferObjects.ThuongHieu;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Date;
import java.text.NumberFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import java.awt.Color;

public class FrmCapNhatXoaSanPham extends JInternalFrame {
	private JTextField txtTenSP;
	private JTextField txtHinh;
	private JTextField txtDonGia;
	private JTextField txtDonGiaKM;
	private JTextField txtSoLuong;
	private JTable tbKetQua;
	
	private List<SanPham> dsSanPham;
	private Hashtable<String, Integer> dsLoai= LoaiBL.docTatCa();
	private Hashtable<String, Integer> dsThuongHieu= ThuongHieuBL.docTatCa();
	
	private JComboBox cbbLoai;
	private JComboBox cbbThuongHieu;
	private SanPham SpChon;
	private int dongChon;

	/**
	 * Launch the application.
	 */
	
	public void xuatDsSanPham() {
		
		DefaultComboBoxModel<String> dcmLoai= (DefaultComboBoxModel<String>) cbbLoai.getModel();
		DefaultComboBoxModel<String> dcmThuongHieu= (DefaultComboBoxModel<String>) cbbThuongHieu.getModel();
		
		DefaultTableModel dtm= (DefaultTableModel) tbKetQua.getModel();
		dtm.setRowCount(0);
		
		dsSanPham= SanPhamBL.docToanBoSP();
		
		Locale lc = new Locale("vi", "VN");
		NumberFormat nf = NumberFormat.getNumberInstance(lc);
		
		for(SanPham sp: dsSanPham) {
			Loai loai= LoaiBL.docTheoIdLoai(sp.getIdLoai());
			ThuongHieu thuongHieu= ThuongHieuBL.docTheoIdThuongHieu(sp.getIdThuongHieu());
			dtm.addRow(new Object[] {sp.getId(), sp.getTenSanPham(), sp.getMoTa(), sp.getHinhAnh(), nf.format(sp.getDonGia()), nf.format(sp.getDonGiaKM()), sp.getSoLuong(), sp.getNgayTao(), loai.getTenLoai(), thuongHieu.getTenThuongHieu()});
		}
		
		for(String key: dsLoai.keySet()) {
			dcmLoai.addElement(key);
		}
		
		for(String key: dsThuongHieu.keySet()) {
			dcmThuongHieu.addElement(key);
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCapNhatXoaSanPham frame = new FrmCapNhatXoaSanPham();
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
	public FrmCapNhatXoaSanPham() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				xuatDsSanPham();
			}
		});
		setClosable(true);
		setTitle("Cập nhật sản phẩm");
		setBounds(100, 100, 822, 526);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên sản phẩm");
		lblNewLabel.setBounds(32, 15, 83, 14);
		getContentPane().add(lblNewLabel);
		
		txtTenSP = new JTextField();
		txtTenSP.setBounds(125, 12, 342, 20);
		getContentPane().add(txtTenSP);
		txtTenSP.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mô tả");
		lblNewLabel_1.setBounds(32, 49, 71, 14);
		getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(125, 49, 342, 95);
		getContentPane().add(scrollPane);
		
		JTextArea txtaMoTa = new JTextArea();
		txtaMoTa.setLineWrap(true);
		scrollPane.setViewportView(txtaMoTa);
		
		JLabel lblNewLabel_2 = new JLabel("Hình");
		lblNewLabel_2.setBounds(32, 170, 71, 14);
		getContentPane().add(lblNewLabel_2);
		
		txtHinh = new JTextField();
		txtHinh.setBounds(124, 167, 304, 20);
		getContentPane().add(txtHinh);
		txtHinh.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Đơn giá");
		lblNewLabel_3.setBounds(32, 208, 71, 14);
		getContentPane().add(lblNewLabel_3);
		
		txtDonGia = new JTextField();
		txtDonGia.setBounds(125, 205, 86, 20);
		getContentPane().add(txtDonGia);
		txtDonGia.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Đơn giá KM");
		lblNewLabel_4.setBounds(32, 251, 71, 14);
		getContentPane().add(lblNewLabel_4);
		
		txtDonGiaKM = new JTextField();
		txtDonGiaKM.setBounds(125, 248, 86, 20);
		getContentPane().add(txtDonGiaKM);
		txtDonGiaKM.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Số lượng");
		lblNewLabel_5.setBounds(32, 294, 71, 14);
		getContentPane().add(lblNewLabel_5);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(125, 291, 86, 20);
		getContentPane().add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Loại");
		lblNewLabel_6.setBounds(245, 208, 71, 14);
		getContentPane().add(lblNewLabel_6);
		
		cbbLoai = new JComboBox();
		cbbLoai.setBounds(338, 204, 129, 22);
		getContentPane().add(cbbLoai);
		
		JLabel lblNewLabel_7 = new JLabel("Thương hiệu");
		lblNewLabel_7.setBounds(245, 251, 83, 14);
		getContentPane().add(lblNewLabel_7);
		
		cbbThuongHieu = new JComboBox();
		cbbThuongHieu.setBounds(338, 247, 129, 22);
		getContentPane().add(cbbThuongHieu);
		
		JLabel lblHinh = new JLabel("Hình Ảnh");
		lblHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinh.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblHinh.setBounds(506, 12, 274, 257);
		getContentPane().add(lblHinh);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 326, 786, 159);
		getContentPane().add(scrollPane_1);
		
		tbKetQua = new JTable();
		tbKetQua.setBackground(new Color(255, 228, 181));
		tbKetQua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dongChon= tbKetQua.getSelectedRow();
				if(dongChon<0) {
					SpChon= null;
				}
				else {
					SpChon= dsSanPham.get(dongChon);
				}
				
				if(SpChon!=null) {
					txtTenSP.setText(SpChon.getTenSanPham());
					txtaMoTa.setText(SpChon.getMoTa());
					txtHinh.setText(SpChon.getHinhAnh());
					txtDonGia.setText(String.valueOf(SpChon.getDonGia()));
					txtDonGiaKM.setText(String.valueOf(SpChon.getDonGiaKM()));
					txtSoLuong.setText(String.valueOf(SpChon.getSoLuong()));
					
					Loai loaiChon= LoaiBL.docTheoIdLoai(SpChon.getIdLoai());
					ThuongHieu thuongHieuChon= ThuongHieuBL.docTheoIdThuongHieu(SpChon.getIdThuongHieu());
					
					cbbLoai.setSelectedItem(loaiChon.getTenLoai());
					cbbThuongHieu.setSelectedItem(thuongHieuChon.getTenThuongHieu());
					
					ImageIcon icon= new ImageIcon("src/img/"+txtHinh.getText());
					icon.setImage(icon.getImage().getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_DEFAULT));
					lblHinh.setIcon(icon);
				}
			}
		});
		tbKetQua.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "T\u00EAn SP", "M\u00F4 t\u1EA3", "H\u00ECnh \u1EA3nh", "\u0110\u01A1n gi\u00E1", "\u0110\u01A1n gi\u00E1 KM", "S\u1ED1 l\u01B0\u1EE3ng", "Ng\u00E0y c\u1EADp nh\u1EADt", "Lo\u1EA1i", "Th\u01B0\u01A1ng hi\u1EC7u"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, Object.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tbKetQua.getColumnModel().getColumn(0).setPreferredWidth(15);
		tbKetQua.getColumnModel().getColumn(4).setPreferredWidth(55);
		tbKetQua.getColumnModel().getColumn(5).setPreferredWidth(60);
		tbKetQua.getColumnModel().getColumn(6).setPreferredWidth(45);
		tbKetQua.getColumnModel().getColumn(7).setPreferredWidth(80);
		scrollPane_1.setViewportView(tbKetQua);
		
		JButton btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc= new JFileChooser("src/img");
				jfc.setDialogTitle("Hãy chọn tập tin hình ảnh");
				int chon= jfc.showOpenDialog(rootPane);
				if(chon==JFileChooser.APPROVE_OPTION) {
					File file= jfc.getSelectedFile();
					txtHinh.setText(file.getAbsolutePath());
					ImageIcon icon= new ImageIcon(txtHinh.getText());
					icon.setImage(icon.getImage().getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_DEFAULT));
					lblHinh.setIcon(icon);
				}
			}
		});
		btnNewButton.setBounds(438, 166, 29, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cập nhật");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(SpChon!=null) {
					String tenSp, moTa, hinhAnh;
					double donGia, donGiaKM;
					int soLuong, idLoai, idThuongHieu;
					Date ngayTao;
					
					try {
						tenSp= txtTenSP.getText().trim();
						moTa= txtaMoTa.getText().trim();
						File fHinhAnh= new File(txtHinh.getText());
						hinhAnh= fHinhAnh.getName();
						donGia= Double.parseDouble(txtDonGia.getText());
						donGiaKM= Double.parseDouble(txtDonGiaKM.getText());
						soLuong= Integer.parseInt(txtSoLuong.getText());
						ngayTao= new Date(new java.util.Date().getTime());
						idLoai= dsLoai.get(cbbLoai.getSelectedItem());
						idThuongHieu= dsThuongHieu.get(cbbThuongHieu.getSelectedItem());
						
						if(tenSp.isEmpty()) {
							JOptionPane.showMessageDialog(rootPane, "Tên của sản phẩm không được để trống","Thông báo",JOptionPane.WARNING_MESSAGE);
							txtTenSP.grabFocus();
							return;
						}
						else if(moTa.isEmpty()) {
							JOptionPane.showMessageDialog(rootPane, "Thông tin mô tả của sản phẩm không được để trống","Thông báo",JOptionPane.WARNING_MESSAGE);
							txtaMoTa.grabFocus();
							return;
						}
						else if(hinhAnh.isEmpty()) {
							JOptionPane.showMessageDialog(rootPane, "Thông tin hình ảnh của sản phẩm không được để trống","Thông báo",JOptionPane.WARNING_MESSAGE);
							txtHinh.grabFocus();
							return;
						}
						else if(donGia < 0) {
							JOptionPane.showMessageDialog(rootPane, "Đơn giá của sản phẩm không được nhỏ hơn 0","Thông báo",JOptionPane.WARNING_MESSAGE);
							txtDonGia.grabFocus();
							return;
						}
						else if(donGiaKM < 0) {
							JOptionPane.showMessageDialog(rootPane, "Đơn giá khuyến mãi của sản phẩm không được nhỏ hơn 0","Thông báo",JOptionPane.WARNING_MESSAGE);
							txtDonGiaKM.grabFocus();
							return;
						}
						else if(soLuong < 0) {
							JOptionPane.showMessageDialog(rootPane, "Số lượng của sản phẩm không được nhỏ hơn 0","Thông báo",JOptionPane.WARNING_MESSAGE);
							txtSoLuong.grabFocus();
							return;
						}
						else if(SpChon.getTenSanPham().equals(tenSp) && SpChon.getMoTa().equals(moTa) && SpChon.getHinhAnh().equals(hinhAnh)
								&& SpChon.getDonGia()==donGia && SpChon.getDonGiaKM()==donGiaKM && SpChon.getSoLuong()==soLuong
								&& SpChon.getIdLoai()==idLoai && SpChon.getIdThuongHieu()==idThuongHieu) {
							JOptionPane.showMessageDialog(rootPane, "Thông tin của sản phẩm không có gì thay đổi","Thông báo",JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						SanPham sp= new SanPham(SpChon.getId(), tenSp, moTa, hinhAnh, donGia, donGiaKM, soLuong, ngayTao, 1, idLoai, idThuongHieu);
						
						SanPhamBL.capNhatSanPham(sp);
						xuatDsSanPham();
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(rootPane,"Vui lòng kiểm tra lại thông tin","Lỗi nhập sai dữ liệu",JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn sản phẩm cần cập nhật","Thông báo",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnNewButton_1.setBounds(506, 280, 129, 30);
		getContentPane().add(btnNewButton_1);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(SpChon != null) {
					int kqChon = JOptionPane.showConfirmDialog(rootPane, "Bạn thực sự muốn xóa sản phẩm này", "Chú ý", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(kqChon == JOptionPane.YES_OPTION) {
						SanPhamBL.xoaSanPham(SpChon.getId());
						xuatDsSanPham();
						SpChon = null;
						txtTenSP.setText(null);
						txtaMoTa.setText(null);
						txtHinh.setText(null);
						txtDonGia.setText(null);
						txtDonGiaKM.setText(null);
						txtSoLuong.setText(null);
						lblHinh.setIcon(null);
					}
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn sản phẩm cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnXoa.setBounds(651, 280, 129, 30);
		getContentPane().add(btnXoa);

	}
}
