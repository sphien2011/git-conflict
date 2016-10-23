package com.example.sphien2011.datarealmcontact.common.service;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

/**
 * Created by sphien2011 on 21/10/2016.
 */
public class Migration implements RealmMigration {
    private static Realm mRealm;
    private static final long REALM_SCHEMA_VERSION = 1;

    public Migration() {
    }

    public static Realm getRealm() {
        if (mRealm == null) {

            RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                    .schemaVersion(REALM_SCHEMA_VERSION)
                    .migration(new Migration())
                    .build();

            mRealm = Realm.getInstance(realmConfiguration);

        }
        return mRealm;
    }

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

    }
}
