package dang.ugi.com.model.DatabaseHelper;

/**
 * Created by DANG on 9/9/2016.
 */
public class UgiDatabase {
    public static final String DATABASE_NAME = "Ugi";
    //TABLE
    public static final String TABLE_NGUOIDUNG = "NguoiDung ";
    public static final String TABLE_MON = "Mon ";
    public static final String TABLE_LOAIMON = "LoaiMon ";
    public static final String TABLE_BAN = "Ban ";
    public static final String TABLE_GOIMON = "GoiMon ";
    public static final String TABLE_CHITIET_GOIMON = "ChiTietGoiMon ";
    public static final String TABLE_PHANQUYEN = "PhanQuyen";
    public static final String TABLE_CUAHANG = "CuaHang ";
    public static final String TABLE_LOAI_CUAHANG = "LoaiCuaHang";
    public static final String TABLE_LOAICUAHANG_LOAIMON = "LoaiCuaHang_LoaiMon";
    public static final String TABLE_NGUOIDUNG_CUAHANG = "NguoiDung_CuaHang";
    public static final String TABLE_GIA = "Gia";
    public static final String TABLE_DOANHTHUMON = "DoanhThuMon";
    public static final String TABLE_HOADON = "HoaDon";

    public static final String COLUMN_ID ="Id";
    //colums table nguoidung
    public static final String COLUMN_MANGUOIDUNG = "MaNguoiDung";
    public static final String COLUMN_TENNGUOIDUNG = "TenNguoiDung";
    public static final String COLUMN_NGUOIDUNG_EMAIL = "Email";
    public static final String COLUMN_MAQUYEN = "MaQuyen";
    public static final String COLUMN_MATKHAU = "MatKhau";
    public static final String COLUMN_GIOITINH = "GioiTInh";
    public static final String COLUMN_NAMSINH = "NamSinh";
    public static final String COLUMN_NGUOIDUNG_SDT = "SoDienThoai";
    public static final String COLUMN_NGUOIDUNG_TINHTRANG = "TinhTrang";
    public static final String COLUMN_NGUOIDUNG_ALLOWLOGIN = "AllowLogin";
    public static final String COLUMN_NGUOIDUNG_SYNC = "Sync";
    // colums table monan
    public static final String COLUMN_MONAN_MAMON = "MaMonAn";
    public static final String COLUMN_MONAN_TENMON = "TenMonAn";
    public static final String COLUMN_MONAN_MALOAI = "MaLoaiMonAn";
    public static final String COLUMN_MONAN_HINHANH = "HinhAnhMonAn";
    public static final String COLUMN_MONAN_GIATHAMKHAO = "GiaThamKhao";
    //colums table loaimonan
    public static final String COLUMN_LOAIMONAN_MALOAI = "MaLoaiMonAn";
    public static final String COLUMN_LOAIMONAN_TENLOAI = "TenLoaiMonAn";
    //colums table gia
    public static final String COLUMN_GIA_MAGIA = "MaGiaMonAn";
    public static final String COLUMN_GIA_MAMONAN = "MaMonAn";
    public static final String COLUMN_GIA_MACUAHANG = "MaCuaHang";
    public static final String COLUMN_GIA_GIAMONAN = "GiaMonAn";

    //colums table banan
    public static final String COLUMN_BANAN_MABAN = "MaBan";
    public static final String COLUMN_BANAN_TENBAN = "TenBan";
    public static final String COLUMN_BANAN_MACUAHANG = "MaCuaHang";
    public static final String COLUMN_BANAN_TINHTRANG = "TinhTrang";
    //columns table cuahang
    public static final String COLUMN_CUAHANG_MACUAHANG = "MaCuaHang";
    public static final String COLUMN_CUAHANG_TENCUAHANG = "TenCuaHang";
    public static final String COLUMN_CUAHANG_MALOAICUAHANG = "MaLoaiCuaHang";
    public static final String COLUMN_CUAHANG_LOGO = "Logo";
    public static final String COLUMN_CUAHANG_DIACHI = "DiaChi";
    public static final String COLUMN_CUAHANG_SDT = "SoDienThoai";
    public static final String COLUMN_CUAHANG_TINHTRANG = "TinhTrang";
    //colums loai hinh cua hang
    public static final String COLUMN_LOAICUAHANG_MALOAICUAHANG = "MaLoaiCuaHang";
    public static final String COLUMN_LOAICUAHANG_TENLOAICUAHANG = "TenLoaiCuaHang";
    //columns table goimon
    public static final String COLUMN_GOIMON_MAGOIMON = "MaGoiMon";
    public static final String COLUMN_GOIMON_MANGUOIDUNGGOI = "MaNguoiDungGoi";
    public static final String COLUMN_GOIMON_THOIGIANBATDAU = "ThoiGianBatDau";
    public static final String COLUMN_GOIMON_MABAN = "MaBan";
    public static final String COLUMN_GOIMON_MACUAHANG = "MaCuaHang";
    public static final String COLUMN_GOIMON_TONGTIEN = "TongTien";
    public static final String COLUMN_GOIMON_TINHTRANG = "TinhTrang";

