package li.qiyu.productsearch.api;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

@RestController
public class ProductController {

    @GetMapping(value = "/api-proxy/service/affil/product/v2/search")
    public String search(@RequestParam("query") String query) {
        return asString("src/main/resources/static/search.json");
    }

    @GetMapping(value = "/api-proxy/service/affil/product/v2/items/{id}")
    public String product(@PathParam("id") String item) {
        return asString("src/main/resources/static/lookup.json");
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
