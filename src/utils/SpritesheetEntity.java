package utils;


public class SpritesheetEntity{
    Spritesheet up, down, right, left;
    String path;
    Spritesheet image;

    public SpritesheetEntity(int num, int up, int down, int right, int left, String path, String file){
        this.path = path;
        this.up  = new Spritesheet(num, up, path, file);
        this.down = new Spritesheet(num , down, path, file);
        this.right = new Spritesheet(num, right, path, file);
        this.left = new Spritesheet(num, left, path, file);
    }

    public SpritesheetEntity(int num, int up, int down, int right, String path, String file){
        this.up  = new Spritesheet(num, up, path, file);
        this.down = new Spritesheet(num , down, path, file);
        this.right = new Spritesheet(num, right, path, file);
        this.left = new Spritesheet(num, right, path, file);

        //image = this.up;
        
        this.left.flipImmagine();
        //this.left.overlayImmagine(image);
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
