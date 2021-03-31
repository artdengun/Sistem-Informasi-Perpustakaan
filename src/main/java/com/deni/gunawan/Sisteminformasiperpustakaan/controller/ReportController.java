package com.deni.gunawan.Sisteminformasiperpustakaan.controller;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.Anggota;
import com.deni.gunawan.Sisteminformasiperpustakaan.repository.AnggotaRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired ApplicationContext context;
    @Autowired AnggotaRepository anggotaRepository;

    @GetMapping("/anggota/pdf")
    @ResponseBody
    public void getPdf(HttpServletResponse response) throws Exception{
        // kita definisikan lokasi reportnya
        Resource resource = context.getResource("classpath:reports/buku.jrxml");
        // kita compile dengan jasperReports
        InputStream inputStream = resource.getInputStream();
        JasperReport report = JasperCompileManager.compileReport(inputStream);

        // parameternya kita set
        Map<String, Object> param = new HashMap<>();

        List<Anggota>  anggota = (List<Anggota>) anggotaRepository.findAll();

        // datasource kita set
        JRDataSource dataSource = new JRBeanCollectionDataSource(anggota);
        param.put("datasource", dataSource);

        // kita buat dia ngeprint
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, param, dataSource);
        // type printan kita
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        // export jadi pdf
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
}
