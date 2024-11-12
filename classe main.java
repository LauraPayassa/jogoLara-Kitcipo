import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 750;
        int boardHeight = 250;

    
        JFrame frame = new JFrame("Pikachu Game");

       
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        PikachuDinossauro pikachuDinossauro = new PikachuDinossauro();
        frame.add(pikachuDinossauro);
        frame.pack();
        frame.setVisible(true);
    }
}
