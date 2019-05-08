import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
class WindowCloser extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
	{
		Window w=e.getWindow();
		w.setVisible(false);
		w.dispose();
	}
}
class GUI
{
static boolean finished=false;
Frame f,f1,f2;
Panel p1;
Panel p2;
Panel p3;
Panel p21,p22;
Label l1,l;
TextField t,nusername,npass;
Font fn;
Button b1,b3;
static String username;
static String pass;
GridBagConstraints gbc;
Panel p4;
Panel p5;
static TextArea ta1=new TextArea();
Label notreg=new Label("");
PrintStream o; 
BufferedWriter out ; 
PrintStream console;  
TextField t2,tf2;
Button b2,reg,save,GotoLogin;
String message;
TextField password;
public GUI()
{
f=new Frame();
f.setSize(500,500);
f1=new Frame();
f1.setSize(500,500);
fn=new Font("Arial",Font.BOLD,20);
f.setLayout(new GridLayout(3,1));
f2=new Frame();
f2.setSize(500,500);


f2.setLayout(new GridLayout(3,1));
p1=new Panel();
p1.setSize(500,500);
p1.setBackground(Color.yellow);
 l=new Label("Welcome To VCHAT");
l.setFont(fn);
p1.add(l);
f.add(p1);
p2=new Panel();
p2.setLayout(new GridLayout(2,1));
p2.setBackground(Color.yellow);
Panel p21=new Panel();
Panel p22=new Panel();
Panel p23=new Panel();
Label l1=new Label("Username");
Label l2=new Label("Password");
Label l3=new Label("Choose Username");
Label l4=new Label(" Set Password");
save=new Button("Save");
//GotoLogin=new Button("GO to Login");
save.addActionListener(new G());
//GotoLogin.addActionListener(new H());
l1.setFont(fn);
l2.setFont(fn);
l3.setFont(fn);
l4.setFont(fn);
f2.add(l3);
f2.add(l4);
nusername=new TextField();
npass=new TextField();
Panel re=new Panel();
re.setLayout(new GridLayout(1,2));
re.setBackground(Color.yellow);
re.add(nusername);
re.add(npass);
Panel re2=new Panel();
re2.setLayout(new GridLayout(1,1));
f2.add(re);
re2.add(save);
f2.add(re2);
//f2.add(GotoLogin);

t=new TextField(10);
  password = new TextField(10);
        password.setEchoChar('*');
        
p22.add(l2);
p22.add(password);
t.addKeyListener(new C());
p21.add(l1);
p21.add(t);
b1=new Button("Enter");
reg=new Button("Register Now");
b3=new Button("Attach");
b1.setForeground(Color.blue);
b1.setFont(fn);
b1.addActionListener(new D());
reg.setForeground(Color.blue);
reg.setFont(fn);
reg.addActionListener(new F());
p23.add(b1);
p23.add(reg);
p2.add(p21);
p2.add(p22);
p2.add(p23);
p2.add(notreg);
f.add(p2);

p3=new Panel();
p3.setBackground(Color.yellow);
f.add(p3);
WindowCloser wc=new WindowCloser();
f.addWindowListener(wc);
f2.addWindowListener(wc);
f.setVisible(true);
}
public void display() throws FileNotFoundException 
{
	    GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

fn=new Font("Arial",Font.BOLD,20);
o = new PrintStream(new FileOutputStream("A.txt",true));
p4=new Panel();
p4.setLayout(new BorderLayout());
p5 = new Panel();
p5.setBackground(Color.BLUE);
p5.setLayout(new GridBagLayout());
p4.setBackground(Color.yellow);
ta1=new TextArea();
ta1.setEditable(false);
ta1.setFont(fn);
ta1.setBackground(Color.yellow);
p4.add(ta1, BorderLayout.CENTER);
tf2=new TextField();
tf2.addKeyListener(new A());
b2=new Button("Send");
b2.addActionListener(new sendMessageButtonListener());
b2.setForeground(Color.black);
b2.setFont(fn);
b3.addActionListener(new F());
b3.setForeground(Color.black);
b3.setFont(fn);
p5.add(b3,right);
try
{
File file = new File("A.txt"); 
BufferedReader br = new BufferedReader(new FileReader(file)); 
String st; 
while ((st = br.readLine()) != null) 
{
	ta1.append(st);
	ta1.append("\n");
}
}
catch(Exception d)
{
} 
p5.add(tf2,left);
p5.add(b2,right);
p4.add(BorderLayout.SOUTH, p5);
f1.setTitle(username+"'s VCHAT");
f1.add(p4);
WindowCloser wc=new WindowCloser();
f1.addWindowListener(wc);
f1.setVisible(true);
tf2.requestFocusInWindow();
}

class sendMessageButtonListener implements ActionListener 
{
		public void actionPerformed(ActionEvent event) 
		{
			if (tf2.getText().length() < 1) 
			{
                // do nothing
            }
			else if (tf2.getText().equals(".Clear"))
			{
                ta1.setText("Cleared all messages\n");
                tf2.setText("");
			try
				{
					PrintWriter writer = new PrintWriter("A.txt");
					writer.print("");
					writer.close();
				}
			catch(Exception g)
				{
				}
            }

			else if (tf2.getText().equals(".Bye")) 
			{
				finished=true;
				f1.setVisible(false);
            }

			 else 
			{
				String message="<" + username + ">:  " + tf2.getText()+"\n";
                ta1.append( tf2.getText()+ "\n");
                tf2.setText("");
				o.append(message);
				byte[] buffer = message.getBytes(); 
				DatagramPacket datagram = new
				DatagramPacket(buffer,buffer.length,Project.group,Project.port); 
				try
				{
					Project.socket.send(datagram); 
				}
				catch(IOException ie) 
				{ 
				System.out.println("Error reading/writing from/to socket"); 
				ie.printStackTrace(); 
				} 
            }
            tf2.requestFocusInWindow();
        }
}
class A implements KeyListener
{
			public void keyReleased(KeyEvent e)
			{
			//
			}
			public void keyTyped(KeyEvent e)
			{
			//
			}
			public void keyPressed (KeyEvent e)
{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{

					if (tf2.getText().length() < 1) 
							{
								// do nothing
							}
					else if (tf2.getText().equals(".Clear")) 
							{
								ta1.setText("Cleared all messages\n");
								tf2.setText("");
								try
									{
									PrintWriter writer = new PrintWriter("A.txt");
									writer.print("");
									writer.close();
									}
								catch(Exception g)
									{
									}
							}

					else if (tf2.getText().equals(".Bye")) 
							{
							finished=true;
							f1.setVisible(false);
							}

				else {
						String message="<" + username + ">:  " + tf2.getText()+"\n";
						ta1.append(tf2.getText()+ "\n");
						tf2.setText("");
						o.append(message);
						byte[] buffer = message.getBytes(); 
						DatagramPacket datagram = new
						DatagramPacket(buffer,buffer.length,Project.group,Project.port); 
					try{
									Project.socket.send(datagram); 
								}
				catch(IOException ie) 
							{ 
								System.out.println("Error reading/writing from/to socket"); 
								ie.printStackTrace(); 
							} 
							}
							tf2.requestFocusInWindow();
						}

				}

}

class G implements ActionListener{
	public void actionPerformed(ActionEvent e)
		{

String nu=nusername.getText();
String np=npass.getText();
try{
   PrintStream credentials = new PrintStream(new FileOutputStream("login.txt",true));
String details=nu+"#"+np+"\n";
credentials.append(details);

credentials.close();
}
catch(Exception v)
{
}
		f.setVisible(true);
		f2.setVisible(false);
		
		}
	}
class F implements ActionListener{
	public void actionPerformed(ActionEvent e)
		{
npass.setText("");
nusername.setText("");
		f.setVisible(false);
		f2.setVisible(true);
		
		}
	}

class E implements ActionListener{
	public void actionPerformed(ActionEvent e)
		{
			FileDialog fileDialog=new FileDialog(f1,"Select File");
			fileDialog.setVisible(true);
			String message="<" + username + ">:  " + fileDialog.getDirectory()+fileDialog.getFile()+"\n";
            ta1.append(message);
			byte[] buffer = message.getBytes(); 
			DatagramPacket datagram = new
			DatagramPacket(buffer,buffer.length,Project.group,Project.port); 
			try
			{
				Project.socket.send(datagram);
			}
				catch(IOException ie) 
			{ 
				System.out.println("Error reading/writing from/to socket"); 
				ie.printStackTrace(); 
			} 
            
            tf2.requestFocusInWindow();
		}
	}
	
class C implements KeyListener
{
		public void keyReleased(KeyEvent e)
		{

		}
		public void keyTyped(KeyEvent e)
		{

		}
		public void keyPressed(KeyEvent e) 
		{
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
				username = t.getText();
				
				 
					//ta1.append("Yooooo");
					if (username.length() < 1)
					{
						System.out.println("No!");
					} else 
					{
						f.setVisible(false);
						f1.setVisible(true);
						try
						{
							display();
						}
						catch(FileNotFoundException z)
						{
						} 
					}
						
				}	
		}

}


