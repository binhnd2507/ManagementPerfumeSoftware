package presentationLayers;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import businessLogics.LoaiBL;
import businessLogics.SanPhamBL;
import businessLogics.ThuongHieuBL;
import dataTransferObjects.SanPham;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class FrmThemSanPham extends JInternalFrame {
	private JTextField txtTenSP;
	private JTextField txtHinh;
	private JTextField txtDonGia;
	private JTextField txtDonGiaKM;
	private JTextField txtSoLuong;
	private JComboBox cbbLoai;
	private JComboBox cbbThuongHieu;
	
	private List<SanPham> dsKiemTraTenSP;
	private Hashtable<String, Integer> dsLoai= LoaiBL.docTatCa();
	private Hashtable<String, Integer> dsThuongHieu= ThuongHieuBL.docTatCa();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmThemSanPham frame = new FrmThemSanPham();
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
	public FrmThemSanPham() {
		setClosable(true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				DefaultComboBoxModel<String> dcmLoai= (DefaultComboBoxModel<String>) cbbLoai.getModel();
				DefaultComboBoxModel<String> dcmThuongHieu= (DefaultComboBoxModel<String>) cbbThuongHieu.getModel();
				
				for(String key: dsLoai.keySet())
					dcmLoai.addElement(key);
				for(String key: dsThuongHieu.keySet())
					dcmThuongHieu.addElement(key);
			}
		});
		setTitle("Thêm sản phẩm mới");
		setBounds(100, 100, 542, 422);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên sản phẩm");
		lblNewLabel.setBounds(22, 11, 90, 14);
		getContentPane().add(lblNewLabel);
		
		txtTenSP = new JTextField();
		txtTenSP.setBounds(119, 8, 377, 20);
		getContentPane().add(txtTenSP);
		txtTenSP.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mô tả");
		lblNewLabel_1.setBounds(22, 47, 90, 14);
		getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(119, 47, 377, 60);
		getContentPane().add(scrollPane);
		
		JTextArea txtaMoTa = new JTextArea();
		scrollPane.setViewportView(txtaMoTa);
		
		JLabel lblNewLabel_2 = new JLabel("Hình");
		lblNewLabel_2.setBounds(22, 128, 90, 14);
		getContentPane().add(lblNewLabel_2);
		
		txtHinh = new JTextField();
		txtHinh.setBounds(119, 125, 333, 20);
		getContentPane().add(txtHinh);
		txtHinh.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Đơn giá");
		lblNewLabel_3.setBounds(22, 161, 90, 14);
		getContentPane().add(lblNewLabel_3);
		
		txtDonGia = new JTextField();
		txtDonGia.setBounds(119, 158, 147, 20);
		getContentPane().add(txtDonGia);
		txtDonGia.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Đơn giá KM");
		lblNewLabel_4.setBounds(22, 197, 90, 14);
		getContentPane().add(lblNewLabel_4);
		
		txtDonGiaKM = new JTextField();
		txtDonGiaKM.setBounds(119, 194, 147, 20);
		getContentPane().add(txtDonGiaKM);
		txtDonGiaKM.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Số lượng");
		lblNewLabel_5.setBounds(22, 235, 90, 14);
		getContentPane().add(lblNewLabel_5);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(119, 232, 147, 20);
		getContentPane().add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Loại");
		lblNewLabel_6.setBounds(22, 270, 90, 14);
		getContentPane().add(lblNewLabel_6);
		
		cbbLoai = new JComboBox();
		cbbLoai.setBounds(119, 266, 147, 22);
		getContentPane().add(cbbLoai);
		
		JLabel lblNewLabel_7 = new JLabel("Thương hiệu");
		lblNewLabel_7.setBounds(22, 307, 90, 14);
		getContentPane().add(lblNewLabel_7);
		
		cbbThuongHieu = new JComboBox();
		cbbThuongHieu.setBounds(119, 303, 147, 22);
		getContentPane().add(cbbThuongHieu);
		
		JLabel lblHinh = new JLabel("Hình Ảnh");
		lblHinh.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinh.setBounds(305, 161, 188, 164);
		getContentPane().add(lblHinh);
		
		JButton btnHinh = new JButton("...");
		btnHinh.addActionListener(new ActionListener() {
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
		btnHinh.setBounds(462, 124, 34, 23);
		getContentPane().add(btnHinh);
		
		JButton btnThem = new JButton("Thêm mới");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tenSP, moTa, hinhAnh;
				double donGia, donGiaKM;
				int soLuong, idLoai, idThuongHieu;
				Date ngayTao;
				try {
				
					tenSP= txtTenSP.getText().trim();
					moTa= txtaMoTa.getText().trim();
					File fileHinh= new File(txtHinh.getText());
					hinhAnh= fileHinh.getName();
					
					donGia= Double.parseDouble(txtDonGia.getText());
					donGiaKM= Double.parseDouble(txtDonGiaKM.getText());
					
					soLuong= Integer.parseInt(txtSoLuong.getText());
					idLoai= dsLoai.get(cbbLoai.getSelectedItem());
					
					idThuongHieu= dsThuongHieu.get(cbbThuongHieu.getSelectedItem());
					
					ngayTao= new Date(new java.util.Date().getTime());
					
					if(tenSP.isEmpty()) {
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
					
					//Kiểm tra tên sản phẩm thêm vào đã có trong cơ sở dữ liêu chưa
					dsKiemTraTenSP = SanPhamBL.docTheoTenSanPham(tenSP);
					
					if(!dsKiemTraTenSP.isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Sản phẩm "+tenSP+ " đã tồn tại, không thể thêm","Thông báo",JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					SanPham sp= new SanPham(0, tenSP, moTa, hinhAnh, donGia, donGiaKM, soLuong, ngayTao, 1, idLoai, idThuongHieu);
					
					SanPhamBL.themSanPham(sp);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(rootPane,"Vui lòng kiểm tra lại thông tin","Lỗi nhập sai dữ liệu",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnThem.setBounds(111, 347, 96, 23);
		getContentPane().add(btnThem);
		
		JButton btnTiepTuc = new JButton("Tiếp tục");
		btnTiepTuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTenSP.setText(null);
				txtTenSP.grabFocus();
				txtaMoTa.setText(null);
				txtHinh.setText(null);
				txtDonGia.setText(null);
				txtDonGiaKM.setText(null);
				txtSoLuong.setText(null);
				lblHinh.setIcon(null);
			}
		});
		btnTiepTuc.setBounds(318, 347, 96, 23);
		getContentPane().add(btnTiepTuc);

	}
}
