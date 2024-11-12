import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Pikachu Dino Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        
        pack();
        setLocationRelativeTo(null); 
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new MainFrame();
    }
}
