package com.deni.gunawan.Sisteminformasiperpustakaan.controller;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.Buku;
import com.deni.gunawan.Sisteminformasiperpustakaan.service.BukuService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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
    @GetMapping(path = "buku/report/excel/LaporanBuku")
    @ResponseBody
    private void getDownloadReportXlsx(HttpServletRequest request, HttpServletResponse response) {
        try {
            //uncomment this codes if u are want to use servlet output stream
            ServletOutputStream servletOutputStream = response.getOutputStream();

            Map<String, Object> params = new HashMap<>();

            List<Buku> bukus = (List<Buku>) bukuService.getBukuList();

            //Data source Set
            JRDataSource dataSource = new JRBeanCollectionDataSource(bukus);
            params.put("datasource", dataSource);

            //get real path for report
            InputStream jasperStream = this.getClass().getResourceAsStream("/reports/buku.jrxml");
            JasperDesign design = JRXmlLoader.load(jasperStream);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);

            JRXlsxExporter xlsxExporter = new JRXlsxExporter();
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            xlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            xlsxExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "buku.xls");

            //uncomment this codes if u are want to use servlet output stream
//        xlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);

            xlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
//        xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//        xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput("car_list.xlsx"));
//        xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            xlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
            xlsxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            xlsxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            xlsxExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
//        xlsxExporter.exportReport();


            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=buku.xls");

            //uncomment this codes if u are want to use servlet output stream
//        servletOutputStream.write(os.toByteArray());

            response.getOutputStream().write(os.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            response.flushBuffer();
        } catch (JRException ex) {
            System.out.println("Error : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException " + ex.getMessage());
        }
    }





}
