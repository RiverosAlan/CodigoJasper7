package test;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author ALAN
 */
public class Main {

    public static void main(String[] args) throws IOException {
        try {
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

            // Exportar el reporte a PDF
            String pdfFilePath = "C:\\Users\\ALAN\\JaspersoftWorkspace\\MyReports\\Reportes\\reporte.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFilePath);
            System.out.println("PDF generado exitosamente en: " + pdfFilePath);

            // Abrir el PDF automáticamente
            File pdfFile = new File(pdfFilePath);
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                    System.out.println("PDF abierto exitosamente.");
                } else {
                    System.out.println("Desktop no es compatible en este sistema.");
                }
            } else {
                System.out.println("El archivo PDF no existe.");
            }
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al intentar abrir el PDF.");
            e.printStackTrace();
        }
    }

}
