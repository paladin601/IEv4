/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IEv4;

import static IEv4.Imagensish.EUDistance;
import java.awt.Color;
import java.util.*;
import static org.bytedeco.javacpp.opencv_core.hconcat;
import static org.bytedeco.javacpp.opencv_core.vconcat;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacv.Java2DFrameUtils;

/**
 *
 * @author Mipto Laptop 1
 */
public class DB
{
    public int faltan;
    Imagensish[] FramesUsed, FramesUsedHD;
    public ArrayList<ArrayList<Imagensish>> Pending;
    public HashSet<Integer> CheckedIndex;
    public HashMap<Integer, ArrayList<Imagensish>> OrderedTiles;
    public Mat Img;
    public boolean CheckingOld = false;
    private int size;
    private boolean status = false;
    private int[] rand;
    public DB(Mat Img)
    {
        size = IEv4UI._gridX * IEv4UI._gridY;
        faltan = size;
        FramesUsed = new Imagensish[size];
        FramesUsedHD = new Imagensish[size];
        CheckedIndex = new HashSet<>();
        OrderedTiles = new HashMap<>();
        Pending = new ArrayList<>();
        rand = new int[IEv4UI._numFrames];
        
        for (int ii = 0; ii < IEv4UI._numFrames; ii++)
        {
            rand[ii] = ii;
        }
        
        for (int ii = IEv4UI._numFrames-1; ii >=0; ii--)
        {
            int shuffleIndex = (int)(Math.random() * (ii +1));
            int swap = rand[ii];
            rand[ii] = rand[shuffleIndex];
            rand[shuffleIndex] = swap;
        }
        
        for (int i = 0; i < IEv4UI._locality; i++)
        {
            Pending.add(new ArrayList<>());
        }

        this.Img = Img;
    }

    public void TileImage()
    {

        ArrayList<Imagensish> aux;
        for (int yy = 0, initY = 0; yy < IEv4UI._gridY; yy++, initY += IEv4UI._gridHeight)
        {
            for (int xx = 0, initX = 0; xx < IEv4UI._gridX; xx++, initX += IEv4UI._gridWidth)
            {

                int index = yy * IEv4UI._gridX + xx;
                Imagensish Piece = new Imagensish(new Mat(this.Img, new Rect(initX, initY, IEv4UI._ExWidth, IEv4UI._ExHeigth)), index);
                int Magnitude = Piece.getMagnitude();

                if (OrderedTiles.containsKey(Magnitude))
                {
                    aux = OrderedTiles.get(Magnitude);
                } else
                {
                    aux = new ArrayList<>();
                    OrderedTiles.put(Integer.valueOf(Magnitude), aux);
                }
                aux.add(Piece);
            }
        }
    }

    private int nextFrame = 0;

    public int FindFrame()
    {
        int frame = rand[nextFrame++];
        CheckedIndex.add(frame);
        return frame;
    }

    public void MatchTiles()
    {
        while (OrderedTiles.size() > 0)
        {
            int frame = FindFrame();
            Imagensish Comp = null;
            Mat grabbedHD = null, lowPoly = null;

            try
            {
                IEv4UI._grabber.setFrameNumber(frame);
                grabbedHD = Java2DFrameUtils.toMat(IEv4UI._grabber.grab());

            } catch (Exception e)
            {
                e.printStackTrace();
                System.out.println(e.getMessage() + "error al agarrar frame");
            }
            lowPoly = IEv4UI.resize(grabbedHD, IEv4UI._gridWidth);

            Comp = new Imagensish(lowPoly, frame);
            int CompMag = Comp.getMagnitude();
            System.out.println("inicio de el frame " + frame);
            if (OrderedTiles.containsKey(CompMag))
            {
                System.out.println("Posible Candidato frame----------------------------------");
                ArrayList<Imagensish> ComparisonList = OrderedTiles.get(CompMag);
                status = false;
                int maxLikeliness = -1;
                for (int ii = 0; ii < ComparisonList.size() && !status; ii++)
                {
                    int likelyness = AlikeImgs(ComparisonList.get(ii), Comp);
                    status = likelyness >= IEv4UI._locality;
                    if (status)
                    {
                        this.FramesUsed[ComparisonList.get(ii).getFrameNumber()] = Comp;

                        ComparisonList.remove(ii);
                        int imgLeft = ComparisonList.size();
                        if (imgLeft == 0)
                        {
                            OrderedTiles.remove(CompMag);
                        }
                        System.out.println(--size+") Faltan " + imgLeft + "Frames y " + OrderedTiles.size()+"pedazos");
                    }
                    else
                    {
                        System.out.println("rejected");
                        maxLikeliness = Math.max(maxLikeliness, likelyness);

                    } 
                }//End For
                if(!status){
                    LookAtSides(Comp);
                }
                if(!status && maxLikeliness >= 0)
                {
                    System.out.println("Para despues");
                    Pending.get(maxLikeliness).add(Comp);           
                }

                if ((this.CheckedIndex.size() + 1) % IEv4UI._change == 0)
                {
                    LowerTheLevel();
                }
                System.out.println("Fin Posible candidato frame -----------------------------");
            }//End Tile Matching

            System.out.println("fin de el frame " + frame);

        }
    }

