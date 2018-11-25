package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.Sound_cdjv.Sound_cdjv;
import uet.oop.bomberman.entities.AnimatedEntitiy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Bomber2;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;

public class Bomb2 extends AnimatedEntitiy {

    protected double _timeToExplode = 120; //2 seconds
    public int _timeAfter = 20;

    protected Board _board;
    protected Flame[] _flames2 = null;
    protected boolean _exploded2 = false;
    protected boolean _allowedToPassThru = true;
    protected boolean _allowedToPassThru2 = true;
    
    Sound_cdjv explosionSound = new Sound_cdjv("C:\\Users\\Admin\\Documents\\NetBeansProjects\\bomberman-starter-starter-project-1\\src\\uet\\oop\\bomberman\\Sound_cdjv\\bomberman_music-master\\explosion.wav");
    public Bomb2(int x, int y, Board board) {
        _x = x;
        _y = y;
        _board = board;
        _sprite = Sprite.bomb;
    }

    @Override
    public void update() {
        if (_timeToExplode > 0) {
            _timeToExplode--;
        } else {
            if (!_exploded2) {
                explode();
            } else {                
                updateFlames();
            }

            if (_timeAfter > 0) {
                _timeAfter--;
            } else {
                
                remove();
                explosionSound.suspend();
            }
        }

        animate();
    }

    @Override
    public void render(Screen screen) {
        if (_exploded2) {
            _sprite = Sprite.bomb_exploded2;
            renderFlames(screen);
        } else {
            _sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);
        }

        int xt = (int) _x << 4;
        int yt = (int) _y << 4;

        screen.renderEntity(xt, yt, this);
    }

    public void renderFlames(Screen screen) {
        for (int i = 0; i < _flames2.length; i++) {
            _flames2[i].render(screen);
        }
    }

    public void updateFlames() {
        for (int i = 0; i < _flames2.length; i++) {
            _flames2[i].update();
        }
    }

    /**
     * X? lý Bomb n?
     */
    protected void explode() {
        boolean check = true;
        _exploded2 = true;

        _allowedToPassThru = true;

        

        Character a = _board.getCharacterAt(_x, _y);

        if (a != null) {
            
            if (a instanceof Bomber2 && Game.bomberCollideFlame2 ) {}
            else a.kill();

        }
        
        _flames2 = new Flame[4];
        for (int i = 0; i < _flames2.length; i++) {

            _flames2[i] = new Flame((int) _x, (int) _y, i, Game.getBombRadius2(), _board);

        }
        explosionSound.start();                         
    }
    
    public FlameSegment flameAt(int x, int y) {
        
        if (!_exploded2) {
            return null;
        }
        
        for (int i = 0; i < _flames2.length; i++) {
            if (_flames2[i] == null) {
                return null;
            }
            FlameSegment e = _flames2[i].flameSegmentAt(x, y);
            if (e != null) {
                return e;
            }
        }

        return null;
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: x? lý khi Bomber ?i ra sau khi v?a ??t bom (_allowedToPassThru)
        // TODO: x? lý va ch?m v?i Flame c?a Bomb khác
        if (e instanceof Flame) {
            _timeToExplode = 0;
            return true;
        }
        if (e instanceof Bomber) {
           
            double diffX = e.getX() - Coordinates.tileToPixel(getX());

            double diffY = e.getY() - Coordinates.tileToPixel(getY());

            if (!(diffX >= -10 && diffX < 16 && diffY >= 1 && diffY <= 28)) { // differences to see if the player has moved out of the bomb, tested values
                
                _allowedToPassThru = false;

            }

            return _allowedToPassThru;

        }if (e instanceof Bomber2) {
           
            double diffX = e.getX() - Coordinates.tileToPixel(getX());

            double diffY = e.getY() - Coordinates.tileToPixel(getY());

            if (!(diffX >= -10 && diffX < 16 && diffY >= 1 && diffY <= 28)) { // differences to see if the player has moved out of the bomb, tested values
                
                _allowedToPassThru2 = false;

            }

            return _allowedToPassThru2;

        }
        return false;
    }
    
}