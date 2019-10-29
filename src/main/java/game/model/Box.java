package game.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.drawRect(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
        graphics.fillRect(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
    }

    @Override
    public void move(int x, int y) {
        this.x = this.getX() + x;
        this.y = this.getY() + y;
    }
}
