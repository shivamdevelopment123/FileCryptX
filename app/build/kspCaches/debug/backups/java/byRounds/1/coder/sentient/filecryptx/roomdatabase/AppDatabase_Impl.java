package coder.sentient.filecryptx.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile itemDAO _itemDAO;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `items_Entity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `trashFileName` TEXT NOT NULL, `trashFileOriginalPath` TEXT NOT NULL, `trashFileDeletionDate` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `bookmark_entities` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `bookmarkFilePath` TEXT NOT NULL, `bookmarkFileName` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `vault_entity` (`id` TEXT NOT NULL, `fileName` TEXT NOT NULL, `originalPath` TEXT NOT NULL, `vaultPath` TEXT NOT NULL, `transferDate` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9a2a02967d19c7a0e1d1d0aa16ecec90')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `items_Entity`");
        db.execSQL("DROP TABLE IF EXISTS `bookmark_entities`");
        db.execSQL("DROP TABLE IF EXISTS `vault_entity`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsItemsEntity = new HashMap<String, TableInfo.Column>(4);
        _columnsItemsEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItemsEntity.put("trashFileName", new TableInfo.Column("trashFileName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItemsEntity.put("trashFileOriginalPath", new TableInfo.Column("trashFileOriginalPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItemsEntity.put("trashFileDeletionDate", new TableInfo.Column("trashFileDeletionDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysItemsEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesItemsEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoItemsEntity = new TableInfo("items_Entity", _columnsItemsEntity, _foreignKeysItemsEntity, _indicesItemsEntity);
        final TableInfo _existingItemsEntity = TableInfo.read(db, "items_Entity");
        if (!_infoItemsEntity.equals(_existingItemsEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "items_Entity(coder.sentient.filecryptx.roomdatabase.ItemEntity).\n"
                  + " Expected:\n" + _infoItemsEntity + "\n"
                  + " Found:\n" + _existingItemsEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsBookmarkEntities = new HashMap<String, TableInfo.Column>(3);
        _columnsBookmarkEntities.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookmarkEntities.put("bookmarkFilePath", new TableInfo.Column("bookmarkFilePath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookmarkEntities.put("bookmarkFileName", new TableInfo.Column("bookmarkFileName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBookmarkEntities = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBookmarkEntities = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBookmarkEntities = new TableInfo("bookmark_entities", _columnsBookmarkEntities, _foreignKeysBookmarkEntities, _indicesBookmarkEntities);
        final TableInfo _existingBookmarkEntities = TableInfo.read(db, "bookmark_entities");
        if (!_infoBookmarkEntities.equals(_existingBookmarkEntities)) {
          return new RoomOpenHelper.ValidationResult(false, "bookmark_entities(coder.sentient.filecryptx.roomdatabase.BookmarkEntity).\n"
                  + " Expected:\n" + _infoBookmarkEntities + "\n"
                  + " Found:\n" + _existingBookmarkEntities);
        }
        final HashMap<String, TableInfo.Column> _columnsVaultEntity = new HashMap<String, TableInfo.Column>(5);
        _columnsVaultEntity.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultEntity.put("fileName", new TableInfo.Column("fileName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultEntity.put("originalPath", new TableInfo.Column("originalPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultEntity.put("vaultPath", new TableInfo.Column("vaultPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaultEntity.put("transferDate", new TableInfo.Column("transferDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVaultEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVaultEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVaultEntity = new TableInfo("vault_entity", _columnsVaultEntity, _foreignKeysVaultEntity, _indicesVaultEntity);
        final TableInfo _existingVaultEntity = TableInfo.read(db, "vault_entity");
        if (!_infoVaultEntity.equals(_existingVaultEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "vault_entity(coder.sentient.filecryptx.roomdatabase.VaultEntity).\n"
                  + " Expected:\n" + _infoVaultEntity + "\n"
                  + " Found:\n" + _existingVaultEntity);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "9a2a02967d19c7a0e1d1d0aa16ecec90", "240304b797db29a4b64d847c0f62aea6");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "items_Entity","bookmark_entities","vault_entity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `items_Entity`");
      _db.execSQL("DELETE FROM `bookmark_entities`");
      _db.execSQL("DELETE FROM `vault_entity`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(itemDAO.class, itemDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public itemDAO itemDAO() {
    if (_itemDAO != null) {
      return _itemDAO;
    } else {
      synchronized(this) {
        if(_itemDAO == null) {
          _itemDAO = new itemDAO_Impl(this);
        }
        return _itemDAO;
      }
    }
  }
}
