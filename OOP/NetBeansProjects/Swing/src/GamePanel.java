
import java.awt.Graphics;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sean-
 */
public class GamePanel extends JPanel {
    public void paint(Graphics g) {
        
        super.paint(g);
        Test test = new Test();
        test.paint(g);
    }
}
