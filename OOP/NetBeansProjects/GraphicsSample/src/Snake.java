
import java.util.LinkedList;
import java.util.List;
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
public class Snake extends JPanel {
    //private int size;
    //private List<Integer> x = new LinkedList();
    //private List<Integer> y = new LinkedList();
    List<Node> nodes = new LinkedList();
    private boolean moving = false;
    
    public void addNode() {
        nodes.add(new Node(10, 10));
    }
    
    public boolean getMoving() {
        return moving;
    }
    
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    
}
