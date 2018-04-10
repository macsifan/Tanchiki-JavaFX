package Tanchiki;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import static Tanchiki.Game.platforms;

public class Characters extends Pane {
    //create ImageView which will show our character
    ImageView imageView;
    //Animation of Characters
    SpriteAnimation animation;


    //Constructor to create character
    public Characters(ImageView imageView, int offsetX, int offsetY, int width, int height) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(imageView, Duration.millis(200), offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
    }

    //method which move  character along x axis
    public void moveX(int x) {
        boolean movingRight = x > 0;
        for (int i = 0; i < Math.abs(x); i++) {
            for (Node platform : platforms) {
                //getBoundsInParent returns the bounds after adjusting them with depending on transforms/layoutX/layoutY.
                if (this.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {

                        if (this.getTranslateX() + Game.CHARACTER_SIZE == platform.getTranslateX()) {
                            this.setTranslateX(this.getTranslateX() - 1);
                            return;
                        }
                    } else {
                        if (this.getTranslateX() == platform.getTranslateX() + Game.BLOCK_SIZE) {
                            this.setTranslateX(this.getTranslateX() + 1);
                            return;
                        }
                    }
                }
            }
            this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    //method which move  character along Y axis
    public void moveY(int y) {
        boolean movingDown = y > 0;
        for (int i = 0; i < Math.abs(y); i++) {
            for (Node platform : platforms) {
                if (this.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (this.getTranslateY() + Game.CHARACTER_SIZE == platform.getTranslateY()) {
                            this.setTranslateY(this.getTranslateY() - 1);
                            return;
                        }
                    } else {
                        if (this.getTranslateY() == platform.getTranslateY() + Game.BLOCK_SIZE) {
                            this.setTranslateY(this.getTranslateY() + 1);
                            return;
                        }
                    }
                }
            }
            this.setTranslateY(this.getTranslateY() + (movingDown ? 1 : -1));
        }
    }


}
