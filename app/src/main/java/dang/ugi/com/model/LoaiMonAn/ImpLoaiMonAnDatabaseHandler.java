package dang.ugi.com.model.LoaiMonAn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dang.ugi.com.model.DatabaseHelper.UgiDatabase;
import dang.ugi.com.model.DatabaseHelper.UgiDatabaseHelper;
import dang.ugi.com.model.Entities.LoaiMon;

/**
 * Created by DANG on 9/26/2016.
 */

public class ImpLoaiMonAnDatabaseHandler implements ILoaiMonAnDatbaseHandler {
    private Context context;
    private SQLiteDatabase database;
    UgiDatabaseHelper databaseHelper;
    List<LoaiMon> loaiMonList;
    Cursor cursor;
    public ImpLoaiMonAnDatabaseHandler(Context context) {
        this.context = context;
        databaseHelper = new UgiDatabaseHelper(context);
    }

    @Override
    public List<LoaiMon> listLoaiMon() {
        loaiMonList = new ArrayList<>();
        try {
            database = databaseHelper.getReadableDatabase();
            cursor = database.query(UgiDatabase.TABLE_LOAIMON,null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do {
                    LoaiMon loaiMon = new LoaiMon();
                    loaiMon.setMaLoaiMon(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_LOAIMONAN_MALOAI)));
                    loaiMon.setTenLoaiMon(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_LOAIMONAN_TENLOAI)));
                    loaiMonList.add(loaiMon);
                }while (cursor.moveToNext());
            }
        }catch (Exception ex){}
        finally {
            cursor.close();
            database.close();
        }

        return loaiMonList;
    }

    @Override
    public List<LoaiMon> listLoaiMonTheoCuaHang(int maCuaHang) {
        loaiMonList = new ArrayList<>();
        String query = "SELECT  DISTINCT  lm.MaLoaiMonAn,lm.TenLoaiMonAn FROM " + UgiDatabase.TABLE_MON + " m , "+UgiDatabase.TABLE_LOAIMON +" lm ," +UgiDatabase.TABLE_GIA +" g WHERE "
               + " lm."+UgiDatabase.COLUMN_LOAIMONAN_MALOAI +" = "+"m."+UgiDatabase.COLUMN_MONAN_MALOAI +" AND "
               + "m."+UgiDatabase.COLUMN_MONAN_MAMON +" = " + " g."+UgiDatabase.COLUMN_GIA_MAMONAN +" AND "
                + "g."+UgiDatabase.COLUMN_GIA_MACUAHANG +" = ? ";
        try {
            database = databaseHelper.getReadableDatabase();
            cursor = database.rawQuery(query,new String[]{String.valueOf(maCuaHang)});
            if (cursor.moveToFirst()){
                do {
                    LoaiMon loaiMon = new LoaiMon();
                    loaiMon.setMaLoaiMon(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_LOAIMONAN_MALOAI)));
                    loaiMon.setTenLoaiMon(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_LOAIMONAN_TENLOAI)));
                    loaiMonList.add(loaiMon);
                }while (cursor.moveToNext());
            }
        }catch (Exception ex){}
        finally {
            cursor.close();
            database.close();
        }

        return loaiMonList;
    }

    @Override
    public List<String> listTenLoaiMon() {
       List<String>  listTenLoaiMon = new ArrayList<>();
        database = databaseHelper.getReadableDatabase();
        try {
             cursor = database.query(UgiDatabase.TABLE_LOAIMON,null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do {
                    String tenLoaiMon = cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_LOAIMONAN_TENLOAI));
                    listTenLoaiMon.add(tenLoaiMon);
                }while (cursor.moveToNext());
            }
        }catch (Exception ex){}
        finally {
            cursor.close();
            database.close();
        }

        return listTenLoaiMon;
    }

    @Override
    public LoaiMon layLoaiMonById(int maLoaiMon) {
        LoaiMon loaiMon = null;
       try {
           database =databaseHelper.getReadableDatabase();
            cursor = database.query(UgiDatabase.TABLE_LOAIMON,null,UgiDatabase.COLUMN_LOAIMONAN_MALOAI + " = ? ",
                   new String[]{String.valueOf(maLoaiMon)},null,null,null);
           if (cursor.moveToFirst()){
               loaiMon = new LoaiMon();
               loaiMon.setMaLoaiMon(maLoaiMon);
               loaiMon.setTenLoaiMon(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_LOAIMONAN_TENLOAI)));
           }
       }catch (Exception ex){}
        finally {
           cursor.close();
           database.close();
       }

        return loaiMon;
    }

    @Override
    public long themLoaiMon(LoaiMon loaiMon) {
        long kiemtra = -1;
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values  = new ContentValues();
            values.put(UgiDatabase.COLUMN_LOAIMONAN_TENLOAI,loaiMon.getTenLoaiMon());
            kiemtra = database.insert(UgiDatabase.TABLE_LOAIMON,null,values);
        }catch (Exception ex){}
        finally {
            database.close();
        }
        return kiemtra;
    }

    @Override
    public long CapNhatLoaiMon(LoaiMon loaiMon) {
        long kiemtra = -1;
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values  = new ContentValues();
            values.put(UgiDatabase.COLUMN_LOAIMONAN_TENLOAI,loaiMon.getTenLoaiMon());
            kiemtra = database.update(UgiDatabase.TABLE_LOAIMON,values,UgiDatabase.COLUMN_LOAIMONAN_MALOAI +" = ? ",
                    new String[]{String.valueOf(loaiMon.getMaLoaiMon())});
        }catch (Exception ex){}
        finally {
            database.close();
        }
        return kiemtra;
    }

    @Override
    public long xoaLoaiMon(int maLoaiMon) {
        database = databaseHelper.getWritableDatabase();
        return database.delete(UgiDatabase.TABLE_LOAIMON,UgiDatabase.COLUMN_LOAIMONAN_MALOAI + " = ? ",
                new String[]{String.valueOf(maLoaiMon)});
    }

    @Override
    public boolean kiemTraLoaiMonTonTai(String tenLoaiMon) {
        boolean kiemtra = false;
        database =databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(UgiDatabase.TABLE_LOAIMON,null,UgiDatabase.COLUMN_LOAIMONAN_TENLOAI + " = ? ",
                new String[]{tenLoaiMon},null,null,null);
        if (cursor.moveToFirst()){
            kiemtra = true;
        }
        cursor.close();
        database.close();
        return kiemtra;
    }
}
