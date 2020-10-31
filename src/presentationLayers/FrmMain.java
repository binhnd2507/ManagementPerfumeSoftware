package presentationLayers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import dataTransferObjects.NguoiDung;

import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class FrmMain {

	private JFrame frmQunLPerform;
	private JDesktopPane desktopPane;
	
	protected static JMenu mnSanPham;
	protected static JMenu mnDonHang;
	protected static JMenu mnQuangCao;
	protected static JMenu mnNguoiDung;
	protected static NguoiDung nguoiDung;
	protected static JTextField txtTenNguoiDung;
	protected static JTextField txtVaiTro;
	protected static JButton btnDangXuat;
	protected static JMenuItem mntmQLDonHang;
	private  static JLabel lblHinh;

	/**
	 * Launch the application.
	 */
	
	public static void xuLyMenu(boolean b) {
		mnSanPham.setEnabled(b);
		mnDonHang.setEnabled(b);
		mnQuangCao.setEnabled(b);
		mnNguoiDung.setEnabled(b);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					FrmMain window = new FrmMain();
					FrmDangNhap fDangNhap= new FrmDangNhap();
					window.desktopPane.add(fDangNhap);
					fDangNhap.setVisible(true);
					btnDangXuat.setEnabled(false);
					xuLyMenu(false);
					window.frmQunLPerform.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQunLPerform = new JFrame();
		frmQunLPerform.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmQunLPerform.setTitle("Qu\u1EA3n l\u00FD Perfume Shop");
		frmQunLPerform.setBounds(100, 100, 783, 539);
		frmQunLPerform.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmQunLPerform.setJMenuBar(menuBar);
		
		mnSanPham = new JMenu("S\u1EA3n ph\u1EA9m");
		menuBar.add(mnSanPham);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Qu\u1EA3n l\u00FD th\u01B0\u01A1ng hi\u1EC7u");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				FrmQuanLyThuongHieuSanPham fth= new FrmQuanLyThuongHieuSanPham();
				desktopPane.add(fth);
				fth.setVisible(true);
			}
		});
		mnSanPham.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Qu\u1EA3n l\u00FD lo\u1EA1i");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				FrmQuanLyLoaiSanPham fQuanLyLoaiSanPham = new FrmQuanLyLoaiSanPham();
				desktopPane.add(fQuanLyLoaiSanPham);
				fQuanLyLoaiSanPham.setVisible(true);
			}
		});
		mnSanPham.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Qu\u1EA3n l\u00FD s\u1EA3n ph\u1EA9m");
		mnSanPham.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Th\u00EAm m\u1EDBi s\u1EA3n ph\u1EA9m");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmThemSanPham ftsp= new FrmThemSanPham();
				desktopPane.add(ftsp);
				ftsp.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("C\u1EADp nh\u1EADt s\u1EA3n ph\u1EA9m");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCapNhatXoaSanPham fcnsp= new FrmCapNhatXoaSanPham();
				desktopPane.add(fcnsp);
				fcnsp.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		mnDonHang = new JMenu("\u0110\u01A1n h\u00E0ng");
		menuBar.add(mnDonHang);
		
		mntmQLDonHang = new JMenuItem("Qu\u1EA3n l\u00FD \u0111\u01A1n h\u00E0ng");
		mntmQLDonHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmQuanLyDonHang fQuanLyDH= new FrmQuanLyDonHang();
				desktopPane.add(fQuanLyDH);
				fQuanLyDH.setVisible(true);
			}
		});
		mnDonHang.add(mntmQLDonHang);
		
		mnQuangCao = new JMenu("Qu\u1EA3ng c\u00E1o");
		menuBar.add(mnQuangCao);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Th\u00EAm m\u1EDBi qu\u1EA3ng c\u00E1o");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				FrmThemQuangCao fThemQuangCao = new FrmThemQuangCao();
				desktopPane.add(fThemQuangCao);
				fThemQuangCao.setVisible(true);
			}
		});
		mnQuangCao.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Cập nhật - Xóa quảng cáo");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				FrmCapNhatXoaQuangCao fCapNhatXoaQC = new FrmCapNhatXoaQuangCao();
				desktopPane.add(fCapNhatXoaQC);
				fCapNhatXoaQC.setVisible(true);
			}
		});
		mnQuangCao.add(mntmNewMenuItem_7);
		
		mnNguoiDung = new JMenu("Ng\u01B0\u1EDDi d\u00F9ng");
		menuBar.add(mnNguoiDung);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Qu\u1EA3n l\u00FD vai tr\u00F2");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				FrmQuanLyVaiTro fQuanLyVT = new FrmQuanLyVaiTro();
				desktopPane.add(fQuanLyVT);
				fQuanLyVT.setVisible(true);
			}
		});
		mnNguoiDung.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Qu\u1EA3n l\u00FD ng\u01B0\u1EDDi d\u00F9ng");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				FrmQuanLyNguoiDung fQuanLyND = new FrmQuanLyNguoiDung();
				desktopPane.add(fQuanLyND);
				fQuanLyND.setVisible(true);
			}
		});
		mnNguoiDung.add(mntmNewMenuItem_9);
		
		desktopPane = new JDesktopPane();
		frmQunLPerform.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmDangNhap fDangNhap= new FrmDangNhap();
				FrmMain.this.desktopPane.add(fDangNhap);
				if(JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát chương trình hay không?", "Thông báo",2)==0) {
					nguoiDung= null;
					txtTenNguoiDung.setText(null);
					txtVaiTro.setText(null);
					xuLyMenu(false);
					btnDangXuat.setEnabled(false);
					fDangNhap.setVisible(true);
				}
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("Vai trò");
		lblNewLabel_3.setForeground(new Color(199, 21, 133));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblNewLabel_3.setBounds(545, 104, 43, 14);
		desktopPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Tên người dùng");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblNewLabel_2.setForeground(new Color(199, 21, 133));
		lblNewLabel_2.setBounds(491, 59, 108, 27);
		desktopPane.add(lblNewLabel_2);
		btnDangXuat.setBounds(642, 132, 96, 23);
		desktopPane.add(btnDangXuat);
		
		JLabel lblNewLabel = new JLabel(" Bull Shop");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Pristina", Font.BOLD, 50));
		lblNewLabel.setForeground(new Color(255, 192, 203));
		lblNewLabel.setBounds(35, 11, 447, 71);
		desktopPane.add(lblNewLabel);
		
		lblHinh = new JLabel("");
		lblHinh.setOpaque(true);
		lblHinh.setBackground(new Color(255, 255, 255));
		lblHinh.setBounds(0, 0, 1302, 722);
		
		ImageIcon hinhMain= new ImageIcon("src/img/lifestyle-desktop-liw.png");
		hinhMain.setImage(hinhMain.getImage().getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_DEFAULT));
		lblHinh.setIcon(hinhMain);
		
		desktopPane.add(lblHinh);
		
		txtTenNguoiDung = new JTextField();
		txtTenNguoiDung.setEditable(false);
		txtTenNguoiDung.setBounds(598, 63, 140, 20);
		desktopPane.add(txtTenNguoiDung);
		txtTenNguoiDung.setColumns(10);
		
		txtVaiTro = new JTextField();
		txtVaiTro.setEditable(false);
		txtVaiTro.setBounds(598, 101, 140, 20);
		desktopPane.add(txtVaiTro);
		txtVaiTro.setColumns(10);
	}
}
