/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IEv4;

import java.awt.Color;
import java.awt.image.BufferedImage;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacpp.opencv_imgproc.*;
/**
 *
 * @author Mipto Laptop 1
 */
public class Imagensish {
    private Color[] Prom;
    private Mat Img;
    private int FrameNumber;

    //Constructores----------------------------------------------------------------------------------
    public Imagensish(Mat Img, int FrameNumber) {
        this.Img = Img;
        this.FrameNumber = FrameNumber;
        Prom = new Color[5];
        
        MeasureImg(Java2DFrameUtils.toBufferedImage(Img));
    }
    public Imagensish(BufferedImage Img, int FrameNumber) {
        this.Img = Java2DFrameUtils.toMat(Img);
        this.FrameNumber = FrameNumber;
        Prom = new Color[5];
        
        MeasureImg(Img);
    }
    public Imagensish(Frame Img, int FrameNumber) {
        this.Img = Java2DFrameUtils.toMat(Img);
        this.FrameNumber = FrameNumber;
        Prom = new Color[5];
        
        MeasureImg(Java2DFrameUtils.toBufferedImage(Img));
    }
    
    //Done
    public final void MeasureImg(BufferedImage In){
        
        Prom[1] = MeasureROI(In, new Rect(0, 0, IEv4UI._gridHalfWidth-1 ,IEv4UI._gridHalfHeight-1));
        Prom[2] = MeasureROI(In, new Rect(IEv4UI._gridHalfWidth, 0, IEv4UI._gridHalfWidth-1, IEv4UI._gridHalfHeight-1));
        Prom[3] = MeasureROI(In, new Rect(0, IEv4UI._gridHalfHeight, IEv4UI._gridHalfWidth-1, IEv4UI._gridHalfHeight-1));
        Prom[4] = MeasureROI(In, new Rect(IEv4UI._gridHalfWidth, IEv4UI._gridHalfHeight, IEv4UI._gridHalfWidth-1, IEv4UI._gridHalfHeight-1));
        
        float red = 0, green = 0, blue = 0;
        for (int ii = 1; ii < Prom.length; ii++) {
            red   += Prom[ii].getRed();
            green += Prom[ii].getGreen();
            blue  += Prom[ii].getBlue();
        }
        red   /= 4;        
        green /= 4;
        blue  /= 4;
        
        Prom[0] = new Color(Clamp(red), Clamp(green), Clamp(blue));
    }
    //Done
    public Color MeasureROI(BufferedImage In, Rect ROI){
        
        float red = 0, green = 0, blue = 0;
        int limy= ROI.y() + ROI.height();        
        int limx= ROI.x() + ROI.width();
        if(limx> In.getWidth()|| limy > In.getHeight()) System.out.println("la tas cagado serio");
        for (int yy = ROI.y(); yy < limy; yy++) {
            for (int xx = ROI.x(); xx < limx; xx++) {
                Color aux = new Color(In.getRGB(xx, yy));
                
                red   += aux.getRed();
                green += aux.getGreen();
                blue  += aux.getBlue();
            }
        }
        int size = ROI.height() * ROI.width();
        red   /= size;        
        green /= size;
        blue  /= size;
        
        return new Color(Clamp(red), Clamp(green), Clamp(blue));
    }
    public static int Clamp(Number in){
        return Math.max(0, Math.min(255, in.intValue()));
    }
    
    public static boolean AlikeImgs(Imagensish A, Imagensish B){
        if(IEv4UI._locality < 0) return true;
        Color[] promA = A.getProm(), promB = B.getProm();
        int local = 0;
        double dist;
        if(IEv4UI._euclidianComp){
            dist = EUDistance(promA[0], promB[0]);
            if( dist > IEv4UI._minTolerance){
                System.out.println(promA[0] +"vs"+ promB[0]+" = "+dist);
                return false;
            }else{
                System.out.println("good");
                if(IEv4UI._locality == 0) return true;
            }
            for (int ii = 1; ii < promA.length && local < IEv4UI._locality; ii++){
                dist = EUDistance(promA[ii], promB[ii]);
                if(dist > IEv4UI._minLocalTolerance) {
                }else{
                    local++;
                }
            }
        }
        else
        {
            dist = LABDistance(promA[0], promB[0]);
            if( dist > IEv4UI._minTolerance){
                System.out.println(promA[0] +"vs"+ promB[0]+" = "+dist);
                return false;
            }else{
                System.out.println("good");
                if(IEv4UI._locality == 0) return true;
            }
            for (int ii = 1; ii < promA.length && local < IEv4UI._locality; ii++){
                dist = LABDistance(promA[ii], promB[ii]);
                if(dist > IEv4UI._minLocalTolerance) {
                }else{
                    local++;
                }
            }
        }
        return local >= IEv4UI._locality;
    }
    
    
    public static double EUDistance(Color A, Color B){
        double red_dist = A.getRed() - B.getRed();
        double green_dist = A.getGreen() - B.getGreen();
        double blue_dist = A.getBlue() - B.getBlue();
        double re = Math.sqrt(red_dist*red_dist + green_dist*green_dist + blue_dist*blue_dist);
        return re;
    }
    
    public static double LABDistance(Color A, Color B){
        A = Imagensish.RGBtoLAB(A);
        B = Imagensish.RGBtoLAB(B);
        double red_dist = A.getRed() - B.getRed();
        double green_dist = A.getGreen() - B.getGreen();
        double blue_dist = A.getBlue() - B.getBlue();
        return Math.sqrt(red_dist*red_dist + green_dist*green_dist + blue_dist*blue_dist);
    }
    
    public static Color RGBtoLAB(Color in){
      return Color.black;          
    }
    
    public static Color RGBtoXYZ(){
        return Color.black;          

    }
    
    //Getters----------------------------------------------------------------------------------
    public Color[] getProm() {
        return Prom;
    }
    public Mat getImg() {
        return Img;
    }
    public int getFrameNumber() {
        return FrameNumber;
    }
}
