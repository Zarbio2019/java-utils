package org.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.ChannelSftp.LsEntry;

/**
 * SftpUtil
 * @author Romulo
 * @date 2014-06-17
 * @version 1.0
 * https://www.programmersought.com/article/17892674657/
 */

public class SftpUtil
{
	protected final Logger log = LogManager.getLogger(getClass());

	// Model
	private String host;
	private String username;
	private String password;
	private int port = 22;

    private ChannelSftp sftp = null;
    private Session sshSession = null;
 
    public SftpUtil(){}
    
    public String getHost()
    {
        return host;
    }
 
    public void setHost(String host)
    {
        this.host = host;
    }
 
    public String getUsername()
    {
        return username;
    }
 
    public void setUsername(String username)
    {
        this.username = username;
    }
 
    public String getPassword()
    {
        return password;
    }
 
    public void setPassword(String password)
    {
        this.password = password;
    }
 
    public int getPort()
    {
        return port;
    }
 
    public void setPort(int port)
    {
        this.port = port;
    }
 
    // Functions
    public void connect()
    {
        try
        {
            JSch jsch = new JSch();
            
            sshSession = jsch.getSession(username, host, port);

            log.info("Session created.");

            sshSession.setPassword(password);
            
            sshSession.setConfig("StrictHostKeyChecking", "no");
            
            sshSession.setTimeout(1000); // 1 seg
            
            sshSession.connect();
            
            log.info("Session connected.");
            
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            
            log.info("Opening Channel.");

            sftp = (ChannelSftp) channel;
            
            log.info("Connected to " + host + ".");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
 
    public void disconnect()
    {
        if (this.sftp != null)
        {
            if (this.sftp.isConnected())
            {
                this.sftp.disconnect();
                
                log.info("sftp is closed already");
            }
        }
        
        if (this.sshSession != null)
        {
            if (this.sshSession.isConnected())
            {
                this.sshSession.disconnect();
                
                log.info("sshSession is closed already");
            }
        }
    }
 
    /**
     * List files in a directory
     * 
     * @Param directory: directory to list
     * @param sftp
     * @return
     * @throws SftpException
     */
    public Vector listFiles(String directory) throws SftpException
    {
        return sftp.ls(directory);
    }
    
    public List<String> listFilesSftp(String directory)
    {
        List<String> filenames = new ArrayList<String>();
        try
        {
            // connect();
            Vector v = listFiles(directory);
            // sftp.cd(remotePath);
            if (v.size() > 0)
            {
                System.out.println ( "The process file number is not zero, to start the download ... fileSize =" + v.size ());
                Iterator it = v.iterator();
                
                while (it.hasNext())
                {
                    LsEntry entry = (LsEntry) it.next();
                    String filename = entry.getFilename();
                    SftpATTRS attrs = entry.getAttrs();
                    
                    filenames.add(filename);
                    
                    //if (!attrs.isDir())
                }
            }
            if (log.isInfoEnabled())
            {
                log.info("listFilesSftp : remotePath=" + directory
                        + ". File size is"
                        + v.size());
            }
        }
        catch (SftpException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // this.disconnect();
        }
        return filenames;
    }

    public List<String> listFilesSftp(String directory, String fileFormat, String fileEndFormat)
    {
        List<String> filenames = new ArrayList<String>();
        try
        {
            // connect();
            Vector v = listFiles(directory);
            // sftp.cd(remotePath);
            if (v.size() > 0)
            {
                System.out.println ( "The process file number is not zero, to start the download ... fileSize =" + v.size ());
                Iterator it = v.iterator();
                
                while (it.hasNext())
                {
                    LsEntry entry = (LsEntry) it.next();
                    String filename = entry.getFilename();
                    SftpATTRS attrs = entry.getAttrs();
                    
                    if (!attrs.isDir())
                    {
                        boolean flag = false;
                        String localFileName = filename;
                        fileFormat = fileFormat == null ? "" : fileFormat
                                .trim();
                        fileEndFormat = fileEndFormat == null ? ""
                                : fileEndFormat.trim();
                                                 // three conditions 
                        if (fileFormat.length() > 0 && fileEndFormat.length() > 0)
                        {
                            if (filename.startsWith(fileFormat) && filename.endsWith(fileEndFormat))
                            {
                                filenames.add(localFileName);
                            }
                        }
                        else if (fileFormat.length() > 0 && "".equals(fileEndFormat))
                        {
                            if (filename.startsWith(fileFormat))
                            {
                            	filenames.add(localFileName);

                            }
                        }
                        else if (fileEndFormat.length() > 0 && "".equals(fileFormat))
                        {
                            if (filename.endsWith(fileEndFormat))
                            {
                            	filenames.add(localFileName);
                            }
                        }
                    }
                }
            }
            if (log.isInfoEnabled())
            {
                log.info("listFilesSftp : remotePath=" + directory
                        + ". File size is"
                        + v.size());
            }
        }
        catch (SftpException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // this.disconnect();
        }
        return filenames;
    }

    /**
     * Determine whether a file exists
     * @param filePath
     * @return
     */
    public boolean fileExists(String filePath)
    {
        File file = new File(filePath);
        if (!file.exists())
        {
            return false;
        }
 
        if (!file.isFile())
        {
            return false;
        }
        
        return true;
    }
        
    /**
     * Determine whether a file exists
     * @param filePath
     * @return
     */
    public boolean fileExistsSftp(String filePath)
    {
        boolean ret = false;
        try
        {
            SftpATTRS sftpATTRS = sftp.lstat(filePath);
            ret = true;
            return sftpATTRS.isReg();
            // return !sftpATTRS.isDir();
        }
        catch (Exception e)
        {
            if (e.getMessage().toLowerCase().equals("no such file"))
            {
            	ret = false;
            }
        }
        return ret;
    }
    
    /**
     * Determine whether a directory exists
     * @param directory
     * @return
     */
    public boolean directoryExistsSftp(String directory)
    {
        boolean directoryExistsSftpFlag = false;
        try
        {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            directoryExistsSftpFlag = true;
            return sftpATTRS.isDir();
        }
        catch (Exception e)
        {
            if (e.getMessage().toLowerCase().equals("no such file"))
            {
                directoryExistsSftpFlag = false;
            }
        }
        return directoryExistsSftpFlag;
    }
    
    /**
     * Create a directory
     * @param createpath
     * @return
     */
    public boolean createDirSftp(String dirPath)
    {
        try
        {
            if (directoryExistsSftp(dirPath))
            {
                this.sftp.cd(dirPath);
                return true;
            }
            
            String pathArry[] = dirPath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            
            for (String path : pathArry)
            {
                if (path.equals(""))
                {
                    continue;
                }
                filePath.append(path + "/");
                if (directoryExistsSftp(filePath.toString()))
                {
                    sftp.cd(filePath.toString());
                }
                else
                {
                    sftp.mkdir(filePath.toString()); // create a directory
                                         
                    sftp.cd(filePath.toString()); // set the current directory and enter
                }
            }
            this.sftp.cd(dirPath);
            return true;
        }
        catch (SftpException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * If the directory does not exist to create the directory
     * @param path
     */
    public void mkdirs(String path)
    {
        File f = new File(path);
 
        String fs = f.getParent();
 
        f = new File(fs);
 
        if (!f.exists())
        {
            f.mkdirs();
        }
    }

    /**
     * Delete local file
     * @param filePath
     * @return
     */
    public boolean deleteFile(String filePath)
    {
        File file = new File(filePath);
        
        if (!file.exists())
        {
            return false;
        }
 
        if (!file.isFile())
        {
            return false;
        }
        
        return true;
    }
    
    /**
     * Delete files stfp
     * @param filePath: full file path to be deleted
     */
    public void deleteFileSftp(String filePath)
    {
        try
        {
            // sftp.cd(directory);
            sftp.rm(filePath);

            log.info("delete file success from sftp.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Obtiene la Ip de la Url.
     * @param url
     * @return
     */
    public String getIpUrl(String url)
    {
    	String ip = "";
    	
		try
		{
			InetAddress address = InetAddress.getByName(new URL(url).getHost());
			ip = address.getHostAddress();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		
		return ip;
    }
    
    /**
     * Batch download file
     * @Param remotPath: remote download directory (the path to the end of the symbol, which can be a relative path eg: / assess /sftp/jiesuan_2/2014/)
     * @Param localPath: save local directory (the symbol to the end of the path, D: \ Duansha \sftp\)
     * @Param fileFormat: Download File Format (begin with a specific character, not empty test)
     * @Param fileEndFormat: download file format (file format)
     * @Param del: Delete after downloadsftpFile
     * @return
     */
    public List<String> batchDownLoadFile(String remotePath, String localPath,
            String fileFormat, String fileEndFormat, boolean del)
    {
        List<String> filenames = new ArrayList<String>();
        try
        {
            // connect();
            Vector v = listFiles(remotePath);
            // sftp.cd(remotePath);
            if (v.size() > 0)
            {
                                 System.out.println ( "The process file number is not zero, to start the download ... fileSize =" + v.size ());
                Iterator it = v.iterator();
                while (it.hasNext())
                {
                    LsEntry entry = (LsEntry) it.next();
                    String filename = entry.getFilename();
                    SftpATTRS attrs = entry.getAttrs();
                    if (!attrs.isDir())
                    {
                        boolean flag = false;
                        String localFileName = localPath + filename;
                        fileFormat = fileFormat == null ? "" : fileFormat
                                .trim();
                        fileEndFormat = fileEndFormat == null ? ""
                                : fileEndFormat.trim();
                                                 // three conditions 
                        if (fileFormat.length() > 0 && fileEndFormat.length() > 0)
                        {
                            if (filename.startsWith(fileFormat) && filename.endsWith(fileEndFormat))
                            {
                                flag = downloadFile(remotePath, filename,localPath, filename);
                                if (flag)
                                {
                                    filenames.add(localFileName);
                                    if (flag && del)
                                    {
                                        deleteFileSftp(remotePath + filename);
                                    }
                                }
                            }
                        }
                        else if (fileFormat.length() > 0 && "".equals(fileEndFormat))
                        {
                            if (filename.startsWith(fileFormat))
                            {
                                flag = downloadFile(remotePath, filename, localPath, filename);
                                if (flag)
                                {
                                    filenames.add(localFileName);
                                    if (flag && del)
                                    {
                                        deleteFileSftp(remotePath + filename);
                                    }
                                }
                            }
                        }
                        else if (fileEndFormat.length() > 0 && "".equals(fileFormat))
                        {
                            if (filename.endsWith(fileEndFormat))
                            {
                                flag = downloadFile(remotePath, filename,localPath, filename);
                                if (flag)
                                {
                                    filenames.add(localFileName);
                                    if (flag && del)
                                    {
                                        deleteFileSftp(remotePath + filename);
                                    }
                                }
                            }
                        }
                        else
                        {
                            flag = downloadFile(remotePath, filename,localPath, filename);
                            if (flag)
                            {
                                filenames.add(localFileName);
                                if (flag && del)
                                {
                                    deleteFileSftp(remotePath + filename);
                                }
                            }
                        }
                    }
                }
            }
            if (log.isInfoEnabled())
            {
                log.info("download file is success:remotePath=" + remotePath
                        + "and localPath=" + localPath + ",file size is"
                        + v.size());
            }
        }
        catch (SftpException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // this.disconnect();
        }
        return filenames;
    }
 
    /**
     * Download a single file
     * @Param remotPath: remote download directory (symbolic path to the end)
     * @Param remoteFileName: Download file name
     * @Param localPath: save local directory (the path symbol end)
     * @Param localFileName: save filename
     * @return
     */
    public boolean downloadFile(String remotePath, String remoteFileName,String localPath, String localFileName)
    {
        FileOutputStream fieloutput = null;
        
        try
        {
            // sftp.cd(remotePath);
            File file = new File(localPath + localFileName);
            // mkdirs(localPath + localFileName);
            fieloutput = new FileOutputStream(file);
            sftp.get(remotePath + remoteFileName, fieloutput);

            log.info("downloadFile : " + remoteFileName + " success from sftp.");

            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SftpException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != fieloutput)
            {
                try
                {
                    fieloutput.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
 
    /**
     * Upload a single file
     * @Param remotePath: remote save directory
     * @Param remoteFileName: save filename
     * @Param localPath: local upload directory (the path symbol end)
     * @Param localFileName: upload the file name
     * @return
     */
    public boolean uploadFile(String remotePath, String remoteFileName,String localPath, String localFileName)
    {
        FileInputStream in = null;
        try
        {
        	createDirSftp(remotePath);
            File file = new File(localPath + localFileName);
            in = new FileInputStream(file);
            sftp.put(in, remoteFileName);
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SftpException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
 
    /**
     * Bulk upload file
     * @Param remotePath: remote save directory
     * @Param localPath: local upload directory (the path symbol end)
     * @Param del: whether to delete the local file upload
     * @return
     */
    public boolean bacthUploadFile(String remotePath, String localPath,
            boolean del)
    {
        try
        {
            connect();
            File file = new File(localPath);
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++)
            {
                if (files[i].isFile()
                        && files[i].getName().indexOf("bak") == -1)
                {
                    if (this.uploadFile(remotePath, files[i].getName(),
                            localPath, files[i].getName())
                            && del)
                    {
                        deleteFile(localPath + files[i].getName());
                    }
                }
            }
            if (log.isInfoEnabled())
            {
                log.info("upload file is success:remotePath=" + remotePath
                        + "and localPath=" + localPath + ",file size is "
                        + files.length);
            }
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            this.disconnect();
        }
        return false;
    }
    
    public static void main(String[] args)
    {       
    	SftpUtil sftp = new SftpUtil();
    	
    	Boolean status = false;
    	
    	try
		{
	    	// Configuracion
	    	String hostName = "ftp://test.rebex.net/";
	    	String ipHostName = sftp.getIpUrl(hostName);
	        int port = 22;// FTP = 21, SFTP = 22
	        String user = "demo";
	        String pwd = "password";
	       
	        sftp.setHost(ipHostName);
	        sftp.setPort(22);
	        sftp.setUsername(user);
	        sftp.setPassword(pwd);
	        
	        // Connect
	        sftp.connect();
	        
	        //status = sftp.directoryExistsSftp("aspnet_client"); // ex: /aspnet_client
	        
	        //status = sftp.fileExistsSftp("/readme.txt");
	        
	        // Download
			String localPath = "D:/temp/"; // local storage address
			String sftpPath = "/"; // sftp download path

		    //sftp.batchDownLoadFile(sftpPath, localPath, "re", ".txt", true);
			
			// File names
			List<String> fileNames = sftp.listFilesSftp(sftpPath);
			fileNames = sftp.listFilesSftp(sftpPath, "re", ".txt");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            sftp.disconnect();
        }
    }
}