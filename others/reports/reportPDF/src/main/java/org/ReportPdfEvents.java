package org.util;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class ReportPdfEvents extends PdfPageEventHelper {
	protected BaseFont baseFont;
	private PdfTemplate totalPages;
	private float footerTextSize = 8f;
	private int pageNumberAlignment = Element.ALIGN_CENTER;

	public ReportPdfEvents() {
		super();
		baseFont = load("fonts", "tahoma.ttf");
	}

	private BaseFont load(String location, String fontname) {
		try {
			return BaseFont.createFont();
		} catch (Exception ex) {
			return null;
		}
	}


	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		
		if(totalPages == null) {
			totalPages = writer.getDirectContent().createTemplate(100, 100);
			totalPages.setBoundingBox(new Rectangle(-20, -20, 100, 100));
		}
		
		PdfContentByte cb = writer.getDirectContent();
		cb.saveState();
		String text = String.format("Página %s de ", writer.getPageNumber());

		float textBase = document.bottom() - 20;
		float textSize = baseFont.getWidthPoint(text, footerTextSize);
		
		cb.beginText();
		cb.setFontAndSize(baseFont, footerTextSize);
		
		if(Element.ALIGN_CENTER == pageNumberAlignment) {
			cb.setTextMatrix((document.right() / 2), textBase);
			cb.showText(text);
			cb.endText();
			cb.addTemplate(totalPages, (document.right() / 2) + textSize, textBase);
		} else if(Element.ALIGN_LEFT == pageNumberAlignment) {
			cb.setTextMatrix(document.left(), textBase);
			cb.showText(text);
			cb.endText();
			cb.addTemplate(totalPages, document.left() + textSize, textBase);
		} else {
			float adjust = baseFont.getWidthPoint("0", footerTextSize);
			cb.setTextMatrix(document.right() - textSize - adjust, textBase);
			cb.showText(text);
			cb.endText();
			cb.addTemplate(totalPages, document.right() - adjust, textBase);
		}
		cb.restoreState();
	}

	@Override
	public void onCloseDocument(PdfWriter writer, Document document) {
		totalPages.beginText();
		totalPages.setFontAndSize(baseFont, footerTextSize);
		totalPages.setTextMatrix(0, 0);
		totalPages.showText(String.valueOf(writer.getPageNumber() - 1));
		totalPages.endText();
	}
	
	public void setPageNumberAlignment(int pageNumberAlignment) {
		this.pageNumberAlignment = pageNumberAlignment;
	}
}