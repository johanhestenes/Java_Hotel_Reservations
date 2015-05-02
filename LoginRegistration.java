import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import javax.swing.*;

public class LoginRegistration extends JFrame
{
   public LoginRegistration() throws FileNotFoundException
   {
      super("Login/Registration");
      
      setSize(390, 300);
      setResizable(false);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      JPanel panel = new JPanel();
      
      panel.setLayout(null);
      JLabel l1 = new JLabel("User ID:");
      final JTextField t1 = new JTextField(20);
      JButton b1 = new JButton("Login");
      
      l1.setBounds(10, 10, 50, 20);
      panel.add(l1);
      t1.setBounds(60, 10, 200, 20);
      panel.add(t1);
      b1.setBounds(270, 10, 80, 20);
      panel.add(b1);
      
 
      JLabel l2 = new JLabel("New User?");
      JLabel l3 = new JLabel("Enter an ID:");
      final JTextField t2 = new JTextField(20);
      JButton b2 = new JButton("Register");
      
      l2.setBounds(10, 200, 70, 20);
      panel.add(l2);
      
      l3.setBounds(10, 230, 100, 20);
      panel.add(l3);
      t2.setBounds(80, 230, 200, 20);
      panel.add(t2);
      b2.setBounds(290, 230, 90, 20);
      panel.add(b2);
      
      this.add(panel);
      
      
      b1.addActionListener(new ActionListener() 
      {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
            String loginID = t1.getText();
            try
            {
               Scanner s = new Scanner(new File("userIDs.txt"));
               
               while (s.hasNext())
               {
                   HotelCalifornia.userAccounts.add(new Guest(s.next()));
               }
               s.close();
            }
            catch (FileNotFoundException ex)
            {
               
            }

            boolean b = false;
            for (User s : HotelCalifornia.userAccounts)
            {
               if (s.userid.equals(loginID))
               {
                  b = true;
                  HotelCalifornia.currentUser = s;
               }
            }  
            if (b == true)
            {
                new ReservationsFrame(loginID);
                dispose();
            }
            if (loginID.equals("admin"))
            {
                 b = true;

                 try 
                 {
                     new ManagerCalendar(HotelCalifornia.manager);
                 } 
                 catch (ParseException ex) 
                 {
                 
                 }
               dispose();
            }
            if (b == false)
            {
               JOptionPane.showMessageDialog(null, "Invalid ID.");
            }
         }
      });
      
      b2.addActionListener(new ActionListener() 
      {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
            String registrationID = t2.getText();
            
            boolean isDuplicate = false;
            
            ArrayList<String> list = new ArrayList<String>();
            try
            {
               Scanner s = new Scanner(new File("userIDs.txt"));

               while (s.hasNext())
               {
                   list.add(s.next());
               }
               s.close();
            }
            catch(FileNotFoundException ex)
            {
               
            }
            
            for (String s : list)
            {
               if (s.equals(registrationID))
               {
                  isDuplicate = true;
               }
            }
            
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("userIDs.txt", true)))) 
            {
               if (registrationID.equals("admin") || registrationID.contains(" "))
               {
                  JOptionPane.showMessageDialog(null, "Invalid ID.");
               }
               if (isDuplicate == true)
               {
                  JOptionPane.showMessageDialog(null, "This ID already exists.");
               }  
               if (!registrationID.isEmpty() && !registrationID.equals("admin") && !registrationID.contains(" ") && isDuplicate == false)
               {
                  out.println(registrationID.trim());
                  JOptionPane.showMessageDialog(null, "Registered.");
                  
                  new LoginRegistration();
                  dispose();
               }
            }
            catch (IOException ex) 
            {
    
            }
            
         }
      });

      setLocationRelativeTo(null);
      setVisible(true);
   }
}
