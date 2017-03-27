package dang.ugi.com.model.CuaHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dang.ugi.com.model.DatabaseHelper.UgiDatabase;
import dang.ugi.com.model.DatabaseHelper.UgiDatabaseHelper;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.LoaiCuaHang;

/**
 * Created by DANG on 9/27/2016.
 */

public class ImpCuaHangDatabaseHandler implements ICuaHangDatabaseHandler {
    Context context;
    SQLiteDatabase database;
    UgiDatabaseHelper databaseHelper;
    List<CuaHang> list;
    private Cursor cursor;

    public ImpCuaHangDatabaseHandler(Context context) {
        this.context = context;
        databaseHelper = new UgiDatabaseHelper(context);
    }

    @Override
    public List<CuaHang> layToanBoCuaHang(int maNguoiDung) {
        list = new ArrayList<>();
        String query ="SELECT * FROM CuaHang ch,NguoiDung nd,NguoiDung_CuaHang ndch WHERE ch.MaCuaHang = ndch.MaCuaHang AND " +
                "ndch.MaNguoiDung = nd.MaNguoiDung AND ndch.MaNguoiDung = ? ";
        database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,new String[]{String.valueOf(maNguoiDung)});
        if (cursor.moveToFirst()){
            do {
                CuaHang cuaHang =new CuaHang();
                cuaHang.setMaCuaHang(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_CUAHANG_MACUAHANG)));
                cuaHang.setTenCuahang(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_CUAHANG_TENCUAHANG)));
                list.add(cuaHang);
            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return list;
    }

    @Override
    public List<CuaHang> timKiemCuahang(String tenCuaHang,int maNguoiDung) {
        list = new ArrayList<>();
        String query ="SELECT * FROM CuaHang ch,NguoiDung nd,NguoiDung_CuaHang ndch WHERE ch.MaCuaHang = ndch.MaCuaHang AND " +
                "ndch.MaNguoiDung = nd.MaNguoiDung AND ndch.MaNguoiDung = ? AND ch.TenCuaHang LIKE ? ";
        database = databaseHelper.getReadableDatabase();
         cursor = database.rawQuery(query,new String[]{String.valueOf(maNguoiDung)});
        if (cursor.moveToFirst()){
            do {
                CuaHang cuaHang =new CuaHang();
                cuaHang.setMaCuaHang(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_CUAHANG_MACUAHANG)));
                cuaHang.setTenCuahang(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_CUAHANG_TENCUAHANG)));
                list.add(cuaHang);
            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return list;
    }

    @Override
    public CuaHang layCuahangById(int maCuaHang) {
        CuaHang cuaHang = null;
        database= databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(UgiDatabase.TABLE_CUAHANG,null,UgiDatabase.COLUMN_CUAHANG_MACUAHANG + " = ? ",
                new String[]{String.valueOf(maCuaHang)},null,null,null);
        if (cursor.moveToFirst()){
               cuaHang =new CuaHang();
                cuaHang.setMaCuaHang(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_CUAHANG_MACUAHANG)));
                cuaHang.setTenCuahang(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_CUAHANG_TENCUAHANG)));

        }
        cursor.close();
        database.close();
        return cuaHang;
    }

    @Override
    public boolean kiemTratenCuahang(String tenCuaHang) {
        boolean kiemtra = false;
        database= databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(UgiDatabase.TABLE_CUAHANG,null,UgiDatabase.COLUMN_CUAHANG_TENCUAHANG + " = ? ",
                new String[]{tenCuaHang},null,null,null);
        if (cursor.moveToFirst()){
            kiemtra = true;
        }
        cursor.close();
        database.close();
        return kiemtra;
    }

    @Override
    public long themCuaHang(CuaHang cuaHang) {
        long ketqua = -1;
       try {
           database = databaseHelper.getWritableDatabase();
           ContentValues values = new ContentValues();
           values.put(UgiDatabase.COLUMN_CUAHANG_MACUAHANG,cuaHang.getMaCuaHang());
           values.put(UgiDatabase.COLUMN_CUAHANG_TENCUAHANG,cuaHang.getTenCuahang());
           values.put(UgiDatabase.COLUMN_CUAHANG_DIACHI,cuaHang.getDiaChi());
           values.put(UgiDatabase.COLUMN_CUAHANG_SDT,cuaHang.getSoDienThoai());
           values.put(UgiDatabase.COLUMN_CUAHANG_LOGO,cuaHang.getLogo());
           ketqua = database.insert(UgiDatabase.TABLE_CUAHANG,null,values);
       }catch (Exception ex){
           ex.printStackTrace();
       }finally {
           database.close();
       }
        return ketqua;
    }

    @Override
    public long suaCuaHang(CuaHang cuaHang) {
        database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UgiDatabase.COLUMN_CUAHANG_TENCUAHANG,cuaHang.getTenCuahang());
        values.put(UgiDatabase.COLUMN_CUAHANG_DIACHI,cuaHang.getDiaChi());
        values.put(UgiDatabase.COLUMN_CUAHANG_SDT,cuaHang.getSoDienThoai());
        values.put(UgiDatabase.COLUMN_CUAHANG_LOGO,cuaHang.getLogo());
        return database.update(UgiDatabase.TABLE_CUAHANG,values,UgiDatabase.COLUMN_CUAHANG_MACUAHANG + " = ? ",new String[]{String.valueOf(cuaHang.getMaCuaHang())});
    }

    @Override
    public long xoaCuaHang(int maCuaHang) {
        return database.delete(UgiDatabase.TABLE_CUAHANG,UgiDatabase.COLUMN_CUAHANG_MACUAHANG + " = ? ",new String[]{String.valueOf(maCuaHang)});
    }

    @Override
    public long themCuaHangNguoiDung(int maCuaHang, int maNguoiDung) {
        database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UgiDatabase.COLUMN_CUAHANG_MACUAHANG,maCuaHang);
        values.put(UgiDatabase.COLUMN_MANGUOIDUNG,maNguoiDung);
        return database.insert(UgiDatabase.TABLE_NGUOIDUNG_CUAHANG,null,values);
    }

    @Override
    public int layMaCuaHangByMaNguoiDung(int maNguoiDung) {
        int maCuaHang = 0;
        database= databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(UgiDatabase.TABLE_NGUOIDUNG_CUAHANG,null,UgiDatabase.COLUMN_MANGUOIDUNG + " = ? ",
                new String[]{String.valueOf(maNguoiDung)},null,null,null);
        if (cursor.moveToFirst()){
            maCuaHang = cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_CUAHANG_MACUAHANG));
            cursor.close();
        }
        database.close();
        return maCuaHang;
    }

    @Override
    public int themLoaiCuaHang(String tenLoaiCuaHang) {
        int ketqua= -1;
       try {
           database = databaseHelper.getWritableDatabase();
           ContentValues values = new ContentValues();
           values.put(UgiDatabase.COLUMN_LOAICUAHANG_TENLOAICUAHANG,tenLoaiCuaHang);
           ketqua = (int) database.insert(UgiDatabase.TABLE_LOAI_CUAHANG,null,values);
       }catch (Exception ex){}
        finally {
            database.close();
       }
        return ketqua;
    }

    @Override
    public CuaHang checkCuaHangNguoiDung(int maNguoiDung) {
        CuaHang cuaHang = null;
        String query ="SELECT * FROM CuaHang ch,NguoiDung nd,NguoiDung_CuaHang ndch WHERE ch.MaCuaHang = ndch.MaCuaHang AND " +
                "ndch.MaNguoiDung = nd.MaNguoiDung AND ndch.MaNguoiDung = "+String.valueOf(maNguoiDung);
        database = databaseHelper.getReadableDatabase();
         cursor = database.rawQuery(query,null);
        try {
            if (cursor.moveToFirst()){
                cuaHang =new CuaHang();
                cuaHang.setMaCuaHang(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_CUAHANG_MACUAHANG)));
                cuaHang.setTenCuahang(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_CUAHANG_TENCUAHANG)));
            }

        }catch (Exception ex){
            Toast.makeText(context,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            if (cursor!=null)
                cursor.close();
            database.close();
        }

        return cuaHang;
    }

    @Override
    public List<LoaiCuaHang> layLoaiCuaHang() {
       List<LoaiCuaHang> list = new ArrayList<>();
        database= databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(UgiDatabase.TABLE_LOAI_CUAHANG,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
           do {
               int maLoaiCuaHang = cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_LOAICUAHANG_MALOAICUAHANG));
               String tenLoaiCuaHang = cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_LOAICUAHANG_TENLOAICUAHANG));
               LoaiCuaHang loaiCuaHang = new LoaiCuaHang();
               loaiCuaHang.setMaLoaiCuaHang(maLoaiCuaHang);
               loaiCuaHang.setTenLoaiCuaHang(tenLoaiCuaHang);
               list.add(loaiCuaHang);
           }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return list;
    }
}
