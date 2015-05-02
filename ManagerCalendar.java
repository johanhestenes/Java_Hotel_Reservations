import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public final class ManagerCalendar 
{
    GregorianCalendar today = new GregorianCalendar();
    Manager m;
    public static String sDisplayDate = "00/00/0000";
    JPanel cal;
    JTextArea area = new JTextArea(25, 27);
    JTextArea area2 = new JTextArea(25,27);
    JFrame frame;
    
    public ManagerCalendar(Manager m) throws ParseException 
    {
        this.m = m;
        cal = mCalendar();
        JScrollPane eventDisplay = eventDisplay();
        frame = new JFrame();
        frame.add(calendarShift(), BorderLayout.NORTH);
        frame.add(cal, BorderLayout.WEST);
        frame.add(eventDisplay(), BorderLayout.CENTER);
        frame.add(roomsDisplay(), BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
               frame.dispose();
            }
        };
        frame.addWindowListener(exitListener);
        frame.pack();
        frame.setVisible(true);
    }

    public JScrollPane eventDisplay() throws ParseException 
    {
        area2.setText("Select date to display booked/available rooms");
        JScrollPane textArea = new JScrollPane(area2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return textArea;
    }
    
    public JScrollPane roomsDisplay() throws ParseException
    {
        JScrollPane textArea = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return textArea;
    }

    public void setDisplayDate(String d) throws ParseException 
    {
        sDisplayDate = d;
        area.setText("Available Rooms on " + sDisplayDate +" : "+ "\n" + m.displayRoomsOnDay(sDisplayDate));
        area2.setText("Bookings on " + sDisplayDate + " : " + "\n" + m.displayEventsOnDay(sDisplayDate));
    }
    
    public JPanel calendarShift()
    {
        JPanel cshift = new JPanel();
        String[] mList = new String[] {"January", "February", "March", "April", "May", "June","July","August","September","October","November", "December"};
        JComboBox<String> monthList = new JComboBox<>(mList);
        monthList.setSelectedIndex(today.get(Calendar.MONTH));
        monthList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
                JComboBox monthList = (JComboBox) event.getSource();
                today.set(Calendar.MONTH, monthList.getSelectedIndex());
                frame.getContentPane().remove(cal);
                cal = mCalendar();
                frame.add(cal, BorderLayout.WEST);
                frame.revalidate();
            }
        });
        
        String[] yList = new String[] {"2010","2011","2012","2013","2014","2015","2016","2017"};
        JComboBox<String> yearList = new JComboBox<>(yList);
        yearList.setSelectedItem("2014");
        yearList.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JComboBox yearList = (JComboBox) event.getSource();
                today.set(Calendar.YEAR, Integer.valueOf((String) yearList.getSelectedItem()));
                frame.getContentPane().remove(cal);
                cal = mCalendar();
                frame.add(cal, BorderLayout.WEST);
                frame.revalidate();
            }
        });
        cshift.setLayout(new FlowLayout(FlowLayout.LEFT));
        cshift.add(monthList);
        cshift.add(yearList);
        return cshift;
    }

    public JPanel mCalendar() 
    {
        int k = 1;
        JPanel panel = new JPanel();
        JButton[][] b = new JButton[7][7];
        panel.setLayout(new GridLayout(7, 7));
      

        for (int row = 0; row < 7; row++) 
        {
            for (int col = 0; col < 7; col++) 
            {
                b[row][col] = new JButton();
                panel.add(b[row][col]);
            }
        }
        int gDay = today.get(Calendar.DAY_OF_WEEK);

        String[] wDay = new String[]{"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
        for (int col = 0; col < 7; col++) 
        {
            b[0][col].setText(wDay[col]);
        }
        int space = 0;

        for (int i = 0; i < gDay ; i++) 
        {
            space++;
        }

        for (int c1 = space; c1 < 7; c1++) 
        {
            b[1][c1].setText(String.valueOf(k));
            b[1][c1].addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    ManagerCalendar.sDisplayDate = ((JButton) e.getSource()).getText();
                    String month = String.valueOf(today.get(Calendar.MONTH) + 1);
                    String year = String.valueOf(today.get(Calendar.YEAR));
                    if (Integer.valueOf(sDisplayDate) < 10) 
                    {
                        sDisplayDate = "0" + sDisplayDate;
                    }
                    if (Integer.valueOf(month) < 10) 
                    {
                        month = "0" + month;
                    }
                    String date = month + "/" + sDisplayDate + "/" + year;
                    try 
                    {
                        setDisplayDate(date);
                    } 
                    catch (ParseException ex) 
                    {
                        Logger.getLogger(ManagerCalendar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            k++;
        }

        outerloop:
        for (int row = 2; row < 7; row++) 
        {
            for (int col = 0; col < 7; col++) 
            {
                if (k > today.getActualMaximum(Calendar.DAY_OF_MONTH)) 
                {
                    break outerloop;
                } 
                else
                {
                    b[row][col].setText(String.valueOf(k));
                    b[row][col].addActionListener(new ActionListener() 
                    {
                        public void actionPerformed(ActionEvent e) 
                        {
                            ManagerCalendar.sDisplayDate = ((JButton) e.getSource()).getText();
                            String month = String.valueOf(today.get(Calendar.MONTH) + 1);
                            String year = String.valueOf(today.get(Calendar.YEAR));
                            if (Integer.valueOf(sDisplayDate) < 10) {
                                sDisplayDate = "0" + sDisplayDate;
                            }
                            if (Integer.valueOf(month) < 10) {
                                month = "0" + month;
                            }
                            String date = month + "/" + sDisplayDate + "/" + year;
                            try 
                            {
                                setDisplayDate(date);
                            } 
                            catch (ParseException ex) 
                            {
                                Logger.getLogger(ManagerCalendar.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    k++;
                }
            }
        }
        return panel;
    }
}
