import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationsFrame extends JFrame
{
   public ReservationsFrame(final String currentUser)
   {
      super("Reservations");
      
      setSize(380, 100);
      setResizable(false);
      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      WindowListener exitListener = new WindowAdapter() 
      {
            @Override
            public void windowClosing(WindowEvent e) 
            {
            try 
            {
               new LoginRegistration();
            } 
            catch (FileNotFoundException ex) 
            {
               
            }
                dispose();
            }
      };
      addWindowListener(exitListener);
      
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout());
      JButton b1 = new JButton("Make a Reservation");
      JButton b2 = new JButton("View/Cancel a Reservation");
      
      panel.add(b1);
      panel.add(b2);
      
      this.add(panel);
      
      b1.addActionListener(new ActionListener() 
      {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
              try 
              {
                  new InfoForm(currentUser);
              } 
              catch (ParseException ex) 
              {
                 
              }
            dispose();
         }
      });
      
      b2.addActionListener(new ActionListener() 
      {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
            new ViewCancelFrame(currentUser);
            dispose();
         }
      });
      
      setLocationRelativeTo(null);
      setVisible(true);
   }
}
