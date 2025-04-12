import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Main Menu");
        setSize(360, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackgroundPanel panel = new BackgroundPanel();
        panel.setLayout(null);

        // Tombol Start Game
        JButton startButton = new JButton("Start Game");
        int buttonWidth = 150;
        int buttonHeight = 50;
        int buttonX = (360 - buttonWidth) / 2; // posisi tengah horizontal
        int buttonY = 280;
        startButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        startButton.setFocusable(false);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setForeground(Color.WHITE);


        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Tutup menu utama
                JFrame frame = new JFrame("Flappy Bird");
                FlappyBird game = new FlappyBird();
                frame.add(game);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        panel.add(startButton);

        // Label pembuat di bawah
        JLabel creditLabel = new JLabel("by Kamu 😎");
        creditLabel.setBounds(0, 580, 360, 30);
        creditLabel.setHorizontalAlignment(SwingConstants.CENTER);
        creditLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        creditLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(creditLabel);

        setContentPane(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = new ImageIcon(getClass().getResource("/assets/background.png")).getImage();
            } catch (Exception e) {
                System.out.println("Gagal load background: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            } else {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
            // Gambar teks dengan shadow
            String title = "Flappy Bird";
            Font font = new Font("Arial", Font.BOLD, 36);
            g2d.setFont(font);
            FontMetrics fm = g2d.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(title)) / 2;
            int y = 120;

            // Shadow
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawString(title, x + 2, y + 2);

            // Teks utama
            g2d.setColor(Color.YELLOW);
            g2d.drawString(title, x, y);
        }
    }
}
