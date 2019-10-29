package game.model;

import static game.model.Model.FIELD_CELL_SIZE;

public abstract class CollisionObject extends GameObject{
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public CollisionObject(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public boolean isCollision(GameObject gameObject, Direction direction){
        boolean flag = false;
        switch (direction){
            case UP:
                if ((this.getY() - FIELD_CELL_SIZE) == gameObject.getY() && this.getX() == gameObject.getX() )
                    flag = true;
                break;
            case DOWN:
                if ((this.getY() + FIELD_CELL_SIZE) == gameObject.getY() && this.getX() == gameObject.getX() )
                    flag = true;
                break;
            case LEFT:
                if (this.getY() == gameObject.getY() && (this.getX() - FIELD_CELL_SIZE) == gameObject.getX() )
                    flag = true;
                break;
            case RIGHT:
                if (this.getY() == gameObject.getY() && (this.getX() + FIELD_CELL_SIZE) == gameObject.getX() )
                    flag = true;
                break;
        }
        return flag;
    }
}

