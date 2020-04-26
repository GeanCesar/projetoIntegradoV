package io.realm;


import android.util.JsonReader;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>(3);
        modelClasses.add(com.example.projetointegrado.modelos.Reservas.class);
        modelClasses.add(com.example.projetointegrado.modelos.Usuario.class);
        modelClasses.add(com.example.projetointegrado.modelos.Sala.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Map<Class<? extends RealmModel>, OsObjectSchemaInfo> getExpectedObjectSchemaInfoMap() {
        Map<Class<? extends RealmModel>, OsObjectSchemaInfo> infoMap = new HashMap<Class<? extends RealmModel>, OsObjectSchemaInfo>(3);
        infoMap.put(com.example.projetointegrado.modelos.Reservas.class, io.realm.ReservasRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.example.projetointegrado.modelos.Usuario.class, io.realm.UsuarioRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.example.projetointegrado.modelos.Sala.class, io.realm.SalaRealmProxy.getExpectedObjectSchemaInfo());
        return infoMap;
    }

    @Override
    public ColumnInfo createColumnInfo(Class<? extends RealmModel> clazz, OsSchemaInfo schemaInfo) {
        checkClass(clazz);

        if (clazz.equals(com.example.projetointegrado.modelos.Reservas.class)) {
            return io.realm.ReservasRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.example.projetointegrado.modelos.Usuario.class)) {
            return io.realm.UsuarioRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.example.projetointegrado.modelos.Sala.class)) {
            return io.realm.SalaRealmProxy.createColumnInfo(schemaInfo);
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.example.projetointegrado.modelos.Reservas.class)) {
            return io.realm.ReservasRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.example.projetointegrado.modelos.Usuario.class)) {
            return io.realm.UsuarioRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.example.projetointegrado.modelos.Sala.class)) {
            return io.realm.SalaRealmProxy.getFieldNames();
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public String getSimpleClassNameImpl(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.example.projetointegrado.modelos.Reservas.class)) {
            return io.realm.ReservasRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.example.projetointegrado.modelos.Usuario.class)) {
            return io.realm.UsuarioRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.example.projetointegrado.modelos.Sala.class)) {
            return io.realm.SalaRealmProxy.getSimpleClassName();
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        try {
            objectContext.set((BaseRealm) baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
            checkClass(clazz);

            if (clazz.equals(com.example.projetointegrado.modelos.Reservas.class)) {
                return clazz.cast(new io.realm.ReservasRealmProxy());
            }
            if (clazz.equals(com.example.projetointegrado.modelos.Usuario.class)) {
                return clazz.cast(new io.realm.UsuarioRealmProxy());
            }
            if (clazz.equals(com.example.projetointegrado.modelos.Sala.class)) {
                return clazz.cast(new io.realm.SalaRealmProxy());
            }
            throw getMissingProxyClassException(clazz);
        } finally {
            objectContext.clear();
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.example.projetointegrado.modelos.Reservas.class)) {
            return clazz.cast(io.realm.ReservasRealmProxy.copyOrUpdate(realm, (com.example.projetointegrado.modelos.Reservas) obj, update, cache));
        }
        if (clazz.equals(com.example.projetointegrado.modelos.Usuario.class)) {
            return clazz.cast(io.realm.UsuarioRealmProxy.copyOrUpdate(realm, (com.example.projetointegrado.modelos.Usuario) obj, update, cache));
        }
        if (clazz.equals(com.example.projetointegrado.modelos.Sala.class)) {
            return clazz.cast(io.realm.SalaRealmProxy.copyOrUpdate(realm, (com.example.projetointegrado.modelos.Sala) obj, update, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public void insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

        if (clazz.equals(com.example.projetointegrado.modelos.Reservas.class)) {
            io.realm.ReservasRealmProxy.insert(realm, (com.example.projetointegrado.modelos.Reservas) object, cache);
        } else if (clazz.equals(com.example.projetointegrado.modelos.Usuario.class)) {
            io.realm.UsuarioRealmProxy.insert(realm, (com.example.projetointegrado.modelos.Usuario) object, cache);
        } else if (clazz.equals(com.example.projetointegrado.modelos.Sala.class)) {
            io.realm.SalaRealmProxy.insert(realm, (com.example.projetointegrado.modelos.Sala) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.example.projetointegrado.modelos.Reservas.class)) {
                io.realm.ReservasRealmProxy.insert(realm, (com.example.projetointegrado.modelos.Reservas) object, cache);
            } else if (clazz.equals(com.example.projetointegrado.modelos.Usuario.class)) {
                io.realm.UsuarioRealmProxy.insert(realm, (com.example.projetointegrado.modelos.Usuario) object, cache);
            } else if (clazz.equals(com.example.projetointegrado.modelos.Sala.class)) {
                io.realm.SalaRealmProxy.insert(realm, (com.example.projetointegrado.modelos.Sala) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.example.projetointegrado.modelos.Reservas.class)) {
                    io.realm.ReservasRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.example.projetointegrado.modelos.Usuario.class)) {
                    io.realm.UsuarioRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.example.projetointegrado.modelos.Sala.class)) {
                    io.realm.SalaRealmProxy.insert(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.example.projetointegrado.modelos.Reservas.class)) {
            io.realm.ReservasRealmProxy.insertOrUpdate(realm, (com.example.projetointegrado.modelos.Reservas) obj, cache);
        } else if (clazz.equals(com.example.projetointegrado.modelos.Usuario.class)) {
            io.realm.UsuarioRealmProxy.insertOrUpdate(realm, (com.example.projetointegrado.modelos.Usuario) obj, cache);
        } else if (clazz.equals(com.example.projetointegrado.modelos.Sala.class)) {
            io.realm.SalaRealmProxy.insertOrUpdate(realm, (com.example.projetointegrado.modelos.Sala) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.example.projetointegrado.modelos.Reservas.class)) {
                io.realm.ReservasRealmProxy.insertOrUpdate(realm, (com.example.projetointegrado.modelos.Reservas) object, cache);
            } else if (clazz.equals(com.example.projetointegrado.modelos.Usuario.class)) {
                io.realm.UsuarioRealmProxy.insertOrUpdate(realm, (com.example.projetointegrado.modelos.Usuario) object, cache);
            } else if (clazz.equals(com.example.projetointegrado.modelos.Sala.class)) {
                io.realm.SalaRealmProxy.insertOrUpdate(realm, (com.example.projetointegrado.modelos.Sala) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.example.projetointegrado.modelos.Reservas.class)) {
                    io.realm.ReservasRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.example.projetointegrado.modelos.Usuario.class)) {
                    io.realm.UsuarioRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.example.projetointegrado.modelos.Sala.class)) {
                    io.realm.SalaRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(com.example.projetointegrado.modelos.Reservas.class)) {
            return clazz.cast(io.realm.ReservasRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.example.projetointegrado.modelos.Usuario.class)) {
            return clazz.cast(io.realm.UsuarioRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.example.projetointegrado.modelos.Sala.class)) {
            return clazz.cast(io.realm.SalaRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(com.example.projetointegrado.modelos.Reservas.class)) {
            return clazz.cast(io.realm.ReservasRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.example.projetointegrado.modelos.Usuario.class)) {
            return clazz.cast(io.realm.UsuarioRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.example.projetointegrado.modelos.Sala.class)) {
            return clazz.cast(io.realm.SalaRealmProxy.createUsingJsonStream(realm, reader));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(com.example.projetointegrado.modelos.Reservas.class)) {
            return clazz.cast(io.realm.ReservasRealmProxy.createDetachedCopy((com.example.projetointegrado.modelos.Reservas) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.example.projetointegrado.modelos.Usuario.class)) {
            return clazz.cast(io.realm.UsuarioRealmProxy.createDetachedCopy((com.example.projetointegrado.modelos.Usuario) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.example.projetointegrado.modelos.Sala.class)) {
            return clazz.cast(io.realm.SalaRealmProxy.createDetachedCopy((com.example.projetointegrado.modelos.Sala) realmObject, 0, maxDepth, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

}
