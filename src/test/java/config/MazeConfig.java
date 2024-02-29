package config;

import geometry.IntCoordinates;

import model.Critter;
import model.ghost.Ghost.*;
import model.PacMan;
import model.Fruit;

import static config.Cell.Content.DOT; //en faisant ca on peut simplement utiliser DOT sans prefixe Content
import static config.Cell.*;
import static config.Cell.Content.NOTHING;
import static config.Cell.Content.ENERGIZER;
import static config.Cell.Content.FRUIT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.LinkedList;

/**
 * Represents the configuration of the maze in the Pacman game.
 * The maze configuration includes the grid of cells, the positions of various game elements such as Pacman, ghosts, and fruits,
 * as well as methods to access and manipulate the maze configuration.
 */
public class MazeConfig {
    private final Cell[][] grid;

    private final IntCoordinates pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos, fruitPos;

        
        /**
         * Constructs a MazeConfig object with the specified parameters.
         * 
         * @param grid The grid representing the maze.
         * @param pacManPos The position of Pacman in the maze.
         * @param blinkyPos The position of Blinky in the maze.
         * @param pinkyPos The position of Pinky in the maze.
         * @param inkyPos The position of Inky in the maze.
         * @param clydePos The position of Clyde in the maze.
         * @param fruitPos The position of the fruit in the maze.
         */
        public MazeConfig(Cell[][] grid, IntCoordinates pacManPos, IntCoordinates blinkyPos, IntCoordinates pinkyPos,
                IntCoordinates inkyPos, IntCoordinates clydePos, IntCoordinates fruitPos) {
            this.grid = new Cell[grid.length][];
            for (int i = 0; i < grid.length; i++) {
                this.grid[i] = new Cell[grid[i].length];
                if (grid[i].length >= 0) {
                    System.arraycopy(grid[i], 0, this.grid[i], 0, grid[i].length);
                }
            }
            this.pacManPos = pacManPos;
            this.blinkyPos = blinkyPos;
            this.inkyPos = inkyPos;
            this.pinkyPos = pinkyPos;
            this.clydePos = clydePos;
            this.fruitPos = fruitPos;
        }
    

    public IntCoordinates getPacManPos() {
        return pacManPos;
    }

