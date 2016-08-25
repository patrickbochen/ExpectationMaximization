import java.awt.Color;

public class EdgeDetection
{   
    public static void main(String[] args)
    {
        In indata = new In(args[0]);
        int size = 28;
        double nimage[][] = new double [size][size];
        double pixelenergy[][] = new double [size][size];
        
        Picture origpicture = new Picture(5*size,5*size);
        for(int i = 0; i< size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                nimage[i][j] = indata.readInt();
                Color color = new Color(255 - (int)(nimage[i][j]),
                                        255 - (int)(nimage[i][j]),
                                        255 - (int)(nimage[i][j]));
                for(int x = 0; x< 5; x++)
                {
                    for(int y = 0; y<5; y++)
                    {
                        origpicture.set(5*j+x,5*i+y, color); 
                    }
                }
            }
        }
        origpicture.show();
        ///////////////////////////////////////////////////////////////////////
        double xenergy = 0, yenergy = 0;
        double max = -1;
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                if(i == 0)
                    xenergy = Math.pow((nimage[i+1][j] - nimage[size-1][j]),2);
                else if (i == size-1)
                    xenergy = Math.pow((nimage[0][j] - nimage[i-1][j]),2);
                else
                    xenergy = Math.pow((nimage[i-1][j] - nimage[i+1][j]),2);
                
                if(j == 0)
                    yenergy = Math.pow((nimage[i][j+1] - nimage[size-1][j]),2);
                else if (j == size-1)
                    yenergy = Math.pow((nimage[i][0] - nimage[i][j-1]),2);
                else
                    yenergy = Math.pow((nimage[i][j-1] - nimage[i][j+1]),2);
                
