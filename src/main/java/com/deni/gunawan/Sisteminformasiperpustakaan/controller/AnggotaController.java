package com.deni.gunawan.Sisteminformasiperpustakaan.controller;

import com.deni.gunawan.Sisteminformasiperpustakaan.configuration.AnggotaExcelExporter;
import com.deni.gunawan.Sisteminformasiperpustakaan.model.Anggota;
import com.deni.gunawan.Sisteminformasiperpustakaan.service.AnggotaService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class AnggotaController {

    @Autowired
    ApplicationContext context;


    @Autowired  private AnggotaService anggotaService;

    @GetMapping("anggota")
    public String getAnggota(Model model){
        List<Anggota> anggotaList = anggotaService.getAnggotaList();
        model.addAttribute("anggotas", anggotaList);

        return "Anggota";
    }
    @PostMapping("anggota/tambahData")
    public String tambahData(Anggota anggota){
        anggotaService.save(anggota);
        return "redirect:/anggota";
    }

    @RequestMapping("anggota/findById")
    @ResponseBody
    public Optional<Anggota> findById(String id){
        return anggotaService.findById(id);
    }

    @RequestMapping(value = "anggota/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Anggota anggota){
        anggotaService.save(anggota);
        return "redirect:/anggota";
    }

    @RequestMapping(value = "anggota/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(String id){
        anggotaService.delete(id);
        return "redirect:/anggota";
    }

    @GetMapping(path = "anggota/report/pdf/LaporanAnggota")
    @ResponseBody
    public void getPdf(HttpServletResponse response) throws Exception {
        Resource resource = context.getResource("classpath:reports/anggota.jrxml");

        InputStream inputStream = resource.getInputStream();
        JasperReport report = JasperCompileManager.compileReport(inputStream);
        //Parameters Set
        Map<String, Object> params = new HashMap<>();

        List<Anggota> anggotas = (List<Anggota>) anggotaService.getAnggotaList();

        //Data source Set
        JRDataSource dataSource = new JRBeanCollectionDataSource(anggotas);
        params.put("datasource", dataSource);

        //Make jasperPrint
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        //Media Type
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        //Export PDF Stream
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }


    @GetMapping("anggota/report/excel/Laporananggota")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=laporananggota" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Anggota> listanggota = anggotaService.getAnggotaList();

        AnggotaExcelExporter anggotaExcelExporter = new AnggotaExcelExporter(listanggota);

        anggotaExcelExporter.export(response);

        }
    }
