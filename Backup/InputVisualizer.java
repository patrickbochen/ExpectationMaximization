//Put resize into main
import java.awt.Color;

public class InputVisualizer
{
    //Declare static variables
    int dimensions;
    int observations;
    double data[][];
    
    //Constructor
    public InputVisualizer(double arrayData[][], int dimen, int ob)
    {
        dimensions = dimen;
        observations = ob;
        data = arrayData;
    }
    //Draws the images
    public void draw(int choose, int increase)
    {
        //Declare and initializes variables
        int counter = 0; //Cycles through the array
        int imglength = (int)(Math.sqrt(dimensions));
        Picture picture = new Picture(imglength*increase,imglength*increase);
    
        //Enlarged
        for(int x = 0; x< imglength; x++)
        {
            for(int y = 0; y<imglength; y++)
            {
                //Black and white if rgb components are equal
                Color color = new Color(255 - (int)data[choose][counter],
                                        255 - (int)data[choose][counter],
                                        255 - (int)data[choose][counter]); // only 1st picture
                counter++;
                
                for(int i = 0; i< increase; i++)
                {
                    for(int j = 0; j<increase; j++)
                    {
                        picture.set(increase*y + i, increase*x+j, color); //switched y and x to make picture object upright
                    }
                }
            }
        }
        picture.show(); //Show the picture object
    }
    
    public static void main(String[] args)
    {
        //Declare and initialize variables
        int observations = 100;
        int dimensions = 784;
        double data[][] = new double [observations][dimensions];
        //Read from input file and stores in array
        for(int i = 0; i< observations; i++)
        {
            for(int j = 0; j < dimensions; j++)
            {
                data[i][j] = StdIn.readInt();
            }
        }
        
        //Object
        InputVisualizer a = new InputVisualizer(data, dimensions, observations);
        for(int i = 0; i<20; i++)
        {
            a.draw(i, 10);
        }
        
    }
}