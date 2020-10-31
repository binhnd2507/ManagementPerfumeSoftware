package presentationLayers;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

import businessLogics.QuangCaoBL;
import dataTransferObjects.QuangCao;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class FrmCapNhatXoaQuangCao extends JInternalFrame {
	private JTextField txtHinh;
	private JTable tbKetQua;
	
	private List<QuangCao> dsqc;
	private List<QuangCao> dsKiemTraHinhQC;
	
	private DefaultTableModel dtm;
	private int dongChon;
	private QuangCao qcChon;

	/**
	 * Launch the application.
	 */
	
	public void xuatDanhSach() {
		
		dsqc = QuangCaoBL.docToanBo();
		
		dtm = (DefaultTableModel) tbKetQua.getModel();
		dtm.setRowCount(0);
		for(QuangCao qc : dsqc) {
			dtm.addRow(new Object[] {qc.getId(), qc.getHinhAnh(), qc.getThongDiep(), qc.getThongTinChiTiet(), qc.getNgayDang()});
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCapNhatXoaQuangCao frame = new FrmCapNhatXoaQuangCao();
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
	public FrmCapNhatXoaQuangCao() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
			
				xuatDanhSach();
			}
		});
		setClosable(true);
		setTitle("Cập nhật - Xóa quảng cáo");
		setBounds(100, 100, 684, 511);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hình");
		lblNewLabel.setBounds(46, 23, 46, 14);
		getContentPane().add(lblNewLabel);
		
		txtHinh = new JTextField();
		txtHinh.setBounds(139, 20, 432, 20);
		getContentPane().add(txtHinh);
		txtHinh.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Thông điệp");
		lblNewLabel_1.setBounds(46, 73, 83, 14);
		getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(139, 73, 247, 76);
		getContentPane().add(scrollPane);
		
		JTextArea txtaThongDiep = new JTextArea();
		scrollPane.setViewportView(txtaThongDiep);
		
		JLabel lblNewLabel_2 = new JLabel("Thông tin chi tiết");
		lblNewLabel_2.setBounds(46, 173, 121, 14);
		getContentPane().add(lblNewLabel_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(139, 198, 247, 76);
		getContentPane().add(scrollPane_1);
		
		JTextArea txtaThongTin = new JTextArea();
		scrollPane_1.setViewportView(txtaThongTin);
		
		JLabel lblHinh = new JLabel("Hình ảnh");
		lblHinh.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinh.setBounds(419, 66, 197, 218);
		getContentPane().add(lblHinh);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(46, 355, 573, 103);
		getContentPane().add(scrollPane_2);
		
		tbKetQua = new JTable();
		tbKetQua.setBackground(new Color(255, 228, 181));
		tbKetQua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				dongChon = tbKetQua.getSelectedRow();
				if(dongChon < 0) {
					qcChon = null;
				}
				else{
					qcChon = dsqc.get(dongChon);
				}
				
				if(qcChon != null) {
					txtHinh.setText(qcChon.getHinhAnh());
					txtaThongDiep.setText(qcChon.getThongDiep());
					txtaThongTin.setText(qcChon.getThongTinChiTiet());
					ImageIcon icon = new ImageIcon("src/img/"+qcChon.getHinhAnh());
					icon.setImage(icon.getImage().getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_DEFAULT));
					lblHinh.setIcon(icon);
				}
			}
		});
		tbKetQua.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "H\u00ECnh \u1EA3nh", "Th\u00F4ng \u0111i\u1EC7p", "Th\u00F4ng tin chi ti\u1EBFt", "Ng\u00E0y \u0111\u0103ng"
			}
		));
		tbKetQua.getColumnModel().getColumn(0).setPreferredWidth(20);
		tbKetQua.getColumnModel().getColumn(1).setPreferredWidth(130);
		tbKetQua.getColumnModel().getColumn(2).setPreferredWidth(150);
		tbKetQua.getColumnModel().getColumn(3).setPreferredWidth(150);
		scrollPane_2.setViewportView(tbKetQua);
		
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
		btnHinh.setBounds(581, 19, 35, 23);
		getContentPane().add(btnHinh);
		
		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(qcChon != null) {
					
					String hinhAnh, thongDiep, thongTinCT;
					Date ngayDang;
					
					File fHinh = new File(txtHinh.getText().trim());
					hinhAnh = fHinh.getName();
					thongDiep = txtaThongDiep.getText().trim();
					thongTinCT = txtaThongTin.getText().trim();
					ngayDang = new Date(new java.util.Date().getTime());
					
					if(hinhAnh.isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Thông tin của hình ảnh quảng cáo không được để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
						txtHinh.grabFocus();
						return;
					}
					else if(thongDiep.isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Thông tin của thông điệp quảng cáo không được để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
						txtaThongDiep.grabFocus();
						return;
					}
					else if(thongTinCT.isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Thông tin chi tiết quảng cáo không được để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
						txtaThongTin.grabFocus();
						return;
					}
					else if(qcChon.getHinhAnh().equals(hinhAnh) && qcChon.getThongDiep().equals(thongDiep) && qcChon.getThongTinChiTiet().equals(thongTinCT)) {
						JOptionPane.showMessageDialog(rootPane, "Thông tin của quảng cáo không có gì thay đổi", "Thông báo", JOptionPane.WARNING_MESSAGE);
						return;
					}
						
					QuangCao qc = new QuangCao(qcChon.getId(), hinhAnh, thongDiep, thongTinCT, ngayDang);
						
					QuangCaoBL.capNhatQuangCao(qc);
					xuatDanhSach();
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn quảng cáo cần cập nhật","Thông báo", JOptionPane.WARNING_MESSAGE);
					return;
				}
			
			}
		});
		btnCapNhat.setBounds(163, 309, 89, 23);
		getContentPane().add(btnCapNhat);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(qcChon != null) {
					int kqChon = JOptionPane.showConfirmDialog(rootPane, "Bạn thật sự muốn xóa quảng cáo này", 
							"Chú ý", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(kqChon == JOptionPane.YES_OPTION) {
						QuangCaoBL.xoaQuangCao(qcChon.getId());
						xuatDanhSach();
						qcChon = null;
						txtHinh.setText(null);
						txtaThongDiep.setText(null);
						txtaThongTin.setText(null);
						lblHinh.setIcon(null);
					}
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn quảng cáo cần xóa","Thông báo", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnXoa.setBounds(415, 309, 89, 23);
		getContentPane().add(btnXoa);

	}
}
