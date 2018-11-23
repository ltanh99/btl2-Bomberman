package uet.oop.bomberman.entities.character.enemy;


import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium;
import uet.oop.bomberman.graphics.Sprite;

public class Pontan1 extends Enemy {
	
	public Pontan1(int x, int y, Board board) {
		super(x, y, board, Sprite.pontan1_dead, Game.getBomberSpeed(), 1500);
		
		_sprite = Sprite.pontan1_left1;
		
		_ai = new AIMedium(_board.getBomber(), this);
		_direction  = _ai.calculateDirection();
	}
	
	@Override
	protected void chooseSprite() {
		switch(_direction) {
			case 0:
			case 1:
				if(_moving)
					_sprite = Sprite.movingSprite(Sprite.pontan1_right1, Sprite.pontan1_right2, Sprite.pontan1_right3, _animate, 60);
				else
					_sprite = Sprite.pontan1_left1;
				break;
			case 2:
			case 3:
				if(_moving)
					_sprite = Sprite.movingSprite(Sprite.pontan1_left1, Sprite.pontan1_left2, Sprite.pontan1_left3, _animate, 60);
				else
					_sprite = Sprite.pontan1_left1;
				break;
		}
	}
}
