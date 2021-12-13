package thionline.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
class Countdown extends JFrame
{
  public Countdown()
  {
    setLocation(400,300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    final long THIRTY_MINUTES = 6000;
    final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm : ss");
    final JLabel clock = new JLabel(sdf.format(new Date(THIRTY_MINUTES)),JLabel.CENTER);
    int x = 0;
    ActionListener al = new ActionListener(){
      long x = THIRTY_MINUTES - 1000;
      public void actionPerformed(ActionEvent ae){
        clock.setText(sdf.format(new Date(x)));
        x -= 1000;
        if (x < 0) {
        	((Timer)ae.getSource()).stop();
        	JOptionPane.showMessageDialog(null, "het gio");
        }
       }
     };
    new javax.swing.Timer(1000, al).start();
    JPanel jp = new JPanel();
    jp.add(clock);
    getContentPane().add(jp);
    pack();
  }
  public static void main(String args[]){new Countdown().setVisible(true);}
}