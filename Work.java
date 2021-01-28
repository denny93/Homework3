import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.*;

public class Work extends JPanel {
    public static Graphics gs;
    public static ShemaColorAndAvatar[][] array_model;

    public  static int[][] array = {
            {1,4,3,4,1},
            {2,2,3,2,2},
            {3,3,3,3,3},
            {2,2,3,2,2},
            {4,1,3,1,4}
    };
    public  static int[][] base = {
            {1,4,3,4,1},
            {2,2,3,2,2},
            {3,3,3,3,3},
            {2,2,3,2,2},
            {4,1,3,1,4}
    };
    static boolean first = true;
    static int padding = 25;
    static JFrame frame;

    public  void paint(Graphics g){
        gs = g;
        AddElements(g, array_model, false);
    }

    public  static void addElements(Graphics g, ShemaColorAndAvatar[][] array_mode, boolean modes) {
        if (modes)
        {
            frame.repaint();
        }
        else
        {
            if (first) {
                GenerateAvatarPOsition();
            }
        }
        int x = 0 ;
        int y = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){

                if (array[i][j] == 1)
                {
                    g.setColor(Color.red);
                }
                else if (array[i][j] == 2) {
                        g.setColor(Color.lightGray);
                }
                else if (array[i][j] == 3)
                {
                    g.setColor(Color.white);
                }
                else if(array[i][j] == 4)
                {
                    g.setColor(Color.gray);
                }
                else
                {
                    g.setColor(Color.orange);
                }
                g.fillRect(x, y, 100, 100);

                g.setColor(Color.black);
                g.drawRect(x, y, 100, 100);
                ShemaColorAndAvatar mdoel = array_model[i][j];
                mdoel.start_y =y;
                mdoel.start_x =x;
                mdoel.end_x =x + 100;
                mdoel.end_y = y+100;
                array_model[i][j] = mdoel;
                AddAvatarsintable(g , array_model , x, y , i , j) ;
                AddCeterElementIndataTable(g,x,y, i , j );
                x+=100;

            }
            y+= 100;
            x = 0 ;
        }
        checkWinner();
    }

    private static void checkWinner() {
        String textWinner = "";
        boolean isok = false;
        if (array_model[2][2].avatar != null) {
            if (array_model[2][2].avatar.getClass().getName() == "Lider") {
                if (array_model[2][2].avatar.color == Color.yellow) {
                    textWinner = "Lider yellow ";
                } else {
                    textWinner = "Lider green ";
                }
                isok = true;
            }
        }
        if (isok) {
            joptionPane.showMessageDialog(null, textWinner, "InfoBox: ", joptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void generateAvatarPosition() {
        int max = 8 ;
        int added = 0;
        Random rand = new Random();
        for (int i = 0 ; i < max ; i++)
        {
            int x = rand.nextInt(5);
            int y = rand.nextInt(5);
            if (x == y && x == 2)
            {
                max++;
            }
            else
            {
                if (array_model[x][y].avatar == null)
                {
                    added++;
                    if (added > 4)
                    {
                        array_model[x][y]= CreateGGard(1);
                    }
                    else
                    {
                        array_model[x][y]= CreateGGard(0);
                    }
                    if (added == 8)
                    {
                        first = false;
                        return;
                    }
                }
                else
                {
                    max++;
                }
            }
        }
    }

    public static void nullColor() {
        for (int m = 0 ; m < 5 ; m++)
        {
           for (int  mk = 0 ; mk < 5 ; mk++)
           {
               array[m][mk] = base[m][mk];
           }
        }
    }

    private  static void addCeterElementInDataTable(Graphics g , int x , int y , int i , int j) {
        if (i == j && i == 2) {
            g.setColor(Color.BLACK);
            g.fillOval(x + padding, y + padding, 50, 50);
            g.setColor(Color.gray);
            g.drawOval(x + padding, y + padding, 50, 50);
        }
    }

    private static void addAvatarsInTable(Graphics g, ShemaColorAndAvatar[][] array_model, int x , int  y , int i , int j) {
        if (array_model[i][j].avatar != null)
        {
            if (array_model[i][j].avatar.type == 2 || array_model[i][j].avatar.type == -2)
            {
                g.setColor(array_model[i][j].avatar.color);
                g.fillOval(x+padding, y+padding, 50, 50);
                g.setColor(array_model[i][j].avatar.border);
                g.drawOval(x+padding, y+padding , 50, 50);
            }
            else
            {
                g.setColor(array_model[i][j].avatar.color);
                g.fillRect(x+padding, y+padding, 50, 50);
                g.setColor(array_model[i][j].avatar.border);
                g.drawRect(x+padding, y+padding , 50, 50);
            }
        }
    }

    private static void changeCordinates(int x, int y, int i, int j) {

    }

    public static void create(){
        frame = new JFrame();
        frame.setSize(600,600);
        frame.getContentPane().add(new Work());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Component mouseClick = new MouseController()  ;
        frame.addMouseListener((MouseListener) mouseClick);
    }
    public static void main(String[] args)
    {
        array_model =
                new ShemaColorAndAvatar[][]{C
                        {createNullElement(), createNullElement(), createNullElement(), createNullElement(), createLider(1)},
                        {createNullElement(), createNullElement(),createNullElement(), createNullElement(), createNullElement()},
                        {createTurtle(), createNullElement(), createNullElement(), createNullElement(), createTurtle()},
                        {createNullElement(), createNullElement(), createNullElement(), createNullElement(), createNullElement()},
                        {createLider(0),   createNullElement(), createNullElement(), createNullElement(),createNullElement()}
                };
        Create();
    }

    private static ShemaColorAndAvatar createTurtle() {
        return new ShemaColorAndAvatar(0,0,0,0 , new Turtle("turtle", 2 , Color.WHITE , Color.RED)) ;

    }

    private static ShemaColorAndAvatar createNullElement() {
        return new ShemaColorAndAvatar(0,0,0,0, null);
    }

    private static ShemaColorAndAvatar createGGard(int i) {
        if (i == 1)
        {
            return new ShemaColorAndAvatar(0,0,0,0 , new Grad("gard", 1 , Color.yellow , Color.green)) ;
        }
        else
        {
            return new ShemaColorAndAvatar(0,0,0,0 , new Grad("gard",  2 , Color.green , Color.yellow));
        }
    }

    private static ShemaColorAndAvatar createLider(int i) {
        if (i == 1)
        {
            return new ShemaColorAndAvatar(0,0,0,0 , new Lider("lider",  1 , Color.yellow , Color.black));
        }
        else
        {
            return new ShemaColorAndAvatar(0,0,0,0 ,  new Lider("lider",  2 , Color.green , Color.black));
        }
    }


}
