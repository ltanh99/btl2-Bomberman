package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Bomber2;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Tile {
        protected Board _board;
        
	public Portal(int x, int y,Board _board, Sprite sprite) {
		super(x, y, sprite);
                this._board = _board;
	}
	
	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý khi Bomber đi vào
		if(e instanceof Bomber ) {
			
			
			if(e.getXTile() == getX() && e.getYTile() == getY()) {
				if(_board.detectNoEnemies())
					_board.nextLevel();
			}
			
			return true;
		}
                if(e instanceof Bomber2 ) {
			
			
			if(e.getXTile() == getX() && e.getYTile() == getY()) {
				if(_board.detectNoEnemies())
					_board.nextLevel();
			}
			
			return true;
		}
		if(e instanceof Flame)
                {
                    return true;
                }
                
		return true;
	}

}