class D implements ActionListener
{
	public void actionPerformed(ActionEvent e) 
	{
		    username = t.getText();

pass=password.getText();
    boolean isFound = false;
    String record = null;
    FileReader in = null;
    try{
        in = new FileReader ("login.txt");
        BufferedReader bin = new BufferedReader(in);
        record = new String();
        while ((record = bin.readLine()) != null)
        {
            if (record.contentEquals(username+"#"+pass))
               { isFound = true;
		break;
		}
            
        }

    bin.close();
    bin = null;
 }catch(IOException ioe){
   
 } 
			if (username.length() < 1) 
			{
                System.out.println("No!");
			} 
			else if(isFound)
			{

                f.setVisible(false);
		        f1.setVisible(true);
				try
				{
                display();
				}
				catch(FileNotFoundException z)
				{
					System.out.print("Exception");
				} 
            }
else
{
notreg.setText("Not registered");
f.setVisible(true);
		        f1.setVisible(false);
}

	}
}

}
public class Project
{ 
private static final String TERMINATE = "Exit"; 
static String name; 
static volatile boolean finished = false; 
static InetAddress group;
static int port;
static MulticastSocket socket;
static GUI g;

	public static void main(String[] args) throws FileNotFoundException 
	{ 
		if (args.length != 2) 
			System.out.println("Two arguments required: <multicast-host> <port-number>"); 
		else
		{ 
			try
			{ 
				g=new GUI();
				//func();
				 group = InetAddress.getByName(args[0]); 
				 port = Integer.parseInt(args[1]); 
				 socket = new MulticastSocket(port); 
				// Since we are deploying 
				socket.setTimeToLive(0); 
				socket.joinGroup(group); 
				Thread t = new Thread(new
				ReadThread(socket,group,port)); 
				t.start();
				g.ta1.append("Start Typing Messages");
				
			} 
			catch(SocketException se) 
			{ 
				System.out.println("Error creating socket"); 
				se.printStackTrace(); 
			} 
			catch(IOException ie) 
			{ 
				System.out.println("Error reading/writing from/to socket"); 
				ie.printStackTrace(); 
			} 
		} 
	} 
}
class ReadThread implements Runnable 
{ 
	private MulticastSocket socket; 
	private InetAddress group; 
	private int port; 
	private static final int MAX_LEN = 1000; 
	ReadThread(MulticastSocket socket,InetAddress group,int port) 
	{ 
		this.socket = socket; 
		this.group = group; 
		this.port = port; 
	} 
	
	@Override
	public void run() 
	{ 
		while(!GUI.finished) 
		{ 
				byte[] buffer = new byte[ReadThread.MAX_LEN]; 
				DatagramPacket datagram = new
				DatagramPacket(buffer,buffer.length,group,port); 
				String message; 
			try
			{ 
				socket.receive(datagram); 
				message = new
				String(buffer,0,datagram.getLength(),"UTF-8"); 
				if(!message.startsWith("<" +GUI.username+">:")) 
					GUI.ta1.append(message);
			} 
			catch(IOException e) 
			{ 
				System.out.println("Socket closed!"); 
			} 
		} 
	} 
}

