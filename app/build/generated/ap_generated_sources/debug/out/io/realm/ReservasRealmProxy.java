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
public class ReservasRealmProxy extends com.example.projetointegrado.modelos.Reservas
    implements RealmObjectProxy, ReservasRealmProxyInterface {

    static final class ReservasColumnInfo extends ColumnInfo {
        long usuarioIndex;
        long dataIndex;
        long salaIndex;
        long statusIndex;

        ReservasColumnInfo(OsSchemaInfo schemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Reservas");
            this.usuarioIndex = addColumnDetails("usuario", objectSchemaInfo);
            this.dataIndex = addColumnDetails("data", objectSchemaInfo);
            this.salaIndex = addColumnDetails("sala", objectSchemaInfo);
            this.statusIndex = addColumnDetails("status", objectSchemaInfo);
        }

        ReservasColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new ReservasColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final ReservasColumnInfo src = (ReservasColumnInfo) rawSrc;
            final ReservasColumnInfo dst = (ReservasColumnInfo) rawDst;
            dst.usuarioIndex = src.usuarioIndex;
            dst.dataIndex = src.dataIndex;
            dst.salaIndex = src.salaIndex;
            dst.statusIndex = src.statusIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(4);
        fieldNames.add("usuario");
        fieldNames.add("data");
        fieldNames.add("sala");
        fieldNames.add("status");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private ReservasColumnInfo columnInfo;
    private ProxyState<com.example.projetointegrado.modelos.Reservas> proxyState;

    ReservasRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (ReservasColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.projetointegrado.modelos.Reservas>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    public com.example.projetointegrado.modelos.Usuario realmGet$usuario() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNullLink(columnInfo.usuarioIndex)) {
            return null;
        }
        return proxyState.getRealm$realm().get(com.example.projetointegrado.modelos.Usuario.class, proxyState.getRow$realm().getLink(columnInfo.usuarioIndex), false, Collections.<String>emptyList());
    }

    @Override
    public void realmSet$usuario(com.example.projetointegrado.modelos.Usuario value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("usuario")) {
                return;
            }
            if (value != null && !RealmObject.isManaged(value)) {
                value = ((Realm) proxyState.getRealm$realm()).copyToRealm(value);
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                // Table#nullifyLink() does not support default value. Just using Row.
                row.nullifyLink(columnInfo.usuarioIndex);
                return;
            }
            proxyState.checkValidObject(value);
            row.getTable().setLink(columnInfo.usuarioIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().nullifyLink(columnInfo.usuarioIndex);
            return;
        }
        proxyState.checkValidObject(value);
        proxyState.getRow$realm().setLink(columnInfo.usuarioIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
    }

    @Override
    @SuppressWarnings("cast")
    public Date realmGet$data() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.util.Date) proxyState.getRow$realm().getDate(columnInfo.dataIndex);
    }

    @Override
    public void realmSet$data(Date value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'data' to null.");
            }
            row.getTable().setDate(columnInfo.dataIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'data' to null.");
        }
        proxyState.getRow$realm().setDate(columnInfo.dataIndex, value);
    }

    @Override
    public com.example.projetointegrado.modelos.Sala realmGet$sala() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNullLink(columnInfo.salaIndex)) {
            return null;
        }
        return proxyState.getRealm$realm().get(com.example.projetointegrado.modelos.Sala.class, proxyState.getRow$realm().getLink(columnInfo.salaIndex), false, Collections.<String>emptyList());
    }

    @Override
    public void realmSet$sala(com.example.projetointegrado.modelos.Sala value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("sala")) {
                return;
            }
            if (value != null && !RealmObject.isManaged(value)) {
                value = ((Realm) proxyState.getRealm$realm()).copyToRealm(value);
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                // Table#nullifyLink() does not support default value. Just using Row.
                row.nullifyLink(columnInfo.salaIndex);
                return;
            }
            proxyState.checkValidObject(value);
            row.getTable().setLink(columnInfo.salaIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().nullifyLink(columnInfo.salaIndex);
            return;
        }
        proxyState.checkValidObject(value);
        proxyState.getRow$realm().setLink(columnInfo.salaIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$status() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.statusIndex);
    }

    @Override
    public void realmSet$status(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.statusIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.statusIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Reservas", 4, 0);
        builder.addPersistedLinkProperty("usuario", RealmFieldType.OBJECT, "Usuario");
        builder.addPersistedProperty("data", RealmFieldType.DATE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedLinkProperty("sala", RealmFieldType.OBJECT, "Sala");
        builder.addPersistedProperty("status", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ReservasColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ReservasColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Reservas";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.example.projetointegrado.modelos.Reservas createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(2);
        if (json.has("usuario")) {
            excludeFields.add("usuario");
        }
        if (json.has("sala")) {
            excludeFields.add("sala");
        }
        com.example.projetointegrado.modelos.Reservas obj = realm.createObjectInternal(com.example.projetointegrado.modelos.Reservas.class, true, excludeFields);

        final ReservasRealmProxyInterface objProxy = (ReservasRealmProxyInterface) obj;
        if (json.has("usuario")) {
            if (json.isNull("usuario")) {
                objProxy.realmSet$usuario(null);
            } else {
                com.example.projetointegrado.modelos.Usuario usuarioObj = UsuarioRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("usuario"), update);
                objProxy.realmSet$usuario(usuarioObj);
            }
        }
        if (json.has("data")) {
            if (json.isNull("data")) {
                objProxy.realmSet$data(null);
            } else {
                Object timestamp = json.get("data");
                if (timestamp instanceof String) {
                    objProxy.realmSet$data(JsonUtils.stringToDate((String) timestamp));
                } else {
                    objProxy.realmSet$data(new Date(json.getLong("data")));
                }
            }
        }
        if (json.has("sala")) {
            if (json.isNull("sala")) {
                objProxy.realmSet$sala(null);
            } else {
                com.example.projetointegrado.modelos.Sala salaObj = SalaRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("sala"), update);
                objProxy.realmSet$sala(salaObj);
            }
        }
        if (json.has("status")) {
            if (json.isNull("status")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
            } else {
                objProxy.realmSet$status((int) json.getInt("status"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.projetointegrado.modelos.Reservas createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.example.projetointegrado.modelos.Reservas obj = new com.example.projetointegrado.modelos.Reservas();
        final ReservasRealmProxyInterface objProxy = (ReservasRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("usuario")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$usuario(null);
                } else {
                    com.example.projetointegrado.modelos.Usuario usuarioObj = UsuarioRealmProxy.createUsingJsonStream(realm, reader);
                    objProxy.realmSet$usuario(usuarioObj);
                }
            } else if (name.equals("data")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$data(null);
                } else if (reader.peek() == JsonToken.NUMBER) {
                    long timestamp = reader.nextLong();
                    if (timestamp > -1) {
                        objProxy.realmSet$data(new Date(timestamp));
                    }
                } else {
                    objProxy.realmSet$data(JsonUtils.stringToDate(reader.nextString()));
                }
            } else if (name.equals("sala")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$sala(null);
                } else {
                    com.example.projetointegrado.modelos.Sala salaObj = SalaRealmProxy.createUsingJsonStream(realm, reader);
                    objProxy.realmSet$sala(salaObj);
                }
            } else if (name.equals("status")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$status((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    public static com.example.projetointegrado.modelos.Reservas copyOrUpdate(Realm realm, com.example.projetointegrado.modelos.Reservas object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
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
            return (com.example.projetointegrado.modelos.Reservas) cachedRealmObject;
        }

        return copy(realm, object, update, cache);
    }

    public static com.example.projetointegrado.modelos.Reservas copy(Realm realm, com.example.projetointegrado.modelos.Reservas newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.projetointegrado.modelos.Reservas) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.example.projetointegrado.modelos.Reservas realmObject = realm.createObjectInternal(com.example.projetointegrado.modelos.Reservas.class, false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        ReservasRealmProxyInterface realmObjectSource = (ReservasRealmProxyInterface) newObject;
        ReservasRealmProxyInterface realmObjectCopy = (ReservasRealmProxyInterface) realmObject;


        com.example.projetointegrado.modelos.Usuario usuarioObj = realmObjectSource.realmGet$usuario();
        if (usuarioObj == null) {
            realmObjectCopy.realmSet$usuario(null);
        } else {
            com.example.projetointegrado.modelos.Usuario cacheusuario = (com.example.projetointegrado.modelos.Usuario) cache.get(usuarioObj);
            if (cacheusuario != null) {
                realmObjectCopy.realmSet$usuario(cacheusuario);
            } else {
                realmObjectCopy.realmSet$usuario(UsuarioRealmProxy.copyOrUpdate(realm, usuarioObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$data(realmObjectSource.realmGet$data());

        com.example.projetointegrado.modelos.Sala salaObj = realmObjectSource.realmGet$sala();
        if (salaObj == null) {
            realmObjectCopy.realmSet$sala(null);
        } else {
            com.example.projetointegrado.modelos.Sala cachesala = (com.example.projetointegrado.modelos.Sala) cache.get(salaObj);
            if (cachesala != null) {
                realmObjectCopy.realmSet$sala(cachesala);
            } else {
                realmObjectCopy.realmSet$sala(SalaRealmProxy.copyOrUpdate(realm, salaObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$status(realmObjectSource.realmGet$status());
        return realmObject;
    }

    public static long insert(Realm realm, com.example.projetointegrado.modelos.Reservas object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.projetointegrado.modelos.Reservas.class);
        long tableNativePtr = table.getNativePtr();
        ReservasColumnInfo columnInfo = (ReservasColumnInfo) realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Reservas.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);

        com.example.projetointegrado.modelos.Usuario usuarioObj = ((ReservasRealmProxyInterface) object).realmGet$usuario();
        if (usuarioObj != null) {
            Long cacheusuario = cache.get(usuarioObj);
            if (cacheusuario == null) {
                cacheusuario = UsuarioRealmProxy.insert(realm, usuarioObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.usuarioIndex, rowIndex, cacheusuario, false);
        }
        java.util.Date realmGet$data = ((ReservasRealmProxyInterface) object).realmGet$data();
        if (realmGet$data != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.dataIndex, rowIndex, realmGet$data.getTime(), false);
        }

        com.example.projetointegrado.modelos.Sala salaObj = ((ReservasRealmProxyInterface) object).realmGet$sala();
        if (salaObj != null) {
            Long cachesala = cache.get(salaObj);
            if (cachesala == null) {
                cachesala = SalaRealmProxy.insert(realm, salaObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.salaIndex, rowIndex, cachesala, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((ReservasRealmProxyInterface) object).realmGet$status(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.projetointegrado.modelos.Reservas.class);
        long tableNativePtr = table.getNativePtr();
        ReservasColumnInfo columnInfo = (ReservasColumnInfo) realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Reservas.class);
        com.example.projetointegrado.modelos.Reservas object = null;
        while (objects.hasNext()) {
            object = (com.example.projetointegrado.modelos.Reservas) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);

            com.example.projetointegrado.modelos.Usuario usuarioObj = ((ReservasRealmProxyInterface) object).realmGet$usuario();
            if (usuarioObj != null) {
                Long cacheusuario = cache.get(usuarioObj);
                if (cacheusuario == null) {
                    cacheusuario = UsuarioRealmProxy.insert(realm, usuarioObj, cache);
                }
                table.setLink(columnInfo.usuarioIndex, rowIndex, cacheusuario, false);
            }
            java.util.Date realmGet$data = ((ReservasRealmProxyInterface) object).realmGet$data();
            if (realmGet$data != null) {
                Table.nativeSetTimestamp(tableNativePtr, columnInfo.dataIndex, rowIndex, realmGet$data.getTime(), false);
            }

            com.example.projetointegrado.modelos.Sala salaObj = ((ReservasRealmProxyInterface) object).realmGet$sala();
            if (salaObj != null) {
                Long cachesala = cache.get(salaObj);
                if (cachesala == null) {
                    cachesala = SalaRealmProxy.insert(realm, salaObj, cache);
                }
                table.setLink(columnInfo.salaIndex, rowIndex, cachesala, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((ReservasRealmProxyInterface) object).realmGet$status(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.projetointegrado.modelos.Reservas object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.projetointegrado.modelos.Reservas.class);
        long tableNativePtr = table.getNativePtr();
        ReservasColumnInfo columnInfo = (ReservasColumnInfo) realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Reservas.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);

        com.example.projetointegrado.modelos.Usuario usuarioObj = ((ReservasRealmProxyInterface) object).realmGet$usuario();
        if (usuarioObj != null) {
            Long cacheusuario = cache.get(usuarioObj);
            if (cacheusuario == null) {
                cacheusuario = UsuarioRealmProxy.insertOrUpdate(realm, usuarioObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.usuarioIndex, rowIndex, cacheusuario, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.usuarioIndex, rowIndex);
        }
        java.util.Date realmGet$data = ((ReservasRealmProxyInterface) object).realmGet$data();
        if (realmGet$data != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.dataIndex, rowIndex, realmGet$data.getTime(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dataIndex, rowIndex, false);
        }

        com.example.projetointegrado.modelos.Sala salaObj = ((ReservasRealmProxyInterface) object).realmGet$sala();
        if (salaObj != null) {
            Long cachesala = cache.get(salaObj);
            if (cachesala == null) {
                cachesala = SalaRealmProxy.insertOrUpdate(realm, salaObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.salaIndex, rowIndex, cachesala, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.salaIndex, rowIndex);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((ReservasRealmProxyInterface) object).realmGet$status(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.projetointegrado.modelos.Reservas.class);
        long tableNativePtr = table.getNativePtr();
        ReservasColumnInfo columnInfo = (ReservasColumnInfo) realm.getSchema().getColumnInfo(com.example.projetointegrado.modelos.Reservas.class);
        com.example.projetointegrado.modelos.Reservas object = null;
        while (objects.hasNext()) {
            object = (com.example.projetointegrado.modelos.Reservas) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);

            com.example.projetointegrado.modelos.Usuario usuarioObj = ((ReservasRealmProxyInterface) object).realmGet$usuario();
            if (usuarioObj != null) {
                Long cacheusuario = cache.get(usuarioObj);
                if (cacheusuario == null) {
                    cacheusuario = UsuarioRealmProxy.insertOrUpdate(realm, usuarioObj, cache);
                }
                Table.nativeSetLink(tableNativePtr, columnInfo.usuarioIndex, rowIndex, cacheusuario, false);
            } else {
                Table.nativeNullifyLink(tableNativePtr, columnInfo.usuarioIndex, rowIndex);
            }
            java.util.Date realmGet$data = ((ReservasRealmProxyInterface) object).realmGet$data();
            if (realmGet$data != null) {
                Table.nativeSetTimestamp(tableNativePtr, columnInfo.dataIndex, rowIndex, realmGet$data.getTime(), false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.dataIndex, rowIndex, false);
            }

            com.example.projetointegrado.modelos.Sala salaObj = ((ReservasRealmProxyInterface) object).realmGet$sala();
            if (salaObj != null) {
                Long cachesala = cache.get(salaObj);
                if (cachesala == null) {
                    cachesala = SalaRealmProxy.insertOrUpdate(realm, salaObj, cache);
                }
                Table.nativeSetLink(tableNativePtr, columnInfo.salaIndex, rowIndex, cachesala, false);
            } else {
                Table.nativeNullifyLink(tableNativePtr, columnInfo.salaIndex, rowIndex);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((ReservasRealmProxyInterface) object).realmGet$status(), false);
        }
    }

    public static com.example.projetointegrado.modelos.Reservas createDetachedCopy(com.example.projetointegrado.modelos.Reservas realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.projetointegrado.modelos.Reservas unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.projetointegrado.modelos.Reservas();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.projetointegrado.modelos.Reservas) cachedObject.object;
            }
            unmanagedObject = (com.example.projetointegrado.modelos.Reservas) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        ReservasRealmProxyInterface unmanagedCopy = (ReservasRealmProxyInterface) unmanagedObject;
        ReservasRealmProxyInterface realmSource = (ReservasRealmProxyInterface) realmObject;

        // Deep copy of usuario
        unmanagedCopy.realmSet$usuario(UsuarioRealmProxy.createDetachedCopy(realmSource.realmGet$usuario(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$data(realmSource.realmGet$data());

        // Deep copy of sala
        unmanagedCopy.realmSet$sala(SalaRealmProxy.createDetachedCopy(realmSource.realmGet$sala(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$status(realmSource.realmGet$status());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Reservas = proxy[");
        stringBuilder.append("{usuario:");
        stringBuilder.append(realmGet$usuario() != null ? "Usuario" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{data:");
        stringBuilder.append(realmGet$data());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{sala:");
        stringBuilder.append(realmGet$sala() != null ? "Sala" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{status:");
        stringBuilder.append(realmGet$status());
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
        ReservasRealmProxy aReservas = (ReservasRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aReservas.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aReservas.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aReservas.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
