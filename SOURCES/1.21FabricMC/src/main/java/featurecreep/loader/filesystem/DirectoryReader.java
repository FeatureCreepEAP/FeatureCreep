package featurecreep.loader.filesystem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryReader extends VirtualFileSystem {

    // 构造函数：从指定目录中读取文件
    public DirectoryReader(File directory) throws IOException {
    	this(directory.toPath());
    }
    
    // 构造函数：从指定目录中读取文件
    public DirectoryReader(Path directory) throws IOException {
        super(directory.toUri());
        readDirectoryRecursively(directory);
    }
    
    // 递归读取目录的方法
    public void readDirectoryRecursively(Path directory) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path entry : stream) {
                String entryName = entry.getFileName().toString();
                Path fullPath = directory.resolve(entryName);

                if (Files.isRegularFile(entry)) {
                    // 读取文件内容并存储在map中
                    try (InputStream is = Files.newInputStream(entry);
                         ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = is.read(buffer)) != -1) {
                            baos.write(buffer, 0, bytesRead);
                        }
                        map.put(fullPath.toString(), baos.toByteArray());
                    }
                } else if (Files.isDirectory(entry)) {
                    // 对于目录，存储引用并递归读取
                    map.put(fullPath.toString(), null);
                    readDirectoryRecursively(entry);
                }
            }
        }
    }

    // 重写getURLForFile方法以处理目录中的文件
    @Override
    public URL getURLForFile(String file) {
        if (getURL() == null) {
            return null;
        }
        // 构建文件的URL
        try {
            File fileObj = new File(new File(getURL().getPath()), file);
            return fileObj.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return getURL();
    }

}