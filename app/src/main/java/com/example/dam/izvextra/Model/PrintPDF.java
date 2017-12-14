package com.example.dam.izvextra.Model;


import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;

public class PrintPDF {

    private final String NAME_FOLDER = "Proyecto";
    private final String PDFGENERED = "Mis Archivos";

    public void createPDF() {
    }

    public void createPDFFile(Context context, Excursion exc) {

        Document document = new Document(PageSize.LETTER);
        String NOMBRE_ARCHIVO = "PDF_" + exc.getId() + "_" + exc.getPlace() + ".pdf";
        String SD = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File pdfDir = new File(SD + File.separator + NAME_FOLDER);

        if (!pdfDir.exists()) {
            pdfDir.mkdir();
        }

        File pdfSubDir = new File(pdfDir.getPath() + File.separator + PDFGENERED);

        if (!pdfSubDir.exists()) {
            pdfSubDir.mkdir();
        }

        String name_complete = pdfSubDir.getPath() + File.separator + NOMBRE_ARCHIVO;

        File outputfile = new File(name_complete);
        if (outputfile.exists()){
            outputfile.delete();
        }

        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(name_complete));

            document.open();
            document.addAuthor("Juan Jose Fernandez Reiloba");
            document.addCreator("Developer Juan Jose Fernandez Reiloba");
            document.addSubject("App Proyecto");
            document.addCreationDate();
            document.addTitle("Registro de Excursion");

            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            String htmlToPDF = "<html><head></head><body><pre>Lugar: " + exc.getPlace() + "\nDescripcion: " + exc.getDescription() + "\nGrupos: " + exc.getGroups()
                    + "\nProfesores: " + exc.getTeachers() + "\nFecha: " + exc.getDate() + "\nHora: " + exc.getHour() + "</pre></body></html>";

            worker.parseXHtml(pdfWriter, document, new StringReader(htmlToPDF));
            document.close();


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
