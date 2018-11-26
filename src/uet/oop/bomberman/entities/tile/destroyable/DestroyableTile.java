
package uet.oop.bomberman.entities.tile.destroyable;

import uet.oop.bomberman.Sound_cdjv.Sound_cdjv;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.bomb.FlameSegment;
import uet.oop.bomberman.entities.character.enemy.Kondoria;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Sprite;

/**
 * �?ối tượng cố định có thể bị phá hủy
 */
public class DestroyableTile extends Tile {

	private final int MAX_ANIMATE = 7500;
	private int _animate = 0;
	protected boolean _destroyed = false;
	protected int _timeToDisapear = 20;
	protected Sprite _belowSprite = Sprite.grass;
	Sound_cdjv destroySound=new Sound_cdjv("C:\\Users\\nguyen ngoc gioi\\Downloads\\btl2-Bomberman-master\\src\\uet\\oop\\bomberman\\Sound_cdjv\\bomberman_music-master\\box_falls.wav");
        
	public DestroyableTile(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public void update() {
		if(_destroyed) {
			if(_animate < MAX_ANIMATE) _animate++; else _animate = 0;
			if(_timeToDisapear > 0) 
				_timeToDisapear--;
                        else{
                                
				remove();
                                destroySound.suspend();
                        }   
		}
	}

	public void destroy() {
                destroySound.start();
		_destroyed = true;
	}
	
	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý khi va chạm với Flame
                if (e instanceof Flame) {

                destroy();

        }
              return false;
	}
	
	public void addBelowSprite(Sprite sprite) {
		_belowSprite = sprite;
	}
	
	protected Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2) {
		int calc = _animate % 30;
		
		if(calc < 10) {
			return normal;
		}
			
		if(calc < 20) {
			return x1;
		}
			
		return x2;
	}
	
}
