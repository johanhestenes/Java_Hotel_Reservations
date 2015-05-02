import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class InfoForm extends JFrame
{  
   public InfoForm(final String currentUser) throws ParseException
   {
      super("Information Form");
      
      setSize(300, 200);
      setResizable(false);
      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      WindowListener exitListener = new WindowAdapter() 
      {
            @Override
            public void windowClosing(WindowEvent e) 
            {
                new ReservationsFrame(currentUser);
                dispose();
            }
      };
      addWindowListener(exitListener);
      
      JPanel panel = new JPanel();
      panel.setLayout(null);
      
      Date d1 = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
     
      JLabel l1 = new JLabel("Check-in:");
      JLabel l2 = new JLabel("Check-out:");
      JLabel l3 = new JLabel("Room type:");
      final JTextField t1 = new JTextField(sdf.format(d1));
      final JTextField t2 = new JTextField();
      
      JButton b1 = new JButton("$200");
      JButton b2 = new JButton("$80");
      
      l1.setBounds(10, 10, 60, 20);
      panel.add(l1);
      l2.setBounds(170, 10, 65, 20);
      panel.add(l2);
      l3.setBounds(10, 100, 65, 20);
      panel.add(l3);
      t1.setBounds(10, 40, 100, 20);
      panel.add(t1);
      t2.setBounds(170, 40, 100, 20);
      panel.add(t2);
      b1.setBounds(80, 100, 90, 30);
      panel.add(b1);
      b2.setBounds(190, 100, 90, 30);
      panel.add(b2);
      
      b1.addActionListener(new ActionListener() 
      {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
            boolean valid1 = false;
            boolean valid2 = false;

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            df.setLenient(false);

            Date today = null;
            try 
            {
               today = df.parse(df.format(new Date()));
            } 
            catch (ParseException ex) 
            {

            }

            Date checkIn = new Date();
            try
            {
               checkIn = df.parse(t1.getText());
               if (checkIn.after(today) || checkIn.equals(today))
               {
                  valid1 = true;
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "Invalid check-in date.");
               }
            }
            catch(ParseException pe)
            {
               JOptionPane.showMessageDialog(null, "Invalid check-in date.");
            }


            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(checkIn);
            cal.add(Calendar.DATE, 61);
            String output = df.format(cal.getTime());
            Date sixtyFromCheckIn = null;
            try 
            {
               sixtyFromCheckIn = df.parse(output);
            } 
            catch (ParseException ex) 
            {
               
            }

            Date checkOut = new Date();
            try
            {
               checkOut = df.parse(t2.getText());

               if (checkOut.after(checkIn) && checkOut.before(sixtyFromCheckIn))
               {
                  valid2 = true;
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "Invalid check-out date.");
               }
            }
            catch(ParseException pe)
            {
               JOptionPane.showMessageDialog(null, "Invalid check-out date.");
            }
            
            if (valid1== true && valid2 == true)
            {
                 try 
                 {
                     new LuxuriousRoomsFrame(currentUser, t1.getText(), t2.getText());
                 } 
                 catch (ParseException ex) 
                 {
                     
                 }
               dispose();
            }
         }
      });

      b2.addActionListener(new ActionListener() 
      {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
            boolean valid1 = false;
            boolean valid2 = false;

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            df.setLenient(false);

            Date today = null;
            try 
            {
               today = df.parse(df.format(new Date()));
            } 
            catch (ParseException ex) 
            {

            }

            Date checkIn = new Date();
            try
            {
               checkIn = df.parse(t1.getText());
               if (checkIn.after(today) || checkIn.equals(today))
               {
                  valid1 = true;
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "Invalid check-in date.");
               }
            }
            catch(ParseException pe)
            {
               JOptionPane.showMessageDialog(null, "Invalid check-in date.");
            }


            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(checkIn);
            cal.add(Calendar.DATE, 61);
            String output = df.format(cal.getTime());
            Date sixtyFromCheckIn = null;
            try 
            {
               sixtyFromCheckIn = df.parse(output);
            } 
            catch (ParseException ex) 
            {
               
            }

            Date checkOut = new Date();
            try
            {
               checkOut = df.parse(t2.getText());

               if (checkOut.after(checkIn) && checkOut.before(sixtyFromCheckIn))
               {
                  valid2 = true;
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "Invalid check-out date.");
               }
            }
            catch(ParseException pe)
            {
               JOptionPane.showMessageDialog(null, "Invalid check-out date.");
            }
            
            if (valid1 == true && valid2 == true)
            {
                 try 
                 {
                     new EconomicRoomsFrame(currentUser, t1.getText(), t2.getText());
                 } 
                 catch (ParseException ex) 
                 {
                    
                 }
               dispose();
            }
         }
      });
      
      this.add(panel);
      
      setLocationRelativeTo(null);
      setVisible(true);
   }
}
