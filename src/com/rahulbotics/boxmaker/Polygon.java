package com.rahulbotics.boxmaker;

import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class Polygon {

	private class Point2D{
		public final float x, y;
		private Point2D next;
		
		public Point2D(float x, float y, Point2D next)
		{
			this.next = next;
			this.x = x;
			this.y = y;
		}

		public boolean isClose(Point2D other)
		{
			float difX = Math.abs(other.x - x);
			float difY = Math.abs(other.y - y);
			if(difX < 10*Math.ulp(x) && difY < 10*Math.ulp(y)) return true;
			
			return false;
		}
		
	}
	
	private Point2D startP;
	private Point2D endP;
	private boolean isClosed = false;
	
	public Polygon(float startX, float startY, float endX, float endY)
	{
		
		endP = new Point2D(endX, endY, null);
		startP = new Point2D(startX, startY, endP);
		
	}
	
	// will invalidate other
	public boolean tryJoin(Polygon other)
	{
		if(this.isClosed || other.isClosed)
			return false;
		
		if(other.startP.isClose(endP))
		{
			endP.next = other.startP.next;
			endP = other.endP;
			other.startP = null;
			other.endP = null;
		}
		else if (other.endP.isClose(startP))
		{
			other.endP.next = startP.next;
			startP = other.startP;
			other.startP = null;
			other.endP = null;
		}
		else if (other.startP.isClose(startP))
		{
			Point2D hlp = other.startP.next;
			other.startP = null;
			other.endP = null;
			while(hlp != null)
			{
				Point2D p = hlp;
				hlp = hlp.next;
				p.next = startP;
				startP = p;
			}
		}
		else if (other.endP.isClose(endP))
		{
			Point2D tmpEnd = endP;
			endP = other.startP;
			other.startP = null;
			other.endP = null;
			
			Point2D tmp = endP.next;
			endP.next = null;
			Point2D tmpStart = endP;
			while(tmp != null)
			{
				Point2D p = tmp;
				tmp = tmp.next;
				p.next = endP;
				tmpStart.next = tmpStart;
			}
			
			tmpEnd.next = tmpStart.next;
		}
		else
		{
			return false;
		}
		
		tryClose();
		
		return true;		
	}

	private void tryClose() {
		
		if(isClosed) return;
		
		if(startP.isClose(endP))
		{
			endP.next = startP.next;
			startP = startP.next;
			isClosed = true;
		}
		
	}
	
	public boolean isClosed()
	{
		return isClosed;
	}

	public void draw(PdfWriter docPdfWriter) {
		PdfContentByte cb = docPdfWriter.getDirectContent();
		cb.setLineWidth(0f);
		cb.moveTo(startP.x, startP.y);
		
		Point2D it = startP;
		while(it != endP)
		{
			it = it.next;
			
			cb.lineTo(it.x, it.y);
		}
		
		if(isClosed)
			cb.closePathStroke();
		else
			cb.stroke();
	}
	
	
}