                pixelenergy[i][j] = xenergy + yenergy;
                if(pixelenergy[i][j] > max)
                    max = pixelenergy[i][j];
            }
        }
        Picture picture = new Picture(5*size,5*size);
        for(int i = 0; i< size; i++)
        {
            for(int j = 0; j<size; j++)
            {
                Color color = new Color(255 - (int)(pixelenergy[i][j] *255/max),
                                        255 - (int)(pixelenergy[i][j] *255/max),
                                        255 - (int)(pixelenergy[i][j] *255/max)); 
                for(int x = 0; x< 5; x++)
                {
                    for(int y = 0; y<5; y++)
                    {
                        picture.set(5*j+x,5*i+y, color); 
                    }
                }
            }
        }
        picture.show();
        ///////////////////////////////////////////////////////////////////////
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                pixelenergy[i][j]*=255/max;
            }
        }
        double pixelenergy2[][] = new double [size][size];
        Picture picture2 = new Picture(5*size,5*size);
        xenergy = 0;
        yenergy = 0;
        max = -1;
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                if(i == 0)
                    xenergy = Math.pow((pixelenergy[i+1][j] - pixelenergy[size-1][j]),2);
                else if (i == size-1)
                    xenergy = Math.pow((pixelenergy[0][j] - pixelenergy[i-1][j]),2);
                else
                    xenergy = Math.pow((pixelenergy[i-1][j] - pixelenergy[i+1][j]),2);
                
                if(j == 0)
                    yenergy = Math.pow((pixelenergy[i][j+1] - pixelenergy[size-1][j]),2);
                else if (j == size-1)
                    yenergy = Math.pow((pixelenergy[i][0] - pixelenergy[i][j-1]),2);
                else
                    yenergy = Math.pow((pixelenergy[i][j-1] - pixelenergy[i][j+1]),2);
                
                pixelenergy2[i][j] = xenergy + yenergy;
                if(pixelenergy2[i][j] > max)
                    max = pixelenergy2[i][j];
                
            }
        }
        for(int i = 0; i< size; i++)
        {
            for(int j = 0; j<size; j++)
            {
                Color color = new Color(255 - (int)(pixelenergy2[i][j] *255/max),
                                        255 - (int)(pixelenergy2[i][j] *255/max),
                                        255 - (int)(pixelenergy2[i][j] *255/max)); 
                for(int x = 0; x< 5; x++)
                {
                    for(int y = 0; y<5; y++)
                    {
                        picture2.set(5*j+x,5*i+y, color); 
                    }
                }
            }
        }
        picture2.show();
        ///////////////////////////////////////////////////////////////////////
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                pixelenergy2[i][j]*=255/max;
            }
        }
        double pixelenergy3[][] = new double [size][size];
        Picture picture3 = new Picture(5*size,5*size);
        xenergy = 0;
        yenergy = 0;
        max = -1;
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                if(i == 0)
                    xenergy = Math.pow((pixelenergy2[i+1][j] - pixelenergy2[size-1][j]),2);
                else if (i == size-1)
                    xenergy = Math.pow((pixelenergy2[0][j] - pixelenergy2[i-1][j]),2);
                else
                    xenergy = Math.pow((pixelenergy2[i-1][j] - pixelenergy2[i+1][j]),2);
                
                if(j == 0)
                    yenergy = Math.pow((pixelenergy2[i][j+1] - pixelenergy2[size-1][j]),2);
                else if (j == size-1)
                    yenergy = Math.pow((pixelenergy2[i][0] - pixelenergy2[i][j-1]),2);
                else
                    yenergy = Math.pow((pixelenergy2[i][j-1] - pixelenergy2[i][j+1]),2);
                
                pixelenergy3[i][j] = xenergy + yenergy;
                if(pixelenergy3[i][j] > max)
                    max = pixelenergy3[i][j];
                
            }
        }
        for(int i = 0; i< size; i++)
        {
            for(int j = 0; j<size; j++)
            {
                Color color = new Color(255 - (int)(pixelenergy3[i][j] *255/max),
                                        255 - (int)(pixelenergy3[i][j] *255/max),
                                        255 - (int)(pixelenergy3[i][j] *255/max)); 
                for(int x = 0; x< 5; x++)
                {
                    for(int y = 0; y<5; y++)
                    {
                        picture3.set(5*j+x,5*i+y, color); 
                    }
                }
            }
        }
        picture3.show();
        ///////////////////////////////////////////////////////////////////////
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                pixelenergy3[i][j]*=255/max;
            }
        }
        double pixelenergy4[][] = new double [size][size];
        Picture picture4 = new Picture(5*size,5*size);
        xenergy = 0;
        yenergy = 0;
        max = -1;
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                if(i == 0)
                    xenergy = Math.pow((pixelenergy3[i+1][j] - pixelenergy3[size-1][j]),2);
                else if (i == size-1)
                    xenergy = Math.pow((pixelenergy3[0][j] - pixelenergy3[i-1][j]),2);
                else
                    xenergy = Math.pow((pixelenergy3[i-1][j] - pixelenergy3[i+1][j]),2);
                
                if(j == 0)
                    yenergy = Math.pow((pixelenergy3[i][j+1] - pixelenergy3[size-1][j]),2);
                else if (j == size-1)
                    yenergy = Math.pow((pixelenergy3[i][0] - pixelenergy3[i][j-1]),2);
                else
                    yenergy = Math.pow((pixelenergy3[i][j-1] - pixelenergy3[i][j+1]),2);
                
                pixelenergy4[i][j] = xenergy + yenergy;
                if(pixelenergy4[i][j] > max)
                    max = pixelenergy4[i][j];
                
            }
        }
        for(int i = 0; i< size; i++)
        {
            for(int j = 0; j<size; j++)
            {
                Color color = new Color(255 - (int)(pixelenergy4[i][j] *255/max),
                                        255 - (int)(pixelenergy4[i][j] *255/max),
                                        255 - (int)(pixelenergy4[i][j] *255/max)); 
                for(int x = 0; x< 5; x++)
                {
                    for(int y = 0; y<5; y++)
                    {
                        picture4.set(5*j+x,5*i+y, color); 
                    }
                }
            }
        }
        picture4.show();
    }
}