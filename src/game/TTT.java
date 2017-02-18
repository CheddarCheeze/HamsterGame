/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TTT {

public static int main(String[] args) {

    // Main CLass

    Control C = new Control(); 

    return 0;
}

}


class Control extends JFrame {

/*
this is the main controler that controls
and combines different components of the game.
*/ 

private Board GameBoard; //Board and Button
private Tools TButtons; // Exit and Reset
//int[][] plays;
//boolean done, aideciding;
    
    Control() {
//        plays = new int[3][3];
//        done = false;
//        aideciding = false;

        setLayout(new BorderLayout());

        GameBoard = new Board();
        TButtons = new Tools();

        TButtons.SetObject(GameBoard);

        add(GameBoard, BorderLayout.CENTER);
        add(TButtons, BorderLayout.SOUTH);

        setVisible(true);
        setSize(350, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}

class GameArray {

    private Board Brd;
    public int GameArr[][];
    private boolean Turn;
    private JButton Pressed;
    boolean winner;

    GameArray(Board B) {

        GameArr = new int[3][3];
        winner = false;
        Brd = B;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GameArr[i][j] = 0;
            }
        }

    }

    public void ArrayInitialize(int i, int j, int Marker) {

        //Set Data sent by Action listener in Board

        GameArr[i][j] = Marker;
        WinCheck(Marker);
    }


    public void WinCheck(int Marker) {

        // if the specified array indexs contain a certain Maker (1,2) on winning pattrens then announce winner

        if ((GameArr[0][0] == Marker && GameArr[0][1] == Marker && GameArr[0][2] == Marker) || (GameArr[1][0] == Marker && GameArr[1][1] == Marker && GameArr[1][2] == Marker) || (GameArr[2][0] == Marker && GameArr[2][1] == Marker && GameArr[2][2] == Marker)) {

            if (Marker == 1) {

                JOptionPane.showMessageDialog(Brd, "CONGRATULATIONS : You Win!");
                View.game1Clear();
                

            } else if (Marker == 2) {

                JOptionPane.showMessageDialog(Brd, "CONGRATULATIONS : AI Wins!");

            }

            winner = true;

        } else if ((GameArr[0][0] == Marker && GameArr[1][0] == Marker && GameArr[2][0] == Marker) || (GameArr[0][1] == Marker && GameArr[1][1] == Marker && GameArr[2][1] == Marker) || (GameArr[0][2] == Marker && GameArr[1][2] == Marker && GameArr[2][2] == Marker)) {

            if (Marker == 1) {
                JOptionPane.showMessageDialog(Brd, "CONGRATULATIONS : You Win!");
                View.game1Clear();

            } else if (Marker == 2) {
                JOptionPane.showMessageDialog(Brd, "CONGRATULATIONS : AI Wins!");

            }

            winner = true;

        } else if ((GameArr[0][0] == Marker && GameArr[1][1] == Marker && GameArr[2][2] == Marker) || (GameArr[2][0] == Marker && GameArr[1][1] == Marker && GameArr[0][2] == Marker)) {

            if (Marker == 1) {
                JOptionPane.showMessageDialog(Brd, "CONGRATULATIONS : You Win!");
                View.game1Clear();

            } else if (Marker == 2) {
                JOptionPane.showMessageDialog(Brd, "CONGRATULATIONS : AI Wins!");

            }
            
            winner = true;

        }

    }

}

class Tools extends JPanel {

private JButton Exit, Reset;
private Board Brd;

Tools() {

    setLayout(new FlowLayout());

    Exit = new JButton("Exit");
    Reset = new JButton("Reset");

    Exit.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent ae) {
            setVisible(false);
        }
    });

    Reset.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent ae) {

            Brd.Reset();

        }

    });

//    add(Exit);
    add(Reset);
}

public void SetObject(Board B) {

    Brd = B;

}

}


