package tech.boshu.changjiangshidai.database;

import android.provider.SyncStateContract;

/**
 * Created by allipper on 2016-01-04.
 */
public class ShortcutEntry implements SyncStateContract.Columns {
    public static final String TABLE_NAME = "shortcut";
    public static final String COLUMN_KEY = "key";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMAGE_NAME = "image_name";
    public static final String COLUMN_JUMPTO_CLASS = "jumpto_class";
    public static final String COLUMN_STATUS = "status";
}
