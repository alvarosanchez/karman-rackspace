package com.odobo.filestorage.rackspace

import com.bertramlabs.plugins.karman.CloudFile
import com.bertramlabs.plugins.karman.Directory
import org.jclouds.cloudfiles.CloudFilesClient
import org.jclouds.openstack.swift.CommonSwiftClient
import org.jclouds.openstack.swift.domain.ContainerMetadata

/**
 * TODO: write doc
 */
class RackspaceDirectory extends Directory {

    @Override
    Boolean exists() {
        cloudFilesClient.containerExists(name)
    }

    @Override
    List listFiles(Object options) {
        cloudFilesClient.listObjects(name, null).collect {
            new RackspaceCloudFile(objectInfo: it, parent: this)
        }
    }

    @Override
    CloudFile getFile(String fileName) {
        new RackspaceCloudFile(objectInfo: cloudFilesClient.getObjectInfo(this.name, fileName), parent: this)
    }

    @Override
    Object save() {
        throw new UnsupportedOperationException("Not yet implemented")
    }

    private CloudFilesClient getCloudFilesClient() {
        provider.cloudFilesClient
    }
}
