package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.Sound_cdjv.Sound_cdjv;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Bomber2;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {
        //Sound_cdjv ItemSound=new Sound_cdjv("C:\\Users\\Admin\\Documents\\NetBeansProjects\\bomberman-starter-starter-project-1\\src\\uet\\oop\\bomberman\\Sound_cdjv\\bomberman_music-master\\power03.wav");
	public SpeedItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý Bomber ăn Item
                if(e instanceof Bomber )
                {
                    
                    ((Bomber) e).addPowerup(this);
			remove();
			return true;
                }if(e instanceof Bomber2 )
                {
                    
                    ((Bomber2) e).addPowerup(this);
			remove();
			return true;
                }
		return true;
	}
        @Override
	public void setValues() {
		_active = true;
		Game.addBomberSpeed(0.5);
                
	}
        @Override
        public void setValues2() {
		_active = true;
		Game.addBomberSpeed2(0.5);
                
	}
}
