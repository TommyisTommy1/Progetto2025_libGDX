package entity;

import utils.Spritesheet;
import utils.SpritesheetEntity;

public class Nemico {
    boolean isAlive;
    boolean isAliveAnimation = false;

    SpritesheetEntity moving;
    SpritesheetEntity notMoving;
    Spritesheet dying;
}
