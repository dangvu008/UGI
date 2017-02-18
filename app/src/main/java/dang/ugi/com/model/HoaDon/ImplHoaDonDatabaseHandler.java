package dang.ugi.com.model.HoaDon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.List;

import dang.ugi.com.model.DatabaseHelper.UgiDatabase;
import dang.ugi.com.model.DatabaseHelper.UgiDatabaseHelper;
import dang.ugi.com.model.Entities.HoaDon;

/**
 * Created by DANG on 12/11/2016.
 */

public class ImplHoaDonDatabaseHandler implements IHoaDonDatabaseHandler {
    SQLiteDatabase sqLiteDatabase;
    UgiDatabaseHelper ugiDatabaseHelper;
    Context context;
    List<HoaDon> listHoaDon;
    Cursor cursor;
    public ImplHoaDonDatabaseHandler(Context context) {
        this.context = context;
        ugiDatabaseHelper = new UgiDatabaseHelper(context);
    }

    @Override
    public long themHoaDon(HoaDon hoaDon) {
        long ketqua = -1 ;
        sqLiteDatabase = ugiDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UgiDatabase.COLUMN_HOADON_MAGOIMON,hoaDon.getMaGoiMon());
        values.put(UgiDatabase.COLUMN_HOADON_GHICHU,hoaDon.getGhiChu());
        values.put(UgiDatabase.COLUMN_HOADON_TIENTHEM,hoaDon.getTienthem());
        values.put(UgiDatabase.COLUMN_HOADON_MANGUOIDUNGTHANHTOAN,hoaDon.getMaNguoiDungThanhToan());
        values.put(UgiDatabase.COLUMN_HOADON_SOTIEN,hoaDon.getSotien());
        values.put(UgiDatabase.COLUMN_HOADON_THOIGIANTHANHTOAN, String.valueOf(hoaDon.getThoiGianThanhToan()));
        sqLiteDatabase.beginTransaction();
       try {
          ketqua= sqLiteDatabase.insert(UgiDatabase.TABLE_HOADON,null,values);
          sqLiteDatabase.setTransactionSuccessful();

       }catch (Exception ex){
           Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
       }
        finally {
          sqLiteDatabase.endTransaction();
           sqLiteDatabase.close();
       }
        return ketqua;
    }

    @Override
    public List<HoaDon> listAllHoaDon() {
        return null;
    }

    @Override
    public float doanhThuNgay(String ngay, int maCuaHang) {
        float doanhthu = 0;
        sqLiteDatabase = ugiDatabaseHelper.getReadableDatabase();
        try {
            String query= "SELECT strftime('%d/%m/%Y',hd."+UgiDatabase.COLUMN_HOADON_THOIGIANTHANHTOAN
                    +") as ngay ,hd."+UgiDatabase.COLUMN_HOADON_SOTIEN+" FROM "+UgiDatabase.TABLE_HOADON +"  hd ,"+UgiDatabase.TABLE_GOIMON
                    + " gm WHERE gm."+UgiDatabase.COLUMN_GOIMON_MAGOIMON + " = hd."+UgiDatabase.COLUMN_HOADON_MAGOIMON
                    +" AND ngay = ? AND gm."+UgiDatabase.COLUMN_GOIMON_MACUAHANG
                    +" = ?";
            cursor = sqLiteDatabase.rawQuery(query,new String[]{ngay, String.valueOf(maCuaHang)});
            if (cursor.moveToFirst()){
                do {
                    float tien = cursor.getFloat(cursor.getColumnIndex(UgiDatabase.COLUMN_HOADON_SOTIEN));
                    doanhthu = doanhthu + tien;
                }while (cursor.moveToNext());
            }
        }catch (Exception ex){}
        finally {
            cursor.close();
            sqLiteDatabase.close();
        }
        return doanhthu;
    }

    @Override
    public float doanhThuThang(String thang, int maCuaHang) {
        float doanhthu = 0;
        sqLiteDatabase = ugiDatabaseHelper.getReadableDatabase();
        try {
            String query= "SELECT strftime('%m/%Y',hd."+UgiDatabase.COLUMN_HOADON_THOIGIANTHANHTOAN
                    +") as thang ,hd."+UgiDatabase.COLUMN_HOADON_SOTIEN+" FROM "+UgiDatabase.TABLE_HOADON +"  hd ,"+UgiDatabase.TABLE_GOIMON
                    + " gm WHERE gm."+UgiDatabase.COLUMN_GOIMON_MAGOIMON + " = hd."+UgiDatabase.COLUMN_HOADON_MAGOIMON
                    +" AND thang = ? AND gm."+UgiDatabase.COLUMN_GOIMON_MACUAHANG
                    +" = ?";
            cursor = sqLiteDatabase.rawQuery(query,new String[]{thang, String.valueOf(maCuaHang)});
            if (cursor.moveToFirst()){
                do {
                    float tien = cursor.getFloat(cursor.getColumnIndex(UgiDatabase.COLUMN_HOADON_SOTIEN));
                    doanhthu = doanhthu + tien;
                }while (cursor.moveToNext());
            }
        }catch (Exception ex){}
        finally {
            cursor.close();
            sqLiteDatabase.close();
        }
        return doanhthu;
    }

    @Override
    public float doanhThuNam(String nam, int maCuaHang) {
        float doanhthu = 0;
        sqLiteDatabase = ugiDatabaseHelper.getReadableDatabase();
        try {
            String query= "SELECT strftime('%Y',hd."+UgiDatabase.COLUMN_HOADON_THOIGIANTHANHTOAN
                    +") as nam ,hd."+UgiDatabase.COLUMN_HOADON_SOTIEN+" FROM "+UgiDatabase.TABLE_HOADON +"  hd ,"+UgiDatabase.TABLE_GOIMON
                    + " gm WHERE gm."+UgiDatabase.COLUMN_GOIMON_MAGOIMON + " = hd."+UgiDatabase.COLUMN_HOADON_MAGOIMON
                    +" AND nam = ? AND gm."+UgiDatabase.COLUMN_GOIMON_MACUAHANG
                    +" = ?";
            cursor = sqLiteDatabase.rawQuery(query,new String[]{nam, String.valueOf(maCuaHang)});
            if (cursor.moveToFirst()){
                do {
                    float tien = cursor.getFloat(cursor.getColumnIndex(UgiDatabase.COLUMN_HOADON_SOTIEN));
                    doanhthu = doanhthu + tien;
                }while (cursor.moveToNext());
            }
        }catch (Exception ex){}
        finally {
            cursor.close();
            sqLiteDatabase.close();
        }
        return doanhthu;
    }

    @Override
    public long xoaHoaDon(int maHoaDon) {
        long ketqua = -1 ;
        sqLiteDatabase = ugiDatabaseHelper.getWritableDatabase();
        try {
            sqLiteDatabase.beginTransaction();
            ketqua = sqLiteDatabase.delete(UgiDatabase.TABLE_HOADON,UgiDatabase.COLUMN_HOADON_MAHOADON +" = ?",new String[]{String.valueOf(maHoaDon)});
            sqLiteDatabase.setTransactionSuccessful();

        }catch (Exception ex){}
        finally {
            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();
        }
        return ketqua;
    }

    @Override
    public float doanhThuTheoBan(int maBan) {
        float doanhthu = 0;
        sqLiteDatabase = ugiDatabaseHelper.getReadableDatabase();
        try {
            String query= "SELECT gm."+UgiDatabase.COLUMN_GOIMON_MABAN
                    +" as maban ,hd."+UgiDatabase.COLUMN_HOADON_SOTIEN+" FROM "+UgiDatabase.TABLE_HOADON +"  hd ,"+UgiDatabase.TABLE_GOIMON
                    + " gm WHERE gm."+UgiDatabase.COLUMN_GOIMON_MAGOIMON + " = hd."+UgiDatabase.COLUMN_HOADON_MAGOIMON
                    +" AND maban = ? ";
            cursor = sqLiteDatabase.rawQuery(query,new String[]{String.valueOf(maBan)});
            if (cursor.moveToFirst()){
                do {
                    float tien = cursor.getFloat(cursor.getColumnIndex(UgiDatabase.COLUMN_HOADON_SOTIEN));
                    doanhthu = doanhthu + tien;
                }while (cursor.moveToNext());
            }
        }catch (Exception ex){}
        finally {
            cursor.close();
            sqLiteDatabase.close();
        }
        return doanhthu;
    }
}
