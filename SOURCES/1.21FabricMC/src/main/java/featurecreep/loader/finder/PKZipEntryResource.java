/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package featurecreep.loader.finder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;

import org.jboss.modules.Resource;

/**
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public class PKZipEntryResource implements Resource {
    private final URL jarFileURL;
    private final String relativePath;
    private final String entryName;

    /**
     * Constructs a new PKZipEntryResource.
     *
     * @param jarFileURL   the URL of the JAR file
     * @param name         the name of the resource
     * @param relativePath the relative path inside the JAR
     */
    public PKZipEntryResource(final URL jarFileURL, final String name, final String relativePath) {
        this.jarFileURL = jarFileURL;
        this.relativePath = relativePath;
        this.entryName = relativePath == null ? name : name.substring(relativePath.length() + 1);
    }

    @Override
    public String getName() {
        return entryName;
    }

    @Override
    public URL getURL() {
        return jarFileURL;
    }

    public JarEntry getEntry() {
    	 JarInputStream jarInputStream = null;
         try {
             jarInputStream = createJarInputStream();
             JarEntry entry;
             String fullEntryName = getFullEntryName();

             while ((entry = jarInputStream.getNextJarEntry()) != null) {
                 if (entry.getName().equals(fullEntryName)) {
                     return entry;
                 }
             }

         } catch(Exception e){
         	e.printStackTrace();
         }
         return null;
    }
    
    
    @Override
    public InputStream openStream() throws IOException {
        JarInputStream jarInputStream = null;
        try {
            jarInputStream = createJarInputStream();
            ZipEntry entry;
            String fullEntryName = getFullEntryName();

            while ((entry = jarInputStream.getNextEntry()) != null) {
                if (entry.getName().equals(fullEntryName)) {
                    // Return a wrapper InputStream that closes the JarInputStream when done
                    return new JarEntryInputStream(jarInputStream);
                } 
                else {
                    jarInputStream.closeEntry();
                }
            }
        } catch (IOException e) {
            if (jarInputStream != null) {
                jarInputStream.close();
            }
            throw e;
        }

        if (jarInputStream != null) {
            jarInputStream.close();
        }
        throw new IOException("Entry not found: " + getFullEntryName());
    }

    @Override
    public long getSize() {
        JarInputStream jarInputStream = null;
        try {
            jarInputStream = createJarInputStream();
            ZipEntry entry;
            String fullEntryName = getFullEntryName();

            while ((entry = jarInputStream.getNextEntry()) != null) {
                if (entry.getName().equals(fullEntryName)) {
                    long size = entry.getSize();
                    return size == -1 ? 0 : size;
                } else {
                    jarInputStream.closeEntry();
                }
            }
            jarInputStream.close();

        } catch(Exception e){
        	e.printStackTrace();
        }finally {
            if (jarInputStream != null) {
            }
        }
        return 0;
    }

    private JarInputStream createJarInputStream() throws IOException {
        URLConnection connection = jarFileURL.openConnection();
        connection.setUseCaches(false);
        InputStream inputStream = connection.getInputStream();
        return new JarInputStream(inputStream);
    }

    private String getFullEntryName() {
        return relativePath == null ? entryName : relativePath + "/" + entryName;
    }

    /**
     * Wrapper InputStream that ensures the JarInputStream is closed when the stream is closed.
     */
    public static class JarEntryInputStream extends InputStream {
        private final JarInputStream jarInputStream;
        private boolean closed = false;

        JarEntryInputStream(JarInputStream jarInputStream) {
            this.jarInputStream = jarInputStream;
        }

        @Override
        public int read() throws IOException {
            if (closed) {
                throw new IOException("Stream closed");
            }
            return jarInputStream.read();
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            if (closed) {
                throw new IOException("Stream closed");
            }
            return jarInputStream.read(b, off, len);
        }

        @Override
        public void close() throws IOException {
            if (!closed) {
                jarInputStream.closeEntry();
                jarInputStream.close();
                closed = true;
            }
        }
    }

}

