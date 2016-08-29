package tech.boshu.changjiangshidai.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import tech.boshu.changjiangshidai.libs.manager.Contextor;

/**
 * Created by allipper on 2016-01-04.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper sInstance;

    private static final String DATABASE_NAME = "changjiangshidai";
    private static final int DATABASE_VERSION = 1;


    /**
     * 创建首页快捷方式
     */
    private static final String SQL_CREATE_ENTRY_SHORTCUT = "CREATE TABLE "
            + ShortcutEntry.TABLE_NAME + " (" + ShortcutEntry._ID
            + " INTEGER PRIMARY KEY," + ShortcutEntry.COLUMN_KEY
            + " INTEGER," + ShortcutEntry.COLUMN_NAME
            + " TEXT NOT NULL," + ShortcutEntry.COLUMN_IMAGE_NAME
            + " TEXT NOT NULL," + ShortcutEntry.COLUMN_JUMPTO_CLASS
            + " TEXT NOT NULL," + ShortcutEntry.COLUMN_STATUS
            + " INTEGER, UNIQUE("
            + ShortcutEntry.COLUMN_KEY + "))";

    /**
     * 删除首页快捷方式
     */
    private static final String SQL_DELETE_ENTRY_SHORTCUT = "DROP TABLE IF EXISTS "
            + ShortcutEntry.TABLE_NAME;


    public static synchronized DatabaseHelper getInstance() {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelper(Contextor.getInstance().getContext());
        }
        return sInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static method "getInstance()" instead.
     */
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRY_SHORTCUT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
