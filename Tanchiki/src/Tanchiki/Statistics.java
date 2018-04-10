package Tanchiki;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static Tanchiki.Bullet.time;
import static Tanchiki.Game.*;

public class Statistics {

    public static int lifesP1 = 3;
    public static int lifesP2 = 3;
    public static int killsP1 = 0;
    public static int killsP2 = 0;

    public static Text killsP1Text = new Text("0");
    public static Text killsP2Text = new Text("0");

    //coordinates for players
    public static int[] player1Coordinates = new int[2];
    public static int[] player2Coordinates = new int[2];


    public static void addStatistics() {
        Image imageP1 = new Image("statisticsP1.png");
        ImageView statisticsP1 = new ImageView(imageP1);

        Image imageP2 = new Image("statistiсsP2.png");
        ImageView statisticsP2 = new ImageView(imageP2);


        gameRoot.getChildren().addAll(statisticsP1, statisticsP2, killsP1Text, killsP2Text);
        statisticsP2.setTranslateX(1350);
        statisticsP2.setTranslateY(120);

        statisticsP1.setTranslateX(1350);
        statisticsP1.setTranslateY(720);
        killsP1Text.setTranslateX(1375);
        killsP1Text.setTranslateY(840);
        killsP1Text.setFont(Font.font("Verdana", 20));
        killsP2Text.setTranslateX(1375);
        killsP2Text.setTranslateY(240);
        killsP2Text.setFont(Font.font("Verdana", 20));
    }

    public static void addkills() {
        killsP1Text.setText(Integer.toString(killsP1));
        killsP2Text.setText(Integer.toString(killsP2));
    }

    public static void lifesLost(int player) {
        Image imageLifesLost = new Image("lifesLost.png");
        if (lifesP1 == 2) {
            ImageView twoLifes = new ImageView(imageLifesLost);
            gameRoot.getChildren().add(twoLifes);
            twoLifes.setTranslateX(1393);
            twoLifes.setTranslateY(775);
        }
        if (lifesP1 == 1) {
            ImageView oneLifes = new ImageView(imageLifesLost);
            gameRoot.getChildren().add(oneLifes);
            oneLifes.setTranslateX(1373);
            oneLifes.setTranslateY(775);

        }
        if (lifesP1 == 0) {
            ImageView zeroLifes = new ImageView(imageLifesLost);
            gameRoot.getChildren().add(zeroLifes);
            zeroLifes.setTranslateX(1353);
            zeroLifes.setTranslateY(775);
            Image win = new Image("P2Win.png");
            ImageView winImage = new ImageView(win);
            gameRoot.getChildren().add(winImage);
            winImage.setTranslateX(260);
            winImage.setTranslateY(300);

        }
        if (lifesP2 == 2) {
            ImageView twoLifes = new ImageView(imageLifesLost);
            gameRoot.getChildren().add(twoLifes);
            twoLifes.setTranslateX(1393);
            twoLifes.setTranslateY(175);
        }
        if (lifesP2 == 1) {
            ImageView oneLifes = new ImageView(imageLifesLost);
            gameRoot.getChildren().add(oneLifes);
            oneLifes.setTranslateX(1373);
            oneLifes.setTranslateY(175);
        }
        if (lifesP2 == 0) {
            ImageView zeroLifes = new ImageView(imageLifesLost);
            gameRoot.getChildren().add(zeroLifes);
            zeroLifes.setTranslateX(1353);
            zeroLifes.setTranslateY(175);
            Image win = new Image("P1Win.png");
            ImageView winImage = new ImageView(win);
            gameRoot.getChildren().add(winImage);
            winImage.setTranslateX(250);
            winImage.setTranslateY(500);
        }
        if (player == 1) {
            Image win = new Image("P2Win.png");
            ImageView winImage = new ImageView(win);
            gameRoot.getChildren().add(winImage);
            winImage.setTranslateX(260);
            winImage.setTranslateY(300);
        }
        if (player == 2) {
            Image win = new Image("P1Win.png");
            ImageView winImage = new ImageView(win);
            gameRoot.getChildren().add(winImage);
            winImage.setTranslateX(250);
            winImage.setTranslateY(500);
        }

    }

    public static void checkIntersect() {
        if (player1.getBoundsInParent().intersects(player2.getBoundsInParent())) {
            Image image = new Image("gif.gif");
            ImageView gifOfexplosion = new ImageView(image);
            gameRoot.getChildren().add(gifOfexplosion);
            gifOfexplosion.setTranslateX(player2.getTranslateX() - 10);
            gifOfexplosion.setTranslateY(player1.getTranslateY());
            time(gifOfexplosion, 630);
            setCooridinates(3, player1Coordinates, player2Coordinates);
        }
    }
}
