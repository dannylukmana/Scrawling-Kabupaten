/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.daftarkabupaten;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Danny
 */
public class Daftarkabupaten {

    public static void main(String[] args) {
        //System.out.println("Hello World!");
        final String url = "https://id.wikipedia.org/wiki/Daftar_kabupaten_di_Indonesia";
        final String outputFile = "kabupaten.csv";

        try {
            // Fetch the document from the URL
            Document doc = Jsoup.connect(url).get();

            // Select the table containing the data
            Element table = doc.select("table.wikitable").first();

            // Prepare CSV writer
            CSVWriter writer = new CSVWriter(new FileWriter(outputFile));

            // Write CSV header
            writer.writeNext(new String[]{
                "No", 
                "Kode Kemendagri", 
                "Kabupaten", 
                "Provinsi", 
                "Ibu Kota", 
                "Kecamatan", 
                "Kecamatan/Desa"
            });

            // Extract rows from the table
            Elements rows = table.select("tr");
            for (Element row : rows) {
                Elements cols = row.select("td");
                if (cols.size() > 1) {
                    String no = cols.get(0).text();
                    String kodeKemendagri = cols.get(1).text();
                    String kabupaten = cols.get(2).text();
                    String provinsi = cols.get(3).text();
                    String ibuKota = cols.get(4).text();
                    String kecamatan = cols.get(5).text();
                    String kelurahanAtauDesa = cols.get(6).text();
                    writer.writeNext(new String[]{
                        no, 
                        kodeKemendagri,
                        kabupaten,
                        provinsi,
                        ibuKota,
                        kecamatan,
                        kelurahanAtauDesa
                    });
                }
            }

            // Close the writer
            writer.close();

            System.out.println("Data berhasil diambil dan disimpan ke " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat mengambil data.");
        }
    }
}
