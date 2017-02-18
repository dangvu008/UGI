package dang.ugi.com.model.GoiMon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dang.ugi.com.model.DatabaseHelper.UgiDatabase;
import dang.ugi.com.model.DatabaseHelper.UgiDatabaseHelper;
import dang.ugi.com.model.Entities.GoiMon;
import dang.ugi.com.model.Utils.FormatData;

/**
 * Created by DANG on 11/20/2016.
 */

public class ImplGoiMonDatabaseHandler implements IGoiMonDatabaseHandler {
  private  Context context;
  private UgiDatabaseHelper ugiDatabaseHelper;
    private SQLiteDatabase database;
   private List<GoiMon> listGoiMon;
   private GoiMon goiMon;
    Cursor cursor;

    public ImplGoiMonDatabaseHandler(Context context) {
        this.context = context;
        ugiDatabaseHelper = new UgiDatabaseHelper(context);
    }
/**
 * -1 trang goi mon  bi loi
 * 0 trang thai da goi thanh cong nhưng chua thanh toan
 * 1 ban da goi mon nhung da thanh toan
 * */
    @Override
    public long themGoiMon(GoiMon goiMon) {
        return 0;
    }

    @Override
    public long themTamGoiMon(GoiMon goiMon) {
        long ketqua = -1;
        database = ugiDatabaseHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(UgiDatabase.COLUMN_GOIMON_MABAN, goiMon.getMaBan());
            values.put(UgiDatabase.COLUMN_GOIMON_MACUAHANG,goiMon.getMaCuaHang());
            values.put(UgiDatabase.COLUMN_GOIMON_MANGUOIDUNGGOI,goiMon.getMaNguoiDungGoi());
            values.put(UgiDatabase.COLUMN_GOIMON_THOIGIANBATDAU, String.valueOf(goiMon.getThoiGianBatDau()));
            ketqua = database.insert(UgiDatabase.TABLE_GOIMON,null,values);
        }catch (Exception ex){}
        finally {
            database.close();
        }
        return ketqua;
    }

    @Override
    public long capNhatGoiMon(GoiMon goiMon) {
        long ketqua = -1;
        database = ugiDatabaseHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(UgiDatabase.COLUMN_GOIMON_MANGUOIDUNGGOI,goiMon.getMaNguoiDungGoi());
            values.put(UgiDatabase.COLUMN_GOIMON_THOIGIANBATDAU, String.valueOf(goiMon.getThoiGianBatDau()));
            values.put(UgiDatabase.COLUMN_GOIMON_TONGTIEN,goiMon.getTongTien());
            values.put(UgiDatabase.COLUMN_GOIMON_TINHTRANG,goiMon.getTinhTrang());
            ketqua = database.update(UgiDatabase.TABLE_GOIMON,values,UgiDatabase.COLUMN_GOIMON_MAGOIMON + " = ? ",new String[]{String.valueOf(goiMon.getMaGoiMon())});
        }catch (Exception ex){}
        finally {
            database.close();
        }
        return ketqua;
    }

    @Override
    public long xoaGoiMon(int maGoiMon) {
        long ketqua = -1 ;
        database = ugiDatabaseHelper.getWritableDatabase();
      try {
          database.beginTransaction();
          ketqua= database.delete(UgiDatabase.TABLE_GOIMON,UgiDatabase.COLUMN_GOIMON_MAGOIMON + " = ?",new String[]{String.valueOf(maGoiMon)});
          database.setTransactionSuccessful();
      }catch (Exception ex){}
        finally {
            database.endTransaction();
          database.close();
      }
        return ketqua;
    }

    @Override
    public List<GoiMon> listAllGoiMon(int maCuaHang) {
        database = ugiDatabaseHelper.getReadableDatabase();
        try {
            cursor = database.query(UgiDatabase.TABLE_GOIMON,null,UgiDatabase.COLUMN_GOIMON_MACUAHANG + " = ? ",
                    new String[]{String.valueOf(maCuaHang)},null,null,UgiDatabase.COLUMN_GOIMON_MAGOIMON +" DESC ");
            if (cursor.moveToFirst()){
                listGoiMon = new ArrayList<>();
                do {
                    goiMon = cursorToGoiMon(cursor);
                    listGoiMon.add(goiMon);
                }while (cursor.moveToNext());
            }
        }catch (Exception ex){}
        finally {
            if (cursor!=null)cursor.close();
            database.close();
        }
        return listGoiMon;
    }

    @Override
    public List<GoiMon> listAllGoiMonTheoTinhTrang(int maCuaHang, int tinhTrang) {
        database = ugiDatabaseHelper.getReadableDatabase();
        try {
            cursor = database.query(UgiDatabase.TABLE_GOIMON,null,UgiDatabase.COLUMN_GOIMON_MACUAHANG + " = ? " +
                    " AND " +UgiDatabase.COLUMN_GOIMON_TINHTRANG + " = ? ",
                    new String[]{String.valueOf(maCuaHang), String.valueOf(tinhTrang)},null,null,UgiDatabase.COLUMN_GOIMON_MAGOIMON +" DESC ");
            if (cursor.moveToFirst()){
                listGoiMon = new ArrayList<>();
                do {
                    goiMon = cursorToGoiMon(cursor);
                    listGoiMon.add(goiMon);
                }while (cursor.moveToNext());
            }
        }catch (Exception ex){
            Log.d("loiroi",ex.getMessage());
        }
        finally {
             if (cursor!=null){
                 cursor.close();
             }
            database.close();
        }
        return listGoiMon;
    }

    @Override
    public GoiMon layGoiMonTheoMaGoiMon(int maGoiMon) {
        database = ugiDatabaseHelper.getReadableDatabase();
        try {
            cursor = database.query(UgiDatabase.TABLE_GOIMON,null,UgiDatabase.COLUMN_GOIMON_MAGOIMON + " = ? ",
                    new String[]{String.valueOf(maGoiMon)},null,null,null);
            if (cursor.moveToFirst()){
                goiMon = cursorToGoiMon(cursor);
            }
        }catch (Exception ex){}
        finally {
            if (cursor!=null){
                cursor.close();
            }
            database.close();
        }
        return goiMon;
    }

    @Override
    public GoiMon layGoiMonDangGoi(int maBan) {
        database = ugiDatabaseHelper.getReadableDatabase();
        try {
            cursor = database.query(UgiDatabase.TABLE_GOIMON,null,UgiDatabase.COLUMN_GOIMON_MABAN + " = ? " +
                    " AND "+UgiDatabase.COLUMN_GOIMON_TINHTRANG + " = 0",
                    new String[]{String.valueOf(maBan)},null,null,null);
            if (cursor.moveToFirst()){
                  goiMon = cursorToGoiMon(cursor);
            }
        }catch (Exception ex){}
        finally {
            if (cursor!=null){
                cursor.close();
            }
            database.close();
        }
        return goiMon;
    }

    @Override
    public long xoaLichSuGoiMonTam() {
        long ketqua = -1 ;
        database = ugiDatabaseHelper.getWritableDatabase();
        try {
            database.beginTransaction();
            ketqua= database.delete(UgiDatabase.TABLE_GOIMON,UgiDatabase.COLUMN_GOIMON_TINHTRANG + " = -1",null);
            database.setTransactionSuccessful();
        }catch (Exception ex){}
        finally {
            database.endTransaction();
            database.close();
        }
        return 0;
    }


    public GoiMon cursorToGoiMon(Cursor cursor){
            goiMon = new GoiMon();
            goiMon.setMaGoiMon(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_GOIMON_MAGOIMON)));
            goiMon.setMaBan(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_GOIMON_MABAN)));
            goiMon.setMaCuaHang(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_GOIMON_MACUAHANG)));
            goiMon.setMaNguoiDungGoi(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_GOIMON_MANGUOIDUNGGOI)));
            goiMon.setTongTien(cursor.getFloat(cursor.getColumnIndex(UgiDatabase.COLUMN_GOIMON_TONGTIEN)));
            goiMon.setTinhTrang(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_GOIMON_TINHTRANG)));
            goiMon.setThoiGianBatDau(FormatData.formatDateTimeVietNam(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_GOIMON_THOIGIANBATDAU))));

        return goiMon;
    }
    @Override
    public int layMaGoiMonCuoiCung() {
        return 0;
    }
}