class Board extends JPanel implements ActionListener {

private JButton B1, B2, B3, B4, B5, B6, B7, B8, B9; // Buttons
private GameArray GArray; // Class with Array
private boolean Player = false;
private int PlayerMark = 1;

/*
 Player is the Current players turn. if false player 1 will play else player 2
 PlayerMark is to set number to the array "1" for player 1 and "2" for player 2
 */
Board() {
    

    // creates the panel
    setLayout(new GridLayout(3, 3));
    
    B1 = new JButton("");
    B2 = new JButton("");
    B3 = new JButton("");
    B4 = new JButton("");
    B5 = new JButton("");
    B6 = new JButton("");
    B7 = new JButton("");
    B8 = new JButton("");
    B9 = new JButton("");

    SetGame();

    add(B1);
    add(B2);
    add(B3);
    add(B4);
    add(B5);
    add(B6);
    add(B7);
    add(B8);
    add(B9);

    B1.addActionListener(this);
    B2.addActionListener(this);
    B3.addActionListener(this);
    B4.addActionListener(this);
    B5.addActionListener(this);
    B6.addActionListener(this);
    B7.addActionListener(this);
    B8.addActionListener(this);
    B9.addActionListener(this);

}

public void SetGame() {

    GArray = new GameArray(this);

    DefaultText();
    DisableAll(false);
    DisableAll(true);


    Player = false; // default Value
    PlayerMark = 1; // default Value
}

public void Reset() {

    SetGame(); // To Reset the Game
}

public void actionPerformed(ActionEvent E) {

    JButton Pressed = (JButton) E.getSource();

    /*
     if any button is pressed the value is sent to GameArray class
     */
    if (Pressed == B1) {
        GArray.ArrayInitialize(0, 0, PlayerMark);
        SetText(Pressed, Player);     // chaneg button text to "X" or "O" based on player turn
        PlayerMark = SwithTurn(Player); // Swithch Turns
        ButtonDisabler(B1); // Disable pressed Button

    } else if (Pressed == B2) {
        GArray.ArrayInitialize(0, 1, PlayerMark);
        SetText(Pressed, Player);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B2);
    } else if (Pressed == B3) {
        GArray.ArrayInitialize(0, 2, PlayerMark);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B3);

    } else if (Pressed == B4) {
        GArray.ArrayInitialize(1, 0, PlayerMark);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B4);

    } else if (Pressed == B5) {
        GArray.ArrayInitialize(1, 1, PlayerMark);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B5);

    } else if (Pressed == B6) {
        GArray.ArrayInitialize(1, 2, PlayerMark);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B6);
    } else if (Pressed == B7) {
        GArray.ArrayInitialize(2, 0, PlayerMark);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B7);
    } else if (Pressed == B8) {
        GArray.ArrayInitialize(2, 1, PlayerMark);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B8);
    } else if (Pressed == B9) {
        GArray.ArrayInitialize(2, 2, PlayerMark);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B9);
    }
    
    if(PlayerMark == 2 && GArray.winner == false) {
        aiTurn();
    }

}

JButton aithinking() {
    JButton choice;
    
    if(checkB1()) {
        choice = B1;
    }
    else if(checkB2()) {
        choice = B2;
    }
    else if(checkB3()) {
        choice = B3;
    }
    else if(checkB4()) {
        choice = B4;
    }
    else if(checkB5()) {
        choice = B5;
    }
    else if(checkB6()) {
        choice = B6;
    }
    else if(checkB7()) {
        choice = B7;
    }
    else if(checkB8()) {
        choice = B8;
    }
    else if(checkB9()) {
        choice = B9;
    }
    else {
        if(GArray.GameArr[1][1] != 1 && GArray.GameArr[1][1] != 2) {
            choice = B5;
        }
        else if(GArray.GameArr[0][0] != 1 && GArray.GameArr[0][0] != 2) {
            choice = B1;
        }
        else if(GArray.GameArr[0][2] != 1 && GArray.GameArr[0][2] != 2) {
            choice = B3;
        }
        else if(GArray.GameArr[2][0] != 1 && GArray.GameArr[2][0] != 2) {
            choice = B7;
        }
        else if(GArray.GameArr[2][2] != 1 && GArray.GameArr[2][2] != 2) {
            choice = B9;
        }
        else if(GArray.GameArr[0][1] != 1 && GArray.GameArr[0][1] != 2) {
            choice = B2;
        }
        else if(GArray.GameArr[1][0] != 1 && GArray.GameArr[1][0] != 2) {
            choice = B4;
        }
        else if(GArray.GameArr[1][2] != 1 && GArray.GameArr[1][2] != 2) {
            choice = B6;
        }
        else {
            choice = B8;
        }
    }
    
    return choice;
}

Boolean checkB1() {
    if(GArray.GameArr[0][1] == 2 && GArray.GameArr[0][2] == 2 ||
        GArray.GameArr[1][0] == 2 && GArray.GameArr[2][0] == 2 ||
            GArray.GameArr[1][1] == 2 && GArray.GameArr[1][2] == 2 ) {
        if(B1.isEnabled())
            return true;
    }
    return false;
}

Boolean checkB2() {
    if(GArray.GameArr[0][0] == 2 && GArray.GameArr[0][2] == 2 ||
        GArray.GameArr[1][1] == 2 && GArray.GameArr[2][1] == 2 ) {
        if(B2.isEnabled())
            return true;
    }
    return false;
}

Boolean checkB3() {
    if(GArray.GameArr[0][0] == 2 && GArray.GameArr[0][1] == 2 ||
        GArray.GameArr[2][0] == 2 && GArray.GameArr[1][1] == 2 ||
            GArray.GameArr[1][2] == 2 && GArray.GameArr[2][2] == 2 ) {
        if(B3.isEnabled())
            return true;
    }
    return false;
}

Boolean checkB4() {
    if(GArray.GameArr[0][0] == 2 && GArray.GameArr[2][0] == 2 ||
        GArray.GameArr[1][1] == 2 && GArray.GameArr[1][2] == 2) {
        if(B4.isEnabled())
            return true;
    }
    return false;
}

