package cs2.game;

import cs2.util.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
  protected Image img; // the image to be displayed for this sprite
  protected Vec2 pos; // the current position of this sprite
  protected Vec2 size;


  /* The remained of the constructors and methods should be uncommented
   * as you write your code. I recommend keeping your project in a state
   * that it can always be run, even if it doesn't do much.
   * Then slowly over time, you can un-comment and add in additional
   * features.
   * DO NOT TRY TO WRITE EVERYTHING ALL AT ONCE. IT WILL NOT WORK.
   */


  /*
  // The constructor should initialize the fields of the class
    */
  public Sprite(Image i, Vec2 p, Vec2 size) { 
      this.img = i;
      this.pos = p;
      this.size = size;

  }

  public void heartPosition(Image i, Vec2 heartPosition){
    img = i;
    pos = heartPosition;
  }


  /*
  // This method should draw the image at the current position
    */
  public void display(GraphicsContext g) { 
    g.drawImage(this.img, pos.getX(),pos.getY(), size.getX(), size.getY());
  }


  /*
  // This method should change the location/position of the sprite
  // by the amount specified in the parameter delta
  */
  public void move(Vec2 delta) { 
    pos.add(delta);
  }

  public boolean overlaps1D(double d, double e, double f, double g){
    if (contains(d, e, f) || contains(d, e, g) || contains(f, g, d) || contains(f, g, e)){
      return true;
    } 
    return false;
  }
  
  public boolean contains(double d, double e, double f){
    if (d <= f && f <= e){
      return true;
    } else {
      return false;
    }
  }

  public boolean overlaps(Sprite other){
    if (this instanceof Enemy && other instanceof Bullet && ((Bullet) other).vel.getY() > 0){
      return false;
    }
    if (this instanceof Player && other instanceof Bullet && ((Bullet) other).vel.getY() < 0){
      return false;
    }
    if (overlaps1D(this.pos.getX(), this.pos.getX() + this.size.getX(), other.pos.getX(), other.pos.getX() + other.size.getX()) && 
    overlaps1D(this.pos.getY(), this.pos.getY() + this.size.getY(), other.pos.getY(), other.pos.getY() + other.size.getY())){ 
      return true; 
    }
    return false;
  }

  /*public boolean Intersection(){
    if (position )
    return true;
  }*/

  /*public boolean intersects(Sprite other){
    boolean collided = false;
    if (this instanceof Player && other instanceof Bullet){
      overlaps(other.getX(), other.getX() + )
     }
    return collided;
  }

  public boolean overlapsx(int x, int width){
    if (otherx.getX() <= pos.getX() && )
  }*/


}
