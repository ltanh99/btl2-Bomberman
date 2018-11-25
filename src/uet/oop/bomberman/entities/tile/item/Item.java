package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.Sound_cdjv.Sound_cdjv;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Item extends Tile {
        protected int _duration = -1; // -1 is infinite, duration in lifes
	protected boolean _active = false;
        //Sound_cdjv ItemSound=new Sound_cdjv("C:\\Users\\Admin\\Documents\\NetBeansProjects\\bomberman-starter-starter-project-1\\src\\uet\\oop\\bomberman\\Sound_cdjv\\bomberman_music-master\\power03.wav");
	public Item(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
        public abstract void setValues();
        public abstract void setValues2();
	
	public void removeLive() {
		if(_duration > 0)
			_duration--;
		
		if(_duration == 0)
			_active = false;
	}
	
	public int getDuration() {
		return _duration;
	}
	public void setDuration(int duration) {
		this._duration = duration;
	}

	public boolean isActive() {
            
            return _active;
                
	}

	public void setActive(boolean active) {
		this._active = active;
                
	}

    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