    //colums table chitietgoimon
    public static final String COLUMN_CHITETGOIMON_MACHITIETGOIMON = "MaChiTietGoiMon";
    public static final String COLUMN_CHITETGOIMON_MAGOIMON = "MaGoiMon";
    public static final String COLUMN_CHITETGOIMON_MAMONAN = "MaMonAn";
    public static final String COLUMN_CHITETGOIMON_SOLUONG = "SoLuong";
    public static final String COLUMN_CHITETGOIMON_THANHTIEN = "ThanhTien";
    // columns table phanquyen
    private static final String COLUMN_PHANQUYEN_MAQUYEN = "MaQuyen";
    private static final String COLUMN_PHANQUYEN_TENQUYEN = "TenQuyen";
    //columns doanh thu mon
    private static final String COLUMN_DOANHTHUMON_MADOANHTHUMON = "MaDoanhThuMon";
    private static final String COLUMN_DOANHTHU_MAMONAN = "MaMonAn";
    private static final String COLUMN_DOANHTHUMON_MACUAHANG = "MaCuaHang";
    public static final String COLUMN_DOANHTHUMON_THOIGIAN = "ThoiGian";
    public static final String COLUMN_DOANHTHUMON_DOANHTHU = "DoanhThu";

    // hoa don
    public static final String COLUMN_HOADON_MAHOADON = "MaHoaDon";
    public static final String COLUMN_HOADON_MAGOIMON = "MaGoiMon";
    public static final String COLUMN_HOADON_THOIGIANTHANHTOAN = "ThoiGianThanhToan";
    public static final String COLUMN_HOADON_MANGUOIDUNGTHANHTOAN = "MaNguoiDung";
    public static final String COLUMN_HOADON_TIENTHEM = "TienThem";
    public static final String COLUMN_HOADON_GHICHU = "GhiChu";
    public static final String COLUMN_HOADON_SOTIEN = "SoTien";

    public static final String CREATE_TABLE_NGUOIDUNG = "CREATE TABLE  " + TABLE_NGUOIDUNG + " ( " +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "+
            COLUMN_MANGUOIDUNG + " INTEGER UNIQUE, " +
            COLUMN_TENNGUOIDUNG + " TEXT , " +
            COLUMN_NGUOIDUNG_EMAIL + " TEXT , " +
            COLUMN_MATKHAU + " TEXT , " +
            COLUMN_GIOITINH + " TEXT ," +
            COLUMN_NAMSINH + " TEXT ," +
            COLUMN_NGUOIDUNG_SDT + " TEXT ," +
            COLUMN_MAQUYEN + " INTEGER NOT NULL," +
            COLUMN_NGUOIDUNG_TINHTRANG + " INTEGER, " +
            COLUMN_NGUOIDUNG_ALLOWLOGIN + " INTEGER DEFAULT 0, " +
            COLUMN_NGUOIDUNG_SYNC + " INTEGER DEFAULT 0 ) ";
    // MON AN

    public static final String CREATE_TABLE_MON = "CREATE TABLE  " + TABLE_MON + "(" +
            COLUMN_MONAN_MAMON + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            COLUMN_MONAN_TENMON + " TEXT , " +
            COLUMN_MONAN_MALOAI + " INTEGER , " +
            COLUMN_MONAN_HINHANH + " TEXT DEFAULT NULL ," +
            COLUMN_MONAN_GIATHAMKHAO + "  REAL  DEFAULT 0) ";
    //GIA

    public static final String CREATE_TABLE_GIA = "CREATE TABLE  " + TABLE_GIA + "(" +
            COLUMN_GIA_MAGIA + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            COLUMN_GIA_MAMONAN + " INTEGER , " +
            COLUMN_GIA_MACUAHANG + " INTEGER  DEFAULT 0," +
            COLUMN_GIA_GIAMONAN + " REAL )";
    //LOAI MON

    public static final String CREATE_TABLE_LOAIMON = "CREATE TABLE  " + TABLE_LOAIMON + "(" +
            COLUMN_LOAIMONAN_MALOAI + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            COLUMN_LOAIMONAN_TENLOAI + " TEXT ) ";

    //BAN AN

    public static final String CREATE_TABLE_BANAN = "CREATE TABLE  " + TABLE_BAN + "(" +
            COLUMN_BANAN_MABAN + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            COLUMN_BANAN_TENBAN + " TEXT , " +
            COLUMN_BANAN_TINHTRANG + " INTEGER DEFAULT 0 ," +
            COLUMN_BANAN_MACUAHANG + " INTEGER DEFAULT 0 )";

