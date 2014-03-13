package com.odobo.filestorage.rackspace

import com.bertramlabs.plugins.karman.KarmanConfigHolder
import com.bertramlabs.plugins.karman.StorageProvider
import com.bertramlabs.plugins.karman.exceptions.ProviderNotFoundException
import com.bertramlabs.plugins.karman.local.LocalStorageProvider
import spock.lang.IgnoreRest
import spock.lang.Specification

/**
 * TODO: write doc
 */
class RackspaceStorageProviderSpec extends Specification {

    def setupSpec() {
        StorageProvider.registerProvider(RackspaceStorageProvider)
    }

    def cleanupSpec() {
        KarmanConfigHolder.providerTypes = [:]
    }

    def "it can be created"() {
        when:
        StorageProvider storageProvider = StorageProvider.create(provider: 'rackspace', username: 'test', secretKey:'test', region:'uk')

        then:
        storageProvider
    }

    def "it checks the required arguments"() {
        when:
        StorageProvider.create(args)

        then:
        thrown(IllegalArgumentException)

        where:
        args << [
            [provider: 'rackspace'],
            [provider: 'rackspace', username: 'test'],
            [provider: 'rackspace', username: 'test', secretKey: 'test']
        ]
    }

    def "it checks the provider exists"() {
        when:
        StorageProvider.create(provider: 'foo', username: 'test', secretKey: 'test')

        then:
        thrown(ProviderNotFoundException)

    }
}
