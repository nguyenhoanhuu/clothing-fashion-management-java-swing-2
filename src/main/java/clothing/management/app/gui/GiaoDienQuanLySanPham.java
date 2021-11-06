package clothing.management.app.gui;

import javax.swing.*;

import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import clothing.management.dao.LoaiSanPhamDao;
import clothing.management.dao.NhaCungCapDao;
import clothing.management.dao.SanPhamDao;
import clothing.management.entity.LoaiSanPham;
import clothing.management.entity.NhaCungCap;
import clothing.management.entity.SanPham;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class GiaoDienQuanLySanPham extends JFrame implements ActionListener {
	MongoClient client = MongoClients.create();
	SanPhamDao sanPhamDao = new SanPhamDao(client);
	LoaiSanPhamDao loaiSPDao = new LoaiSanPhamDao(client);
	NhaCungCapDao nhaCCDao = new NhaCungCapDao(client);
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
	private JButton btnAdd;
	private JButton btnKetXuat;

	public GiaoDienQuanLySanPham() throws InterruptedException {
		//////////////

		//////////////

		this.setTitle("Giao Diện Quản Lý Sản Phẩm");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//setUndecorated(true);
		createGUI();
	}

	private void createGUI() throws InterruptedException {
		JPanel header;

		// Phần North
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
		JLabel lblHeader = new JLabel("Quản Lý Sản Phẩm");
		lblHeader.setFont(new Font("Arial", Font.BOLD, 70));
		header.add(lblHeader);
		pn1.add(header);
		pn1.add(head);
// code  table

		pn2 = new JPanel();
		pn2.setBorder(BorderFactory.createTitledBorder("Danh sách Sản Phẩm"));

//get table  fullScreen
		pn2.setLayout(new BoxLayout(pn2, BoxLayout.PAGE_AXIS));

		String[] cols = { "Mã sản phẩm", "Tên sản phẩm", "Giá", "Số lượng", "Kích cỡ", "Màu sắc", "Thương hiệu",
				"Loại sản phẩm", "Nhà cung cấp" };
		dtm = new DefaultTableModel(cols, 0);
		table = new JTable(dtm);
		JScrollPane scroll = new JScrollPane(table);
//setsize for table
		scroll.setPreferredSize(new DimensionUIResource(1400, 600));
		pn2.add(scroll);

// code function
		pn3 = new JPanel();
		pn3.setPreferredSize(new Dimension(1500, 100));
		pn3.setBorder(BorderFactory.createTitledBorder("các chức năng"));
		pn3BL = new JPanel();
		pn3BL.setPreferredSize(new DimensionUIResource(1400, 50));
		pn3BL.setBorder(BorderFactory.createLoweredBevelBorder());
		cboFind = new JComboBox<String>();
		cboFind.setEditable(false);
		cboFind.addItem("Chọn tiêu chí tìm");
		cboFind.addItem("Tìm theo mã sản phẩm");
		cboFind.addItem("Tìm theo tên sản phẩm");
		cboFind.addItem("Tìm theo thương hiệu");
		txtFind = new JTextField(20);
		JLabel lbFind = new JLabel("Tìm Kiếm theo:");
		btnSearch = new JButton("Tìm Kiếm");
		btnUpdate = new JButton("Cập Nhật");
		btnDelete = new JButton("Xoá Sản Phẩm");
		btnKetXuat = new JButton("Kết Xuất");
		btnAdd = new JButton("Thêm Sản Phẩm");
		pn3BL.add(txtFind);
		pn3BL.add(lbFind);
		pn3BL.add(cboFind);

		lbFind.setPreferredSize(new DimensionUIResource(100, 40));
		txtFind.setPreferredSize(new DimensionUIResource(100, 40));
		cboFind.setPreferredSize(new DimensionUIResource(150, 40));
		btnSearch.setPreferredSize(new DimensionUIResource(100, 40));
		btnUpdate.setPreferredSize(new DimensionUIResource(100, 40));
		btnDelete.setPreferredSize(new DimensionUIResource(150, 40));
		btnKetXuat.setPreferredSize(new DimensionUIResource(100, 40));
		btnAdd.setPreferredSize(new DimensionUIResource(150, 40));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					btnAddActionPerformed(evt);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		pn3BL.add(btnSearch);
		pn3BL.add(btnUpdate);
		pn3BL.add(btnDelete);
		pn3BL.add(btnKetXuat);
		pn3BL.add(btnAdd);

		pn3.add(pn3BL);

		this.add(pn1, BorderLayout.NORTH);
		this.add(pn2, BorderLayout.CENTER);
		this.add(pn3, BorderLayout.SOUTH);
		/**
		 * load dữ liệu lên table
		 */

		List<SanPham> danhSachSanPham = sanPhamDao.layDanhSachSanPham();

		for (SanPham sanPham : danhSachSanPham) {
			List<LoaiSanPham> tenLoai = loaiSPDao.timTheoTenLoai(sanPham.getLoaiSanPham().getMaLoai());
			List<NhaCungCap> tenNCC = nhaCCDao.timTHeoTenNCC(sanPham.getNhaCungCap().getMaNhaCungCap());
			dtm.addRow(new Object[] { sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getGia(),
					sanPham.getSoLuong(), sanPham.getDanhSachKichCo(), sanPham.getDanhSachMauSac(),
					sanPham.getThuongHieu(), tenLoai.get(0).getTenLoai(), tenNCC.get(0).getTenNhaCungCap() });
		}

		btnSearch.addActionListener(this);
		btnDelete.addActionListener(this);
	}
//*********************kết thúc load**************************************

	private void btnAddActionPerformed(ActionEvent evt) throws InterruptedException {
		new clothing.management.app.gui.GiaoDienThemSanPham().setVisible(true);
		setVisible(false);
	}

	private void btnBackActionPerformed(ActionEvent evt) throws InterruptedException {
		new GiaoDienDieuKhien().setVisible(true);
		setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnSearch)) {
			try {
				xuLyTimKiem();
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
		} else if (o.equals(btnDelete)) {
			int row = table.getSelectedRow();

			if (row < 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xoá");
			} else {
				if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá chuyến đi không!", "Cảnh Báo",
						JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
					try {
						sanPhamDao.xoaSanPham(table.getValueAt(row, 0).toString());
						dtm.removeRow(row);

					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}

		}
	}

	private void xuLyTimKiem() throws InterruptedException {
		int select = cboFind.getSelectedIndex();
		switch (select) {

		case 0: {
			List<SanPham> dsSanPham = sanPhamDao.layDanhSachSanPham();
			dtm.setRowCount(0);
			for (SanPham sanPham : dsSanPham) {
				List<LoaiSanPham> tenLoai = loaiSPDao.timTheoTenLoai(sanPham.getLoaiSanPham().getMaLoai());
				List<NhaCungCap> tenNCC = nhaCCDao.timTHeoTenNCC(sanPham.getNhaCungCap().getMaNhaCungCap());
				dtm.addRow(new Object[] { sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getGia(),
						sanPham.getSoLuong(), sanPham.getDanhSachKichCo(), sanPham.getDanhSachMauSac(),
						sanPham.getThuongHieu(), tenLoai.get(0).getTenLoai(), tenNCC.get(0).getTenNhaCungCap() });
			}
			break;
		}

		case 1: {
			String maSanPham = txtFind.getText();

			List<SanPham> dsSanPham = sanPhamDao.timSanPhamTheoTieuChi("maSanPham", maSanPham);
			dtm.setRowCount(0);

			for (SanPham sanPham : dsSanPham) {
				List<LoaiSanPham> tenLoai = loaiSPDao.timTheoTenLoai(sanPham.getLoaiSanPham().getMaLoai());
				List<NhaCungCap> tenNCC = nhaCCDao.timTHeoTenNCC(sanPham.getNhaCungCap().getMaNhaCungCap());
				dtm.addRow(new Object[] { sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getGia(),
						sanPham.getSoLuong(), sanPham.getDanhSachKichCo(), sanPham.getDanhSachMauSac(),
						sanPham.getThuongHieu(), tenLoai.get(0).getTenLoai(), tenNCC.get(0).getTenNhaCungCap() });
			}
			break;

		}

		case 2: {
			String tenSanPham = txtFind.getText();

			List<SanPham> dsSanPham = sanPhamDao.timSanPhamTheoTieuChi("tenSanPham", tenSanPham);

			dtm.setRowCount(0);

			for (SanPham sanPham : dsSanPham) {
				List<LoaiSanPham> tenLoai = loaiSPDao.timTheoTenLoai(sanPham.getLoaiSanPham().getMaLoai());
				List<NhaCungCap> tenNCC = nhaCCDao.timTHeoTenNCC(sanPham.getNhaCungCap().getMaNhaCungCap());
				dtm.addRow(new Object[] { sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getGia(),
						sanPham.getSoLuong(), sanPham.getDanhSachKichCo(), sanPham.getDanhSachMauSac(),
						sanPham.getThuongHieu(), tenLoai.get(0).getTenLoai(), tenNCC.get(0).getTenNhaCungCap() });
			}
			break;
		}

		case 3: {
			String thuongHieu = txtFind.getText();
			List<SanPham> dsSanPham = sanPhamDao.timSanPhamTheoTieuChi("thuongHieu", thuongHieu);
			dtm.setRowCount(0);
			for (SanPham sanPham : dsSanPham) {
				List<LoaiSanPham> tenLoai = loaiSPDao.timTheoTenLoai(sanPham.getLoaiSanPham().getMaLoai());
				List<NhaCungCap> tenNCC = nhaCCDao.timTHeoTenNCC(sanPham.getNhaCungCap().getMaNhaCungCap());
				dtm.addRow(new Object[] { sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getGia(),
						sanPham.getSoLuong(), sanPham.getDanhSachKichCo(), sanPham.getDanhSachMauSac(),
						sanPham.getThuongHieu(), tenLoai.get(0).getTenLoai(), tenNCC.get(0).getTenNhaCungCap() });
			}
			break;
		}
		}
	}
}
