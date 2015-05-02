import java.awt.TextArea;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ReceiptDisplay extends JFrame
{
    public ReceiptDisplay(String input, String currentUser)
    {
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        Receipt receipt;

        if (input.equals("simple"))
        {
            receipt = new Receipt(new SimpleReceipt());
        }   

        else
        {
            receipt = new Receipt(new ComprehensiveReceipt());
        }

        TextArea t1 = new TextArea(receipt.executeStrategy(currentUser));
        t1.setBounds(0, 0, 495, 473);
        panel.add(t1);
        this.add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
