import processing.core.PApplet;

public class Sketch extends PApplet {

	
  // global variables
  int CELL_WIDTH = 20;
  int CELL_HEIGHT = 20;
  int MARGIN = 5;
  int ROW_COUNT = 10;
  int COLUMN_COUNT = 10;

  int SCREEN_WIDTH = (CELL_WIDTH + MARGIN) * COLUMN_COUNT + MARGIN;
  int SCREEN_HEIGHT = (CELL_HEIGHT + MARGIN) * ROW_COUNT + MARGIN;

  int mouseXToGrid;
  int mouseYToGrid;
  boolean gridPressed = false;
  boolean gridPrint = false;
  int selectedCount;
  int rowSelectCount;
  int columnSelectCount;
  int continuousGrid;

  boolean reset;

  // array data
  int[][] intGrid = new int[ROW_COUNT][COLUMN_COUNT];
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    // put your size call here
    size(SCREEN_WIDTH, SCREEN_HEIGHT);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(0);

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

	  drawGrid();
    gridData();
  }

  public void drawGrid(){
    for (int COLUMNS = 0; COLUMNS < COLUMN_COUNT; COLUMNS++){
      for (int ROWS = 0; ROWS < ROW_COUNT; ROWS++){

        if (gridPressed && mouseXToGrid == COLUMNS && mouseYToGrid == ROWS){

          if (intGrid[ROWS][COLUMNS] == 0){

            intGrid[ROWS][COLUMNS] = 1;
            selectedCount ++;
          }

          else if (intGrid[ROWS][COLUMNS] == 1){

            intGrid[ROWS][COLUMNS] = 0;
            selectedCount --;
          }

          if (COLUMNS > 0 && intGrid[ROWS][COLUMNS-1] == 0){

            intGrid[ROWS][COLUMNS-1] = 1;
            selectedCount ++;
          }

          else if (COLUMNS > 0 && intGrid[ROWS][COLUMNS-1] == 1){
          
            intGrid[ROWS][COLUMNS-1] = 0;
            selectedCount --;
          }

          if (COLUMNS < COLUMN_COUNT - 1 && intGrid[ROWS][COLUMNS+1] == 0){

            intGrid[ROWS][COLUMNS+1] = 1;
            selectedCount ++;
          }

          else if (COLUMNS < COLUMN_COUNT - 1 && intGrid[ROWS][COLUMNS+1] == 1){

            intGrid[ROWS][COLUMNS+1] = 0;
            selectedCount --;
          }

          if (ROWS > 0 && intGrid[ROWS-1][COLUMNS] == 0){

            intGrid[ROWS-1][COLUMNS] = 1;
            selectedCount ++;
          }
          
          else if (ROWS > 0 && intGrid[ROWS-1][COLUMNS] == 1){

            intGrid[ROWS-1][COLUMNS] = 0;
            selectedCount --;
          }

          if (ROWS < ROW_COUNT - 1 && intGrid[ROWS+1][COLUMNS] == 0){

            intGrid[ROWS+1][COLUMNS] = 1;
            selectedCount ++;
          }

          else if (ROWS < ROW_COUNT - 1 && intGrid[ROWS+1][COLUMNS] == 1){

            intGrid[ROWS+1][COLUMNS] = 0;
            selectedCount --;
            
          }

          // text outputs
          println( "Total of " + selectedCount + " cells are selected.");
          

          gridPressed = false;
        }

        // colour change
        if (intGrid[ROWS][COLUMNS] == 1){

          fill(0, 255, 0);
          
        }

        else {

          fill(255, 255, 255);
        }

        rect(MARGIN + (COLUMNS * (CELL_WIDTH + MARGIN)), MARGIN + (ROWS * (CELL_HEIGHT + MARGIN)), CELL_WIDTH, CELL_HEIGHT);

        // additional stuff
        if (reset){
          for (int i = 0; i < COLUMN_COUNT; i++){
            for (int j = 0; j < ROW_COUNT; j++){
    
              intGrid[j][i] = 0;
            }
          }

          selectedCount = 0;
          continuousGrid = 0;
          rowSelectCount = 0;
          columnSelectCount = 0;
        }
      }
    }
  }
  
  public void gridData(){

    if (gridPrint){

      for (int i = 0; i < ROW_COUNT; i++){
        for (int j = 0; j < COLUMN_COUNT; j++){
  
          if (intGrid[i][j] == 1){

            rowSelectCount ++;
          }

          if (j < COLUMN_COUNT - 1){

            if (intGrid[i][j] == 1 && intGrid[i][j+1] == 1){

              continuousGrid ++;
            }
          }
          
          if (j > 0 && j < COLUMN_COUNT){

            if (intGrid[i][j-1] == 1 && intGrid[i][j] == 1 && j == COLUMN_COUNT - 1){

              continuousGrid ++;
            } 

            else if (intGrid[i][j-1] == 1 && intGrid[i][j] == 1 && intGrid[i][j+1] == 0 && j < COLUMN_COUNT - 1){

              continuousGrid ++;
            }
          }
        }

        if (rowSelectCount > 2 && continuousGrid > 0){

          println("There are " + continuousGrid + " continuous blocks selected on row " + i + ".");
        }

        println("Row " + i + " has " + rowSelectCount + " cells selected.");

        rowSelectCount = 0;
        continuousGrid = 0;
      }

      for (int i = 0; i < COLUMN_COUNT; i++){
        for (int j = 0; j < ROW_COUNT; j ++){

          if (intGrid[j][i] == 1){

            columnSelectCount ++;
          }
        }

        println("Column " + i + " has " + columnSelectCount + " cells selected.");
        columnSelectCount = 0;
      }

      gridPrint = false;
    }
  }

  public void mousePressed(){
  
    mouseXToGrid = mouseX / (CELL_WIDTH + MARGIN);
    mouseYToGrid = mouseY / (CELL_HEIGHT + MARGIN);
    gridPressed = true;
    gridPrint = true;
  }

  // additional stuff for fun
  public void keyPressed(){
    if (key == 'r'){

      reset = true;
    }
  }

  public void keyReleased(){
    if (key == 'r'){

      reset = false;
    }
  }
}
