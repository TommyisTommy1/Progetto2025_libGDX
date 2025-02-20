package DialogPkg;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class DialogMngr {
    private HashMap<String, Dialog> dialogs = new HashMap<>();
    private static final String FILE_NAME = "src/res/dialogs/Dialog1.json";

    public DialogMngr() {
        loadDialogs();
    }

    private void loadDialogs() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                Gson gson = new Gson();
                dialogs = gson.fromJson(reader, new TypeToken<HashMap<String, Dialog>>(){}.getType());
                System.out.println("Dialogs loaded successfully!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Dialog.json not found! No dialogs loaded.");
        }
    }

    public Dialog getDialog(String character) {
        return dialogs.getOrDefault(character, new Dialog ("No dialog available.", "", ""));
    }

    public HashMap<String, Dialog> getDialogs() {
        return dialogs;
    }

    
}
