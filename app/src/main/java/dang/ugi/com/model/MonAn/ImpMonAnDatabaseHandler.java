package dang.ugi.com.model.MonAn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dang.ugi.com.model.DatabaseHelper.UgiDatabase;
import dang.ugi.com.model.DatabaseHelper.UgiDatabaseHelper;
import dang.ugi.com.model.Entities.Gia;
import dang.ugi.com.model.Entities.MonAn;

/**
 * Created by DANG on 9/27/2016.
 */

public class ImpMonAnDatabaseHandler implements IMonAnDatabaseHandler {
    Context context;
    SQLiteDatabase database;
    UgiDatabaseHelper databaseHelper;
    List<MonAn> list;
    Cursor cursor;
    public ImpMonAnDatabaseHandler(Context context) {
        this.context = context;
        databaseHelper = new UgiDatabaseHelper(context);
    }

    @Override
    public long themMonAn(MonAn monAn) {
        database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UgiDatabase.COLUMN_MONAN_TENMON,monAn.getTenMonAn());
        values.put(UgiDatabase.COLUMN_MONAN_GIATHAMKHAO,monAn.getGiaThamKhao());
        values.put(UgiDatabase.COLUMN_MONAN_HINHANH,monAn.getHinhAnh());
        values.put(UgiDatabase.COLUMN_LOAIMONAN_MALOAI,monAn.getMaLoaiMon());
        return database.insert(UgiDatabase.TABLE_MON,null,values);
    }

    @Override
    public long capNhatMonAn(MonAn monAn) {
        database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UgiDatabase.COLUMN_MONAN_TENMON,monAn.getTenMonAn());
        values.put(UgiDatabase.COLUMN_MONAN_HINHANH,monAn.getHinhAnh());
        values.put(UgiDatabase.COLUMN_LOAIMONAN_MALOAI,monAn.getMaLoaiMon());
        return database.update(UgiDatabase.TABLE_MON,values,UgiDatabase.COLUMN_MONAN_MAMON + " = ? ",new String[]{String.valueOf(monAn.getMaMonAn())});
    }

    @Override
    public List<MonAn> listAllMonAn() {
        list = new ArrayList<>();
        database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(UgiDatabase.TABLE_MON,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                MonAn monAn = new MonAn();
                monAn.setMaMonAn(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_MAMON)));
                monAn.setMaLoaiMon(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_MALOAI)));
                monAn.setTenMonAn(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_TENMON)));
                monAn.setGiaThamKhao(cursor.getFloat(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_GIATHAMKHAO)));
                if (cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_HINHANH))==null){
                    monAn.setHinhAnh("null");
                }else{
                    monAn.setHinhAnh(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_HINHANH)));
                }

                list.add(monAn);
            }while (cursor.moveToNext());
        }
        cursor.close();
        databaseHelper.close();
        return list;
    }

    @Override
    public List<MonAn> listAllMonAnTheoCuaHang(int maCuaHang) {
        list = new ArrayList<>();
        database = databaseHelper.getReadableDatabase();
        String query="SELECT * FROM " +UgiDatabase.TABLE_MON +" ma, " + UgiDatabase.TABLE_GIA +" gia WHERE "
                +  "gia." +UgiDatabase.COLUMN_GIA_MACUAHANG +" = ? "
                + " AND ma." + UgiDatabase.COLUMN_MONAN_MAMON  +" = gia."  +UgiDatabase.COLUMN_GIA_MAMONAN;
        cursor = database.rawQuery(query,new String[]{String.valueOf(maCuaHang)});
        if (cursor.moveToFirst()){
            do {
                MonAn monAn = new MonAn();
                monAn.setMaMonAn(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_MAMON)));
                monAn.setMaLoaiMon(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_MALOAI)));
                monAn.setTenMonAn(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_TENMON)));
                if (cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_HINHANH))==null){
                    monAn.setHinhAnh("null");
                }else{
                    monAn.setHinhAnh(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_HINHANH)));
                }
                list.add(monAn);
            }while (cursor.moveToNext());
        }
        cursor.close();
        databaseHelper.close();
        return list;
    }

    @Override
    public List<MonAn> listAllMonAnTheoMaLoai(int maLoai) {
        list = new ArrayList<>();
        database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(UgiDatabase.TABLE_MON,null,UgiDatabase.COLUMN_MONAN_MALOAI + " = ?",new String[]{String.valueOf(maLoai)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                MonAn monAn = new MonAn();
                monAn.setMaMonAn(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_MAMON)));
                monAn.setMaLoaiMon(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_MALOAI)));
                monAn.setTenMonAn(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_TENMON)));
                if (cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_HINHANH))==null){
                    monAn.setHinhAnh("null");
                }else{
                    monAn.setHinhAnh(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_HINHANH)));
                }
                list.add(monAn);
            }while (cursor.moveToNext());
        }
        cursor.close();
        databaseHelper.close();
        return list;
    }

    @Override
    public List<MonAn> listTimKiemMonAn(String tenMonAn) {
        list = new ArrayList<>();
        database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(UgiDatabase.TABLE_MON,null,UgiDatabase.COLUMN_MONAN_TENMON + " LIKE ?",new String[]{tenMonAn+"%"},null,null,null);
        if (cursor.moveToFirst()){
            do {
                MonAn monAn = new MonAn();
                monAn.setMaMonAn(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_MAMON)));
                monAn.setMaLoaiMon(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_MALOAI)));
                monAn.setTenMonAn(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_TENMON)));
                if (cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_HINHANH))==null){
                    monAn.setHinhAnh("null");
                }else{
                    monAn.setHinhAnh(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_HINHANH)));
                }
                list.add(monAn);
            }while (cursor.moveToNext());
        }
        cursor.close();
        databaseHelper.close();
        return list;
    }
    public List<MonAn> listTimKiemMonAnTrongThucDon(String tenMonAn,int maCuaHang) {
        list = new ArrayList<>();
        try {
            database = databaseHelper.getReadableDatabase();
            String query="SELECT * FROM " +UgiDatabase.TABLE_MON +" ma, " + UgiDatabase.TABLE_GIA +" gia WHERE "
                    +  "ma."+UgiDatabase.COLUMN_MONAN_TENMON +" LIKE ? " +"AND gia." +UgiDatabase.COLUMN_GIA_MACUAHANG +" = ? "
                    + " AND ma." + UgiDatabase.COLUMN_MONAN_MAMON  +" = gia."  +UgiDatabase.COLUMN_GIA_MAMONAN;

             cursor = database.rawQuery(query,new String[]{tenMonAn+"%", String.valueOf(maCuaHang)});
            if (cursor.moveToFirst()){
                do {
                    MonAn monAn = new MonAn();
                    monAn.setMaMonAn(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_MAMON)));
                    monAn.setMaLoaiMon(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_MALOAI)));
                    monAn.setTenMonAn(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_TENMON)));
                    if (cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_HINHANH))==null){
                        monAn.setHinhAnh("null");
                    }else{
                        monAn.setHinhAnh(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_HINHANH)));
                    }
                    list.add(monAn);
                }while (cursor.moveToNext());
            }

        }catch (Exception ex){}
        finally {
            if (cursor!=null)cursor.close();
            databaseHelper.close();
        }

        return list;
    }
    @Override
    public MonAn layMonAnbyId(int id) {
        MonAn monAn =null;
       try {
           database = databaseHelper.getReadableDatabase();
            cursor = database.query(UgiDatabase.TABLE_MON,null,UgiDatabase.COLUMN_MONAN_MAMON + " = ?",new String[]{String.valueOf(id)},null,null,null);
           if (cursor.moveToFirst()){
               monAn = new MonAn();
               monAn.setMaMonAn(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_MAMON)));
               monAn.setMaLoaiMon(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_MALOAI)));
               monAn.setTenMonAn(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_TENMON)));
               if (cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_HINHANH))==null){
                   monAn.setHinhAnh("null");
               }else{
                   monAn.setHinhAnh(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_MONAN_HINHANH)));
               }

           }
       }catch (SQLiteAbortException ex){}
        finally {
           if (cursor!=null)  cursor.close();
           databaseHelper.close();
       }

        return monAn;
    }

    @Override
    public long xoaMonAn(int id) {
        long ketqua =-1;
        try {
            database = databaseHelper.getWritableDatabase();
            ketqua = database.delete(UgiDatabase.TABLE_MON,UgiDatabase.COLUMN_MONAN_MAMON + " = ? ",new String[]{String.valueOf(id)});
        }catch (Exception e){
        }finally {
            database.close();
        }
        return ketqua;

    }

    @Override
    public long xoaBangGia(int maMon, int maCuaHang) {
        database = databaseHelper.getWritableDatabase();
        return database.delete(UgiDatabase.TABLE_GIA,UgiDatabase.COLUMN_MONAN_MAMON + " = ? AND " +
               UgiDatabase.COLUMN_GIA_MACUAHANG + " = ? ",new String[]{String.valueOf(maMon), String.valueOf(maCuaHang)});
    }

    @Override
    public boolean kiemTraTenMonTonTai(String tenMon) {
        boolean kiemtra = false;
        database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(UgiDatabase.TABLE_MON,null,UgiDatabase.COLUMN_MONAN_TENMON + " = ?",new String[]{tenMon},null,null,null);
        if (cursor.moveToFirst()){
           kiemtra = true;

        }
        cursor.close();
        databaseHelper.close();
        return kiemtra;
    }
    @Override
    public boolean kiemTraTenMonTonTaiTrongThucDon(String tenMon,int maCuaHang) {
        boolean kiemtra = false;
        database = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM "+ UgiDatabase.TABLE_MON + " m , "+UgiDatabase.TABLE_GIA +"  g "
                +" WHERE m." +UgiDatabase.COLUMN_MONAN_MAMON + " = g."+UgiDatabase.COLUMN_GIA_MAMONAN
                + " AND m."+UgiDatabase.COLUMN_MONAN_TENMON + " = ? AND g." +UgiDatabase.COLUMN_GIA_MACUAHANG + " = ?";
        cursor = database.rawQuery(query,new String[]{tenMon, String.valueOf(maCuaHang)});
        if (cursor.moveToFirst()){
            kiemtra = true;

        }
        cursor.close();
        databaseHelper.close();
        return kiemtra;
    }
    @Override
    public boolean kiemtraMonDacoTrongThucDon(int maMon,int maCuaHang) {
        boolean kiemtra = false;
        database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(UgiDatabase.TABLE_GIA,null,UgiDatabase.COLUMN_GIA_MAMONAN + " = ?" +
                " AND " +UgiDatabase.COLUMN_GIA_MACUAHANG + " = ?",new String[]{String.valueOf(maMon), String.valueOf(maCuaHang)},null,null,null);
        if (cursor.moveToFirst()){
            kiemtra = true;
        }
        cursor.close();
        databaseHelper.close();
        return kiemtra;
    }

    @Override
    public long themBangGia(Gia gia) {
        long ketqua =-1;
       try {
           database = databaseHelper.getWritableDatabase();
           ContentValues values = new ContentValues();
           values.put(UgiDatabase.COLUMN_MONAN_MAMON,gia.getMaMon());
           values.put(UgiDatabase.COLUMN_CUAHANG_MACUAHANG,gia.getMaCuaHang());
           values.put(UgiDatabase.COLUMN_GIA_GIAMONAN,gia.getGia());
           ketqua = database.insert(UgiDatabase.TABLE_GIA,null,values);
       }catch (Exception e){
       }finally {
           database.close();
       }
        return ketqua;
    }

    @Override
    public float layGiaMonTheoMaMon(int maMon) {
        float gia =0;
        database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(UgiDatabase.TABLE_GIA,null,UgiDatabase.COLUMN_MONAN_MAMON + " = ?",new String[]{String.valueOf(maMon)},null,null,null);
        if (cursor.moveToFirst()){
           gia = cursor.getFloat(cursor.getColumnIndex(UgiDatabase.COLUMN_GIA_GIAMONAN));
        }
        cursor.close();
        databaseHelper.close();
        return gia;
    }
    @Override
    public int layMaCuaHangByMaMon(int maMon) {
        int maCuahang = 0;
        database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(UgiDatabase.TABLE_GIA,new String[]{UgiDatabase.COLUMN_GIA_MACUAHANG},UgiDatabase.COLUMN_MONAN_MAMON + " = ?",new String[]{String.valueOf(maMon)},null,null,null);
        if (cursor.moveToFirst()){
            maCuahang = cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_GIA_MACUAHANG));
        }
        cursor.close();
        databaseHelper.close();
        return maCuahang;
    }

}
