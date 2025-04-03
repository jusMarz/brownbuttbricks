import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BrickLayout {

    private ArrayList<Brick> bricks;
    private int[][] brickLayout;
    private int cols;

    public BrickLayout(String fileName, int cols, boolean dropAllBricks) {
        this.cols = cols;
        ArrayList<String> fileData = getFileData(fileName);
        bricks = new ArrayList<Brick>();
        for (String line : fileData) {
            String[] points = line.split(",");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            Brick b = new Brick(start, end);
            bricks.add(b);
        }
        brickLayout = new int[bricks.size()][cols];
        if (dropAllBricks) {
            while (bricks.size() != 0) {
                doOneBrick();
            }
        }
    }

    public void doOneBrick() {
        if (bricks.size() != 0) {
            Brick b = bricks.remove(0);
            int xCord = b.getStart();
            int yCord = 0;
            boolean foundspot1 = false;
            while(!foundspot1) // check each row
            {
                    for(int i = b.getStart(); i <= b.getEnd(); i ++) // check each collumn
                    {
                        if((brickLayout[yCord][i] == 1))
                        {
                            foundspot1 = true;
                        }
                    }

                if (foundspot1)
                {
                    yCord--;
                    break;
                }
                yCord++;
                if(yCord == brickLayout.length)
                {
                    yCord--;
                    break;
                }
            }
            for(int i = 0; i <= b.getEnd() - b.getStart(); i++)
            {
                brickLayout[yCord][b.getStart() + i] = 1;
            }
        }
    }

    public ArrayList<String> getFileData(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        return fileData;
    }

    public void printBrickLayout() {
        for (int r = 0; r < brickLayout.length; r++) {
            for (int c = 0; c < brickLayout[0].length; c++) {
                System.out.print(brickLayout[r][c] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkBrickSpot(int r, int c) {
        if (brickLayout[r][c] == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public int[][] getBrickLayout() {
        return brickLayout;
    }

    public void BrickFall()
    {

        for(int i = brickLayout.length - 1; i > 0; i--)
        {

            for(int b = 0;b < brickLayout[0].length;b++)
            {

                if((brickLayout[i][b] == 0) && (brickLayout[i - 1][b] == 1))
                {

                    int len = 0;
                    for(int l = 0; (b + l < brickLayout[0].length)&&(brickLayout[i - 1][b + l] == 1); l++)
                    {
                        len++;
                    }
                    boolean isSpace = true;
                    for(int l2 = 0; (l2 < len) && (isSpace); l2++ )
                    {
                        if (brickLayout[i][b + l2] != 0)
                        {
                            isSpace = false;
                        }
                    }
                    if (isSpace)
                    {

                        System.out.println(len);
                        for(int p = 0; p < len; p++)
                        {
                            brickLayout[i - 1][b + p] = 0;
                            brickLayout[i][b + p] = 1;
                        }
                    }
                    b += len;
                }
            }
        }

        if (bricks.size() != 0)
        {
            Brick b = bricks.remove(0);
            for (int i = 0; i <= b.getEnd() - b.getStart(); i++)
            {
                brickLayout[0][b.getStart() + i] = 1;
            }
        }
    }
}