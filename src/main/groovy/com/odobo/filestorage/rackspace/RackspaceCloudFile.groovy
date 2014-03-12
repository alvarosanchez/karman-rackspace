package com.odobo.filestorage.rackspace

import com.bertramlabs.plugins.karman.CloudFile
import org.jclouds.openstack.swift.CommonSwiftClient
import org.jclouds.openstack.swift.domain.MutableObjectInfoWithMetadata
import org.jclouds.openstack.swift.domain.ObjectInfo

/**
 * TODO: write doc
 */
class RackspaceCloudFile extends CloudFile {

    ObjectInfo objectInfo
    RackspaceDirectory parent

    private URL getUrl() {
        return objectInfo.uri.toURL()
    }

    @Override
    InputStream getInputStream() {
        url.openStream()
    }


    @Override
    String getText(String encoding) {
        url.getText(encoding)
    }

    @Override
    byte[] getBytes() {
        url.bytes
    }

    @Override
    void setText(String text) {
        throw new UnsupportedOperationException("Not yet implemented")
    }

    @Override
    void setBytes(Object bytes) {
        throw new UnsupportedOperationException("Not yet implemented")
    }

    @Override
    Long getContentLength() {
        objectInfo.bytes
    }

    @Override
    String getContentType() {
        objectInfo.contentType
    }

    @Override
    void setContentType(String contentType) {
        throw new UnsupportedOperationException("Not yet implemented")
    }

    @Override
    Boolean exists() {
        HttpURLConnection connection = url.openConnection()
        connection.responseCode != 404
    }

    @Override
    Object save(Object acl) {
        throw new UnsupportedOperationException("Not yet implemented")
    }

    @Override
    Object delete() {
        throw new UnsupportedOperationException("Not yet implemented")
    }

    @Override
    void setMetaAttribute(Object key, Object value) {
        throw new UnsupportedOperationException("Not yet implemented")
    }

    @Override
    Object getMetaAttribute(Object key) {
        metaAttributes.get(key)
    }

    @Override
    Map<String, String> getMetaAttributes() {
        if (objectInfo instanceof MutableObjectInfoWithMetadata) {
            objectInfo.metadata
        } else {
            return [:]
        }
    }

    @Override
    void removeMetaAttribute(Object key) {
        throw new UnsupportedOperationException("Not yet implemented")
    }

    private CommonSwiftClient getCloudFilesClient() {
        provider.cloudFilesClient
    }
}
