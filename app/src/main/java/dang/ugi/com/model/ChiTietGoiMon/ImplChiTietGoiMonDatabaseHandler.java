package dang.ugi.com.model.ChiTietGoiMon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dang.ugi.com.model.DatabaseHelper.UgiDatabase;
import dang.ugi.com.model.DatabaseHelper.UgiDatabaseHelper;
import dang.ugi.com.model.Entities.ChiTietGoiMon;

/**
 * Created by DANG on 11/19/2016.
 */

public class ImplChiTietGoiMonDatabaseHandler implements IChiTietGoiMonDatabaseHandler {
    Context context;
    SQLiteDatabase database;
    UgiDatabaseHelper databaseHelper;
    List<ChiTietGoiMon> chiTietGoiMons;
    Cursor cursor;
    public ImplChiTietGoiMonDatabaseHandler(Context context) {
        this.context = context;
        databaseHelper = new UgiDatabaseHelper(context);
    }

    @Override
    public long themChiTietGoiMon(ChiTietGoiMon chiTietGoiMon) {
        int ketqua = -1;
        database =databaseHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(UgiDatabase.COLUMN_CHITETGOIMON_MAGOIMON,chiTietGoiMon.getMaGoiMon());
            values.put(UgiDatabase.COLUMN_CHITETGOIMON_MAMONAN,chiTietGoiMon.getMaMon());
            values.put(UgiDatabase.COLUMN_CHITETGOIMON_SOLUONG,chiTietGoiMon.getSoLuong());
            values.put(UgiDatabase.COLUMN_CHITETGOIMON_THANHTIEN,chiTietGoiMon.getThanhTien());
            ketqua = (int) database.insert(UgiDatabase.TABLE_CHITIET_GOIMON,null,values);
        }catch (Exception ex){}
        finally {
            database.close();
        }
        return ketqua;
    }

    @Override
    public long capnhatChiTietGoiMon(ChiTietGoiMon chiTietGoiMon) {
        int ketqua = -1;
        database =databaseHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(UgiDatabase.COLUMN_CHITETGOIMON_MAGOIMON,chiTietGoiMon.getMaGoiMon());
            values.put(UgiDatabase.COLUMN_CHITETGOIMON_MAMONAN,chiTietGoiMon.getMaMon());
            values.put(UgiDatabase.COLUMN_CHITETGOIMON_SOLUONG,chiTietGoiMon.getSoLuong());
            values.put(UgiDatabase.COLUMN_CHITETGOIMON_THANHTIEN,chiTietGoiMon.getThanhTien());
            ketqua = database.update(UgiDatabase.TABLE_CHITIET_GOIMON,values,UgiDatabase.COLUMN_CHITETGOIMON_MACHITIETGOIMON + " = ?",
                    new String[]{String.valueOf(chiTietGoiMon.getMaChiTietGoiMon())});
        }catch (Exception ex){}
        finally {
            database.close();
        }
        return ketqua;

    }

    @Override
    public long capnhatSoLuongChiTietGoiMon(ChiTietGoiMon chiTietGoiMon) {
        int ketqua = -1;
        database =databaseHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(UgiDatabase.COLUMN_CHITETGOIMON_SOLUONG,chiTietGoiMon.getSoLuong());
            ketqua = database.update(UgiDatabase.TABLE_CHITIET_GOIMON,values,UgiDatabase.COLUMN_CHITETGOIMON_MACHITIETGOIMON +" = ?",
                    new String[]{String.valueOf(chiTietGoiMon.getMaChiTietGoiMon())});
        }catch (Exception ex){}
        finally {
            database.close();
        }
        return ketqua;
    }

    @Override
    public ChiTietGoiMon kiemTraMonDaTonTai(int maMon, int maGoiMon) {
        ChiTietGoiMon chiTietGoiMon =null;
        database = databaseHelper.getReadableDatabase();
        try {
          /*  String query = "SELECT COUNT(*) FROM "
                  +  UgiDatabase.TABLE_CHITIET_GOIMON + " ctgm WHERE "
                    + " ctgm."+UgiDatabase.COLUMN_CHITETGOIMON_MAMONAN + " = ?"
                    + " AND ctgm." +UgiDatabase.COLUMN_CHITETGOIMON_MAGOIMON + " = ? ";*/
            cursor = database.query(UgiDatabase.TABLE_CHITIET_GOIMON,null,
                    UgiDatabase.COLUMN_CHITETGOIMON_MAMONAN +  " = ? AND " +UgiDatabase.COLUMN_CHITETGOIMON_MAGOIMON + " = ?",
                    new String[]{String.valueOf(maMon), String.valueOf(maGoiMon)},null,null,null);
            if (cursor.moveToFirst()){
               chiTietGoiMon  = new ChiTietGoiMon();
                chiTietGoiMon.setMaChiTietGoiMon(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_CHITETGOIMON_MACHITIETGOIMON)));
                chiTietGoiMon.setMaMon(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_CHITETGOIMON_MAMONAN)));
                chiTietGoiMon.setSoLuong(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_CHITETGOIMON_SOLUONG)));
                chiTietGoiMon.setThanhTien(cursor.getFloat(cursor.getColumnIndex(UgiDatabase.COLUMN_CHITETGOIMON_THANHTIEN)));
            }

        }catch (Exception ex){}
        finally {
            cursor.close();
            database.close();
        }
        return chiTietGoiMon;
    }


    @Override
    public long xoaChiTietGoiMon(int maChiTietGoiMon) {
        database = databaseHelper.getWritableDatabase();
        return database.delete(UgiDatabase.TABLE_CHITIET_GOIMON,UgiDatabase.COLUMN_CHITETGOIMON_MACHITIETGOIMON + " = ?",
                new String[]{String.valueOf(maChiTietGoiMon)});
    }

    @Override
    public long xoaChiTietGoiMonByMaGoiMon(int maGoiMon) {
        long ketqua = -1;
        chiTietGoiMons= this.listChiTietGoiMon(maGoiMon);
        database = databaseHelper.getWritableDatabase();
        try {
            database.beginTransaction();
            for (int i =0;i<chiTietGoiMons.size();i++){
                ketqua = database.delete(UgiDatabase.TABLE_CHITIET_GOIMON,UgiDatabase.COLUMN_CHITETGOIMON_MACHITIETGOIMON + " = ?",
                        new String[]{String.valueOf(chiTietGoiMons.get(i).getMaChiTietGoiMon())});
            }
            database.setTransactionSuccessful();
        }catch (Exception ex){}
        finally {
            database.endTransaction();
            database.close();
        }
        return ketqua;
    }

    @Override
    public List<ChiTietGoiMon> listChiTietGoiMon(int maGoiMon) {
        database = databaseHelper.getReadableDatabase();
        chiTietGoiMons = new ArrayList<>();
        try {
            cursor = database.query(UgiDatabase.TABLE_CHITIET_GOIMON,null,UgiDatabase.COLUMN_CHITETGOIMON_MAGOIMON + " = ?",
                    new String[]{String.valueOf(maGoiMon)},null,null,null);
            if (cursor.moveToFirst()){
                do {
                    ChiTietGoiMon chiTietGoiMon = new ChiTietGoiMon();
                    chiTietGoiMon.setMaChiTietGoiMon(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_CHITETGOIMON_MACHITIETGOIMON)));
                    chiTietGoiMon.setMaMon(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_CHITETGOIMON_MAMONAN)));
                    chiTietGoiMon.setSoLuong(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_CHITETGOIMON_SOLUONG)));
                    chiTietGoiMon.setThanhTien(cursor.getFloat(cursor.getColumnIndex(UgiDatabase.COLUMN_CHITETGOIMON_THANHTIEN)));
                    chiTietGoiMons.add(chiTietGoiMon);
                }while (cursor.moveToNext());

            }
        }catch (Exception ex){}
        finally {
            if (cursor!=null){
                cursor.close();
            }
            database.close();
        }
        return chiTietGoiMons;
    }

    @Override
    public ChiTietGoiMon layChiTietGoiMonTheoMa(int maChiTietGoiMon) {
        database = databaseHelper.getReadableDatabase();
        ChiTietGoiMon chiTietGoiMon = null;
        try {
            cursor = database.query(UgiDatabase.CREATE_TABLE_CHITIET_GOIMON,null,UgiDatabase.COLUMN_CHITETGOIMON_MACHITIETGOIMON + " = ?",
                    new String[]{String.valueOf(maChiTietGoiMon)},null,null,null);
            if (cursor.moveToFirst()){

                    chiTietGoiMon = new ChiTietGoiMon();
                    chiTietGoiMon.setMaChiTietGoiMon(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_CHITETGOIMON_MACHITIETGOIMON)));
                    chiTietGoiMon.setMaMon(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_CHITETGOIMON_MAMONAN)));
                    chiTietGoiMon.setSoLuong(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_CHITETGOIMON_SOLUONG)));
                    chiTietGoiMon.setThanhTien(cursor.getFloat(cursor.getColumnIndex(UgiDatabase.COLUMN_CHITETGOIMON_THANHTIEN)));
            }
        }catch (Exception ex){}
        finally {
            cursor.close();
            database.close();
        }
        return chiTietGoiMon;
    }
}
