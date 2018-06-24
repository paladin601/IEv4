/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IEv4;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.javacv.Frame;

/**
 *
 * @author Mipto Laptop 1
 */
public class Imagensish
{

    private int Magnitude = 0;

    private Color[] Prom;
    private Mat Img;
    private int FrameNumber;

    //Constructores----------------------------------------------------------------------------------
    public Imagensish(Mat Img, int FrameNumber)
    {
        this.Img = Img;
        this.FrameNumber = FrameNumber;
        Prom = new Color[5];

        MeasureImg(Java2DFrameUtils.toBufferedImage(Img));
    }

    public Imagensish(BufferedImage Img, int FrameNumber)
    {
        this.Img = Java2DFrameUtils.toMat(Img);
        this.FrameNumber = FrameNumber;
        Prom = new Color[5];

        MeasureImg(Img);
    }

    public Imagensish(Frame Img, int FrameNumber)
    {
        this.Img = Java2DFrameUtils.toMat(Img);
        this.FrameNumber = FrameNumber;
        Prom = new Color[5];

        MeasureImg(Java2DFrameUtils.toBufferedImage(Img));
    }

    //Done
    public final void MeasureImg(BufferedImage In)
    {

        Prom[1] = MeasureROI(In, new Rect(0, 0, IEv4UI._gridHalfWidth - 1, IEv4UI._gridHalfHeight - 1));
        Prom[2] = MeasureROI(In, new Rect(IEv4UI._gridHalfWidth, 0, IEv4UI._gridHalfWidth - 1, IEv4UI._gridHalfHeight - 1));
        Prom[3] = MeasureROI(In, new Rect(0, IEv4UI._gridHalfHeight, IEv4UI._gridHalfWidth - 1, IEv4UI._gridHalfHeight - 1));
        Prom[4] = MeasureROI(In, new Rect(IEv4UI._gridHalfWidth, IEv4UI._gridHalfHeight, IEv4UI._gridHalfWidth - 1, IEv4UI._gridHalfHeight - 1));

        float red = 0, green = 0, blue = 0;
        for (int ii = 1; ii < Prom.length; ii++)
        {
            red += Prom[ii].getRed();
            green += Prom[ii].getGreen();
            blue += Prom[ii].getBlue();
        }
        red /= 4;
        green /= 4;
        blue /= 4;

        Prom[0] = new Color(Clamp(red), Clamp(green), Clamp(blue));
        Magnitude = (int) EUDistance(Color.BLACK, Prom[0]);
    }

    //Done
    public Color MeasureROI(BufferedImage In, Rect ROI)
    {

        float red = 0, green = 0, blue = 0;
        int limy = ROI.y() + ROI.height();
        int limx = ROI.x() + ROI.width();
        if (limx > In.getWidth() || limy > In.getHeight())
        {
            System.out.println("la tas cagado serio");
        }
        for (int yy = ROI.y(); yy < limy; yy++)
        {
            for (int xx = ROI.x(); xx < limx; xx++)
            {
                Color aux = new Color(In.getRGB(xx, yy));

                red += aux.getRed();
                green += aux.getGreen();
                blue += aux.getBlue();
            }
        }
        int size = ROI.height() * ROI.width();
        red /= size;
        green /= size;
        blue /= size;

        return new Color(Clamp(red), Clamp(green), Clamp(blue));
    }

    public static int Clamp(Number in)
    {
        return Math.max(0, Math.min(255, in.intValue()));
    }

    public static double EUDistance(Color A, Color B)
    {
        double red_dist = A.getRed() - B.getRed();
        double green_dist = A.getGreen() - B.getGreen();
        double blue_dist = A.getBlue() - B.getBlue();
        double re = Math.sqrt(red_dist * red_dist + green_dist * green_dist + blue_dist * blue_dist);
        return re;
    }

    public static double LABDistance(Color A, Color B)
    {
        HashMap<String, Double> A1 = Imagensish.RGBtoLAB(A);
        HashMap<String, Double> B1 = Imagensish.RGBtoLAB(B);
        double red_dist = A1.get("L") - B1.get("L");
        double green_dist = A1.get("A") - B1.get("A");
        double blue_dist = A1.get("B") - B1.get("B");
        return Math.sqrt(red_dist * red_dist + green_dist * green_dist + blue_dist * blue_dist);
    }

    public static HashMap<String, Double> RGBtoLAB(Color in)
    {
        HashMap<String, Double> re = new HashMap<>();
        HashMap<String, Double> aux = RGBtoXYZ(in);
        double l, a, b, x, y, z, xn, yn, zn;
        //valores constastes triestimulos 
        xn = 95.057f;
        yn = 100.0f;
        zn = 108.883f;

        x = aux.get("X");
        y = aux.get("Y");
        z = aux.get("Z");

        // aca es x/xn y y/yn y z/zn
        l = 116 * f(y / yn) - 16;
        a = 500 * (f(x / xn) - f(y / yn));
        b = 200 * (f(y / yn) - f(z / zn));

        re.put("L", l);
        re.put("A", a);
        re.put("B", b);

        return re;
    }

    public static HashMap<String, Double> RGBtoXYZ(Color in)
    {
        HashMap<String, Double> re = new HashMap<>();
        double r, g, b;
        double x, y, z;
        //normalizar
        r = in.getRed() / 255.0f;
        g = in.getGreen() / 255.0f;
        b = in.getBlue() / 255.0f;

        r = r <= 0.04045 ? r / 12.92 : Math.pow((r + 0.055) / 1.055, 2.4);
        g = g <= 0.04045 ? g / 12.92 : Math.pow((g + 0.055) / 1.055, 2.4);
        b = b <= 0.04045 ? b / 12.92 : Math.pow((b + 0.055) / 1.055, 2.4);

        //srgb d65
        x = r * 0.4124564f + g * 0.3575761f + b * 0.1804375f;
        y = r * 0.2126729f + g * 0.7151522f + b * 0.0721750f;
        z = r * 0.0193339f + g * 0.1191920f + b * 0.9503041f;

        re.put("X", x);
        re.put("Y", y);
        re.put("Z", z);

        return re;

    }

    public static double f(double t)
    {
        final double es3 = Math.pow(6 / 29, 3), es2 = Math.pow(6 / 29, 2);

        if (t > es3)
        {
            t = Math.cbrt(t);
        } else
        {
            t = (t / (3 * es2)) + (4 / 29);
        }
        return t;
    }

    //Getters----------------------------------------------------------------------------------
    public Color[] getProm()
    {
        return Prom;
    }

    public Mat getImg()
    {
        return Img;
    }

    public int getFrameNumber()
    {
        return FrameNumber;
    }

    public int getMagnitude()
    {
        return Magnitude/(int)IEv4UI._minTolerance;
    }
}
