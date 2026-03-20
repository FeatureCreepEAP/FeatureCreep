// This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild
package com.asbestosstar.cpiojava;
import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.KaitaiStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class CpioRpm extends KaitaiStruct {
    public static CpioRpm fromFile(String fileName) throws IOException {
        return new CpioRpm(new ByteBufferKaitaiStream(fileName));
    }

    public CpioRpm(KaitaiStream _io) {
        this(_io, null, null);
    }

    public CpioRpm(KaitaiStream _io, KaitaiStruct _parent) {
        this(_io, _parent, null);
    }

    public CpioRpm(KaitaiStream _io, KaitaiStruct _parent, CpioRpm _root) {
        super(_io);
        this._parent = _parent;
        this._root = _root == null ? this : _root;
        _read();
    }
    private void _read() {
        this.files = new ArrayList<File>();
        {
            int i = 0;
            while (!this._io.isEof()) {
                this.files.add(new File(this._io, this, _root));
                i++;
            }
        }
    }
    public static class File extends KaitaiStruct {
        public static File fromFile(String fileName) throws IOException {
            return new File(new ByteBufferKaitaiStream(fileName));
        }

        public File(KaitaiStream _io) {
            this(_io, null, null);
        }

        public File(KaitaiStream _io, CpioRpm _parent) {
            this(_io, _parent, null);
        }

        public File(KaitaiStream _io, CpioRpm _parent, CpioRpm _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();

        }
        private void _read() {
            this.header = new FileHeader(this._io, this, _root);
         if (header().pathNameSize() - 1 > 0) {
      //  System.out.println(header().pathNameSize());
        	 this.pathName = this._io.readBytes((header().pathNameSize() - 1));
         System.out.println(new String(pathName));
        
            this.stringTerminator = this._io.readBytes(1);
            if (!(Arrays.equals(stringTerminator(), new byte[] { 0 }))) {
                throw new KaitaiStream.ValidationNotEqualError(new byte[] { 0 }, stringTerminator(), _io(), "/types/file/seq/2");
            }
            if (KaitaiStream.mod(header().pathNameSize(), 2) == 1) {
                this.pathNamePadding = this._io.readBytes(1);
                if (!(Arrays.equals(pathNamePadding(), new byte[] { 0 }))) {
                    throw new KaitaiStream.ValidationNotEqualError(new byte[] { 0 }, pathNamePadding(), _io(), "/types/file/seq/3");
                }
            }
     setFileData();
            if ( ((Arrays.equals(pathName(), new byte[] { 84, 82, 65, 73, 76, 69, 82, 33, 33, 33 })) && (header().fileSize() == 0)) ) {
                this.endOfFilePadding = this._io.readBytesFull();
            }
         }
        }
        public void setFileData() {
			// TODO Auto-generated method stub
        if	(header().fileSize() > 0) {
        	
        
        	byte first = this._io.readBytes(1)[0];
        	System.out.println(first);
        if(first == 0) {
        this.setFileData();
        
        
        }else {
        	System.out.println("Not Zero");
        	byte[] added = this._io.readBytes(header().fileSize()-1);
        	byte[] result = new byte[added.length + 1];   
        	result[0]=first;
        	System.arraycopy(added, 0, result, 1, added.length);
        	this.fileData = result;
            if (KaitaiStream.mod(header().fileSize(), 2) == 1) {
                this.fileDataPadding = this._io.readBytes(1);
                if (!(Arrays.equals(fileDataPadding(), new byte[] { 0 }))) {
 //                   throw new KaitaiStream.ValidationNotEqualError(new byte[] { 0 }, fileDataPadding(), _io(), "/types/file/seq/5");
                }
            }
        
        
        }
        
        }else {
        	this.fileData = new byte[0];
        }
        
        }
		private FileHeader header;
        private byte[] pathName;
        private byte[] stringTerminator;
        private byte[] pathNamePadding;
        private byte[] fileData;
        private byte[] fileDataPadding;
        private byte[] endOfFilePadding;
        private CpioRpm _root;
        private CpioRpm _parent;
        public FileHeader header() { return header; }
        public byte[] pathName() { return pathName; }
        public byte[] stringTerminator() { return stringTerminator; }
        public byte[] pathNamePadding() { return pathNamePadding; }
        public byte[] fileData() { return fileData; }
        public byte[] fileDataPadding() { return fileDataPadding; }
        public byte[] endOfFilePadding() { return endOfFilePadding; }
        public CpioRpm _root() { return _root; }
        public CpioRpm _parent() { return _parent; }
    }
    public static class FileHeader extends KaitaiStruct {
        public static FileHeader fromFile(String fileName) throws IOException {
            return new FileHeader(new ByteBufferKaitaiStream(fileName));
        }

        public FileHeader(KaitaiStream _io) {
            this(_io, null, null);
        }

        public FileHeader(KaitaiStream _io, CpioRpm.File _parent) {
            this(_io, _parent, null);
        }

        public FileHeader(KaitaiStream _io, CpioRpm.File _parent, CpioRpm _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
        byte[] first = this._io.readBytes(1);
        if(new String(first).equals("0")) {	
        byte[] nextfive = this._io.readBytes(5);
        	byte[] mag ={first[0],nextfive[0],nextfive[1],nextfive[2],nextfive[3],nextfive[4]};
        	this.magic = mag;
           
        	if (!(Arrays.equals(magic(), new byte[] { -57, 113 }))) {//old numbers
             //  throw new KaitaiStream.ValidationNotEqualError(new byte[] { -57, 113 }, magic(), _io(), "/types/file_header/seq/0");
            }
        
            
            //String dev = new String(this._io.readBytes(8),StandardCharsets.US_ASCII);
            String inode = new String(this._io.readBytes(8),StandardCharsets.US_ASCII);
            //System.out.println(inode);
            String mode = new String(this._io.readBytes(8),StandardCharsets.US_ASCII);
            String c_uid = new String(this._io.readBytes(8),StandardCharsets.US_ASCII);
            String c_gid = new String(this._io.readBytes(8),StandardCharsets.US_ASCII);            
            String numberOfLinks = new String(this._io.readBytes(8),StandardCharsets.US_ASCII);
            //String rDeviceNumber = new String(this._io.readBytes(8),StandardCharsets.US_ASCII);
            String modificationTime = new String(this._io.readBytes(8),StandardCharsets.US_ASCII);
         byte[] filsize = this._io.readBytes(8);
         //System.out.println(filsize[0]+" "+filsize[1]+" "+filsize[2]+" "+filsize[3]+" "+filsize[4]+" "+filsize[5]+" "+filsize[6]+" "+filsize[7]);
            String filesizeHex = new String(filsize,StandardCharsets.US_ASCII);
        //  System.out.println(filesizeHex);
            // String pathNameSizeHex = new String(this._io.readBytes(8),StandardCharsets.US_ASCII);
            String c_devmajor = new String(this._io.readBytes(8),StandardCharsets.US_ASCII);
            String c_devminor = new String(this._io.readBytes(8),StandardCharsets.US_ASCII);
            String c_rdevmajor = new String(this._io.readBytes(8),StandardCharsets.US_ASCII);
            String c_rdevminor = new String(this._io.readBytes(8),StandardCharsets.US_ASCII);
            String c_namesize = new String(this._io.readBytes(8),StandardCharsets.UTF_8);
            String c_check = new String(this._io.readBytes(8),StandardCharsets.UTF_8);

         //   String filesizeHex = new String(this._io.readBytes(101), 54, 8, StandardCharsets.UTF_8);
            //this._io.readBytes(54);
            //String filesizeHex = new String(this._io.readBytes(8),StandardCharsets.US_ASCII);

           // System.out.println(filesizeHex);

//System.out.println(c_rdevminor);

//System.out.println(c_check);

            // Convert the hexadecimal string to a decimal long
     
                try {
                	this.fileSize = Long.parseLong(filesizeHex, 16);
		//	System.out.println(fileSize);
             
                } catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
	                this.fileSize = 0;
	    			System.out.println(fileSize);

				}
       
            
            
         //    System.out.println(fileSize);
         //    System.out.println(c_check);
       //      System.out.println(fileSize);

//System.out.println(c_namesize);

	try {
		this.pathNameSize = Integer.parseInt(c_namesize, 16);
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		pathNameSize = 0;

	}

	
	
	
        
  //  System.out.println(pathNameSize);	

        }else {
        	//System.out.println(new String(first));
        	this._read();
        }

           // this.deviceNumber = this._io.readU8le();
          //  this.inodeNumber = this._io.readU2le();
          //  this.mode = this._io.readU2le();
          //  this.userId = this._io.readU2le();
          //  this.groupId = this._io.readU2le();
          //  this.numberOfLinks = this._io.readU2le();
          //  this.rDeviceNumber = this._io.readU2le();
          //  this.modificationTime = new FourByteUnsignedInteger(this._io, this, _root);
          //  this.pathNameSize = this._io.readU2le();
          //  this.fileSize = new FourByteUnsignedInteger(this._io, this, _root);

        }
        private byte[] magic;
        private int deviceNumber;
        private int inodeNumber;
        private int mode;
        private int userId;
        private int groupId;
        private int numberOfLinks;
        private int rDeviceNumber;
        private FourByteUnsignedInteger modificationTime;
        private int pathNameSize;
        private long fileSize;
        private CpioRpm _root;
        private CpioRpm.File _parent;
        public byte[] magic() { return magic; }
        public int deviceNumber() { return deviceNumber; }
        public int inodeNumber() { return inodeNumber; }
        public int mode() { return mode; }
        public int userId() { return userId; }
        public int groupId() { return groupId; }
        public int numberOfLinks() { return numberOfLinks; }
        public int rDeviceNumber() { return rDeviceNumber; }
        public FourByteUnsignedInteger modificationTime() { return modificationTime; }
        public int pathNameSize() { return pathNameSize; }
        public long fileSize() { return fileSize; }
        public CpioRpm _root() { return _root; }
        public CpioRpm.File _parent() { return _parent; }
    }
    public static class FourByteUnsignedInteger extends KaitaiStruct {
        public static FourByteUnsignedInteger fromFile(String fileName) throws IOException {
            return new FourByteUnsignedInteger(new ByteBufferKaitaiStream(fileName));
        }

        public FourByteUnsignedInteger(KaitaiStream _io) {
            this(_io, null, null);
        }

        public FourByteUnsignedInteger(KaitaiStream _io, CpioRpm.FileHeader _parent) {
            this(_io, _parent, null);
        }

        public FourByteUnsignedInteger(KaitaiStream _io, CpioRpm.FileHeader _parent, CpioRpm _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.mostSignificantBits = this._io.readU2le();
            this.leastSignificantBits = this._io.readU2le();
        }
        private Integer value;
        public Integer value() {
            if (this.value != null)
                return this.value;
            int _tmp = (int) ((leastSignificantBits() + (mostSignificantBits() << 16)));
            this.value = _tmp;
            return this.value;
        }
        private int mostSignificantBits;
        private int leastSignificantBits;
        private CpioRpm _root;
        private CpioRpm.FileHeader _parent;
        public int mostSignificantBits() { return mostSignificantBits; }
        public int leastSignificantBits() { return leastSignificantBits; }
        public CpioRpm _root() { return _root; }
        public CpioRpm.FileHeader _parent() { return _parent; }
    }
    private ArrayList<File> files;
    private CpioRpm _root;
    private KaitaiStruct _parent;
    public ArrayList<File> files() { return files; }
    public CpioRpm _root() { return _root; }
    public KaitaiStruct _parent() { return _parent; }
}
