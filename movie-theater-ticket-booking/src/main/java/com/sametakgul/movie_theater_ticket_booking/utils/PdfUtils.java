package com.sametakgul.movie_theater_ticket_booking.utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.sametakgul.movie_theater_ticket_booking.entity.response.TicketResponse;

import java.io.ByteArrayOutputStream;

public class PdfUtils {

    public static byte[] createTicketPdf(TicketResponse ticketResponse) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Movie Ticket"));
            document.add(new Paragraph("Movie Name: " + ticketResponse.getMovieName()));
            document.add(new Paragraph("Theater Name: " + ticketResponse.getTheaterName()));
            document.add(new Paragraph("Address: " + ticketResponse.getAddress()));
            document.add(new Paragraph("Date: " + ticketResponse.getDate().toString()));
            document.add(new Paragraph("Time: " + ticketResponse.getTime().toString()));
            document.add(new Paragraph("Booked Seats: " + ticketResponse.getBookedSeats()));
            document.add(new Paragraph("Total Price: $" + ticketResponse.getTotalPrice()));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }
}
