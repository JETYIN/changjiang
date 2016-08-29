package tech.boshu.changjiangshidai.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.bean.HomepageItem;

/**
 * Created by allipper on 2016-01-04.
 */
public class DBUtils {

    public static void initShortcuts(List<HomepageItem> items) {
        SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
        db.beginTransaction();
        try {
            for (HomepageItem item : items) {
                ContentValues values = new ContentValues();
                values.put(ShortcutEntry.COLUMN_KEY, item.key);
                values.put(ShortcutEntry.COLUMN_NAME, item.name);
                values.put(ShortcutEntry.COLUMN_IMAGE_NAME, item.imageName);
                values.put(ShortcutEntry.COLUMN_JUMPTO_CLASS, item.jumptoClass);
                values.put(ShortcutEntry.COLUMN_STATUS, item.status);
                db.replace(ShortcutEntry.TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public static List<HomepageItem> queryAvaliableShorcuts() {
        SQLiteDatabase db = DatabaseHelper.getInstance().getReadableDatabase();
        List<HomepageItem> items = new ArrayList<>();
        String[] columns = {ShortcutEntry.COLUMN_KEY, ShortcutEntry.COLUMN_NAME, ShortcutEntry
                .COLUMN_IMAGE_NAME, ShortcutEntry.COLUMN_JUMPTO_CLASS, ShortcutEntry.COLUMN_STATUS};
        String orderBy = ShortcutEntry.COLUMN_KEY + " ASC ";
        String selection = ShortcutEntry.COLUMN_STATUS + " = ?";
        String[] selectionArgs = {"1"};
        Cursor cursor = db.query(true, ShortcutEntry.TABLE_NAME, columns, selection,
                selectionArgs, null, null, orderBy, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            HomepageItem item = new HomepageItem();
            item.key = cursor.getInt(cursor.getColumnIndex(ShortcutEntry
                    .COLUMN_KEY));
            item.name = cursor.getString(cursor.getColumnIndex(ShortcutEntry
                    .COLUMN_NAME));
            item.imageName = cursor.getString(cursor.getColumnIndex(ShortcutEntry
                    .COLUMN_IMAGE_NAME));
            item.jumptoClass = cursor.getString(cursor.getColumnIndex(ShortcutEntry
                    .COLUMN_JUMPTO_CLASS));
            item.status = cursor.getInt(cursor.getColumnIndex(ShortcutEntry.COLUMN_STATUS));
            items.add(item);
        }

        cursor.close();
        return items;
    }

    public static List<HomepageItem> queryAllShorcuts() {
        SQLiteDatabase db = DatabaseHelper.getInstance().getReadableDatabase();
        List<HomepageItem> items = new ArrayList<>();
        String[] columns = {ShortcutEntry.COLUMN_KEY, ShortcutEntry.COLUMN_NAME, ShortcutEntry
                .COLUMN_IMAGE_NAME, ShortcutEntry.COLUMN_JUMPTO_CLASS, ShortcutEntry.COLUMN_STATUS};
        String orderBy = ShortcutEntry.COLUMN_KEY + " ASC ";
        Cursor cursor = db.query(true, ShortcutEntry.TABLE_NAME, columns, null,
                null, null, null, orderBy, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            HomepageItem item = new HomepageItem();
            item.key = cursor.getInt(cursor.getColumnIndex(ShortcutEntry
                    .COLUMN_KEY));
            item.name = cursor.getString(cursor.getColumnIndex(ShortcutEntry
                    .COLUMN_NAME));
            item.imageName = cursor.getString(cursor.getColumnIndex(ShortcutEntry
                    .COLUMN_IMAGE_NAME));
            item.jumptoClass = cursor.getString(cursor.getColumnIndex(ShortcutEntry
                    .COLUMN_JUMPTO_CLASS));
            item.status = cursor.getInt(cursor.getColumnIndex(ShortcutEntry.COLUMN_STATUS));
            items.add(item);
        }

        cursor.close();
        return items;
    }

}
