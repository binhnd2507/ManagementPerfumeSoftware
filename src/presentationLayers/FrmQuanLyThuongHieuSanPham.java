package presentationLayers;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import businessLogics.ThuongHieuBL;
import dataTransferObjects.ThuongHieu;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class FrmQuanLyThuongHieuSanPham extends JInternalFrame {
	private JTextField txtTen;
	private JTextField txtHinh;
	private JTable tbKetQua;
	
	private List<ThuongHieu> dsThuongHieu;
	private List<ThuongHieu> dsKiemTraTenTH;
	
	private DefaultTableModel dtm;
	private int dongChon;
	private ThuongHieu thuongHieuChon;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoa;

	/**
	 * Launch the application.
	 */
	
	public void xuLyButton(boolean b) {
		
		btnThem.setEnabled(b);
		btnCapNhat.setEnabled(b);
		btnXoa.setEnabled(b);
	}
	
	public void xuatDanhSach() {
		
		dsThuongHieu = ThuongHieuBL.docToanBoThongTin();
		
		dtm = (DefaultTableModel) tbKetQua.getModel();
		dtm.setRowCount(0);
		
		for(ThuongHieu th : dsThuongHieu) {
			dtm.addRow(new Object[] {th.getId(), th.getTenThuongHieu(), th.getHinhAnh()});
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmQuanLyThuongHieuSanPham frame = new FrmQuanLyThuongHieuSanPham();
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
	public FrmQuanLyThuongHieuSanPham() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
			
				xuLyButton(false);
				xuatDanhSach();
			}
		});
		setTitle("Quản lý thương hiệu sản phẩm");
		setClosable(true);
		setBounds(100, 100, 618, 417);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên thương hiệu");
		lblNewLabel.setBounds(28, 38, 100, 14);
		getContentPane().add(lblNewLabel);
		
		txtTen = new JTextField();
		txtTen.setBounds(139, 35, 178, 20);
		getContentPane().add(txtTen);
		txtTen.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Hình ảnh");
		lblNewLabel_1.setBounds(28, 85, 100, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtHinh = new JTextField();
		txtHinh.setBounds(139, 82, 232, 20);
		getContentPane().add(txtHinh);
		txtHinh.setColumns(10);
		
		JLabel lblHinh = new JLabel("Hình ảnh");
		lblHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinh.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblHinh.setBounds(438, 11, 160, 168);
		getContentPane().add(lblHinh);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 210, 588, 168);
		getContentPane().add(scrollPane);
		
		tbKetQua = new JTable();
		tbKetQua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				xuLyButton(false);
				btnCapNhat.setEnabled(true);
				btnXoa.setEnabled(true);
				
				dongChon = tbKetQua.getSelectedRow();
				if(dongChon < 0) {
					thuongHieuChon = null;
				}
				else {
					thuongHieuChon = dsThuongHieu.get(dongChon);
				}
				
				if(thuongHieuChon != null) {
					txtTen.setText(thuongHieuChon.getTenThuongHieu());
					txtHinh.setText(thuongHieuChon.getHinhAnh());
					ImageIcon icon = new ImageIcon("src/img/"+thuongHieuChon.getHinhAnh());
					icon.setImage(icon.getImage().getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_DEFAULT));
					lblHinh.setIcon(icon);
				}
			}
		});
		tbKetQua.setBackground(new Color(255, 228, 181));
		tbKetQua.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"ID", "T\u00EAn th\u01B0\u01A1ng hi\u1EC7u", "H\u00ECnh \u1EA3nh"
			}
		));
		tbKetQua.getColumnModel().getColumn(0).setPreferredWidth(30);
		tbKetQua.getColumnModel().getColumn(1).setPreferredWidth(300);
		tbKetQua.getColumnModel().getColumn(2).setPreferredWidth(500);
		scrollPane.setViewportView(tbKetQua);
		
		JButton btnNhapMoi = new JButton("Nhập mới");
		btnNhapMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				xuLyButton(false);
				btnThem.setEnabled(true);
				
				txtTen.setText(null);
				txtTen.grabFocus();
				txtHinh.setText(null);
				lblHinh.setIcon(null);;
			}
		});
		btnNhapMoi.setBounds(327, 34, 89, 23);
		getContentPane().add(btnNhapMoi);
		
		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String tenThuongHieu, hinhAnh;
				try {
					tenThuongHieu = txtTen.getText().trim();
					File fHinh = new File(txtHinh.getText());
					hinhAnh = fHinh.getName();
					
					if(tenThuongHieu.isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Tên của thương hiệu sản phẩm không được để trống","Thông báo",JOptionPane.WARNING_MESSAGE);
						txtTen.grabFocus();
						return;
					}
					else if(hinhAnh.isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Thông tin hình ảnh của thương hiệu sản phẩm không được để trống","Thông báo",JOptionPane.WARNING_MESSAGE);
						txtHinh.grabFocus();
						return;
					}
					
					dsKiemTraTenTH = ThuongHieuBL.docTheoTenThuongHieu(tenThuongHieu);
					
					if(!dsKiemTraTenTH.isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Thương hiệu "+tenThuongHieu+" đã tồn tại, không thể thêm", "Thông báo", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					ThuongHieu th = new ThuongHieu(0, tenThuongHieu, hinhAnh);
					
					ThuongHieuBL.themThuongHieu(th);
					xuatDanhSach();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(rootPane,"Vui lòng kiểm tra lại thông tin","Lỗi nhập sai dữ liệu",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnThem.setBounds(39, 156, 89, 23);
		getContentPane().add(btnThem);
		
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(thuongHieuChon != null) {
					String tenThuongHieu, hinhAnh;
					try {
						tenThuongHieu = txtTen.getText().trim();
						File fHinh = new File(txtHinh.getText());
						hinhAnh = fHinh.getName();
						
						if(tenThuongHieu.isEmpty()) {
							JOptionPane.showMessageDialog(rootPane, "Tên của thương hiệu sản phẩm không được để trống!","Thông báo",JOptionPane.WARNING_MESSAGE);
							txtTen.grabFocus();
							return;
						}
						else if(hinhAnh.isEmpty()) {
							JOptionPane.showMessageDialog(rootPane, "Thông tin hình ảnh thương hiệu không được để trống!","Thông báo",JOptionPane.WARNING_MESSAGE);
							txtHinh.grabFocus();
							return;
						}else if(thuongHieuChon.getTenThuongHieu().equals(tenThuongHieu) && thuongHieuChon.getHinhAnh().equals(hinhAnh)) {
							JOptionPane.showMessageDialog(rootPane, "Thông tin của thương hiệu sản phẩm không có gì thay đổi!", "Thông báo", JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						ThuongHieu th = new ThuongHieu(thuongHieuChon.getId(), tenThuongHieu, hinhAnh);
						
						ThuongHieuBL.capNhatThuongHieu(th);
						xuatDanhSach();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(rootPane,"Vui lòng kiểm tra lại thông tin","Lỗi nhập sai dữ liệu",JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn thương hiệu sản phẩm cần cập nhật!", "Thông báo", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnCapNhat.setBounds(174, 156, 89, 23);
		getContentPane().add(btnCapNhat);
		
		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(thuongHieuChon != null) {
					int kqChon = JOptionPane.showConfirmDialog(rootPane, "Bạn thật sự muốn xóa thương hiệu này","Chú ý", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(kqChon == JOptionPane.YES_OPTION) {
						ThuongHieuBL.xoaThuongHieu(thuongHieuChon.getId());
						xuatDanhSach();
						thuongHieuChon = null;
						txtTen.setText(null);
						txtHinh.setText(null);
						lblHinh.setIcon(null);
					}
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn thương hiệu sản phẩm cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnXoa.setBounds(306, 156, 89, 23);
		getContentPane().add(btnXoa);
		
		JButton btnHinh = new JButton("...");
		btnHinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				JFileChooser jfc = new JFileChooser("src/img");
				jfc.setDialogTitle("Hãy chọn một tập tin hình ảnh");
				int chon = jfc.showOpenDialog(rootPane);
				if(chon == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					txtHinh.setText(file.getAbsolutePath());
					ImageIcon icon = new ImageIcon(txtHinh.getText());
					icon.setImage(icon.getImage().getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_DEFAULT));
					lblHinh.setIcon(icon);
				}
			}
		});
		btnHinh.setBounds(381, 81, 35, 23);
		getContentPane().add(btnHinh);

	}
}
