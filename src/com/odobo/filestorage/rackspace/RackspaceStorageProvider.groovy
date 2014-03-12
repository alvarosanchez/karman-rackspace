package com.odobo.filestorage.rackspace

import com.bertramlabs.plugins.karman.Directory
import com.bertramlabs.plugins.karman.StorageProvider
import org.jclouds.ContextBuilder
import org.jclouds.blobstore.BlobStore
import org.jclouds.blobstore.BlobStoreContext
import org.jclouds.cloudfiles.CloudFilesClient
import org.jclouds.openstack.swift.CommonSwiftAsyncClient
import org.jclouds.openstack.swift.CommonSwiftClient
import org.jclouds.rest.RestContext

/**
 * TODO: write doc
 */
class RackspaceStorageProvider extends StorageProvider {

    static String name = "Rackspace Cloud Files"

    String accessKey
    String secretKey
    String region

    CloudFilesClient cloudFilesClient

    RackspaceStorageProvider(String accessKey, String secretKey, String region) {
        this.accessKey = accessKey
        this.secretKey = secretKey
        this.region = region

        this.cloudFilesClient = ContextBuilder.newBuilder(region)
                .credentials(accessKey, secretKey)
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
