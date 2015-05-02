import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import javax.swing.*;

public class EconomicRoomsFrame extends JFrame
{
   static Guest current = new Guest();
   public EconomicRoomsFrame(final String currentUser, final String checkIn, final String checkOut) throws ParseException
   {
      super("Available Economic Rooms");
      
     for(int i = 0;i<HotelCalifornia.userAccounts.size();i++)
      {
          if(HotelCalifornia.userAccounts.get(i).userid.equals(currentUser))
          {
              current = (Guest) HotelCalifornia.userAccounts.get(i);
              break;
          }
      }
      
      setSize(400, 280);
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
      
      Integer economicArray[] = new Integer[10];

      Calendar start = HotelCalifornia.manager.stringToDate(checkIn);
      Calendar end = HotelCalifornia.manager.stringToDate(checkOut);

      for (int i = 0; i < 10; i++)
      {
          
          if (!HotelCalifornia.manager.isBooked(HotelCalifornia.rooms[i], start, end))
          {
              economicArray[i] = i;
          }
      }
      
      final JList<Integer> jlist = new JList<>(economicArray);

      jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      jlist.setBounds(10, 60, 150, 180);
      
      JButton b1 = new JButton("Confirm");
      JButton b2 = new JButton("Transaction Done");
      
      JLabel l1 = new JLabel("Available Economic Rooms");
      JLabel l2 = new JLabel("Select a room number:");
      
      b1.setBounds(165, 200, 80, 40);
      b2.setBounds(250, 200, 135, 40);
      
      l1.setBounds(10, 10, 170, 20);
      l2.setBounds(10, 40, 170, 20);
      
      panel.add(l1);
      panel.add(l2);
      panel.add(b1);
      panel.add(b2);
      panel.add(jlist);
      
      this.add(panel);
      
      b1.addActionListener(new ActionListener() 
      {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
             if(jlist.getSelectedIndex()>=0)
             {
                JOptionPane.showMessageDialog(null, "Confirmed for " + currentUser + " " + checkIn + " - " + checkOut + " room#: " + (jlist.getSelectedIndex()));
                Object[] options = {"Yes", "No"};
                int choice = JOptionPane.showOptionDialog(null, "Would you like to make additional reservations?", 
                        "Message", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                
                if (choice == 0)
                {
                     HotelCalifornia.tempRooms.add(jlist.getSelectedIndex());
                     try 
                     {
                         current.addEvent(checkIn, checkOut, jlist.getSelectedIndex());
                     } 
                     catch (ParseException ex) 
                     {
                        
                     }
                     
                     Manager m = new Manager();
                     try 
                     {
                         m.save();
                     } 
                     catch (IOException ex) 
                     {
                        
                     } 
                   
                     try 
                     {
                         new InfoForm(currentUser);
                         dispose();
                     } 
                     catch (ParseException ex) 
                     {
                        
                     }
                }
                else
                {
                     HotelCalifornia.tempRooms.add(jlist.getSelectedIndex());
                     try 
                     {
                         current.addEvent(checkIn, checkOut, jlist.getSelectedIndex());
                     } 
                     catch (ParseException ex) 
                     {
                        
                     }
                     
                     Manager m = new Manager();
                     try
                     {
                         m.save();
                     } 
                     catch (IOException ex) 
                     {

                     } 
                                     
                     try
                     {
                         new EconomicRoomsFrame(currentUser, checkIn, checkOut);
                     } 
                     catch (ParseException ex) 
                     {
                        
                     }
                     dispose();
                }
             }
         }
      });
      
      b2.addActionListener(new ActionListener() 
      {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
            dispose();
            new ReceiptDialog(currentUser);
         }
      });
      
      setLocationRelativeTo(null);
      setVisible(true);
   }
}
