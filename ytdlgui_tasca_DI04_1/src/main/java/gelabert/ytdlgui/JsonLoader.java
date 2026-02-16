package gelabert.ytdlgui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class JsonLoader {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static List<Category> loadCategories() {
        try (InputStream is = JsonLoader.class.getResourceAsStream("/properties/categories.json")) {

            if (is == null) {
                // No s'ha trobat el fitxer al classpath
                System.err.println("categories.json not found in /properties");
                return Collections.emptyList();
            }

            return MAPPER.readValue(is, new TypeReference<List<Category>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static User loadUser() {
        try (InputStream is = JsonLoader.class.getResourceAsStream("/properties/user.json")) {

            if (is == null) {
                System.err.println("user.json not found in /properties");
                return null;
            }

            return MAPPER.readValue(is, User.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

