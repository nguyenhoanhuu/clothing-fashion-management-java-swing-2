package clothing.management.app;

import com.mongodb.reactivestreams.client.MongoClient;

import clothing.management.dao.NhanVienDao;
import clothing.management.entity.NhanVien;

public class Globals {

	public static NhanVien nhanVien;

	public static NhanVien getNhanVien() {
		return nhanVien;
	}

	public static void setNhanVien(NhanVien nhanVien) {
		Globals.nhanVien = nhanVien;
	}

}
