package pkgyoke;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
	int boardWidth = 600;
	int boardHeight = 650; // 50pixels to each panel in form for each x/o
	
	JFrame frame = new JFrame("Tic-Tac-Toe Game");
	JLabel label = new JLabel();
	JPanel panel = new JPanel();
	JPanel boardPanel = new JPanel();
	JButton[][] board = new JButton[3][3];
	String playerX = "X";
	String playerO = "O";
	String currentPlayer = playerX; //this means that the first player will be x //
	
	boolean gameOver = false;
	int turns = 0;
	
	TicTacToe(){
		frame.setVisible(true);
		frame.setSize(boardWidth,boardHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		label.setBackground(Color.darkGray);
		label.setForeground(Color.green);
		label.setFont(new Font("Arial" , Font.BOLD , 50));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setText("[ Tic-Tac-Toe ]");
		label.setOpaque(true);
		
		panel.setLayout(new BorderLayout());
		panel.add(label);
		frame.add(panel,BorderLayout.NORTH);
		
		
		boardPanel.setLayout(new GridLayout(3,3));
		boardPanel.setBackground(Color.DARK_GRAY);
		frame.add(boardPanel);
		
		
		for(int r = 0;r < 3;r++) {
			for(int c = 0;c < 3; c++) {
				
				JButton tile = new JButton();
				board[r][c] = tile;
				// then we need to add this buttons to boardPanel //
				boardPanel.add(tile);
				tile.setBackground(Color.DARK_GRAY);
				tile.setForeground(Color.green);
				tile.setFont(new Font("Arial",Font.BOLD,120));
				tile.setFocusable(false);
				//tile.setText(currentPlayer);
				tile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (gameOver) {
							return;
						}
						JButton tile = (JButton) e.getSource();
						if(tile.getText() == "") {
							tile.setText(currentPlayer);
							turns++;
							checkwinner();
							if(!gameOver) {
								currentPlayer = currentPlayer == playerX ? playerO : playerX;
								label.setText(currentPlayer + ": => turn ? ");	
							}
						}
					}
				});
			}
		}
		
		}
void checkwinner() {
	//horizontal check
	for(int r = 0;r < 3;r++) {
		if(board[r][0].getText() == "") continue;
		
		if(board[r][0].getText() == board[r][1].getText() && board[r][1].getText() == board[r][2].getText()) {
			for(int i = 0;i < 3 ; i++) {
				setWinner(board[r][i]);
			}
			gameOver = true;
			return;
		}
	}
	
	// vertical check
	for(int c = 0 ; c < 3;c++) {
		if(board[0][c].getText() == "") continue;
		
		if(board[0][c].getText() == board[1][c].getText() && board[1][c].getText() == board[2][c].getText()) {
			for(int i = 0;i < 3; i++) {
				setWinner(board[i][c]);
			}
			gameOver = true;
			return;
		}
	}
	// diagonally check
	if(board[0][0].getText() == board[1][1].getText()
			&& board[1][1].getText() == board[2][2].getText() 
			&& board[0][0].getText() != "")
	{
		for(int i = 0;i < 3;i++) {
			setWinner(board[i][i]);
		}
		gameOver = true;
		return;
	}
	//anti - diagonal 
	if(board[0][2].getText() == board[1][1].getText() 
			&& board[1][1].getText() == board[2][0].getText()
			&& board[0][2].getText() != "") {
		setWinner(board[0][2]);
		setWinner(board[1][1]);
		setWinner(board[2][0]);
		gameOver = true;
		return;
	}
	if (turns == 9 ) {
		for(int r = 0;r < 3;r++) {
			for(int c = 0; c < 3;c++) {
				sitTie(board[r][c]);
			}
		}
		gameOver = true;
	}
	
}
void setWinner(JButton tile) {
	tile.setForeground(Color.blue);
	tile.setBackground(Color.green);
	label.setText(currentPlayer + " is the Winner! "); 
}
void sitTie(JButton tile) {
	tile.setForeground(Color.orange);
	tile.setBackground(Color.gray);
	label.setText("Tie");
}
}




