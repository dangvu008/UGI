package dang.ugi.com.model.DatabaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DANG on 9/9/2016.
 */
public class UgiDatabaseHelper extends SQLiteOpenHelper {
    public UgiDatabaseHelper(Context context) {
        super(context, UgiDatabase.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UgiDatabase.CREATE_TABLE_BANAN);
        sqLiteDatabase.execSQL(UgiDatabase.CREATE_TABLE_GOIMON);
        sqLiteDatabase.execSQL(UgiDatabase.CREATE_TABLE_CHITIET_GOIMON);
        sqLiteDatabase.execSQL(UgiDatabase.CREATE_TABLE_CUAHANG_NGUOIDUNG);
        sqLiteDatabase.execSQL(UgiDatabase.CREATE_TABLE_CUAHANG);
        sqLiteDatabase.execSQL(UgiDatabase.CREATE_TABLE_LOAI_CUAHANG);
        sqLiteDatabase.execSQL(UgiDatabase.CREATE_TABLE_LOAI_CUAHANG_LOAIMON);
        sqLiteDatabase.execSQL(UgiDatabase.CREATE_TABLE_NGUOIDUNG);
        sqLiteDatabase.execSQL(UgiDatabase.CREATE_TABLE_MON);
        sqLiteDatabase.execSQL(UgiDatabase.CREATE_TABLE_GIA);
        sqLiteDatabase.execSQL(UgiDatabase.CREATE_TABLE_LOAIMON);
        sqLiteDatabase.execSQL(UgiDatabase.CREATE_TABLE_PHANQUYEN);
        sqLiteDatabase.execSQL(UgiDatabase.CREATE_TABLE_DOANHTHUMON);
        sqLiteDatabase.execSQL(UgiDatabase.CREATE_TABLE_HOADON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+UgiDatabase.TABLE_BAN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+UgiDatabase.TABLE_CHITIET_GOIMON);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+UgiDatabase.TABLE_CUAHANG);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+UgiDatabase.TABLE_LOAICUAHANG_LOAIMON);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+UgiDatabase.TABLE_LOAI_CUAHANG);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+UgiDatabase.TABLE_GIA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+UgiDatabase.TABLE_GOIMON);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+UgiDatabase.TABLE_LOAIMON);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+UgiDatabase.TABLE_MON);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+UgiDatabase.TABLE_GIA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+UgiDatabase.TABLE_NGUOIDUNG);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+UgiDatabase.TABLE_NGUOIDUNG_CUAHANG);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+UgiDatabase.TABLE_PHANQUYEN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  "+UgiDatabase.TABLE_DOANHTHUMON);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
