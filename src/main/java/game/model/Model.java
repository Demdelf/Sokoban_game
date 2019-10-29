package game.model;

import game.controller.EventListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Model {
    public static int FIELD_CELL_SIZE = 20;
    EventListener eventListener;
    GameObjects gameObjects;
    int currentLevel = 1;
    LevelLoader levelLoader = new LevelLoader(
            Paths.get("D:\\JavaRushTasks\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3410\\res\\levels.txt"));

    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects(){
        return gameObjects;
    }

    public void restartLevel(int level){
        try {
            gameObjects = levelLoader.getLevel(level);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restart(){
        restartLevel(currentLevel);
    }

    public  void startNextLevel(){
        currentLevel++;
        restartLevel(currentLevel);
    }
    public void move(Direction direction){
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)) return;
        if (checkBoxCollisionAndMoveIfAvaliable(direction)) return;
        switch (direction){
            case LEFT:
                player.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                player.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                player.move(0, FIELD_CELL_SIZE);
                break;
        }
        checkCompletion();

    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction){
        boolean flag = false;
        for (Wall w: gameObjects.getWalls()
        ) {
            if (gameObject.isCollision(w, direction)) flag = true;
        }

        return flag;
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction){


        Player player = gameObjects.getPlayer();
        GameObject stoped = null;
        for (GameObject gameObject : gameObjects.getAll()) {
            if (!(gameObject instanceof Player) && !(gameObject instanceof Home) && player.isCollision(gameObject, direction)) {
                stoped = gameObject;
            }
        }

        if ((stoped == null)) {
            return false;
        }

        if (stoped instanceof Box) {
            Box stopedBox = (Box) stoped;
            if (checkWallCollision(stopedBox, direction)) {
                return true;
            }
            for (Box box : gameObjects.getBoxes()) {
                if (stopedBox.isCollision(box, direction)) {
                    return true;
                }
            }

            switch (direction) {
                case LEFT:
                    stopedBox.move(-FIELD_CELL_SIZE, 0);
                    break;
                case RIGHT:
                    stopedBox.move(FIELD_CELL_SIZE, 0);
                    break;
                case UP:
                    stopedBox.move(0, -FIELD_CELL_SIZE);
                    break;
                case DOWN:
                    stopedBox.move(0, FIELD_CELL_SIZE);
            }
        }
        return false;
    }


    public void checkCompletion(){
        int numberCollisions = 0;
        for(CollisionObject coBox : gameObjects.getBoxes()){
            for(GameObject goHome : gameObjects.getHomes()){
                if((coBox.getX() == goHome.getX()) && (coBox.getY() == goHome.getY())) numberCollisions++;
            }
        }
        if(numberCollisions == gameObjects.getBoxes().size()){
            eventListener.levelCompleted(currentLevel);
        }
    }


}
