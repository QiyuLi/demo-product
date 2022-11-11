package li.qiyu.productsearch.api;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

@RestController
public class ProductController {

    @GetMapping(value = "/api-proxy/service/affil/product/v2/search")
    public String search(@RequestParam("query") String query) {
        return asString("src/main/resources/static/search.json");
    }

    @GetMapping(value = "/api-proxy/service/affil/product/v2/items/{id}")
    public String product(@PathVariable("id") String itemId) {
        String json = asString("src/main/resources/static/lookup.json");
        if (Strings.isNotEmpty(itemId)) {
            try {
                int suffix = Math.abs(itemId.hashCode() % 10) + 1;
                System.out.println(suffix);
                File lookup = new File("src/main/resources/static/lookup" + suffix + ".json");
                if (lookup.isFile()) {
                    json = asString(lookup.getPath());
                }
            } catch (Exception e) {
                // ignore
            }
        }
        return json;
    }

    @GetMapping(value = "/api-proxy/service/affil/product/v2/nbp")
    public String recommendation(@RequestParam("itemId") String itemID) {
        return asString("src/main/resources/static/recommendation.json");
    }

    private static String asString(String resource) {
        try (Reader reader = new InputStreamReader(new FileInputStream(resource), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
