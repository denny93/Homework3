import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController extends JComponent implements MouseListener {
        int mode  = 1;
        int x = 0 ;
        int y = 0 ;
        int isOK = 0;
        int first_i = 0 ;
        int first_j = 0;
        @Override
        public void mouseClicked(MouseEvent e) {
                if (mode == 1)
                {
                        if(CHeckPOsitionAvatar(e.getX() ,e.getY() ) == false)
                        {
                                mode = 1;
                                return;
                        }
                        x = e.getX();
                        y = e.getY();
                        mode = 2;
                        for (int i = 0 ; i < 5 ; i++)
                        {
                                for (int j = 0 ; j < 5 ; j++)
                                {
                                        if ((Work.array_model[i][j].start_x <= x && Work.array_model[i][j].end_x >= x) &&
                                                (Work.array_model[i][j].start_y <= y && Work.array_model[i][j].end_y >= y))
                                        {
                                                if(Work.array_model[i][j].avatar.type == -2)
                                                {
                                                        mode = 1;
                                                        return;
                                                }
                                                first_i = i;
                                                first_j = j;
                                                isOK++;
                                        }
                                }
                        }
                }
                else
                {

                          int second_j = 0 ; int second_i = 0 ;
                          int x_s = e.getX();
                        int y_s = e.getY();
                        for (int i = 0 ; i < 5 ; i++)
                        {
                                if (isOK > 1)
                                {break;}
                                for (int j = 0 ; j < 5 ; j++ )
                                {
                                        if((Work.array_model[i][j].start_x <= x_s && Work.array_model[i][j].end_x >= x_s) &&
                                                (Work.array_model[i][j].start_y <= y_s && Work.array_model[i][j].end_y >= y_s))
                                        {
                                                second_i = i;
                                                second_j = j;
                                                isOK++;
                                        }
                                }
                        }
                        if (CheckClick(second_i,second_j) == false)
                        {
                                isOK--;
                                mode = 1;
                                return;
                        }
                        Work.NullColor();
                        if (isOK > 1)
                        {
                                MoveAvatar(first_i,first_j,second_i,second_j);
                                isOK = 0;
                        }
                        mode = 1;
                }
        }

        private boolean checkClick(int second_i, int second_j) {
                if (Work.array[second_i][second_j] == 10)
                {
                        return  true;
                }
                else
                {
                        return false;
                }
        }

        private void moveAvatar(int first_i, int first_j, int second_i, int second_j) {
                if(Work.array_model[second_i][second_j].avatar != null) {
                        if (Work.array_model[second_i][second_j].avatar.getClass().getName() == "Turtle") {
                                Work.array_model[second_i][second_j].avatar = null;
                                Work.array_model[first_i][first_j].avatar = null;
                                Work.AddElements(Work.gs, Work.array_model, true);
                                return;
                        }
                }
                Avatar model = Work.array_model[first_i][first_j].avatar;
                Work.array_model[first_i][first_j].avatar = Work.array_model[second_i][second_j].avatar;
                Work.array_model[second_i][second_j].avatar = model;
                Work.AddElements(Work.gs, Work.array_model ,true);
        }

        private boolean checkPositionAvatar(int x, int y) {

                boolean isok = false;
                int i_s = 0;
                int j_s = 0;
                for (int i = 0 ; i < 5 ; i++)
                {
                        if (isok  == true)
                        {break;}
                        for (int j = 0 ; j < 5 ; j++ )
                        {
                                if ((Work.array_model[i][j].start_x <= x && Work.array_model[i][j].end_x >= x) &&
                                        (Work.array_model[i][j].start_y <= y && Work.array_model[i][j].end_y >= y))
                                {
                                        if(Work.array_model[i][j].avatar != null)
                                        {
                                                i_s = i;
                                                j_s = j;
                                                isok = true;
                                                break;
                                        }
                                }
                        }
                }

                if (isok== true)
                {
                        if(Work.array_model[i_s][j_s].avatar.getClass().getName() == "Grad")
                        {
                                GardColorMode(i_s, j_s);
                        }
                        else
                        {
                                LiderrMove(i_s , j_s);
                        }
                }
                Work.AddElements(Work.gs, Work.array_model ,true);
                return isok;
        }

        private void liderrMove(int i_s, int j_s) {
                int last_i = i_s ;
                int last_j  = j_s ;
                for (int i = i_s-1 ; i > -1 ; i--)
                {
                        if (Work.array_model[i][j_s].avatar != null)
                        {
                                break;
                        }
                        last_i = i;
                        if ( i== 2 && j_s == 2)
                        {
                                break;
                        }
                }
                ChangeColorGard(last_i, j_s, 0);
                last_i = i_s;
                for (int i = i_s+1 ; i < 5 ; i++)
                {
                        if (Work.array_model[i][j_s].avatar != null)
                        {
                                break;
                        }
                        last_i = i;
                        if (i == 2 && j_s == 2)
                        {
                                break;
                        }
                }
                ChangeColorGard(last_i, j_s, 0);
                last_i = i_s;
                for (int j = j_s -1; j > -1 ; j--)
                {
                        if (Work.array_model[i_s][j].avatar != null)
                        {
                                break;
                        }
                        last_j = j;
                        if (i_s == 2 && j == 2)
                        {
                                break;
                        }
                }
                ChangeColorGard(i_s, last_j, 0);
                last_j = j_s;
                for (int j = j_s+1 ; j < 5 ; j++)
                {
                        if (Work.array_model[i_s][j].avatar != null)
                        {
                                break;
                        }
                        last_j = j;
                        if (i_s == 2 && j == 2)
                        {
                                break;
                        }
                }
                ChangeColorGard(i_s, last_j, 0);
        }

        private void gardColorMode(int i_s, int j_s) {
                if (i_s + 1 < 5 )
                {
                        ChangeColorGard(i_s + 1, j_s, 1);
                }
                if (i_s - 1 > -1 )
                {
                        ChangeColorGard(i_s -1, j_s, 1);
                }
                if (j_s + 1 < 5)
                {
                        ChangeColorGard(i_s , j_s+1 , 1);
                }
                if (j_s - 1 > -1)
                {
                        ChangeColorGard(i_s , j_s-1, 1);
                }
        }

        private void changeColorGard(int i_s, int j_s , int mode_color) {
                if(Work.array_model[i_s][j_s].avatar == null)
                {
                        if (mode_color == 1)
                        {
                               if(i_s == 2 && j_s == 2)
                               {
                                       return;
                               }
                        }
                        Work.array[i_s][j_s] =10;
                }
                else
                {
                        if(Work.array_model[i_s][j_s].avatar.type == -2)
                        {
                                Work.array[i_s][j_s] =10;
                                return;
                        }
                }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

}