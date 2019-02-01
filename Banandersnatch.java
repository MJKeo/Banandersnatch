import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

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
                playerName = responseField.getText();
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
        visualLabel.setIcon(new ImageIcon("images/jungle.jpg"));
        promptLabel.setText("You're alone on an expedition in the Amazon Jungle and you hear a rustle behind you");
        questionLabel.setText("What do you do? (run, turn around, climb tree)");
    }

    public void secondEvent() {
        visualLabel.setIcon(new ImageIcon("images/bigApe.jpg"));
        promptLabel.setText("You turn around and see an enourmous APE staring down at you");
        questionLabel.setText("What do you do? (run, fight)");
    }

    public void secondEventAlt() {
        visualLabel.setIcon(new ImageIcon("images/apeBelow.jpg"));
        promptLabel.setText("You climb the tree, you see a massive ape below. There are also bananas in your tree");
        questionLabel.setText("What do you do? (take banana, stay quiet)");
    }

    public void thirdEvent() {
        visualLabel.setIcon(new ImageIcon("images/happy.jpg"));
        promptLabel.setText("The APE respects your fighting spirit and motions for you to follow");
        questionLabel.setText("What do you do? (follow, don't follow)");
    }

    public void getBanana() {
        visualLabel.setIcon(new ImageIcon("images/banana.jpg"));
        promptLabel.setText("You now have a banana");
        questionLabel.setText("Type 'continue' to continue");
        hasBanana = true;
    }

    public void thirdEventAlt() {
        visualLabel.setIcon(new ImageIcon("images/bigApe.jpg"));
        promptLabel.setText("You climb down once you think it's safe, but turn to see an enourmous APE staring");
        questionLabel.setText(" down at you. What do you do? (run, fight)");
    }

    public void thirdEventSneak() {
        promptLabel.setText("You try to sneak away while the APE's not looking");
        questionLabel.setText("Pick a number between 1 and 10 [inclusive]");
    }


    // SECOND HALF OF BANANDERSNATCH

    public void secondHalfIntro() {
        visualLabel.setIcon(new ImageIcon("images/apelantis.jpg"));
        promptLabel.setText("You follow the APE. He brings you to the lost city of APElantis. You take a look");
        questionLabel.setText("around. Which way do you look? (left, right)");
    }

    public void lookLeft() {
        visualLabel.setIcon(new ImageIcon("images/statue.jpg"));
        promptLabel.setText("You look left and see a 50ft gold statue of an ape wearing a crown. Looks like some");
        questionLabel.setText("sort of king. Type 'continue' to continue");
    }

    public void lookRight() {
        visualLabel.setIcon(new ImageIcon("images/apeCity.jpg"));
        promptLabel.setText("You look right and see thousands of APEs, marketplaces, and homes. It contains");
        questionLabel.setText("technology you can't even fathom. Type 'continue' to continue");
    }

    public void meetKing() {
        visualLabel.setIcon(new ImageIcon("images/kingApe.jpg"));
        promptLabel.setText("You are brought into a palace of sorts. In it you are met with the King APE");
        questionLabel.setText("What do you do? (bow, try to communicate)");
    }

    public void fightChampionApe() {
        promptLabel.setText("You bow before the King APE, who declares that you must prove yourself by fighting");
        questionLabel.setText("the APE Champion. Type 'continue' to continue");
    }

    public void actualFight() {
        visualLabel.setIcon(new ImageIcon("images/championApe.jpg"));
        promptLabel.setText("The Champion APE comes out from the shadows, he is 15ft tall and RIPPED.");
        questionLabel.setText("Choose your weapon (rock, paper, scissors)");
    }

    public void winFight() {
        visualLabel.setIcon(new ImageIcon("images/paper.jpg"));
        promptLabel.setText("Your paper beats the Champion APE's rock, you have won!");
        questionLabel.setText("Type 'continue' to continue");
    }

    public void kingOffer() {
        visualLabel.setIcon(new ImageIcon("images/kingApe.jpg"));
        promptLabel.setText("The King APE is impressed and offers to make you the new Champion APE");
        questionLabel.setText("What do you do? (accept, decline, challenge King APE)");
    }

    public void challengeKing() {
        visualLabel.setIcon(new ImageIcon("images/challengeKing.jpg"));
        promptLabel.setText("The King APE accepts your challenge");
        if (hasBanana) {
            questionLabel.setText("Choose your weapon (rock, paper, scissors, banana)");
        } else {
            questionLabel.setText("Choose your weapon (rock, paper, scissors)");
        }
    }

    public void beatKing() {
        promptLabel.setText("You take out the banana as the King APE throws rock. All APEs are momentarily");
        questionLabel.setText("distracted as you switch your hand to rock, you win! Type 'continue' to continue");
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
