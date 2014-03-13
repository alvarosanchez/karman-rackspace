package com.odobo.filestorage.rackspace

import com.bertramlabs.plugins.karman.KarmanConfigHolder
import com.bertramlabs.plugins.karman.StorageProvider
import com.bertramlabs.plugins.karman.local.LocalStorageProvider
import spock.lang.Specification

/**
 * TODO: write doc
 */
class RackspaceStorageProviderSpec extends Specification {

    def setupSpec() {
        StorageProvider.registerProvider(RackspaceStorageProvider)
    }

    def "it can be created"() {
        when:
        StorageProvider storageProvider = StorageProvider.create(provider: 'rackspace', username: 'test', secretKey:'test', region:'uk')

        then:
        storageProvider
    }

}
