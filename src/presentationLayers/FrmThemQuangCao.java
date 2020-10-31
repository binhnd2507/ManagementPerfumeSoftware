package presentationLayers;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import businessLogics.QuangCaoBL;
import dataTransferObjects.QuangCao;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FrmThemQuangCao extends JInternalFrame {
	private JTextField txtHinh;
	private JTextArea txtaThongDiep;
	private JTextArea txtaThongTin;
	
	private List<QuangCao> dsKiemTraQC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmThemQuangCao frame = new FrmThemQuangCao();
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
	public FrmThemQuangCao() {
		setClosable(true);
		setTitle("Thêm Quảng Cáo");
		setBounds(100, 100, 613, 371);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hình");
		lblNewLabel.setBounds(30, 32, 46, 14);
		getContentPane().add(lblNewLabel);
		
		txtHinh = new JTextField();
		txtHinh.setBounds(105, 29, 426, 20);
		getContentPane().add(txtHinh);
		txtHinh.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Thông điệp");
		lblNewLabel_1.setBounds(30, 88, 72, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Thông tin chi tiết");
		lblNewLabel_2.setBounds(30, 153, 113, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblHinh = new JLabel("Hình ảnh");
		lblHinh.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinh.setBounds(328, 72, 248, 224);
		getContentPane().add(lblHinh);
		
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
		btnHinh.setBounds(541, 28, 35, 23);
		getContentPane().add(btnHinh);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String hinhAnh, thongDiep, thongTinCT;
				Date ngayDang;
				
				File fileHinh = new File(txtHinh.getText());
				hinhAnh = fileHinh.getName();
				thongDiep = txtaThongDiep.getText();
				thongTinCT = txtaThongTin.getText();
				ngayDang = new Date(new java.util.Date().getTime());
				
				if(hinhAnh.isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Thông tin hình ảnh của quảng cáo sản phẩm không được để trống!","Thông báo",JOptionPane.WARNING_MESSAGE);
					txtHinh.grabFocus();
					return;
				}
				else if(thongDiep.isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Thông tin thông điệp của quảng cáo sản phẩm không được để trống!","Thông báo",JOptionPane.WARNING_MESSAGE);
					txtaThongDiep.grabFocus();
					return;
				}
				else if(thongTinCT.isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Thông tin chi tiết của quảng cáo sản phẩm không được để trống!","Thông báo",JOptionPane.WARNING_MESSAGE);
					txtaThongTin.grabFocus();
					return;
				}
				
				//Kiểm tra file hình ảnh đã có trong cơ sở dữ liệu chưa
				dsKiemTraQC = QuangCaoBL.docTheoHinhQuangCao(hinhAnh);
				
				if(!dsKiemTraQC.isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Quảng cáo "+hinhAnh+" đã tồn tại, không thể thêm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				QuangCao qc = new QuangCao(0, hinhAnh, thongDiep, thongTinCT, ngayDang);
				
				QuangCaoBL.themQuangCao(qc);
			}
		});
		btnThem.setBounds(30, 287, 113, 30);
		getContentPane().add(btnThem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(105, 88, 192, 44);
		getContentPane().add(scrollPane);
		
		txtaThongDiep = new JTextArea();
		scrollPane.setViewportView(txtaThongDiep);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(105, 178, 192, 87);
		getContentPane().add(scrollPane_1);
		
		txtaThongTin = new JTextArea();
		scrollPane_1.setViewportView(txtaThongTin);
		
		JButton btnTiepTuc = new JButton("Tiếp tục");
		btnTiepTuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				txtHinh.setText(null);
				txtHinh.grabFocus();
				txtaThongDiep.setText(null);
				txtaThongTin.setText(null);
				lblHinh.setIcon(null);
			}
		});
		btnTiepTuc.setBounds(184, 287, 113, 30);
		getContentPane().add(btnTiepTuc);

	}
}
