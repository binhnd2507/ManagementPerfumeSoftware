package presentationLayers;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import businessLogics.LoaiBL;
import dataTransferObjects.Loai;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmQuanLyLoaiSanPham extends JInternalFrame {
	private JTextField txtTen;
	private JTable tbKetQua;
	
	private List<Loai> dsLoai;
	private List<Loai> dsKiemTraTenLoai; 
	
	private DefaultTableModel dtm;
	private int dongChon; 
	private Loai loaiChon;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoa;
	

	/**
	 * Launch the application.
	 */
	
	public void xuatDanhSach() {
		
		dsLoai = LoaiBL.docToanBoThongTin();
		dtm = (DefaultTableModel) tbKetQua.getModel();
		dtm.setRowCount(0);
		
		for(Loai l : dsLoai) {
			dtm.addRow(new Object[] {l.getId(), l.getTenLoai()});
		}
		
	}
	
	public void xuLyButton(boolean b) {
		
		btnThem.setEnabled(b);
		btnCapNhat.setEnabled(b);
		btnXoa.setEnabled(b);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmQuanLyLoaiSanPham frame = new FrmQuanLyLoaiSanPham();
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
	public FrmQuanLyLoaiSanPham() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
			
				xuatDanhSach();
				xuLyButton(false);
			}
		});
		setClosable(true);
		setTitle("Quản lý loại sản phẩm");
		setBounds(100, 100, 477, 353);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên loại");
		lblNewLabel.setBounds(38, 41, 46, 14);
		getContentPane().add(lblNewLabel);
		
		txtTen = new JTextField();
		txtTen.setBounds(94, 38, 243, 20);
		getContentPane().add(txtTen);
		txtTen.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 147, 441, 165);
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
					loaiChon = null;
				}
				else {
					loaiChon = dsLoai.get(dongChon);
				}
				
				if(loaiChon != null) {
					txtTen.setText(loaiChon.getTenLoai());
				}
			}
		});
		tbKetQua.setBackground(new Color(255, 228, 181));
		tbKetQua.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"ID", "T\u00EAn lo\u1EA1i"
			}
		));
		tbKetQua.getColumnModel().getColumn(1).setPreferredWidth(400);
		scrollPane.setViewportView(tbKetQua);
		
		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String tenLoai;
				try {
					tenLoai = txtTen.getText().trim();
					
					if(tenLoai.isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Tên của loại sản phẩm không được để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
						txtTen.grabFocus();
						return;
					}
					
					dsKiemTraTenLoai = LoaiBL.docTheoTenLoai(tenLoai);
					
					if(!dsKiemTraTenLoai.isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Loại "+tenLoai+" đã tồn tại, không thể thêm", "Thông báo", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					Loai l = new Loai(0, tenLoai);
					
					LoaiBL.themLoai(l);
					xuatDanhSach();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(rootPane,"Vui lòng kiểm tra lại thông tin","Lỗi nhập sai dữ liệu",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnThem.setBounds(48, 93, 89, 23);
		getContentPane().add(btnThem);
		
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(loaiChon != null) {
					String tenLoai;
					try {
						tenLoai = txtTen.getText().trim();
						
						if(tenLoai.isEmpty()) {
							JOptionPane.showMessageDialog(rootPane, "Tên của loại sản phẩm không được để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
							txtTen.grabFocus();
							return;
						}
						else if(loaiChon.getTenLoai().equals(tenLoai)) {
							JOptionPane.showMessageDialog(rootPane, "Thông tin của loại sản phẩm không có gì thay đổi", "Thông báo", JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						Loai l = new Loai(loaiChon.getId(), tenLoai);
						
						LoaiBL.capNhatLoai(l);
						xuatDanhSach();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(rootPane,"Vui lòng kiểm tra lại thông tin","Lỗi nhập sai dữ liệu",JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn loại sản phẩm cần cập nhật", "Thông báo", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnCapNhat.setBounds(185, 93, 89, 23);
		getContentPane().add(btnCapNhat);
		
		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(loaiChon != null) {
					int kqChon = JOptionPane.showConfirmDialog(rootPane, "Bạn thật sự muốn xóa loại sản phẩm này", 
							"Chú ý", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(kqChon == JOptionPane.YES_OPTION) {
						LoaiBL.xoaLoai(loaiChon.getId());
						xuatDanhSach();
						loaiChon = null;
						txtTen.setText(null);
					}
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn loại sản phẩm cần xóa","Thông báo", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnXoa.setBounds(322, 93, 89, 23);
		getContentPane().add(btnXoa);
		
		JButton btnNhapMoi = new JButton("Nhập mới");
		btnNhapMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				xuLyButton(false);
				btnThem.setEnabled(true);
				
				txtTen.setText(null);
				txtTen.grabFocus();
			}
		});
		btnNhapMoi.setBounds(362, 37, 89, 23);
		getContentPane().add(btnNhapMoi);

	}
}
