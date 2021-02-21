import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Main {
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		Scanner espace = new Scanner(System.in);
		String espace_x_y = espace.nextLine();
		String[] e = espace_x_y.split(" ");
	    int x = Integer.valueOf(e[0]);
	    int y = Integer.valueOf(e[1]);

	    Scanner nbPoint = new Scanner(System.in);
	    int nbp = nbPoint.nextInt();
			ArrayList<Point> points = new ArrayList<Point>();
			Point p1 = new Point(0,0);
			Point p2 = new Point(x,0);
			points.add(p1);


			
			nbp += 2;
	    Plateau plateau = new Plateau(x,y,nbp);


	    for(int parcours =0; parcours < nbp-2;parcours++) {
	    	Scanner point = new Scanner(System.in);
		    String coord_point = point.nextLine();
		    String[] c = coord_point.split(" ");
		    Point p = new Point(Integer.valueOf(c[0]),Integer.valueOf(c[1]));
		    points.add(p);
	    }
	    points.add(p2);
			 //les 2 points ajouté (0,0) et (l,0)
	    plateau.setPoints(points);
	    System.out.println("algo premiere approche :" + plateau.surface(points));
	    System.out.println("algo divisé pour mieux regner :"+plateau.div_pour());

	}
}
