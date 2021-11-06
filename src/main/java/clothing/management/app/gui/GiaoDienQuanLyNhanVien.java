package clothing.management.app.gui;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import clothing.management.dao.NhanVienDao;
import clothing.management.entity.LoaiSanPham;
import clothing.management.entity.NhaCungCap;
import clothing.management.entity.NhanVien;
import clothing.management.entity.SanPham;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GiaoDienQuanLyNhanVien extends JFrame implements ActionListener {
	MongoClient client = MongoClients.create();
	NhanVienDao nhanVienDao = new NhanVienDao(client);
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnBack;
	private JPanel pn1;
	private JPanel head;
	private JPanel pn2;
	private DefaultTableModel dtm;
	private JTable table;
	private JComboBox<String> cboFind;
	private JPanel pn3;
	private JPanel pn3BL;
	private JTextField txtFind;
	private JButton btnSearch;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnPrint;

	public GiaoDienQuanLyNhanVien() {
		this.setTitle("Giao Diện Quản Lý Nhân Viên");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//setUndecorated(true);
		createGUI();
	}

	private void createGUI() {
		JPanel header;

		// Pháº§n North
		pn1 = new JPanel();
		head = new JPanel();
		head.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		head.setLayout(new FlowLayout(FlowLayout.LEFT));
		head.setPreferredSize(new Dimension(150, 50));
		btnBack = new JButton("Quay lại");
		btnBack.setFont(new Font("Arial", Font.BOLD, 20));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					btnBackActionPerformed(evt);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		head.add(btnBack);
		header = new JPanel();
		header.setLayout(new FlowLayout(FlowLayout.CENTER));
		header.setPreferredSize(new Dimension(1350, 90));
		JLabel lblHeader = new JLabel("Quản Lý Nhân Viên");
		lblHeader.setFont(new Font("Arial", Font.BOLD, 70));
		header.add(lblHeader);
		pn1.add(header);
		pn1.add(head);
// code  table

		pn2 = new JPanel();
		pn2.setBorder(BorderFactory.createTitledBorder("Danh Sách Nhân Viên"));

//get table  fullScreen
		pn2.setLayout(new BoxLayout(pn2, BoxLayout.PAGE_AXIS));

		String[] cols = { "Mã nhân viên", "Họ tên", "Số điện thoại", "Giới tính", "Email", "Ngày sinh", "CMND/CCCD" };
		dtm = new DefaultTableModel(cols, 0);
		table = new JTable(dtm);
		JScrollPane scroll = new JScrollPane(table);
//setsize for table
		scroll.setPreferredSize(new DimensionUIResource(1400, 600));
		pn2.add(scroll);

// code function
		pn3 = new JPanel();
		pn3.setPreferredSize(new Dimension(1500, 100));
		pn3.setBorder(BorderFactory.createTitledBorder("Các chức năng"));
		pn3BL = new JPanel();
		pn3BL.setPreferredSize(new DimensionUIResource(1400, 50));
		pn3BL.setBorder(BorderFactory.createLoweredBevelBorder());
		cboFind = new JComboBox<String>();
		cboFind.setEditable(false);
		cboFind.addItem("Chọn tiêu chí");
		cboFind.addItem("Tìm theo mã nhân viên");
		cboFind.addItem("Tìm theo tên nhân viên");
		cboFind.addItem("Tìm theo số điện thoại");
		txtFind = new JTextField(20);
		JLabel lbFind = new JLabel("Tìm kiếm theo:");
		btnSearch = new JButton("Tìm Kiếm");
		btnUpdate = new JButton("Cập Nhật");
		btnDelete = new JButton("Xoá");
		btnPrint = new JButton("Kết Xuất");
		pn3BL.add(txtFind);
		pn3BL.add(lbFind);
		pn3BL.add(cboFind);

		lbFind.setPreferredSize(new DimensionUIResource(100, 40));
		txtFind.setPreferredSize(new DimensionUIResource(100, 40));
		cboFind.setPreferredSize(new DimensionUIResource(150, 40));
		btnSearch.setPreferredSize(new DimensionUIResource(100, 40));
		btnUpdate.setPreferredSize(new DimensionUIResource(150, 40));
		btnDelete.setPreferredSize(new DimensionUIResource(100, 40));
		btnPrint.setPreferredSize(new DimensionUIResource(100, 40));
		pn3BL.add(btnSearch);
		pn3BL.add(btnUpdate);
		pn3BL.add(btnDelete);
		pn3BL.add(btnPrint);

		pn3.add(pn3BL);

		this.add(pn1, BorderLayout.NORTH);
		this.add(pn2, BorderLayout.CENTER);
		this.add(pn3, BorderLayout.SOUTH);
		btnDelete.addActionListener(this);
		btnSearch.addActionListener(this);
		btnUpdate.addActionListener(this);
		/**
		 * load dữ liệu lên table
		 */
//		List<NhanVien> danh

	}

	private void btnBackActionPerformed(ActionEvent evt) throws InterruptedException {
		new GiaoDienDieuKhien().setVisible(true);
		setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

	}

//	private void xuLyTimKiem() throws InterruptedException {
//		int select = cboFind.getSelectedIndex();
//		switch (select) {
//
//		case 0: {
//			List<NhanVien> dsNhanVien = sanPhamDao.layDanhSachSanPham();
//			dtm.setRowCount(0);
//			for (SanPham sanPham : dsSanPham) {
//				List<LoaiSanPham> tenLoai = loaiSPDao.timTheoTenLoai(sanPham.getLoaiSanPham().getMaLoai());
//				List<NhaCungCap> tenNCC = nhaCCDao.timTHeoTenNCC(sanPham.getNhaCungCap().getMaNhaCungCap());
//				dtm.addRow(new Object[] { sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getGia(),
//						sanPham.getSoLuong(), sanPham.getDanhSachKichCo(), sanPham.getDanhSachMauSac(),
//						sanPham.getThuongHieu(), tenLoai.get(0).getTenLoai(), tenNCC.get(0).getTenNhaCungCap() });
//			}
//			break;
//		}
//
//		case 1: {
//			String maSanPham = txtFind.getText();
//
//			List<SanPham> dsSanPham = sanPhamDao.timSanPhamTheoTieuChi("maSanPham", maSanPham);
//			dtm.setRowCount(0);
//
//			for (SanPham sanPham : dsSanPham) {
//				List<LoaiSanPham> tenLoai = loaiSPDao.timTheoTenLoai(sanPham.getLoaiSanPham().getMaLoai());
//				List<NhaCungCap> tenNCC = nhaCCDao.timTHeoTenNCC(sanPham.getNhaCungCap().getMaNhaCungCap());
//				dtm.addRow(new Object[] { sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getGia(),
//						sanPham.getSoLuong(), sanPham.getDanhSachKichCo(), sanPham.getDanhSachMauSac(),
//						sanPham.getThuongHieu(), tenLoai.get(0).getTenLoai(), tenNCC.get(0).getTenNhaCungCap() });
//			}
//			break;
//
//		}
//
//		case 2: {
//			String tenSanPham = txtFind.getText();
//
//			List<SanPham> dsSanPham = sanPhamDao.timSanPhamTheoTieuChi("tenSanPham", tenSanPham);
//
//			dtm.setRowCount(0);
//
//			for (SanPham sanPham : dsSanPham) {
//				List<LoaiSanPham> tenLoai = loaiSPDao.timTheoTenLoai(sanPham.getLoaiSanPham().getMaLoai());
//				List<NhaCungCap> tenNCC = nhaCCDao.timTHeoTenNCC(sanPham.getNhaCungCap().getMaNhaCungCap());
//				dtm.addRow(new Object[] { sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getGia(),
//						sanPham.getSoLuong(), sanPham.getDanhSachKichCo(), sanPham.getDanhSachMauSac(),
//						sanPham.getThuongHieu(), tenLoai.get(0).getTenLoai(), tenNCC.get(0).getTenNhaCungCap() });
//			}
//			break;
//		}
//
//		case 3: {
//			String thuongHieu = txtFind.getText();
//			List<SanPham> dsSanPham = sanPhamDao.timSanPhamTheoTieuChi("thuongHieu", thuongHieu);
//			dtm.setRowCount(0);
//			for (SanPham sanPham : dsSanPham) {
//				List<LoaiSanPham> tenLoai = loaiSPDao.timTheoTenLoai(sanPham.getLoaiSanPham().getMaLoai());
//				List<NhaCungCap> tenNCC = nhaCCDao.timTHeoTenNCC(sanPham.getNhaCungCap().getMaNhaCungCap());
//				dtm.addRow(new Object[] { sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getGia(),
//						sanPham.getSoLuong(), sanPham.getDanhSachKichCo(), sanPham.getDanhSachMauSac(),
//						sanPham.getThuongHieu(), tenLoai.get(0).getTenLoai(), tenNCC.get(0).getTenNhaCungCap() });
//			}
//			break;
//		}
//		}
//	}
}