    public void LookAtSides(Imagensish Comp){
        int CompMag = Comp.getSide() + Comp.getMagnitude();
        
        if (Comp.getSide()!= 0 && OrderedTiles.containsKey(CompMag))
        {
            System.out.println("Posible LADO Candidato --------------------------------------");
            ArrayList<Imagensish> ComparisonList = OrderedTiles.get(CompMag);
            int maxLikeliness = -1;
            for (int jj = 0; jj < ComparisonList.size(); jj++)
            {
                int likelyness = AlikeImgs(ComparisonList.get(jj), Comp);

                if (likelyness >= IEv4UI._locality)
                {
                    this.FramesUsed[ComparisonList.get(jj).getFrameNumber()] = Comp;
                    ComparisonList.remove(jj);
                    int imgLeft = ComparisonList.size();
                    if (imgLeft == 0)
                    {
                        OrderedTiles.remove(CompMag);
                    }
                    System.out.println(--size+")El lado lo logró Faltan " + imgLeft + "Frames");
                    break;
                }
                else
                {
                    System.out.println("rejected");
                    maxLikeliness = Math.max(maxLikeliness, likelyness);

                } 
            }//End For
            
            if(!status && maxLikeliness >= 0)
            {
                System.out.println("Para despues");
                Pending.get(maxLikeliness).add(Comp);           
            }
            System.out.println("Fin Posible LADO Candidato -------------------------------------");

        }//End Tile Matching
    }
    
    public void LowerTheLevel()
    {
        System.out.println("Bajando el nivel");
        IEv4UI._locality--;
        if (IEv4UI._locality >= 0)
        {
            ArrayList<Imagensish> viejas = Pending.remove(IEv4UI._locality);
            for (int ii = 0; ii < viejas.size(); ii++)
            {
                Imagensish Comp = viejas.get(ii);
                int CompMag = Comp.getMagnitude();

                if (OrderedTiles.containsKey(CompMag))
                {
                    System.out.println("Posible Viejo Candidato --------------------------------------");
                    ArrayList<Imagensish> ComparisonList = OrderedTiles.get(CompMag);

                    for (int jj = 0; jj < ComparisonList.size(); jj++)
                    {
                        int likelyness = AlikeImgs(ComparisonList.get(jj), Comp);

                        if (likelyness >= IEv4UI._locality)
                        {
                            this.FramesUsed[ComparisonList.get(jj).getFrameNumber()] = Comp;
                            ComparisonList.remove(jj);
                            int imgLeft = ComparisonList.size();
                            if (imgLeft == 0)
                            {
                                OrderedTiles.remove(CompMag);
                            }
                            System.out.println(--size+")El viejo lo logró Faltan " + imgLeft + "Frames");
                            break;
                        }
                    }//End For
                    System.out.println("Fin Posible Viejo Candidato -------------------------------------");

                }//End Tile Matching
            }
        }
    }

    public Mat GenerateMosaic()
    {
        TileImage();
        MatchTiles();
        System.out.println("Generating mosaic");
        Mat aux = null, aux1 = null;
        int i = 0;
        for (int jj = 0; jj < this.FramesUsed.length; jj++)
        {
            if ((jj % IEv4UI._gridX) == 0)
            {
                if (jj != 0)
                {
                    if (i == 0)
                    {
                        aux1 = aux.clone();
                        i++;
                    } else
                    {
                        vconcat(aux1, aux, aux1);
                    }
                }
                aux = this.FramesUsed[jj].getImg().clone();
            } else
            {
                // if((jj%IEv3UI._gridX)!=0){
                hconcat(aux, FramesUsed[jj].getImg(), aux);
                //}
            }
        }
        vconcat(aux1, aux, aux1);
        return aux1;
    }

    static double f(double t)
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

    public static int AlikeImgs(Imagensish A, Imagensish B)
    {
        if (IEv4UI._locality < 0)
        {
            return Integer.MAX_VALUE;
        }
        Color[] promA = A.getProm(), promB = B.getProm();
        int local = 0;
        double dist;
        if (IEv4UI._euclidianComp)
        {
            dist = EUDistance(promA[0], promB[0]);
            if (dist > IEv4UI._minTolerance)
            {
                System.out.println(promA[0] + "vs" + promB[0] + " = " + dist);
                return Integer.MIN_VALUE;
            } else
            {
                if (IEv4UI._locality == 0)
                {
                    System.out.println("Averageing");
                    return 0;
                }
            }
            for (int ii = 1; ii < promA.length && local < IEv4UI._locality; ii++)
            {
                dist = EUDistance(promA[ii], promB[ii]);
                if (dist <= IEv4UI._minLocalTolerance)
                {
                    local++;
                }
            }
        } else
        {
            dist = Imagensish.LABDistance(promA[0], promB[0]);
            if (dist > IEv4UI._minTolerance)
            {
                System.out.println(promA[0] + "vs" + promB[0] + " = " + dist);
                return Integer.MIN_VALUE;
            } else
            {
                if (IEv4UI._locality == 0)
                {
                    System.out.println("Averageing");
                    return 0;
                }
            }
            for (int ii = 1; ii < promA.length && local < IEv4UI._locality; ii++)
            {
                dist = Imagensish.LABDistance(promA[ii], promB[ii]);
                if (dist <= IEv4UI._minLocalTolerance)
                {
                    local++;
                }
            }
        }
        return local;
    }

}
