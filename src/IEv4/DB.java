/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IEv4;
import java.util.*;
import static org.bytedeco.javacpp.opencv_core.hconcat;
import static org.bytedeco.javacpp.opencv_core.vconcat;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point2d;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameUtils;

/**
 *
 * @author Mipto Laptop 1
 */
public class DB {

 
    public int MinTolerance = 30, MinLocalTolerance = 40;
    Imagensish[] FramesUsed, FramesUsedHD, TiledImage;
    public ArrayList<ArrayList<Imagensish>> Pending;
    public HashSet<Integer> CheckedIndex;
    public ArrayList<Integer> FramesToMatch;
    public Point2d[] TileInits;
    public Mat Img;
    public boolean CheckingOld=false;
    private int size;
//    public ArrayList<Frame> FramesToCheck;    
//    public ArrayList<Integer> FramesToCheckIndex;
    

    
       public DB(Mat Img) {
        size = IEv4UI._gridX * IEv4UI._gridY;
           
        FramesUsed = new Imagensish[size];
        FramesUsedHD = new Imagensish[size];
        TiledImage = new Imagensish[size];
        TileInits = new Point2d[size];
        CheckedIndex = new HashSet<>();
        FramesToMatch = new ArrayList<>();
        Pending = new ArrayList<>();
           for (int i = 0; i < 4; i++) {
               Pending.add(new ArrayList<>());
           }
        
        this.Img = Img;
    }
    
    public void TileImage(){
        for (int yy = 0, initY = 0; yy < IEv4UI._gridY; yy++, initY+= IEv4UI._gridHeight) {
            for (int xx = 0, initX = 0; xx < IEv4UI._gridX; xx++, initX+= IEv4UI._gridWidth) {
                int index = yy * IEv4UI._gridX+ xx;
                this.TileInits[index] = new Point2d(initX, initY);
                this.TiledImage[index] = new Imagensish(new Mat(this.Img, new Rect(initX, initY, IEv4UI._ExWidth, IEv4UI._ExHeigth)), -1);
                this.FramesToMatch.add(index);
                //this.FramesUsed[(index+1)%FramesUsed.length] = this.TiledImage[index];
            }
        }
    }

    private int stride = (int)( Math.random() * IEv4UI._numFrames)%IEv4UI._numFrames;
    private int nextFrame = 0;
    public int FindFrame(){
        do{
            nextFrame = (nextFrame+stride)%IEv4UI._numFrames;
            if(this.CheckedIndex.add(nextFrame)){
                return nextFrame;
            }
            else
            {
                stride = (int)( Math.random() * IEv4UI._numFrames)%IEv4UI._numFrames;
            }
        }while(true);
    }
    
    public void MatchTiles(){
        while(FramesToMatch.size() > 0){
            int frame = FindFrame();
            Imagensish Comp = null;
            Mat grabbedHD = null, lowPoly = null;
            
            try {
                IEv4UI._grabber.setFrameNumber(frame);
                grabbedHD = Java2DFrameUtils.toMat(IEv4UI._grabber.grab());
                     
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage()+ "error al agarrar frame");
            }
            lowPoly = IEv4UI.resize(grabbedHD, IEv4UI._gridWidth);
            Comp = new Imagensish(lowPoly, frame);

            
            for (int ii = 0; ii < FramesToMatch.size(); ii++) {
                int index = FramesToMatch.get(ii);
                if(Imagensish.AlikeImgs(TiledImage[index], Comp)){
                    this.FramesToMatch.remove(ii);
                    this.FramesUsed[index] = Comp;
                    //this.FramesUsedHD[index] = new Imagensish(grabbedHD, frame);
                    ii = FramesToMatch.size();
                    IEv4UI.Msg.setText("Faltan "+ ii + "Frames");
                }
            }
            System.out.println("visto el frame "+frame);
            if((this.CheckedIndex.size()+1)%IEv4UI._change == 0) LowerTheLevel();
            
        }
    }

    public void LowerTheLevel(){
        IEv4UI._locality--;
    }
    public Mat GenerateMosaic(){
        TileImage();
        MatchTiles();
        System.out.println("Generating mosaic");
        Mat aux=null,aux1=null;
        int i=0;
       for(int jj=0;jj<this.FramesUsed.length ;jj++){
            if((jj%IEv4UI._gridX)==0 ){
                if(jj!=0){
                    if(i==0){
                        aux1=aux.clone();
                        i++;
                    }else{
                        vconcat(aux1, aux, aux1);
                    } 
                }
                aux = this.FramesUsed[jj].getImg().clone();
            }else{
               // if((jj%IEv3UI._gridX)!=0){
                    hconcat(aux,FramesUsed[jj].getImg(),aux);
                //}
            }          
        }
       vconcat(aux1, aux, aux1);
        return aux1;
    }
    
    static double  f(double t){
        final double es3 = Math.pow(6/29,3)
       ,es2 = Math.pow(6/29,2);
        
         if(t > es3){
             t = Math.cbrt(t);
         }else{
             t = (t/(3 * es2)) + (4/29);
         }
         return t;
     }
}
