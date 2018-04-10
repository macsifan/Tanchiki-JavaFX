package Tanchiki;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static Tanchiki.Music.changeMusic;

public class Display extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    Scene sceneM;
    static Stage mainStage;
    static Scene sceneLevel;
    static Game game = new Game();


    public void start(Stage mainStage1) {

        changeMusic();
        mainStage = mainStage1;
        mainStage.setResizable(false);
        //Create  StackPane which will holds Image of interface and VBox with 3 image(play,construction,exit)
        StackPane root = new StackPane();
        //Create Images
        Image main = new Image("main.jpg");
        ImageView mainImage = new ImageView(main);

        Image playM = new Image("playM.png");
        ImageView playImage = new ImageView(playM);

        Image constructionM = new Image("constructionM.png");
        ImageView constructionImage = new ImageView(constructionM);

        Image exitM = new Image("exitM.png");
        ImageView exitImage = new ImageView(exitM);

        Image danceGif = new Image("dance.gif");
        ImageView gifDance = new ImageView(danceGif);
        gifDance.setTranslateX(-265);
        gifDance.setTranslateY(-100);

        Image danceGif2 = new Image("dance.gif");
        ImageView gifDance2 = new ImageView(danceGif2);
        gifDance2.setTranslateX(265);
        gifDance2.setTranslateY(-100);

        //Use css for Images
        playImage.getStyleClass().add("image");
        constructionImage.getStyleClass().add("image");
        exitImage.getStyleClass().add("image");


        //Create Vbox for bottons and set the location
        VBox vBox = new VBox(15, playImage, constructionImage, exitImage);
        vBox.setTranslateX(235);
        vBox.setTranslateY(215);

        //Add VBox and Image to sceneM
        root.getChildren().addAll(mainImage, gifDance, gifDance2, vBox);

        //Create Scene add to sceneM StackPane
        sceneM = new Scene(root, 620, 410);
        addCss(sceneM);
        //Set Title and Scene to StackPane
        mainStage.setTitle("TANCHIKI");
        mainStage.setScene(sceneM);
        mainStage.getIcons().add(new Image("icon.jpg"));
        mainStage.show();


        //Set Action to Images
        //Play Image start a game
        playImage.setOnMouseClicked(event -> {
            StackPane rootLevel = new StackPane();
            Image level = new Image("level.jpg");
            ImageView levelImage = new ImageView(level);

            Image map1 = new Image("map1.png");
            ImageView map1Image = new ImageView(map1);

            Image map2 = new Image("map2.png");
            ImageView map2Image = new ImageView(map2);

            Image map3 = new Image("map3.png");
            ImageView map3Image = new ImageView(map3);

            Image map4 = new Image("map4.png");
            ImageView map4Image = new ImageView(map4);

            Image map5 = new Image("map5.png");
            ImageView map5Image = new ImageView(map5);

            Image map6 = new Image("map6.png");
            ImageView map6Image = new ImageView(map6);

            Image imageBack = new Image("back.png");
            ImageView backImage = new ImageView(imageBack);

            map1Image.getStyleClass().add("image");
            map2Image.getStyleClass().add("image");
            map3Image.getStyleClass().add("image");
            map4Image.getStyleClass().add("image");
            map5Image.getStyleClass().add("image");
            map6Image.getStyleClass().add("image");
            backImage.getStyleClass().add("image");


            map1Image.setTranslateX(-200);
            map1Image.setTranslateY(-130);

            map2Image.setTranslateY(-130);


            map3Image.setTranslateX(200);
            map3Image.setTranslateY(-130);

            map4Image.setTranslateX(-200);
            map4Image.setTranslateY(100);

            map5Image.setTranslateY(100);

            map6Image.setTranslateX(200);
            map6Image.setTranslateY(100);

            backImage.setTranslateX(-265);
            backImage.setTranslateY(270);

            rootLevel.getChildren().addAll(levelImage, map1Image, map2Image, map3Image, map4Image, map5Image, map6Image, backImage);
            sceneLevel = new Scene(rootLevel, 629, 582);
            addCss(sceneLevel);
            mainStage.setScene(sceneLevel);

            map1Image.setOnMouseClicked(event1 -> {
                try {
                    game.start(mainStage, 1, 545, 900, 545, 70);
                } catch (Exception e) {
                }
            });
            map2Image.setOnMouseClicked(event1 -> {
                try {
                    game.start(mainStage, 2, 1145, 900, 185, 70);
                } catch (Exception e) {
                }
            });
            map3Image.setOnMouseClicked(event1 -> {
                try {
                    game.start(mainStage, 3, 1265, 425, 65, 425);
                } catch (Exception e) {
                }
            });

            map4Image.setOnMouseClicked(event1 -> {
                try {
                    game.start(mainStage, 4, 1265, 425, 65, 425);
                } catch (Exception e) {
                }
            });

            map5Image.setOnMouseClicked(event1 -> {
                try {
                    game.start(mainStage, 5, 545, 900, 545, 70);
                } catch (Exception e) {
                }
            });

            map6Image.setOnMouseClicked(event1 -> {
                try {
                    game.start(mainStage, 6, 185, 900, 1145, 70);
                } catch (Exception e) {
                }
            });


            backImage.setOnMouseClicked(event1 -> {
                mainStage.setScene(sceneM);
            });


        });
        //Construction Image show information
        constructionImage.setOnMouseClicked(event -> {
            //Create stackPane to construction
            StackPane rootConstruction = new StackPane();
            //Image to construction
            Image imageConstruction = new Image("construction.png");
            ImageView constructionImageC = new ImageView(imageConstruction);

            //Image back return to begin
            Image imageBack = new Image("back.png");
            ImageView backImage = new ImageView(imageBack);


            backImage.setTranslateX(-270);
            backImage.setTranslateY(190);
            //add css to Image
            backImage.getStyleClass().add("image");
            //add images to stackPane
            rootConstruction.getChildren().addAll(constructionImageC, backImage);

            //Create Scene with StackPane
            Scene sceneToConstruction = new Scene(rootConstruction, 650, 410);
            addCss(sceneToConstruction);
            //Create Stage
            Stage stageOfConstruction = new Stage();
            //Add icon to Stage
            stageOfConstruction.getIcons().add(new Image("icon.jpg"));
            stageOfConstruction.setResizable(false);
            stageOfConstruction.setTitle("CONSTRUCTION");
            stageOfConstruction.setScene(sceneToConstruction);
            //Back Image return to menu
            backImage.setOnMouseClicked(event1 -> {
                mainStage.show();
                stageOfConstruction.close();

            });

            stageOfConstruction.show();
            mainStage.close();
        });
        //Exit Image close a programm
        exitImage.setOnMouseClicked(event -> System.exit(0));

    }

    //addCss method add css file to sceneM
    public static void addCss(Scene scene) {
        scene.getStylesheets().add("style.css");
    }

    public static void restart() {
        game = new Game();
        mainStage.setX(630);
        mainStage.setY(200);
        mainStage.setScene(sceneLevel);
    }

}