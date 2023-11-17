import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WebCrawler {
    public static void main(String[] args) {
        String url = "https://blinkmypc.it/build/r5-5600g-ddr4-16gb-ssd-512gb/"; // L'URL del sito web da esplorare
        crawl(url);
    }

    public static void crawl(String url) {
        try {
            Document document = Jsoup.connect(url).get(); // Ottiene il Documento HTML dalla pagina web

            int count = 0; // Contatore per tenere traccia dei link trovati

            // Trova tutti i link nella pagina
            Elements links = document.select("a[href]");
            for (Element div : document.select("div.single-offer-archive")) {
                for (Element link : div.select("a[href]")) {
                    String linkUrl = link.absUrl("href");
                    System.out.println(linkUrl);

                    count++; // Incrementa il contatore

                    if (count == 6) {
                        break; // Esce dal ciclo quando sono stati trovati 6 link
                    }
                }

                if (count == 6) {
                    break; // Esce dal ciclo esterno quando sono stati trovati 6 link
                }
            }

            // Connessione al database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bottelegram?user=localhost&password=");
            // Preparazione dell'istruzione SQL per l'inserimento
            String insertQuery = "INSERT INTO links (url) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            for (Element div : document.select("div.single-offer-archive")) {
                for (Element link : div.select("a[href]")) {
                    String linkUrl = link.absUrl("href");
                    System.out.println(linkUrl);

                    // Inserimento del link nel database
                    preparedStatement.setString(1, linkUrl);
                    preparedStatement.executeUpdate();

                    count++;

                    if (count == 6) {
                        break;
                    }
                }

                if (count == 6) {
                    break;
                }
            }

            // Chiusura della connessione e dello statement
            preparedStatement.close();
            connection.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}