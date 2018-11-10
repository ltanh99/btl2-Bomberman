/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.level;

/**
 *
 * @author Admin
 */
import uet.oop.bomberman.Board;
import uet.oop.bomberman.exceptions.LoadLevelException;

/**
 * Load và l?u tr? thông tin b?n ?? các màn ch?i
 */
public abstract class LevelLoader {

    protected int _width, _height; // default values just for testing
    protected int _level;
    protected String[] _lineTiles;
    protected Board _board;

    public LevelLoader(Board board, int level) throws LoadLevelException {
        _board = board;
        loadLevel(level);
    }

    public abstract void loadLevel(int level) throws LoadLevelException;

    public abstract void createEntities();

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public int getLevel() {
        return _level;
    }

}