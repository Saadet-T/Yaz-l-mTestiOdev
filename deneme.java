package odev;
public class Deneme {
public int x;
public int y;
public Deneme(int x,int y) {
this.x = x;
this.y = y;
}
public int x2y2() {
return x * x + y * y;
}
public double karakok() {
return Math.sqrt((double)x2y2());
}
public double log2() {
++y;
return Math.log(((double)x)/y);
}
@Override
public boolean equals(Object obj) {
if(obj != null && obj instanceof Deneme) {
return ((Deneme)obj).x == this.x && ((Deneme)obj).y == this.y;
}
return false;
}
}

