package ohtu;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
     
        BufferedReader br = new BufferedReader(new FileReader(".secred"));
        String studentNr = br.readLine();

        // System.out.println(studentNr);
        
        // String studentNr = "011120775";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        
        System.out.println("");
        System.out.println("opiskelijanumero: " +studentNr );
        System.out.println("");
        int tehtavaa = 0 ;
        double tuntia = 0 ;
        for (Submission submission : subs) {
            System.out.println(submission.toString());
            tehtavaa += submission.getExercises().size();
            tuntia += submission.getHours();
        }
        System.out.println("");
        System.out.println("Yhteensä: "+tehtavaa+ " tehtävää "+tuntia+" tuntia");

    }
}

