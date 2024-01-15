import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithJSON {
    public static ArrayList<Fabric> ReadFromFileJSON(String filename) {
        ArrayList<Fabric> people = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray list = (JSONArray) jsonParser.parse(new FileReader(filename));
            for(int i = 0; i < list.size(); ++i) {
                JSONObject object = (JSONObject) list.get(i);
                String Type = (String) object.get("Type");
                String Place = (String) object.get("Place");
                String Amount = (String) object.get("Amount");
                Fabric car = new Fabric(Type, Place, Amount);
                people.add(car);
            }
        } catch (IOException | org.json.simple.parser.ParseException ex) {
            throw new RuntimeException(ex);
        }
        return people;
    }

    public static void WriteInFileJSON(ArrayList<Fabric> p) throws IOException {
        FileWriter writer = new FileWriter("out_file.json");
        JSONArray obj = new JSONArray();
        writer.write("[ ");
        for(int i = 0; i < p.size(); ++i) {
            if(i + 1 == p.size()) {
                JSONObject object = new JSONObject();
                object.put("Type", p.get(i).getType());
                object.put("Place", p.get(i).getPlace());
                object.put("Amount", p.get(i).getAmount());
                writer.write(object.toJSONString());
                break;
            }
            JSONObject object = new JSONObject();
            object.put("Type", p.get(i).getType());
            object.put("Place", p.get(i).getPlace());
            object.put("Amount", p.get(i).getAmount());
            writer.write(object.toJSONString() + ", ");
        }
        writer.write(" ]");
        writer.flush();
    }
}