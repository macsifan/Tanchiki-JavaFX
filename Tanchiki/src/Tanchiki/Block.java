package Tanchiki;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Block extends Pane {
    Image blocksImg = new Image("textures.jpg");
    ImageView block;

    public enum BlockType {
        WALL, BETON, ZONE, EAGLEP1, EAGLEP2
    }

    public Block(BlockType blockType, int x, int y) {
        block = new ImageView(blocksImg);
        block.setFitWidth(Game.BLOCK_SIZE);
        block.setFitHeight(Game.BLOCK_SIZE);
        setTranslateX(x);
        setTranslateY(y);
        switch (blockType) {
            //1
            case WALL:
                block.setViewport(new Rectangle2D(100, 0, 60, 60));
                break;

            //2
            case BETON:
                block.setViewport(new Rectangle2D(100, 60, 60, 60));
                this.setId("X");
                break;

            //3
            case EAGLEP1:
                block.setViewport(new Rectangle2D(100, 120, 60, 60));
                this.setId("P1");
                break;

            //4
            case EAGLEP2:
                block.setViewport(new Rectangle2D(160, 120, 60, 60));
                this.setId("P2");
                break;

            //*
            case ZONE:
                block.setViewport(new Rectangle2D(100, 180, 60, 60));
                this.setId("X");
                break;
        }
        getChildren().add(block);
        Game.platforms.add(this);
        Game.gameRoot.getChildren().add(this);
    }
}
