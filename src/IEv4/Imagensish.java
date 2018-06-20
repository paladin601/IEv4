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
        
        Prom[1] = MeasureROI(In, new Rect(0, 0, IEv4UI._gridHalfHeight ,IEv4UI._gridHalfHeight));
        Prom[2] = MeasureROI(In, new Rect(IEv4UI._gridHalfHeight, 0, IEv4UI._gridHalfHeight, IEv4UI._gridHalfHeight));
        Prom[3] = MeasureROI(In, new Rect(0, IEv4UI._gridHalfHeight, IEv4UI._gridHalfHeight, IEv4UI._gridHalfHeight));
        Prom[4] = MeasureROI(In, new Rect(IEv4UI._gridHalfHeight, IEv4UI._gridHalfHeight, IEv4UI._gridHalfHeight, IEv4UI._gridHalfHeight));
        
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
        for (int yy = ROI.y(); yy < ROI.height(); yy++) {
            for (int xx = ROI.x(); xx < ROI.width(); xx++) {
                Color aux = new Color(In.getRGB(xx, yy));
                
                red   += aux.getRed();
                green += aux.getGreen();
                blue  += aux.getBlue();
            }
        }
        int size = ROI.width() * ROI.height();
        red   /= size;        
        green /= size;
        blue  /= size;
        
        return new Color(Clamp(red), Clamp(green), Clamp(blue));
    }
    public static int Clamp(Number in){
        return Math.max(0, Math.min(255, in.intValue()));
    }
    
    public static boolean AlikeImgs(Imagensish A, Imagensish B){
        Color[] promA = A.getProm(), promB = B.getProm();
        if(IEv4UI._euclidianComp){
            if(EUDistance(promA[0], promB[0]) > IEv4UI._minTolerance) return false;
            for (int ii = 1; ii < promA.length; ii++){
                if(EUDistance(promA[ii], promB[ii]) > IEv4UI._minLocalTolerance) return false;
            }
        }
        else
        {
            if(LABDistance(promA[0], promB[0]) > IEv4UI._minTolerance) return false;
            for (int ii = 1; ii < promA.length; ii++){
                if(LABDistance(promA[ii], promB[ii]) > IEv4UI._minLocalTolerance) return false;
            }
        }
        
        return true;
    }
    
    
    public static double EUDistance(Color A, Color B){
        double red_dist = A.getRed() - B.getRed();
        double green_dist = A.getGreen() - B.getGreen();
        double blue_dist = A.getBlue() - B.getBlue();
        return Math.sqrt(red_dist*red_dist + green_dist*green_dist + blue_dist*blue_dist);
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
