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
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import javafx.scene.text.Font;
import com.itextpdf.text.pdf.PdfPTable;

import models.CarteBancaire;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utils.MyConnection;

/**
 *
 * @author houss
 */
public class PdfOrder {
    
        Connection cnx = MyConnection.getInstance().getCon();
 public java.util.List<CarteBancaire> recuperer() throws SQLException {
        java.util.List<CarteBancaire> list = new ArrayList<>();
        try {
            String req = "Select * from  carte_bancaire "  ;
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                CarteBancaire t = new CarteBancaire();
                t.setId(RS.getInt("id"));
                t.setIdtypecarte_id(RS.getInt(2));
                t.setEmail(RS.getString(3));
                t.setIdentifier(RS.getString(4));
                t.setDescription(RS.getString(5));
                t.setCin_s1(RS.getString(6));
                t.setCin_s2(RS.getString(7));
                t.setStatus(RS.getString(8));

              
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    public void orderPdf(CarteBancaire c)throws SQLException{
        
        java.util.List<CarteBancaire> equipe=recuperer();
        try {

            Document document = new Document();
            // PdfWriter.getInstance(document, new FileOutputStream("C:\\ordrePdfn" + c.getId_cmd() + ".pdf"));
            PdfWriter.getInstance(document, new FileOutputStream("C:\\xampp\\htdocs\\images\\ordrePdfn" + c.getId() + ".pdf"));
            document.open();
            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            com.itextpdf.text.Font fontmini = FontFactory.getFont(FontFactory.COURIER, 11, BaseColor.BLACK);
            com.itextpdf.text.Font fontgras = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
            com.itextpdf.text.Font fontgrastitre = FontFactory.getFont(FontFactory.TIMES_BOLD, 50, BaseColor.BLACK);
            document.add(new Paragraph("\n\n"));
            Chunk chunk = new Chunk("  Liste des ", fontgrastitre);
            document.add(chunk);
            
            document.add(new Paragraph("\n\n"));
            Chunk chunk1 = new Chunk("  Carte Bancaire ", fontgrastitre);
            document.add(chunk1);
            document.add(new Paragraph("\n\n\n\n"));

            Image image = Image.getInstance("http://localhost/images/Mazebank.png");
            image.scaleToFit(100, 100);
            image.setAbsolutePosition(460, 720);
            document.add(image);
            chunk = new Chunk("  Banque: ", fontgras);
            document.add(chunk);
            chunk = new Chunk("      Mazebank ", font);
            document.add(chunk);
            document.add(new Paragraph("\n\n"));

            chunk = new Chunk("  Client: ", fontgras);
            document.add(chunk);
            chunk = new Chunk("      " + c.getEmail() + " Description " + c.getDescription(), font);
            document.add(chunk);
            document.add(new Paragraph("\n"));
            chunk = new Chunk("                " + c.getIdentifier(), fontmini);
            document.add(chunk);
            document.add(new Paragraph("\n\n"));
            chunk = new Chunk("  Status de demande    ", fontgras);
            document.add(chunk);
            document.add(new Paragraph("\n"));
            chunk = new Chunk("  " + c.getStatus() + "               ", fontmini);
            document.add(chunk);
            document.add(new Paragraph("\n\n\n\n"));

            PdfPTable tab = new PdfPTable(6); // Increase column count to 6
        
// Add title
            PdfPCell cell = new PdfPCell(new Paragraph("Liste des demandes des cartes bancaires"));
            cell.setColspan(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setFixedHeight(25);
            tab.addCell(cell);

// Add header
            PdfPCell id = new PdfPCell(new Paragraph("ID"));
            id.setHorizontalAlignment(Element.ALIGN_CENTER);
            id.setFixedHeight(20);
            tab.addCell(id);

            PdfPCell nom = new PdfPCell(new Paragraph("Email"));
            nom.setHorizontalAlignment(Element.ALIGN_CENTER);
            nom.setFixedHeight(20);
            tab.addCell(nom);

            PdfPCell cin = new PdfPCell(new Paragraph("CinSide1")); // New column for image
            cin.setHorizontalAlignment(Element.ALIGN_CENTER);
            cin.setFixedHeight(50);
            tab.addCell(cin);
            PdfPCell cin2 = new PdfPCell(new Paragraph("CinSide2")); // New column for image
            cin2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cin2.setFixedHeight(50);
            tab.addCell(cin2);
            

            PdfPCell voiture = new PdfPCell(new Paragraph("Identifiant"));
            voiture.setHorizontalAlignment(Element.ALIGN_CENTER);
            voiture.setFixedHeight(20);
            tab.addCell(voiture);

            PdfPCell pays = new PdfPCell(new Paragraph("Description"));
            pays.setHorizontalAlignment(Element.ALIGN_CENTER);
            pays.setFixedHeight(20);
            tab.addCell(pays);
            
           

            for (CarteBancaire e : equipe) {
                String idChar = Integer.toString(e.getId());

                PdfPCell idd = new PdfPCell(new Paragraph(idChar));
                idd.setHorizontalAlignment(Element.ALIGN_CENTER);
                idd.setFixedHeight(20);
                tab.addCell(idd);

                PdfPCell nomm = new PdfPCell(new Paragraph(e.getEmail()));
                nomm.setHorizontalAlignment(Element.ALIGN_CENTER);
                nomm.setFixedHeight(20);
                tab.addCell(nomm);

                Image image2 = Image.getInstance("C:\\xampp\\htdocs\\images\\" + c.getCin_s1()); // Load image
                PdfPCell imageCell = new PdfPCell(image2);
                imageCell.setFixedHeight(40);
                imageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tab.addCell(imageCell); // Add image to table

                Image image3 = Image.getInstance("C:\\xampp\\htdocs\\images\\" + c.getCin_s2()); // Load image
                PdfPCell imageCell1 = new PdfPCell(image3);
                imageCell1.setFixedHeight(40);
                imageCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                tab.addCell(imageCell1); // Add image to table
                
                PdfPCell voituree = new PdfPCell(new Paragraph(e.getIdentifier()));
                voituree.setHorizontalAlignment(Element.ALIGN_CENTER);
                voituree.setFixedHeight(20);
                tab.addCell(voituree);

                PdfPCell payss = new PdfPCell(new Paragraph(e.getDescription()));
                payss.setHorizontalAlignment(Element.ALIGN_CENTER);
                payss.setFixedHeight(20);
                tab.addCell(payss);
            }

            document.add(tab);

            

            document.close();
            System.out.println("pdf ajouter !");
        } catch (Exception e) {
            System.out.println("Error PDF " + e.getMessage());

        }

    }
}
