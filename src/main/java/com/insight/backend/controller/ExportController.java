package com.insight.backend.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.insight.backend.service.rating.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExportController {
    private final PdfGeneratorService pdfGeneratorService;

    /**
     * Constructs a ExportController with the specified services and mapper.
     * @param pdfGeneratorService the mapper to convert ratings to pdf
     */
    @Autowired
    public ExportController(PdfGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    /**
     * Downloads a file from the export_pdf directory
     *
     * @param auditId the name of the audit to download
     * @return the file as a response
     */

    @GetMapping(path = "/api/v1/audits/{auditId}/export", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> auditReport(@PathVariable("auditId") long auditId) throws IOException {
        try {

            // Generate PDF
            ByteArrayInputStream bis = pdfGeneratorService.createPdf(auditId);

            // Set HTTP headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=Report.pdf");

            // Return the PDF as a response
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));

        } catch (Exception e) {
            // Temporary error handling
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}