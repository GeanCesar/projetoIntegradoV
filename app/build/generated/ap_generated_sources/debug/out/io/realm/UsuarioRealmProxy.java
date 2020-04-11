package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.ProxyUtils;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class UsuarioRealmProxy extends com.example.projetointegrado.modelos.Usuario
    implements RealmObjectProxy, UsuarioRealmProxyInterface {

    static final class UsuarioColumnInfo extends ColumnInfo {
        long emailIndex;
        long nomeIndex;
        long senhaIndex;

        UsuarioColumnInfo(OsSchemaInfo schemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Usuario");
            this.emailIndex = addColumnDetails("email", objectSchemaInfo);
            this.nomeIndex = addColumnDetails("nome", objectSchemaInfo);
            this.senhaIndex = addColumnDetails("senha", objectSchemaInfo);
        }

        UsuarioColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new UsuarioColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final UsuarioColumnInfo src = (UsuarioColumnInfo) rawSrc;
            final UsuarioColumnInfo dst = (UsuarioColumnInfo) rawDst;
            dst.emailIndex = src.emailIndex;
            dst.nomeIndex = src.nomeIndex;
            dst.senhaIndex = src.senhaIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(3);
        fieldNames.add("email");
        fieldNames.add("nome");
        fieldNames.add("senha");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private UsuarioColumnInfo columnInfo;
    private ProxyState<com.example.projetointegrado.modelos.Usuario> proxyState;

    UsuarioRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (UsuarioColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.projetointegrado.modelos.Usuario>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$email() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.emailIndex);
    }

    @Override
    public void realmSet$email(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'email' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$nome() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nomeIndex);
    }

    @Override
    public void realmSet$nome(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'nome' to null.");
            }
            row.getTable().setString(columnInfo.nomeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'nome' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.nomeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$senha() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.senhaIndex);
    }

    @Override
    public void realmSet$senha(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'senha' to null.");
            }
            row.getTable().setString(columnInfo.senhaIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'senha' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.senhaIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Usuario", 3, 0);
        builder.addPersistedProperty("email", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("nome", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("senha", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static UsuarioColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new UsuarioColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Usuario";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.example.projetointegrado.modelos.Usuario createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.projetointegrado.modelos.Usuario obj = null;
        if (update) {
            Table table = realm.getTable(com.example.projetointegrado.modelos.Usuario.class);
            UsuarioColumnInfo columnInfo = (UsuarioColumnInfo) realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Usuario.class);
            long pkColumnIndex = columnInfo.emailIndex;
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("email")) {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("email"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Usuario.class), false, Collections.<String> emptyList());
                    obj = new io.realm.UsuarioRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("email")) {
                if (json.isNull("email")) {
                    obj = (io.realm.UsuarioRealmProxy) realm.createObjectInternal(com.example.projetointegrado.modelos.Usuario.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.UsuarioRealmProxy) realm.createObjectInternal(com.example.projetointegrado.modelos.Usuario.class, json.getString("email"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'email'.");
            }
        }

        final UsuarioRealmProxyInterface objProxy = (UsuarioRealmProxyInterface) obj;
        if (json.has("nome")) {
            if (json.isNull("nome")) {
                objProxy.realmSet$nome(null);
            } else {
                objProxy.realmSet$nome((String) json.getString("nome"));
            }
        }
        if (json.has("senha")) {
            if (json.isNull("senha")) {
                objProxy.realmSet$senha(null);
            } else {
                objProxy.realmSet$senha((String) json.getString("senha"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.projetointegrado.modelos.Usuario createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.example.projetointegrado.modelos.Usuario obj = new com.example.projetointegrado.modelos.Usuario();
        final UsuarioRealmProxyInterface objProxy = (UsuarioRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("email")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$email((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$email(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("nome")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$nome((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$nome(null);
                }
            } else if (name.equals("senha")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$senha((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$senha(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'email'.");
        }
        return realm.copyToRealm(obj);
    }

    public static com.example.projetointegrado.modelos.Usuario copyOrUpdate(Realm realm, com.example.projetointegrado.modelos.Usuario object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.example.projetointegrado.modelos.Usuario) cachedRealmObject;
        }

        com.example.projetointegrado.modelos.Usuario realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.example.projetointegrado.modelos.Usuario.class);
            UsuarioColumnInfo columnInfo = (UsuarioColumnInfo) realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Usuario.class);
            long pkColumnIndex = columnInfo.emailIndex;
            long rowIndex = table.findFirstString(pkColumnIndex, ((UsuarioRealmProxyInterface) object).realmGet$email());
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Usuario.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.UsuarioRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, realmObject, object, cache) : copy(realm, object, update, cache);
    }

    public static com.example.projetointegrado.modelos.Usuario copy(Realm realm, com.example.projetointegrado.modelos.Usuario newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.projetointegrado.modelos.Usuario) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.example.projetointegrado.modelos.Usuario realmObject = realm.createObjectInternal(com.example.projetointegrado.modelos.Usuario.class, ((UsuarioRealmProxyInterface) newObject).realmGet$email(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        UsuarioRealmProxyInterface realmObjectSource = (UsuarioRealmProxyInterface) newObject;
        UsuarioRealmProxyInterface realmObjectCopy = (UsuarioRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$nome(realmObjectSource.realmGet$nome());
        realmObjectCopy.realmSet$senha(realmObjectSource.realmGet$senha());
        return realmObject;
    }

    public static long insert(Realm realm, com.example.projetointegrado.modelos.Usuario object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.projetointegrado.modelos.Usuario.class);
        long tableNativePtr = table.getNativePtr();
        UsuarioColumnInfo columnInfo = (UsuarioColumnInfo) realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Usuario.class);
        long pkColumnIndex = columnInfo.emailIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((UsuarioRealmProxyInterface) object).realmGet$email();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$nome = ((UsuarioRealmProxyInterface) object).realmGet$nome();
        if (realmGet$nome != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nomeIndex, rowIndex, realmGet$nome, false);
        }
        String realmGet$senha = ((UsuarioRealmProxyInterface) object).realmGet$senha();
        if (realmGet$senha != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.senhaIndex, rowIndex, realmGet$senha, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.projetointegrado.modelos.Usuario.class);
        long tableNativePtr = table.getNativePtr();
        UsuarioColumnInfo columnInfo = (UsuarioColumnInfo) realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Usuario.class);
        long pkColumnIndex = columnInfo.emailIndex;
        com.example.projetointegrado.modelos.Usuario object = null;
        while (objects.hasNext()) {
            object = (com.example.projetointegrado.modelos.Usuario) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((UsuarioRealmProxyInterface) object).realmGet$email();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$nome = ((UsuarioRealmProxyInterface) object).realmGet$nome();
            if (realmGet$nome != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nomeIndex, rowIndex, realmGet$nome, false);
            }
            String realmGet$senha = ((UsuarioRealmProxyInterface) object).realmGet$senha();
            if (realmGet$senha != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.senhaIndex, rowIndex, realmGet$senha, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.projetointegrado.modelos.Usuario object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.projetointegrado.modelos.Usuario.class);
        long tableNativePtr = table.getNativePtr();
        UsuarioColumnInfo columnInfo = (UsuarioColumnInfo) realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Usuario.class);
        long pkColumnIndex = columnInfo.emailIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((UsuarioRealmProxyInterface) object).realmGet$email();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$nome = ((UsuarioRealmProxyInterface) object).realmGet$nome();
        if (realmGet$nome != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nomeIndex, rowIndex, realmGet$nome, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nomeIndex, rowIndex, false);
        }
        String realmGet$senha = ((UsuarioRealmProxyInterface) object).realmGet$senha();
        if (realmGet$senha != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.senhaIndex, rowIndex, realmGet$senha, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.senhaIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.projetointegrado.modelos.Usuario.class);
        long tableNativePtr = table.getNativePtr();
        UsuarioColumnInfo columnInfo = (UsuarioColumnInfo) realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Usuario.class);
        long pkColumnIndex = columnInfo.emailIndex;
        com.example.projetointegrado.modelos.Usuario object = null;
        while (objects.hasNext()) {
            object = (com.example.projetointegrado.modelos.Usuario) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((UsuarioRealmProxyInterface) object).realmGet$email();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$nome = ((UsuarioRealmProxyInterface) object).realmGet$nome();
            if (realmGet$nome != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nomeIndex, rowIndex, realmGet$nome, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nomeIndex, rowIndex, false);
            }
            String realmGet$senha = ((UsuarioRealmProxyInterface) object).realmGet$senha();
            if (realmGet$senha != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.senhaIndex, rowIndex, realmGet$senha, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.senhaIndex, rowIndex, false);
            }
        }
    }

    public static com.example.projetointegrado.modelos.Usuario createDetachedCopy(com.example.projetointegrado.modelos.Usuario realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.projetointegrado.modelos.Usuario unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.projetointegrado.modelos.Usuario();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.projetointegrado.modelos.Usuario) cachedObject.object;
            }
            unmanagedObject = (com.example.projetointegrado.modelos.Usuario) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        UsuarioRealmProxyInterface unmanagedCopy = (UsuarioRealmProxyInterface) unmanagedObject;
        UsuarioRealmProxyInterface realmSource = (UsuarioRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$email(realmSource.realmGet$email());
        unmanagedCopy.realmSet$nome(realmSource.realmGet$nome());
        unmanagedCopy.realmSet$senha(realmSource.realmGet$senha());

        return unmanagedObject;
    }

    static com.example.projetointegrado.modelos.Usuario update(Realm realm, com.example.projetointegrado.modelos.Usuario realmObject, com.example.projetointegrado.modelos.Usuario newObject, Map<RealmModel, RealmObjectProxy> cache) {
        UsuarioRealmProxyInterface realmObjectTarget = (UsuarioRealmProxyInterface) realmObject;
        UsuarioRealmProxyInterface realmObjectSource = (UsuarioRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$nome(realmObjectSource.realmGet$nome());
        realmObjectTarget.realmSet$senha(realmObjectSource.realmGet$senha());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Usuario = proxy[");
        stringBuilder.append("{email:");
        stringBuilder.append(realmGet$email());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{nome:");
        stringBuilder.append(realmGet$nome());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{senha:");
        stringBuilder.append(realmGet$senha());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioRealmProxy aUsuario = (UsuarioRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aUsuario.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUsuario.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aUsuario.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
