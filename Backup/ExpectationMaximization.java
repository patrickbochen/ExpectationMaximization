//Change observations at instance variables and main
//Command prompt line: java ExpectationManagement digits100.txt label.txt

//compare gammaij to out label array

//import java.util.Random;

public class ExpectationMaximization
{
    //Declare and initializes variables 
    
    //based of user and data input
    private static int observations = 100;
    private static int imglength = 28;
    private static int clusters = 5;
    
    private static int dimensions = imglength*imglength;
    private double mean[][];
    private double gammaij[][];
    private double sum;
   
    //Constructor
    public ExpectationMaximization(double[][] m, double[][] gij, double s)
    {
        mean = m;
        gammaij = gij;
        sum = s;
    }
    
    public static ExpectationMaximization kmeans(int nimage[][])
    {
        //Variables
        double mean[][] = new double [clusters][dimensions];
        double nextmean[][] = new double[clusters][dimensions];
        double gammaij[][] = new double [observations][clusters];
        
        int counter = 0;
        double convergence = 1;
        
        /*
        //Random pixel values to initialize the cluster means
        Random randomGen = new Random();
        for(int x = 0; x< 5; x++)
        {
        for(int y = 0; y < dimensions; y++)
        {
            nextmean[x][y] = randomGen.nextInt(100)+1;
        }
        }*/
        
        //Assign the first mean of clusters to preset data in the clusters
        for(int d = 0; d < dimensions; d++)
        {
            nextmean[0][d] = nimage[0][d]; //7
            nextmean[1][d] = nimage[1][d]; //2
            nextmean[2][d] = nimage[2][d]; //0
            nextmean[3][d] = nimage[3][d]; //5
            nextmean[4][d] = nimage[77][d]; //6
        }
        
        //Determine interation
        while(convergence > .000001)
        {
            //Initialize gammaij to 0 every loop
            gammaij = new double [observations][clusters];
            //Update mean to equal nextmean
            for(int j = 0; j <clusters ; j++)
            {
                for(int d = 0; d < dimensions; d++)
                {
                    mean[j][d] = nextmean[j][d];
                }
            }
            
            //E-Step
            for(int i = 0; i<observations; i++)
            {
                //Defensive copy of current image
                double xi [] = new double[dimensions];
                for(int d = 0; d < dimensions; d++)
                {
                    xi[d] = nimage[i][d];
                }
                //Initialze the minimum distance to a cluster to infinity
                double champmin = Double.POSITIVE_INFINITY;
                //Index of closest cluster
                double closestj = 0;
                
                //Finds the closest cluster
                for(int j = 0; j<clusters; j++)
                {
                    //Store mean[][j] into a 1-d array
                    double x2col[] = new double [dimensions];
                    for(int d = 0; d<dimensions; d++)
                    {
                        x2col[d] = mean[j][d];
                    }
                    //Euclidian distance between cluster mean and image
                    double dist = eudist(xi, x2col);
                    //Update current champion
                    if(dist < champmin)
                    {
                        champmin = dist;
                        closestj = j;
                    }
                }
                //Update gammij array
                gammaij[i][(int)closestj] = 1;
            }
            
            //M Step
            for(int j = 0; j<clusters; j++)
            {
                //Initialize the sum(gamma*x)
                double sumgammaijx[] = new double[dimensions];
                //Initialize sum(gamma) to 1 to avoid problem when diving by 0
                double sumgammaij = 1;
                
                for(int i = 0; i<observations; i++)
                {
                    for(int d = 0; d < dimensions; d++)
                    {
                        sumgammaijx[d] = sumgammaijx[d] + gammaij[i][j]*nimage[i][d];
                    }
                    sumgammaij = sumgammaij + gammaij[i][j];
                }
                //Mean for the next step
                for(int d = 0; d < dimensions; d++)
                {
                    nextmean[j][d] = sumgammaijx[d] / sumgammaij;
                }
            }
            //Counter for loop
            System.out.print(counter + "   "); //////////////////////////////////////////////////////////////////
            counter++;
            
            //Temporary 1-d arrays for euclidean distance method
            double tempmean1[] = new double [dimensions];
            double tempmean2[] = new double [dimensions];
            double tempmean3[] = new double [dimensions];
            double tempmean4[] = new double [dimensions];
            double tempmean5[] = new double [dimensions];
            double tempnextmean1[] = new double [dimensions];
            double tempnextmean2[] = new double [dimensions];
            double tempnextmean3[] = new double [dimensions];
            double tempnextmean4[] = new double [dimensions];
            double tempnextmean5[] = new double [dimensions];
            //Initialze the arrays for each cluster from mean and next mean
            for(int d = 0; d < dimensions; d++)
            {
                tempmean1[d] = mean[0][d];
                tempmean2[d] = mean[1][d];
                tempmean3[d] = mean[2][d];
                tempmean4[d] = mean[3][d];
                tempmean5[d] = mean[4][d];
                tempnextmean1[d] = nextmean[0][d];
                tempnextmean2[d] = nextmean[1][d];
                tempnextmean3[d] = nextmean[2][d];
                tempnextmean4[d] = nextmean[3][d];
                tempnextmean5[d] = nextmean[4][d];
            }
            
            //Has convergence occured yet, euclidian distance
            convergence = eudist(tempmean1,tempnextmean1) + 
                eudist(tempmean2,tempnextmean2) + 
                eudist(tempmean3,tempnextmean3) + 
                eudist(tempmean4,tempnextmean4) + 
                eudist(tempmean5,tempnextmean5);
            
            System.out.println("conv: " + convergence); /////////////////////////////////////////////
        }
        
        //object function calculation
        double sum = 0;
        //For every image and every potential cluster
        for(int i = 0; i<observations; i++)
        {
            for(int j = 0; j <clusters; j++)
            {
                //If te image belong to the cluster
                if(gammaij[i][j] == 1)
                {
                    //Distance between image and mean of cluster
                    double tempnimage [] = new double[dimensions];
                    double tempnextmean [] = new double[dimensions];
                    for(int d = 0; d < dimensions; d ++)
                    {
                        tempnimage[d] = nimage[i][d];
                        tempnextmean[d] = nextmean[j][d];
                    }
                    double dist = eudist(tempnimage,tempnextmean);
                    sum += dist;
                }
            }
        }
        System.out.println("Sum:   " + sum);////////////////////////////////////////////////////////
        
        //Returns an EM object
        ExpectationMaximization r = new ExpectationMaximization(nextmean, gammaij, sum);
        return r;
        
    }
    