    public IntCoordinates getBlinkyPos() {
        return blinkyPos;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public IntCoordinates getPinkyPos() {
        return pinkyPos;
    }

    public IntCoordinates getInkyPos() {
        return inkyPos;
    }

    public IntCoordinates getClydePos() {
        return clydePos;
    }

    public IntCoordinates getFruitPos() {
        return fruitPos;
    }

    public int getWidth() {
        return grid[0].length;
    }

    public int getHeight() {
        return grid.length;
    }

    public Cell getCell(IntCoordinates pos) {
        return grid[Math.floorMod(pos.y(), getHeight())][Math.floorMod(pos.x(), getWidth())];
    }
    /**
     * This method is used to read the maze from a text file.
     * @param chemin : the path of the file
     * 
     * The maps work with the following characters:
     * | : a wall on the left or right side of the cell
     * - : a wall on the top or bottom side of the cell
     * * : a wall on the top or bottom side of the cell for ghosts
     * . : a dot
     * E : an energizer
     * P : Pacman
     * b : Blinky
     * p : Pinky
     * i : Inky
     * c : Clyde
     * f : fruit
     * 
     * In the text files, the cells are represented as follows:
     * +---+
     * | C |
     * +---+
     * 
     * C represents the content of the cell described above.
     * Except for the edges of the maze, each wall is common to two connecting cells.
     *
     */
    public static MazeConfig lectureDuFichier(String chemin){
      BufferedReader read=null;
      LinkedList<String> ligne=new LinkedList <String>(); // chaque cellule  de cette liste  representera une ligne du fichier texte 
      //on crée un objet FileReader qui permet de lire le contenu du fichier 
      //puis on crée un autre objet de type BufferReader afin de faciliter la lecture du fichier:Il lit en bloc au lieu de lire en caractères
      //read: c'est l'objet créer 
      try{
       read=new BufferedReader(new FileReader(chemin));
      String  row; 
      while((row=read.readLine())!=null){ //on met la premiere ligne du fichier dans row  sous forme d'une chaine de caractere 
        ligne.add(row); //on rajoute cette chaine de caractère contenu dans row  à cette liste "ligne" 
      }
    }catch(IOException e){
         e.printStackTrace(); //afficher les erreurs afin de les detecter facilement dans certains cas 
      }finally{
        try{
            if (read != null) {
                read.close();
        }
      }catch(IOException e){
         e.printStackTrace();
      }
      }
     
      IntCoordinates pacManPos=null; 
      IntCoordinates blinkyPos=null;
      IntCoordinates pinkyPos=null;
      IntCoordinates inkyPos=null;
      IntCoordinates clydePos=null;
      IntCoordinates fruitPos=null;

        boolean n=true;//north
        boolean w=true;//south
        boolean e=true;//est
        boolean s=true;//west
        boolean gwn=true;
        boolean gws=true;
      int nbLigne=ligne.size()/2; //le nombre de ligne c'est le nombre de cellule\2 car le contenu de la cellule se trouve dans une ligne sur deux 
      String ch=ligne.get(0);//on recupere la premiere ligne
      int nbColonne=ch.length()/4;//on recupere son nombre de colonne (qui correspond a sa longueur)\4 car le contenu de la cellule se trouve dans une colonne sur quatre 
      Cell[][] grille=new Cell[nbLigne][nbColonne]; //on crée notre grille de cellule qui va representer la grille principal du jeu 
      //On parcourt pour remplir la grille 
      for(int i=0;i<nbLigne;i++){//là c'est pour parcourir la liste (les lignes de la grilles)
        String l=ligne.get(2*i+1); //on fesant ça on recupère que les lignes dont lequels se trouve le contenu de la cellule 
        for(int j=0;j<nbColonne;j++){ // la c'est pour les colonnes : chaque caractere est contenu dans une colonne 
           
        //on regarde ce qu'il y a autour de la cellule 
            if(l.charAt(j*4)=='|'){
                w=true;
            }else{
                w=false;
            }
            if(l.charAt(j*4+4)=='|'){
            e=true;
            }else{
                e=false;
            }
            if(ligne.get(i*2).charAt(j*4+2)=='-'){
                n=true;
            }else{
                n=false;
            }
            if(ligne.get(i*2+2).charAt(j*4+2)=='-'){
                s=true;
            }else{
                s=false;
            }
            if(ligne.get(i*2).charAt(j*4+2)=='*'){
                gwn=true;
            }else{
                gwn=false;
            }
            if(ligne.get(i*2+2).charAt(j*4+2)=='*'){
                gws=true;
            }else{
                gws=false;
            }
            //les "|" et les "-" representent des murs fermés
            
            if(l.charAt(j*4+2) == '.'){
                grille[i][j]=new Cell(n,e,s,w,gwn,gws,DOT);
            } //un point à gagner 
            else if(l.charAt(j*4+2) == 'E'){
                grille[i][j]=new Cell(n,e,s,w,gwn,gws,ENERGIZER);
            }//un Energizer à gagner 
            else if(l.charAt(j*4+2) == ' '){
                grille[i][j]=new Cell(n,e,s,w,gwn,gws,NOTHING);
            }//VIDE
            else if(l.charAt(j*4+2) == 'P'){
                pacManPos=new IntCoordinates(j,i);
                grille[i][j]=new Cell(n,e,s,w,gwn,gws,NOTHING);
            }//pour la position de Pacman
            else if(l.charAt(j*4+2) == 'p'){
               
                pinkyPos=new IntCoordinates(j,i);
                grille[i][j]=new Cell(n,e,s,w,gwn,gws,NOTHING);
            }//pour la position de pinky
            else if(l.charAt(j*4+2) == 'i'){
                inkyPos=new IntCoordinates(j,i);
                grille[i][j]=new Cell(n,e,s,w,gwn,gws,NOTHING);
            }//pour la position de inky
            else if(l.charAt(j*4+2) == 'b'){
                blinkyPos=new IntCoordinates(j,i);
                grille[i][j]=new Cell(n,e,s,w,gwn,gws,NOTHING);
            }//pour la position de blinky
            else if(l.charAt(j*4+2) == 'c'){
                clydePos=new IntCoordinates(j,i);
                grille[i][j]=new Cell(n,e,s,w,gwn,gws,NOTHING);
            }//pour la position de clyde
            else if(l.charAt(j*4+2) == 'f'){
                fruitPos=new IntCoordinates(j,i);
                grille[i][j]=new Cell(n,e,s,w,gwn,gws,FRUIT);
            }//pour la position des fruits
            else {
                grille[i][j]=new Cell(n,e,s,w,gwn,gws,NOTHING);
            }//VIDE
            }
        }
    
        
      return new MazeConfig(grille, pacManPos,blinkyPos, pinkyPos, inkyPos, clydePos, fruitPos); //retourner notre grille qui represente la map
    }

    /**
     * This method is used to create the maze chosen by the player. 
     * The default maze is the maze 1.
     * @return the maze
     */
    // public static MazeConfig makeExample1() {
    //     return lectureDuFichier("src/main/resources/maps/map" + MapSetter.chosenLabyrinth + ".txt");
    // }

    public static MazeConfig makeExample1() {
        return lectureDuFichier("src/main/resources/maps/map1test.txt");
    }

    /**
     * This method is used to check if the position is in the maze.
     * @param pos : the position to check
     * 
     * @return true if the position is in the maze, false otherwise
     */
    public boolean isInBounds(IntCoordinates pos) {
        return pos.x() >= 0 && pos.x() < getWidth() && pos.y() >= 0 && pos.y() < getHeight();
    }

    /**
     * This method is used to make a maze from a given level.
     * @param level : the level of the maze
     * 
     * @throws IOException
     * This exception is thrown when an IO operation has failed or been interrupted.
     * Here, it is thrown when the file is not found
     * 
     * @return the maze
     */
    public static MazeConfig makeMaze(int level) throws IOException {
        return lectureDuFichier("src/main/resources/maps/map" + level + ".txt");
    }
}
