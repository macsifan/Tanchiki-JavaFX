package Tanchiki;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

// extends by Transition because this class have method interpolate
public class SpriteAnimation extends Transition {
    //our textures
    private final ImageView imageView;
    //bias of animation
    private int offsetX;
    private int offsetY;
    //size of one picture
    private final int width;
    private final int height;

    public SpriteAnimation(ImageView imageView, Duration duration, int offsetX, int offsetY, int width, int height) {
        this.imageView = imageView;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        //duration of Animation
        setCycleDuration(duration);
        //duration is INDEFINITE
        setCycleCount(Animation.INDEFINITE);
        //linear animation without stop
        setInterpolator(Interpolator.LINEAR);
        //our frame
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    }

    public void setOffsetX(int x) {
        this.offsetX = x;
    }

    public void setOffsetY(int y) {
        this.offsetY = y;
    }

    // method call in each animation frame
    // determines the behavior of animation
    protected void interpolate(double frac) {
        final int x = offsetX;
        final int y = offsetY;
        imageView.setViewport(new Rectangle2D(x, y, width, height));
    }
}
