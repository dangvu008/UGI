package dang.ugi.com.model.BanAn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dang.ugi.com.model.DatabaseHelper.UgiDatabase;
import dang.ugi.com.model.DatabaseHelper.UgiDatabaseHelper;
import dang.ugi.com.model.Entities.BanAn;

/**
 * Created by DANG on 9/19/2016.
 */
public class ImplBanAnDatabaseHandler implements IBanAnDatabaseHandler{
    UgiDatabaseHelper databaseHelper;
    SQLiteDatabase database;
    Cursor cursor;
    BanAn banAn;
    public ImplBanAnDatabaseHandler(Context context) {
        databaseHelper = new UgiDatabaseHelper(context);

    }

    @Override
    public List<BanAn> layToanBoBanAn(int maCuaHang) {
        List<BanAn> list = null;
       try {
           database = databaseHelper.getReadableDatabase();
           cursor = database.query(UgiDatabase.TABLE_BAN,null,UgiDatabase.COLUMN_BANAN_TINHTRANG + " != -1 AND " +
                   UgiDatabase.COLUMN_CUAHANG_MACUAHANG +" = ? ",new String[]{String.valueOf(maCuaHang)},null,null," TinhTrang DESC ");
           if (cursor.moveToFirst()){
               list = new ArrayList<>();
               do {
                   BanAn banAn = new BanAn();
                   banAn.setMaBanAn(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_BANAN_MABAN)));
                   banAn.setTenBanAn(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_BANAN_TENBAN)));
                   banAn.setTinhTrang(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_BANAN_TINHTRANG)));
                   list.add(banAn);
               }while (cursor.moveToNext());

           }
       }catch (Exception ex){}
        finally {
           cursor.deactivate();
           cursor.close();
           database.close();

       }
        return list;
    }

    @Override
    public List<String> layToanBoTenBanAn(int maCuaHang) {
        List<String> listTenBan = null;
       try {
           database = databaseHelper.getReadableDatabase();
            cursor = database.query(UgiDatabase.TABLE_BAN,new String[]{UgiDatabase.COLUMN_BANAN_TENBAN},
                   UgiDatabase.COLUMN_CUAHANG_MACUAHANG +" = ? ",
                   new String[]{String.valueOf(maCuaHang)},null,null,null);
           if (cursor.moveToFirst()){
               listTenBan= new ArrayList<>();
               do {
                   String tenBan =  cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_BANAN_TENBAN));
                   listTenBan.add(tenBan);
               }while (cursor.moveToNext());

           }
       }catch (Exception ex){

       }finally {
           cursor.close();
           cursor.deactivate();
           database.close();
       }

        return listTenBan;
    }

    @Override
    public List<BanAn> timKiemBanAnBangTen(String tenBanAn,int maCuahang) {
        List<BanAn> list = new ArrayList<>();
       try {
           database = databaseHelper.getReadableDatabase();
           databaseHelper.onOpen(database);
          cursor = database.query(UgiDatabase.TABLE_BAN,null,UgiDatabase.COLUMN_BANAN_TENBAN + " LIKE ?" +
                           " AND  " +UgiDatabase.COLUMN_BANAN_MACUAHANG + " = ?",
                   new String[]{"%"+tenBanAn+"%", String.valueOf(maCuahang)},null,null,null);
           if (cursor.moveToFirst()){
               do {
                   BanAn banAn = new BanAn();
                   banAn.setMaBanAn(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_BANAN_MABAN)));
                   banAn.setTenBanAn(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_BANAN_TENBAN)));
                   list.add(banAn);
               }while (cursor.moveToNext());

           }
       }catch (Exception ex){}
        finally {
          if (cursor!=null){
              cursor.close();
              cursor.deactivate();
          }
           database.close();
       }
        return list;
    }

    @Override
    public BanAn layBanAnbyMaBanAn(int maBan) {
       try {
           database = databaseHelper.getReadableDatabase();
            cursor = database.query(UgiDatabase.TABLE_BAN,null,UgiDatabase.COLUMN_BANAN_MABAN + " = ? "
                   ,new String[]{String.valueOf(maBan)},null,null,null);
           if (cursor.moveToFirst()){
               banAn = new BanAn();
               banAn.setMaBanAn(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_BANAN_MABAN)));
               banAn.setTenBanAn(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_BANAN_TENBAN)));
               banAn.setTinhTrang(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_BANAN_TINHTRANG)));
               banAn.setMaCuaHang(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_BANAN_MACUAHANG)));
           }
       }catch (Exception ex){}
        finally {
          if (cursor!=null){
              cursor.close();
              cursor.deactivate();
          }
           database.close();
       }

        return banAn;
    }

    @Override
    public boolean kiemTraTenBanTonTai(String tenBan,int maCuaHang) {
        boolean kiemtra = false;
        try {
            database = databaseHelper.getReadableDatabase();
            cursor = database.query(UgiDatabase.TABLE_BAN,new String[]{UgiDatabase.COLUMN_BANAN_TENBAN},UgiDatabase.COLUMN_BANAN_TENBAN + " = ?"+
                            " AND  " +UgiDatabase.COLUMN_BANAN_MACUAHANG + " = ?"
                    ,new String[]{String.valueOf(tenBan), String.valueOf(maCuaHang)},null,null,null);
            if (cursor.moveToFirst() && cursor!=null){
                kiemtra = true;

            }
        }catch (Exception ex){}
        finally {
             if (cursor!=null){
                 cursor.close();
             }
            cursor.deactivate();
            database.close();
        }

        return kiemtra;
}

    @Override
    public long capNhatTrangThaiBan(int maBan, int trangThai) {
        long ketqua = -1;
        try {
            database = databaseHelper.getWritableDatabase();
            database.beginTransaction();
            ContentValues values = new ContentValues();
            values.put(UgiDatabase.COLUMN_BANAN_TINHTRANG,trangThai);
            ketqua = database.update(UgiDatabase.TABLE_BAN,values,UgiDatabase.COLUMN_BANAN_MABAN + " = ? ",
                    new String[]{String.valueOf(maBan)});
            database.setTransactionSuccessful();
        }catch (Exception ex){}
        finally {
            database.endTransaction();
            database.close();
        }
        return ketqua;
    }

    @Override
    public List<BanAn> listAllChuyenBan(int maCuaHang, int maban) {
        List<BanAn> list = null;
        try {
            database = databaseHelper.getReadableDatabase();
            cursor = database.query(UgiDatabase.TABLE_BAN,null,UgiDatabase.COLUMN_BANAN_TINHTRANG + " != 0 AND " +
                    UgiDatabase.COLUMN_CUAHANG_MACUAHANG +" = ? " +
                    " AND "+UgiDatabase.COLUMN_BANAN_MABAN  + " != ? ",new String[]{String.valueOf(maCuaHang), String.valueOf(maban)},null,null,null);
            if (cursor.moveToFirst()){
                list = new ArrayList<>();
                do {
                    BanAn banAn = new BanAn();
                    banAn.setMaBanAn(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_BANAN_MABAN)));
                    banAn.setTenBanAn(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_BANAN_TENBAN)));
                    list.add(banAn);
                }while (cursor.moveToNext());

            }
        }catch (Exception ex){}
        finally {
            cursor.deactivate();
            cursor.close();
            database.close();

        }
        return list;
    }

    @Override
    public List<BanAn> timKiemBanAnBangTen(String tenBanAn, int maCuaHang, int maBan) {
        List<BanAn> list = new ArrayList<>();
        try {
            database = databaseHelper.getReadableDatabase();
            cursor = database.query(UgiDatabase.TABLE_BAN,null,UgiDatabase.COLUMN_BANAN_TENBAN + " LIKE ?" +
                            " AND  " +UgiDatabase.COLUMN_BANAN_MACUAHANG + " = ? AND "
                    +UgiDatabase.COLUMN_BANAN_MABAN + " != ? ",
                    new String[]{"%"+tenBanAn+"%", String.valueOf(maCuaHang), String.valueOf(maBan)},null,null,null);
            if (cursor.moveToFirst()){
                do {
                    BanAn banAn = new BanAn();
                    banAn.setMaBanAn(cursor.getInt(cursor.getColumnIndex(UgiDatabase.COLUMN_BANAN_MABAN)));
                    banAn.setTenBanAn(cursor.getString(cursor.getColumnIndex(UgiDatabase.COLUMN_BANAN_TENBAN)));
                    list.add(banAn);
                }while (cursor.moveToNext());

            }
        }catch (Exception ex){}
        finally {
            if (cursor!=null){
                cursor.close();
                cursor.deactivate();
            }
            database.close();
        }
        return list;
    }

  /*  @Override
    public boolean kiemTraTrangThaiBan(String tenBan, int maCuaHang) {
        boolean kiemtra = false;
        try {
            database = databaseHelper.getReadableDatabase();
            cursor = database.query(UgiDatabase.TABLE_BAN,new String[]{UgiDatabase.COLUMN_BANAN_TINHTRANG},UgiDatabase. + " = ?"+
                            " AND  " +UgiDatabase.COLUMN_BANAN_MACUAHANG + " = ?"
                    ,new String[]{String.valueOf(tenBan), String.valueOf(maCuaHang)},null,null,null);
            if (cursor.moveToFirst() && cursor!=null){
                kiemtra = true;

            }
        }catch (Exception ex){}
        finally {
            if (cursor!=null){
                cursor.close();
            }
            cursor.deactivate();
            database.close();
        }

        return kiemtra;
    }*/

    /**
     * -1 trang thai ban khong hoat dong
     * 0 ban hoat dong nhung chua co khach / chua goi mon
     * 1 ban dang hoat dong va da goi mon
     * */
    @Override
    public long themBanAn(BanAn banAn) {
        long kiemtra = 0 ;
       try {
           database = databaseHelper.getWritableDatabase();
           database.beginTransaction();
           ContentValues values = new ContentValues();
           values.put(UgiDatabase.COLUMN_BANAN_TENBAN,banAn.getTenBanAn());
           values.put(UgiDatabase.COLUMN_BANAN_MACUAHANG,banAn.getMaCuaHang());
           values.put(UgiDatabase.COLUMN_BANAN_TINHTRANG,0);
           kiemtra = database.insert(UgiDatabase.TABLE_BAN,null,values);
           database.setTransactionSuccessful();
       }catch (Exception ex){}
        finally {
           database.endTransaction();
           database.close();
       }
        return kiemtra;
    }


    @Override
    public long suaBanAn(BanAn banAn) {
        long ketqua = -1;
        try {
            database = databaseHelper.getWritableDatabase();
            database.beginTransaction();
            ContentValues values = new ContentValues();
            values.put(UgiDatabase.COLUMN_BANAN_TENBAN,banAn.getTenBanAn());
            values.put(UgiDatabase.COLUMN_BANAN_TINHTRANG,banAn.getTinhTrang());
            ketqua = database.update(UgiDatabase.TABLE_BAN,values,UgiDatabase.COLUMN_BANAN_MABAN + " = ? "+
                            " AND  " +UgiDatabase.COLUMN_BANAN_MACUAHANG + " = ?",
                    new String[]{String.valueOf(banAn.getMaBanAn()), String.valueOf(banAn.getMaCuaHang())});
            database.setTransactionSuccessful();
        }catch (Exception ex){}
        finally {
            database.endTransaction();
            database.close();
        }
        return ketqua;
    }

    @Override
    public long xoaBanAn(int maBan) {
        database = databaseHelper.getWritableDatabase();
        return database.delete(UgiDatabase.TABLE_BAN,UgiDatabase.COLUMN_BANAN_MABAN + " = ? "
                ,new String[]{String.valueOf(maBan)});
    }

    @Override
    public int demSoBan(int maCuaHang) {
        int soBan = 0;
        try{

            database = databaseHelper.getReadableDatabase();
            cursor = database.query(UgiDatabase.TABLE_BAN,new String[]{UgiDatabase.COLUMN_BANAN_TENBAN},
                    UgiDatabase.COLUMN_BANAN_MACUAHANG + " = ?"
                    ,new String[]{String.valueOf(maCuaHang)},null,null,null);
            if (cursor.moveToFirst() && cursor!=null){
                soBan = cursor.getCount();
                cursor.close();
            }
        }catch (Exception ex){}
        finally {
            cursor.close();
            database.close();
        }


        return soBan;
    }

    @Override
    public void closedb() {
        database.close();
    }

}
