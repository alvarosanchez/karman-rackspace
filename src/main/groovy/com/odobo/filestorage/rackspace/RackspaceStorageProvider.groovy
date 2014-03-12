package com.odobo.filestorage.rackspace

import com.bertramlabs.plugins.karman.Directory
import com.bertramlabs.plugins.karman.StorageProvider
import org.jclouds.ContextBuilder
import org.jclouds.cloudfiles.CloudFilesClient

/**
 * TODO: write doc
 */
class RackspaceStorageProvider extends StorageProvider {

    static String name = "rackspace"

    String username
    String secretKey
    String region

    CloudFilesClient cloudFilesClient

    RackspaceStorageProvider(Map options) {
        this.username = options.username
        this.secretKey = options.secretKey
        this.region = "cloudfiles-${options.region}"

        this.cloudFilesClient = ContextBuilder.newBuilder(this.region)
                .credentials(this.username, this.secretKey)
                .buildApi(CloudFilesClient)
    }

    @Override
    Directory getDirectory(String name) {
        new RackspaceDirectory(name: name, provider: this)
    }

    @Override
    Object getDirectories() {
        cloudFilesClient.listContainers(null).collect {
            new RackspaceDirectory(name: it.name, provider: this)
        }
    }
}
