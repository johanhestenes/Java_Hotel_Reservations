import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ViewCancelFrame extends JFrame
{
     static int eventindex = -1;
     static Guest current = new Guest();
     @SuppressWarnings("empty-statement")
     public ViewCancelFrame(final String currentUser)
     {
         super("View/Cancel Reservations");

         for(int i = 0; i < HotelCalifornia.userAccounts.size(); i++)
         {
             if (HotelCalifornia.userAccounts.get(i).userid.equals(currentUser))
             {
                 current = (Guest) HotelCalifornia.userAccounts.get(i);
                 break;
             }
         }

         setSize(400, 400);
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
         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         String[] list = new String[current.events.size()];
         String[] emp = {};
         if (current.events.size() == 0)
         {
             list =emp;
         }


         for (int i = 0; i<current.events.size();i++)   
         {
             list[i] = current.events.get(i).toString();
         }
         JScrollPane scrollPane = new JScrollPane();

         final JList jlist = new JList(list);
         jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         jlist.setBounds(10, 30, 375, 250);   
         
         
         
         jlist.addListSelectionListener(new ListSelectionListener()
         {
             @Override
             public void valueChanged(ListSelectionEvent e) 
             {
                 eventindex = jlist.getSelectedIndex();
             }
         });

         JButton b1 = new JButton("Cancel Selected Reservation");  
         b1.addActionListener(new ActionListener()
         {
             @Override
             public void actionPerformed(ActionEvent e) 
             {
                 if(current.events.size()==0)
                 {

                 }
                 else
                 {
                   current.deleteEvent(current.events.get(eventindex));
                   try 
                   {
                      HotelCalifornia.manager.save();
                   } 
                   catch (IOException ex) 
                   {
                      Logger.getLogger(ViewCancelFrame.class.getName()).log(Level.SEVERE, null, ex);
                   } 
                   new ViewCancelFrame(current.userid);
                   dispose();
                 }
             }
         });
         JLabel l1 = new JLabel("Select a reservation:");

         b1.setBounds(100, 305, 200, 40);

         l1.setBounds(10, 10, 170, 20);

         scrollPane.setViewportView(jlist);
         
         panel.add(l1);
         panel.add(scrollPane);
         panel.add(b1);

         this.add(panel);

         setLocationRelativeTo(null);
         setVisible(true);
     }
}
