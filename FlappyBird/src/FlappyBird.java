import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    Image backgroundImage;
    Image birdImage;
    Image lowePipeImage;
    Image upperPipeImage;

    int playerStartPosX = frameHeight / 8;
    int playerStartPosY = frameHeight / 8;
    int playerWidth = 34;
    int playerHeight = 24;

    Player player;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;

    boolean isGameStarted = true; // Game langsung jalan
    boolean isGameOver = false;

    JLabel scoreLabel;
    int score = 0;

    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);
        setLayout(null);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(10, 10, 200, 30);
        this.add(scoreLabel);

        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getClassLoader().getResource("assets/bird.png")).getImage();
        lowePipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<>();

        pipesCooldown = new Timer(3000, e -> placePipes());
        pipesCooldown.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public void placePipes() {
        int randomPosY = (int) (-pipeHeight / 2 - Math.random() * (pipeHeight / 2));
        int openingSpace = 150;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowePipeImage);
        pipes.add(lowerPipe);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (Pipe pipe : pipes) {
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        if (isGameOver) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(0, 0, 0, 180)); // hitam transparan
            int boxWidth = 280;
            int boxHeight = 140;
            int boxX = (frameWidth - boxWidth) / 2;
            int boxY = (frameHeight - boxHeight) / 2;
            g2d.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 20, 20);

            // Font & warna
            g.setColor(Color.WHITE);

            // Tulisan "Game Over!"
            g.setFont(new Font("Arial", Font.BOLD, 24));
            String gameOverText = "Game Over!";
            FontMetrics fm = g.getFontMetrics();
            int textX = boxX + (boxWidth - fm.stringWidth(gameOverText)) / 2;
            g.drawString(gameOverText, textX, boxY + 40);

            // Tulisan skor
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            String scoreText = "Skor Akhir: " + score;
            fm = g.getFontMetrics();
            textX = boxX + (boxWidth - fm.stringWidth(scoreText)) / 2;
            g.drawString(scoreText, textX, boxY + 75);

            // Tulisan instruksi restart
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            String restartText = "Tekan R untuk memulai kembali";
            fm = g.getFontMetrics();
            textX = boxX + (boxWidth - fm.stringWidth(restartText)) / 2;
            g.drawString(restartText, textX, boxY + 105);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void move() {
        if (isGameOver) return;

        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + (int)(player.getVelocityY() * 0.85));
        player.setPosY(Math.max(player.getPosY(), 0));

        for (Pipe pipe : pipes) {
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());
        }

        if (player.getPosY() > frameHeight - playerHeight) {
            isGameOver = true;
            gameLoop.stop();
            pipesCooldown.stop();
        }

        Rectangle playerBounds = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
        for (Pipe pipe : pipes) {
            Rectangle pipeBounds = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
            if (playerBounds.intersects(pipeBounds)) {
                isGameOver = true;
                gameLoop.stop();
                pipesCooldown.stop();
            }
        }

        for (Pipe pipe : pipes) {
            if (!pipe.isPassed() && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                pipe.setPassed(true);
                if (pipes.indexOf(pipe) % 2 == 0) {
                    score++;
                    scoreLabel.setText("Score: " + score);
                }
            }
        }
    }

    public void restartGame() {
        isGameOver = false;
        isGameStarted = true;

        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);
        pipes.clear();
        score = 0;
        scoreLabel.setText("Score: 0");

        gameLoop.start();
        pipesCooldown.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && isGameStarted && !isGameOver) {
            player.setVelocityY(-10);
        }

        if (e.getKeyCode() == KeyEvent.VK_R && isGameOver) {
            restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
