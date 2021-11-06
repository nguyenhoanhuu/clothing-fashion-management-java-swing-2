package clothing.management.app;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import clothing.management.app.gui.GiaoDienDangNhap;
import clothing.management.app.gui.GiaoDienDieuKhien;
import clothing.management.dao.HoaDonDao;
import clothing.management.dao.NhaCungCapDao;
import clothing.management.entity.ChiTietHoaDon;
import clothing.management.entity.HoaDon;
import clothing.management.entity.KhachHang;
import clothing.management.entity.NhaCungCap;
import clothing.management.entity.NhanVien;
import clothing.management.entity.SanPham;

public class ClothingManagementApplication {
	public static void main(String[] args) throws Exception {
//    	new GiaoDienDieuKhien().setVisible(true);
		new GiaoDienDangNhap().setVisible(true);
		MongoClient client = MongoClients.create();
//		NhaCungCapDao nhaCCDao = new NhaCungCapDao(client);
//		NhaCungCap nhaCC = new NhaCungCap("NCC00002", "AM", "12NHH", "0968172177");

//		nhaCCDao.addSuppiler(nhaCC);
//		System.out.println(nhaCCDao.getListSupllier());
//		HoaDonDao HDDao = new HoaDonDao(client);
//
//		System.out.println(HDDao.getAllListBill());
//		System.out.println(HDDao.xoaHoaDon("maHoaDon", "HD00004") ? "oke" : "no");
//		ChiTietHoaDon chiTietHoaDon1 = new ChiTietHoaDon(new SanPham("SP00001"), 2, 500000);
//		ChiTietHoaDon chiTietHoaDon2 = new ChiTietHoaDon(new SanPham("SP00002"), 4, 500000);
//
//		HoaDon hoaDon = new HoaDon("HD00004", new KhachHang("KH00002"), new NhanVien("NV00001"),
//				new Date(System.currentTimeMillis()), 20, Arrays.asList(chiTietHoaDon1, chiTietHoaDon2));
//		System.out.println(HDDao.createBill(hoaDon));
	}
}
