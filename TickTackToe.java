
import java.util.Scanner;

public class TickTackToe
{
  char[] board = new char[9];

  char[] player_idents = {'X', 'O'};

  public TickTackToe()
  {
    clearBoard();
  }
  
  public boolean winCond(char ident) {
    return
      board[0] == ident && board[1] == ident && board[2] == ident ||
      board[3] == ident && board[4] == ident && board[5] == ident ||
      board[6] == ident && board[7] == ident && board[8] == ident ||
      board[0] == ident && board[3] == ident && board[6] == ident ||
      board[1] == ident && board[4] == ident && board[7] == ident ||
      board[2] == ident && board[5] == ident && board[8] == ident ||
      board[0] == ident && board[4] == ident && board[8] == ident ||
      board[2] == ident && board[4] == ident && board[6] == ident;
  }

  public void clearBoard()
  {
    for (int i = 0; i < board.length; i++) 
    {
      board[i] = ' ';
    }
  }

  public <T> int updateBoard(int player, T field)
  {
    int fieldc = -1;
    if (field instanceof String)
    {
      try 
      {
        Integer.parseInt(field.toString());
      }
      catch (NumberFormatException err)
      {
        return -1;
      }
      fieldc = Integer.parseInt(field.toString());
    }
    else if (field instanceof Integer)
    {
      fieldc = (Integer) field;
    }

    --fieldc;
    if (0 <= fieldc && fieldc <= 8)
    {
      if(board[fieldc] == ' ')
      {
        board[fieldc] = player_idents[player];
        return 1;
      }
    }
    return -1;
  }

  public int checkIfWon(int player)
  { 
    if (winCond(player_idents[player]))
    {
      return 1;
    }
    for (int i = 0; i < board.length; i++) 
    {
      if (board[i] == ' ')
      {
        return 2;
      }
    }
    return 0;
  }

  public <T> int playerInput(int player, T field)
  {
    try
    {
      if (updateBoard(player, field) == -1)
      {
        return -1;
      };
    }
    catch (Error err)
    {
      return -1;
    }
    return checkIfWon(player);
  }

  public void printBoard()
  {
    System.out.println(
      "\n# Board #\n" +
      board[0] + " | " + board[1] + " | " + board[2] + "\n" +
      board[3] + " | " + board[4] + " | " + board[5] + "\n" +
      board[6] + " | " + board[7] + " | " + board[8] + "\n"
    );
  }

  private static boolean cmdOutput(Scanner scann, TickTackToe game, int player)
  {
    String nextLine;
    System.out.println(String.format("Spieler %s:", player+1));
    while (true)
    {
      try
      {
        nextLine = scann.nextLine();
      }
      catch (Error err)
      {
        continue;
      }
      if (nextLine.equals("exit") || nextLine.equals("quit"))
      {
        System.out.println("exiting!");
        return false;
      }
      int result = game.playerInput(player, nextLine);
      game.printBoard();
      switch (result)
      {
        case -1:
          System.out.println("\nPlease enter something valid: ");
          continue;
        case 0:
          System.out.println("Board FULL!\n");
          game.clearBoard();
          break;
        case 1:
          System.out.println(String.format("Spieler %s WON! Starting New Game!\n", player+1));
          game.clearBoard();
          break;
        default:
          break;
      }
      return true;
    }
  }
  public static void main(String[] args) 
  {
    Scanner scann;
    try {
      scann = new Scanner(System.in);
      try
      {
        TickTackToe game = new TickTackToe();
        int i = 0;
        boolean running = true;
        while(running)
        {
          running = cmdOutput(scann, game, i%2);
          i++;
        }
        scann.close();
      }
      catch (Error err) {
        System.out.println("Exiting!\nCause:");
        System.out.println(err);
        System.exit(1);
      }
    }
    catch (Error err) 
    {
      System.out.println("Could not open Scanner. Exiting!\nError:");
      System.out.println(err);
      System.exit(1);
    }
  }
}

// gitlab.schule.gymb.de:3000/baechtleC/9c
