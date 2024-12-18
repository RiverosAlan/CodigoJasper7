package test;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALAN
 */
public class JasperPdf {

    public static void main(String[] args) {
        try {

            // Desactivar las advertencias específicas de JasperReports
            Logger jasperLogger = Logger.getLogger("net.sf.jasperreports.view.SaveContributorUtils");
            jasperLogger.setLevel(Level.OFF); // Desactiva todas las advertencias para esa clase

            // Ruta del archivo .jrxml
            String jrxmlFilePath = "C:\\Users\\ALAN\\JaspersoftWorkspace\\MyReports\\Invoice.jrxml";

            // Compilar el archivo .jrxml a un archivo .jasper
            String jasperFilePath = "C:\\Users\\ALAN\\JaspersoftWorkspace\\MyReports\\Reportes\\MiReporte.jasper";
            JasperCompileManager.compileReportToFile(jrxmlFilePath, jasperFilePath);
            System.out.println("Reporte compilado exitosamente!");

            // Crear parámetros para pasar al reporte
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("titulo", "Reporte de prueba JasperReports");
            parameters.put("autor", "Alan");

            // Crear el reporte usando JREmptyDataSource (sin datos)
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, parameters, new JREmptyDataSource());

            // Exportar el reporte a PDF (opcional, si quieres guardar el archivo)
            String pdfFilePath = "C:\\Users\\ALAN\\JaspersoftWorkspace\\MyReports\\Reportes\\reporte.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFilePath);
            System.out.println("PDF generado exitosamente en: " + pdfFilePath);

            // Visualizar el reporte directamente con JasperViewer
            JasperViewer.viewReport(jasperPrint, false); // El "false" evita cerrar la app al cerrar el visor
            System.out.println("Visualización del reporte con JasperViewer completada.");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

}
