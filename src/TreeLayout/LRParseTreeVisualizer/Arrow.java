/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeLayout.LRParseTreeVisualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JComponent;

/**
 *
 * @author HP
 */
public class Arrow extends JComponent
{

/** x1,y1 is first endpoint of this arrow.
 *  x2,y2 is second endpoint of this arrow.
 *  color is the color of this arrow, default is black.
 */
public double x1,y1,x2,y2;		// endpoints of this Arrow
Color color;				// color of this Arrow

/** arrowhead's angle with respect to the shaft
 */
public static final double angle = Math.PI/10;	// arrowhead angle to shaft
/** length of arrowhead
 */
public static final double len = 10;		// arrowhead length

/** Create an arrow with given endpoints
 *  @param xOne x coordinate of first endpoint
 *  @param yOne y coordinate of first endpoint
 *  @param xTwo x coordinate of second endpoint
 *  @param yTwo y coordinate of second endpoint
 */
public Arrow (double xOne, double yOne, double xTwo, double yTwo)
{  x1 = xOne;
   x2 = xTwo;
   y1 = yOne;
   y2 = yTwo;
}

/** Create an arrow with given endpoints and given color
 *  @param xOne x coordinate of first endpoint
 *  @param yOne y coordinate of first endpoint
 *  @param xTwo x coordinate of second endpoint
 *  @param yTwo y coordinate of second endpoint
 *  @param c color of this arrow
 */
public Arrow (double xOne, double yOne, double xTwo, double yTwo, Color c)
{  x1 = xOne;
   x2 = xTwo;
   y1 = yOne;
   y2 = yTwo;
   color = c;
}

/** Private fields needed for arrowhead endpoints   
 *
 */
double ax1,ay1, ax2, ay2;		// coordinates of arrowhead endpoints

/** Display this arrow on the given Graphics object g.
 *
 */
public void paint (Graphics g)
{ super.paint (g);
  Graphics2D graphics2D = (Graphics2D) g;
  Color oldColor;
  oldColor = graphics2D.getColor();
  graphics2D.setColor (color);

  // paint the shaft
  graphics2D.draw (new Line2D.Double (x1,y1,x2,y2));

  // paint arrowhead
      	arrHead (x1,y1,x2,y2);
     	graphics2D.draw (new Line2D.Double (x2,y2,ax1,ay1));
     	graphics2D.draw (new Line2D.Double (x2,y2,ax2,ay2));
  graphics2D.setColor (oldColor);
}

/**  Compute the arrowhead endpoints
 *  arrow is pointing from x1,y1 to x2,y2
 *  angle to shaft is angle.
 *  length of arrowhead is len.
 *  return endpoints in ax1, ay1, ax2, ay2.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
 */
public void arrHead (double x1, double y1, double x2, double y2)
{  double c,a,beta,theta,phi;
   c = Math.sqrt ((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
   if (Math.abs(x2-x1) < 1e-6)
	if (y2<y1) theta = Math.PI/2;
	else theta = - Math.PI/2; 
   else
     { if (x2>x1)
   	   theta = Math.atan ((y1-y2)/(x2-x1)) ;
   	else
	   theta = Math.atan ((y1-y2)/(x1-x2));
     }
   a = Math.sqrt (len*len  + c*c - 2*len*c*Math.cos(angle));
   beta = Math.asin (len*Math.sin(angle)/a);
   phi = theta - beta;
   ay1 = y1 - a * Math.sin(phi);		// coordinates of arrowhead endpoint
   if (x2>x1) 
	ax1 = x1 + a * Math.cos(phi);
   else
	ax1 = x1 - a * Math.cos(phi);
   phi = theta + beta;				// second arrowhead endpoint
   ay2 = y1 - a * Math.sin(phi);
   if (x2>x1)
	ax2 = x1 + a * Math.cos(phi);
   else 
	ax2 = x1 - a * Math.cos(phi);
}

}
