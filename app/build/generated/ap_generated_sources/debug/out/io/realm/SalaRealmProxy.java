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
public class SalaRealmProxy extends com.example.projetointegrado.modelos.Sala
    implements RealmObjectProxy, SalaRealmProxyInterface {

    static final class SalaColumnInfo extends ColumnInfo {
        long nSalaIndex;
        long laboratorioIndex;
        long projetorIndex;

        SalaColumnInfo(OsSchemaInfo schemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Sala");
            this.nSalaIndex = addColumnDetails("nSala", objectSchemaInfo);
            this.laboratorioIndex = addColumnDetails("laboratorio", objectSchemaInfo);
            this.projetorIndex = addColumnDetails("projetor", objectSchemaInfo);
        }

        SalaColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new SalaColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final SalaColumnInfo src = (SalaColumnInfo) rawSrc;
            final SalaColumnInfo dst = (SalaColumnInfo) rawDst;
            dst.nSalaIndex = src.nSalaIndex;
            dst.laboratorioIndex = src.laboratorioIndex;
            dst.projetorIndex = src.projetorIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(3);
        fieldNames.add("nSala");
        fieldNames.add("laboratorio");
        fieldNames.add("projetor");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private SalaColumnInfo columnInfo;
    private ProxyState<com.example.projetointegrado.modelos.Sala> proxyState;

    SalaRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (SalaColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.projetointegrado.modelos.Sala>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$nSala() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.nSalaIndex);
    }

    @Override
    public void realmSet$nSala(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.nSalaIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.nSalaIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public boolean realmGet$laboratorio() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.laboratorioIndex);
    }

    @Override
    public void realmSet$laboratorio(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.laboratorioIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.laboratorioIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public boolean realmGet$projetor() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.projetorIndex);
    }

    @Override
    public void realmSet$projetor(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.projetorIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.projetorIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Sala", 3, 0);
        builder.addPersistedProperty("nSala", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("laboratorio", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("projetor", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static SalaColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new SalaColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Sala";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.example.projetointegrado.modelos.Sala createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.projetointegrado.modelos.Sala obj = realm.createObjectInternal(com.example.projetointegrado.modelos.Sala.class, true, excludeFields);

        final SalaRealmProxyInterface objProxy = (SalaRealmProxyInterface) obj;
        if (json.has("nSala")) {
            if (json.isNull("nSala")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'nSala' to null.");
            } else {
                objProxy.realmSet$nSala((int) json.getInt("nSala"));
            }
        }
        if (json.has("laboratorio")) {
            if (json.isNull("laboratorio")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'laboratorio' to null.");
            } else {
                objProxy.realmSet$laboratorio((boolean) json.getBoolean("laboratorio"));
            }
        }
        if (json.has("projetor")) {
            if (json.isNull("projetor")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'projetor' to null.");
            } else {
                objProxy.realmSet$projetor((boolean) json.getBoolean("projetor"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.projetointegrado.modelos.Sala createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.example.projetointegrado.modelos.Sala obj = new com.example.projetointegrado.modelos.Sala();
        final SalaRealmProxyInterface objProxy = (SalaRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("nSala")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$nSala((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'nSala' to null.");
                }
            } else if (name.equals("laboratorio")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$laboratorio((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'laboratorio' to null.");
                }
            } else if (name.equals("projetor")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$projetor((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'projetor' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    public static com.example.projetointegrado.modelos.Sala copyOrUpdate(Realm realm, com.example.projetointegrado.modelos.Sala object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
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
            return (com.example.projetointegrado.modelos.Sala) cachedRealmObject;
        }

        return copy(realm, object, update, cache);
    }

    public static com.example.projetointegrado.modelos.Sala copy(Realm realm, com.example.projetointegrado.modelos.Sala newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.projetointegrado.modelos.Sala) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.example.projetointegrado.modelos.Sala realmObject = realm.createObjectInternal(com.example.projetointegrado.modelos.Sala.class, false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        SalaRealmProxyInterface realmObjectSource = (SalaRealmProxyInterface) newObject;
        SalaRealmProxyInterface realmObjectCopy = (SalaRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$nSala(realmObjectSource.realmGet$nSala());
        realmObjectCopy.realmSet$laboratorio(realmObjectSource.realmGet$laboratorio());
        realmObjectCopy.realmSet$projetor(realmObjectSource.realmGet$projetor());
        return realmObject;
    }

    public static long insert(Realm realm, com.example.projetointegrado.modelos.Sala object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.projetointegrado.modelos.Sala.class);
        long tableNativePtr = table.getNativePtr();
        SalaColumnInfo columnInfo = (SalaColumnInfo) realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Sala.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.nSalaIndex, rowIndex, ((SalaRealmProxyInterface) object).realmGet$nSala(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.laboratorioIndex, rowIndex, ((SalaRealmProxyInterface) object).realmGet$laboratorio(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.projetorIndex, rowIndex, ((SalaRealmProxyInterface) object).realmGet$projetor(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.projetointegrado.modelos.Sala.class);
        long tableNativePtr = table.getNativePtr();
        SalaColumnInfo columnInfo = (SalaColumnInfo) realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Sala.class);
        com.example.projetointegrado.modelos.Sala object = null;
        while (objects.hasNext()) {
            object = (com.example.projetointegrado.modelos.Sala) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.nSalaIndex, rowIndex, ((SalaRealmProxyInterface) object).realmGet$nSala(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.laboratorioIndex, rowIndex, ((SalaRealmProxyInterface) object).realmGet$laboratorio(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.projetorIndex, rowIndex, ((SalaRealmProxyInterface) object).realmGet$projetor(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.projetointegrado.modelos.Sala object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.projetointegrado.modelos.Sala.class);
        long tableNativePtr = table.getNativePtr();
        SalaColumnInfo columnInfo = (SalaColumnInfo) realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Sala.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.nSalaIndex, rowIndex, ((SalaRealmProxyInterface) object).realmGet$nSala(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.laboratorioIndex, rowIndex, ((SalaRealmProxyInterface) object).realmGet$laboratorio(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.projetorIndex, rowIndex, ((SalaRealmProxyInterface) object).realmGet$projetor(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.projetointegrado.modelos.Sala.class);
        long tableNativePtr = table.getNativePtr();
        SalaColumnInfo columnInfo = (SalaColumnInfo) realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Sala.class);
        com.example.projetointegrado.modelos.Sala object = null;
        while (objects.hasNext()) {
            object = (com.example.projetointegrado.modelos.Sala) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.nSalaIndex, rowIndex, ((SalaRealmProxyInterface) object).realmGet$nSala(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.laboratorioIndex, rowIndex, ((SalaRealmProxyInterface) object).realmGet$laboratorio(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.projetorIndex, rowIndex, ((SalaRealmProxyInterface) object).realmGet$projetor(), false);
        }
    }

    public static com.example.projetointegrado.modelos.Sala createDetachedCopy(com.example.projetointegrado.modelos.Sala realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.projetointegrado.modelos.Sala unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.projetointegrado.modelos.Sala();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.projetointegrado.modelos.Sala) cachedObject.object;
            }
            unmanagedObject = (com.example.projetointegrado.modelos.Sala) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        SalaRealmProxyInterface unmanagedCopy = (SalaRealmProxyInterface) unmanagedObject;
        SalaRealmProxyInterface realmSource = (SalaRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$nSala(realmSource.realmGet$nSala());
        unmanagedCopy.realmSet$laboratorio(realmSource.realmGet$laboratorio());
        unmanagedCopy.realmSet$projetor(realmSource.realmGet$projetor());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Sala = proxy[");
        stringBuilder.append("{nSala:");
        stringBuilder.append(realmGet$nSala());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{laboratorio:");
        stringBuilder.append(realmGet$laboratorio());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{projetor:");
        stringBuilder.append(realmGet$projetor());
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
        SalaRealmProxy aSala = (SalaRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aSala.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aSala.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aSala.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
