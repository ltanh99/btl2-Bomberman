package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.character.Bomber;

public class Portal extends Tile {

    Board _board;

    public Portal(int x, int y,Board _board, Sprite sprite) {
        super(x, y, sprite);
        this._board = _board;
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: x? lý khi Bomber ?i vào
        if (e instanceof Bomber) {
            if (e.getXTile() == getX() && e.getYTile() == getY()) {
                if (_board.detectNoEnemies()) {
                    _board.nextLevel();
                }
            }

            return true;

        }

        return false;
    }

}
