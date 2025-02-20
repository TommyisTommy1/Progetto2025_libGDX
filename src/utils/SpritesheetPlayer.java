package utils;

public class SpritesheetPlayer{
    Spritesheet up, down, right, left;

    public SpritesheetPlayer(int num, int up, int down, int right, int left, String file){
        this.up  = new Spritesheet(num, up, file);
        this.down = new Spritesheet(num , down, file);
        this.right = new Spritesheet(num, right, file);
        this.left = new Spritesheet(num, left, file);
    }

    public SpritesheetPlayer(int num, int up, int down, int right, String file){
        this.up  = new Spritesheet(num, up, file);
        this.down = new Spritesheet(num , down, file);
        this.right = new Spritesheet(num, right, file);
        this.left = new Spritesheet(num, right, file);
        this.left.flipImmagine();
    }

    public Spritesheet getUp() {
        return up;
    }

    public void setUp(Spritesheet up) {
        this.up = up;
    }

    public Spritesheet getDown() {
        return down;
    }

    public void setDown(Spritesheet down) {
        this.down = down;
    }

    public Spritesheet getRight() {
        return right;
    }

    public void setRight(Spritesheet right) {
        this.right = right;
    }

    public Spritesheet getLeft() {
        return left;
    }

    public void setLeft(Spritesheet left) {
        this.left = left;
    }
}