    //CUA HANG

    public static final String CREATE_TABLE_CUAHANG = "CREATE TABLE  " + TABLE_CUAHANG + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            COLUMN_CUAHANG_MACUAHANG + " INTEGER UNIQUE , " +
            COLUMN_CUAHANG_TENCUAHANG + " TEXT , " +
            COLUMN_CUAHANG_MALOAICUAHANG + " INTEGER , " +
            COLUMN_CUAHANG_LOGO + " TEXT , " +
            COLUMN_CUAHANG_DIACHI + " TEXT , " +
            COLUMN_CUAHANG_SDT + " TEXT , " +
            COLUMN_CUAHANG_TINHTRANG + " INTEGER DEFAULT 1)";

    // CUAHANG_NGUOIDUNG

    public static final String CREATE_TABLE_CUAHANG_NGUOIDUNG = "CREATE TABLE  " + TABLE_NGUOIDUNG_CUAHANG + "(" +
            " Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_CUAHANG_MACUAHANG + " INTEGER, " +
            COLUMN_MANGUOIDUNG + " INTEGER )";

    //LOAI CUA HANG

    public static final String CREATE_TABLE_LOAI_CUAHANG = "CREATE TABLE  " + TABLE_LOAI_CUAHANG + "(" +
            COLUMN_LOAICUAHANG_MALOAICUAHANG + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_LOAICUAHANG_TENLOAICUAHANG + " TEXT )";

    //CUAHANG_LOAIMON

    public static final String CREATE_TABLE_LOAI_CUAHANG_LOAIMON = "CREATE TABLE  " + TABLE_LOAICUAHANG_LOAIMON + "(" +
            " ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_LOAICUAHANG_MALOAICUAHANG + " TEXT, " +
            COLUMN_LOAIMONAN_MALOAI + " TEXT )";

    // GOI MON

    public static final String CREATE_TABLE_GOIMON = "CREATE TABLE  " + TABLE_GOIMON + "(" +
            COLUMN_GOIMON_MAGOIMON + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            COLUMN_GOIMON_MANGUOIDUNGGOI + " INTEGER , " +
            COLUMN_GOIMON_THOIGIANBATDAU + " DATETIME , " +
            COLUMN_GOIMON_MABAN + " INTEGER , " +
            COLUMN_GOIMON_MACUAHANG + " INTEGER , " +
            COLUMN_GOIMON_TONGTIEN + " REAL DEFAULT 0, " +
            COLUMN_GOIMON_TINHTRANG + " INTEGER DEFAULT -1 )";

    //CHI TIET GOI MON

    public static final String CREATE_TABLE_CHITIET_GOIMON = "CREATE TABLE  " + TABLE_CHITIET_GOIMON + "(" +
            COLUMN_CHITETGOIMON_MACHITIETGOIMON + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_CHITETGOIMON_MAGOIMON + " INTEGER , " +
            COLUMN_CHITETGOIMON_MAMONAN + " INTEGER, " +
            COLUMN_CHITETGOIMON_SOLUONG + " INTEGER, " +
            COLUMN_CHITETGOIMON_THANHTIEN + " REAL DEFAULT 0 )";

    //PHAN QUYEN

    public static final String CREATE_TABLE_PHANQUYEN = "CREATE TABLE  " + TABLE_PHANQUYEN + "(" +
            COLUMN_PHANQUYEN_MAQUYEN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_PHANQUYEN_TENQUYEN + " TEXT )";

    // DOANH THU MON

    public static final String CREATE_TABLE_DOANHTHUMON = "CREATE TABLE  " + TABLE_DOANHTHUMON + "(" +
            COLUMN_DOANHTHUMON_MADOANHTHUMON + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            COLUMN_DOANHTHUMON_MACUAHANG + " INTEGER , " +
            COLUMN_DOANHTHUMON_DOANHTHU + " REAL DEFAULT 0," +
            COLUMN_DOANHTHUMON_THOIGIAN + "  TEXT ) ";
    //hoa don
    public static final String CREATE_TABLE_HOADON = "CREATE TABLE  " + TABLE_HOADON + "(" +
            COLUMN_HOADON_MAHOADON  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+
            COLUMN_HOADON_MAGOIMON + " INTEGER , " +
            COLUMN_HOADON_MANGUOIDUNGTHANHTOAN + " INTEGER , " +
            COLUMN_HOADON_THOIGIANTHANHTOAN + " DATETIME , " +
            COLUMN_HOADON_TIENTHEM + " REAL DEFAULT 0, " +
            COLUMN_HOADON_GHICHU + " TEXT , " +
            COLUMN_HOADON_SOTIEN + " real )";
}

