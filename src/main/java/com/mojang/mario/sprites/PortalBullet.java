package com.mojang.mario.sprites;
import com.mojang.mario.*;
import com.mojang.mario.level.*;
public class PortalBullet extends Sprite{
	public int type;
	public LevelScene world;
	public PortalBullet(LevelScene scene, float x, float y, float xa, float ya, int type){
		this.x=x;
		this.y=y;
		this.xa = xa;
		this.ya = ya;
		sheet = Art.particles;
		this.yPic=4;
		this.xPic = type;
		this.type = type;
                wPic  = hPic = 16;
		xPicO = 8;
		yPicO = 15;
		world=scene;
	}
        public void move(){
		super.move();
		if(isBlocking(x, y)){
			spriteContext.removeSprite(this);
			world.portalInFlight[type]=false;
			buildPortal();
		}
	}
	public void buildPortal(){
		int myX = ((int) (x / 16)) * 16;
		int myY = ((int) (y / 16)) * 16;
		int portalX = myX;
		int portalY = myY;
		if(xa<0){
			portalX += 16;
		}
		else if(xa > 0){
			portalX -= 16;
		}
		if(ya<0){
			portalY += 16;
		}
		else if(ya > 0){
			portalY -= 16;
		}
		world.addSprite(new FireFlower(world, portalX, portalY));
	}
			
    private boolean isBlocking(float _x, float _y)
    {
        int x = (int) (_x / 16);
        int y = (int) (_y / 16);
        //if (x == (int) (this.x / 16) && y == (int) (this.y / 16)) return false;

        //boolean blocking = world.level.isBlocking(x, y, xa, ya);
        byte block = world.level.getBlock(x, y);
	System.out.println(block);
	return block!=0;
    }

}