Boolean checkB5() {
    if(GArray.GameArr[0][0] == 2 && GArray.GameArr[2][2] == 2 ||
        GArray.GameArr[2][0] == 2 && GArray.GameArr[0][2] == 2 ||
            GArray.GameArr[0][1] == 2 && GArray.GameArr[2][1] == 2 ||
            GArray.GameArr[1][0] == 2 && GArray.GameArr[1][2] == 2) {
        if(B5.isEnabled())
            return true;
    }
    return false;
}

Boolean checkB6() {
    if(GArray.GameArr[0][2] == 2 && GArray.GameArr[2][2] == 2 ||
        GArray.GameArr[1][0] == 2 && GArray.GameArr[1][1] == 2) {
        if(B6.isEnabled())
            return true;
    }
    return false;
}

Boolean checkB7() {
    if(GArray.GameArr[0][0] == 2 && GArray.GameArr[1][0] == 2 ||
        GArray.GameArr[1][1] == 2 && GArray.GameArr[0][2] == 2 ||
            GArray.GameArr[2][1] == 2 && GArray.GameArr[2][2] == 2 ) {
        if(B7.isEnabled())
            return true;
    }
    return false;
}

Boolean checkB8() {
    if(GArray.GameArr[0][1] == 2 && GArray.GameArr[1][1] == 2 ||
        GArray.GameArr[2][0] == 2 && GArray.GameArr[2][2] == 2) {
        if(B8.isEnabled())
            return true;
    }
    return false;
}

Boolean checkB9() {
    if(GArray.GameArr[0][0] == 2 && GArray.GameArr[1][1] == 2 ||
        GArray.GameArr[2][0] == 2 && GArray.GameArr[2][1] == 2 ||
            GArray.GameArr[0][2] == 2 && GArray.GameArr[1][2] == 2 ) {
        if(B9.isEnabled())
            return true;
    }
    return false;
}


public void aiTurn() {
    
    JButton Pressed = (JButton) aithinking();
    
    if(GArray.winner) {
        return;
    }
    
    else if (Pressed == B1) {
        GArray.ArrayInitialize(0, 0, PlayerMark);
        SetText(Pressed, Player);     // chaneg button text to "X" or "O" based on player turn
        PlayerMark = SwithTurn(Player); // Swithch Turns
        ButtonDisabler(B1); // Disable pressed Button

    } else if (Pressed == B2) {
        GArray.ArrayInitialize(0, 1, PlayerMark);
        SetText(Pressed, Player);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B2);
    } else if (Pressed == B3) {
        GArray.ArrayInitialize(0, 2, PlayerMark);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B3);

    } else if (Pressed == B4) {
        GArray.ArrayInitialize(1, 0, PlayerMark);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B4);

    } else if (Pressed == B5) {
        GArray.ArrayInitialize(1, 1, PlayerMark);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B5);

    } else if (Pressed == B6) {
        GArray.ArrayInitialize(1, 2, PlayerMark);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B6);
    } else if (Pressed == B7) {
        GArray.ArrayInitialize(2, 0, PlayerMark);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B7);
    } else if (Pressed == B8) {
        GArray.ArrayInitialize(2, 1, PlayerMark);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B8);
    } else if (Pressed == B9) {
        GArray.ArrayInitialize(2, 2, PlayerMark);
        SetText(Pressed, Player);
        PlayerMark = SwithTurn(Player);
        ButtonDisabler(B9);
    }
}


public int SwithTurn(boolean last) {

    // if the past player was false(player 1) then swith to true(player 2)

    if (last == true) {
        Player = false;
        return 1;

    } else if (last == false) {
        Player = true;
        return 2;
    } else {
        return 3;
    }

}

public void ButtonDisabler(JButton Btn) {

    Btn.setEnabled(false); // Disable Button

}

public void DisableAll(boolean Opp) {

    // Disable  All Buttons
    B1.setEnabled(Opp);
    B2.setEnabled(Opp);
    B3.setEnabled(Opp);
    B4.setEnabled(Opp);
    B5.setEnabled(Opp);
    B6.setEnabled(Opp);
    B7.setEnabled(Opp);
    B8.setEnabled(Opp);
    B9.setEnabled(Opp);

    B1.setIcon(null);
    B2.setIcon(null);
    B3.setIcon(null);
    B4.setIcon(null);
    B5.setIcon(null);
    B6.setIcon(null);
    B7.setIcon(null);
    B8.setIcon(null);
    B9.setIcon(null);
}

public void SetText(JButton Btn, boolean Play) {

    if (Play == true) {
//        Btn.setText("O");
        Btn.setIcon(new ImageIcon("hamster/npc/face_enemy.png"));
    } else if (Play == false) {
//        Btn.setText("X");
        Btn.setIcon(new ImageIcon("hamster/npc/face_us.png"));
    }

}

public void DefaultText(){

    B1.setText("");
    B2.setText("");
    B3.setText("");
    B4.setText("");
    B5.setText("");
    B6.setText("");
    B7.setText("");
    B8.setText("");
    B9.setText("");

}


}