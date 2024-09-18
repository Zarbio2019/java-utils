package org.util;

import java.awt.Color;
import java.util.Map;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.web.servlet.view.document.AbstractPdfView;

public class ReportPdfView extends AbstractPdfView {

	private String nombre;
	
	public ReportePdfView(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	protected Document newDocument() {
		return new Document(PageSize.A4.rotate());
		
	}
	
	@Override
	@SuppressWarnings({ "unchecked"})
	//protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
	protected void buildPdfDocument(Document document, PdfWriter writer) throws Exception {
		
		ReportPdfEvents objEventos = new ReportPdfEvents();
		writer.setPageEvent(objEventos);
		
		// cambiar nombre de archivo
		int cantidadColumnas = 2 + 1;
		
		Table table = new Table(cantidadColumnas);
		table.setPadding(5);
		table.setWidth(100);
		
		Cell cell = new Cell(nombre);
		cell.setHeader(true);
		cell.setColspan(cantidadColumnas);
		cell.setBorderWidthTop(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthRight(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new Color(48, 180, 249));
		table.addCell(cell);
		table.endHeaders();
		
		Cell desc = new Cell("DESCRIPCIÓN");
		desc.setBackgroundColor(new Color(66, 139, 202));
		desc.setWidth(30);
		
        table.addCell(desc);
        
        //Generamos la Cabecera
        {
			Cell cabecera = new Cell("HEADER");
			cabecera.setBackgroundColor(new Color(66, 139, 202));
			table.addCell(cabecera);
		}
        
        //Generamos el Detalle
        {
			Cell FIL = new Cell("Fila 1");
			FIL.setBackgroundColor(new Color(238, 238, 238));
			FIL.setWidth(30);
			FIL.setGrayFill(0.9f);
			table.addCell(FIL);

			{			
				Cell valores = new Cell((valor==null?"":""+"valor"));
				valores.setHorizontalAlignment(Element.ALIGN_CENTER);
				valores.setVerticalAlignment(Element.ALIGN_CENTER);

				table.addCell(valores);
			}
		}
        document.add(table);
	}
}
