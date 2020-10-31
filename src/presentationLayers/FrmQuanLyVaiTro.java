package presentationLayers;

import java.awt.EventQueue;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import businessLogics.VaiTroBL;
import dataTransferObjects.VaiTro;

import java.awt.Color;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmQuanLyVaiTro extends JInternalFrame {
	private JTextField txtTenVaiTro;
	private JTable tbKetQua;
	
	private List<VaiTro> dsvt;
	private List<VaiTro> dsKiemTraTenVT;
	
	private DefaultTableModel dtm;
	private VaiTro vtChon;
	private int dongChon;
	private JButton btnNhapMoi;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoa;
	

	/**
	 * Launch the application.
	 */
	
	public void xuatDS() {
		
		dsvt = VaiTroBL.docToanBoThongTin();
		dtm = (DefaultTableModel) tbKetQua.getModel();
		dtm.setRowCount(0);
		for(VaiTro vt : dsvt) {
			dtm.addRow(new Object[] {vt.getId(), vt.getTenVaiTro(),vt.getMoTa()});
		}
	}
	
	public void xuLyButton(boolean b) {
		
		btnCapNhat.setEnabled(b);
		btnThem.setEnabled(b);
		btnXoa.setEnabled(b);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmQuanLyVaiTro frame = new FrmQuanLyVaiTro();
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
	public FrmQuanLyVaiTro() {
		setClosable(true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				
				xuatDS();
				xuLyButton(false);
			}
		});
		setTitle("Quản lý vai trò");
		setBounds(100, 100, 527, 428);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên vai trò");
		lblNewLabel.setBounds(66, 24, 69, 14);
		getContentPane().add(lblNewLabel);
		
		txtTenVaiTro = new JTextField();
		txtTenVaiTro.setBounds(137, 21, 214, 20);
		getContentPane().add(txtTenVaiTro);
		txtTenVaiTro.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mô tả");
		lblNewLabel_1.setBounds(66, 73, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(137, 73, 278, 114);
		getContentPane().add(scrollPane);
		
		JTextArea txtaMoTa = new JTextArea();
		scrollPane.setViewportView(txtaMoTa);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(42, 263, 425, 92);
		getContentPane().add(scrollPane_1);
		
		tbKetQua = new JTable();
		tbKetQua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				xuLyButton(false);
				btnCapNhat.setEnabled(true);
				btnXoa.setEnabled(true);
				
				dongChon = tbKetQua.getSelectedRow();
				if(dongChon < 0) {
					vtChon = null;
				}
				else {
					vtChon = dsvt.get(dongChon);
				}
				
				if(vtChon != null) {
					txtTenVaiTro.setText(vtChon.getTenVaiTro());
					txtaMoTa.setText(vtChon.getMoTa());
				}
			}
		});
		tbKetQua.setBackground(new Color(255, 228, 181));
		tbKetQua.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"ID", "T\u00EAn vai tr\u00F2", "M\u00F4 t\u1EA3 c\u00F4ng vi\u1EC7c"
			}
		));
		tbKetQua.getColumnModel().getColumn(0).setResizable(false);
		tbKetQua.getColumnModel().getColumn(0).setPreferredWidth(30);
		tbKetQua.getColumnModel().getColumn(1).setResizable(false);
		tbKetQua.getColumnModel().getColumn(1).setPreferredWidth(100);
		tbKetQua.getColumnModel().getColumn(2).setResizable(false);
		tbKetQua.getColumnModel().getColumn(2).setPreferredWidth(200);
		scrollPane_1.setViewportView(tbKetQua);
		
		btnNhapMoi = new JButton("Nhập mới");
		btnNhapMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				xuLyButton(false);
				btnThem.setEnabled(true);
				txtTenVaiTro.setText(null);
				txtTenVaiTro.grabFocus();
				txtaMoTa.setText(null);
			}
		});
		btnNhapMoi.setBounds(361, 20, 89, 23);
		getContentPane().add(btnNhapMoi);
		
		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String tenVT, moTa;
				try {
					tenVT = txtTenVaiTro.getText().trim();
					moTa = txtaMoTa.getText().trim();
					
					if(tenVT.isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Tên của vai trò không được bỏ trống","Thông báo",JOptionPane.WARNING_MESSAGE);
						return;
					}
					else if (moTa.isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Thông tin mô tả của vai trò không được bỏ trống","Thông báo",JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					dsKiemTraTenVT = VaiTroBL.docTheoTenVaiTro(tenVT);
					
					if(!dsKiemTraTenVT.isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Vai trò "+tenVT+" đã tồn tại, không thể thêm","Kết quả",JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					VaiTro vt = new VaiTro(0, tenVT, moTa);
					
					VaiTroBL.themVaiTro(vt);
					xuatDS();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(rootPane,"Vui lòng kiểm tra lại thông tin","Lỗi nhập sai dữ liệu",JOptionPane.WARNING_MESSAGE);
				}
			
			}
		});
		btnThem.setBounds(61, 215, 89, 23);
		getContentPane().add(btnThem);
		
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(vtChon != null) {
					String tenVT, moTa;
					try {
						tenVT = txtTenVaiTro.getText().trim();
						moTa = txtaMoTa.getText().trim();
						
						if(tenVT.isEmpty()) {
							JOptionPane.showMessageDialog(rootPane, "Tên vai trò không được bỏ trống","Thông báo",JOptionPane.WARNING_MESSAGE);
							txtTenVaiTro.grabFocus();
							return;
						}
						else if (moTa.isEmpty()) {
							JOptionPane.showMessageDialog(rootPane, "Mô tả không được bỏ trống","Thông báo",JOptionPane.WARNING_MESSAGE);
							txtaMoTa.grabFocus();
							return;
						}
						else if(vtChon.getTenVaiTro().equals(tenVT) && vtChon.getMoTa().equals(moTa)) {
							JOptionPane.showMessageDialog(rootPane, "Thông tin của vai trò người dùng không có gì thay đổi","Thông báo",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						
						VaiTro vt = new VaiTro(vtChon.getId(), tenVT, moTa);
						
						VaiTroBL.capNhatVaiTro(vt);
						xuatDS();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(rootPane,"Vui lòng kiểm tra lại thông tin","Lỗi nhập sai dữ liệu",JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn vai trò cần cập nhật","Thông báo",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnCapNhat.setBounds(211, 215, 89, 23);
		getContentPane().add(btnCapNhat);
		
		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(vtChon != null) {
					
					int kqChon = JOptionPane.showConfirmDialog(rootPane, "Bạn thật sự muốn xóa vai trò này","Chú ý",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(kqChon == JOptionPane.YES_OPTION) {
						VaiTroBL.xoaVaiTro(vtChon.getId());
						xuatDS();
						vtChon = null;
						txtTenVaiTro.setText(null);
						txtaMoTa.setText(null);
					}
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn vai trò cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
			}
		});
		btnXoa.setBounds(361, 215, 89, 23);
		getContentPane().add(btnXoa);

	}
}
