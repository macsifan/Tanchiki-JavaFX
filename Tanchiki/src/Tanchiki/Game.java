package Tanchiki;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;

import static Tanchiki.Display.addCss;
import static Tanchiki.Display.restart;
import static Tanchiki.Music.changeMusic;
import static Tanchiki.Statistics.*;

public class Game {

    //key code which we press
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    //Blocks for level
    public static ArrayList<Block> platforms = new ArrayList<>();

    //Bullet objects for 2 players
    public static Bullet bulletP1;
    public static Bullet bulletP2;

    //Size of Block and Characters
    public static final int BLOCK_SIZE = 60;
    public static final int CHARACTER_SIZE = 50;

    //Image with all textures
    static Image textures = new Image("textures.jpg");

    //Create object of Characters class (first player)
    static ImageView imageViewP1 = new ImageView(textures);
    public static Characters player1 = new Characters(imageViewP1, 0, 0, 50, 50);

    //Create object of Characters class (second player)
    static ImageView imageViewP2 = new ImageView(textures);
    public static Characters player2 = new Characters(imageViewP2, 50, 50, 50, 50);

    //last pressed key to determine the direction of bullet
    char lastPressedKeyP1 = 'w';
    char lastPressedKeyP2 = 'd';

    //boolean for time between bullets (3000ms)
    public static boolean counterP1 = true;
    public static boolean counterP2 = true;


    //Main Pane
    public static Pane appRoot = new Pane();
    //Pain with block,bullets and players
    public static Pane gameRoot = new Pane();
    //int to create level and location of players
    public static int MapLevel;

