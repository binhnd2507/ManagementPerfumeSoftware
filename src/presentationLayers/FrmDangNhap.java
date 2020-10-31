package presentationLayers;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import businessLogics.NguoiDungBL;
import businessLogics.VaiTroBL;
import dataTransferObjects.NguoiDung;
import dataTransferObjects.VaiTro;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class FrmDangNhap extends JInternalFrame {
	private JTextField txtEmail;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDangNhap frame = new FrmDangNhap();
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
	public FrmDangNhap() {
	
		setTitle("\u0110\u0103ng nh\u1EADp");
		setBounds(100, 100, 466, 263);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setForeground(new Color(192, 192, 192));
		lblNewLabel.setBounds(190, 14, 58, 14);
		getContentPane().add(lblNewLabel);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(258, 11, 182, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(192, 192, 192));
		lblNewLabel_1.setBounds(190, 55, 58, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(258, 52, 182, 20);
		getContentPane().add(txtPassword);
		
		JButton btnDangNhap = new JButton("\u0110\u0103ng nh\u1EADp");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email, password;
				email= txtEmail.getText();
				password= new String(txtPassword.getPassword());
				
				NguoiDung nd= NguoiDungBL.docTheoEmailPassword(email, password);
				
				if(nd==null)
					JOptionPane.showMessageDialog(rootPane, "Email hoặc mật khẩu sai!");
				else {
					JOptionPane.showMessageDialog(rootPane, "Đăng nhập thành công");
					dispose();
					FrmMain.nguoiDung= nd;
					FrmMain.txtTenNguoiDung.setText(nd.getHoTen());
					
					VaiTro vaiTro= VaiTroBL.docTheoIdVaiTro(nd.getIdVaiTro());
					FrmMain.txtVaiTro.setText(vaiTro.getTenVaiTro());
					FrmMain.btnDangXuat.setEnabled(true);
					switch(vaiTro.getId()) {
					case 1: 
						FrmMain.xuLyMenu(false);
						break;
					case 2:
						FrmMain.xuLyMenu(true);
						break;
					case 3:
					case 4:
						FrmMain.xuLyMenu(false);
						FrmMain.mnDonHang.setEnabled(true);
						break;
					}
				}
			}
		});
		btnDangNhap.setBounds(341, 96, 99, 23);
		getContentPane().add(btnDangNhap);
		
		JLabel lblHinh = new JLabel("");
		lblHinh.setBounds(0, 0, 460, 233);
		
		ImageIcon hinhDangNhap= new ImageIcon("src/img/26758b23e6326ceefdd98eb211cfb186.jpg");
		hinhDangNhap.setImage(hinhDangNhap.getImage().getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_DEFAULT));
		lblHinh.setIcon(hinhDangNhap);
		
		getContentPane().add(lblHinh);

	}
}
