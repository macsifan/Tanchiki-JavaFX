package Tanchiki;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ConcurrentModificationException;

import static Tanchiki.Game.*;
import static Tanchiki.Statistics.*;

public class Bullet {
    Image bulletImage = new Image("textures.jpg");
    ImageView imageView = new ImageView(bulletImage);
    Characters bullet = new Characters(imageView, 180, 0, 15, 15);

    public Bullet(double translateX, double translateY, int movebyX, int moveByY) {
        bullet(translateX, translateY, movebyX, moveByY);
    }

    //create a bulletP1
    public void bullet(double translateX, double translateY, int movebyX, int moveByY) {
        if (movebyX == 0 && moveByY < 0) {
            Characters bullet1 = new Characters(imageView, 160, 0, 15, 15);
            bullet = bullet1;
        } else if (movebyX == 0 && moveByY > 0) {
            Characters bullet1 = new Characters(imageView, 160, 15, 15, 15);
            bullet = bullet1;
        } else if (moveByY == 0 && movebyX < 0) {
            Characters bullet1 = new Characters(imageView, 160, 30, 15, 15);
            bullet = bullet1;
        } else if (moveByY == 0 && movebyX > 0) {
            Characters bullet1 = new Characters(imageView, 160, 45, 15, 15);
            bullet = bullet1;

        } else {
            Characters bullet1 = new Characters(imageView, 160, 15, 15, 15);
            bullet = bullet1;
        }
        bullet.setTranslateY(translateY);
        bullet.setTranslateX(translateX);
        gameRoot.getChildren().add(bullet);
        transition(bullet, movebyX, moveByY);
    }

    TranslateTransition translateTransition = new TranslateTransition();

    public AnimationTimer timer = new AnimationTimer() {
        public void handle(long now) {
            bulletIntersect();
        }
    };

    //method which transit a bulletP1 along x or y axis
    public void transition(Characters bullet, int x, int y) {

        //Creating Translate Transition
        //Setting the duration of the transition
        translateTransition.setDuration(Duration.millis(2800));
        //Setting the node for the transition
        translateTransition.setNode(bullet);
        //Setting the value of the transition along the x axis.
        translateTransition.setByX(x);
        translateTransition.setByY(y);
        //Setting the cycle count for the transition
        translateTransition.setCycleCount(1);
        //Setting auto reverse value to false
        translateTransition.setAutoReverse(true);
        //Playing the animation
        translateTransition.play();

        timer.start();
        translateTransition.setOnFinished(event -> {
            exparison(bullet);
        });
    }

    //time count in trivial way
    public static void exparison(Characters bullet) {
        Image image = new Image("gif.gif");
        ImageView gifOfexplosion = new ImageView(image);
        gameRoot.getChildren().add(gifOfexplosion);
        gameRoot.getChildren().remove(bullet);
        gifOfexplosion.setTranslateX(bullet.getTranslateX() - 10);
        gifOfexplosion.setTranslateY(bullet.getTranslateY());
        time(gifOfexplosion, 630);
    }

    public static void time(ImageView gif, int duration) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(duration));
        translateTransition.setNode(gif);
        translateTransition.play();
        translateTransition.setOnFinished(event -> {
            gameRoot.getChildren().remove(gif);
        });
    }

    public void bulletIntersect() {
        if (bullet.getBoundsInParent().intersects(player1.getBoundsInParent())) {
            translateTransition.stop();
            exparison(bullet);
            timer.stop();
            setCooridinates(1, player1Coordinates, player2Coordinates);
            lifesP1--;
            killsP2++;
            addkills();
            lifesLost(0);
        }
        if (bullet.getBoundsInParent().intersects(player2.getBoundsInParent())) {
            translateTransition.stop();
            exparison(bullet);
            timer.stop();
            setCooridinates(2, player1Coordinates, player2Coordinates);
            lifesP2--;
            killsP1++;
            addkills();
            lifesLost(0);

        }
        try {
            for (Node platform : platforms) {
                if (bullet.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    translateTransition.stop();
                    exparison(bullet);

                    if (platform.getId() != "X") {
                        platforms.remove(platform);
                        Image image = new Image("nullblock.png");
                        ImageView nullBlock = new ImageView(image);
                        nullBlock.setOpacity(0.5);
                        gameRoot.getChildren().add(nullBlock);
                        nullBlock.setTranslateX(platform.getTranslateX());
                        nullBlock.setTranslateY(platform.getTranslateY());
                        if (platform.getId() == "P1") {
                            lifesP1 = -1;
                            lifesLost(1);
                        }
                        if (platform.getId() == "P2") {
                            lifesP2 = -1;
                            lifesLost(2);
                        }
                    }
                    timer.stop();
                }


            }
        } catch (ConcurrentModificationException e) {
        }
    }
}



