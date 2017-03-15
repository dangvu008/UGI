package dang.ugi.com.model.NguoiDung;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import dang.ugi.com.model.DatabaseHelper.UgiDatabase;
import dang.ugi.com.model.DatabaseHelper.UgiDatabaseHelper;
import dang.ugi.com.model.Entities.NguoiDung;

/**
 * Created by DANG on 9/9/2016.
 */
public class ImpNguoiDungDatabaseHandler implements INguoiDungDatabaseHandler {
   private UgiDatabaseHelper databaseHelper;
   private   SQLiteDatabase database;
   private   Cursor cursor;
    public ImpNguoiDungDatabaseHandler(Context context) {
        this.databaseHelper = new UgiDatabaseHelper(context);
    }

    @Override
    public long themNguoiDung(NguoiDung nguoiDung) {
        long ketqua = -1;
        try{
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(UgiDatabase.COLUMN_MANGUOIDUNG,nguoiDung.getMaNguoiDung());
            values.put(UgiDatabase.COLUMN_TENNGUOIDUNG,nguoiDung.getTenNguoiDung());
            values.put(UgiDatabase.COLUMN_NGUOIDUNG_EMAIL,nguoiDung.getEmail());
            values.put(UgiDatabase.COLUMN_GIOITINH,nguoiDung.getGioitinh());
            values.put(UgiDatabase.COLUMN_NAMSINH, String.valueOf(nguoiDung.getNamSinh()));
            values.put(UgiDatabase.COLUMN_MATKHAU,nguoiDung.getMatKhau());
            values.put(UgiDatabase.COLUMN_NGUOIDUNG_SDT,nguoiDung.getSoDienThoai());
            values.put(UgiDatabase.COLUMN_NGUOIDUNG_TINHTRANG,1);
            values.put(UgiDatabase.COLUMN_MAQUYEN,nguoiDung.getMaQuyen());
            values.put(UgiDatabase.COLUMN_NGUOIDUNG_ALLOWLOGIN,nguoiDung.getAllowLogin());
            values.put(UgiDatabase.COLUMN_NGUOIDUNG_SYNC,nguoiDung.getSync());
            ketqua =database.insert(UgiDatabase.TABLE_NGUOIDUNG,null,values);
        }catch (Exception ex){}
        finally {
            database.close();
        }
        return ketqua;
    }

