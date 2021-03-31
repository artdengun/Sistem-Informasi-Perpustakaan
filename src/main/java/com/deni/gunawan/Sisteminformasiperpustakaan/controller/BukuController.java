package com.deni.gunawan.Sisteminformasiperpustakaan.controller;

import com.deni.gunawan.Sisteminformasiperpustakaan.configuration.BukuExcelExporter;
import com.deni.gunawan.Sisteminformasiperpustakaan.model.Buku;
import com.deni.gunawan.Sisteminformasiperpustakaan.service.BukuService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class BukuController {

    @Autowired
    ApplicationContext context;


    @Autowired  private BukuService bukuService;

    @GetMapping("buku")
    public String getBuku(Model model){
        List<Buku> bukuList = bukuService.getBukuList();
        model.addAttribute("bukus", bukuList);

        return "Buku";
    }

    @PostMapping("buku/tambahData")
    public String tambahData(Buku Buku){
        bukuService.save(Buku);
        return "redirect:/buku";
    }

    @RequestMapping("buku/findById")
    @ResponseBody
    public Optional<Buku> findById(String id){
        return bukuService.findById(id);
    }

    @RequestMapping(value = "buku/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Buku Buku){
        bukuService.save(Buku);
        return "redirect:/buku";
    }

    @RequestMapping(value = "buku/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(String id){
        bukuService.delete(id);
        return "redirect:/buku";
    }

    @GetMapping(path = "buku/report/pdf/LaporanBuku")
    @ResponseBody
    public void getPdf(HttpServletResponse response) throws Exception {
        Resource resource = context.getResource("classpath:reports/buku.jrxml");

        InputStream inputStream = resource.getInputStream();
        JasperReport report = JasperCompileManager.compileReport(inputStream);
        //Parameters Set
        Map<String, Object> params = new HashMap<>();

        List<Buku> bukus = (List<Buku>) bukuService.getBukuList();

        //Data source Set
        JRDataSource dataSource = new JRBeanCollectionDataSource(bukus);
        params.put("datasource", dataSource);

        //Make jasperPrint
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        //Media Type
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        //Export PDF Stream
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }


    @GetMapping("buku/report/excel/LaporanBuku")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=laporanBuku" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Buku> listBuku = bukuService.getBukuList();

        BukuExcelExporter bukuExcelExporter = new BukuExcelExporter(listBuku);

        bukuExcelExporter.export(response);

        }
    }
