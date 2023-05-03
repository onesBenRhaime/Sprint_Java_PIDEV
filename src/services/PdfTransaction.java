/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.scene.text.Font;
import models.Transaction;

/**
 *
 * @author houss
 */
public class PdfTransaction {

    public void orderPdf(Transaction c) {
        try {
            // Créer un nouveau document PDF
            Document document = new Document();
            // Créer un écrivain de document PDF
            Date d = Date.valueOf(LocalDate.now());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(d);
            PdfWriter.getInstance(document, new FileOutputStream("C:\\xampp\\htdocs\\img\\Transaction" + c.getId() + "_" + dateString + ".pdf"));
            // Ouvrir le document PDF pour écrire
            document.open();
            // Ajouter du texte au document PDF
            document.add(new Paragraph("MAZEBANK : Simplifions votre vie financière "));
            /**
             * *Transaction*****
             */

            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            com.itextpdf.text.Font fontmini = FontFactory.getFont(FontFactory.COURIER, 11, BaseColor.BLACK);
            com.itextpdf.text.Font fontgras = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
            com.itextpdf.text.Font fontgrastitre = FontFactory.getFont(FontFactory.TIMES_BOLD, 50, BaseColor.BLACK);
            document.add(new Paragraph("\n\n"));
            Chunk chunk = new Chunk("  Transactions ", fontgrastitre);
            document.add(chunk);
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("\n\n"));
            chunk = new Chunk("  Faite le :  " + c.getDate(), fontgras);
            document.add(chunk);
            Image image = Image.getInstance("C:\\xampp\\htdocs\\img\\logo.png");
            image.scaleToFit(100, 100);
            image.setAbsolutePosition(460, 720);
            document.add(image);
            document.add(new Paragraph("\n\n"));
            chunk = new Chunk(" Montant:  " + c.getMontant() + " DT", fontgras);
            document.add(chunk);
            document.add(new Paragraph("\n"));
            chunk = new Chunk(" Request From: "+c.getRequestFrom(), fontgras);
            document.add(chunk);
                       
            document.add(new Paragraph("\n"));
            chunk = new Chunk(" Request To: "+ c.getRequestTo(), fontgras);
            document.add(chunk);
            document.add(new Paragraph("\n"));
            chunk = new Chunk("  Agnece  :  " + c.getAgenceName(), fontgras);
            document.add(chunk);
            document.add(new Paragraph("\n"));
            chunk = new Chunk(" Type  :  "+c.getTypeTransaction(), fontgras);           
            document.add(new Paragraph("\n"));
            document.add(chunk);
            

            /**
             * *Transaction*****
             */
            // Fermer le document PDF
            document.close();
            System.out.println("Le document PDF a été créé avec succès.");

        } catch (Exception e) {
            System.out.println("Error PDF " + e.getMessage());

        }

    }
}
