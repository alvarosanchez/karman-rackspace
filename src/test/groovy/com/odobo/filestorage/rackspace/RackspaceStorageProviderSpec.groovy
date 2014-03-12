package com.odobo.filestorage.rackspace

import com.bertramlabs.plugins.karman.KarmanConfigHolder
import com.bertramlabs.plugins.karman.StorageProvider
import com.bertramlabs.plugins.karman.local.LocalStorageProvider
import spock.lang.Specification

/**
 * TODO: write doc
 */
class RackspaceStorageProviderSpec extends Specification {

    def "it can be created"() {
        given:
        KarmanConfigHolder.providerTypes << ["${RackspaceStorageProvider.name}": RackspaceStorageProvider]

        when:
        StorageProvider storageProvider = StorageProvider.create(provider: RackspaceStorageProvider.name, username: 'test', secretKey:'test', region:'uk')

        then:
        storageProvider
    }
}
