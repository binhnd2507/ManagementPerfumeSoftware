package presentationLayers;

import java.awt.EventQueue;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import businessLogics.NguoiDungBL;
import businessLogics.VaiTroBL;
import dataTransferObjects.NguoiDung;
import dataTransferObjects.VaiTro;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmQuanLyNguoiDung extends JInternalFrame {
	private JTextField txtTen;
	private JTextField txtID;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTextField txtHoTen;
	private JTextField txtDienThoai;
	private JTable tbKetQua;
	
	private DefaultComboBoxModel<String> dcm;
	private DefaultTableModel dtm;
	private Hashtable<String, Integer> dsVaiTro = VaiTroBL.docTatCa();
	private List<NguoiDung> dsnd;
	private JComboBox cbbVaiTro;
	private NguoiDung ndChon;

	/**
	 * Launch the application.
	 */
	
	public void xuatDS() {
		
		dtm = (DefaultTableModel) tbKetQua.getModel();
		dtm.setRowCount(0);
		for(NguoiDung nd : dsnd) {
			dtm.addRow(new Object[] {nd.getId(), nd.getEmail(), nd.getHoTen(), nd.getDiaChi(), nd.getDienThoai()});
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmQuanLyNguoiDung frame = new FrmQuanLyNguoiDung();
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
	public FrmQuanLyNguoiDung() {
		setClosable(true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				
				dcm = (DefaultComboBoxModel<String>) cbbVaiTro.getModel();
				for(String key : dsVaiTro.keySet()) {
					dcm.addElement(key);
				}
			}
		});
		setTitle("Quản lý người dùng");
		setBounds(100, 100, 784, 522);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên người dùng");
		lblNewLabel.setBounds(38, 28, 96, 14);
		getContentPane().add(lblNewLabel);
		
		txtTen = new JTextField();
		txtTen.setBounds(144, 25, 232, 20);
		getContentPane().add(txtTen);
		txtTen.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(38, 292, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(94, 289, 40, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setBounds(38, 337, 46, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(94, 331, 297, 20);
		getContentPane().add(txtEmail);
		
		JLabel lblNewLabel_1_2 = new JLabel("Địa chỉ");
		lblNewLabel_1_2.setBounds(38, 381, 46, 14);
		getContentPane().add(lblNewLabel_1_2);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(94, 375, 297, 20);
		getContentPane().add(txtDiaChi);
		
		JLabel lblNewLabel_1_3 = new JLabel("Vai trò");
		lblNewLabel_1_3.setBounds(452, 292, 46, 14);
		getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Họ tên");
		lblNewLabel_1_1_1.setBounds(452, 334, 46, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("ĐTDĐ");
		lblNewLabel_1_2_1.setBounds(452, 378, 46, 14);
		getContentPane().add(lblNewLabel_1_2_1);
		
		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(508, 331, 222, 20);
		getContentPane().add(txtHoTen);
		
		txtDienThoai = new JTextField();
		txtDienThoai.setColumns(10);
		txtDienThoai.setBounds(508, 375, 222, 20);
		getContentPane().add(txtDienThoai);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 82, 692, 151);
		getContentPane().add(scrollPane);
		
		tbKetQua = new JTable();
		tbKetQua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				int dongChon = tbKetQua.getSelectedRow();
				if(dongChon < 0) {
					ndChon = null;
				}
				else {
					ndChon = dsnd.get(dongChon);
				}
				
				if(ndChon != null) {
					txtID.setText(String.valueOf(ndChon.getId()));
					txtEmail.setText(ndChon.getEmail());
					txtDiaChi.setText(ndChon.getDiaChi());
					VaiTro vaiTro = VaiTroBL.docTheoIdVaiTro(ndChon.getIdVaiTro());
					cbbVaiTro.setSelectedItem(vaiTro.getTenVaiTro());
					txtHoTen.setText(ndChon.getHoTen());
					txtDienThoai.setText(ndChon.getDienThoai());
				}
			}
		});
		tbKetQua.setBackground(new Color(255, 228, 181));
		tbKetQua.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Email", "H\u1ECD t\u00EAn", "\u0110\u1ECBa ch\u1EC9", "\u0110TD\u0110"
			}
		));
		tbKetQua.getColumnModel().getColumn(0).setPreferredWidth(30);
		tbKetQua.getColumnModel().getColumn(0).setMaxWidth(2147483645);
		tbKetQua.getColumnModel().getColumn(1).setResizable(false);
		tbKetQua.getColumnModel().getColumn(1).setPreferredWidth(200);
		tbKetQua.getColumnModel().getColumn(2).setPreferredWidth(150);
		tbKetQua.getColumnModel().getColumn(3).setPreferredWidth(200);
		scrollPane.setViewportView(tbKetQua);
		
		cbbVaiTro = new JComboBox();
		cbbVaiTro.setBounds(508, 288, 150, 20);
		getContentPane().add(cbbVaiTro);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String tenND = txtTen.getText();
				
				if(tenND.isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Bạn phải nhập tên cần tìm","Kết quả",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				dsnd = NguoiDungBL.docTheoTenNguoiDung(tenND);
				
				if(dsnd.isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Không tìm thấy tên này trong danh sách","Kết quả",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				xuatDS();
			}
		});
		btnTim.setBounds(386, 24, 89, 23);
		getContentPane().add(btnTim);
		
		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(ndChon!=null) {
					String email, hoTen, diaChi, dienThoai;
					int idVaiTro;
					
					try {
						email = txtEmail.getText().trim();
						hoTen = txtHoTen.getText().trim();
						diaChi = txtDiaChi.getText().trim();
						dienThoai = txtDienThoai.getText().trim();
						
						idVaiTro = dsVaiTro.get(cbbVaiTro.getSelectedItem());
						
						if(ndChon.getEmail().equals(email) && ndChon.getHoTen().equals(hoTen) && ndChon.getDiaChi().equals(diaChi) 
								&& ndChon.getDienThoai().equals(dienThoai) && ndChon.getIdVaiTro() == idVaiTro) {
							JOptionPane.showMessageDialog(rootPane, "Thông tin người dùng không có gì thay đổi","Thông báo",1);
							return;
						}
						
						if(email.isEmpty()) {
							JOptionPane.showMessageDialog(rootPane, "Địa chỉ email không được để trống!","Lỗi nhập sai dữ liệu",1);
							return;
						}
						else if(hoTen.isEmpty()) {
							JOptionPane.showMessageDialog(rootPane, "Tên người dùng không được để trống!","Lỗi nhập sai dữ liệu",1);
							return;
						}
						else if(diaChi.isEmpty()) {
							JOptionPane.showMessageDialog(rootPane, "Địa chỉ không được để trống!","Lỗi nhập sai dữ liệu",1);
							return;
						}
						else if(dienThoai.isEmpty()) {
							JOptionPane.showMessageDialog(rootPane, "Số điện thoại không được để trống!","Lỗi nhập sai dữ liệu",1);
							return;
						}
						
						NguoiDung nd = new NguoiDung(ndChon.getId(), email, ndChon.getPassword(), hoTen, diaChi, dienThoai, idVaiTro);
						
						NguoiDungBL.capNhatNguoiDung(nd);
						xuatDS();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(rootPane,"Vui lòng kiểm tra lại và nhập đầy đủ thông tin cập nhật!","Lỗi nhập sai dữ liệu",1);
					}
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn người dùng cần cập nhật!","Thông báo",1);
					return;
				}
			}
		});
		btnCapNhat.setBounds(112, 435, 89, 23);
		getContentPane().add(btnCapNhat);

	}
}
