package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;


public class FileTransfer {

	private String username;
	private String password;
	private String ip;
	private int port;
	private File fileToSend;
	private JTextArea textArea;
	
	private JTextField usernameJT;
	private JPasswordField passwordJP;
	private JTextField ip1;
	private JTextField ip2;
	private JTextField ip3;
	private JTextField ip4;
	private JTextField portJT;

	
	public FileTransfer(JTextField username, JPasswordField password, JTextField ip1, JTextField ip2, 
			JTextField ip3, JTextField ip4, JTextField port, JTextArea textArea, File fileToSend)
	{
		usernameJT = username;
		passwordJP = password;
		this.ip1 = ip1;
		this.ip2 = ip2;
		this.ip3 = ip3;
		this.ip4 = ip4;
		portJT = port;
		this.textArea = textArea;
		this.fileToSend = fileToSend;
	}
	/**
	 * Sends a file to the corresponding ftp server
	 */
	public void send()
	{
		update();
		
		FTPClient fc = new FTPClient();

		if(validate())
		{
			try 
			{
				fc.connect(ip, port);
				if(fc.login(username, password))
				{
					fc.enterLocalActiveMode();

					FileInputStream fIn = new FileInputStream(fileToSend);
					fc.setFileType(FTPClient.BINARY_FILE_TYPE);

					if( checkTransferDirectory(fc) )
					{	
						boolean result = fc.storeFile("/transfer/"+fileToSend.getName(), fIn);

						if(result)
							textArea.append("Succesful transfer in '/transfer/' directory \n" +
									"-----------------------------------\n");
					}

					else
					{
						textArea.append("Trying in root '/' \n");
						boolean result = fc.storeFile("/"+fileToSend.getName(), fIn);

						if(result)
							textArea.append("Succesful transfer int root '/' directory \n" +
									"-----------------------------------\n");
					}
					
					boolean logout = fc.logout();

					if(logout)
						textArea.append("logout successful\n" +
								"-----------------------------------\n");
					else
						textArea.append("logout unsuccessful\n" +
								"-----------------------------------\n");

					fc.disconnect();
				}

			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		else
			textArea.append("Invalid fields\n" );

	}
	
	public void changeFileToSend(File newFile)
	{
		fileToSend = newFile;
	}

	private void update()
	{
		this.username = usernameJT.getText();
		this.password = new String(passwordJP.getPassword());
		this.ip = ip1.getText() +"."+ip2.getText() +"." +ip3.getText()+"."+ip4.getText();
		this.port = Integer.parseInt(portJT.getText());
	}
	
	private boolean validate()
	{
		
		if(username.trim().equals("") || password.trim().equals(""))
			textArea.append("Login in as default\n");
		
		String[] ipA = ip.split("[.]");
		int[] ips = new int[4];

		if(ipA.length != 4)
			return false;

		try
		{
			for(int i = 0 ; i < ipA.length; i++)
			 {
				ips[i] = Integer.parseInt(ipA[i]);
				
				if(ips[i] > 255)
					return false;
			 }
		}
		catch(NumberFormatException e)
		{
			System.out.println("exception");
			return false;
		}

		return true;
	}

	/**
	 * Checks the presence of the /transfer directory. I choose this directory to save the files
	 * that this app sends to the ftp server.
	 *   
	 * @param server the ftp server to check for the /transfer directory.
	 * @return true if the directory is present or if it's successfully created. 
	 */
	private boolean checkTransferDirectory(FTPClient server)
	{
		try 
		{
			textArea.append("Looking for the '/transfer/' directory\n");

			FTPFile[] directories = server.listDirectories();
			boolean transferDirectoryPresent = false;

			for(FTPFile dir : directories)
			{
				if(dir.getName().equals("transfer"))
				{
					transferDirectoryPresent = true;
					break;
				}
			}

			if(!transferDirectoryPresent)
			{
				boolean check = server.makeDirectory("/transfer");
				if(check)
					textArea.append("'/transfer/' directory created\n");
				else
				{
					textArea.append("'/transfer/' directory not created\n");
					return false;
				}
			}
			else
				textArea.append("'/transfer/' directory already present\n");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		return true;
	}
	
	public boolean isFileNull()
	{
		if(fileToSend==null)
			return true;
		else
			return false;
	}


}
