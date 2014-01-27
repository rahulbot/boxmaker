package com.rahulbotics.boxmaker;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.lowagie.text.pdf.PdfWriter;

public class PolygonDrawer {

	List<Polygon> polygonList = new LinkedList<Polygon>();
	
	public void addLine(float p0x, float p0y, float p1x, float p1y) {

		if (p0x == p1x && p0y == p1y)
			return;

		Polygon line = new Polygon(p0x, p0y, p1x, p1y);
		
		add(line);
		
		
	}
	
	private void add(Polygon input)
	{
		
		Iterator<Polygon> it = polygonList.iterator();
		while(it.hasNext())
		{
			Polygon poly = it.next();
			if(poly.isClosed()) continue;
			if(poly.tryJoin(input))
			{
				it.remove();
				add(poly);
				return;
			}
		}
		
		polygonList.add(input);
		
	}

	public void drawToPDF(PdfWriter docPdfWriter) {
		
		
		Iterator<Polygon> polygonsIt = polygonList.iterator();
		while(polygonsIt.hasNext())
		{
			Polygon polygon = polygonsIt.next();
			
			polygon.draw(docPdfWriter);
			
		}
		
	}


}
