import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Banandersnatch extends JFrame implements ActionListener {
    private static JPanel mainPanel = new JPanel();
    private static JPanel inputPanel = new JPanel();
    private static JPanel promptPanel = new JPanel();
    private static JPanel responsePanel = new JPanel();
    private static JPanel leftPanel = new JPanel();
    private static JPanel rightPanel = new JPanel();
    private static JPanel gameOverPanel = new JPanel();
    private static JLabel visualLabel = new JLabel();
    private static JLabel gameOverLabel = new JLabel("", SwingConstants.CENTER);
    private static JLabel gameOverTitle = new JLabel("GAME OVER", SwingConstants.CENTER);
    private static JLabel promptLabel = new JLabel("Welcome to Banandersnatch, the choose your own adventure game.", SwingConstants.CENTER);
    private static JLabel questionLabel = new JLabel("Please enter your name to begin", SwingConstants.CENTER);
    private static JTextField responseField = new JTextField(20);
    private static JButton submitButton = new JButton("Submit Response");

    private static String playerName;
    private static String response = "";
    private static String prompt = "";
    private static String question = "";
    private static boolean firstTime = true;
    private static int spot = 0;
    private static boolean hasBanana = false;

    public Banandersnatch() {
        super("Banandersnatched");
        setVisible(true);
        setSize(570, 800);
        setDefaultCloseOperation((WindowConstants.EXIT_ON_CLOSE));

        add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(visualLabel, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        leftPanel.setBackground(Color.BLACK);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        rightPanel.setBackground(Color.BLACK);
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(promptPanel, BorderLayout.NORTH);
        promptPanel.setLayout(new BorderLayout());
        promptPanel.add(promptLabel, BorderLayout.CENTER);
        promptPanel.add(questionLabel, BorderLayout.SOUTH);
        inputPanel.add(responsePanel, BorderLayout.SOUTH);
        responsePanel.add(responseField);
        responsePanel.add(submitButton);
        visualLabel.setIcon(new ImageIcon("images/cover.png"));
        mainPanel.setBackground(Color.BLACK);
        submitButton.addActionListener(this);


        validate();
    }



    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        Banandersnatch adventure = new Banandersnatch();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!responseField.getText().equals("")) {
            if (playerName == null) {
                if (responseField.getText().equalsIgnoreCase("APE")) {
                    playerName = "Marc";
                } else if (responseField.getText().equalsIgnoreCase("give mike an internship")) {
                    kingEnding();
                } else {
                    playerName = responseField.getText();
                }
                setTitle("Banandersnatch - " + playerName);
                responseField.setText("");
                spot++;
                firstEvent();
            } else {
                response = responseField.getText();
                responseField.setText("");
            }

            if (spot == 1 && !firstTime) {
                if (response.equalsIgnoreCase("run")) {
                    gameOver("You try and outrun the creature, but you make too much noise and alert the creature who chases you down and captures you.");
                } else if (response.equalsIgnoreCase("turn around")) {
                    spot += 1;
                    secondEvent();
                } else if (response.equalsIgnoreCase("climb tree")) {
                    spot += 100;
                    secondEventAlt();
                } else {
                    responseField.setText("Please enter a valid input");
                }
            } else if (spot == 2) {
                if (response.equalsIgnoreCase("run")) {
                    gameOver("You try and outrun the creature, but you the APE chases you down and captures you.");
                } else if (response.equalsIgnoreCase("fight")) {
                    spot += 1;
                    thirdEvent();
                } else {
                    responseField.setText("Please enter a valid input");
                }
            } else if (spot == 101) {
                if (response.equalsIgnoreCase("take banana")) {
                    spot = 123123;
                    getBanana();
                } else if (response.equalsIgnoreCase("stay quiet")) {
                    spot += 1;
                    thirdEventAlt();
                }
            } else if (spot == 3) {
                if (response.equalsIgnoreCase("follow")) {
                    spot = 50;
                    secondHalfIntro();
                } else if (response.equalsIgnoreCase("don't follow")) {
                    spot++;
                    thirdEventSneak();
                } else {
                    responseField.setText("Please enter a valid input");
                }
            } else if (spot == 4) {
                int num = Integer.parseInt(response);
                int answer = (int) (Math.random() * 10) + 1;
                if (num == answer) {
                    escapeEnding();
                } else {
                    gameOver("The APE's keen hearing detects your escape and is offended, he hunts you down and captures you");
                }
            } else if (spot == 123123) {
                if (response.equalsIgnoreCase("continue")) {
                    spot = 102;
                    thirdEventAlt();
                } else {
                    responseField.setText("Please enter a valid input");
                }
            } else if (spot == 102) {
                if (response.equalsIgnoreCase("run")) {
                    gameOver("You try and outrun the creature, but you the APE chases you down and captures you.");
                } else if (response.equalsIgnoreCase("fight")) {
                    spot = 3;
                    thirdEvent();
                } else {
                    responseField.setText("Please enter a valid input");
                }
            } else if (spot == 50) {
                if (response.equalsIgnoreCase("left")) {
                    spot += 1;
                    lookLeft();
                } else if (response.equalsIgnoreCase("right")) {
                    spot += 2;
                    lookRight();
                } else {
                    responseField.setText("Please enter a valid input");
                }
            } else if (spot == 51 || spot == 52) {
                if (response.equalsIgnoreCase("continue")) {
                    spot = 53;
                    meetKing();
                } else {
                    responseField.setText("Please enter a valid input");
                }
            } else if (spot == 53) {
                if (response.equalsIgnoreCase("bow")) {
                    spot = 54;
                    fightChampionApe();
                } else if (response.equalsIgnoreCase("try to communicate")) {
                    gameOver("You make APE noise in an attempt to communicate, but the king speaks perfect english and is greatly offended, you're sentenced to life in prison");
                } else {
                    responseField.setText("Please enter a valid input");
                }
            } else if (spot == 54) {
                if (response.equalsIgnoreCase("continue")) {
                    spot = 55;
                    actualFight();
                } else {
                    responseField.setText("Please enter a valid input");
                }
            } else if (spot == 55) {
                if (response.equalsIgnoreCase("rock")) {
                    gameOver("The ape chose paper, for failing the challenge you are sentenced to life in prison");
                } else if (response.equalsIgnoreCase("paper")) {
                    spot = 56;
                    winFight();
                } else if (response.equalsIgnoreCase("scissors")) {
                    gameOver("The ape chose rock, for failing the challenge you are sentenced to life in prison");
                } else {
                    responseField.setText("Please enter a valid input");
                }
            } else if (spot == 56) {
                if (response.equalsIgnoreCase("continue")) {
                    spot = 57;
                    kingOffer();
                } else {
                    responseField.setText("Please enter a valid input");
                }
            } else if (spot == 57) {
                if (response.equalsIgnoreCase("accept")) {
                    championEnding();
                } else if (response.equalsIgnoreCase("challenge king ape")) {
                    spot = 58;
                    challengeKing();
                } else if (response.equalsIgnoreCase("decline")) {
                    gameOver("The King APE is offended by your refusal, you have been sentenced to life in prison");
                } else {
                    responseField.setText("Please enter a valid input");
                }
            } else if (spot == 58) {
                if (response.equalsIgnoreCase("rock")) {
                    gameOver("The King APE chose paper, you are sentenced to life in prison");
                } else if (response.equalsIgnoreCase("paper")) {
                    gameOver("The King APE chose scissors, you are sentenced to life in prison");
                } else if (response.equalsIgnoreCase("scissors")) {
                    gameOver("The King APE chose rock, you are sentenced to life in prison");
                } else if (response.equalsIgnoreCase("banana") && hasBanana) {
                    spot = 1000;
                    beatKing();
                } else {
                    responseField.setText("Please enter a valid input");
                }
            } else if (spot == 1000) {
                if (response.equalsIgnoreCase("continue")) {
                    kingEnding();
                } else {
                    responseField.setText("Please enter a valid input");
                }
            } else if (firstTime) {
                firstTime = false;
            }
        } else {
            responseField.setText("Please enter a valid input");
        }
    }

    public void firstEvent() {
        try {
            URL url = new URL("http://blogs.discovermagazine.com/d-brief/files/2017/08/shutterstock_244338682.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("You're alone on an expedition in the Amazon Jungle and you hear a rustle behind you");
            questionLabel.setText("What do you do? (run, turn around, climb tree)");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void secondEvent() {
        try {
            URL url = new URL("https://c1.staticflickr.com/5/4075/4740022283_f1f2329b53_b.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("You turn around and see an enourmous APE staring down at you");
            questionLabel.setText("What do you do? (run, fight)");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void secondEventAlt() {
        try {
            URL url = new URL("https://images.fineartamerica.com/images/artworkimages/mediumlarge/2/you-big-ape-robert-frank-gabriel.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("You climb the tree, you see a massive ape below. There are also bananas in your tree");
            questionLabel.setText("What do you do? (take banana, stay quiet)");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void thirdEvent() {
        try {
            URL url = new URL("https://www.sciencedaily.com/images/2007/08/070802091437_1_900x600.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("The APE respects your fighting spirit and motions for you to follow");
            questionLabel.setText("What do you do? (follow, don't follow)");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getBanana() {
        try {
            URL url = new URL("https://images-na.ssl-images-amazon.com/images/I/71gI-IUNUkL._SY355_.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("You now have a banana");
            questionLabel.setText("Type 'continue' to continue");
            hasBanana = true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void thirdEventAlt() {
        try {
            URL url = new URL("https://c1.staticflickr.com/5/4075/4740022283_f1f2329b53_b.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("You climb down once you think it's safe, but turn to see an enourmous APE staring");
            questionLabel.setText(" down at you. What do you do? (run, fight)");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void thirdEventSneak() {
        promptLabel.setText("You try to sneak away while the APE's not looking");
        questionLabel.setText("Pick a number between 1 and 10 [inclusive]");
    }


    // SECOND HALF OF BANANDERSNATCH

    public void secondHalfIntro() {
        try {
            URL url = new URL("https://www.ancient-origins.net/sites/default/files/field/image/lost-city-of-gold.JPG");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("You follow the APE. He brings you to the lost city of APElantis. You take a look");
            questionLabel.setText("around. Which way do you look? (left, right)");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lookLeft() {
        try {
            URL url = new URL("http://rotocasted.com/static/mediafiles/tmpirUbaL.jpg.580x580_q85.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("You look left and see a 50ft gold statue of an ape wearing a crown. Looks like some");
            questionLabel.setText("sort of king. Type 'continue' to continue");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lookRight() {
        try {
            URL url = new URL("http://theculturalgutter.com/wp-content/uploads/2012/07/pota-magno.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("You look right and see thousands of APEs, marketplaces, and homes. It contains");
            questionLabel.setText("technology you can't even fathom. Type 'continue' to continue");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void meetKing() {
        try {
            URL url = new URL("https://res.cloudinary.com/teepublic/image/private/s--S3Net-__--/t_Preview/b_rgb:191919,c_limit,f_jpg,h_630,q_90,w_630/v1534188776/production/designs/3016141_0.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("You are brought into a palace of sorts. In it you are met with the King APE");
            questionLabel.setText("What do you do? (bow, try to communicate)");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fightChampionApe() {
        try {
            URL url = new URL("https://res.cloudinary.com/teepublic/image/private/s--S3Net-__--/t_Preview/b_rgb:191919,c_limit,f_jpg,h_630,q_90,w_630/v1534188776/production/designs/3016141_0.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("You bow before the King APE, who declares that you must prove yourself by fighting");
            questionLabel.setText("the APE Champion. Type 'continue' to continue");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void actualFight() {
        try {
            URL url = new URL("https://vignette.wikia.nocookie.net/planetoftheapes/images/f/fd/1396985361008-09-ss036-0180-v157-le1077.jpg/revision/latest?cb=20140409125015");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("The Champion APE comes out from the shadows, he is 15ft tall and RIPPED.");
            questionLabel.setText("Choose your weapon (rock, paper, scissors)");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void winFight() {
        try {
            URL url = new URL("https://res.cloudinary.com/teepublic/image/private/s--5cPQH0NH--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_jpg,h_630,q_90,w_630/v1503238736/production/designs/1837630_0.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("Your paper beats the Champion APE's rock, you have won!");
            questionLabel.setText("Type 'continue' to continue");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void kingOffer() {
        try {
            URL url = new URL("https://res.cloudinary.com/teepublic/image/private/s--S3Net-__--/t_Preview/b_rgb:191919,c_limit,f_jpg,h_630,q_90,w_630/v1534188776/production/designs/3016141_0.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("The King APE is impressed and offers to make you the new Champion APE");
            questionLabel.setText("What do you do? (accept, decline, challenge King APE)");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void challengeKing() {
        try {
            URL url = new URL("https://f4.bcbits.com/img/0006163982_10.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("The King APE accepts your challenge");
            if (hasBanana) {
                questionLabel.setText("Choose your weapon (rock, paper, scissors)");
            } else {
                questionLabel.setText("Choose your weapon (rock, paper, scissors)");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void beatKing() {
        try {
            URL url = new URL("https://f4.bcbits.com/img/0006163982_10.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            visualLabel.setIcon(icon);
            promptLabel.setText("You take out the banana as the King APE throws rock. All APEs are momentarily");
            questionLabel.setText("distracted as you switch your hand to rock, you win! Type 'continue' to continue");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void gameOver(String message) {
        setSize(1000, 200);
        remove(mainPanel);
        add(gameOverPanel);
        gameOverPanel.setLayout(new BorderLayout());
        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);
        gameOverPanel.add(gameOverTitle, BorderLayout.SOUTH);
        gameOverTitle.setFont(new Font("serif", Font.BOLD, 25));
        gameOverLabel.setText(message);
    }

    public void escapeEnding() {
        setSize(1000, 200);
        remove(mainPanel);
        add(gameOverPanel);
        gameOverPanel.setLayout(new BorderLayout());
        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);
        gameOverPanel.add(gameOverTitle, BorderLayout.SOUTH);
        gameOverTitle.setText("ENDING #1");
        gameOverLabel.setText("You successfully escaped the APE, but you feel as if you've missed a great adventure");
    }

    public void championEnding() {
        setSize(1000, 200);
        remove(mainPanel);
        add(gameOverPanel);
        gameOverPanel.setLayout(new BorderLayout());
        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);
        gameOverPanel.add(gameOverTitle, BorderLayout.SOUTH);
        gameOverTitle.setText("ENDING #2");
        gameOverLabel.setText("Congrats you are now the Champion APE!!");
    }

    public void kingEnding() {
        setSize(1000, 200);
        remove(mainPanel);
        add(gameOverPanel);
        gameOverPanel.setLayout(new BorderLayout());
        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);
        gameOverPanel.add(gameOverTitle, BorderLayout.SOUTH);
        gameOverTitle.setText("ENDING #3");
        gameOverLabel.setText("You have become the King APE. You are now a God among APEs");
    }
}
