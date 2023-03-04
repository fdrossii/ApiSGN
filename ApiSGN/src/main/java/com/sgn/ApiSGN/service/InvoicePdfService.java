package com.sgn.ApiSGN.service;

import com.sgn.ApiSGN.model.Invoice;
import com.sgn.ApiSGN.model.SaleItem;
import com.sgn.ApiSGN.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;



@Service
public class InvoicePdfService {

    private static final String PDF_RESOURCES = "/pdf-resources/";

    private static SpringTemplateEngine springTemplateEngine;

    @Autowired
    public InvoicePdfService(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    public static File generateInvoicePdf(Invoice invoice) throws Exception, ErrorResponse {

        Context context = getInvoicePdf(invoice);
        String html = loadAndFillTemplate(context);
        String xhtml = convertToXhtml(html);
        return renderInvoicePdf(xhtml);
    }

    private static String convertToXhtml(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setXHTML(true);
        tidy.setIndentContent(true);
        tidy.setPrintBodyOnly(true);
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setSmartIndent(true);
        tidy.setShowWarnings(false);
        tidy.setQuiet(true);
        tidy.setTidyMark(false);

        Document htmlDOM = tidy.parseDOM(new ByteArrayInputStream(html.getBytes()), null);

        OutputStream out = new ByteArrayOutputStream();
        tidy.pprint(htmlDOM, out);
        return out.toString();
    }

    private static File renderInvoicePdf(String html) throws Exception {
        File file = File.createTempFile("invoice", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    private static Context getInvoicePdf(Invoice invoice) throws ErrorResponse {
        SaleItem[] saleItemList = invoice.getSaleItem();
        Double total = invoice.getTotal();
        Context context = new Context();
        context.setVariable("saleItemList", saleItemList);
        context.setVariable("total", total);
        return context;
    }

    private static String loadAndFillTemplate(Context context) {
        return springTemplateEngine.process("InvoicePDF", context);
    }
}