    //method which create map
    public void createMap(int level) {
        //set Location for players
        setCooridinates(3, player1Coordinates, player2Coordinates);
        //Create MAP
        for (int i = 0; i < LevelData.levels[level - 1].length; i++) {
            String line = LevelData.levels[level - 1][i];
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case '0':
                        break;
                    case '1':
                        Block wall = new Block(Block.BlockType.WALL, j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case '2':
                        Block beton = new Block(Block.BlockType.BETON, j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case '3':
                        Block eagle = new Block(Block.BlockType.EAGLEP1, j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case '4':
                        Block eagleP1 = new Block(Block.BlockType.EAGLEP2, j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case '*':
                        Block zone = new Block(Block.BlockType.ZONE, j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;

                }
            }
        }
    }

    //method which add image of Grass
    public void addGrass(int level) {
        for (int i = 0; i < LevelData.levels[level - 1].length; i++) {
            String line = LevelData.levels[level - 1][i];
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case ('G'):
                        Image grassImage = new Image("Grass.png");
                        ImageView grass = new ImageView(grassImage);
                        grass.setTranslateX(j * BLOCK_SIZE);
                        grass.setTranslateY(i * BLOCK_SIZE);
                        gameRoot.getChildren().add(grass);
                        break;
                }
            }
        }
    }

    //Method which set coordinates for players
    //int player(1 - first player,2 - second player, 3 - both player)
    public static void setCooridinates(int player, int[] P1coordinates, int[] P2coordinates) {
        if (player == 1) {
            player1.setTranslateX(P1coordinates[0]);
            player1.setTranslateY(P1coordinates[1]);
        }

        if (player == 2) {
            player2.setTranslateX(P2coordinates[0]);
            player2.setTranslateY(P2coordinates[1]);
        }

        if (player == 3) {
            player1.setTranslateX(P1coordinates[0]);
            player1.setTranslateY(P1coordinates[1]);

            player2.setTranslateX(P2coordinates[0]);
            player2.setTranslateY(P2coordinates[1]);
        }
    }

    //main method which create a map and add a players
    public void initContent(int level) {
        //call method createMap() and ddStatistics()
        createMap(level);
        addStatistics();

        gameRoot.getChildren().addAll(player1, player2);
        addGrass(level);
        //Add players and Image to gameRoot and gameRoot to appRoot
        appRoot.getChildren().add(gameRoot);
    }


    //move of players
    public void update() {
        //first player moves
        if (isPressed(KeyCode.W)) {
            lastPressedKeyP1 = 'W';
            player1.animation.play();
            player1.animation.setOffsetY(0);
            player1.moveY(-2);
        } else if (isPressed(KeyCode.S)) {
            lastPressedKeyP1 = 'S';
            player1.animation.play();
            player1.animation.setOffsetY(50);
            player1.moveY(2);
        } else if (isPressed(KeyCode.A)) {
            lastPressedKeyP1 = 'A';
            player1.animation.play();
            player1.animation.setOffsetY(100);
            player1.moveX(-2);
        } else if (isPressed(KeyCode.D)) {
            lastPressedKeyP1 = 'D';
            player1.animation.play();
            player1.animation.setOffsetY(150);
            player1.moveX(2);
        } else {
            player1.animation.stop();
        }
        if (isPressed(KeyCode.SPACE) && counterP1 == true) {
            counterP1 = false;
            if (lastPressedKeyP1 == 'W') {
                bulletP1 = new Bullet(player1.getTranslateX() + 18, player1.getTranslateY() - 25, 0, -1180);
            }
            if (lastPressedKeyP1 == 'S') {
                bulletP1 = new Bullet(player1.getTranslateX() + 18, player1.getTranslateY() + 60, 0, 1180);
            }
            if (lastPressedKeyP1 == 'A') {
                bulletP1 = new Bullet(player1.getTranslateX() - 25, player1.getTranslateY() + 18, -1180, 0);
            }
            if (lastPressedKeyP1 == 'D') {
                bulletP1 = new Bullet(player1.getTranslateX() + 60, player1.getTranslateY() + 18, 1180, 0);
            }
            //Timer in Trivial way
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setDuration(Duration.millis(2000));
            translateTransition.setNode(imageViewP1);
            translateTransition.play();
            translateTransition.setOnFinished(event -> {
                counterP1 = true;
            });
        }

        //second player moves
        if (isPressed(KeyCode.UP)) {
            lastPressedKeyP2 = 'U';
            player2.animation.play();
            player2.animation.setOffsetY(0);
            player2.animation.setOffsetX(50);
            player2.moveY(-2);
        } else if (isPressed(KeyCode.DOWN)) {
            lastPressedKeyP2 = 'D';
            player2.animation.play();
            player2.animation.setOffsetY(50);
            player2.animation.setOffsetX(50);
            player2.moveY(2);
        } else if (isPressed(KeyCode.LEFT)) {
            lastPressedKeyP2 = 'L';
            player2.animation.play();
            player2.animation.setOffsetY(100);
            player2.animation.setOffsetX(50);
            player2.moveX(-2);
        } else if (isPressed(KeyCode.RIGHT)) {
            lastPressedKeyP2 = 'R';
            player2.animation.play();
            player2.animation.setOffsetY(150);
            player2.animation.setOffsetX(50);
            player2.moveX(2);
        } else {
            player2.animation.stop();
        }
        if (isPressed(KeyCode.ENTER) && counterP2 == true) {
            counterP2 = false;
            if (lastPressedKeyP2 == 'U') {
                bulletP2 = new Bullet(player2.getTranslateX() + 18, player2.getTranslateY() - 25, 0, -1180);
            }
            if (lastPressedKeyP2 == 'D') {
                bulletP2 = new Bullet(player2.getTranslateX() + 18, player2.getTranslateY() + 60, 0, 1180);
            }
            if (lastPressedKeyP2 == 'L') {
                bulletP2 = new Bullet(player2.getTranslateX() - 25, player2.getTranslateY() + 18, -1180, 0);
            }
            if (lastPressedKeyP2 == 'R') {
                bulletP2 = new Bullet(player2.getTranslateX() + 60, player2.getTranslateY() + 18, 1180, 0);
            }
            //Timer in trivialWay
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setDuration(Duration.millis(2000));
            translateTransition.setNode(imageViewP1);
            translateTransition.play();
            translateTransition.setOnFinished(event -> {
                counterP2 = true;
            });
        }
    }


    //method which return value of button

    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    // Animation which execute in each frame of game
    AnimationTimer timer = new AnimationTimer() {
        public void handle(long now) {
            update();
            checkIntersect();
            timerStop();

        }
    };


    public void timerStop() {
        if (lifesP1 == 0 || lifesP2 == 0 || lifesP1 == -1 || lifesP2 == -1) {
            timer.stop();
        }
    }

    public void start(Stage primaryStage, int level, int XP1, int YP1, int XP2, int YP2) {
        //set level
        MapLevel = level;

        //set  coordinates
        player1Coordinates[0] = XP1;
        player1Coordinates[1] = YP1;
        player2Coordinates[0] = XP2;
        player2Coordinates[1] = YP2;

        appRoot.setPrefSize(1440, 1000);

        initContent(level);

        Scene scene = new Scene(appRoot, Color.BLACK);
        //when we put and release a button
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
        });
        timer.start();

        //Back Image
        Image imageBack = new Image("back1.png");
        ImageView backImage = new ImageView(imageBack);
        backImage.getStyleClass().add("image");
        gameRoot.getChildren().add(backImage);
        backImage.setTranslateY(960);
        //Change Music Image
        Image imageChangeMusic = new Image("changeMusic.jpg");
        ImageView changeMusicImage = new ImageView(imageChangeMusic);
        changeMusicImage.getStyleClass().add("image");
        gameRoot.getChildren().add(changeMusicImage);
        changeMusicImage.setTranslateX(90);
        changeMusicImage.setTranslateY(960);
        addCss(scene);

        backImage.setOnMouseClicked(event -> {
            timer.stop();
            bulletP1 = null;
            bulletP2 = null;
            keys = new HashMap<>();
            platforms = new ArrayList<>();
            gameRoot = new Pane();
            appRoot = new Pane();
            killsP1 = 0;
            killsP1 = 0;
            lifesP1 = 3;
            lifesP2 = 3;
            restart();
        });

        changeMusicImage.setOnMouseClicked(event -> {
            changeMusic();
        });

        primaryStage.setTitle("TANCHIKI");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setX(300);
        primaryStage.setY(0);
        primaryStage.show();
    }

}