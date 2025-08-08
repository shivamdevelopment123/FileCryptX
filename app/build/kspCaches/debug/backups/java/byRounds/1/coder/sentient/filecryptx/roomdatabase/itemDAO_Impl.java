package coder.sentient.filecryptx.roomdatabase;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class itemDAO_Impl implements itemDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ItemEntity> __insertionAdapterOfItemEntity;

  private final EntityInsertionAdapter<BookmarkEntity> __insertionAdapterOfBookmarkEntity;

  private final EntityInsertionAdapter<VaultEntity> __insertionAdapterOfVaultEntity;

  private final EntityDeletionOrUpdateAdapter<VaultEntity> __deletionAdapterOfVaultEntity;

  private final SharedSQLiteStatement __preparedStmtOfRemoveBookmark;

  public itemDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfItemEntity = new EntityInsertionAdapter<ItemEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `items_Entity` (`id`,`trashFileName`,`trashFileOriginalPath`,`trashFileDeletionDate`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ItemEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTrashFileName());
        statement.bindString(3, entity.getTrashFileOriginalPath());
        statement.bindString(4, entity.getTrashFileDeletionDate());
      }
    };
    this.__insertionAdapterOfBookmarkEntity = new EntityInsertionAdapter<BookmarkEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `bookmark_entities` (`id`,`bookmarkFilePath`,`bookmarkFileName`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookmarkEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getBookmarkFilePath());
        statement.bindString(3, entity.getBookmarkFileName());
      }
    };
    this.__insertionAdapterOfVaultEntity = new EntityInsertionAdapter<VaultEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `vault_entity` (`id`,`fileName`,`originalPath`,`vaultPath`,`transferDate`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VaultEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getFileName());
        statement.bindString(3, entity.getOriginalPath());
        statement.bindString(4, entity.getVaultPath());
        statement.bindLong(5, entity.getTransferDate());
      }
    };
    this.__deletionAdapterOfVaultEntity = new EntityDeletionOrUpdateAdapter<VaultEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `vault_entity` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VaultEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__preparedStmtOfRemoveBookmark = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM bookmark_entities WHERE bookmarkFilePath = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertTrashItem(final ItemEntity trashItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfItemEntity.insert(trashItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object addBookmark(final BookmarkEntity bookmark,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBookmarkEntity.insert(bookmark);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object addVaultItem(final VaultEntity vaultItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfVaultEntity.insert(vaultItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteVaultItem(final VaultEntity vaultItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfVaultEntity.handle(vaultItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object removeBookmark(final String filePath,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveBookmark.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, filePath);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfRemoveBookmark.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getTrashItemByFileName(final String trashFileName,
      final Continuation<? super ItemEntity> $completion) {
    final String _sql = "SELECT * FROM items_Entity WHERE trashFileName = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, trashFileName);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ItemEntity>() {
      @Override
      @NonNull
      public ItemEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTrashFileName = CursorUtil.getColumnIndexOrThrow(_cursor, "trashFileName");
          final int _cursorIndexOfTrashFileOriginalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "trashFileOriginalPath");
          final int _cursorIndexOfTrashFileDeletionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "trashFileDeletionDate");
          final ItemEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTrashFileName;
            _tmpTrashFileName = _cursor.getString(_cursorIndexOfTrashFileName);
            final String _tmpTrashFileOriginalPath;
            _tmpTrashFileOriginalPath = _cursor.getString(_cursorIndexOfTrashFileOriginalPath);
            final String _tmpTrashFileDeletionDate;
            _tmpTrashFileDeletionDate = _cursor.getString(_cursorIndexOfTrashFileDeletionDate);
            _result = new ItemEntity(_tmpId,_tmpTrashFileName,_tmpTrashFileOriginalPath,_tmpTrashFileDeletionDate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTrashItem(final String trashFileName,
      final Continuation<? super ItemEntity> $completion) {
    final String _sql = "SELECT * FROM items_Entity WHERE trashFileName= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, trashFileName);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ItemEntity>() {
      @Override
      @NonNull
      public ItemEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTrashFileName = CursorUtil.getColumnIndexOrThrow(_cursor, "trashFileName");
          final int _cursorIndexOfTrashFileOriginalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "trashFileOriginalPath");
          final int _cursorIndexOfTrashFileDeletionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "trashFileDeletionDate");
          final ItemEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTrashFileName;
            _tmpTrashFileName = _cursor.getString(_cursorIndexOfTrashFileName);
            final String _tmpTrashFileOriginalPath;
            _tmpTrashFileOriginalPath = _cursor.getString(_cursorIndexOfTrashFileOriginalPath);
            final String _tmpTrashFileDeletionDate;
            _tmpTrashFileDeletionDate = _cursor.getString(_cursorIndexOfTrashFileDeletionDate);
            _result = new ItemEntity(_tmpId,_tmpTrashFileName,_tmpTrashFileOriginalPath,_tmpTrashFileDeletionDate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllBookmarks(final Continuation<? super List<BookmarkEntity>> $completion) {
    final String _sql = "SELECT * FROM bookmark_entities";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<BookmarkEntity>>() {
      @Override
      @NonNull
      public List<BookmarkEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBookmarkFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "bookmarkFilePath");
          final int _cursorIndexOfBookmarkFileName = CursorUtil.getColumnIndexOrThrow(_cursor, "bookmarkFileName");
          final List<BookmarkEntity> _result = new ArrayList<BookmarkEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BookmarkEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpBookmarkFilePath;
            _tmpBookmarkFilePath = _cursor.getString(_cursorIndexOfBookmarkFilePath);
            final String _tmpBookmarkFileName;
            _tmpBookmarkFileName = _cursor.getString(_cursorIndexOfBookmarkFileName);
            _item = new BookmarkEntity(_tmpId,_tmpBookmarkFilePath,_tmpBookmarkFileName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getVaultItemByPath(final String vaultPath,
      final Continuation<? super VaultEntity> $completion) {
    final String _sql = "SELECT * FROM vault_entity WHERE vaultPath = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, vaultPath);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<VaultEntity>() {
      @Override
      @Nullable
      public VaultEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFileName = CursorUtil.getColumnIndexOrThrow(_cursor, "fileName");
          final int _cursorIndexOfOriginalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "originalPath");
          final int _cursorIndexOfVaultPath = CursorUtil.getColumnIndexOrThrow(_cursor, "vaultPath");
          final int _cursorIndexOfTransferDate = CursorUtil.getColumnIndexOrThrow(_cursor, "transferDate");
          final VaultEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFileName;
            _tmpFileName = _cursor.getString(_cursorIndexOfFileName);
            final String _tmpOriginalPath;
            _tmpOriginalPath = _cursor.getString(_cursorIndexOfOriginalPath);
            final String _tmpVaultPath;
            _tmpVaultPath = _cursor.getString(_cursorIndexOfVaultPath);
            final long _tmpTransferDate;
            _tmpTransferDate = _cursor.getLong(_cursorIndexOfTransferDate);
            _result = new VaultEntity(_tmpId,_tmpFileName,_tmpOriginalPath,_tmpVaultPath,_tmpTransferDate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
