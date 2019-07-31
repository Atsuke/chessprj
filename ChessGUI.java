package chessprj;

import java.awt.Dimension;

import javax.swing.JFrame;

/**********************************************************************
 * GUI in which the Chess game is able to operate
 *
 * @author Mike Day and Gretchen Holzhauer
 * @version July 30 2019
 **********************************************************************/

public class ChessGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChessPanel panel = new ChessPanel();
        frame.getContentPane().add(panel);

        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(800, 637));
        frame.pack();
        frame.setVisible(true);

    }
}