    public static double eudist (double x1[], double x2[])
    {
        double temp [] = new double [x1.length];
        double sum = 0;
        for(int i = 0; i<x1.length; i++)
        {
            temp[i] = x1[i] - x2[i];
            sum += (temp[i]*temp[i]);
        }
        return sum;
    }
    
    public static void label(double [][] gammaij, int []label)
    {
        int counter = 0;
        for(int i = 0; i < observations; i++)
        {
            int temp=0;
            switch(label[i])
            {
                case 7:
                    temp = 0;
                    break;
                case 2:
                    temp = 1;
                    break;
                case 0:
                    temp = 2;
                    break;
                case 5:
                    temp = 3;
                    break;
                case 6:
                    temp = 4;
                    break;
            }
                    
            for(int j = 0; j < clusters; j++)
            {
                if((int)gammaij[i][j] == 1)
                {
                    
                    if(temp != j)
                    {
                        counter++;
                        System.out.println("Obs: " + i + " ");
                    }
                }
            }
        }
        System.out.println(counter);
    }
    
    public static void main(String [] args)
    {
        In indata = new In(args[0]);
        In inlabel = new In(args[1]);
        //Declare and initialize variables
        int observations = 100;
        int dimensions = 784;
        int increase = 10;
        
        //Read from input file and stores in array
        int nimage[][] = new int [observations][dimensions];
        for(int i = 0; i< observations; i++)
        {
            for(int j = 0; j < dimensions; j++)
            {
                nimage[i][j] = indata.readInt();
            }
        }
        
        ExpectationMaximization test = kmeans(nimage);
        InputVisualizer dr = new InputVisualizer(test.mean, dimensions, observations);
        for(int i = 0; i<clusters; i++)
        {
            dr.draw(i, increase);
        }
        
        int label[] = new int [observations];
        for(int i = 0; i< observations; i++)
        {
            label[i] = inlabel.readInt();
        }
        label(test.gammaij , label);
    }
}
