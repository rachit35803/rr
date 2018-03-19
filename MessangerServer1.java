import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
class MessangerServer1 extends JFrame implements ActionListener
{
JTextField jtf, jtf1;
JTextArea jta, jta1;
JScrollPane jsp;
JButton jb1, jb2;
ServerSocket ss;
Socket s;
DataInputStream din;
DataOutputStream dout;
BufferedReader br;

MessangerServer1(String name)
{
super(name);
Container c =getContentPane();
jtf=new JTextField();
jtf.setBounds(20,30, 250, 250);

jta= new JTextArea(50, 50);

jta.setFont(new Font("Varinda", Font.PLAIN, 15));

jsp= new JScrollPane(jta);
jsp.setBounds(20, 30, 250, 250);

c.add(jsp);
c.add(jtf);
jb1= new JButton("Send");
jb1.setBounds(250, 340, 100, 60);
jb1.setLayout(new BorderLayout());

c.add(jb1);
jb1.addActionListener(this);
jtf1=new JTextField();
jtf1.setBounds(20,290, 100, 40);
jta1= new JTextArea(50, 50);
jta1.setBounds(20, 290, 250, 40);
jta1.setFont(new Font("Varinda", Font.PLAIN, 15));


c.add(jta1);
c.add(jtf1);


setSize(400, 500);

c.setBackground(Color.gray);
setLayout(new BorderLayout());
setDefaultCloseOperation(EXIT_ON_CLOSE);

setVisible(true);	

try
{

 ss=new ServerSocket(10);  
s=ss.accept();  
//System.out.println("client Connected");
jta.setText("Client Connected");
 din=new DataInputStream(s.getInputStream());  
 dout=new DataOutputStream(s.getOutputStream());  
br=new BufferedReader(new InputStreamReader(System.in));  
 String str=jta1.getText();
String str2=jta1.getText();
  
while(!str.equals("stop")){  
str=din.readUTF(); 
//System.out.println("client says: "+str);  
jta.setText(" client Says:  " +str);
str2=br.readLine(); 
dout.writeUTF(str2);  
dout.flush();  
}  
din.close();  
s.close();  
ss.close(); 
}
catch(Exception ex)
{
	System.out.println(ex);
	
}
}
 
public void actionPerformed(ActionEvent e)
{
	
	if(e.getSource()==jb1)
	{
	jta.setText(" me: " +jta1.getText());
	try
{

dout.writeUTF();

}
catch(Exception ex)
{

}
	
	}

}
public static void main(String...o) 
{
	
	new MessangerServer1("Messanger Server");
}

}
