package org.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Value;

/** 
 * ZipUtil.java
 * @author WODZAROD
 */
public class ZipUtil {
	public static final int ZIP_BUFFER_SIZE = 50;

	public static byte[] zipByteArray(byte[] file) throws IOException {
		byte[] byReturn = null;
		Deflater oDeflate = new Deflater(Deflater.DEFLATED, false);
		oDeflate.setInput(file);
		oDeflate.finish();
		ByteArrayOutputStream oZipStream = new ByteArrayOutputStream();
		try {
			while (!oDeflate.finished()) {
				byte[] byRead = new byte[ZIP_BUFFER_SIZE];
				int iBytesRead = oDeflate.deflate(byRead);
				if (iBytesRead == byRead.length) {
					oZipStream.write(byRead);
				} else {
					oZipStream.write(byRead, 0, iBytesRead);
				}
			}
			oDeflate.end();
			byReturn = oZipStream.toByteArray();
		} finally {
			oZipStream.close();
		}
		return byReturn;
	}

	public static byte[] unzipByteArray(byte[] file) throws DataFormatException, IOException {
		byte[] byReturn = null;

		Inflater oInflate = new Inflater(false);
		oInflate.setInput(file);

		ByteArrayOutputStream oZipStream = new ByteArrayOutputStream();
		try {
			while (!oInflate.finished()) {
				byte[] byRead = new byte[ZIP_BUFFER_SIZE];
				int iBytesRead = oInflate.inflate(byRead);
				if (iBytesRead == byRead.length) {
					oZipStream.write(byRead);
				} else {
					oZipStream.write(byRead, 0, iBytesRead);
				}
			}
			byReturn = oZipStream.toByteArray();
		} catch (DataFormatException ex) {
			throw new DataFormatException("Attempting to unzip file that is not zipped.");
		} finally {
			oZipStream.close();
		}
		return byReturn;
	}

	/*
	public static Map<String, OperacionNetCash> zipFilesToFileMap(Map<String, List<OperacionNetCash>> mapArchivos)
			throws IOException {
		Map<String, OperacionNetCash> salida = new HashMap<String, OperacionNetCash>();
		OperacionNetCash operacionSalida;

		ByteArrayOutputStream out1;
		ByteArrayInputStream in;
		ZipOutputStream out;
		List<String> nombresArhivos;

		if (!mapArchivos.isEmpty()) {
			for (Entry<String, List<OperacionNetCash>> entry : mapArchivos.entrySet()) {
				if (!entry.getValue().isEmpty()) {
					out1 = new ByteArrayOutputStream();
					out = new ZipOutputStream(out1);
					nombresArhivos = new ArrayList<String>();
					for (OperacionNetCash operacion : entry.getValue()) {

						byte[] buf = new byte[1024];
						try {
							in = new ByteArrayInputStream(ZipUtil.unzipByteArray(operacion.getArchivo()));
						} catch (Exception e) {
							// En caso el archivo no este zipeado lo tomamo tal cual
							in = new ByteArrayInputStream(operacion.getArchivo());

						}
						// Validamos duplicados
						Integer contadorDuplicados = 0;
						String nombreArchivo = operacion.getNombreArchivo() + "." + operacion.getExtensionArchivo();
						while (nombresArhivos.contains(nombreArchivo)) {
							contadorDuplicados++;
							nombreArchivo = operacion.getNombreArchivo() + "(" + (contadorDuplicados + 1) + ")."
									+ operacion.getExtensionArchivo();
						}
						nombresArhivos.add(nombreArchivo);
						// agregar el ZIP al output stream
						out.putNextEntry(new ZipEntry(nombreArchivo));
						// transferir los bytes del archivo al archivo zipeado
						int len;
						while ((len = in.read(buf)) > 0) {
							out.write(buf, 0, len);
						}
						// completar el arhivo
						out.closeEntry();
						in.close();
					}
					// completar el archivo ZIP
					out.close();

					operacionSalida = new OperacionNetCash();
					operacionSalida.setArchivo(out1.toByteArray());
					operacionSalida.setNombreArchivo(entry.getKey());
					operacionSalida.setExtensionArchivo("zip");
					operacionSalida.setCodTipoAdjunto(entry.getKey());

					salida.put(entry.getKey(), operacionSalida);
					out1.close();
				}
			}
		}
		return salida;
	}
	*/
}
