package com.odobo.filestorage.rackspace

import com.bertramlabs.plugins.karman.Directory
import com.bertramlabs.plugins.karman.KarmanConfigHolder
import com.bertramlabs.plugins.karman.StorageProvider
import com.bertramlabs.plugins.karman.exceptions.ProviderNotFoundException
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

    def "it list directories"() {
        given:
        StorageProvider storageProvider = StorageProvider.create(provider: 'rackspace', username: System.getenv('CF_USERNAME'), secretKey: System.getenv('CF_PASSWORD'), region:'uk')

        when:
        List<Directory> directories = storageProvider.directories

        then:
        directories.size()
    }

    def "it give access to a directory"() {
        given:
        StorageProvider storageProvider = StorageProvider.create(provider: 'rackspace', username: System.getenv('CF_USERNAME'), secretKey: System.getenv('CF_PASSWORD'), region:'uk')

        when:
        Directory directory = storageProvider.getDirectory('api-test')

        then:
        directory.name == 'api-test'
        directory.isDirectory()
        !directory.isFile()

    }
}
