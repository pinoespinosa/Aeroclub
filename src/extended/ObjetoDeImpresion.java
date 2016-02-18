package extended;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
public class ObjetoDeImpresion implements Printable {

	private Container p;

	public int print(Graphics g, PageFormat f, int pageIndex) {
		if (pageIndex == 0) {

			Graphics2D g2 = (Graphics2D) g;
			g2.translate(20, 25);
			g2.rotate(Math.toRadians(90));
			g2.scale(0.8, 0.8);
			
			p.printAll(g);


			
			g2.translate(0, -700);
			p.printAll(g);
			
			return PAGE_EXISTS;
		} else {
			return NO_SUCH_PAGE;
		}
	}

	public Container getP() {
		return p;
	}

	public void setP(Container p) {
		this.p = p;
	}
}