    @Override
    public long capNhapNguoiDung(NguoiDung nguoiDung) {
        database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UgiDatabase.COLUMN_TENNGUOIDUNG,nguoiDung.getTenNguoiDung());
        values.put(UgiDatabase.COLUMN_NGUOIDUNG_EMAIL,nguoiDung.getEmail());
        values.put(UgiDatabase.COLUMN_GIOITINH,nguoiDung.getGioitinh());
        values.put(UgiDatabase.COLUMN_NAMSINH, String.valueOf(nguoiDung.getNamSinh()));
        values.put(UgiDatabase.COLUMN_MATKHAU,nguoiDung.getMatKhau());
        values.put(UgiDatabase.COLUMN_NGUOIDUNG_SDT,nguoiDung.getSoDienThoai());
        values.put(UgiDatabase.COLUMN_NGUOIDUNG_TINHTRANG,nguoiDung.getTinhTrang());
        values.put(UgiDatabase.COLUMN_NGUOIDUNG_SYNC,nguoiDung.getSync());
        return database.update(UgiDatabase.TABLE_NGUOIDUNG,values,UgiDatabase.COLUMN_MANGUOIDUNG+ " = ?",new String[]{String.valueOf(nguoiDung.getMaNguoiDung())});

    }

    @Override
    public long capNhatMatKhau(int maNguoiDung, String matKhau) {
        return 0;
    }

    @Override
    public List<NguoiDung> listAllNguoiDung() {
        List<NguoiDung> listNguoiDung = null;
        try {
            database = databaseHelper.getReadableDatabase();
            cursor  = database.query(UgiDatabase.TABLE_NGUOIDUNG,null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                listNguoiDung = new ArrayList<>();
                do {
                    NguoiDung nguoiDung = CursorToNguoiDung(cursor);
                    listNguoiDung.add(nguoiDung);
                }while (cursor.moveToNext());

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            cursor.close();
            database.close();
        }
        return listNguoiDung;
    }

    @Override
    public List<NguoiDung> listAllNguoiDung(int maCuaHang) {
        List<NguoiDung> listNguoiDung = null;
        Cursor cursor = null;
        try {
            database = databaseHelper.getReadableDatabase();
            String query ="SELECT * FROM "+UgiDatabase.TABLE_NGUOIDUNG +" nd ,"+UgiDatabase.TABLE_NGUOIDUNG_CUAHANG +
                    " ndch ON nd."+UgiDatabase.COLUMN_MANGUOIDUNG +" = ndch."+UgiDatabase.COLUMN_MANGUOIDUNG +" WHERE "
                    + "ndch."+UgiDatabase.COLUMN_CUAHANG_MACUAHANG +" = ?";
            cursor  = database.rawQuery(query,new String[]{String.valueOf(maCuaHang)});
            if (cursor.moveToFirst()){
                 listNguoiDung = new ArrayList<>();
                do {
                   NguoiDung nguoiDung = CursorToNguoiDung(cursor);
                    listNguoiDung.add(nguoiDung);
                }while (cursor.moveToNext());
                cursor.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            database.close();
        }
        return listNguoiDung;
    }
    @Override
    public List<NguoiDung> listUnsyncNguoiDung(int maCuaHang) {
        List<NguoiDung> listNguoiDung = null;
        Cursor cursor = null;
        try {
            database = databaseHelper.getReadableDatabase();
            String query ="SELECT * FROM "+UgiDatabase.TABLE_NGUOIDUNG +" nd ,"+UgiDatabase.TABLE_NGUOIDUNG_CUAHANG +
                    " ndch ON nd."+UgiDatabase.COLUMN_MANGUOIDUNG +" = ndch."+UgiDatabase.COLUMN_MANGUOIDUNG +" WHERE "
                    + "ndch."+UgiDatabase.COLUMN_CUAHANG_MACUAHANG +" = ? AND "
                    +"nd."+UgiDatabase.COLUMN_NGUOIDUNG_SYNC +" = 0";
            cursor  = database.rawQuery(query,new String[]{String.valueOf(maCuaHang)});
            if (cursor.moveToFirst()){
                listNguoiDung = new ArrayList<>();
                do {
                    NguoiDung nguoiDung = CursorToNguoiDung(cursor);
                    listNguoiDung.add(nguoiDung);
                }while (cursor.moveToNext());

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (cursor!=null){
                cursor.close();
            }
            database.close();
        }
        return listNguoiDung;
    }

    @Override
    public NguoiDung layNguoiDungById(int maNguoiDung) {
        NguoiDung nguoiDung = null;
        Cursor cursor = null;
        try {
            database = databaseHelper.getReadableDatabase();
            cursor  = database.query(UgiDatabase.TABLE_NGUOIDUNG,null,
                    UgiDatabase.COLUMN_MANGUOIDUNG + " = ? "
                    ,new String[]{String.valueOf(maNguoiDung)},null,null,null);
            if (cursor.moveToFirst()){
                nguoiDung = CursorToNguoiDung(cursor);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            cursor.close();
            database.close();
        }
        return nguoiDung;
    }

    @Override
    public boolean kiemTraEmailTonTai(String email) {
        boolean kiemtra = false;
        database = databaseHelper.getReadableDatabase();
        Cursor cursor  = database.query(UgiDatabase.TABLE_NGUOIDUNG,new String[]{UgiDatabase.COLUMN_MAQUYEN,UgiDatabase.COLUMN_TENNGUOIDUNG,UgiDatabase.COLUMN_NGUOIDUNG_EMAIL},
                UgiDatabase.COLUMN_NGUOIDUNG_EMAIL + " = ? ",new String[]{email},null,null,null);
        if (cursor.moveToFirst()&&cursor!=null){
            kiemtra= true;
        }
        cursor.close();
        database.close();
        return kiemtra;
    }

    @Override
    public boolean kiemTraSoDienThoaiTonTai(String sodienthoai) {
        boolean kiemtra = false;
        database = databaseHelper.getReadableDatabase();
        Cursor cursor  = database.query(UgiDatabase.TABLE_NGUOIDUNG,new String[]{UgiDatabase.COLUMN_MAQUYEN,UgiDatabase.COLUMN_TENNGUOIDUNG},
                UgiDatabase.COLUMN_NGUOIDUNG_SDT + " = ? ",new String[]{sodienthoai},null,null,null);
        if (cursor.moveToFirst()&&cursor!=null){
            kiemtra= true;
        }
        cursor.close();
        database.close();
        return kiemtra;
    }

    @Override
    public boolean kiemTraTenTonTai(String ten) {
        boolean kiemtra = false;
        database = databaseHelper.getReadableDatabase();
        Cursor cursor  = database.query(UgiDatabase.TABLE_NGUOIDUNG,new String[]{UgiDatabase.COLUMN_MAQUYEN,UgiDatabase.COLUMN_TENNGUOIDUNG},
                UgiDatabase.COLUMN_TENNGUOIDUNG + " = ? ",new String[]{ten},null,null,null);
        if (cursor.moveToFirst()&&cursor!=null){
            kiemtra= true;
        }
        cursor.close();
        database.close();
        return kiemtra;
    }

    @Override
    public boolean xoaNguoiDung(int maNguoiDung) {
        return false;
    }

    @Override
    public boolean updateNguoiDung(NguoiDung nguoiDung) {
        return false;
    }


    @Override
    public NguoiDung dangNhap(String account,String matkhau) {
        NguoiDung nguoiDung = null;
        try {
            String mkEncrypt = AESCrypt.encrypt("mat khau",matkhau);
            database = databaseHelper.getReadableDatabase();
             cursor  = database.query(UgiDatabase.TABLE_NGUOIDUNG,null,
                    UgiDatabase.COLUMN_NGUOIDUNG_SDT + " = ? OR "+UgiDatabase.COLUMN_NGUOIDUNG_EMAIL + " = ? AND " +
                           UgiDatabase.COLUMN_MATKHAU  +" = ? "
                    ,new String[]{account,account,mkEncrypt},null,null,null);
            if (cursor.moveToFirst()){
                    nguoiDung = CursorToNguoiDung(cursor);
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            database.close();
        }
     return nguoiDung;
    }

    @NonNull
    private NguoiDung CursorToNguoiDung(Cursor cursor) {
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setMaNguoiDung(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_MANGUOIDUNG)));
        nguoiDung.setTenNguoiDung(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_TENNGUOIDUNG)));
        nguoiDung.setEmail(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_NGUOIDUNG_EMAIL)));
        nguoiDung.setSoDienThoai(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_NGUOIDUNG_SDT)));
        nguoiDung.setMatKhau(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MATKHAU)));
        nguoiDung.setMaQuyen(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_MAQUYEN)));
        nguoiDung.setTinhTrang(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_NGUOIDUNG_TINHTRANG)));
        nguoiDung.setSync(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_NGUOIDUNG_SYNC)));
        return nguoiDung;
    }

    @Override
    public long capNhatTrangThaiNguoiDung(int maNguoiDung, int trangThai) {
        database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UgiDatabase.COLUMN_NGUOIDUNG_TINHTRANG,trangThai);
        return database.update(UgiDatabase.TABLE_NGUOIDUNG,values,UgiDatabase.COLUMN_MANGUOIDUNG+ " = ?",new String[]{String.valueOf(maNguoiDung)});
    }

    @Override
    public List<NguoiDung> timKiemNguoiDung(String keySearch,int maCuaHang) {
        List<NguoiDung> listNguoiDung = null;
        Cursor cursor = null;
        try {
            database = databaseHelper.getReadableDatabase();
            String query ="SELECT * FROM "+UgiDatabase.TABLE_NGUOIDUNG +" nd ,"+UgiDatabase.TABLE_NGUOIDUNG_CUAHANG +
                    "ndch ON nd."+UgiDatabase.COLUMN_MANGUOIDUNG +" = ndch."+UgiDatabase.COLUMN_MANGUOIDUNG +" WHERE "
                    + "ndch."+UgiDatabase.COLUMN_CUAHANG_MACUAHANG +" = ? AND nd."+UgiDatabase.COLUMN_TENNGUOIDUNG +" LIKE ?";
            cursor  = database.rawQuery(query,new String[]{String.valueOf(maCuaHang),keySearch});
            if (cursor.moveToFirst()){
                listNguoiDung = new ArrayList<>();
                do {
                    NguoiDung nguoiDung = CursorToNguoiDung(cursor);
                    listNguoiDung.add(nguoiDung);
                }while (cursor.moveToNext());

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            cursor.close();
            database.close();
        }
        return listNguoiDung;
    }
}
