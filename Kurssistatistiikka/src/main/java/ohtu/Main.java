package ohtu;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi

        BufferedReader br = new BufferedReader(new FileReader(".secred"));
        String studentNr = br.readLine();

        // System.out.println(studentNr);
        // String studentNr = "011120775";
        if (args.length > 0) {
            studentNr = args[0];
        }

        Gson mapper = new Gson();

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/" + studentNr + "/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );
        String urlInfo = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        String bodyTextInfo = Request.Get(urlInfo).execute().returnContent().asString();
        //System.out.println(bodyTextInfo);

        Kurssi kurssi = mapper.fromJson(bodyTextInfo, Kurssi.class);

        //System.out.println(kurssi);
        System.out.println("");
        System.out.println("Kurssi: Ohjelmistotuotanto, syksy 2017");
        System.out.println("");
        System.out.println("opiskelijanumero: " + studentNr);
        System.out.println("");
        int tehtavaa = 0;
        double tuntia = 0;
        for (Submission submission : subs) {
            submission.setTavoiteTehtavia(kurssi.getExercises().get(submission.getWeek() - 1));
            System.out.println(submission.toString());
            tehtavaa += submission.getExercises().size();
            tuntia += submission.getHours();
        }
        System.out.println("");
        System.out.println("Yhteensä: " + tehtavaa + " tehtävää " + tuntia + " tuntia");
        System.out.println("");

        String urlKurssiStats = "https://studies.cs.helsinki.fi/ohtustats/stats";

        String statsResponse = Request.Get(urlKurssiStats).execute().returnContent().asString();

        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(statsResponse).getAsJsonObject();
        //System.out.println(parsittuData);

        int palautusta = 0;
        int tehtavia = 0;
        for (int i = 1; i <= kurssi.getWeek(); i++) {

            JsonObject jobject = parsittuData.getAsJsonObject("" + i);
            palautusta += Integer.parseInt(jobject.get("students").toString());
            tehtavia += Integer.parseInt(jobject.get("exercise_total").toString());

        }

        System.out.println("kurssilla yhteensä " + palautusta + " palautusta, palautettuja tehtäviä " + tehtavia + " kpl");
    }
}
