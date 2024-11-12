import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private int boardWidth = 750;
    private int boardHeight = 250;

    // Pikachu e Obstáculos
    private Block pikachu;
    private ArrayList<Block> obstacles;

    // Física do jogo
    private int velocityX = -12;
    private int velocityY = 0;
    private int gravity = 1;
    
    private boolean gameOver = false;
    private int score = 0;

    private Timer gameLoop;
    private Timer placeObstacleTimer;

    // Imagens
    private Image pikachuRunImg, pikachuDeadImg, pikachuJumpImg, ekansImg, caliopeGengarImg;

    public GamePanel() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.lightGray);
        setFocusable(true);
        addKeyListener(this);

        loadImages();
        initializeGame();
        
        // Configura o timer do jogo
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        // Timer para gerar obstáculos
        placeObstacleTimer = new Timer(1500, e -> placeObstacle());
        placeObstacleTimer.start();
    }

    private void loadImages() {
        pikachuRunImg = new ImageIcon("src/imagens/pikachurun.gif").getImage();
        pikachuDeadImg = new ImageIcon("src/imagens/pikachumorto.png").getImage();
        pikachuJumpImg = new ImageIcon("src/imagens/pikachujump.png").getImage();
        ekansImg = new ImageIcon("src/imagens/ekans.png").getImage();
        caliopeGengarImg = new ImageIcon("src/imagens/calipegengar.png").getImage();
    }

    private void initializeGame() {
        pikachu = new Block(50, boardHeight - 50, 60, 50, pikachuRunImg);
        obstacles = new ArrayList<>();
    }

    private void placeObstacle() {
        if (gameOver) return;

        double chance = Math.random();
        if (chance > 0.5) {
            obstacles.add(new Block(700, boardHeight - 50, 65, 50, ekansImg));
        } else {
            obstacles.add(new Block(700, boardHeight - 50, 70, 50, caliopeGengarImg));
        }

        if (obstacles.size() > 10) {
            obstacles.remove(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.drawImage(pikachu.img, pikachu.x, pikachu.y, pikachu.width, pikachu.height, null);

        for (Block obstacle : obstacles) {
            g.drawImage(obstacle.img, obstacle.x, obstacle.y, obstacle.width, obstacle.height, null);
        }

        g.setColor(Color.black);
        g.setFont(new Font("Courier", Font.PLAIN, 32));
        g.drawString(gameOver ? "Game Over: " + score : String.valueOf(score), 10, 35);
    }

    private void move() {
        velocityY += gravity;
        pikachu.y += velocityY;

        if (pikachu.y > boardHeight - pikachu.height) {
            pikachu.y = boardHeight - pikachu.height;
            velocityY = 0;
            pikachu.img = pikachuRunImg;
        }

        for (Block obstacle : obstacles) {
            obstacle.x += velocityX;
            if (collision(pikachu, obstacle)) {
                gameOver = true;
                pikachu.img = pikachuDeadImg;
            }
        }

        if (!gameOver) score++;
    }

    private boolean collision(Block a, Block b) {
        return a.x < b.x + b.width && a.x + a.width > b.x &&
               a.y < b.y + b.height && a.y + a.height > b.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            placeObstacleTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (pikachu.y == boardHeight - pikachu.height) {
                velocityY = -17;
                pikachu.img = pikachuJumpImg;
            }
            if (gameOver) {
                initializeGame();
                score = 0;
                gameOver = false;
                gameLoop.start();
                placeObstacleTimer.start();
            }
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
